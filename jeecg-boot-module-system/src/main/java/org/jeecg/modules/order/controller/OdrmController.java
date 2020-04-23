package org.jeecg.modules.order.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.bom.entity.Stylem;
import org.jeecg.modules.bom.vo.StylemPage;
import org.jeecg.modules.order.entity.Odrd;
import org.jeecg.modules.order.entity.Odrm;
import org.jeecg.modules.order.vo.OdrmPage;
import org.jeecg.modules.order.service.IOdrmService;
import org.jeecg.modules.order.service.IOdrdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 订单主表
 * @Author: jeecg-boot
 * @Date:   2019-07-20 10:01:01
 * @Version: V1.0
 */
@RestController
@RequestMapping("/order/odrm")
@Slf4j
public class OdrmController {
	@Autowired
	private IOdrmService odrmService;
	@Autowired
	private IOdrdService odrdService;

	/**
	 * 分页列表查询
	 * @param odrm
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "order/OdrmList")
	public Result<IPage<Odrm>> queryPageList(Odrm odrm,
											 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											 HttpServletRequest req) {
		Result<IPage<Odrm>> result = new Result<IPage<Odrm>>();
		QueryWrapper<Odrm> queryWrapper = QueryGenerator.initQueryWrapper(odrm, req.getParameterMap());
//		queryWrapper.and(wrapper -> wrapper.eq("pro_dept", "U"));		//查询条件中增加部别
		Page<Odrm> page = new Page<Odrm>(pageNo, pageSize);
		IPage<Odrm> pageList = odrmService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 *   添加
	 * @param odrmPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Odrm> add(@RequestBody OdrmPage odrmPage) {
		Result<Odrm> result = new Result<Odrm>();
		try {
			Odrm odrm = new Odrm();
			BeanUtils.copyProperties(odrmPage, odrm);
			odrmService.saveMain(odrm, odrmPage.getOdrdList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

//	/**
//	 *  编辑
//	 * @param odrm
//	 * @return
//	 */
//	@PutMapping(value = "/edit")
//	public Result<Odrm> edit(@RequestBody Odrm odrm) {
//		Result<Odrm> result = new Result<Odrm>();
//		Odrm odrmEntity = odrmService.getById(odrm.getId());
//		if(odrmEntity==null) {
//			result.error500("未找到对应实体");
//		}else {
//			odrm.setUpdateTime(new Date());
//			boolean ok = odrmService.updateById(odrm);
//			//TODO 返回false说明什么？
//			if(ok) {
//				result.success("修改成功!");
//			}
//		}
//		return result;
//	}
	@PutMapping(value = "/edit")
	public Result<Odrm> edit(@RequestBody OdrmPage odrmPage) {
		Result<Odrm> result = new Result<Odrm>();
		Odrm odrm = new Odrm();
		BeanUtils.copyProperties(odrmPage, odrm);
		Odrm odrmEntity = odrmService.getById(odrmPage.getId());
		if(odrmEntity==null) {
			result.error500("未找到对应实体");
		}else {
//			odrmPage.setUpdateTime(new Date());
			boolean ok = odrmService.updateById(odrm);
			odrmService.updateMain(odrm, odrmPage.getOdrdList());
			result.success("修改成功!");
		}
		return result;
	}

	/**
	 *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			odrmService.delMain(id);
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
	@DeleteMapping(value = "/deleteBatch")
	public Result<Odrm> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Odrm> result = new Result<Odrm>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.odrmService.delBatchMain(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<Odrm> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Odrm> result = new Result<Odrm>();
		Odrm odrm = odrmService.getById(id);
		if(odrm==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(odrm);
			result.setSuccess(true);
		}
		return result;
	}

	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryOdrdByMainId")
	public Result<List<Odrd>> queryOdrdListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<Odrd>> result = new Result<List<Odrd>>();
		List<Odrd> odrdList = odrdService.selectByMainId(id);
		result.setResult(odrdList);
		result.setSuccess(true);
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
		QueryWrapper<Odrm> queryWrapper = null;
		try {
			String paramsStr = request.getParameter("paramsStr");
			if (oConvertUtils.isNotEmpty(paramsStr)) {
				String deString = URLDecoder.decode(paramsStr, "UTF-8");
				Odrm odrm = JSON.parseObject(deString, Odrm.class);
				queryWrapper = QueryGenerator.initQueryWrapper(odrm, request.getParameterMap());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		//Step.2 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		List<OdrmPage> pageList = new ArrayList<OdrmPage>();
		List<Odrm> odrmList = odrmService.list(queryWrapper);
		for (Odrm odrm : odrmList) {
			OdrmPage vo = new OdrmPage();
			BeanUtils.copyProperties(odrm, vo);
			List<Odrd> odrdList = odrdService.selectByMainId(odrm.getId());
			vo.setOdrdList(odrdList);
			pageList.add(vo);
		}
		//导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "订单主表列表");
		mv.addObject(NormalExcelConstants.CLASS, OdrmPage.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("订单主表列表数据", "导出人:Jeecg", "导出信息"));
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
				List<OdrmPage> list = ExcelImportUtil.importExcel(file.getInputStream(), OdrmPage.class, params);
				for (OdrmPage page : list) {
					Odrm po = new Odrm();
					BeanUtils.copyProperties(page, po);
					odrmService.saveMain(po, page.getOdrdList());
				}
				return Result.ok("文件导入成功！数据行数:" + list.size());
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
