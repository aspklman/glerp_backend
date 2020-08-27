package org.jeecg.modules.order.mapper;

import java.util.List;
import java.util.Map;

import org.jeecg.modules.quality.entity.SampleInspectStd;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 验货抽检标准
 * @Author: jeecg-boot
 * @Date:   2020-08-07
 * @Version: V1.0
 */
public interface SampleInspectStdMapper extends BaseMapper<SampleInspectStd> {

    public List<Map<String, Object>> getInspectDetail(String factNo, String factOdrNo);

}
