package org.jeecg.modules.scan.mapper;

import java.util.List;
import org.jeecg.modules.scan.entity.Pur_sup_picture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-20 09:37:27
 * @Version: V1.0
 */
public interface Pur_sup_pictureMapper extends BaseMapper<Pur_sup_picture> {

	public boolean deleteByMainId(String mainId);
    
	public List<Pur_sup_picture> selectByMainId(String mainId);
}
