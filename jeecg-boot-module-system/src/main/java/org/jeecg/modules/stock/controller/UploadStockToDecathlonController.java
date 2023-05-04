package org.jeecg.modules.stock.controller;

//import java.net.http.HttpHeaders;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.stock.entity.UploadStockToDecathlon;
import org.jeecg.modules.stock.service.IUploadStockToDecathlonService;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 上传库存至迪卡侬
 * @Author: jeecg-boot
 * @Date: 2021-08-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "上传库存至迪卡侬")
@RestController
@RequestMapping("/stock/uploadStockToDecathlon")
public class UploadStockToDecathlonController {
    @Autowired
    private IUploadStockToDecathlonService uploadStockToDecathlonService;


    /**
     * 授权 获取第三方的token
     *
     * @throws Exception
     */
	@AutoLog(value = "上传库存至迪卡侬-获取第三方的token")
	@ApiOperation(value = "上传库存至迪卡侬-获取第三方的token", notes = "上传库存至迪卡侬-获取第三方的token")
	@GetMapping(value = "/login")
    public String getHuiCalculatedOrderToken() throws Exception {





		//把第三方账号密码封装到map
        JSONObject paramsJson = new JSONObject();
        JSONObject headerJson = new JSONObject();
        JSONObject bodyJson = new JSONObject();
        headerJson.put("Authorization", "Basic Q2IzNTFjODRlZmMxZjU1MGU1MmI0YjE2NjczZmI5NzZiMGNiODYyMGI6U1ptVFRUZjZHWHNrdDkwb0xlc3dIUTBEZ282MWtaZXVHREY1ZzhXZ1BvY28xYW5TQkJJMG53R3h5bFJIekdkaA==");
//        bodyJson.put("grant_type", "client_credentials");

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Basic Q2IzNTFjODRlZmMxZjU1MGU1MmI0YjE2NjczZmI5NzZiMGNiODYyMGI6U1ptVFRUZjZHWHNrdDkwb0xlc3dIUTBEZ282MWtaZXVHREY1ZzhXZ1BvY28xYW5TQkJJMG53R3h5bFJIekdkaA==");
//        bodyJson.put("Content-Type", "application/x-www-form-urlencoded");
        headerJson.put("Content-Type", "application/x-www-form-urlencoded");
//        bodyJson.put("grant_type", "client_credentials");
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        bodyJson.put("grant_type", "client_credentials");
        paramsJson.put("Body", bodyJson);
        paramsJson.put("Header", headerJson);
//        paramsJson.put("Body", bodyJson);
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("grant_type", "client_credentials");
//        paramsMap.put("Headers", "Basic Q2IzNTFjODRlZmMxZjU1MGU1MmI0YjE2NjczZmI5NzZiMGNiODYyMGI6U1ptVFRUZjZHWHNrdDkwb0xlc3dIUTBEZ282MWtaZXVHREY1ZzhXZ1BvY28xYW5TQkJJMG53R3h5bFJIekdkaA==");
        String tokenStr = "";
        try {
//            tokenStr = HttpUtil.post("https://idpdecathlon.oxylane.com/as/token.oauth2", paramsMap);
            tokenStr = HttpUtil.post("https://idpdecathlon.oxylane.com/as/token.oauth2", paramsJson);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("第三方接口访问异常");
        }
        //用fastJson 解析一波  直接返回
//        JSONObject object = JSON.parseObject(tokenStr);
//        System.out.println(JSON.toJSONString(object));
//        String tokenObj = object.getString("token");
//        System.out.println(tokenObj);
//        JSONObject token = JSON.parseObject(tokenObj);
//        System.out.println("token-->" + token.getString("token"));
//        return token.getString("token");
        System.out.println("token-->" + tokenStr);
//        System.out.println("paramsJson-->" + paramsMap);
        System.out.println("paramsJson-->" + paramsJson);

        return tokenStr;
    }

//    /**
//     * 调用第三方接口，下单
//     * 说明下： ApiReturnObject    ApiReturnUtil 是我们自己项目封装的返回 无需理会
//     */
//    @PostMapping("/order")
//    public ApiReturnObject getgeneOrder(HuiCalculatedOrder huiCalculatedOrder) {
//		//把需要传的参数封装到map
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("orderNum", huiCalculatedOrder.getOrderNum());
//        paramsMap.put("checkNum", huiCalculatedOrder.getCheckNum());
//        String post = null;
//        try {
//            //因为第三方规定：header  需要传 token ， 所以用下面的链式构建
//            //post = HttpUtil.post(myProps.getHuiCalculatedOrderUrl() + "/gene/order/create", paramsMap);
//
//            //链式构建请求，用的是hutool 工具类
//            post = HttpRequest.post(myProps.getHuiCalculatedOrderUrl() + "/gene/order/create")
//                    .header("token", getHuiCalculatedOrderToken())  //value  是 上面获取的token
//                    .form(paramsMap).timeout(20000).execute().body();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ApiReturnUtil.error("第三方接口错误", e.getMessage());
//        }
//        log.info(post);
//        JSONObject jsonbody = JSON.parseObject(post);   //使用了fastJson 工具类
//        if ("00".equals(jsonbody.getString("errorCode"))) {
//
//            return ApiReturnUtil.success("下单成功", post);
//        } else {
//            return ApiReturnUtil.error(jsonbody.getString("errorMessage"));
//        }
//
//    }


    /**
     * 分页列表查询
     *
     * @param uploadStockToDecathlon
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "上传库存至迪卡侬-分页列表查询")
    @ApiOperation(value = "上传库存至迪卡侬-分页列表查询", notes = "上传库存至迪卡侬-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<UploadStockToDecathlon>> queryPageList(UploadStockToDecathlon uploadStockToDecathlon,
                                                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                               HttpServletRequest req) {
        Result<IPage<UploadStockToDecathlon>> result = new Result<IPage<UploadStockToDecathlon>>();
        QueryWrapper<UploadStockToDecathlon> queryWrapper = QueryGenerator.initQueryWrapper(uploadStockToDecathlon, req.getParameterMap());
        Page<UploadStockToDecathlon> page = new Page<UploadStockToDecathlon>(pageNo, pageSize);
        IPage<UploadStockToDecathlon> pageList = uploadStockToDecathlonService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加
     *
     * @param uploadStockToDecathlon
     * @return
     */
    @AutoLog(value = "上传库存至迪卡侬-添加")
    @ApiOperation(value = "上传库存至迪卡侬-添加", notes = "上传库存至迪卡侬-添加")
    @PostMapping(value = "/add")
    public Result<UploadStockToDecathlon> add(@RequestBody UploadStockToDecathlon uploadStockToDecathlon) {
        Result<UploadStockToDecathlon> result = new Result<UploadStockToDecathlon>();
        try {
            uploadStockToDecathlonService.save(uploadStockToDecathlon);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 编辑
     *
     * @param uploadStockToDecathlon
     * @return
     */
    @AutoLog(value = "上传库存至迪卡侬-编辑")
    @ApiOperation(value = "上传库存至迪卡侬-编辑", notes = "上传库存至迪卡侬-编辑")
    @PutMapping(value = "/edit")
    public Result<UploadStockToDecathlon> edit(@RequestBody UploadStockToDecathlon uploadStockToDecathlon) {
        Result<UploadStockToDecathlon> result = new Result<UploadStockToDecathlon>();
        UploadStockToDecathlon uploadStockToDecathlonEntity = uploadStockToDecathlonService.getById(uploadStockToDecathlon.getId());
        if (uploadStockToDecathlonEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = uploadStockToDecathlonService.updateById(uploadStockToDecathlon);
            //TODO 返回false说明什么？
            if (ok) {
                result.success("修改成功!");
            }
        }

        return result;
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "上传库存至迪卡侬-通过id删除")
    @ApiOperation(value = "上传库存至迪卡侬-通过id删除", notes = "上传库存至迪卡侬-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            uploadStockToDecathlonService.removeById(id);
        } catch (Exception e) {
            log.error("删除失败", e.getMessage());
            return Result.error("删除失败!");
        }
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "上传库存至迪卡侬-批量删除")
    @ApiOperation(value = "上传库存至迪卡侬-批量删除", notes = "上传库存至迪卡侬-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<UploadStockToDecathlon> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<UploadStockToDecathlon> result = new Result<UploadStockToDecathlon>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.uploadStockToDecathlonService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "上传库存至迪卡侬-通过id查询")
    @ApiOperation(value = "上传库存至迪卡侬-通过id查询", notes = "上传库存至迪卡侬-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<UploadStockToDecathlon> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<UploadStockToDecathlon> result = new Result<UploadStockToDecathlon>();
        UploadStockToDecathlon uploadStockToDecathlon = uploadStockToDecathlonService.getById(id);
        if (uploadStockToDecathlon == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(uploadStockToDecathlon);
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
        QueryWrapper<UploadStockToDecathlon> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                UploadStockToDecathlon uploadStockToDecathlon = JSON.parseObject(deString, UploadStockToDecathlon.class);
                queryWrapper = QueryGenerator.initQueryWrapper(uploadStockToDecathlon, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<UploadStockToDecathlon> pageList = uploadStockToDecathlonService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "上传库存至迪卡侬列表");
        mv.addObject(NormalExcelConstants.CLASS, UploadStockToDecathlon.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("上传库存至迪卡侬列表数据", "导出人:Jeecg", "导出信息"));
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
                List<UploadStockToDecathlon> listUploadStockToDecathlons = ExcelImportUtil.importExcel(file.getInputStream(), UploadStockToDecathlon.class, params);
                uploadStockToDecathlonService.saveBatch(listUploadStockToDecathlons);
                return Result.ok("文件导入成功！数据行数:" + listUploadStockToDecathlons.size());
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
