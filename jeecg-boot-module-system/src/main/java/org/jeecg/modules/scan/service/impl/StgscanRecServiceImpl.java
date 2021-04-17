package org.jeecg.modules.scan.service.impl;

import org.jeecg.modules.scan.entity.StgscanRec;
import org.jeecg.modules.scan.mapper.StgscanRecMapper;
import org.jeecg.modules.scan.service.IStgscanRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 成品入库扫描(卡洛驰)
 * @Author: jeecg-boot
 * @Date:   2021-03-11
 * @Version: V1.0
 */
@Service
public class StgscanRecServiceImpl extends ServiceImpl<StgscanRecMapper, StgscanRec> implements IStgscanRecService {

    @Autowired
    private StgscanRecMapper stgscanRecMapper;

    @Override
    public String getLocArea(String factNo, String stkNo, String locateNo, String locArea) {
        return stgscanRecMapper.getLocArea(factNo, stkNo, locateNo, locArea);
    }

    @Override
    public String getMonthlySettlement(String factNo, String stkNo) {
        return stgscanRecMapper.getMonthlySettlement(factNo, stkNo);
    }

    @Override
    public Map<String, Object> getOrderData(String factNo, String stkNo) {
        return stgscanRecMapper.getOrderData(factNo, stkNo);
    }

    @Override
    public Integer getRepeatScan(String factNo, String stkNo) {
        return stgscanRecMapper.getRepeatScan(factNo, stkNo);
    }

    @Override
    public Map<String, Object> getMoldingData(String factNo, String stkNo) {
        return stgscanRecMapper.getMoldingData(factNo, stkNo);
    }

    @Override
    public String getNoGenOrder(String factNo, String stkNo, String userNo) {
        return stgscanRecMapper.getNoGenOrder(factNo, stkNo, userNo);
    }

    @Override
    public Map<String, Object> getTotalBoxesPairs(String factNo, String factOdrNo) {
        return stgscanRecMapper.getTotalBoxesPairs(factNo, factOdrNo);
    }

//    @Override
//    public Integer getScannedBoxes(String factNo, String factOdrNo) {
//        return stgscanRecMapper.getScannedBoxes(factNo, factOdrNo);
//    }

    @Override
    public String getLastLocArea(String factNo, String factOdrNo) {
        return stgscanRecMapper.getLastLocArea(factNo, factOdrNo);
    }

    @Override
    public void insertScanRecord(String proDate, String userNo, String proFact, String factNo, String barNo,
                                 String recDate, String recTime, String secNo, String stkNo, String locateNo, String locArea) {
        stgscanRecMapper.insertScanRecord(proDate, userNo, proFact, factNo, barNo,
                                          recDate, recTime, secNo, stkNo, locateNo, locArea);
    }

    @Override
    public Map<String, String> genInStock(Map<String, String> map) {
        return stgscanRecMapper.genInStock(map);
    }

    @Override
    public Integer deleteScanRecord(String factNo, String barNo) {
        return stgscanRecMapper.deleteScanRecord(factNo, barNo);
    }

}
