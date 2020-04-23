package org.jeecg.modules.scan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.scan.entity.StgscanQuality;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 成型品质扫描
 * @Author: jeecg-boot
 * @Date: 2020-04-03
 * @Version: V1.0
 */
public interface StgscanQualityMapper extends BaseMapper<StgscanQuality> {

    public List<String> getBadNoQty(String proFact, String styleNo, String secNo, String recDate);

    public void insertBadNoQty(String proFact, String styleNo, String secNo, String recDate, String badLevel, String badNo, String addValue, String recTime, String userNo);

    public void deleteBadNoQty(String proFact, String styleNo, String secNo, String recDate, String badNo, String userNo);

}
