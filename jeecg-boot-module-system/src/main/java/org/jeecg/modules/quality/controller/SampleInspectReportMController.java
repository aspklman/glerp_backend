package org.jeecg.modules.quality.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.quality.entity.SampleInspectReportD;
import org.jeecg.modules.quality.entity.SampleInspectReportM;
import org.jeecg.modules.quality.vo.SampleInspectReportMPage;
import org.jeecg.modules.quality.service.ISampleInspectReportMService;
import org.jeecg.modules.quality.service.ISampleInspectReportDService;
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
 * @Description: 验货报告主表
 * @Author: jeecg-boot
 * @Date:   2020-08-15 14:58:39
 * @Version: V1.0
 */
@RestController
@RequestMapping("/quality/sampleInspectReportM")
@Slf4j
public class SampleInspectReportMController {
	@Autowired
	private ISampleInspectReportMService sampleInspectReportMService;
	@Autowired
	private ISampleInspectReportDService sampleInspectReportDService;
	
	/**
	  * 分页列表查询
	 * @param sampleInspectReportM
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<SampleInspectReportM>> queryPageList(SampleInspectReportM sampleInspectReportM,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<SampleInspectReportM>> result = new Result<IPage<SampleInspectReportM>>();
		QueryWrapper<SampleInspectReportM> queryWrapper = QueryGenerator.initQueryWrapper(sampleInspectReportM, req.getParameterMap());
		Page<SampleInspectReportM> page = new Page<SampleInspectReportM>(pageNo, pageSize);
		IPage<SampleInspectReportM> pageList = sampleInspectReportMService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	 /**
	  * 取样数量
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "验货报告主表-取样数量")
	 @ApiOperation(value = "验货报告主表-取样数量", notes = "验货报告主表-取样数量")
	 @GetMapping(value = "/getSampleQty")
	 public Result<Map<String, Object>> getSampleQty(@RequestParam("pssr") String pssr) {
		 Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String factOdrNo = pssr.trim();
			 Map<String, Object> sampleQty = this.sampleInspectReportMService.getSampleQty(factNo, factOdrNo);
			 result.setResult(sampleQty);
			 result.success("获取成功!");
		 }
		 log.info("结果：" + result);
		 return result;
	 }

	 /**
	  * 缺陷数量
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "验货报告主表-缺陷数量")
	 @ApiOperation(value = "验货报告主表-缺陷数量", notes = "验货报告主表-缺陷数量")
	 @GetMapping(value = "/getDefectQty")
	 public Result<List<Map<String, Object>>> getDefectQty(@RequestParam("pssr") String pssr) {
		 Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String[] p = pssr.split(",");
			 String factOdrNo = p[0];
			 String itemMainNo = p[1];
			 String versionNo = p[2];
			 log.info("参数：" + factNo + '/' + factOdrNo + '/' + itemMainNo + '/' + versionNo);
			 List<Map<String, Object>> defectQty = this.sampleInspectReportMService.getDefectQty(factNo, factOdrNo, itemMainNo, versionNo);
			 result.setResult(defectQty);
			 result.success("获取成功!");
		 }
		 log.info("结果：" + result);
		 return result;
	 }

	 /**
	  * 检查点
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "验货报告主表-检查点")
	 @ApiOperation(value = "验货报告主表-检查点", notes = "验货报告主表-检查点")
	 @GetMapping(value = "/getCheckPoints")
	 public Result<List<Map<String, Object>>> getCheckPoints(@RequestParam("pssr") String pssr) {
		 Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String[] p = pssr.split(",");
			 String custOdrNo = p[0];
			 String versionNo = p[1];
			 String styleShorten = p[2];
			 String itemMainNo = p[3];
			 String itemMediumNo = p[4];
			 log.info("参数：" + factNo + '/' + custOdrNo + '/' +versionNo + '/' + styleShorten + '/' + itemMainNo + '/' +  itemMediumNo);
			 List<Map<String, Object>> checkPoints = this.sampleInspectReportMService.getCheckPoints(factNo, custOdrNo, versionNo, styleShorten, itemMainNo, itemMediumNo);
			 result.setResult(checkPoints);
			 result.success("获取成功!");
		 }
		 log.info("结果：" + result);
		 return result;
	 }

	 /**
	  * 增加缺陷数量
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "验货报告主表-增加缺陷数量")
	 @ApiOperation(value = "验货报告主表-增加缺陷数量", notes = "验货报告主表-增加缺陷数量")
	 @GetMapping(value = "/addMinor")
	 public void addMinor(@RequestParam("pssr") String pssr) {
		 Result result = new Result();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String[] p = pssr.split(",");
			 String minorField = "";
			 String majorField = "";
			 String criticalField = "";
			 String custOdrNo = p[0];
			 String versionNo = p[1];
			 String styleShorten = p[2];
			 String itemMainNo = p[3];
			 String itemMediumNo = p[4];
			 String checkPointsNo = p[5];
			 String updateField = p[6];
			 if (updateField.equals("minor")) {
				 minorField = "minor";
			 } else if (updateField.equals("major")) {
				 majorField = "major";
			 } else if (updateField.equals("critical")) {
				 criticalField = "critical";
			 }
			 log.info("参数8：" + factNo + "/" + custOdrNo + "/" +  versionNo + "/" +  styleShorten + "/" +  itemMainNo + "/" +  itemMediumNo + "/" +  checkPointsNo + "/" +  minorField + "/" +  majorField + "/" +  criticalField);
			 this.sampleInspectReportMService.addMinor(factNo, custOdrNo, versionNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo, minorField, majorField, criticalField);
			 result.success("获取成功!");
		 }
//         log.info("结果：" + result);
//         return result;
	 }

	 /**
	  * 减少缺陷数量
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "验货报告主表-减少缺陷数量")
	 @ApiOperation(value = "验货报告主表-减少缺陷数量", notes = "验货报告主表-减少缺陷数量")
	 @GetMapping(value = "/subtractMinor")
	 public void subtractMinor(@RequestParam("pssr") String pssr) {
		 Result result = new Result();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String[] p = pssr.split(",");
			 String minorField = "";
			 String majorField = "";
			 String criticalField = "";
			 String custOdrNo = p[0];
			 String versionNo = p[1];
			 String styleShorten = p[2];
			 String itemMainNo = p[3];
			 String itemMediumNo = p[4];
			 String checkPointsNo = p[5];
			 String updateField = p[6];
			 if (updateField.equals("minor")) {
				 minorField = "minor";
			 } else if (updateField.equals("major")) {
				 majorField = "major";
			 } else if (updateField.equals("critical")) {
				 criticalField = "critical";
			 }
			 log.info("参数8：" + factNo + "/" + custOdrNo + "/" +  versionNo + "/" +  styleShorten + "/" +  itemMainNo + "/" +  itemMediumNo + "/" +  checkPointsNo + "/" +  minorField + "/" +  majorField + "/" +  criticalField);
			 this.sampleInspectReportMService.subtractMinor(factNo, custOdrNo, versionNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo, minorField, majorField, criticalField);
			 result.success("获取成功!");
		 }
//         log.info("结果：" + result);
//         return result;
	 }

	 /**
	  * 更新验货结果
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "验货报告主表-更新验货结果")
	 @ApiOperation(value = "验货报告主表-更新验货结果", notes = "验货报告主表-更新验货结果")
	 @GetMapping(value = "/updateInspectorDecision")
	 public void updateInspectorDecision(@RequestParam("pssr") String pssr) {
		 Result result = new Result();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String[] p = pssr.split(",");
			 String custOdrNo = p[0];
			 String versionNo = p[1];
			 String styleShorten = p[2];
			 String inspectorDecision = p[3];
			 log.info("参数5：" + factNo + "/" + custOdrNo + "/" +  versionNo + "/" +  styleShorten + "/" +  inspectorDecision);
			 this.sampleInspectReportMService.updateInspectorDecision(factNo, custOdrNo, versionNo, styleShorten, inspectorDecision);
			 result.success("获取成功!");
		 }
//         log.info("结果：" + result);
//         return result;
	 }

	 /**
	  * 新增-查询客户订单
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "验货报告主表-新增-查询客户订单")
	 @ApiOperation(value = "验货报告主表-新增-查询客户订单", notes = "验货报告主表-新增-查询客户订单")
	 @GetMapping(value = "/queryCustOdrNo")
	 public Result<Integer> queryCustOdrNo(@RequestParam("pssr") String pssr) {
		 Result<Integer> result = new Result<Integer>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String[] p = pssr.split(",");
			 String custOdrNo = p[0];
			 log.info("查询订单：" + factNo + "/" + custOdrNo);
			 Integer qty = this.sampleInspectReportMService.queryCustOdrNo(factNo, custOdrNo);
			 result.setResult(qty);
			 result.success("获取成功!");
		 }
         log.info("结果：" + result);
         return result;
	 }

	 /**
	  * 新增-插入主档
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "验货报告主表-新增-插入主档")
	 @ApiOperation(value = "验货报告主表-新增-插入主档", notes = "验货报告主表-新增-插入主档")
	 @GetMapping(value = "/insertReportM")
	 public Result<?> insertReportM(@RequestParam("pssr") String pssr) {
		 Result result = new Result();
//		 if (paramReportM == null) {
//			 result.error500("参数不识别！");
//		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
//			 String custOdrNo = sampleInspectReportM.custOdrNo;
//			 String versionNo = paramReportM.versionNo;
//			 String paceCode = paramReportM.paceCode;
//			 String modelColour = paramReportM.modelColour;
//			 String orderType = paramReportM.orderType;
			 String[] p = pssr.split(",");
			 String custOdrNo = p[0];
			 String versionNo = p[1];
//			 String paceCode = p[2];
//			 String modelColour = p[3];
			 String orderType = p[4];

//			 String custOdrNo = (String) object.get("custOdrNo");
//			 String versionNo = (String) object.get("versionNo");
//			 String paceCode = (String) object.get("paceCode");
//			 String modelColour = (String) object.get("modelColour");
//			 String orderType = (String) object.get("orderType");
			 String createBy = loginUser.getUsername();
//			 log.info("插入主档：" + factNo + "/" + custOdrNo + "/" + versionNo + "/" +paceCode + "/" + modelColour + "/" + orderType + "/" + createBy);
			 log.info("插入主档：" + factNo + "/" + custOdrNo + "/" + versionNo + "/" + orderType + "/" + createBy);
//			 this.sampleInspectReportMService.insertReportM(factNo, custOdrNo, versionNo, paceCode, modelColour, orderType, createBy);
		try {
			this.sampleInspectReportMService.insertReportM(factNo, custOdrNo, versionNo, orderType, createBy);
		} catch(Exception e) {
			return result.error500("新增失败！" + e.getMessage());
		}

//			 result.setResult(qty);
			 return result.ok("新增成功!");
//		 }
//         log.info("结果：" + result);
//         return result;
	 }

	 /**
	  * 新增-插入子档
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "验货报告主表-新增-插入子档")
	 @ApiOperation(value = "验货报告主表-新增-插入子档", notes = "验货报告主表-新增-插入子档")
	 @GetMapping(value = "/insertReportD")
	 public Result<?> insertReportD(@RequestParam("pssr") String pssr) {
		 Result result = new Result();
//		 if (pssr == null) {
//			 result.error500("参数不识别！");
//		 } else {
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			String factNo = loginUser.getFactNo();
			String[] p = pssr.split(",");
			String custOdrNo = p[0];
			String versionNo = p[1];
		    String createBy = loginUser.getUsername();
			log.info("插入子档：" + factNo + "/" + custOdrNo + "/" + versionNo + "/" + createBy);
			try {
				this.sampleInspectReportMService.insertReportD(factNo, custOdrNo, versionNo, createBy);
			} catch (Exception e) {
				return result.error500("新增失败！");
			}

			return result.ok("新增成功！");
//		 }
	 }

	 /**
	  * 提交问题
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "验货报告主表-提交问题")
	 @ApiOperation(value = "验货报告主表-提交问题", notes = "验货报告主表-提交问题")
	 @GetMapping(value = "/addQuestion")
	 public Result<?> addQuestion(@RequestParam("pssr") String pssr) {
		 Result result = new Result();
//		 if (pssr == null) {
//			 result.error500("参数不识别！");
//		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String[] p = pssr.split(",");
//			 String minorField = "";
//			 String majorField = "";
//			 String criticalField = "";
			 String custOdrNo = p[0];
			 String versionNo = p[1];
			 String styleShorten = p[2];
			 String itemMainNo = p[3];
			 String itemMediumNo = p[4];
			 String checkPointsNo = p[5];
			 String findQuestion = p[6];
//			 if (updateField.equals("minor")) {
//				 minorField = "minor";
//			 } else if (updateField.equals("major")) {
//				 majorField = "major";
//			 } else if (updateField.equals("critical")) {
//				 criticalField = "critical";
//			 }
			 log.info("参数8：" + factNo + "/" + custOdrNo + "/" +  versionNo + "/" +  styleShorten + "/" +  itemMainNo + "/" +  itemMediumNo + "/" +  checkPointsNo + "/" +  findQuestion);
			 try {
				 this.sampleInspectReportMService.addQuestion(factNo, custOdrNo, versionNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo, findQuestion);
			 } catch (Exception e) {
			 	return result.error500("提交失败！");
			 }
			 return result.ok("提交成功！");
//		 }
//         log.info("结果：" + result);
//         return result;
	 }

	 /**
	  * 删除问题
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "验货报告主表-删除问题")
	 @ApiOperation(value = "验货报告主表-删除问题", notes = "验货报告主表-删除问题")
	 @GetMapping(value = "/subtractQuestion")
	 public Result<?> subtractQuestion(@RequestParam("pssr") String pssr) {
		 Result result = new Result();
//		 if (pssr == null) {
//			 result.error500("参数不识别！");
//		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String[] p = pssr.split(",");
			 String custOdrNo = p[0];
			 String versionNo = p[1];
			 String styleShorten = p[2];
			 String itemMainNo = p[3];
			 String itemMediumNo = p[4];
			 String checkPointsNo = p[5];
			 log.info("参数7：" + factNo + "/" + custOdrNo + "/" +  versionNo + "/" +  styleShorten + "/" +  itemMainNo + "/" +  itemMediumNo + "/" +  checkPointsNo);
			 try {
				 this.sampleInspectReportMService.subtractQuestion(factNo, custOdrNo, versionNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo);
			 } catch (Exception e) {
			 	return result.error500("删除失败！");
			 }
			 return result.success("删除成功！");
//		 }
//         log.info("结果：" + result);
//         return result;
	 }
	
	/**
	  *   添加
	 * @param sampleInspectReportMPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<SampleInspectReportM> add(@RequestBody SampleInspectReportMPage sampleInspectReportMPage) {
		Result<SampleInspectReportM> result = new Result<SampleInspectReportM>();
		try {
			SampleInspectReportM sampleInspectReportM = new SampleInspectReportM();
			BeanUtils.copyProperties(sampleInspectReportMPage, sampleInspectReportM);
			sampleInspectReportMService.saveMain(sampleInspectReportM, sampleInspectReportMPage.getSampleInspectReportDList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param sampleInspectReportM
	 * @return
	 */
//	@PutMapping(value = "/edit")
//	public Result<SampleInspectReportM> edit(@RequestBody SampleInspectReportMPage sampleInspectReportMPage) {
//		Result<SampleInspectReportM> result = new Result<SampleInspectReportM>();
//		SampleInspectReportM sampleInspectReportM = sampleInspectReportMService.getById(sampleInspectReportMPage.getId());
//		if(sampleInspectReportM==null) {
//			result.error500("未找到对应实体");
//		}else {
//			sampleInspectReportMPage.setUpdateTime(new Date());
//			sampleInspectReportMService.updateById(sampleInspectReportM);
//			sampleInspectReportMService.updateMain(sampleInspectReportM, sampleInspectReportMPage.getSampleInspectReportDList());
//			result.success("修改成功!");
//		}
//		return result;
//	}
	
	//此段在更新时,主表【修改时间】字段不会更新，子表【创建时间】字段会修改为当天的日期，子表【修改时间】字段不会更新？
	@PutMapping(value = "/edit")
	public Result<SampleInspectReportM> edit(@RequestBody SampleInspectReportMPage sampleInspectReportMPage) {
		Result<SampleInspectReportM> result = new Result<SampleInspectReportM>();
		SampleInspectReportM sampleInspectReportM = new SampleInspectReportM();
		BeanUtils.copyProperties(sampleInspectReportMPage, sampleInspectReportM);
		SampleInspectReportM sampleInspectReportMEntity = sampleInspectReportMService.getById(sampleInspectReportM.getId());
		if(sampleInspectReportMEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = sampleInspectReportMService.updateById(sampleInspectReportM);
			sampleInspectReportMService.updateMain(sampleInspectReportM, sampleInspectReportMPage.getSampleInspectReportDList());
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
			sampleInspectReportMService.delMain(id);
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
	public Result<SampleInspectReportM> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<SampleInspectReportM> result = new Result<SampleInspectReportM>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.sampleInspectReportMService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<SampleInspectReportM> queryById(@RequestParam(name="id",required=true) String id) {
		Result<SampleInspectReportM> result = new Result<SampleInspectReportM>();
		SampleInspectReportM sampleInspectReportM = sampleInspectReportMService.getById(id);
		if(sampleInspectReportM==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(sampleInspectReportM);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/querySampleInspectReportDByMainId")
	public Result<List<SampleInspectReportD>> querySampleInspectReportDListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<SampleInspectReportD>> result = new Result<List<SampleInspectReportD>>();
		List<SampleInspectReportD> sampleInspectReportDList = sampleInspectReportDService.selectByMainId(id);
		result.setResult(sampleInspectReportDList);
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
      QueryWrapper<SampleInspectReportM> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              SampleInspectReportM sampleInspectReportM = JSON.parseObject(deString, SampleInspectReportM.class);
              queryWrapper = QueryGenerator.initQueryWrapper(sampleInspectReportM, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<SampleInspectReportMPage> pageList = new ArrayList<SampleInspectReportMPage>();
      List<SampleInspectReportM> sampleInspectReportMList = sampleInspectReportMService.list(queryWrapper);
      for (SampleInspectReportM sampleInspectReportM : sampleInspectReportMList) {
          SampleInspectReportMPage vo = new SampleInspectReportMPage();
          BeanUtils.copyProperties(sampleInspectReportM, vo);
          List<SampleInspectReportD> sampleInspectReportDList = sampleInspectReportDService.selectByMainId(sampleInspectReportM.getId());
          vo.setSampleInspectReportDList(sampleInspectReportDList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "验货报告主表列表");
      mv.addObject(NormalExcelConstants.CLASS, SampleInspectReportMPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("验货报告主表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<SampleInspectReportMPage> list = ExcelImportUtil.importExcel(file.getInputStream(), SampleInspectReportMPage.class, params);
              for (SampleInspectReportMPage page : list) {
                  SampleInspectReportM po = new SampleInspectReportM();
                  BeanUtils.copyProperties(page, po);
                  sampleInspectReportMService.saveMain(po, page.getSampleInspectReportDList());
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
