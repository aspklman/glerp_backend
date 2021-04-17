package org.jeecg.modules.scan.service;

import org.jeecg.modules.scan.entity.StgscanRec;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 成品入库扫描(卡洛驰)
 * @Author: jeecg-boot
 * @Date:   2021-03-11
 * @Version: V1.0
 */
public interface IStgscanRecService extends IService<StgscanRec> {

    String getLocArea(String factNo, String stkNo, String locateNo, String locArea);

    String getMonthlySettlement(String factNo, String stkNo);

    Map<String, Object> getOrderData(String factNo, String stkNo);

    Integer getRepeatScan(String factNo, String stkNo);

    Map<String, Object> getMoldingData(String factNo, String stkNo);

    String getNoGenOrder(String factNo, String stkNo, String userNo);

    Map<String, Object> getTotalBoxesPairs(String factNo, String factOdrNo);

//    Integer getScannedBoxes(String factNo, String factOdrNo);

    String getLastLocArea(String factNo, String factOdrNo);

    void insertScanRecord(String proDate, String userNo, String proFact, String factNo, String barNo,
                          String recDate, String recTime, String secNo, String stkNo, String locateNo, String locArea);

    Map<String, String> genInStock(Map<String, String> map);

    Integer deleteScanRecord(String factNo, String barNo);
}
