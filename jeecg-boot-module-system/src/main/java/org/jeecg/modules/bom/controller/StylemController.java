package org.jeecg.modules.bom.controller;

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
import org.jeecg.modules.bom.entity.Styles;
import org.jeecg.modules.bom.entity.Stylem;
import org.jeecg.modules.bom.vo.StylemPage;
import org.jeecg.modules.order.entity.Odrm;
import org.jeecg.modules.order.vo.OdrmPage;
import org.jeecg.modules.bom.service.IStylemService;
import org.jeecg.modules.bom.service.IStylesService;
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
 * @Description: 鞋型基本资料表
 * @Author: jeecg-boot
 * @Date:   2019-08-07 09:08:29
 * @Version: V1.0
 */
@RestController
@RequestMapping("/bom/stylem")
@Slf4j
public class StylemController {
	@Autowired
	private IStylemService stylemService;
	@Autowired
	private IStylesService stylesService;
	
	/**
	  * 分页列表查询
	 * @param stylem
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<Stylem>> queryPageList(Stylem stylem,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Stylem>> result = new Result<IPage<Stylem>>();
		QueryWrapper<Stylem> queryWrapper = QueryGenerator.initQueryWrapper(stylem, req.getParameterMap());
		Page<Stylem> page = new Page<Stylem>(pageNo, pageSize);
		IPage<Stylem> pageList = stylemService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param stylemPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Stylem> add(@RequestBody StylemPage stylemPage) {
		Result<Stylem> result = new Result<Stylem>();
		try {
			Stylem stylem = new Stylem();
			BeanUtils.copyProperties(stylemPage, stylem);
			stylemService.saveMain(stylem, stylemPage.getStylesList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param stylem
	 * @return
	 */
//	@PutMapping(value = "/edit")
//	public Result<Stylem> edit(@RequestBody Stylem stylem) {
//		Result<Stylem> result = new Result<Stylem>();
//		Stylem stylemEntity = stylemService.getById(stylem.getId());
//		if(stylemEntity==null) {
//			result.error500("未找到对应实体");
//		}else {
//			stylem.setUpdateTime(new Date());
//			boolean ok = stylemService.updateById(stylem);
//			//TODO 返回false说明什么？
//			if(ok) {
//				result.success("修改成功!");
//			}
//		}
//		return result;
//	}
	
	//此段在更新时,会将【创建时间】修改为当天的日期
	@PutMapping(value = "/edit")
	public Result<Stylem> edit(@RequestBody StylemPage stylemPage) {
		Result<Stylem> result = new Result<Stylem>();
		Stylem stylem = new Stylem();
		BeanUtils.copyProperties(stylemPage, stylem);
		Stylem stylemEntity = stylemService.getById(stylem.getId());
		if(stylemEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = stylemService.updateById(stylem);
			stylemService.updateMain(stylem, stylemPage.getStylesList());
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
			stylemService.delMain(id);
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
	public Result<Stylem> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Stylem> result = new Result<Stylem>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.stylemService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<Stylem> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Stylem> result = new Result<Stylem>();
		Stylem stylem = stylemService.getById(id);
		if(stylem==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(stylem);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryStylesByMainId")
	public Result<List<Styles>> queryStylesListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<Styles>> result = new Result<List<Styles>>();
		List<Styles> stylesList = stylesService.selectByMainId(id);
		result.setResult(stylesList);
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
      QueryWrapper<Stylem> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Stylem stylem = JSON.parseObject(deString, Stylem.class);
              queryWrapper = QueryGenerator.initQueryWrapper(stylem, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<StylemPage> pageList = new ArrayList<StylemPage>();
      List<Stylem> stylemList = stylemService.list(queryWrapper);
      for (Stylem stylem : stylemList) {
          StylemPage vo = new StylemPage();
          BeanUtils.copyProperties(stylem, vo);
          List<Styles> stylesList = stylesService.selectByMainId(stylem.getId());
          vo.setStylesList(stylesList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "鞋型基本资料表列表");
      mv.addObject(NormalExcelConstants.CLASS, StylemPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("鞋型基本资料表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<StylemPage> list = ExcelImportUtil.importExcel(file.getInputStream(), StylemPage.class, params);
              for (StylemPage page : list) {
                  Stylem po = new Stylem();
                  BeanUtils.copyProperties(page, po);
                  stylemService.saveMain(po, page.getStylesList());
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
