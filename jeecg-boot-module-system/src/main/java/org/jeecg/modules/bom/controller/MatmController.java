package org.jeecg.modules.bom.controller;

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
import org.jeecg.modules.bom.entity.Matm;
import org.jeecg.modules.bom.service.IMatmService;
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
 * @Description: 材料表
 * @Author: jeecg-boot
 * @Date:   2019-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="材料表")
@RestController
@RequestMapping("/bom/matm")
public class MatmController {
	@Autowired
	private IMatmService matmService;
	@Autowired
	private ISysDataLogService sysDataLogService;
	
	/**
	  * 分页列表查询
	 * @param matm
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "材料表-分页列表查询")
	@ApiOperation(value="材料表-分页列表查询", notes="材料表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Matm>> queryPageList(Matm matm,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Matm>> result = new Result<IPage<Matm>>();
		QueryWrapper<Matm> queryWrapper = QueryGenerator.initQueryWrapper(matm, req.getParameterMap());
		Page<Matm> page = new Page<Matm>(pageNo, pageSize);
		IPage<Matm> pageList = matmService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param matm
	 * @return
	 */
	@AutoLog(value = "材料表-添加")
	@ApiOperation(value="材料表-添加", notes="材料表-添加")
	@PostMapping(value = "/add")
	public Result<Matm> add(@RequestBody Matm matm) {
		Result<Matm> result = new Result<Matm>();
		try {
			matm.setMatNo(matm.getMatNo().toUpperCase());		//编号转换成大写后保存
			matmService.save(matm);
			sysDataLogService.addDataLog("matm", matm.getId(), JSONObject.toJSONString(matm));		//记录数据快照
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param matm
	 * @return
	 */
	@AutoLog(value = "材料表-编辑")
	@ApiOperation(value="材料表-编辑", notes="材料表-编辑")
	@PutMapping(value = "/edit")
	public Result<Matm> edit(@RequestBody Matm matm) {
		Result<Matm> result = new Result<Matm>();
		Matm matmEntity = matmService.getById(matm.getId());
		if(matmEntity==null) {
			result.error500("未找到对应实体");
		}else {
			matm.setMatNo(matm.getMatNo().toUpperCase());		//编号转换成大写后保存
			sysDataLogService.addDataLog("matm", matm.getId(), JSONObject.toJSONString(matm));		//记录数据快照
			boolean ok = matmService.updateById(matm);
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
	@AutoLog(value = "材料表-通过id删除")
	@ApiOperation(value="材料表-通过id删除", notes="材料表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			matmService.removeById(id);
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
	@AutoLog(value = "材料表-批量删除")
	@ApiOperation(value="材料表-批量删除", notes="材料表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Matm> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Matm> result = new Result<Matm>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.matmService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "材料表-通过id查询")
	@ApiOperation(value="材料表-通过id查询", notes="材料表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Matm> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Matm> result = new Result<Matm>();
		Matm matm = matmService.getById(id);
		if(matm==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(matm);
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
      QueryWrapper<Matm> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Matm matm = JSON.parseObject(deString, Matm.class);
              queryWrapper = QueryGenerator.initQueryWrapper(matm, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Matm> pageList = matmService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "材料表列表");
      mv.addObject(NormalExcelConstants.CLASS, Matm.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("材料表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Matm> listMatms = ExcelImportUtil.importExcel(file.getInputStream(), Matm.class, params);
              matmService.saveBatch(listMatms);
              return Result.ok("文件导入成功！数据行数:" + listMatms.size());
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
