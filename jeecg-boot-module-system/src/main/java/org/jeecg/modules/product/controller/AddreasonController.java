package org.jeecg.modules.product.controller;

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
import org.jeecg.modules.product.entity.Addreason;
import org.jeecg.modules.product.service.IAddreasonService;
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
 * @Description: 补料原因表
 * @Author: jeecg-boot
 * @Date:   2019-12-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="补料原因表")
@RestController
@RequestMapping("/product/addreason")
public class AddreasonController {
	@Autowired
	private IAddreasonService addreasonService;
	
	/**
	  * 分页列表查询
	 * @param addreason
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "补料原因表-分页列表查询")
	@ApiOperation(value="补料原因表-分页列表查询", notes="补料原因表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Addreason>> queryPageList(Addreason addreason,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Addreason>> result = new Result<IPage<Addreason>>();
		QueryWrapper<Addreason> queryWrapper = QueryGenerator.initQueryWrapper(addreason, req.getParameterMap());
		Page<Addreason> page = new Page<Addreason>(pageNo, pageSize);
		IPage<Addreason> pageList = addreasonService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param addreason
	 * @return
	 */
	@AutoLog(value = "补料原因表-添加")
	@ApiOperation(value="补料原因表-添加", notes="补料原因表-添加")
	@PostMapping(value = "/add")
	public Result<Addreason> add(@RequestBody Addreason addreason) {
		Result<Addreason> result = new Result<Addreason>();
		try {
			addreasonService.save(addreason);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param addreason
	 * @return
	 */
	@AutoLog(value = "补料原因表-编辑")
	@ApiOperation(value="补料原因表-编辑", notes="补料原因表-编辑")
	@PutMapping(value = "/edit")
	public Result<Addreason> edit(@RequestBody Addreason addreason) {
		Result<Addreason> result = new Result<Addreason>();
		Addreason addreasonEntity = addreasonService.getById(addreason.getId());
		if(addreasonEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = addreasonService.updateById(addreason);
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
	@AutoLog(value = "补料原因表-通过id删除")
	@ApiOperation(value="补料原因表-通过id删除", notes="补料原因表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			addreasonService.removeById(id);
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
	@AutoLog(value = "补料原因表-批量删除")
	@ApiOperation(value="补料原因表-批量删除", notes="补料原因表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Addreason> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Addreason> result = new Result<Addreason>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.addreasonService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "补料原因表-通过id查询")
	@ApiOperation(value="补料原因表-通过id查询", notes="补料原因表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Addreason> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Addreason> result = new Result<Addreason>();
		Addreason addreason = addreasonService.getById(id);
		if(addreason==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(addreason);
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
      QueryWrapper<Addreason> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Addreason addreason = JSON.parseObject(deString, Addreason.class);
              queryWrapper = QueryGenerator.initQueryWrapper(addreason, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Addreason> pageList = addreasonService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "补料原因表列表");
      mv.addObject(NormalExcelConstants.CLASS, Addreason.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("补料原因表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Addreason> listAddreasons = ExcelImportUtil.importExcel(file.getInputStream(), Addreason.class, params);
              addreasonService.saveBatch(listAddreasons);
              return Result.ok("文件导入成功！数据行数:" + listAddreasons.size());
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
