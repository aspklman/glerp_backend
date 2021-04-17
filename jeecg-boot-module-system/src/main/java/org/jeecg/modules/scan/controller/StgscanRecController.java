package org.jeecg.modules.scan.controller;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
import org.jeecg.modules.scan.entity.StgscanRec;
import org.jeecg.modules.scan.service.IStgscanRecService;
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
 * @Description: 成品入库扫描(卡洛驰)
 * @Author: jeecg-boot
 * @Date:   2021-03-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="成品入库扫描(卡洛驰)")
@RestController
@RequestMapping("/scan/stgscanRec")
public class StgscanRecController {
	@Autowired
	private IStgscanRecService stgscanRecService;

     /**
      * 获取存放地点
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "成品入库扫描(卡洛驰)-获取存放地点")
     @ApiOperation(value = "成品入库扫描(卡洛驰)-获取存放地点", notes = "成品入库扫描(卡洛驰)-获取存放地点")
     @GetMapping(value = "/getLocArea")
     public Result<String> getLocArea(@RequestParam("pssr") String pssr) {
         Result<String> result = new Result<String>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
             String[] p = pssr.split(",");
             String stkNo = p[0];
             String locateNo = p[1];
             String locArea = p[2];
//             log.info("参数：" + factNo + '/' + stkNo + '/' + locateNo + '/' +  locArea);
             String serviceLocArea = this.stgscanRecService.getLocArea(factNo, stkNo, locateNo, locArea);
             result.setResult(serviceLocArea);
             result.success("获取成功!");
         }
//         log.info("结果：" + result);
         return result;
     }

	 /**
	  * 获取月结年月
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "成品入库扫描(卡洛驰)-获取月结年月")
	 @ApiOperation(value = "成品入库扫描(卡洛驰)-获取月结年月", notes = "成品入库扫描(卡洛驰)-获取月结年月")
	 @GetMapping(value = "/getMonthlySettlement")
	 public Result<String> getMonthlySettlement(@RequestParam("pssr") String pssr) {
		 Result<String> result = new Result<String>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String stkNo = pssr;
			 String monthlySettlement = this.stgscanRecService.getMonthlySettlement(factNo, stkNo);
			 result.setResult(monthlySettlement);
			 result.success("获取成功!");
		 }
//		 log.info("结果：" + result);
		 return result;
	 }

	 /**
	  * 获取订单数据
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "成品入库扫描(卡洛驰)-获取订单数据")
	 @ApiOperation(value = "成品入库扫描(卡洛驰)-获取订单数据", notes = "成品入库扫描(卡洛驰)-获取订单数据")
	 @GetMapping(value = "/getOrderData")
	 public Result<Map<String, Object>> getOrderData(@RequestParam("pssr") String pssr) {
		 Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String outboxNo = pssr;
             Map<String, Object> orderData = this.stgscanRecService.getOrderData(factNo, outboxNo);
			 result.setResult(orderData);
			 result.success("获取成功!");
		 }
//		 log.info("结果：" + result);
		 return result;
	 }

     /**
      * 检测条码是否重复扫描
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "成品入库扫描(卡洛驰)-检测条码是否重复扫描")
     @ApiOperation(value = "成品入库扫描(卡洛驰)-检测条码是否重复扫描", notes = "成品入库扫描(卡洛驰)-检测条码是否重复扫描")
     @GetMapping(value = "/getRepeatScan")
     public Result<Integer> getRepeatScan(@RequestParam("pssr") String pssr) {
         Result<Integer> result = new Result<Integer>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
             String outboxNo = pssr;
             Integer repeatScan = this.stgscanRecService.getRepeatScan(factNo, outboxNo);
             result.setResult(repeatScan);
             result.success("获取成功!");
         }
//         log.info("结果：" + result);
         return result;
     }

	 /**
	  * 获取成型包装扫描数据
	  *
	  * @param pssr
	  * @return
	  */
	 @AutoLog(value = "成品入库扫描(卡洛驰)-获取成型包装扫描数据")
	 @ApiOperation(value = "成品入库扫描(卡洛驰)-获取成型包装扫描数据", notes = "成品入库扫描(卡洛驰)-获取成型包装扫描数据")
	 @GetMapping(value = "/getMoldingData")
	 public Result<Map<String, Object>> getMoldingData(@RequestParam("pssr") String pssr) {
		 Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		 if (pssr == null) {
			 result.error500("参数不识别！");
		 } else {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String factNo = loginUser.getFactNo();
			 String outboxNo = pssr;
             Map<String, Object> moldingData = this.stgscanRecService.getMoldingData(factNo, outboxNo);
			 result.setResult(moldingData);
			 result.success("获取成功!");
		 }
//		 log.info("结果：" + result);
		 return result;
	 }

     /**
      * 获取未产生入库单之订单编号
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "成品入库扫描(卡洛驰)-获取未产生入库单之订单编号")
     @ApiOperation(value = "成品入库扫描(卡洛驰)-获取未产生入库单之订单编号", notes = "成品入库扫描(卡洛驰)-获取未产生入库单之订单编号")
     @GetMapping(value = "/getNoGenOrder")
     public Result<String> getNoGenOrder(@RequestParam("pssr") String pssr) {
         Result<String> result = new Result<String>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
             String userNo = loginUser.getUsername();
             String stkNo = pssr;
//             log.info("参数：" + factNo + '/' + stkNo + '/' + userNo);
             String noGenOrder = this.stgscanRecService.getNoGenOrder(factNo, stkNo, userNo);
             result.setResult(noGenOrder);
             result.success("获取成功!");
         }
//         log.info("结果：" + result);
         return result;
     }

     /**
      * 获取订单总箱数和总双数
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "成品入库扫描(卡洛驰)-获取订单总箱数和总双数")
     @ApiOperation(value = "成品入库扫描(卡洛驰)-获取订单总箱数和总双数", notes = "成品入库扫描(卡洛驰)-获取订单总箱数和总双数")
     @GetMapping(value = "/getTotalBoxesPairs")
     public Result<Map<String, Object>> getTotalBoxesPairs(@RequestParam("pssr") String pssr) {
         Result<Map<String, Object>> result = new Result<Map<String, Object>>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
             String factOdrNo = pssr;
             Map<String, Object> totalBoxesPairs = this.stgscanRecService.getTotalBoxesPairs(factNo, factOdrNo);
             result.setResult(totalBoxesPairs);
             result.success("获取成功!");
         }
//         log.info("结果：" + result);
         return result;
     }

//     /**
//      * 获取订单已扫描箱数
//      *
//      * @param pssr
//      * @return
//      */
//     @AutoLog(value = "成品入库扫描(卡洛驰)-获取订单已扫描箱数")
//     @ApiOperation(value = "成品入库扫描(卡洛驰)-获取订单已扫描箱数", notes = "成品入库扫描(卡洛驰)-获取订单已扫描箱数")
//     @GetMapping(value = "/getScannedBoxes")
//     public Result<Integer> getScannedBoxes(@RequestParam("pssr") String pssr) {
//         Result<Integer> result = new Result<Integer>();
//         if (pssr == null) {
//             result.error500("参数不识别！");
//         } else {
//             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//             String factNo = loginUser.getFactNo();
//             String factOdrNo = pssr;
//             Integer scannedBoxes = this.stgscanRecService.getScannedBoxes(factNo, factOdrNo);
//             result.setResult(scannedBoxes);
//             result.success("获取成功!");
//         }
//         log.info("结果：" + result);
//         return result;
//     }

     /**
      * 获取订单最后一次入库之存放地点
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "成品入库扫描(卡洛驰)-获取订单最后一次入库之存放地点")
     @ApiOperation(value = "成品入库扫描(卡洛驰)-获取订单最后一次入库之存放地点", notes = "成品入库扫描(卡洛驰)-获取订单最后一次入库之存放地点")
     @GetMapping(value = "/getLastLocArea")
     public Result<String> getLastLocArea(@RequestParam("pssr") String pssr) {
         Result<String> result = new Result<String>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
             String factOdrNo = pssr;
             String lastLocArea = this.stgscanRecService.getLastLocArea(factNo, factOdrNo);
             result.setResult(lastLocArea);
             result.success("获取成功!");
         }
//         log.info("结果：" + result);
         return result;
     }

     /**
      * 插入扫描记录
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "成品入库扫描(卡洛驰)-插入扫描记录")
     @ApiOperation(value = "成品入库扫描(卡洛驰)-插入扫描记录", notes = "成品入库扫描(卡洛驰)-插入扫描记录")
     @GetMapping(value = "/insertScanRecord")
     public Result<Boolean> insertScanRecord(@RequestParam("pssr") String pssr) {
         Result<Boolean> result = new Result<Boolean>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
             String userNo = loginUser.getUsername();
             String[] p = pssr.split(",");
             String recDate = p[0];
             String proFact = p[1];
             String barNo = p[2];
             String recTime = p[3];
             String secNo = p[4];
             String stkNo = p[5];
             String locateNo = p[6];
             String locArea = p[7];
             this.stgscanRecService.insertScanRecord(recDate, userNo, proFact, factNo, barNo,
                                                     recDate, recTime, secNo, stkNo, locateNo, locArea);
             result.setResult(true);
             result.success("插入成功!");
         }
//         log.info("结果：" + result);
         return result;
     }

     /**
      * 产生入库单
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "成品入库扫描(卡洛驰)-产生入库单")
     @ApiOperation(value = "成品入库扫描(卡洛驰)-产生入库单", notes = "成品入库扫描(卡洛驰)-产生入库单")
     @GetMapping(value = "/genInStock")
     public Result<String> genInStock(@RequestParam("pssr") String pssr) {
         Result<String> result = new Result<String>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
             String userNo = loginUser.getUsername();
             Map<String, String> map = new HashMap<String, String>();
             map.put("factNo", factNo);
             map.put("userNo", userNo);
             this.stgscanRecService.genInStock(map);
             String inStock = map.get("oReturn");
             result.setResult(inStock);
             result.success("执行成功!");
         }
         log.info("结果：" + result);
         return result;
     }

     /**
      * 删除扫描记录
      *
      * @param pssr
      * @return
      */
     @AutoLog(value = "成品入库扫描(卡洛驰)-删除扫描记录")
     @ApiOperation(value = "成品入库扫描(卡洛驰)-删除扫描记录", notes = "成品入库扫描(卡洛驰)-删除扫描记录")
     @GetMapping(value = "/deleteScanRecord")
     public Result<Integer> deleteScanRecord(@RequestParam("pssr") String pssr) {
         Result<Integer> result = new Result<Integer>();
         if (pssr == null) {
             result.error500("参数不识别！");
         } else {
             LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
             String factNo = loginUser.getFactNo();
             Integer scanRecord = this.stgscanRecService.deleteScanRecord(factNo, pssr);
             result.setResult(scanRecord);
             result.success("删除扫描记录成功！");
             log.info("删除扫描记录：" + scanRecord);
         }
         log.info("结果：" + result);
         return result;
     }
	
	/**
	  * 分页列表查询
	 * @param stgscanRec
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "成品入库扫描(卡洛驰)-分页列表查询")
	@ApiOperation(value="成品入库扫描(卡洛驰)-分页列表查询", notes="成品入库扫描(卡洛驰)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<StgscanRec>> queryPageList(StgscanRec stgscanRec,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<StgscanRec>> result = new Result<IPage<StgscanRec>>();
		QueryWrapper<StgscanRec> queryWrapper = QueryGenerator.initQueryWrapper(stgscanRec, req.getParameterMap());
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String userNo = loginUser.getUsername();
        queryWrapper.and(wrapper -> wrapper.eq("trim(user_no)", userNo));
        queryWrapper.and(wrapper -> wrapper.eq("gen_in_stock_rmk", "0"));
		Page<StgscanRec> page = new Page<StgscanRec>(pageNo, pageSize);
		IPage<StgscanRec> pageList = stgscanRecService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param stgscanRec
	 * @return
	 */
	@AutoLog(value = "成品入库扫描(卡洛驰)-添加")
	@ApiOperation(value="成品入库扫描(卡洛驰)-添加", notes="成品入库扫描(卡洛驰)-添加")
	@PostMapping(value = "/add")
	public Result<StgscanRec> add(@RequestBody StgscanRec stgscanRec) {
		Result<StgscanRec> result = new Result<StgscanRec>();
		try {
			stgscanRecService.save(stgscanRec);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param stgscanRec
	 * @return
	 */
	@AutoLog(value = "成品入库扫描(卡洛驰)-编辑")
	@ApiOperation(value="成品入库扫描(卡洛驰)-编辑", notes="成品入库扫描(卡洛驰)-编辑")
	@PutMapping(value = "/edit")
	public Result<StgscanRec> edit(@RequestBody StgscanRec stgscanRec) {
		Result<StgscanRec> result = new Result<StgscanRec>();
		StgscanRec stgscanRecEntity = stgscanRecService.getById(stgscanRec.getId());
		if(stgscanRecEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = stgscanRecService.updateById(stgscanRec);
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
	@AutoLog(value = "成品入库扫描(卡洛驰)-通过id删除")
	@ApiOperation(value="成品入库扫描(卡洛驰)-通过id删除", notes="成品入库扫描(卡洛驰)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			stgscanRecService.removeById(id);
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
	@AutoLog(value = "成品入库扫描(卡洛驰)-批量删除")
	@ApiOperation(value="成品入库扫描(卡洛驰)-批量删除", notes="成品入库扫描(卡洛驰)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<StgscanRec> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<StgscanRec> result = new Result<StgscanRec>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.stgscanRecService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "成品入库扫描(卡洛驰)-通过id查询")
	@ApiOperation(value="成品入库扫描(卡洛驰)-通过id查询", notes="成品入库扫描(卡洛驰)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<StgscanRec> queryById(@RequestParam(name="id",required=true) String id) {
		Result<StgscanRec> result = new Result<StgscanRec>();
		StgscanRec stgscanRec = stgscanRecService.getById(id);
		if(stgscanRec==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(stgscanRec);
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
      QueryWrapper<StgscanRec> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              StgscanRec stgscanRec = JSON.parseObject(deString, StgscanRec.class);
              queryWrapper = QueryGenerator.initQueryWrapper(stgscanRec, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<StgscanRec> pageList = stgscanRecService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "成品入库扫描(卡洛驰)列表");
      mv.addObject(NormalExcelConstants.CLASS, StgscanRec.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("成品入库扫描(卡洛驰)列表数据", "导出人:Jeecg", "导出信息"));
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
              List<StgscanRec> listStgscanRecs = ExcelImportUtil.importExcel(file.getInputStream(), StgscanRec.class, params);
              stgscanRecService.saveBatch(listStgscanRecs);
              return Result.ok("文件导入成功！数据行数:" + listStgscanRecs.size());
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
