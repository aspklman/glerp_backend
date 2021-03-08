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
import org.jeecg.modules.quality.entity.SampleInspectStyle;
import org.jeecg.modules.quality.service.ISampleInspectStyleService;
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
 * @Description: 型体检验项目
 * @Author: jeecg-boot
 * @Date:   2020-12-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="型体检验项目")
@RestController
@RequestMapping("/quality/sampleInspectStyle")
public class SampleInspectStyleController {
	@Autowired
	private ISampleInspectStyleService sampleInspectStyleService;
	
	/**
	  * 分页列表查询
	 * @param sampleInspectStyle
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "型体检验项目-分页列表查询")
	@ApiOperation(value="型体检验项目-分页列表查询", notes="型体检验项目-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="quality/SampleInspectStyleList")
	public Result<IPage<SampleInspectStyle>> queryPageList(SampleInspectStyle sampleInspectStyle,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<SampleInspectStyle>> result = new Result<IPage<SampleInspectStyle>>();
		QueryWrapper<SampleInspectStyle> queryWrapper = QueryGenerator.initQueryWrapper(sampleInspectStyle, req.getParameterMap());
		Page<SampleInspectStyle> page = new Page<SampleInspectStyle>(pageNo, pageSize);
		IPage<SampleInspectStyle> pageList = sampleInspectStyleService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

     /**
      * 检查点
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "型体检验项目-检查点")
     @ApiOperation(value = "型体检验项目-检查点", notes = "型体检验项目-检查点")
     @GetMapping(value = "/getCheckPoints")
     public Result<List<Map<String, Object>>> getCheckPoints(@RequestParam("pssr") String pssr) {
         Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
             String[] p = pssr.split(",");
             String styleShorten = p[0];
             String itemMainNo = p[1];
             String itemMediumNo = p[2];
             log.info("参数：" + factNo + '/' + styleShorten + '/' + itemMainNo + '/' +  itemMediumNo);
             List<Map<String, Object>> checkPoints = this.sampleInspectStyleService.getCheckPoints(factNo, styleShorten, itemMainNo, itemMediumNo);
             result.setResult(checkPoints);
             result.success("获取成功!");
         }
         log.info("结果：" + result);
         return result;
     }

     /**
      * 增加检查点
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "型体检验项目-增加检查点")
     @ApiOperation(value = "型体检验项目-增加检查点", notes = "型体检验项目-增加检查点")
     @GetMapping(value = "/addCheckPoint")
     public void addCheckPoint(@RequestParam("pssr") String pssr) {
         Result result = new Result<>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
             String[] p = pssr.split(",");
             String styleShorten = p[0];
             String itemMainNo = p[1];
             String itemMediumNo = p[2];
             String checkPointsNo = p[3];
             log.info("参数：" + factNo + '/' + styleShorten + '/' + itemMainNo + '/' +  itemMediumNo + '/' +  checkPointsNo);
             this.sampleInspectStyleService.addCheckPoint(factNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo);
             result.ok("增加成功！");
         }
     }

     /**
      * 删除检查点
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "型体检验项目-删除检查点")
     @ApiOperation(value = "型体检验项目-删除检查点", notes = "型体检验项目-删除检查点")
     @GetMapping(value = "/subtractCheckPoint")
     public void subtractCheckPoint(@RequestParam("pssr") String pssr) {
         Result result = new Result<>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
             String[] p = pssr.split(",");
             String styleShorten = p[0];
             String itemMainNo = p[1];
             String itemMediumNo = p[2];
             String checkPointsNo = p[3];
             log.info("参数：" + factNo + '/' + styleShorten + '/' + itemMainNo + '/' +  itemMediumNo + '/' +  checkPointsNo);
             this.sampleInspectStyleService.subtractCheckPoint(factNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo);
             result.ok("删除成功！");
         }
     }
	
//	/**
//	  *   添加
//	 * @param sampleInspectStyle
//	 * @return
//	 */
//	@AutoLog(value = "型体检验项目-添加")
//	@ApiOperation(value="型体检验项目-添加", notes="型体检验项目-添加")
//	@PostMapping(value = "/add")
//	public Result<SampleInspectStyle> add(@RequestBody SampleInspectStyle sampleInspectStyle) {
//		Result<SampleInspectStyle> result = new Result<SampleInspectStyle>();
//		try {
//			sampleInspectStyleService.save(sampleInspectStyle);
//			result.success("添加成功！");
//		} catch (Exception e) {
//			log.error(e.getMessage(),e);
//			result.error500("操作失败");
//		}
//		return result;
//	}
	
//	/**
//	  *  编辑
//	 * @param sampleInspectStyle
//	 * @return
//	 */
//	@AutoLog(value = "型体检验项目-编辑")
//	@ApiOperation(value="型体检验项目-编辑", notes="型体检验项目-编辑")
//	@PutMapping(value = "/edit")
//	public Result<SampleInspectStyle> edit(@RequestBody SampleInspectStyle sampleInspectStyle) {
//		Result<SampleInspectStyle> result = new Result<SampleInspectStyle>();
//		SampleInspectStyle sampleInspectStyleEntity = sampleInspectStyleService.getById(sampleInspectStyle.getId());
//		if(sampleInspectStyleEntity==null) {
//			result.error500("未找到对应实体");
//		}else {
//			boolean ok = sampleInspectStyleService.updateById(sampleInspectStyle);
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
	@AutoLog(value = "型体检验项目-通过id删除")
	@ApiOperation(value="型体检验项目-通过id删除", notes="型体检验项目-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			sampleInspectStyleService.removeById(id);
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
	@AutoLog(value = "型体检验项目-批量删除")
	@ApiOperation(value="型体检验项目-批量删除", notes="型体检验项目-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<SampleInspectStyle> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<SampleInspectStyle> result = new Result<SampleInspectStyle>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.sampleInspectStyleService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "型体检验项目-通过id查询")
	@ApiOperation(value="型体检验项目-通过id查询", notes="型体检验项目-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SampleInspectStyle> queryById(@RequestParam(name="id",required=true) String id) {
		Result<SampleInspectStyle> result = new Result<SampleInspectStyle>();
		SampleInspectStyle sampleInspectStyle = sampleInspectStyleService.getById(id);
		if(sampleInspectStyle==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(sampleInspectStyle);
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
      QueryWrapper<SampleInspectStyle> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              SampleInspectStyle sampleInspectStyle = JSON.parseObject(deString, SampleInspectStyle.class);
              queryWrapper = QueryGenerator.initQueryWrapper(sampleInspectStyle, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<SampleInspectStyle> pageList = sampleInspectStyleService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "型体检验项目列表");
      mv.addObject(NormalExcelConstants.CLASS, SampleInspectStyle.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("型体检验项目列表数据", "导出人:Jeecg", "导出信息"));
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
              List<SampleInspectStyle> listSampleInspectStyles = ExcelImportUtil.importExcel(file.getInputStream(), SampleInspectStyle.class, params);
              sampleInspectStyleService.saveBatch(listSampleInspectStyles);
              return Result.ok("文件导入成功！数据行数:" + listSampleInspectStyles.size());
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
