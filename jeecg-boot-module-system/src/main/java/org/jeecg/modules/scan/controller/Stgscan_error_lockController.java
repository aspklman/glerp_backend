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
import org.jeecg.modules.scan.entity.Stgscan_error_lock;
import org.jeecg.modules.scan.service.IStgscan_error_lockService;
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
 * @Description: 扫描错误锁定表
 * @Author: jeecg-boot
 * @Date:   2019-12-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="扫描错误锁定表")
@RestController
@RequestMapping("/scan/stgscan_error_lock")
public class Stgscan_error_lockController {
	@Autowired
	private IStgscan_error_lockService stgscan_error_lockService;
	
	/**
	  * 分页列表查询
	 * @param stgscan_error_lock
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "扫描错误锁定表-分页列表查询")
	@ApiOperation(value="扫描错误锁定表-分页列表查询", notes="扫描错误锁定表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Stgscan_error_lock>> queryPageList(Stgscan_error_lock stgscan_error_lock,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Stgscan_error_lock>> result = new Result<IPage<Stgscan_error_lock>>();
		QueryWrapper<Stgscan_error_lock> queryWrapper = QueryGenerator.initQueryWrapper(stgscan_error_lock, req.getParameterMap());
		Page<Stgscan_error_lock> page = new Page<Stgscan_error_lock>(pageNo, pageSize);
		IPage<Stgscan_error_lock> pageList = stgscan_error_lockService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param stgscan_error_lock
	 * @return
	 */
	@AutoLog(value = "扫描错误锁定表-添加")
	@ApiOperation(value="扫描错误锁定表-添加", notes="扫描错误锁定表-添加")
	@PostMapping(value = "/add")
	@RequiresPermissions("stgscan_error_lock:add")
	public Result<Stgscan_error_lock> add(@RequestBody Stgscan_error_lock stgscan_error_lock) {
		Result<Stgscan_error_lock> result = new Result<Stgscan_error_lock>();
		try {
			stgscan_error_lockService.save(stgscan_error_lock);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param stgscan_error_lock
	 * @return
	 */
	@AutoLog(value = "扫描错误锁定表-编辑")
	@ApiOperation(value="扫描错误锁定表-编辑", notes="扫描错误锁定表-编辑")
	@PutMapping(value = "/edit")
	@RequiresPermissions("stgscan_error_lock:edit")
	public Result<Stgscan_error_lock> edit(@RequestBody Stgscan_error_lock stgscan_error_lock) {
		Result<Stgscan_error_lock> result = new Result<Stgscan_error_lock>();
		Stgscan_error_lock stgscan_error_lockEntity = stgscan_error_lockService.getById(stgscan_error_lock.getId());
		if(stgscan_error_lockEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = stgscan_error_lockService.updateById(stgscan_error_lock);
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
	@AutoLog(value = "扫描错误锁定表-通过id删除")
	@ApiOperation(value="扫描错误锁定表-通过id删除", notes="扫描错误锁定表-通过id删除")
	@DeleteMapping(value = "/delete")
	@RequiresPermissions("stgscan_error_lock:delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			stgscan_error_lockService.removeById(id);
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
	@AutoLog(value = "扫描错误锁定表-批量删除")
	@ApiOperation(value="扫描错误锁定表-批量删除", notes="扫描错误锁定表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	@RequiresPermissions("stgscan_error_lock:deleteBatch")
	public Result<Stgscan_error_lock> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Stgscan_error_lock> result = new Result<Stgscan_error_lock>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.stgscan_error_lockService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "扫描错误锁定表-通过id查询")
	@ApiOperation(value="扫描错误锁定表-通过id查询", notes="扫描错误锁定表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Stgscan_error_lock> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Stgscan_error_lock> result = new Result<Stgscan_error_lock>();
		Stgscan_error_lock stgscan_error_lock = stgscan_error_lockService.getById(id);
		if(stgscan_error_lock==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(stgscan_error_lock);
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
  @RequiresPermissions("stgscan_error_lock:exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Stgscan_error_lock> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Stgscan_error_lock stgscan_error_lock = JSON.parseObject(deString, Stgscan_error_lock.class);
              queryWrapper = QueryGenerator.initQueryWrapper(stgscan_error_lock, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Stgscan_error_lock> pageList = stgscan_error_lockService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "扫描错误锁定表列表");
      mv.addObject(NormalExcelConstants.CLASS, Stgscan_error_lock.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("扫描错误锁定表列表数据", "导出人:Jeecg", "导出信息"));
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
  @RequiresPermissions("stgscan_error_lock:importExcel")
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
              List<Stgscan_error_lock> listStgscan_error_locks = ExcelImportUtil.importExcel(file.getInputStream(), Stgscan_error_lock.class, params);
              stgscan_error_lockService.saveBatch(listStgscan_error_locks);
              return Result.ok("文件导入成功！数据行数:" + listStgscan_error_locks.size());
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
