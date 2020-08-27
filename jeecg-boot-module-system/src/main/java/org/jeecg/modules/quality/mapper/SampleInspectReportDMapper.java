package org.jeecg.modules.quality.mapper;

import java.util.List;
import org.jeecg.modules.quality.entity.SampleInspectReportD;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 验货报告子表
 * @Author: jeecg-boot
 * @Date:   2020-08-15 14:58:40
 * @Version: V1.0
 */
public interface SampleInspectReportDMapper extends BaseMapper<SampleInspectReportD> {

	public boolean deleteByMainId(String mainId);
    
	public List<SampleInspectReportD> selectByMainId(String mainId);
}
