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
import org.jeecg.modules.order.entity.Inboxspec;
import org.jeecg.modules.order.service.IInboxspecService;
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
 * @Description: 内盒规格表
 * @Author: jeecg-boot
 * @Date:   2019-11-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="内盒规格表")
@RestController
@RequestMapping("/order/inboxspec")
public class InboxspecController {
	@Autowired
	private IInboxspecService inboxspecService;
	@Autowired
	private ISysDataLogService sysDataLogService;
	
	/**
	  * 分页列表查询
	 * @param inboxspec
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "内盒规格表-分页列表查询")
	@ApiOperation(value="内盒规格表-分页列表查询", notes="内盒规格表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Inboxspec>> queryPageList(Inboxspec inboxspec,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Inboxspec>> result = new Result<IPage<Inboxspec>>();
		QueryWrapper<Inboxspec> queryWrapper = QueryGenerator.initQueryWrapper(inboxspec, req.getParameterMap());
		Page<Inboxspec> page = new Page<Inboxspec>(pageNo, pageSize);
		IPage<Inboxspec> pageList = inboxspecService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param inboxspec
	 * @return
	 */
	@AutoLog(value = "内盒规格表-添加")
	@ApiOperation(value="内盒规格表-添加", notes="内盒规格表-添加")
	@PostMapping(value = "/add")
	@RequiresPermissions("inboxspec:add")
	public Result<Inboxspec> add(@RequestBody Inboxspec inboxspec) {
		Result<Inboxspec> result = new Result<Inboxspec>();
		try {
			inboxspec.setInboxNo(inboxspec.getInboxNo().toUpperCase());			//编号转换成大写后保存
			inboxspecService.save(inboxspec);
			sysDataLogService.addDataLog("inboxspec", inboxspec.getId(), JSONObject.toJSONString(inboxspec));		//记录数据快照
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param inboxspec
	 * @return
	 */
	@AutoLog(value = "内盒规格表-编辑")
	@ApiOperation(value="内盒规格表-编辑", notes="内盒规格表-编辑")
	@PutMapping(value = "/edit")
	@RequiresPermissions("inboxspec:edit")
	public Result<Inboxspec> edit(@RequestBody Inboxspec inboxspec) {
		Result<Inboxspec> result = new Result<Inboxspec>();
		Inboxspec inboxspecEntity = inboxspecService.getById(inboxspec.getId());
		if(inboxspecEntity==null) {
			result.error500("未找到对应实体");
		}else {
			inboxspec.setInboxNo(inboxspec.getInboxNo().toUpperCase());			//编号转换成大写后保存
			sysDataLogService.addDataLog("inboxspec", inboxspec.getId(), JSONObject.toJSONString(inboxspec));		//记录数据快照
			boolean ok = inboxspecService.updateById(inboxspec);
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
	@AutoLog(value = "内盒规格表-通过id删除")
	@ApiOperation(value="内盒规格表-通过id删除", notes="内盒规格表-通过id删除")
	@DeleteMapping(value = "/delete")
	@RequiresPermissions("inboxspec:delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			inboxspecService.removeById(id);
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
	@AutoLog(value = "内盒规格表-批量删除")
	@ApiOperation(value="内盒规格表-批量删除", notes="内盒规格表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	@RequiresPermissions("inboxspec:deleteBatch")
	public Result<Inboxspec> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Inboxspec> result = new Result<Inboxspec>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.inboxspecService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "内盒规格表-通过id查询")
	@ApiOperation(value="内盒规格表-通过id查询", notes="内盒规格表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Inboxspec> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Inboxspec> result = new Result<Inboxspec>();
		Inboxspec inboxspec = inboxspecService.getById(id);
		if(inboxspec==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(inboxspec);
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
  @RequiresPermissions("inboxspec:exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Inboxspec> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Inboxspec inboxspec = JSON.parseObject(deString, Inboxspec.class);
              queryWrapper = QueryGenerator.initQueryWrapper(inboxspec, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Inboxspec> pageList = inboxspecService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "内盒规格表列表");
      mv.addObject(NormalExcelConstants.CLASS, Inboxspec.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("内盒规格表列表数据", "导出人:Jeecg", "导出信息"));
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
  @RequiresPermissions("inboxspec:importExcel")
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
              List<Inboxspec> listInboxspecs = ExcelImportUtil.importExcel(file.getInputStream(), Inboxspec.class, params);
              inboxspecService.saveBatch(listInboxspecs);
              return Result.ok("文件导入成功！数据行数:" + listInboxspecs.size());
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
