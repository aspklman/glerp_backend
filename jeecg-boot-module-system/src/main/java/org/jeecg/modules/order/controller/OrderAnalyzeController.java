package org.jeecg.modules.order.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.order.entity.OrderAnalyze;
import org.jeecg.modules.order.service.IOrderAnalyzeService;

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
 * @Description: 订单分析
 * @Author: jeecg-boot
 * @Date: 2020-05-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "订单分析")
@RestController
@RequestMapping("/order/orderAnalyze")
public class OrderAnalyzeController {
    @Autowired
    private IOrderAnalyzeService orderAnalyzeService;

    /**
     * 今日客户接单数量
     *
     * @param
     * @return
     */
    @AutoLog(value = "订单分析-今日客户接单数量")
    @ApiOperation(value = "订单分析-今日客户接单数量", notes = "订单分析-今日客户接单数量")
    @GetMapping(value = "/getCustRevByToday")
    public Result<List<Map<String, Object>>> getCustRevByToday() {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String factNo = loginUser.getFactNo();
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String revOdrToday = dateFormat.format(currentDate);
            log.info("当天日期" + revOdrToday);
            List<Map<String, Object>> orderAnalyze = this.orderAnalyzeService.getCustRevByToday(factNo, revOdrToday);
            log.info("今日订单数:" + orderAnalyze);
            result.setResult(orderAnalyze);
            result.success("查询成功！");
        } catch (Exception e) {
            result.error500("查询失败！ " + e);
        }
        return result;
    }

    /**
     * 本周客户接单数量
     *
     * @param
     * @return
     */
    @AutoLog(value = "订单分析-本周客户接单数量")
    @ApiOperation(value = "订单分析-本周客户接单数量", notes = "订单分析-本周客户接单数量")
    @GetMapping(value = "/getCustRevByWeek")
    public Result<List<Map<String, Object>>> getCustRevByWeek() {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String factNo = loginUser.getFactNo();
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String revOdrWeek = dateFormat.format(currentDate);
            log.info("本周当天日期" + revOdrWeek);
            List<Map<String, Object>> orderAnalyze = this.orderAnalyzeService.getCustRevByWeek(factNo, revOdrWeek);
            log.info("本周订单数:" + orderAnalyze);
            result.setResult(orderAnalyze);
            result.success("查询成功！");
        } catch (Exception e) {
            result.error500("查询失败！ " + e);
        }
        return result;
    }

    /**
     * 本月客户接单数量
     *
     * @param
     * @return
     */
    @AutoLog(value = "订单分析-本月客户接单数量")
    @ApiOperation(value = "订单分析-本月客户接单数量", notes = "订单分析-本月客户接单数量")
    @GetMapping(value = "/getCustRevByMonth")
    public Result<List<Map<String, Object>>> getCustRevByMonth() {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
//         if (params == null) {
//             result.error500("参数不识别！");
//         } else {
//			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//			 String factNo = loginUser.getFactNo();
//             JSONObject jsonObject = JSONObject.parseObject(params);
//             String revOdrMonth = jsonObject.getString("revOdrMonth");
//			 String stockYymm = jsonObject.getString("stockYymm");
//			 String factOdrNo = jsonObject.getString("factOdrNo");
//			 String locNo = jsonObject.getString("locNo");
//			 String cpartNo = jsonObject.getString("cpartNo");
//			 String locArea = jsonObject.getString("locArea");
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String factNo = loginUser.getFactNo();
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
            String revOdrMonth = dateFormat.format(currentDate);
            List<Map<String, Object>> custOdrQtyMap = this.orderAnalyzeService.getCustRevByMonth(factNo, revOdrMonth);
            log.info("本月订单数:" + custOdrQtyMap);
            result.setResult(custOdrQtyMap);
            result.success("查询成功！");
        } catch (Exception e) {
            result.error500("查询失败！ " + e);
        }
//         }
        return result;
    }

    /**
     * 本年客户接单数量
     *
     * @param
     * @return
     */
    @AutoLog(value = "订单分析-本年客户接单数量")
    @ApiOperation(value = "订单分析-本年客户接单数量", notes = "订单分析-本年客户接单数量")
    @GetMapping(value = "/getCustRevByYear")
    public Result<List<Map<String, Object>>> getCustRevByYear() {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String factNo = loginUser.getFactNo();
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            String revOdrYear = dateFormat.format(currentDate);
            List<Map<String, Object>> orderAnalyze = this.orderAnalyzeService.getCustRevByYear(factNo, revOdrYear);
            log.info("本年订单数:" + orderAnalyze);
            result.setResult(orderAnalyze);
            result.success("查询成功！");
        } catch (Exception e) {
            result.error500("查询失败！ " + e);
        }
        return result;
    }

    /**
     * 用户所选日期期间客户接单数量
     *
     * @param period
     * @return
     */
    @AutoLog(value = "订单分析-用户所选日期期间客户接单数量")
    @ApiOperation(value = "订单分析-用户所选日期期间客户接单数量", notes = "订单分析-用户所选日期期间客户接单数量")
    @GetMapping(value = "/getCustRevPeriodOdrQty")
    public Result<List<Map<String, Object>>> getCustRevPeriodOdrQty(@RequestParam("period") String period) {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
        if (period == null) {
            result.error500("参数不识别！");
        } else {
            try {
                LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                String factNo = loginUser.getFactNo();
                String periodArray[] = period.split(",");
                String dateStart = periodArray[0];
                String dateEnd = periodArray[1];
                log.info("用户所选日期期间订单数:" + dateStart + '/' + dateEnd);
                List<Map<String, Object>> orderAnalyze = this.orderAnalyzeService.getCustRevByPeriod(factNo, dateStart, dateEnd);
                log.info("用户所选日期期间订单数:" + orderAnalyze);
                result.setResult(orderAnalyze);
                result.success("查询成功！");
            } catch (Exception e) {
                result.error500("查询失败！ " + e);
            }
        }
        return result;
    }

//	/**
//	  * 分页列表查询
//	 * @param orderAnalyze
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "订单分析-分页列表查询")
//	@ApiOperation(value="订单分析-分页列表查询", notes="订单分析-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<IPage<OrderAnalyze>> queryPageList(OrderAnalyze orderAnalyze,
//									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//									  HttpServletRequest req) {
//		Result<IPage<OrderAnalyze>> result = new Result<IPage<OrderAnalyze>>();
//		QueryWrapper<OrderAnalyze> queryWrapper = QueryGenerator.initQueryWrapper(orderAnalyze, req.getParameterMap());
//		Page<OrderAnalyze> page = new Page<OrderAnalyze>(pageNo, pageSize);
//		IPage<OrderAnalyze> pageList = orderAnalyzeService.page(page, queryWrapper);
//		result.setSuccess(true);
//		result.setResult(pageList);
//		return result;
//	}

//	/**
//	  *   添加
//	 * @param orderAnalyze
//	 * @return
//	 */
//	@AutoLog(value = "订单分析-添加")
//	@ApiOperation(value="订单分析-添加", notes="订单分析-添加")
//	@PostMapping(value = "/add")
//	public Result<OrderAnalyze> add(@RequestBody OrderAnalyze orderAnalyze) {
//		Result<OrderAnalyze> result = new Result<OrderAnalyze>();
//		try {
//			orderAnalyzeService.save(orderAnalyze);
//			result.success("添加成功！");
//		} catch (Exception e) {
//			log.error(e.getMessage(),e);
//			result.error500("操作失败");
//		}
//		return result;
//	}
//
//	/**
//	  *  编辑
//	 * @param orderAnalyze
//	 * @return
//	 */
//	@AutoLog(value = "订单分析-编辑")
//	@ApiOperation(value="订单分析-编辑", notes="订单分析-编辑")
//	@PutMapping(value = "/edit")
//	public Result<OrderAnalyze> edit(@RequestBody OrderAnalyze orderAnalyze) {
//		Result<OrderAnalyze> result = new Result<OrderAnalyze>();
//		OrderAnalyze orderAnalyzeEntity = orderAnalyzeService.getById(orderAnalyze.getId());
//		if(orderAnalyzeEntity==null) {
//			result.error500("未找到对应实体");
//		}else {
//			boolean ok = orderAnalyzeService.updateById(orderAnalyze);
//			//TODO 返回false说明什么？
//			if(ok) {
//				result.success("修改成功!");
//			}
//		}
//
//		return result;
//	}
//
//	/**
//	  *   通过id删除
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "订单分析-通过id删除")
//	@ApiOperation(value="订单分析-通过id删除", notes="订单分析-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		try {
//			orderAnalyzeService.removeById(id);
//		} catch (Exception e) {
//			log.error("删除失败",e.getMessage());
//			return Result.error("删除失败!");
//		}
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	  *  批量删除
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "订单分析-批量删除")
//	@ApiOperation(value="订单分析-批量删除", notes="订单分析-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<OrderAnalyze> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		Result<OrderAnalyze> result = new Result<OrderAnalyze>();
//		if(ids==null || "".equals(ids.trim())) {
//			result.error500("参数不识别！");
//		}else {
//			this.orderAnalyzeService.removeByIds(Arrays.asList(ids.split(",")));
//			result.success("删除成功!");
//		}
//		return result;
//	}
//
//	/**
//	  * 通过id查询
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "订单分析-通过id查询")
//	@ApiOperation(value="订单分析-通过id查询", notes="订单分析-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<OrderAnalyze> queryById(@RequestParam(name="id",required=true) String id) {
//		Result<OrderAnalyze> result = new Result<OrderAnalyze>();
//		OrderAnalyze orderAnalyze = orderAnalyzeService.getById(id);
//		if(orderAnalyze==null) {
//			result.error500("未找到对应实体");
//		}else {
//			result.setResult(orderAnalyze);
//			result.setSuccess(true);
//		}
//		return result;
//	}

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<OrderAnalyze> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                OrderAnalyze orderAnalyze = JSON.parseObject(deString, OrderAnalyze.class);
                queryWrapper = QueryGenerator.initQueryWrapper(orderAnalyze, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<OrderAnalyze> pageList = orderAnalyzeService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "订单分析列表");
        mv.addObject(NormalExcelConstants.CLASS, OrderAnalyze.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("订单分析列表数据", "导出人:Jeecg", "导出信息"));
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
                List<OrderAnalyze> listOrderAnalyzes = ExcelImportUtil.importExcel(file.getInputStream(), OrderAnalyze.class, params);
                orderAnalyzeService.saveBatch(listOrderAnalyzes);
                return Result.ok("文件导入成功！数据行数:" + listOrderAnalyzes.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
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
