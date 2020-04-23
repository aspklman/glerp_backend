package org.jeecg.modules.order.mapper;

import java.util.List;
import org.jeecg.modules.order.entity.Odrd;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 订单子表
 * @Author: jeecg-boot
 * @Date:   2020-03-10 09:37:03
 * @Version: V1.0
 */
public interface OdrdMapper extends BaseMapper<Odrd> {

	public boolean deleteByMainId(String mainId);
    
	public List<Odrd> selectByMainId(String mainId);
}
