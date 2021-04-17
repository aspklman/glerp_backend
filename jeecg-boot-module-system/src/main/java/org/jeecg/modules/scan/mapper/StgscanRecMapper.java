package org.jeecg.modules.scan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.scan.entity.StgscanRec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 成品入库扫描(卡洛驰)
 * @Author: jeecg-boot
 * @Date:   2021-03-11
 * @Version: V1.0
 */
public interface StgscanRecMapper extends BaseMapper<StgscanRec> {

    String getLocArea(String factNo, String stkNo, String locateNo, String locArea);

    String getMonthlySettlement(String factNo, String stkNo);

    Map<String, Object> getOrderData(String factNo, String outboxNo);

    Integer getRepeatScan(String factNo, String outboxNo);

    Map<String, Object> getMoldingData(String factNo, String outboxNo);

    String getNoGenOrder(String factNo, String stkNo, String userNo);

    Map<String, Object> getTotalBoxesPairs(String factNo, String factOdrNo);

//    Integer getScannedBoxes(String factNo, String factOdrNo);

    String getLastLocArea(String factNo, String factOdrNo);

    void insertScanRecord(String proDate, String userNo, String proFact, String factNo, String barNo,
                          String recDate, String recTime, String secNo, String stkNo, String locateNo, String locArea);

    Map<String, String> genInStock(Map<String, String> map);

    Integer deleteScanRecord(String factNo, String barNo);

}
