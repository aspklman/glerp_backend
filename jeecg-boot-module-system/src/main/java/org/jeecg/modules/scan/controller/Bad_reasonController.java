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
import org.jeecg.modules.scan.entity.Bad_reason;
import org.jeecg.modules.scan.service.IBad_reasonService;
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
 * @Description: 不良原因(新材)表
 * @Author: jeecg-boot
 * @Date:   2019-12-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="不良原因(新材)表")
@RestController
@RequestMapping("/scan/bad_reason")
public class Bad_reasonController {
	@Autowired
	private IBad_reasonService bad_reasonService;
	
	/**
	  * 分页列表查询
	 * @param bad_reason
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "不良原因(新材)表-分页列表查询")
	@ApiOperation(value="不良原因(新材)表-分页列表查询", notes="不良原因(新材)表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Bad_reason>> queryPageList(Bad_reason bad_reason,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Bad_reason>> result = new Result<IPage<Bad_reason>>();
		QueryWrapper<Bad_reason> queryWrapper = QueryGenerator.initQueryWrapper(bad_reason, req.getParameterMap());
		Page<Bad_reason> page = new Page<Bad_reason>(pageNo, pageSize);
		IPage<Bad_reason> pageList = bad_reasonService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param bad_reason
	 * @return
	 */
	@AutoLog(value = "不良原因(新材)表-添加")
	@ApiOperation(value="不良原因(新材)表-添加", notes="不良原因(新材)表-添加")
	@PostMapping(value = "/add")
    @RequiresPermissions("bad_reason:add")
	public Result<Bad_reason> add(@RequestBody Bad_reason bad_reason) {
		Result<Bad_reason> result = new Result<Bad_reason>();
		try {
			bad_reasonService.save(bad_reason);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param bad_reason
	 * @return
	 */
	@AutoLog(value = "不良原因(新材)表-编辑")
	@ApiOperation(value="不良原因(新材)表-编辑", notes="不良原因(新材)表-编辑")
	@PutMapping(value = "/edit")
    @RequiresPermissions("bad_reason:edit")
	public Result<Bad_reason> edit(@RequestBody Bad_reason bad_reason) {
		Result<Bad_reason> result = new Result<Bad_reason>();
		Bad_reason bad_reasonEntity = bad_reasonService.getById(bad_reason.getId());
		if(bad_reasonEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = bad_reasonService.updateById(bad_reason);
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
	@AutoLog(value = "不良原因(新材)表-通过id删除")
	@ApiOperation(value="不良原因(新材)表-通过id删除", notes="不良原因(新材)表-通过id删除")
	@DeleteMapping(value = "/delete")
    @RequiresPermissions("bad_reason:delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			bad_reasonService.removeById(id);
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
	@AutoLog(value = "不良原因(新材)表-批量删除")
	@ApiOperation(value="不良原因(新材)表-批量删除", notes="不良原因(新材)表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
    @RequiresPermissions("bad_reason:deleteBatch")
	public Result<Bad_reason> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Bad_reason> result = new Result<Bad_reason>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.bad_reasonService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "不良原因(新材)表-通过id查询")
	@ApiOperation(value="不良原因(新材)表-通过id查询", notes="不良原因(新材)表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Bad_reason> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Bad_reason> result = new Result<Bad_reason>();
		Bad_reason bad_reason = bad_reasonService.getById(id);
		if(bad_reason==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(bad_reason);
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
  @RequiresPermissions("bad_reason:exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Bad_reason> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Bad_reason bad_reason = JSON.parseObject(deString, Bad_reason.class);
              queryWrapper = QueryGenerator.initQueryWrapper(bad_reason, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Bad_reason> pageList = bad_reasonService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "不良原因(新材)表列表");
      mv.addObject(NormalExcelConstants.CLASS, Bad_reason.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("不良原因(新材)表列表数据", "导出人:Jeecg", "导出信息"));
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
  @RequiresPermissions("bad_reason:importExcel")
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
              List<Bad_reason> listBad_reasons = ExcelImportUtil.importExcel(file.getInputStream(), Bad_reason.class, params);
              bad_reasonService.saveBatch(listBad_reasons);
              return Result.ok("文件导入成功！数据行数:" + listBad_reasons.size());
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
