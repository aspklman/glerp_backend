package org.jeecg.modules.quality.service.impl;

import org.jeecg.modules.quality.entity.SampleInspectStyle;
import org.jeecg.modules.quality.mapper.SampleInspectStyleMapper;
import org.jeecg.modules.quality.service.ISampleInspectStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 型体检验项目
 * @Author: jeecg-boot
 * @Date:   2020-12-22
 * @Version: V1.0
 */
@Service
public class SampleInspectStyleServiceImpl extends ServiceImpl<SampleInspectStyleMapper, SampleInspectStyle> implements ISampleInspectStyleService {

    @Autowired
    private SampleInspectStyleMapper sampleInspectStyleMapper;

    @Override
    public List<Map<String, Object>> getCheckPoints(String factNo, String styleShorten, String itemMainNo, String itemMediumNo) {
        return sampleInspectStyleMapper.getCheckPoints(factNo, styleShorten, itemMainNo, itemMediumNo);
    };

    @Override
    public void addCheckPoint(String factNo, String styleShorten, String itemMainNo, String itemMediumNo, String checkPointsNo) {
        sampleInspectStyleMapper.addCheckPoint(factNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo);
    }

    @Override
    public void subtractCheckPoint(String factNo, String styleShorten, String itemMainNo, String itemMediumNo, String checkPointsNo) {
        sampleInspectStyleMapper.subtractCheckPoint(factNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo);
    }

}
