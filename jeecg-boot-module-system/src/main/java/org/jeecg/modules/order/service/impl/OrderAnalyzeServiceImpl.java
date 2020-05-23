package org.jeecg.modules.order.service.impl;

import org.jeecg.modules.order.entity.OrderAnalyze;
import org.jeecg.modules.order.mapper.OrderAnalyzeMapper;
import org.jeecg.modules.order.service.IOrderAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 订单分析
 * @Author: jeecg-boot
 * @Date:   2020-05-14
 * @Version: V1.0
 */
@Service
public class OrderAnalyzeServiceImpl extends ServiceImpl<OrderAnalyzeMapper, OrderAnalyze> implements IOrderAnalyzeService {

    @Autowired
    private OrderAnalyzeMapper orderAnalyzeMapper;

    @Override
    public List<Map<String, Object>> getCustRevOdrQty(String factNo, String revOdrDateByToday, String revOdrDateByWeek, String revOdrDateByMonth, String revOdrDateByYear, String dateStart, String dateEnd) {
        return orderAnalyzeMapper.getCustRevOdrQty(factNo, revOdrDateByToday, revOdrDateByWeek, revOdrDateByMonth, revOdrDateByYear, dateStart, dateEnd);
    }
    @Override
    public List<Map<String, Object>> getBrandRevOdrQty(String factNo, String revOdrDateByToday, String revOdrDateByWeek, String revOdrDateByMonth, String revOdrDateByYear, String dateStart, String dateEnd) {
        return orderAnalyzeMapper.getBrandRevOdrQty(factNo, revOdrDateByToday, revOdrDateByWeek, revOdrDateByMonth, revOdrDateByYear, dateStart, dateEnd);
    }
    @Override
    public List<Map<String, Object>> getStyleRevOdrQty(String factNo, String revOdrDateByToday, String revOdrDateByWeek, String revOdrDateByMonth, String revOdrDateByYear, String dateStart, String dateEnd) {
        return orderAnalyzeMapper.getStyleRevOdrQty(factNo, revOdrDateByToday, revOdrDateByWeek, revOdrDateByMonth, revOdrDateByYear, dateStart, dateEnd);
    }
    @Override
    public List<Map<String, Object>> getDestRevOdrQty(String factNo, String revOdrDateByToday, String revOdrDateByWeek, String revOdrDateByMonth, String revOdrDateByYear, String dateStart, String dateEnd) {
        return orderAnalyzeMapper.getDestRevOdrQty(factNo, revOdrDateByToday, revOdrDateByWeek, revOdrDateByMonth, revOdrDateByYear, dateStart, dateEnd);
    }

}
