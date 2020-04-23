package org.jeecg.modules.scan.mapper;

import org.jeecg.modules.scan.entity.Giftm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 礼品鞋主表
 * @Author: jeecg-boot
 * @Date:   2019-08-20 14:36:40
 * @Version: V1.0
 */
public interface GiftmMapper extends BaseMapper<Giftm> {

	public String selectGiftNo();		//编号(gift_no)：获取礼品鞋主表之最大编号+1，作为新增记录之编号
	
}
