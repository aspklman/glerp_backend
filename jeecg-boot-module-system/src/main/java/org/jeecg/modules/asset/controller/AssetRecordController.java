package org.jeecg.modules.asset.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.asset.entity.AssetChange;
import org.jeecg.modules.asset.entity.AssetRecord;
import org.jeecg.modules.asset.service.IAssetRecordService;
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
 * @Description: 资产档案表
 * @Author: jeecg-boot
 * @Date:   2023-04-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="资产档案表")
@RestController
@RequestMapping("/asset/assetRecord")
public class AssetRecordController {
	@Autowired
	private IAssetRecordService assetRecordService;

	 /**
	  * 异动资产明细
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "资产档案-异动资产明细")
	 @ApiOperation(value = "资产档案-异动资产明细", notes = "资产档案-异动资产明细")
	 @GetMapping(value = "/updateAssetDetail")
	 public Result<AssetRecord> updateAssetDetail(@RequestParam("pssr") String pssr) {
		 Result<AssetRecord> result = new Result<AssetRecord>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String userNo = loginUser.getUsername();
			 String[] p = pssr.split(",");
			 String assetNo = p[0];
			 String state = p[1];
			 String factLocation = p[2];
			 String deptNo = p[3];
			 String location = p[4];
			 String locationFl = p[5];
			 String locationDl = p[6];
			 String locationFact = p[7];
			 String custodian = p[8];
			 String custodianUser = p[9];
			 log.info("参数8：" + factNo + "/" + assetNo);
			 this.assetRecordService.updateAssetDetail(factNo, assetNo, state, factLocation, deptNo, location, locationFl, locationDl, locationFact, custodian, custodianUser, userNo);
//			 result.setResult(serialNo);
			 result.success("获取成功!");
		 }
		 log.info("结果：" + result);
		 return result;
	 }

	 /**
	  * 获取并填写资产编号
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "资产档案-获取并填写资产编号")
	 @ApiOperation(value = "资产档案-获取并填写资产编号", notes = "资产档案-获取并填写资产编号")
	 @GetMapping(value = "/getAssetNo")
	 public Result<String> getAssetNo(@RequestParam("pssr") String pssr) {
		 Result<String> result = new Result<String>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String[] p = pssr.split(",");
			 String catalogId = p[0];
			 log.info("参数8：" + factNo + "/" + catalogId);
			 String serialNo = this.assetRecordService.getAssetNo(factNo, catalogId);
			 result.setResult(serialNo);
			 result.success("获取成功!");
		 }
         log.info("结果：" + result);
         return result;
	 }
	
	/**
	  * 分页列表查询
	 * @param assetRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "资产档案表-分页列表查询")
	@ApiOperation(value="资产档案表-分页列表查询", notes="资产档案表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AssetRecord>> queryPageList(AssetRecord assetRecord,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<AssetRecord>> result = new Result<IPage<AssetRecord>>();
		QueryWrapper<AssetRecord> queryWrapper = QueryGenerator.initQueryWrapper(assetRecord, req.getParameterMap());
		Page<AssetRecord> page = new Page<AssetRecord>(pageNo, pageSize);
		IPage<AssetRecord> pageList = assetRecordService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param assetRecord
	 * @return
	 */
	@AutoLog(value = "资产档案表-添加")
	@ApiOperation(value="资产档案表-添加", notes="资产档案表-添加")
	@PostMapping(value = "/add")
	public Result<AssetRecord> add(@RequestBody AssetRecord assetRecord) {
		Result<AssetRecord> result = new Result<AssetRecord>();
		try {
			assetRecordService.save(assetRecord);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param assetRecord
	 * @return
	 */
	@AutoLog(value = "资产档案表-编辑")
	@ApiOperation(value="资产档案表-编辑", notes="资产档案表-编辑")
	@PutMapping(value = "/edit")
	public Result<AssetRecord> edit(@RequestBody AssetRecord assetRecord) {
		Result<AssetRecord> result = new Result<AssetRecord>();
		AssetRecord assetRecordEntity = assetRecordService.getById(assetRecord.getId());
		if(assetRecordEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = assetRecordService.updateById(assetRecord);
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
	@AutoLog(value = "资产档案表-通过id删除")
	@ApiOperation(value="资产档案表-通过id删除", notes="资产档案表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			assetRecordService.removeById(id);
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
	@AutoLog(value = "资产档案表-批量删除")
	@ApiOperation(value="资产档案表-批量删除", notes="资产档案表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<AssetRecord> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<AssetRecord> result = new Result<AssetRecord>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.assetRecordService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资产档案表-通过id查询")
	@ApiOperation(value="资产档案表-通过id查询", notes="资产档案表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AssetRecord> queryById(@RequestParam(name="id",required=true) String id) {
		Result<AssetRecord> result = new Result<AssetRecord>();
		AssetRecord assetRecord = assetRecordService.getById(id);
		if(assetRecord==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(assetRecord);
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
      QueryWrapper<AssetRecord> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              AssetRecord assetRecord = JSON.parseObject(deString, AssetRecord.class);
              queryWrapper = QueryGenerator.initQueryWrapper(assetRecord, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<AssetRecord> pageList = assetRecordService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "资产档案表列表");
      mv.addObject(NormalExcelConstants.CLASS, AssetRecord.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("资产档案表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<AssetRecord> listAssetRecords = ExcelImportUtil.importExcel(file.getInputStream(), AssetRecord.class, params);
              assetRecordService.saveBatch(listAssetRecords);
              return Result.ok("文件导入成功！数据行数:" + listAssetRecords.size());
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
