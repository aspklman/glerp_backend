package org.jeecg.modules.order.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.order.entity.OrderAnalyze;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 订单分析
 * @Author: jeecg-boot
 * @Date:   2020-05-14
 * @Version: V1.0
 */
public interface OrderAnalyzeMapper extends BaseMapper<OrderAnalyze> {

    public List<Map<String, Object>> getCustRevByToday(String factNo, String revOdrToday);
    public List<Map<String, Object>> getBrandRevByToday(String factNo, String revOdrToday);
    public List<Map<String, Object>> getStyleRevByToday(String factNo, String revOdrToday);

    public List<Map<String, Object>> getCustRevByWeek(String factNo, String revOdrWeek);
    public List<Map<String, Object>> getBrandRevByWeek(String factNo, String revOdrWeek);
    public List<Map<String, Object>> getStyleRevByWeek(String factNo, String revOdrWeek);

    public List<Map<String, Object>> getCustRevByMonth(String factNo, String revOdrMonth);
    public List<Map<String, Object>> getBrandRevByMonth(String factNo, String revOdrMonth);
    public List<Map<String, Object>> getStyleRevByMonth(String factNo, String revOdrMonth);

    public List<Map<String, Object>> getCustRevByYear(String factNo, String revOdrYear);
    public List<Map<String, Object>> getBrandRevByYear(String factNo, String revOdrYear);
    public List<Map<String, Object>> getStyleRevByYear(String factNo, String revOdrYear);

    public List<Map<String, Object>> getCustRevByPeriod(String factNo, String dateStart, String dateEnd);
    public List<Map<String, Object>> getBrandRevByPeriod(String factNo, String dateStart, String dateEnd);
    public List<Map<String, Object>> getStyleRevByPeriod(String factNo, String dateStart, String dateEnd);

}
