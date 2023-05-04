package org.jeecg.modules.asset.service;

import org.jeecg.modules.asset.entity.AssetRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 资产档案表
 * @Author: jeecg-boot
 * @Date:   2023-04-24
 * @Version: V1.0
 */
public interface IAssetRecordService extends IService<AssetRecord> {

    public String getAssetNo(String factNo, String catalogId);

    public void updateAssetDetail(String factNo, String assetNo, String state, String factLocation, String deptNo, String location, String locationFl, String locationDl, String locationFact, String custodian, String custodianUser, String userNo);

}
