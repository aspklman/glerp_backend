package org.jeecg.modules.order.service;

import org.jeecg.modules.quality.entity.SampleInspectStd;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 验货抽检标准
 * @Author: jeecg-boot
 * @Date:   2020-08-07
 * @Version: V1.0
 */
public interface ISampleInspectStdService extends IService<SampleInspectStd> {

    public List<Map<String, Object>> getInspectDetail(String factNo, String factOdrNo);

}
