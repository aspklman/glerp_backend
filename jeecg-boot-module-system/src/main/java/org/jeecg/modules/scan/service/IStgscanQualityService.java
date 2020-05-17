package org.jeecg.modules.scan.service;

import org.jeecg.modules.scan.entity.StgscanQuality;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 成型品质扫描
 * @Author: jeecg-boot
 * @Date: 2020-04-03
 * @Version: V1.0
 */
public interface IStgscanQualityService extends IService<StgscanQuality> {

    public List<String> getBadNoQty(String proFact, String styleNo, String secNo, String recDate);

    public List<String> getBadNoQtyVn(String proFact, String styleNo, String secNo, String recDate);

    public void insertBadNoQty(String factNo, String proFact, String styleNo, String secNo, String recDate, String badLevel, String badNo, String addValue, String recTime, String userNo);

    public void insertBadNoQtyVn(String factNo, String proFact, String styleNo, String secNo, String recDate, String badLevel, String badNo, String addValue, String recTime, String userNo);

    public void deleteBadNoQty(String factNo, String proFact, String styleNo, String secNo, String recDate, String badNo, String userNo);

    public void deleteBadNoQtyVn(String factNo, String proFact, String styleNo, String secNo, String recDate, String badNo, String userNo);

}
