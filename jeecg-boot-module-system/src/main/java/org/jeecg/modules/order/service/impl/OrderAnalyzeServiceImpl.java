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
    public List<Map<String, Object>> getCustRevByToday(String factNo, String revOdrToday) {
        return orderAnalyzeMapper.getCustRevByToday(factNo, revOdrToday);
    }

    @Override
    public List<Map<String, Object>> getCustRevByWeek(String factNo, String revOdrWeek) {
        return orderAnalyzeMapper.getCustRevByWeek(factNo, revOdrWeek);
    }

    @Override
    public List<Map<String, Object>> getCustRevByMonth(String factNo, String revOdrMonth) {
        return orderAnalyzeMapper.getCustRevByMonth(factNo, revOdrMonth);
    }

    @Override
    public List<Map<String, Object>> getCustRevByYear(String factNo, String revOdrYear) {
        return orderAnalyzeMapper.getCustRevByYear(factNo, revOdrYear);
    }

    @Override
    public List<Map<String, Object>> getCustRevByPeriod(String factNo, String dateStart, String dateEnd) {
        return orderAnalyzeMapper.getCustRevByPeriod(factNo, dateStart, dateEnd);
    }

}
