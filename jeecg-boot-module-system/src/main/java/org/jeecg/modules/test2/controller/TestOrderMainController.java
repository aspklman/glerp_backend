package org.jeecg.modules.test2.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.test2.entity.TestOrderCustom;
import org.jeecg.modules.test2.entity.TestOrderTicket;
import org.jeecg.modules.test2.entity.TestOrderMain;
import org.jeecg.modules.test2.vo.TestOrderMainPage;
import org.jeecg.modules.test2.service.ITestOrderMainService;
import org.jeecg.modules.test2.service.ITestOrderCustomService;
import org.jeecg.modules.test2.service.ITestOrderTicketService;
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
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-19
 * @Version: V1.0
 */
@RestController
@RequestMapping("/test2/testOrderMain")
@Slf4j
public class TestOrderMainController {
	@Autowired
	private ITestOrderMainService testOrderMainService;
	@Autowired
	private ITestOrderCustomService testOrderCustomService;
	@Autowired
	private ITestOrderTicketService testOrderTicketService;
	
	/**
	  * 分页列表查询
	 * @param testOrderMain
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<TestOrderMain>> queryPageList(TestOrderMain testOrderMain,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TestOrderMain>> result = new Result<IPage<TestOrderMain>>();
		QueryWrapper<TestOrderMain> queryWrapper = QueryGenerator.initQueryWrapper(testOrderMain, req.getParameterMap());
		Page<TestOrderMain> page = new Page<TestOrderMain>(pageNo, pageSize);
		IPage<TestOrderMain> pageList = testOrderMainService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param testOrderMainPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<TestOrderMain> add(@RequestBody TestOrderMainPage testOrderMainPage) {
		Result<TestOrderMain> result = new Result<TestOrderMain>();
		try {
			TestOrderMain testOrderMain = new TestOrderMain();
			BeanUtils.copyProperties(testOrderMainPage, testOrderMain);
			
			testOrderMainService.saveMain(testOrderMain, testOrderMainPage.getTestOrderCustomList(),testOrderMainPage.getTestOrderTicketList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param testOrderMainPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<TestOrderMain> edit(@RequestBody TestOrderMainPage testOrderMainPage) {
		Result<TestOrderMain> result = new Result<TestOrderMain>();
		TestOrderMain testOrderMain = new TestOrderMain();
		BeanUtils.copyProperties(testOrderMainPage, testOrderMain);
		TestOrderMain testOrderMainEntity = testOrderMainService.getById(testOrderMain.getId());
		if(testOrderMainEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = testOrderMainService.updateById(testOrderMain);
			testOrderMainService.updateMain(testOrderMain, testOrderMainPage.getTestOrderCustomList(),testOrderMainPage.getTestOrderTicketList());
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
			testOrderMainService.delMain(id);
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
	public Result<TestOrderMain> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TestOrderMain> result = new Result<TestOrderMain>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.testOrderMainService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<TestOrderMain> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TestOrderMain> result = new Result<TestOrderMain>();
		TestOrderMain testOrderMain = testOrderMainService.getById(id);
		if(testOrderMain==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(testOrderMain);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryTestOrderCustomByMainId")
	public Result<List<TestOrderCustom>> queryTestOrderCustomListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<TestOrderCustom>> result = new Result<List<TestOrderCustom>>();
		List<TestOrderCustom> testOrderCustomList = testOrderCustomService.selectByMainId(id);
		result.setResult(testOrderCustomList);
		result.setSuccess(true);
		return result;
	}
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryTestOrderTicketByMainId")
	public Result<List<TestOrderTicket>> queryTestOrderTicketListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<TestOrderTicket>> result = new Result<List<TestOrderTicket>>();
		List<TestOrderTicket> testOrderTicketList = testOrderTicketService.selectByMainId(id);
		result.setResult(testOrderTicketList);
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
      QueryWrapper<TestOrderMain> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TestOrderMain testOrderMain = JSON.parseObject(deString, TestOrderMain.class);
              queryWrapper = QueryGenerator.initQueryWrapper(testOrderMain, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TestOrderMainPage> pageList = new ArrayList<TestOrderMainPage>();
      List<TestOrderMain> testOrderMainList = testOrderMainService.list(queryWrapper);
      for (TestOrderMain testOrderMain : testOrderMainList) {
          TestOrderMainPage vo = new TestOrderMainPage();
          BeanUtils.copyProperties(testOrderMain, vo);
          List<TestOrderCustom> testOrderCustomList = testOrderCustomService.selectByMainId(testOrderMain.getId());
          vo.setTestOrderCustomList(testOrderCustomList);
          List<TestOrderTicket> testOrderTicketList = testOrderTicketService.selectByMainId(testOrderMain.getId());
          vo.setTestOrderTicketList(testOrderTicketList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "订单列表");
      mv.addObject(NormalExcelConstants.CLASS, TestOrderMainPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("订单列表数据", "导出人:Jeecg", "导出信息"));
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
              List<TestOrderMainPage> list = ExcelImportUtil.importExcel(file.getInputStream(), TestOrderMainPage.class, params);
              for (TestOrderMainPage page : list) {
                  TestOrderMain po = new TestOrderMain();
                  BeanUtils.copyProperties(page, po);
                  testOrderMainService.saveMain(po, page.getTestOrderCustomList(),page.getTestOrderTicketList());
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
