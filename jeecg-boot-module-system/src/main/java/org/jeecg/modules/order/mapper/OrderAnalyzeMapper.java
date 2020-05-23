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

    public List<Map<String, Object>> getCustRevOdrQty(String factNo, String revOdrDateByToday, String revOdrDateByWeek, String revOdrDateByMonth, String revOdrDateByYear, String dateStart, String dateEnd);
    public List<Map<String, Object>> getBrandRevOdrQty(String factNo, String revOdrDateByToday, String revOdrDateByWeek, String revOdrDateByMonth, String revOdrDateByYear, String dateStart, String dateEnd);
    public List<Map<String, Object>> getStyleRevOdrQty(String factNo, String revOdrDateByToday, String revOdrDateByWeek, String revOdrDateByMonth, String revOdrDateByYear, String dateStart, String dateEnd);
    public List<Map<String, Object>> getDestRevOdrQty(String factNo, String revOdrDateByToday, String revOdrDateByWeek, String revOdrDateByMonth, String revOdrDateByYear, String dateStart, String dateEnd);

}
