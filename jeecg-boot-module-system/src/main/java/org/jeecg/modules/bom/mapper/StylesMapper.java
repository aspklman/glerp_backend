package org.jeecg.modules.bom.mapper;

import java.util.List;
import org.jeecg.modules.bom.entity.Styles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 鞋型SIZE表
 * @Author: jeecg-boot
 * @Date:   2019-08-07 09:08:29
 * @Version: V1.0
 */
public interface StylesMapper extends BaseMapper<Styles> {

	public boolean deleteByMainId(String mainId);
    
	public List<Styles> selectByMainId(String mainId);
}
