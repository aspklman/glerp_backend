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
     * 客户_接单日期_数量
     *
     * @param
     * @return
     */
    @AutoLog(value = "订单分析-客户_接单日期_数量")
    @ApiOperation(value = "订单分析-客户_接单日期_数量", notes = "订单分析-客户_接单日期_数量")
    @GetMapping(value = "/getCustRevOdrQty")
    public Result<List<Map<String, Object>>> getCustRevOdrQty(@RequestParam("datekind") String datekind) {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String factNo = loginUser.getFactNo();
            JSONObject jsonObject = JSONObject.parseObject(datekind);
            String dateKindName = jsonObject.getString("name");
            String revOdrDateByToday = "";
            String revOdrDateByWeek = "";
            String revOdrDateByMonth = "";
            String revOdrDateByYear = "";
            String dateStart = "";
            String dateEnd = "";
            Date currentDate = new Date();
            if (dateKindName.equals("today")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                revOdrDateByToday = dateFormat.format(currentDate);
            } else if (dateKindName.equals("week")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                revOdrDateByWeek = dateFormat.format(currentDate);
            } else if (dateKindName.equals("month")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
                revOdrDateByMonth = dateFormat.format(currentDate);
            } else if (dateKindName.equals("year")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                revOdrDateByYear = dateFormat.format(currentDate);
            } else {
                String periodArray[] = dateKindName.split(",");
                dateStart = periodArray[0];
                dateEnd = periodArray[1];
            }
            log.info("tabKind参数3：" + dateKindName + '/' + revOdrDateByToday + "/" + revOdrDateByWeek + '/'  + revOdrDateByMonth + "/" + revOdrDateByYear + '/'  + dateStart + "/" + dateEnd);
            List<Map<String, Object>> custOdrQtyMap = this.orderAnalyzeService.getCustRevOdrQty(factNo, revOdrDateByToday, revOdrDateByWeek, revOdrDateByMonth, revOdrDateByYear, dateStart, dateEnd);
            log.info("订单数:" + custOdrQtyMap);
            result.setResult(custOdrQtyMap);
            result.success("查询成功！");
        } catch (Exception e) {
            result.error500("查询失败！ " + e);
        }
        return result;
    }

    /**
     * 品牌_接单日期_数量
     *
     * @param
     * @return
     */
    @AutoLog(value = "订单分析-品牌_接单日期_数量")
    @ApiOperation(value = "订单分析-品牌_接单日期_数量", notes = "订单分析-品牌_接单日期_数量")
    @GetMapping(value = "/getBrandRevOdrQty")
    public Result<List<Map<String, Object>>> getBrandRevOdrQty(@RequestParam("datekind") String datekind) {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String factNo = loginUser.getFactNo();
            JSONObject jsonObject = JSONObject.parseObject(datekind);
            String dateKindName = jsonObject.getString("name");
            String revOdrDateByToday = "";
            String revOdrDateByWeek = "";
            String revOdrDateByMonth = "";
            String revOdrDateByYear = "";
            String dateStart = "";
            String dateEnd = "";
            Date currentDate = new Date();
            if (dateKindName.equals("today")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                revOdrDateByToday = dateFormat.format(currentDate);
            } else if (dateKindName.equals("week")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                revOdrDateByWeek = dateFormat.format(currentDate);
            } else if (dateKindName.equals("month")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
                revOdrDateByMonth = dateFormat.format(currentDate);
            } else if (dateKindName.equals("year")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                revOdrDateByYear = dateFormat.format(currentDate);
            } else {
                String periodArray[] = dateKindName.split(",");
                dateStart = periodArray[0];
                dateEnd = periodArray[1];
            }
            log.info("tabKind参数3：" + dateKindName + '/' + revOdrDateByToday + "/" + revOdrDateByWeek + '/'  + revOdrDateByMonth + "/" + revOdrDateByYear + '/'  + dateStart + "/" + dateEnd);
            List<Map<String, Object>> custOdrQtyMap = this.orderAnalyzeService.getBrandRevOdrQty(factNo, revOdrDateByToday, revOdrDateByWeek, revOdrDateByMonth, revOdrDateByYear, dateStart, dateEnd);
            log.info("订单数:" + custOdrQtyMap);
            result.setResult(custOdrQtyMap);
            result.success("查询成功！");
        } catch (Exception e) {
            result.error500("查询失败！ " + e);
        }
        return result;
    }

    /**
     * 型体_接单日期_数量
     *
     * @param
     * @return
     */
    @AutoLog(value = "订单分析-型体_接单日期_数量")
    @ApiOperation(value = "订单分析-型体_接单日期_数量", notes = "订单分析-型体_接单日期_数量")
    @GetMapping(value = "/getStyleRevOdrQty")
    public Result<List<Map<String, Object>>> getStyleRevOdrQty(@RequestParam("datekind") String datekind) {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String factNo = loginUser.getFactNo();
            JSONObject jsonObject = JSONObject.parseObject(datekind);
            String dateKindName = jsonObject.getString("name");
            String revOdrDateByToday = "";
            String revOdrDateByWeek = "";
            String revOdrDateByMonth = "";
            String revOdrDateByYear = "";
            String dateStart = "";
            String dateEnd = "";
            Date currentDate = new Date();
            if (dateKindName.equals("today")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                revOdrDateByToday = dateFormat.format(currentDate);
            } else if (dateKindName.equals("week")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                revOdrDateByWeek = dateFormat.format(currentDate);
            } else if (dateKindName.equals("month")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
                revOdrDateByMonth = dateFormat.format(currentDate);
            } else if (dateKindName.equals("year")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                revOdrDateByYear = dateFormat.format(currentDate);
            } else {
                String periodArray[] = dateKindName.split(",");
                dateStart = periodArray[0];
                dateEnd = periodArray[1];
            }
            log.info("tabKind参数3：" + dateKindName + '/' + revOdrDateByToday + "/" + revOdrDateByWeek + '/'  + revOdrDateByMonth + "/" + revOdrDateByYear + '/'  + dateStart + "/" + dateEnd);
            List<Map<String, Object>> custOdrQtyMap = this.orderAnalyzeService.getStyleRevOdrQty(factNo, revOdrDateByToday, revOdrDateByWeek, revOdrDateByMonth, revOdrDateByYear, dateStart, dateEnd);
            log.info("订单数:" + custOdrQtyMap);
            result.setResult(custOdrQtyMap);
            result.success("查询成功！");
        } catch (Exception e) {
            result.error500("查询失败！ " + e);
        }
        return result;
    }

    /**
     * 目的地_接单日期_数量
     *
     * @param
     * @return
     */
    @AutoLog(value = "订单分析-目的地_接单日期_数量")
    @ApiOperation(value = "订单分析-目的地_接单日期_数量", notes = "订单分析-目的地_接单日期_数量")
    @GetMapping(value = "/getDestRevOdrQty")
    public Result<List<Map<String, Object>>> getDestRevOdrQty(@RequestParam("datekind") String datekind) {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String factNo = loginUser.getFactNo();
            JSONObject jsonObject = JSONObject.parseObject(datekind);
            String dateKindName = jsonObject.getString("name");
            String revOdrDateByToday = "";
            String revOdrDateByWeek = "";
            String revOdrDateByMonth = "";
            String revOdrDateByYear = "";
            String dateStart = "";
            String dateEnd = "";
            Date currentDate = new Date();
            if (dateKindName.equals("today")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                revOdrDateByToday = dateFormat.format(currentDate);
            } else if (dateKindName.equals("week")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                revOdrDateByWeek = dateFormat.format(currentDate);
            } else if (dateKindName.equals("month")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
                revOdrDateByMonth = dateFormat.format(currentDate);
            } else if (dateKindName.equals("year")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                revOdrDateByYear = dateFormat.format(currentDate);
            } else {
                String periodArray[] = dateKindName.split(",");
                dateStart = periodArray[0];
                dateEnd = periodArray[1];
            }
            log.info("tabKind参数3：" + dateKindName + '/' + revOdrDateByToday + "/" + revOdrDateByWeek + '/'  + revOdrDateByMonth + "/" + revOdrDateByYear + '/'  + dateStart + "/" + dateEnd);
            List<Map<String, Object>> custOdrQtyMap = this.orderAnalyzeService.getDestRevOdrQty(factNo, revOdrDateByToday, revOdrDateByWeek, revOdrDateByMonth, revOdrDateByYear, dateStart, dateEnd);
            log.info("订单数:" + custOdrQtyMap);
            result.setResult(custOdrQtyMap);
            result.success("查询成功！");
        } catch (Exception e) {
            result.error500("查询失败！ " + e);
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
