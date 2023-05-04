package org.jeecg.modules.order.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.quality.entity.SampleInspectStd;
import org.jeecg.modules.order.mapper.SampleInspectStdMapper;
import org.jeecg.modules.order.service.ISampleInspectStdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 验货抽检标准
 * @Author: jeecg-boot
 * @Date:   2020-08-07
 * @Version: V1.0
 */
@Service
public class SampleInspectStdServiceImpl extends ServiceImpl<SampleInspectStdMapper, SampleInspectStd> implements ISampleInspectStdService {

    @Autowired
    private SampleInspectStdMapper sampleInspectStdMapper;

    @Override
    public List<Map<String, Object>> getInspectDetail(String factNo, String factOdrNo) {
        return sampleInspectStdMapper.getInspectDetail(factNo, factOdrNo);
    }

}
