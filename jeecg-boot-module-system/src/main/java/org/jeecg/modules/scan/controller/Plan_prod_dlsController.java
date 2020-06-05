package org.jeecg.modules.scan.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.scan.entity.Plan_prod_dls;
import org.jeecg.modules.scan.service.IPlan_prod_dlsService;
import java.util.Date;
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
 * @Description: 成型目标产量(大罗沙)表
 * @Author: jeecg-boot
 * @Date:   2019-12-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="成型目标产量(大罗沙)表")
@RestController
@RequestMapping("/scan/plan_prod_dls")
public class Plan_prod_dlsController {
	@Autowired
	private IPlan_prod_dlsService plan_prod_dlsService;
	
	/**
	  * 分页列表查询
	 * @param plan_prod_dls
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "成型目标产量(大罗沙)表-分页列表查询")
	@ApiOperation(value="成型目标产量(大罗沙)表-分页列表查询", notes="成型目标产量(大罗沙)表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Plan_prod_dls>> queryPageList(Plan_prod_dls plan_prod_dls,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Plan_prod_dls>> result = new Result<IPage<Plan_prod_dls>>();
		QueryWrapper<Plan_prod_dls> queryWrapper = QueryGenerator.initQueryWrapper(plan_prod_dls, req.getParameterMap());
		Page<Plan_prod_dls> page = new Page<Plan_prod_dls>(pageNo, pageSize);
		IPage<Plan_prod_dls> pageList = plan_prod_dlsService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param plan_prod_dls
	 * @return
	 */
	@AutoLog(value = "成型目标产量(大罗沙)表-添加")
	@ApiOperation(value="成型目标产量(大罗沙)表-添加", notes="成型目标产量(大罗沙)表-添加")
	@PostMapping(value = "/add")
	@RequiresPermissions("plan_prod_dls:add")
	public Result<Plan_prod_dls> add(@RequestBody Plan_prod_dls plan_prod_dls) {
		Result<Plan_prod_dls> result = new Result<Plan_prod_dls>();
		try {
			plan_prod_dlsService.save(plan_prod_dls);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param plan_prod_dls
	 * @return
	 */
	@AutoLog(value = "成型目标产量(大罗沙)表-编辑")
	@ApiOperation(value="成型目标产量(大罗沙)表-编辑", notes="成型目标产量(大罗沙)表-编辑")
	@PutMapping(value = "/edit")
	@RequiresPermissions("plan_prod_dls:edit")
	public Result<Plan_prod_dls> edit(@RequestBody Plan_prod_dls plan_prod_dls) {
		Result<Plan_prod_dls> result = new Result<Plan_prod_dls>();
		Plan_prod_dls plan_prod_dlsEntity = plan_prod_dlsService.getById(plan_prod_dls.getId());
		if(plan_prod_dlsEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = plan_prod_dlsService.updateById(plan_prod_dls);
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
	@AutoLog(value = "成型目标产量(大罗沙)表-通过id删除")
	@ApiOperation(value="成型目标产量(大罗沙)表-通过id删除", notes="成型目标产量(大罗沙)表-通过id删除")
	@DeleteMapping(value = "/delete")
	@RequiresPermissions("plan_prod_dls:delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			plan_prod_dlsService.removeById(id);
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
	@AutoLog(value = "成型目标产量(大罗沙)表-批量删除")
	@ApiOperation(value="成型目标产量(大罗沙)表-批量删除", notes="成型目标产量(大罗沙)表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	@RequiresPermissions("plan_prod_dls:deleteBatch")
	public Result<Plan_prod_dls> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Plan_prod_dls> result = new Result<Plan_prod_dls>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.plan_prod_dlsService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "成型目标产量(大罗沙)表-通过id查询")
	@ApiOperation(value="成型目标产量(大罗沙)表-通过id查询", notes="成型目标产量(大罗沙)表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Plan_prod_dls> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Plan_prod_dls> result = new Result<Plan_prod_dls>();
		Plan_prod_dls plan_prod_dls = plan_prod_dlsService.getById(id);
		if(plan_prod_dls==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(plan_prod_dls);
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
  @RequiresPermissions("plan_prod_dls:exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Plan_prod_dls> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Plan_prod_dls plan_prod_dls = JSON.parseObject(deString, Plan_prod_dls.class);
              queryWrapper = QueryGenerator.initQueryWrapper(plan_prod_dls, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Plan_prod_dls> pageList = plan_prod_dlsService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "成型目标产量(大罗沙)表列表");
      mv.addObject(NormalExcelConstants.CLASS, Plan_prod_dls.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("成型目标产量(大罗沙)表列表数据", "导出人:Jeecg", "导出信息"));
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
  @RequiresPermissions("plan_prod_dls:importExcel")
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
              List<Plan_prod_dls> listPlan_prod_dlss = ExcelImportUtil.importExcel(file.getInputStream(), Plan_prod_dls.class, params);
              plan_prod_dlsService.saveBatch(listPlan_prod_dlss);
              return Result.ok("文件导入成功！数据行数:" + listPlan_prod_dlss.size());
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
