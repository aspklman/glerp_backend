package org.jeecg.modules.scan.mapper;

import java.util.List;
import org.jeecg.modules.scan.entity.Sku_picture_d;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: SKU图片明细表
 * @Author: jeecg-boot
 * @Date:   2020-01-13 13:36:26
 * @Version: V1.0
 */
public interface Sku_picture_dMapper extends BaseMapper<Sku_picture_d> {

	public boolean deleteByMainId(String mainId);
    
	public List<Sku_picture_d> selectByMainId(String mainId);
}
