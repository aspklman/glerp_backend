package org.jeecg.modules.quality.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.quality.entity.SampleInspectStyle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 型体检验项目
 * @Author: jeecg-boot
 * @Date:   2020-12-22
 * @Version: V1.0
 */
public interface SampleInspectStyleMapper extends BaseMapper<SampleInspectStyle> {

    public List<Map<String, Object>> getCheckPoints(String factNo, String styleShorten, String itemMainNo, String itemMediumNo);

    public void addCheckPoint(String factNo, String styleShorten, String itemMainNo, String itemMediumNo, String checkPointsNo);

    public void subtractCheckPoint(String factNo, String styleShorten, String itemMainNo, String itemMediumNo, String checkPointsNo);

}
