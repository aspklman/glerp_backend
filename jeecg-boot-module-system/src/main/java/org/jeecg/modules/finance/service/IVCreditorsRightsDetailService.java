package org.jeecg.modules.finance.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.finance.entity.VCreditorsRightsDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 债权明细表
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
@DS("slave_1")
public interface IVCreditorsRightsDetailService extends IService<VCreditorsRightsDetail> {

    public void updateSettleOrg(String settleOrg);

    public void updateAnalysisEndDate(String analysisEndDate);

}
