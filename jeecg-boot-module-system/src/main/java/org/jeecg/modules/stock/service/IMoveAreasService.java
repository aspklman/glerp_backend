package org.jeecg.modules.stock.service;

import org.jeecg.modules.stock.entity.MoveAreas;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 成品移库
 * @Author: jeecg-boot
 * @Date:   2020-04-28
 * @Version: V1.0
 */
public interface IMoveAreasService extends IService<MoveAreas> {

    public void moveAreas(String factNo, String stkNo, String stockYymm, String factOdrNo, String locNo, String cpartNo, String locAreaOut, String locAreaIn, String createUpdateBy);

    public List<MoveAreas> queryOtherAreas(String factNo, String stkNo, String stockYymm, String factOdrNo, String locNo, String cpartNo, String locArea);

}
