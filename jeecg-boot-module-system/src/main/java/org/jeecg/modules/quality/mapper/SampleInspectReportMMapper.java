package org.jeecg.modules.quality.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.quality.entity.SampleInspectReportM;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 验货报告主表
 * @Author: jeecg-boot
 * @Date:   2020-08-15 14:58:40
 * @Version: V1.0
 */
public interface SampleInspectReportMMapper extends BaseMapper<SampleInspectReportM> {

    public Map<String, Object> getSampleQty(String factNo, String custOdrNo);

    public List<Map<String, Object>> getDefectQty(String factNo, String custOdrNo, String itemMainNo);

    public List<Map<String, Object>> getCheckPoints(String factNo, String custOdrNo, String versionNo, String styleShorten, String itemMainNo, String itemMediumNo);

    public void addMinor(String factNo, String custOdrNo, String versionNo, String styleShorten, String itemMainNo, String itemMediumNo, String checkPointsNo, String minorField, String majorField, String criticalField);

    public void subtractMinor(String factNo, String custOdrNo, String versionNo, String styleShorten, String itemMainNo, String itemMediumNo, String checkPointsNo, String minorField, String majorField, String criticalField);

    public void updateInspectorDecision(String factNo, String custOdrNo, String versionNo, String styleShorten, String inspectorDecision);

    public Integer queryCustOdrNo(String factNo, String custOdrNo);

    public void insertReportM(String factNo, String custOdrNo, String versionNo, String paceCode, String modelColour, String orderType, String createBy);

    public void insertReportD(String factNo, String custOdrNo, String versionNo);

}
