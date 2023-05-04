package org.jeecg.modules.asset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.asset.entity.AssetRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 资产档案表
 * @Author: jeecg-boot
 * @Date:   2023-04-24
 * @Version: V1.0
 */
public interface AssetRecordMapper extends BaseMapper<AssetRecord> {

    public String getAssetNo(String factNo, String catalogId);

    public void updateAssetDetail(String factNo, String assetNo, String state, String factLocation, String deptNo, String location, String locationFl, String locationDl, String locationFact, String custodian, String custodianUser, String userNo);

}
