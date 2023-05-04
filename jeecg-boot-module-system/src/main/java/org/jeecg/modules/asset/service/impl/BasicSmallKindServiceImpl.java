package org.jeecg.modules.asset.service.impl;

import org.jeecg.modules.asset.entity.BasicSmallKind;
import org.jeecg.modules.asset.mapper.BasicSmallKindMapper;
import org.jeecg.modules.asset.service.IBasicSmallKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 资产明细类表
 * @Author: jeecg-boot
 * @Date:   2023-04-24
 * @Version: V1.0
 */
@Service
public class BasicSmallKindServiceImpl extends ServiceImpl<BasicSmallKindMapper, BasicSmallKind> implements IBasicSmallKindService {

    @Autowired
    private BasicSmallKindMapper basicSmallKindMapper;

    @Override
    public Map<String, Object> getAssetName(String factNo, String catalogId){
        return basicSmallKindMapper.getAssetName(factNo, catalogId);
    }

}
