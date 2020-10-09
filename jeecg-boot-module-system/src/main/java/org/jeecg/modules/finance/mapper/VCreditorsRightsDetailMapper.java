package org.jeecg.modules.finance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.finance.entity.VCreditorsRightsDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 债权明细表
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
public interface VCreditorsRightsDetailMapper extends BaseMapper<VCreditorsRightsDetail> {

    public void updateSettleOrg(String settleOrg);

    public void updateAnalysisEndDate(String analysisEndDate);

}
