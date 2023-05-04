package org.jeecg.modules.asset.service;

import org.jeecg.modules.asset.entity.BasicSmallKind;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 资产明细类表
 * @Author: jeecg-boot
 * @Date:   2023-04-24
 * @Version: V1.0
 */
public interface IBasicSmallKindService extends IService<BasicSmallKind> {

    public Map<String, Object> getAssetName(String factNo, String catalogId);

}
