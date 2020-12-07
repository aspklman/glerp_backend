package org.jeecg.modules.order.service;

import org.jeecg.modules.order.entity.WholeProcessReport;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 全流程报表
 * @Author: jeecg-boot
 * @Date:   2020-07-31
 * @Version: V1.0
 */
public interface IWholeProcessReportService extends IService<WholeProcessReport> {

    public List<Map<String, Object>> getSizeQty(String factNo, String factOdrNo);

    public List<Map<String, Object>> getLocArea(String factNo, String factOdrNo);

    public Map<String, Object> getStockData(String factNo, String factOdrNo);

    public Map<String, Object> getInspectReport(String factNo, String custOdrNo);

}
