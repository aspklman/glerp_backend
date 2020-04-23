package org.jeecg.modules.bom.controller;

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
import org.jeecg.modules.bom.entity.Brand;
import org.jeecg.modules.bom.service.IBrandService;
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
 * @Description: 品牌表
 * @Author: jeecg-boot
 * @Date:   2019-07-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="品牌表")
@RestController
@RequestMapping("/bom/brand")
public class BrandController {
	@Autowired
	private IBrandService brandService;
	
	@Autowired
	private ISysDataLogService sysDataLogService;
	
	/**
	  * 分页列表查询
	 * @param brand
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "品牌表-分页列表查询")
	@ApiOperation(value="品牌表-分页列表查询", notes="品牌表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Brand>> queryPageList(Brand brand,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Brand>> result = new Result<IPage<Brand>>();
		QueryWrapper<Brand> queryWrapper = QueryGenerator.initQueryWrapper(brand, req.getParameterMap());
		Page<Brand> page = new Page<Brand>(pageNo, pageSize);
		IPage<Brand> pageList = brandService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param brand
	 * @return
	 */
	@AutoLog(value = "品牌表-添加")
	@ApiOperation(value="品牌表-添加", notes="品牌表-添加")
	@PostMapping(value = "/add")
	@RequiresPermissions("brand:add")
	public Result<Brand> add(@RequestBody Brand brand) {
		Result<Brand> result = new Result<Brand>();
		try {
			brand.setBrandNo(brand.getBrandNo().toUpperCase());		//编号转换成大写后保存
			brand.setBrandNm(brand.getBrandNm().toUpperCase());		//名称转换成大写后保存
			brandService.save(brand);
			sysDataLogService.addDataLog("brand", brand.getId(), JSONObject.toJSONString(brand));		//记录数据快照
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param brand
	 * @return
	 */
	@AutoLog(value = "品牌表-编辑")
	@ApiOperation(value="品牌表-编辑", notes="品牌表-编辑")
	@PutMapping(value = "/edit")
	@RequiresPermissions("brand:edit")
	public Result<Brand> edit(@RequestBody Brand brand) {
		Result<Brand> result = new Result<Brand>();
		Brand brandEntity = brandService.getById(brand.getId());
		if(brandEntity==null) {
			result.error500("未找到对应实体");
		}else {
			brand.setBrandNo(brand.getBrandNo().toUpperCase());		//编号转换成大写后保存
			brand.setBrandNm(brand.getBrandNm().toUpperCase());		//名称转换成大写后保存
			sysDataLogService.addDataLog("brand", brand.getId(), JSONObject.toJSONString(brand));		//记录数据快照
			boolean ok = brandService.updateById(brand);
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
	@AutoLog(value = "品牌表-通过id删除")
	@ApiOperation(value="品牌表-通过id删除", notes="品牌表-通过id删除")
	@DeleteMapping(value = "/delete")
	@RequiresPermissions("user:delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			brandService.removeById(id);
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
	@AutoLog(value = "品牌表-批量删除")
	@ApiOperation(value="品牌表-批量删除", notes="品牌表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	@RequiresPermissions("brand:deleteBatch")
	public Result<Brand> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Brand> result = new Result<Brand>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.brandService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "品牌表-通过id查询")
	@ApiOperation(value="品牌表-通过id查询", notes="品牌表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Brand> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Brand> result = new Result<Brand>();
		Brand brand = brandService.getById(id);
		if(brand==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(brand);
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
  @RequiresPermissions("brand:exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Brand> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Brand brand = JSON.parseObject(deString, Brand.class);
              queryWrapper = QueryGenerator.initQueryWrapper(brand, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Brand> pageList = brandService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "品牌表列表");
      mv.addObject(NormalExcelConstants.CLASS, Brand.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("品牌表列表数据", "导出人:Jeecg", "导出信息"));
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
  @RequiresPermissions("brand:importExcel")
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
              List<Brand> listBrands = ExcelImportUtil.importExcel(file.getInputStream(), Brand.class, params);
              brandService.saveBatch(listBrands);
              return Result.ok("文件导入成功！数据行数:" + listBrands.size());
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
