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
import org.jeecg.modules.scan.entity.PurSupPicture;
import org.jeecg.modules.scan.service.IPurSupPictureService;
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
 * @Description: SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="SKU图片表")
@RestController
@RequestMapping("/scan/purSupPicture")
public class PurSupPictureController {
	@Autowired
	private IPurSupPictureService purSupPictureService;
	
	/**
	  * 分页列表查询
	 * @param purSupPicture
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "SKU图片表-分页列表查询")
	@ApiOperation(value="SKU图片表-分页列表查询", notes="SKU图片表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<PurSupPicture>> queryPageList(PurSupPicture purSupPicture,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<PurSupPicture>> result = new Result<IPage<PurSupPicture>>();
		QueryWrapper<PurSupPicture> queryWrapper = QueryGenerator.initQueryWrapper(purSupPicture, req.getParameterMap());
		Page<PurSupPicture> page = new Page<PurSupPicture>(pageNo, pageSize);
		IPage<PurSupPicture> pageList = purSupPictureService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	 //	 @ResponseBody
//	 @PostMapping("/upload")
	 @RequestMapping("/upload")
	 public Result upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("sku") String sku, @RequestParam("basicdataid") String basicDataId) {
		 Result result = new Result();
		 try {
//		 	String sku = purSupBasicData.getSku();
//		 	String sku = JSON.toJSONString(obj);
			 log.info("控制文件：" + sku);
			 purSupPictureService.upload(multipartFile, sku, basicDataId);
			 result.success("上传成功！");
		 }
		 catch (Exception ex) {
			 log.info(ex.getMessage(), ex);
			 result.error500("上传失败");
		 }
		 return result;
	 }
	
	/**
	  *   添加
	 * @param purSupPicture
	 * @return
	 */
	@AutoLog(value = "SKU图片表-添加")
	@ApiOperation(value="SKU图片表-添加", notes="SKU图片表-添加")
	@PostMapping(value = "/add")
	public Result<PurSupPicture> add(@RequestBody PurSupPicture purSupPicture) {
		Result<PurSupPicture> result = new Result<PurSupPicture>();
		try {
			purSupPictureService.save(purSupPicture);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param purSupPicture
	 * @return
	 */
	@AutoLog(value = "SKU图片表-编辑")
	@ApiOperation(value="SKU图片表-编辑", notes="SKU图片表-编辑")
	@PutMapping(value = "/edit")
	public Result<PurSupPicture> edit(@RequestBody PurSupPicture purSupPicture) {
		Result<PurSupPicture> result = new Result<PurSupPicture>();
		PurSupPicture purSupPictureEntity = purSupPictureService.getById(purSupPicture.getId());
		if(purSupPictureEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = purSupPictureService.updateById(purSupPicture);
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
	@AutoLog(value = "SKU图片表-通过id删除")
	@ApiOperation(value="SKU图片表-通过id删除", notes="SKU图片表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			purSupPictureService.removeById(id);
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
	@AutoLog(value = "SKU图片表-批量删除")
	@ApiOperation(value="SKU图片表-批量删除", notes="SKU图片表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<PurSupPicture> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<PurSupPicture> result = new Result<PurSupPicture>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.purSupPictureService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "SKU图片表-通过id查询")
	@ApiOperation(value="SKU图片表-通过id查询", notes="SKU图片表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<PurSupPicture> queryById(@RequestParam(name="id",required=true) String id) {
		Result<PurSupPicture> result = new Result<PurSupPicture>();
		PurSupPicture purSupPicture = purSupPictureService.getById(id);
		if(purSupPicture==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(purSupPicture);
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
      QueryWrapper<PurSupPicture> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              PurSupPicture purSupPicture = JSON.parseObject(deString, PurSupPicture.class);
              queryWrapper = QueryGenerator.initQueryWrapper(purSupPicture, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<PurSupPicture> pageList = purSupPictureService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "SKU图片表列表");
      mv.addObject(NormalExcelConstants.CLASS, PurSupPicture.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("SKU图片表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<PurSupPicture> listPurSupPictures = ExcelImportUtil.importExcel(file.getInputStream(), PurSupPicture.class, params);
              purSupPictureService.saveBatch(listPurSupPictures);
              return Result.ok("文件导入成功！数据行数:" + listPurSupPictures.size());
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
