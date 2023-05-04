package org.jeecg.modules.asset.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.asset.entity.BasicSmallKind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 资产明细类表
 * @Author: jeecg-boot
 * @Date:   2023-04-24
 * @Version: V1.0
 */
public interface BasicSmallKindMapper extends BaseMapper<BasicSmallKind> {

    public Map<String, Object> getAssetName(String factNo, String catalogId);

}
