package org.jeecg.modules.stock.controller;

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
import org.jeecg.modules.stock.entity.Stkidm;
import org.jeecg.modules.stock.service.IStkidmService;
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
 * @Description: 仓库表
 * @Author: jeecg-boot
 * @Date:   2019-12-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="仓库表")
@RestController
@RequestMapping("/stock/stkidm")
public class StkidmController {
	@Autowired
	private IStkidmService stkidmService;
	
	/**
	  * 分页列表查询
	 * @param stkidm
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "仓库表-分页列表查询")
	@ApiOperation(value="仓库表-分页列表查询", notes="仓库表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Stkidm>> queryPageList(Stkidm stkidm,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Stkidm>> result = new Result<IPage<Stkidm>>();
		QueryWrapper<Stkidm> queryWrapper = QueryGenerator.initQueryWrapper(stkidm, req.getParameterMap());
		Page<Stkidm> page = new Page<Stkidm>(pageNo, pageSize);
		IPage<Stkidm> pageList = stkidmService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param stkidm
	 * @return
	 */
	@AutoLog(value = "仓库表-添加")
	@ApiOperation(value="仓库表-添加", notes="仓库表-添加")
	@PostMapping(value = "/add")
	@RequiresPermissions("stkidm:add")
	public Result<Stkidm> add(@RequestBody Stkidm stkidm) {
		Result<Stkidm> result = new Result<Stkidm>();
		try {
			stkidmService.save(stkidm);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param stkidm
	 * @return
	 */
	@AutoLog(value = "仓库表-编辑")
	@ApiOperation(value="仓库表-编辑", notes="仓库表-编辑")
	@PutMapping(value = "/edit")
	@RequiresPermissions("stkidm:edit")
	public Result<Stkidm> edit(@RequestBody Stkidm stkidm) {
		Result<Stkidm> result = new Result<Stkidm>();
		Stkidm stkidmEntity = stkidmService.getById(stkidm.getId());
		if(stkidmEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = stkidmService.updateById(stkidm);
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
	@AutoLog(value = "仓库表-通过id删除")
	@ApiOperation(value="仓库表-通过id删除", notes="仓库表-通过id删除")
	@DeleteMapping(value = "/delete")
	@RequiresPermissions("stkidm:delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			stkidmService.removeById(id);
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
	@AutoLog(value = "仓库表-批量删除")
	@ApiOperation(value="仓库表-批量删除", notes="仓库表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	@RequiresPermissions("stkidm:deleteBatch")
	public Result<Stkidm> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Stkidm> result = new Result<Stkidm>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.stkidmService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "仓库表-通过id查询")
	@ApiOperation(value="仓库表-通过id查询", notes="仓库表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Stkidm> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Stkidm> result = new Result<Stkidm>();
		Stkidm stkidm = stkidmService.getById(id);
		if(stkidm==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(stkidm);
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
  @RequiresPermissions("stkidm:exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Stkidm> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Stkidm stkidm = JSON.parseObject(deString, Stkidm.class);
              queryWrapper = QueryGenerator.initQueryWrapper(stkidm, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Stkidm> pageList = stkidmService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "仓库表列表");
      mv.addObject(NormalExcelConstants.CLASS, Stkidm.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("仓库表列表数据", "导出人:Jeecg", "导出信息"));
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
  @RequiresPermissions("stkidm:importExcel")
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
              List<Stkidm> listStkidms = ExcelImportUtil.importExcel(file.getInputStream(), Stkidm.class, params);
              stkidmService.saveBatch(listStkidms);
              return Result.ok("文件导入成功！数据行数:" + listStkidms.size());
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
