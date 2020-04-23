package org.jeecg.modules.scan.mapper;

import java.util.List;
import org.jeecg.modules.scan.entity.Giftd;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 礼品鞋明细表
 * @Author: jeecg-boot
 * @Date:   2019-08-20 14:36:40
 * @Version: V1.0
 */
public interface GiftdMapper extends BaseMapper<Giftd> {

	public boolean deleteByMainId(String mainId);
    
	public List<Giftd> selectByMainId(String mainId);
}
