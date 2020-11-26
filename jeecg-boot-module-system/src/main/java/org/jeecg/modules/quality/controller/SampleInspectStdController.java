package org.jeecg.modules.quality.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.order.service.ISampleInspectStdService;
import org.jeecg.modules.quality.entity.SampleInspectStd;
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
 * @Description: 验货抽检标准
 * @Author: jeecg-boot
 * @Date:   2020-08-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="验货抽检标准")
@RestController
@RequestMapping("/quality/sampleInspectStd")
public class SampleInspectStdController {
	@Autowired
	private ISampleInspectStdService sampleInspectStdService;
	
	/**
	  * 分页列表查询
	 * @param sampleInspectStd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "验货抽检标准-分页列表查询")
	@ApiOperation(value="验货抽检标准-分页列表查询", notes="验货抽检标准-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="quality/SampleInspectStdList")
	public Result<IPage<SampleInspectStd>> queryPageList(SampleInspectStd sampleInspectStd,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<SampleInspectStd>> result = new Result<IPage<SampleInspectStd>>();
		QueryWrapper<SampleInspectStd> queryWrapper = QueryGenerator.initQueryWrapper(sampleInspectStd, req.getParameterMap());
		Page<SampleInspectStd> page = new Page<SampleInspectStd>(pageNo, pageSize);
		IPage<SampleInspectStd> pageList = sampleInspectStdService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

     /**
      * 验货明细
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "验货抽检标准-验货明细")
     @ApiOperation(value = "验货抽检标准-验货明细", notes = "验货抽检标准-验货明细")
     @GetMapping(value = "/getInspectDetail")
     public Result<List<Map<String, Object>>> getInspectDetail(@RequestParam("pssr") String pssr) {
         Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
//			 log.info("厂别：" + factNo);
             String factOdrNo = pssr.trim().toString();
//			 log.info("工厂订单：" + factOdrNo + '/');
             List<Map<String, Object>> sizeQty = this.sampleInspectStdService.getInspectDetail(factNo, factOdrNo);
             result.setResult(sizeQty);
             result.success("获取成功!");
         }
         log.info("结果：" + result);
         return result;
     }
	
	/**
	  *   添加
	 * @param sampleInspectStd
	 * @return
	 */
	@AutoLog(value = "验货抽检标准-添加")
	@ApiOperation(value="验货抽检标准-添加", notes="验货抽检标准-添加")
	@PostMapping(value = "/add")
	public Result<SampleInspectStd> add(@RequestBody SampleInspectStd sampleInspectStd) {
		Result<SampleInspectStd> result = new Result<SampleInspectStd>();
		try {
			sampleInspectStdService.save(sampleInspectStd);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
//	/**
//	  *  编辑
//	 * @param sampleInspectStd
//	 * @return
//	 */
//	@AutoLog(value = "验货抽检标准-编辑")
//	@ApiOperation(value="验货抽检标准-编辑", notes="验货抽检标准-编辑")
//	@PutMapping(value = "/edit")
//	public Result<SampleInspectStd> edit(@RequestBody SampleInspectStd sampleInspectStd) {
//		Result<SampleInspectStd> result = new Result<SampleInspectStd>();
//		SampleInspectStd sampleInspectStdEntity = sampleInspectStdService.getById(sampleInspectStd.getId());
//		if(sampleInspectStdEntity==null) {
//			result.error500("未找到对应实体");
//		}else {
//			boolean ok = sampleInspectStdService.updateById(sampleInspectStd);
//			//TODO 返回false说明什么？
//			if(ok) {
//				result.success("修改成功!");
//			}
//		}
//
//		return result;
//	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "验货抽检标准-通过id删除")
	@ApiOperation(value="验货抽检标准-通过id删除", notes="验货抽检标准-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			sampleInspectStdService.removeById(id);
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
	@AutoLog(value = "验货抽检标准-批量删除")
	@ApiOperation(value="验货抽检标准-批量删除", notes="验货抽检标准-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<SampleInspectStd> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<SampleInspectStd> result = new Result<SampleInspectStd>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.sampleInspectStdService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "验货抽检标准-通过id查询")
	@ApiOperation(value="验货抽检标准-通过id查询", notes="验货抽检标准-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SampleInspectStd> queryById(@RequestParam(name="id",required=true) String id) {
		Result<SampleInspectStd> result = new Result<SampleInspectStd>();
		SampleInspectStd sampleInspectStd = sampleInspectStdService.getById(id);
		if(sampleInspectStd==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(sampleInspectStd);
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
      QueryWrapper<SampleInspectStd> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              SampleInspectStd sampleInspectStd = JSON.parseObject(deString, SampleInspectStd.class);
              queryWrapper = QueryGenerator.initQueryWrapper(sampleInspectStd, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<SampleInspectStd> pageList = sampleInspectStdService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "验货抽检标准列表");
      mv.addObject(NormalExcelConstants.CLASS, SampleInspectStd.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("验货抽检标准列表数据", "导出人:Jeecg", "导出信息"));
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
              List<SampleInspectStd> listSampleInspectStds = ExcelImportUtil.importExcel(file.getInputStream(), SampleInspectStd.class, params);
              sampleInspectStdService.saveBatch(listSampleInspectStds);
              return Result.ok("文件导入成功！数据行数:" + listSampleInspectStds.size());
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
