package org.jeecg.modules.finance.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.finance.entity.VRecAgingAnalysis;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 应收账龄分析表
 * @Author: jeecg-boot
 * @Date:   2020-08-31
 * @Version: V1.0
 */
@DS("slave_1")
public interface IVRecAgingAnalysisService extends IService<VRecAgingAnalysis> {

}
