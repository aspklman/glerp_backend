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
import org.jeecg.modules.scan.entity.PurSupPictureDetail;
import org.jeecg.modules.scan.entity.PurSupPictureMain;
import org.jeecg.modules.scan.vo.PurSupPictureMainPage;
import org.jeecg.modules.scan.service.IPurSupPictureMainService;
import org.jeecg.modules.scan.service.IPurSupPictureDetailService;
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

 /**
 * @Description: 主表-SKU基础资料表
 * @Author: jeecg-boot
 * @Date:   2020-02-25 09:08:43
 * @Version: V1.0
 */
@RestController
@RequestMapping("/scan/purSupPictureMain")
@Slf4j
public class PurSupPictureMainController {
	@Autowired
	private IPurSupPictureMainService purSupPictureMainService;
	@Autowired
	private IPurSupPictureDetailService purSupPictureDetailService;
	
	/**
	  * 分页列表查询
	 * @param purSupPictureMain
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<PurSupPictureMain>> queryPageList(PurSupPictureMain purSupPictureMain,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<PurSupPictureMain>> result = new Result<IPage<PurSupPictureMain>>();
		QueryWrapper<PurSupPictureMain> queryWrapper = QueryGenerator.initQueryWrapper(purSupPictureMain, req.getParameterMap());
		Page<PurSupPictureMain> page = new Page<PurSupPictureMain>(pageNo, pageSize);
		IPage<PurSupPictureMain> pageList = purSupPictureMainService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	 @RequestMapping("/upload")
	 public Result upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("sku") String sku, @RequestParam("basicdataid") String basicDataId) {
		 Result result = new Result();
		 try {
			 log.info("控制文件：" + sku);
			 purSupPictureDetailService.upload(multipartFile, sku, basicDataId);
			 result.success("上传成功！");
		 }
		 catch (Exception ex) {
			 log.info(ex.getMessage(), ex);
			 result.error500("上传失败");
		 }
		 return result;
	 }

	 @RequestMapping("/remove")
	 public Result upload(@RequestParam("filename") String fileName) {
		 Result result = new Result();
		 try {
			 purSupPictureDetailService.remove(fileName);
			 result.success("上传成功！");
		 }
		 catch (Exception ex) {
			 log.info(ex.getMessage(), ex);
			 result.error500("上传失败");
		 }
		 return result;
	 }
	
	/**
	  *   添加
	 * @param purSupPictureMainPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<PurSupPictureMain> add(@RequestBody PurSupPictureMainPage purSupPictureMainPage) {
		Result<PurSupPictureMain> result = new Result<PurSupPictureMain>();
		try {
			PurSupPictureMain purSupPictureMain = new PurSupPictureMain();
			BeanUtils.copyProperties(purSupPictureMainPage, purSupPictureMain);
			purSupPictureMainService.saveMain(purSupPictureMain, purSupPictureMainPage.getPurSupPictureDetailList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param purSupPictureMain
	 * @return
	 */
//	@PutMapping(value = "/edit")
//	public Result<PurSupPictureMain> edit(@RequestBody PurSupPictureMainPage purSupPictureMainPage) {
//		Result<PurSupPictureMain> result = new Result<PurSupPictureMain>();
//		PurSupPictureMain purSupPictureMain = purSupPictureMainService.getById(purSupPictureMainPage.getId());
//		if(purSupPictureMain==null) {
//			result.error500("未找到对应实体");
//		}else {
//			purSupPictureMainPage.setUpdateTime(new Date());
//			purSupPictureMainService.updateById(purSupPictureMain);
//			purSupPictureMainService.updateMain(purSupPictureMain, purSupPictureMainPage.getPurSupPictureDetailList());
//			result.success("修改成功!");
//		}
//		return result;
//	}
	
	//此段在更新时,主表【修改时间】字段不会更新，子表【创建时间】字段会修改为当天的日期，子表【修改时间】字段不会更新？
	@PutMapping(value = "/edit")
	public Result<PurSupPictureMain> edit(@RequestBody PurSupPictureMainPage purSupPictureMainPage) {
		Result<PurSupPictureMain> result = new Result<PurSupPictureMain>();
		PurSupPictureMain purSupPictureMain = new PurSupPictureMain();
		BeanUtils.copyProperties(purSupPictureMainPage, purSupPictureMain);
		PurSupPictureMain purSupPictureMainEntity = purSupPictureMainService.getById(purSupPictureMain.getId());
		if(purSupPictureMainEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = purSupPictureMainService.updateById(purSupPictureMain);
			purSupPictureMainService.updateMain(purSupPictureMain, purSupPictureMainPage.getPurSupPictureDetailList());
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
			purSupPictureMainService.delMain(id);
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
	public Result<PurSupPictureMain> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<PurSupPictureMain> result = new Result<PurSupPictureMain>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.purSupPictureMainService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<PurSupPictureMain> queryById(@RequestParam(name="id",required=true) String id) {
		Result<PurSupPictureMain> result = new Result<PurSupPictureMain>();
		PurSupPictureMain purSupPictureMain = purSupPictureMainService.getById(id);
		if(purSupPictureMain==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(purSupPictureMain);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryPurSupPictureDetailByMainId")
	public Result<List<PurSupPictureDetail>> queryPurSupPictureDetailListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<PurSupPictureDetail>> result = new Result<List<PurSupPictureDetail>>();
		List<PurSupPictureDetail> purSupPictureDetailList = purSupPictureDetailService.selectByMainId(id);
		result.setResult(purSupPictureDetailList);
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
      QueryWrapper<PurSupPictureMain> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              PurSupPictureMain purSupPictureMain = JSON.parseObject(deString, PurSupPictureMain.class);
              queryWrapper = QueryGenerator.initQueryWrapper(purSupPictureMain, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<PurSupPictureMainPage> pageList = new ArrayList<PurSupPictureMainPage>();
      List<PurSupPictureMain> purSupPictureMainList = purSupPictureMainService.list(queryWrapper);
      for (PurSupPictureMain purSupPictureMain : purSupPictureMainList) {
          PurSupPictureMainPage vo = new PurSupPictureMainPage();
          BeanUtils.copyProperties(purSupPictureMain, vo);
          List<PurSupPictureDetail> purSupPictureDetailList = purSupPictureDetailService.selectByMainId(purSupPictureMain.getId());
          vo.setPurSupPictureDetailList(purSupPictureDetailList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "主表-SKU基础资料表列表");
      mv.addObject(NormalExcelConstants.CLASS, PurSupPictureMainPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("主表-SKU基础资料表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<PurSupPictureMainPage> list = ExcelImportUtil.importExcel(file.getInputStream(), PurSupPictureMainPage.class, params);
              for (PurSupPictureMainPage page : list) {
                  PurSupPictureMain po = new PurSupPictureMain();
                  BeanUtils.copyProperties(page, po);
                  purSupPictureMainService.saveMain(po, page.getPurSupPictureDetailList());
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
