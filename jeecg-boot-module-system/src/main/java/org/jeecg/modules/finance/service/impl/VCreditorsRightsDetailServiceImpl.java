package org.jeecg.modules.finance.service.impl;

import org.jeecg.modules.finance.entity.VCreditorsRightsDetail;
import org.jeecg.modules.finance.mapper.VCreditorsRightsDetailMapper;
import org.jeecg.modules.finance.service.IVCreditorsRightsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 债权明细表
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
@Service
public class VCreditorsRightsDetailServiceImpl extends ServiceImpl<VCreditorsRightsDetailMapper, VCreditorsRightsDetail> implements IVCreditorsRightsDetailService {

    @Autowired
    private VCreditorsRightsDetailMapper vCreditorsRightsDetailMapper;

    @Override
    public void updateSettleOrg(String settleOrg) {
        vCreditorsRightsDetailMapper.updateSettleOrg(settleOrg);
    }

    @Override
    public void updateAnalysisEndDate(String analysisEndDate) {
        vCreditorsRightsDetailMapper.updateAnalysisEndDate(analysisEndDate);
    }

}
