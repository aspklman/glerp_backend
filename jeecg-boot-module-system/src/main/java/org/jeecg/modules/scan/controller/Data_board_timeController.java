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
import org.jeecg.modules.scan.entity.Data_board_time;
import org.jeecg.modules.scan.service.IData_board_timeService;
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
 * @Description: 数据看板时间表
 * @Author: jeecg-boot
 * @Date:   2019-12-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="数据看板时间表")
@RestController
@RequestMapping("/scan/data_board_time")
public class Data_board_timeController {
	@Autowired
	private IData_board_timeService data_board_timeService;
	
	/**
	  * 分页列表查询
	 * @param data_board_time
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "数据看板时间表-分页列表查询")
	@ApiOperation(value="数据看板时间表-分页列表查询", notes="数据看板时间表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Data_board_time>> queryPageList(Data_board_time data_board_time,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Data_board_time>> result = new Result<IPage<Data_board_time>>();
		QueryWrapper<Data_board_time> queryWrapper = QueryGenerator.initQueryWrapper(data_board_time, req.getParameterMap());
		Page<Data_board_time> page = new Page<Data_board_time>(pageNo, pageSize);
		IPage<Data_board_time> pageList = data_board_timeService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param data_board_time
	 * @return
	 */
	@AutoLog(value = "数据看板时间表-添加")
	@ApiOperation(value="数据看板时间表-添加", notes="数据看板时间表-添加")
	@PostMapping(value = "/add")
	public Result<Data_board_time> add(@RequestBody Data_board_time data_board_time) {
		Result<Data_board_time> result = new Result<Data_board_time>();
		try {
			data_board_timeService.save(data_board_time);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param data_board_time
	 * @return
	 */
	@AutoLog(value = "数据看板时间表-编辑")
	@ApiOperation(value="数据看板时间表-编辑", notes="数据看板时间表-编辑")
	@PutMapping(value = "/edit")
	public Result<Data_board_time> edit(@RequestBody Data_board_time data_board_time) {
		Result<Data_board_time> result = new Result<Data_board_time>();
		Data_board_time data_board_timeEntity = data_board_timeService.getById(data_board_time.getId());
		if(data_board_timeEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = data_board_timeService.updateById(data_board_time);
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
	@AutoLog(value = "数据看板时间表-通过id删除")
	@ApiOperation(value="数据看板时间表-通过id删除", notes="数据看板时间表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			data_board_timeService.removeById(id);
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
	@AutoLog(value = "数据看板时间表-批量删除")
	@ApiOperation(value="数据看板时间表-批量删除", notes="数据看板时间表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Data_board_time> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Data_board_time> result = new Result<Data_board_time>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.data_board_timeService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "数据看板时间表-通过id查询")
	@ApiOperation(value="数据看板时间表-通过id查询", notes="数据看板时间表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Data_board_time> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Data_board_time> result = new Result<Data_board_time>();
		Data_board_time data_board_time = data_board_timeService.getById(id);
		if(data_board_time==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(data_board_time);
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
      QueryWrapper<Data_board_time> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Data_board_time data_board_time = JSON.parseObject(deString, Data_board_time.class);
              queryWrapper = QueryGenerator.initQueryWrapper(data_board_time, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Data_board_time> pageList = data_board_timeService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "数据看板时间表列表");
      mv.addObject(NormalExcelConstants.CLASS, Data_board_time.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("数据看板时间表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Data_board_time> listData_board_times = ExcelImportUtil.importExcel(file.getInputStream(), Data_board_time.class, params);
              data_board_timeService.saveBatch(listData_board_times);
              return Result.ok("文件导入成功！数据行数:" + listData_board_times.size());
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
