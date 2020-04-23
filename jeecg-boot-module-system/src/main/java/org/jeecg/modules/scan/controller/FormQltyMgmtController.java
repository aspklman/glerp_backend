package org.jeecg.modules.scan.controller;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.scan.entity.FormQltyMgmt;
import org.jeecg.modules.scan.entity.StgscanQuality;
import org.jeecg.modules.scan.service.IFormQltyMgmtService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.scan.service.IStgscanQualityService;
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
 * @Description: 成型品质管理
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="成型品质管理")
@RestController
@RequestMapping("/scan/formQltyMgmt")
public class FormQltyMgmtController {
	@Autowired
	private IFormQltyMgmtService formQltyMgmtService;
	@Autowired
    private IStgscanQualityService stgscanQualityService;
	
	/**
	  * 分页列表查询
	 * @param formQltyMgmt
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "成型品质管理-分页列表查询")
	@ApiOperation(value="成型品质管理-分页列表查询", notes="成型品质管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FormQltyMgmt>> queryPageList(FormQltyMgmt formQltyMgmt,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<FormQltyMgmt>> result = new Result<IPage<FormQltyMgmt>>();
		QueryWrapper<FormQltyMgmt> queryWrapper = QueryGenerator.initQueryWrapper(formQltyMgmt, req.getParameterMap());
		queryWrapper.orderByDesc("bad_level");
		queryWrapper.orderByAsc("bad_no");
		Page<FormQltyMgmt> page = new Page<FormQltyMgmt>(pageNo, pageSize);
		IPage<FormQltyMgmt> pageList = formQltyMgmtService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

     /**
      * 不良原因名称及数量查询
      * @param pssr
      * @return
      */
     @AutoLog(value = "成型品质管理-不良原因名称及数量查询")
     @ApiOperation(value="成型品质管理-不良原因名称及数量查询", notes="成型品质管理-不良原因名称及数量查询")
     @GetMapping(value = "/getBadNoQty")
     public Result<List<String>> getBadNoQty(@RequestParam("pssr") String pssr) {
         Result<List<String>> result = new Result<List<String>>();
         if(pssr==null) {
             result.error500("参数不识别！");
         }else {
             String[] p = pssr.split(",");
             String proFact = p[0];
             String styleNo = p[1];
             String secNo = p[2];
             String recDate = p[3];
             log.info("======传入参数======");
             log.info("厂别：" + proFact);
             log.info("型体：" + styleNo);
             log.info("组别：" + secNo);
             log.info("日期：" + recDate);
             log.info("====================");
             List<String> stgscanQualityList = this.stgscanQualityService.getBadNoQty(proFact, styleNo, secNo, recDate);
             result.setResult(stgscanQualityList);
             result.success("获取成功!");
         }
         log.info("结果：" + result);
         return result;
     }

     /**
      * 插入
      * @param pssrbba
      * @return
      */
     @AutoLog(value = "成型品质管理-插入")
     @ApiOperation(value="成型品质管理-插入", notes="成型品质管理-插入")
     @GetMapping(value = "/insertBadNoQty")
     public void insertBadNoQty(@RequestParam("pssrbba") String pssrbba) {
         Result result = new Result();
         if(pssrbba==null) {
             result.error500("参数不识别！");
         }else {
//             log.info("传入参数：" + pssrbba);
             String[] p = pssrbba.split(",");
             String proFact = p[0];
             String styleNo = p[1];
             String secNo = p[2];
             String recDate = p[3];
             String badLevel = p[4];
             String badNo = p[5];
             String addValue = p[6];
             //时间
             LocalDateTime localDateTime = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));      //时区：上海
             String pattern = "yyyyMMdd HHmmss";
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
             String recTime = formatter.format(localDateTime).substring(9);
             //用户
             LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String userNo = sysUser.getUsername();

//             log.info("厂别：" + proFact);
//             log.info("型体：" + styleNo);
//             log.info("组别：" + secNo);
//             log.info("日期：" + recDate);
//             log.info("不良等级：" + badLevel);
//             log.info("不良原因：" + badNo);
//             log.info("增加数：" + addValue);
//             log.info("时间：" + recTime);
//             log.info("用户：" + userNo);
             this.stgscanQualityService.insertBadNoQty(proFact, styleNo, secNo, recDate, badLevel, badNo, addValue, recTime, userNo);
             result.success("获取成功!");
         }
//         log.info("结果：" + result);
//         return result;
     }

	 /**
	  * 删除
	  * @param pssrb
	  * @return
	  */
	 @AutoLog(value = "成型品质管理-删除")
	 @ApiOperation(value="成型品质管理-删除", notes="成型品质管理-删除")
	 @GetMapping(value = "/deleteBadNoQty")
	 public void deleteBadNoQty(@RequestParam("pssrb") String pssrb) {
		 Result result = new Result();
		 if(pssrb==null) {
			 result.error500("参数不识别！");
		 }else {
//			 log.info("传入参数：" + pssrb);
			 String[] p = pssrb.split(",");
			 String proFact = p[0];
			 String styleNo = p[1];
			 String secNo = p[2];
			 String recDate = p[3];
			 String badNo = p[4];
			 //用户
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String userNo = sysUser.getUsername();

//			 log.info("厂别：|" + proFact + '|');
//			 log.info("型体：|" + styleNo + '|');
//			 log.info("组别：|" + secNo + '|');
//			 log.info("日期：|" + recDate + '|');
//			 log.info("不良原因：|" + badNo + '|');
//			 log.info("用户：|" + userNo + '|');
			 this.stgscanQualityService.deleteBadNoQty(proFact, styleNo, secNo, recDate, badNo, userNo);
			 result.success("获取成功!");
		 }
//		 log.info("结果：" + result);
//		 return result;
	 }
	
	/**
	  *   添加
	 * @param formQltyMgmt
	 * @return
	 */
	@AutoLog(value = "成型品质管理-添加")
	@ApiOperation(value="成型品质管理-添加", notes="成型品质管理-添加")
	@PostMapping(value = "/add")
	public Result<FormQltyMgmt> add(@RequestBody FormQltyMgmt formQltyMgmt) {
		Result<FormQltyMgmt> result = new Result<FormQltyMgmt>();
		try {
			formQltyMgmtService.save(formQltyMgmt);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param formQltyMgmt
	 * @return
	 */
	@AutoLog(value = "成型品质管理-编辑")
	@ApiOperation(value="成型品质管理-编辑", notes="成型品质管理-编辑")
	@PutMapping(value = "/edit")
	public Result<FormQltyMgmt> edit(@RequestBody FormQltyMgmt formQltyMgmt) {
		Result<FormQltyMgmt> result = new Result<FormQltyMgmt>();
		FormQltyMgmt formQltyMgmtEntity = formQltyMgmtService.getById(formQltyMgmt.getId());
		if(formQltyMgmtEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = formQltyMgmtService.updateById(formQltyMgmt);
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
	@AutoLog(value = "成型品质管理-通过id删除")
	@ApiOperation(value="成型品质管理-通过id删除", notes="成型品质管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			formQltyMgmtService.removeById(id);
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
	@AutoLog(value = "成型品质管理-批量删除")
	@ApiOperation(value="成型品质管理-批量删除", notes="成型品质管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<FormQltyMgmt> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<FormQltyMgmt> result = new Result<FormQltyMgmt>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.formQltyMgmtService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "成型品质管理-通过id查询")
	@ApiOperation(value="成型品质管理-通过id查询", notes="成型品质管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FormQltyMgmt> queryById(@RequestParam(name="id",required=true) String id) {
		Result<FormQltyMgmt> result = new Result<FormQltyMgmt>();
		FormQltyMgmt formQltyMgmt = formQltyMgmtService.getById(id);
		if(formQltyMgmt==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(formQltyMgmt);
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
      QueryWrapper<FormQltyMgmt> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              FormQltyMgmt formQltyMgmt = JSON.parseObject(deString, FormQltyMgmt.class);
              queryWrapper = QueryGenerator.initQueryWrapper(formQltyMgmt, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<FormQltyMgmt> pageList = formQltyMgmtService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "成型品质管理列表");
      mv.addObject(NormalExcelConstants.CLASS, FormQltyMgmt.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("成型品质管理列表数据", "导出人:Jeecg", "导出信息"));
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
              List<FormQltyMgmt> listFormQltyMgmts = ExcelImportUtil.importExcel(file.getInputStream(), FormQltyMgmt.class, params);
              formQltyMgmtService.saveBatch(listFormQltyMgmts);
              return Result.ok("文件导入成功！数据行数:" + listFormQltyMgmts.size());
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
