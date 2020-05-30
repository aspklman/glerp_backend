package org.jeecg.modules.order.controller;

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
import org.jeecg.modules.order.entity.Custom;
import org.jeecg.modules.order.service.ICustomService;
import org.jeecg.modules.system.service.ISysDataLogService;

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
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户表
 * @Author: jeecg-boot
 * @Date:   2019-07-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户表")
@RestController
@RequestMapping("/order/custom")
public class CustomController {
	@Autowired
	private ICustomService customService;
	
	@Autowired
	private ISysDataLogService sysDataLogService;
	
	/**
	  * 分页列表查询
	 * @param custom
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户表-分页列表查询")
	@ApiOperation(value="客户表-分页列表查询", notes="客户表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Custom>> queryPageList(Custom custom,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Custom>> result = new Result<IPage<Custom>>();
		QueryWrapper<Custom> queryWrapper = QueryGenerator.initQueryWrapper(custom, req.getParameterMap());
		Page<Custom> page = new Page<Custom>(pageNo, pageSize);
		IPage<Custom> pageList = customService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param custom
	 * @return
	 */
	@AutoLog(value = "客户表-添加")
	@ApiOperation(value="客户表-添加", notes="客户表-添加")
	@PostMapping(value = "/add")
	@RequiresPermissions("custom:add")
	public Result<Custom> add(@RequestBody Custom custom) {
		Result<Custom> result = new Result<Custom>();
		try {
			custom.setCustomNo(custom.getCustomNo().toUpperCase());			//编号转换成大写后保存
			custom.setCustomFnm(custom.getCustomFnm().toUpperCase());		//简称转换成大写后保存
			custom.setCustomGnm(custom.getCustomGnm().toUpperCase());		//全称转换成大写后保存
			custom.setCustomEnm(custom.getCustomEnm().toUpperCase());		//英文名称转换成大写后保存
			customService.save(custom);
			sysDataLogService.addDataLog("custom", custom.getId(), JSONObject.toJSONString(custom));		//记录数据快照
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param custom
	 * @return
	 */
	@AutoLog(value = "客户表-编辑")
	@ApiOperation(value="客户表-编辑", notes="客户表-编辑")
	@PutMapping(value = "/edit")
	@RequiresPermissions("custom:edit")
	public Result<Custom> edit(@RequestBody Custom custom) {
		Result<Custom> result = new Result<Custom>();
		Custom customEntity = customService.getById(custom.getId());
		if(customEntity==null) {
			result.error500("未找到对应实体");
		}else {
			custom.setCustomNo(custom.getCustomNo().toUpperCase());			//编号转换成大写后保存
			custom.setCustomFnm(custom.getCustomFnm().toUpperCase());		//简称转换成大写后保存
			custom.setCustomGnm(custom.getCustomGnm().toUpperCase());		//全称转换成大写后保存
			custom.setCustomEnm(custom.getCustomEnm().toUpperCase());		//英文名称转换成大写后保存
			sysDataLogService.addDataLog("custom", custom.getId(), JSONObject.toJSONString(custom));		//记录数据快照
			boolean ok = customService.updateById(custom);
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
	@AutoLog(value = "客户表-通过id删除")
	@ApiOperation(value="客户表-通过id删除", notes="客户表-通过id删除")
	@DeleteMapping(value = "/delete")
	@RequiresPermissions("custom:delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			customService.removeById(id);
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
	@AutoLog(value = "客户表-批量删除")
	@ApiOperation(value="客户表-批量删除", notes="客户表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	@RequiresPermissions("custom:deleteBatch")
	public Result<Custom> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Custom> result = new Result<Custom>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.customService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户表-通过id查询")
	@ApiOperation(value="客户表-通过id查询", notes="客户表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Custom> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Custom> result = new Result<Custom>();
		Custom custom = customService.getById(id);
		if(custom==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(custom);
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
  @RequiresPermissions("custom:exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Custom> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Custom custom = JSON.parseObject(deString, Custom.class);
              queryWrapper = QueryGenerator.initQueryWrapper(custom, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Custom> pageList = customService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户表列表");
      mv.addObject(NormalExcelConstants.CLASS, Custom.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户表列表数据", "导出人:Jeecg", "导出信息"));
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
  @RequiresPermissions("custom:importExcel")
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
              List<Custom> listCustoms = ExcelImportUtil.importExcel(file.getInputStream(), Custom.class, params);
              customService.saveBatch(listCustoms);
              return Result.ok("文件导入成功！数据行数:" + listCustoms.size());
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
