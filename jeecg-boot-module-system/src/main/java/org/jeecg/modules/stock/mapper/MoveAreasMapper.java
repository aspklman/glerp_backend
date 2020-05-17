package org.jeecg.modules.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.stock.entity.MoveAreas;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 成品移库
 * @Author: jeecg-boot
 * @Date:   2020-04-28
 * @Version: V1.0
 */
public interface MoveAreasMapper extends BaseMapper<MoveAreas> {

    public void moveAreas(String factNo, String stkNo, String stockYymm, String factOdrNo, String locNo, String cpartNo, String locAreaOut, String locAreaIn, String createUpdateBy);

    public List<MoveAreas> queryOtherAreas(String factNo, String stkNo, String stockYymm, String factOdrNo, String locNo, String cpartNo, String locArea);

}
