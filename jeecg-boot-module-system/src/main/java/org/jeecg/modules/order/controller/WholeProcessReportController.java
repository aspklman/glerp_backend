package org.jeecg.modules.order.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.order.entity.WholeProcessReport;
import org.jeecg.modules.order.service.IWholeProcessReportService;
import java.util.Date;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 全流程报表
 * @Author: jeecg-boot
 * @Date:   2020-07-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全流程报表")
@RestController
@RequestMapping("/order/wholeProcessReport")
public class WholeProcessReportController {
	@Autowired
	private IWholeProcessReportService wholeProcessReportService;
	
	/**
	  * 分页列表查询
	 * @param wholeProcessReport
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全流程报表-分页列表查询")
	@ApiOperation(value="全流程报表-分页列表查询", notes="全流程报表-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "order/WholeProcessReportList")
	public Result<IPage<WholeProcessReport>> queryPageList(WholeProcessReport wholeProcessReport,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WholeProcessReport>> result = new Result<IPage<WholeProcessReport>>();
		QueryWrapper<WholeProcessReport> queryWrapper = QueryGenerator.initQueryWrapper(wholeProcessReport, req.getParameterMap());
//		queryWrapper.and(wrapper -> wrapper.eq("出货状况", "N"));
		queryWrapper.orderByDesc("订单交期", "fact_odr_no_in");
		Page<WholeProcessReport> page = new Page<WholeProcessReport>(pageNo, pageSize);
		IPage<WholeProcessReport> pageList = wholeProcessReportService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	 /**
	  * SIZE数量查询
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "全流程报表-SIZE数量查询")
	 @ApiOperation(value = "全流程报表-SIZE数量查询", notes = "全流程报表-SIZE数量查询")
	 @GetMapping(value = "/getSizeQty")
	 public Result<List<Map<String, Object>>> getSizeQty(@RequestParam("pssr") String pssr) {
		 Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
//			 log.info("厂别：" + factNo);
			 String factOdrNo = pssr.trim().toString();
//			 log.info("工厂订单：" + factOdrNo + '/');
			 List<Map<String, Object>> sizeQty = this.wholeProcessReportService.getSizeQty(factNo, factOdrNo);
			 result.setResult(sizeQty);
			 result.success("获取成功!");
		 }
		 log.info("结果：" + result);
		 return result;
	 }

	 /**
	  * 库存数据查询
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "全流程报表-库存数据查询")
	 @ApiOperation(value = "全流程报表-库存数据查询", notes = "全流程报表-库存数据查询")
	 @GetMapping(value = "/getStockData")
	 public Result<Map<String, Object>> getStockData(@RequestParam("pssr") String pssr) {
		 Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
//			 log.info("厂别：" + factNo);
			 String factOdrNo = pssr.trim().toString();
//			 log.info("工厂订单：" + factOdrNo + '/');
			 Map<String, Object> stockData = this.wholeProcessReportService.getStockData(factNo, factOdrNo);
			 result.setResult(stockData);
			 result.success("获取成功!");
		 }
		 log.info("结果：" + result);
		 return result;
	 }

	 /**
	  * 验货报告查询
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "全流程报表-验货报告查询")
	 @ApiOperation(value = "全流程报表-验货报告查询", notes = "全流程报表-验货报告查询")
	 @GetMapping(value = "/getInspectReport")
	 public Result<Map<String, Object>> getInspectReport(@RequestParam("pssr") String pssr) {
		 Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
//			 log.info("厂别：" + factNo);
			 String custOdrNo = pssr.trim().toString();
//			 log.info("工厂订单：" + custOdrNo + '/');
			 Map<String, Object> stockData = this.wholeProcessReportService.getInspectReport(factNo, custOdrNo);
			 result.setResult(stockData);
			 result.success("获取成功!");
		 }
		 log.info("结果：" + result);
		 return result;
	 }
	
	/**
	  *   添加
	 * @param wholeProcessReport
	 * @return
	 */
	@AutoLog(value = "全流程报表-添加")
	@ApiOperation(value="全流程报表-添加", notes="全流程报表-添加")
	@PostMapping(value = "/add")
	public Result<WholeProcessReport> add(@RequestBody WholeProcessReport wholeProcessReport) {
		Result<WholeProcessReport> result = new Result<WholeProcessReport>();
		try {
			wholeProcessReportService.save(wholeProcessReport);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wholeProcessReport
	 * @return
	 */
	@AutoLog(value = "全流程报表-编辑")
	@ApiOperation(value="全流程报表-编辑", notes="全流程报表-编辑")
	@PutMapping(value = "/edit")
	public Result<WholeProcessReport> edit(@RequestBody WholeProcessReport wholeProcessReport) {
		Result<WholeProcessReport> result = new Result<WholeProcessReport>();
		WholeProcessReport wholeProcessReportEntity = wholeProcessReportService.getById(wholeProcessReport.getId());
		if(wholeProcessReportEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wholeProcessReportService.updateById(wholeProcessReport);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全流程报表-通过id删除")
	@ApiOperation(value="全流程报表-通过id删除", notes="全流程报表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wholeProcessReportService.removeById(id);
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
	@AutoLog(value = "全流程报表-批量删除")
	@ApiOperation(value="全流程报表-批量删除", notes="全流程报表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<WholeProcessReport> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WholeProcessReport> result = new Result<WholeProcessReport>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wholeProcessReportService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全流程报表-通过id查询")
	@ApiOperation(value="全流程报表-通过id查询", notes="全流程报表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WholeProcessReport> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WholeProcessReport> result = new Result<WholeProcessReport>();
		WholeProcessReport wholeProcessReport = wholeProcessReportService.getById(id);
		if(wholeProcessReport==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wholeProcessReport);
			result.setSuccess(true);
		}
		return result;
	}

     /**
      * 导出excel
      *
      * @param request
      */
     @RequestMapping(value = "/exportXls")
     public ModelAndView exportXls(HttpServletRequest request, WholeProcessReport wholeProcessReport) {
         // Step.1 组装查询条件查询数据
         QueryWrapper<WholeProcessReport> queryWrapper = QueryGenerator.initQueryWrapper(wholeProcessReport, request.getParameterMap());
         List<WholeProcessReport> pageList = wholeProcessReportService.list(queryWrapper);
         // Step.2 AutoPoi 导出Excel
         ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
         mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
         //导出文件名称
         mv.addObject(NormalExcelConstants.FILE_NAME, "全流程报表");
         mv.addObject(NormalExcelConstants.CLASS, WholeProcessReport.class);
         LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
         mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全流程报表", "导出人:"+user.getRealname(), "全流程报表"));
         return mv;
     }

//  /**
//      * 导出excel
//   *
//   * @param request
//   * @param response
//   */
//  @RequestMapping(value = "/exportXls")
//  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
//      // Step.1 组装查询条件
//      QueryWrapper<WholeProcessReport> queryWrapper = null;
//      try {
//          String paramsStr = request.getParameter("paramsStr");
//          if (oConvertUtils.isNotEmpty(paramsStr)) {
//              String deString = URLDecoder.decode(paramsStr, "UTF-8");
//              WholeProcessReport wholeProcessReport = JSON.parseObject(deString, WholeProcessReport.class);
//              queryWrapper = QueryGenerator.initQueryWrapper(wholeProcessReport, request.getParameterMap());
//          }
//      } catch (UnsupportedEncodingException e) {
//          e.printStackTrace();
//      }
//
//      //Step.2 AutoPoi 导出Excel
////      queryWrapper.and(wrapper -> wrapper.eq("订单交期", "20201105"));
//      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
//      List<WholeProcessReport> pageList = wholeProcessReportService.list(queryWrapper);
//      //导出文件名称
//      mv.addObject(NormalExcelConstants.FILE_NAME, "全流程报表列表");
//      mv.addObject(NormalExcelConstants.CLASS, WholeProcessReport.class);
//      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全流程报表列表数据", "导出人:Jeecg", "导出信息"));
//      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
//      return mv;
//  }

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
              List<WholeProcessReport> listWholeProcessReports = ExcelImportUtil.importExcel(file.getInputStream(), WholeProcessReport.class, params);
              wholeProcessReportService.saveBatch(listWholeProcessReports);
              return Result.ok("文件导入成功！数据行数:" + listWholeProcessReports.size());
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
