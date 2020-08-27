package org.jeecg.modules.order.service.impl;

import org.jeecg.modules.order.entity.WholeProcessReport;
import org.jeecg.modules.order.mapper.WholeProcessReportMapper;
import org.jeecg.modules.order.service.IWholeProcessReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 全流程报表
 * @Author: jeecg-boot
 * @Date:   2020-07-31
 * @Version: V1.0
 */
@Service
public class WholeProcessReportServiceImpl extends ServiceImpl<WholeProcessReportMapper, WholeProcessReport> implements IWholeProcessReportService {

    @Autowired
    private WholeProcessReportMapper wholeProcessReportMapper;

    @Override
    public List<Map<String, Object>> getSizeQty(String factNo, String factOdrNo) {
        return wholeProcessReportMapper.getSizeQty(factNo, factOdrNo);
    }

    @Override
    public Map<String, Object> getStockData(String factNo, String factOdrNo) {
        return wholeProcessReportMapper.getStockData(factNo, factOdrNo);
    }

}
