package org.jeecg.modules.asset.service.impl;

import org.jeecg.modules.asset.entity.AssetChange;
import org.jeecg.modules.asset.mapper.AssetChangeMapper;
import org.jeecg.modules.asset.mapper.AssetRecordMapper;
import org.jeecg.modules.asset.service.IAssetChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 资产异动表
 * @Author: jeecg-boot
 * @Date:   2023-04-27
 * @Version: V1.0
 */
@Service
public class AssetChangeServiceImpl extends ServiceImpl<AssetChangeMapper, AssetChange> implements IAssetChangeService {

    @Autowired
    private AssetChangeMapper assetChangeMapper;

    @Override
    public String getChangeNo(String factNo){
        return assetChangeMapper.getChangeNo(factNo);
    }

    @Override
    public Map<String, Object> getAssetDetailByNo(String factNo, String assetNo){
        return assetChangeMapper.getAssetDetailByNo(factNo, assetNo);
    }

}
