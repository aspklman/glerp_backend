package org.jeecg.modules.order.service;

import org.jeecg.modules.order.entity.OrderAnalyze;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 订单分析
 * @Author: jeecg-boot
 * @Date:   2020-05-14
 * @Version: V1.0
 */
public interface IOrderAnalyzeService extends IService<OrderAnalyze> {

    public List<Map<String, Object>> getCustRevByToday(String factNo, String revOdrToday);

    public List<Map<String, Object>> getCustRevByWeek(String factNo, String revOdrWeek);

    public List<Map<String, Object>> getCustRevByMonth(String factNo, String revOdrMonth);

    public List<Map<String, Object>> getCustRevByYear(String factNo, String revOdrYear);

    public List<Map<String, Object>> getCustRevByPeriod(String factNo, String dateStart, String dateEnd);

}
