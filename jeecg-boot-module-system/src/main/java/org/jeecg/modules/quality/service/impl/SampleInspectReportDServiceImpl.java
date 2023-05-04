package org.jeecg.modules.quality.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.quality.entity.SampleInspectReportD;
import org.jeecg.modules.quality.mapper.SampleInspectReportDMapper;
import org.jeecg.modules.quality.service.ISampleInspectReportDService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 验货报告子表
 * @Author: jeecg-boot
 * @Date:   2020-08-15 14:58:40
 * @Version: V1.0
 */
@Service
public class SampleInspectReportDServiceImpl extends ServiceImpl<SampleInspectReportDMapper, SampleInspectReportD> implements ISampleInspectReportDService {
	
	@Autowired
	private SampleInspectReportDMapper sampleInspectReportDMapper;
	
	@Override
	public List<SampleInspectReportD> selectByMainId(String mainId) {
		return sampleInspectReportDMapper.selectByMainId(mainId);
	}
}
