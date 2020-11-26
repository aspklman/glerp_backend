package org.jeecg.modules.quality.service.impl;

import org.jeecg.modules.quality.entity.SampleInspectReportM;
import org.jeecg.modules.quality.entity.SampleInspectReportD;
import org.jeecg.modules.quality.mapper.SampleInspectReportDMapper;
import org.jeecg.modules.quality.mapper.SampleInspectReportMMapper;
import org.jeecg.modules.quality.service.ISampleInspectReportMService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;
import java.util.Map;

/**
 * @Description: 验货报告主表
 * @Author: jeecg-boot
 * @Date:   2020-08-15 14:58:40
 * @Version: V1.0
 */
@Service
public class SampleInspectReportMServiceImpl extends ServiceImpl<SampleInspectReportMMapper, SampleInspectReportM> implements ISampleInspectReportMService {

	@Autowired
	private SampleInspectReportMMapper sampleInspectReportMMapper;
	@Autowired
	private SampleInspectReportDMapper sampleInspectReportDMapper;

	@Override
	public Map<String, Object> getSampleQty(String factNo, String factOdrNo) {
		return sampleInspectReportMMapper.getSampleQty(factNo, factOdrNo);
	}

	@Override
	public List<Map<String, Object>> getDefectQty(String factNo, String factOdrNo, String itemMainNo, String versionNo) {
		return sampleInspectReportMMapper.getDefectQty(factNo, factOdrNo, itemMainNo, versionNo);
	}

	@Override
	public List<Map<String, Object>> getCheckPoints(String factNo, String custOdrNo, String versionNo, String styleShorten, String itemMainNo, String itemMediumNo) {
		return sampleInspectReportMMapper.getCheckPoints(factNo, custOdrNo, versionNo, styleShorten, itemMainNo, itemMediumNo);
	}

	@Override
	public void addMinor(String factNo, String custOdrNo, String versionNo, String styleShorten, String itemMainNo, String itemMediumNo, String checkPointsNo, String minorField, String majorField, String criticalField) {
		sampleInspectReportMMapper.addMinor(factNo, custOdrNo, versionNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo, minorField, majorField, criticalField);
	}

	@Override
	public void subtractMinor(String factNo, String custOdrNo, String versionNo, String styleShorten, String itemMainNo, String itemMediumNo, String checkPointsNo, String minorField, String majorField, String criticalField) {
		sampleInspectReportMMapper.subtractMinor(factNo, custOdrNo, versionNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo, minorField, majorField, criticalField);
	}

	@Override
	public void updateInspectorDecision(String factNo, String custOdrNo, String versionNo, String styleShorten, String inspectorDecision) {
		sampleInspectReportMMapper.updateInspectorDecision(factNo, custOdrNo, versionNo, styleShorten, inspectorDecision);
	}

	@Override
	public Integer queryCustOdrNo(String factNo, String custOdrNo) {
		return sampleInspectReportMMapper.queryCustOdrNo(factNo, custOdrNo);
	}

	@Override
	public void insertReportM(String factNo, String custOdrNo, String orderType, String createBy) {
//		sampleInspectReportMMapper.insertReportM(factNo, custOdrNo, versionNo, paceCode, modelColour, orderType, createBy);
		sampleInspectReportMMapper.insertReportM(factNo, custOdrNo, orderType, createBy);
	}

	@Override
	public void insertReportD(String factNo, String custOdrNo, String createBy) {
		sampleInspectReportMMapper.insertReportD(factNo, custOdrNo, createBy);
	}

	@Override
	public void addTraceabilityCode(String factNo, String custOdrNo, String versionNo, String traceabilityCode) {
		sampleInspectReportMMapper.addTraceabilityCode(factNo, custOdrNo, versionNo, traceabilityCode);
	}

	@Override
	public void subtractTraceabilityCode(String factNo, String custOdrNo, String versionNo) {
		sampleInspectReportMMapper.subtractTraceabilityCode(factNo, custOdrNo, versionNo);
	}

	@Override
	public void addQuestion(String factNo, String custOdrNo, String versionNo, String styleShorten, String itemMainNo, String itemMediumNo, String checkPointsNo, String findQuestion) {
		sampleInspectReportMMapper.addQuestion(factNo, custOdrNo, versionNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo, findQuestion);
	}

	@Override
	public void subtractQuestion(String factNo, String custOdrNo, String versionNo, String styleShorten, String itemMainNo, String itemMediumNo, String checkPointsNo) {
		sampleInspectReportMMapper.subtractQuestion(factNo, custOdrNo, versionNo, styleShorten, itemMainNo, itemMediumNo, checkPointsNo);
	}
	
	@Override
	@Transactional
	public void saveMain(SampleInspectReportM sampleInspectReportM, List<SampleInspectReportD> sampleInspectReportDList) {
		sampleInspectReportMMapper.insert(sampleInspectReportM);
		for(SampleInspectReportD entity:sampleInspectReportDList) {
			//外键设置
			entity.setMainId(sampleInspectReportM.getId());
			sampleInspectReportDMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(SampleInspectReportM sampleInspectReportM,List<SampleInspectReportD> sampleInspectReportDList) {
		sampleInspectReportMMapper.updateById(sampleInspectReportM);
		
		//1.先删除子表数据
		sampleInspectReportDMapper.deleteByMainId(sampleInspectReportM.getId());
		
		//2.子表数据重新插入
		for(SampleInspectReportD entity:sampleInspectReportDList) {
			//外键设置
			entity.setMainId(sampleInspectReportM.getId());
			sampleInspectReportDMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		sampleInspectReportDMapper.deleteByMainId(id);
		sampleInspectReportMMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			sampleInspectReportDMapper.deleteByMainId(id.toString());
			sampleInspectReportMMapper.deleteById(id);
		}
	}
	
}
