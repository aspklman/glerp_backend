package org.jeecg.modules.asset.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.asset.entity.AssetChange;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 资产异动表
 * @Author: jeecg-boot
 * @Date:   2023-04-27
 * @Version: V1.0
 */
public interface AssetChangeMapper extends BaseMapper<AssetChange> {

    public String getChangeNo(String factNo);

    public Map<String, Object> getAssetDetailByNo(String factNo, String assetNo);

}
