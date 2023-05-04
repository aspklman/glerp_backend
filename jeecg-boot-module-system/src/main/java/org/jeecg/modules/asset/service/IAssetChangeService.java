package org.jeecg.modules.asset.service;

import org.jeecg.modules.asset.entity.AssetChange;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 资产异动表
 * @Author: jeecg-boot
 * @Date:   2023-04-27
 * @Version: V1.0
 */
public interface IAssetChangeService extends IService<AssetChange> {

    public String getChangeNo(String factNo);

    public Map<String, Object> getAssetDetailByNo(String factNo, String assetNo);

}
