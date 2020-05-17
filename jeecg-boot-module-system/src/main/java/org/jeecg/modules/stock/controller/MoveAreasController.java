package org.jeecg.modules.stock.controller;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.config.ApiContext;
import org.jeecg.modules.stock.entity.MoveAreas;
import org.jeecg.modules.stock.service.IMoveAreasService;
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
 * @Description: 成品移库
 * @Author: jeecg-boot
 * @Date:   2020-04-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="成品移库")
@RestController
@RequestMapping("/stock/moveAreas")
public class MoveAreasController {
	@Autowired
	private IMoveAreasService moveAreasService;

	@Autowired
	private ApiContext apiContext;
	
	/**
	  * 分页列表查询
	 * @param moveAreas
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "成品移库-分页列表查询")
	@ApiOperation(value="成品移库-分页列表查询", notes="成品移库-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "stock/MoveAreasList")
	public Result<IPage<MoveAreas>> queryPageList(MoveAreas moveAreas,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<MoveAreas>> result = new Result<IPage<MoveAreas>>();
		QueryWrapper<MoveAreas> queryWrapper = QueryGenerator.initQueryWrapper(moveAreas, req.getParameterMap());
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMM");
		String stockYymm = dateFormat.format(currentDate);
		queryWrapper.and(wrapper -> wrapper.eq("stock_yymm", stockYymm));		//只显示当月库存
		queryWrapper.and(wrapper -> wrapper.gt("stock_qty", 0));		//只显示库存数大于0的记录
//        apiContext.setCurrentProviderId(1L);
//        log.info("当前租户ID：" + apiContext.getCurrentProviderId());
		Page<MoveAreas> page = new Page<MoveAreas>(pageNo, pageSize);
		IPage<MoveAreas> pageList = moveAreasService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param moveAreas
	 * @return
	 */
	@AutoLog(value = "成品移库-添加")
	@ApiOperation(value="成品移库-添加", notes="成品移库-添加")
	@PostMapping(value = "/add")
	public Result<MoveAreas> add(@RequestBody MoveAreas moveAreas) {
		Result<MoveAreas> result = new Result<MoveAreas>();
		try {
			moveAreasService.save(moveAreas);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param moveAreas
	 * @return
	 */
	@AutoLog(value = "成品移库-编辑")
	@ApiOperation(value="成品移库-编辑", notes="成品移库-编辑")
	@PutMapping(value = "/edit")
	public Result<MoveAreas> edit(@RequestBody MoveAreas moveAreas) {
		Result<MoveAreas> result = new Result<MoveAreas>();
		MoveAreas moveAreasEntity = moveAreasService.getById(moveAreas.getId());
		if(moveAreasEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = moveAreasService.updateById(moveAreas);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}

     /**
	  *  移库
	  * @param params
	  * @return
	  */
	 @AutoLog(value = "成品移库-移库")
	 @ApiOperation(value="成品移库-移库", notes="成品移库-移库")
	 @GetMapping(value = "/move")
	 public Result move(@RequestParam("params") String params) {
		 Result result = new Result();
		 if (params == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 JSONObject jsonObject = JSONObject.parseObject(params);
			 String stkNo = jsonObject.getString("stkNo");
			 String stockYymm = jsonObject.getString("stockYymm");
			 String factOdrNo = jsonObject.getString("factOdrNo");
			 String locNo = jsonObject.getString("locNo");
			 String cpartNo = jsonObject.getString("cpartNo");
			 String locAreaOut = jsonObject.getString("locAreaOut");
			 String locAreaIn = jsonObject.getString("locAreaIn");
			 String createUpdateBy = loginUser.getUsername();

//			 log.info("工厂编号：" + factNo);
//             log.info("库别：" + stkNo);
//             log.info("库存年月：" + stockYymm);
//             log.info("订单编号：" + factOdrNo);
//             log.info("存放区域：" + locNo);
//             log.info("成本部位：" + cpartNo);
//			 log.info("移出地点：" + locAreaOut);
//			 log.info("移入地点：" + locAreaIn);
//			 log.info("建立修改用户：" + createUpdateBy);

			 try {
				 this.moveAreasService.moveAreas(factNo, stkNo, stockYymm, factOdrNo, locNo, cpartNo, locAreaOut, locAreaIn, createUpdateBy);
				 result.success("移库成功！");
			 } catch (Exception e) {
				 result.error500("移库失败！ " + e);
			 }
		 }
		 return result;
	 }

	 /**
	  *  查询其它存放地点
	  * @param params
	  * @return
	  */
	 @AutoLog(value = "成品移库-查询其它存放地点")
	 @ApiOperation(value="成品移库-查询其它存放地点", notes="成品移库-查询其它存放地点")
	 @GetMapping(value = "/queryOtherAreas")
	 public Result<List<MoveAreas>> queryOtherAreas(@RequestParam("params") String params) {
		 Result<List<MoveAreas>> result = new Result<List<MoveAreas>>();
		 if (params == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 JSONObject jsonObject = JSONObject.parseObject(params);
			 String stkNo = jsonObject.getString("stkNo");
			 String stockYymm = jsonObject.getString("stockYymm");
			 String factOdrNo = jsonObject.getString("factOdrNo");
			 String locNo = jsonObject.getString("locNo");
			 String cpartNo = jsonObject.getString("cpartNo");
			 String locArea = jsonObject.getString("locArea");

//			 log.info("工厂编号：" + factNo);
//			 log.info("库别：" + stkNo);
//			 log.info("库存年月：" + stockYymm);
//			 log.info("订单编号：" + factOdrNo);
//			 log.info("存放区域：" + locNo);
//			 log.info("成本部位：" + cpartNo);
//			 log.info("存放地点：" + locArea);

			 try {
				 List<MoveAreas> moveAreas = this.moveAreasService.queryOtherAreas(factNo, stkNo, stockYymm, factOdrNo, locNo, cpartNo, locArea);
				 log.info("其它存放地点:" + moveAreas);
				 result.setResult(moveAreas);
				 result.success("移库成功！");
			 } catch (Exception e) {
				 result.error500("移库失败！ " + e);
			 }
		 }
		 return result;
	 }
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "成品移库-通过id删除")
	@ApiOperation(value="成品移库-通过id删除", notes="成品移库-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			moveAreasService.removeById(id);
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
	@AutoLog(value = "成品移库-批量删除")
	@ApiOperation(value="成品移库-批量删除", notes="成品移库-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<MoveAreas> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<MoveAreas> result = new Result<MoveAreas>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.moveAreasService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "成品移库-通过id查询")
	@ApiOperation(value="成品移库-通过id查询", notes="成品移库-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<MoveAreas> queryById(@RequestParam(name="id",required=true) String id) {
		Result<MoveAreas> result = new Result<MoveAreas>();
		MoveAreas moveAreas = moveAreasService.getById(id);
		if(moveAreas==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(moveAreas);
			result.setSuccess(true);
		}
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
      QueryWrapper<MoveAreas> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              MoveAreas moveAreas = JSON.parseObject(deString, MoveAreas.class);
              queryWrapper = QueryGenerator.initQueryWrapper(moveAreas, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<MoveAreas> pageList = moveAreasService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "成品移库列表");
      mv.addObject(NormalExcelConstants.CLASS, MoveAreas.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("成品移库列表数据", "导出人:Jeecg", "导出信息"));
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
              List<MoveAreas> listMoveAreass = ExcelImportUtil.importExcel(file.getInputStream(), MoveAreas.class, params);
              moveAreasService.saveBatch(listMoveAreass);
              return Result.ok("文件导入成功！数据行数:" + listMoveAreass.size());
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
