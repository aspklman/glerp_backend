package org.jeecg.modules.quality.service;

import org.jeecg.modules.quality.entity.SampleInspectReportD;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 验货报告子表
 * @Author: jeecg-boot
 * @Date:   2020-08-15 14:58:40
 * @Version: V1.0
 */
public interface ISampleInspectReportDService extends IService<SampleInspectReportD> {

	public List<SampleInspectReportD> selectByMainId(String mainId);
}
