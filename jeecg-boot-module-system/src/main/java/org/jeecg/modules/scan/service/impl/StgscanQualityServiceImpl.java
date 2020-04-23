package org.jeecg.modules.scan.service.impl;

import org.jeecg.modules.scan.entity.StgscanQuality;
import org.jeecg.modules.scan.mapper.StgscanQualityMapper;
import org.jeecg.modules.scan.service.IStgscanQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 成型品质扫描
 * @Author: jeecg-boot
 * @Date: 2020-04-03
 * @Version: V1.0
 */
@Service
public class StgscanQualityServiceImpl extends ServiceImpl<StgscanQualityMapper, StgscanQuality> implements IStgscanQualityService {

    @Autowired
    private StgscanQualityMapper stgscanQualityMapper;

    @Override
    public List<String> getBadNoQty(String proFact, String styleNo, String secNo, String recDate) {
        return stgscanQualityMapper.getBadNoQty(proFact, styleNo, secNo, recDate);
    }

    @Override
    public void insertBadNoQty(String proFact, String styleNo, String secNo, String recDate, String badLevel, String badNo, String addValue, String recTime, String userNo) {
        stgscanQualityMapper.insertBadNoQty(proFact, styleNo, secNo, recDate, badLevel, badNo, addValue, recTime, userNo);
    }

    @Override
    public void deleteBadNoQty(String proFact, String styleNo, String secNo, String recDate, String badNo, String userNo) {
        stgscanQualityMapper.deleteBadNoQty(proFact, styleNo, secNo, recDate, badNo, userNo);
    }

}
