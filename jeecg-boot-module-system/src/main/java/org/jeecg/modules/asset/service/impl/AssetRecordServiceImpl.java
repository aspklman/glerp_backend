package org.jeecg.modules.asset.service.impl;

import org.jeecg.modules.asset.entity.AssetRecord;
import org.jeecg.modules.asset.mapper.AssetRecordMapper;
import org.jeecg.modules.asset.mapper.AssetVendormMapper;
import org.jeecg.modules.asset.service.IAssetRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 资产档案表
 * @Author: jeecg-boot
 * @Date:   2023-04-24
 * @Version: V1.0
 */
@Service
public class AssetRecordServiceImpl extends ServiceImpl<AssetRecordMapper, AssetRecord> implements IAssetRecordService {

    @Autowired
    private AssetRecordMapper assetRecordMapper;

    @Override
    public String getAssetNo(String factNo, String catalogId){
        return assetRecordMapper.getAssetNo(factNo, catalogId);
    }

    @Override
    public void updateAssetDetail(String factNo, String assetNo, String state, String factLocation, String deptNo, String location, String locationFl, String locationDl, String locationFact, String custodian, String custodianUser, String userNo){
        assetRecordMapper.updateAssetDetail(factNo, assetNo, state, factLocation, deptNo, location, locationFl, locationDl, locationFact, custodian, custodianUser, userNo);
    }

}
