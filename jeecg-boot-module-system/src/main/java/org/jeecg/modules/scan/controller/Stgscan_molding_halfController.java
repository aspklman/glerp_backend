package org.jeecg.modules.scan.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.scan.entity.Stgscan_molding_half;
import org.jeecg.modules.scan.service.IStgscan_molding_halfService;
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
 * @Description: 半成品产量表
 * @Author: jeecg-boot
 * @Date:   2019-12-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="半成品产量表")
@RestController
@RequestMapping("/scan/stgscan_molding_half")
public class Stgscan_molding_halfController {
	@Autowired
	private IStgscan_molding_halfService stgscan_molding_halfService;
	
	/**
	  * 分页列表查询
	 * @param stgscan_molding_half
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "半成品产量表-分页列表查询")
	@ApiOperation(value="半成品产量表-分页列表查询", notes="半成品产量表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Stgscan_molding_half>> queryPageList(Stgscan_molding_half stgscan_molding_half,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Stgscan_molding_half>> result = new Result<IPage<Stgscan_molding_half>>();
		QueryWrapper<Stgscan_molding_half> queryWrapper = QueryGenerator.initQueryWrapper(stgscan_molding_half, req.getParameterMap());
		Page<Stgscan_molding_half> page = new Page<Stgscan_molding_half>(pageNo, pageSize);
		IPage<Stgscan_molding_half> pageList = stgscan_molding_halfService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param stgscan_molding_half
	 * @return
	 */
	@AutoLog(value = "半成品产量表-添加")
	@ApiOperation(value="半成品产量表-添加", notes="半成品产量表-添加")
	@PostMapping(value = "/add")
	public Result<Stgscan_molding_half> add(@RequestBody Stgscan_molding_half stgscan_molding_half) {
		Result<Stgscan_molding_half> result = new Result<Stgscan_molding_half>();
		try {
			stgscan_molding_halfService.save(stgscan_molding_half);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param stgscan_molding_half
	 * @return
	 */
	@AutoLog(value = "半成品产量表-编辑")
	@ApiOperation(value="半成品产量表-编辑", notes="半成品产量表-编辑")
	@PutMapping(value = "/edit")
	public Result<Stgscan_molding_half> edit(@RequestBody Stgscan_molding_half stgscan_molding_half) {
		Result<Stgscan_molding_half> result = new Result<Stgscan_molding_half>();
		Stgscan_molding_half stgscan_molding_halfEntity = stgscan_molding_halfService.getById(stgscan_molding_half.getId());
		if(stgscan_molding_halfEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = stgscan_molding_halfService.updateById(stgscan_molding_half);
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
	@AutoLog(value = "半成品产量表-通过id删除")
	@ApiOperation(value="半成品产量表-通过id删除", notes="半成品产量表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			stgscan_molding_halfService.removeById(id);
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
	@AutoLog(value = "半成品产量表-批量删除")
	@ApiOperation(value="半成品产量表-批量删除", notes="半成品产量表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Stgscan_molding_half> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Stgscan_molding_half> result = new Result<Stgscan_molding_half>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.stgscan_molding_halfService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "半成品产量表-通过id查询")
	@ApiOperation(value="半成品产量表-通过id查询", notes="半成品产量表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Stgscan_molding_half> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Stgscan_molding_half> result = new Result<Stgscan_molding_half>();
		Stgscan_molding_half stgscan_molding_half = stgscan_molding_halfService.getById(id);
		if(stgscan_molding_half==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(stgscan_molding_half);
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
      QueryWrapper<Stgscan_molding_half> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Stgscan_molding_half stgscan_molding_half = JSON.parseObject(deString, Stgscan_molding_half.class);
              queryWrapper = QueryGenerator.initQueryWrapper(stgscan_molding_half, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Stgscan_molding_half> pageList = stgscan_molding_halfService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "半成品产量表列表");
      mv.addObject(NormalExcelConstants.CLASS, Stgscan_molding_half.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("半成品产量表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Stgscan_molding_half> listStgscan_molding_halfs = ExcelImportUtil.importExcel(file.getInputStream(), Stgscan_molding_half.class, params);
              stgscan_molding_halfService.saveBatch(listStgscan_molding_halfs);
              return Result.ok("文件导入成功！数据行数:" + listStgscan_molding_halfs.size());
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
