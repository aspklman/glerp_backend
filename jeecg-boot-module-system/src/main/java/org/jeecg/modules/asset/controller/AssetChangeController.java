package org.jeecg.modules.asset.controller;

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
import org.jeecg.modules.asset.entity.AssetChange;
import org.jeecg.modules.asset.service.IAssetChangeService;
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
 * @Description: 资产异动表
 * @Author: jeecg-boot
 * @Date:   2023-04-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="资产异动表")
@RestController
@RequestMapping("/asset/assetChange")
public class AssetChangeController {
	@Autowired
	private IAssetChangeService assetChangeService;

	 /**
	  * 获取异动单号
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "资产档案-获取异动单号")
	 @ApiOperation(value = "资产档案-获取异动单号", notes = "资产档案-获取异动单号")
	 @GetMapping(value = "/getChangeNo")
	 public Result<String> getChangeNo(@RequestParam("pssr") String pssr) {
		 Result<String> result = new Result<String>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String[] p = pssr.split(",");
			 String changeNo = p[0];
			 log.info("参数8：" + factNo + "/" + changeNo);
			 String newChangeNo = this.assetChangeService.getChangeNo(factNo);
			 result.setResult(newChangeNo);
			 result.success("获取成功!");
		 }
		 log.info("结果：" + result);
		 return result;
	 }

	 /**
	  * 根据资产编号获取资产明细
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "资产档案-根据资产编号获取资产明细")
	 @ApiOperation(value = "资产档案-根据资产编号获取资产明细", notes = "资产档案-根据资产编号获取资产明细")
	 @GetMapping(value = "/getAssetDetailByNo")
	 public Result<Map<String, Object>> getAssetDetailByNo(@RequestParam("pssr") String pssr) {
		 Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String[] p = pssr.split(",");
			 String assetNo = p[0];
			 log.info("参数8：" + factNo + "/" + assetNo);
			 Map<String, Object> assetDetail = this.assetChangeService.getAssetDetailByNo(factNo, assetNo);
			 result.setResult(assetDetail);
			 result.success("获取成功!");
		 }
		 log.info("结果：" + result);
		 return result;
	 }
	
	/**
	  * 分页列表查询
	 * @param assetChange
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "资产异动表-分页列表查询")
	@ApiOperation(value="资产异动表-分页列表查询", notes="资产异动表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AssetChange>> queryPageList(AssetChange assetChange,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<AssetChange>> result = new Result<IPage<AssetChange>>();
		QueryWrapper<AssetChange> queryWrapper = QueryGenerator.initQueryWrapper(assetChange, req.getParameterMap());
		Page<AssetChange> page = new Page<AssetChange>(pageNo, pageSize);
		IPage<AssetChange> pageList = assetChangeService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param assetChange
	 * @return
	 */
	@AutoLog(value = "资产异动表-添加")
	@ApiOperation(value="资产异动表-添加", notes="资产异动表-添加")
	@PostMapping(value = "/add")
	public Result<AssetChange> add(@RequestBody AssetChange assetChange) {
		Result<AssetChange> result = new Result<AssetChange>();
		try {
			assetChangeService.save(assetChange);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param assetChange
	 * @return
	 */
	@AutoLog(value = "资产异动表-编辑")
	@ApiOperation(value="资产异动表-编辑", notes="资产异动表-编辑")
	@PutMapping(value = "/edit")
	public Result<AssetChange> edit(@RequestBody AssetChange assetChange) {
		Result<AssetChange> result = new Result<AssetChange>();
		AssetChange assetChangeEntity = assetChangeService.getById(assetChange.getId());
		if(assetChangeEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = assetChangeService.updateById(assetChange);
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
	@AutoLog(value = "资产异动表-通过id删除")
	@ApiOperation(value="资产异动表-通过id删除", notes="资产异动表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			assetChangeService.removeById(id);
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
	@AutoLog(value = "资产异动表-批量删除")
	@ApiOperation(value="资产异动表-批量删除", notes="资产异动表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<AssetChange> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<AssetChange> result = new Result<AssetChange>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.assetChangeService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资产异动表-通过id查询")
	@ApiOperation(value="资产异动表-通过id查询", notes="资产异动表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AssetChange> queryById(@RequestParam(name="id",required=true) String id) {
		Result<AssetChange> result = new Result<AssetChange>();
		AssetChange assetChange = assetChangeService.getById(id);
		if(assetChange==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(assetChange);
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
      QueryWrapper<AssetChange> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              AssetChange assetChange = JSON.parseObject(deString, AssetChange.class);
              queryWrapper = QueryGenerator.initQueryWrapper(assetChange, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<AssetChange> pageList = assetChangeService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "资产异动表列表");
      mv.addObject(NormalExcelConstants.CLASS, AssetChange.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("资产异动表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<AssetChange> listAssetChanges = ExcelImportUtil.importExcel(file.getInputStream(), AssetChange.class, params);
              assetChangeService.saveBatch(listAssetChanges);
              return Result.ok("文件导入成功！数据行数:" + listAssetChanges.size());
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
