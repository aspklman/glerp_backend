package org.jeecg.modules.product.controller;

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
import org.jeecg.modules.product.entity.Sec;
import org.jeecg.modules.product.service.ISecService;
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
 * @Description: 生产组别表
 * @Author: jeecg-boot
 * @Date:   2019-12-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="生产组别表")
@RestController
@RequestMapping("/product/sec")
public class SecController {
	@Autowired
	private ISecService secService;
	
	/**
	  * 分页列表查询
	 * @param sec
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "生产组别表-分页列表查询")
	@ApiOperation(value="生产组别表-分页列表查询", notes="生产组别表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Sec>> queryPageList(Sec sec,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Sec>> result = new Result<IPage<Sec>>();
		QueryWrapper<Sec> queryWrapper = QueryGenerator.initQueryWrapper(sec, req.getParameterMap());
		Page<Sec> page = new Page<Sec>(pageNo, pageSize);
		IPage<Sec> pageList = secService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param sec
	 * @return
	 */
	@AutoLog(value = "生产组别表-添加")
	@ApiOperation(value="生产组别表-添加", notes="生产组别表-添加")
	@PostMapping(value = "/add")
	@RequiresPermissions("sec:add")
	public Result<Sec> add(@RequestBody Sec sec) {
		Result<Sec> result = new Result<Sec>();
		try {
			secService.save(sec);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param sec
	 * @return
	 */
	@AutoLog(value = "生产组别表-编辑")
	@ApiOperation(value="生产组别表-编辑", notes="生产组别表-编辑")
	@PutMapping(value = "/edit")
	@RequiresPermissions("sec:edit")
	public Result<Sec> edit(@RequestBody Sec sec) {
		Result<Sec> result = new Result<Sec>();
		Sec secEntity = secService.getById(sec.getId());
		if(secEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = secService.updateById(sec);
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
	@AutoLog(value = "生产组别表-通过id删除")
	@ApiOperation(value="生产组别表-通过id删除", notes="生产组别表-通过id删除")
	@DeleteMapping(value = "/delete")
	@RequiresPermissions("sec:delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			secService.removeById(id);
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
	@AutoLog(value = "生产组别表-批量删除")
	@ApiOperation(value="生产组别表-批量删除", notes="生产组别表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	@RequiresPermissions("sec:deleteBatch")
	public Result<Sec> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Sec> result = new Result<Sec>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.secService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "生产组别表-通过id查询")
	@ApiOperation(value="生产组别表-通过id查询", notes="生产组别表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Sec> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Sec> result = new Result<Sec>();
		Sec sec = secService.getById(id);
		if(sec==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(sec);
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
  @RequiresPermissions("sec:exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Sec> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Sec sec = JSON.parseObject(deString, Sec.class);
              queryWrapper = QueryGenerator.initQueryWrapper(sec, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Sec> pageList = secService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "生产组别表列表");
      mv.addObject(NormalExcelConstants.CLASS, Sec.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("生产组别表列表数据", "导出人:Jeecg", "导出信息"));
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
  @RequiresPermissions("sec:importExcel")
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
              List<Sec> listSecs = ExcelImportUtil.importExcel(file.getInputStream(), Sec.class, params);
              secService.saveBatch(listSecs);
              return Result.ok("文件导入成功！数据行数:" + listSecs.size());
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
