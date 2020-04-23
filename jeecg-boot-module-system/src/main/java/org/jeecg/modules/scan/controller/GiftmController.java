package org.jeecg.modules.scan.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.scan.entity.Giftd;
import org.jeecg.modules.scan.entity.Giftm;
import org.jeecg.modules.scan.vo.GiftmPage;
import org.jeecg.modules.system.service.ISysDataLogService;
import org.jeecg.modules.scan.service.IGiftmService;
import org.jeecg.modules.scan.service.IGiftdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

 /**
 * @Description: 礼品鞋主表
 * @Author: jeecg-boot
 * @Date:   2019-08-20 14:36:39
 * @Version: V1.0
 */
@RestController
@RequestMapping("/scan/giftm")
@Slf4j
public class GiftmController {
	@Autowired
	private IGiftmService giftmService;
	@Autowired
	private IGiftdService giftdService;
	@Autowired
	private ISysDataLogService sysDataLogService;
	
	/**
	  * 分页列表查询
	 * @param giftm
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<Giftm>> queryPageList(Giftm giftm,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Giftm>> result = new Result<IPage<Giftm>>();
		QueryWrapper<Giftm> queryWrapper = QueryGenerator.initQueryWrapper(giftm, req.getParameterMap());
		Page<Giftm> page = new Page<Giftm>(pageNo, pageSize);
		IPage<Giftm> pageList = giftmService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param giftmPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Giftm> add(@RequestBody GiftmPage giftmPage) {
		Result<Giftm> result = new Result<Giftm>();
		try {
			Giftm giftm = new Giftm();
			BeanUtils.copyProperties(giftmPage, giftm);
			giftmService.saveMain(giftm, giftmPage.getGiftdList());
			sysDataLogService.addDataLog("giftm", giftm.getId(), JSONObject.toJSONString(giftm));		//记录数据快照
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param giftm
	 * @return
	 */
//	@PutMapping(value = "/edit")
//	public Result<Giftm> edit(@RequestBody GiftmPage giftmPage) {
//		Result<Giftm> result = new Result<Giftm>();
//		Giftm giftm = giftmService.getById(giftmPage.getId());
//		if(giftm==null) {
//			result.error500("未找到对应实体");
//		}else {
//			giftmPage.setUpdateTime(new Date());
//			giftmService.updateById(giftm);
//			giftmService.updateMain(giftm, giftmPage.getGiftdList());
//			result.success("修改成功!");
//		}
//		return result;
//	}
	@PutMapping(value = "/edit")
	public Result<Giftm> edit(@RequestBody GiftmPage giftmPage) {
		Result<Giftm> result = new Result<Giftm>();
		Giftm giftm = new Giftm();
		BeanUtils.copyProperties(giftmPage, giftm);
		Giftm giftmEntity = giftmService.getById(giftm.getId());
		if(giftmEntity==null) {
			result.error500("未找到对应实体");
		}else {
			sysDataLogService.addDataLog("giftm", giftm.getId(), JSONObject.toJSONString(giftm));		//记录数据快照
			boolean ok = giftmService.updateById(giftm);
			giftmService.updateMain(giftm, giftmPage.getGiftdList());
			result.success("修改成功!");
		}
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			giftmService.delMain(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<Giftm> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Giftm> result = new Result<Giftm>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.giftmService.delBatchMain(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<Giftm> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Giftm> result = new Result<Giftm>();
		Giftm giftm = giftmService.getById(id);
		if(giftm==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(giftm);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryGiftdByMainId")
	public Result<List<Giftd>> queryGiftdListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<Giftd>> result = new Result<List<Giftd>>();
		List<Giftd> giftdList = giftdService.selectByMainId(id);
		result.setResult(giftdList);
		result.setSuccess(true);
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Giftm> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Giftm giftm = JSON.parseObject(deString, Giftm.class);
              queryWrapper = QueryGenerator.initQueryWrapper(giftm, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<GiftmPage> pageList = new ArrayList<GiftmPage>();
      List<Giftm> giftmList = giftmService.list(queryWrapper);
      for (Giftm giftm : giftmList) {
          GiftmPage vo = new GiftmPage();
          BeanUtils.copyProperties(giftm, vo);
          List<Giftd> giftdList = giftdService.selectByMainId(giftm.getId());
          vo.setGiftdList(giftdList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "礼品鞋主表列表");
      mv.addObject(NormalExcelConstants.CLASS, GiftmPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("礼品鞋主表列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

  /**
      * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<GiftmPage> list = ExcelImportUtil.importExcel(file.getInputStream(), GiftmPage.class, params);
              for (GiftmPage page : list) {
                  Giftm po = new Giftm();
                  BeanUtils.copyProperties(page, po);
                  giftmService.saveMain(po, page.getGiftdList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

}
