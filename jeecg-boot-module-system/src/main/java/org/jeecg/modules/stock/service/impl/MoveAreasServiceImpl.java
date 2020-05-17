package org.jeecg.modules.stock.service.impl;

import org.jeecg.modules.stock.entity.MoveAreas;
import org.jeecg.modules.stock.mapper.MoveAreasMapper;
import org.jeecg.modules.stock.service.IMoveAreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 成品移库
 * @Author: jeecg-boot
 * @Date:   2020-04-28
 * @Version: V1.0
 */
@Service
public class MoveAreasServiceImpl extends ServiceImpl<MoveAreasMapper, MoveAreas> implements IMoveAreasService {

    @Autowired
    private MoveAreasMapper moveAreasMapper;

    @Override
    public void moveAreas(String factNo, String stkNo, String stockYymm, String factOdrNo, String locNo, String cpartNo, String locAreaOut, String locAreaIn, String createUpdateBy) {
        moveAreasMapper.moveAreas(factNo, stkNo, stockYymm, factOdrNo, locNo, cpartNo, locAreaOut, locAreaIn, createUpdateBy);
    }

    @Override
    public List<MoveAreas> queryOtherAreas(String factNo, String stkNo, String stockYymm, String factOdrNo, String locNo, String cpartNo, String locArea) {
        return moveAreasMapper.queryOtherAreas(factNo, stkNo, stockYymm, factOdrNo, locNo, cpartNo, locArea);
    }

}
