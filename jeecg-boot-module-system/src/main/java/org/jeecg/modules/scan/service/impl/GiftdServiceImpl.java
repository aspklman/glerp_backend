package org.jeecg.modules.scan.service.impl;

import org.jeecg.modules.scan.entity.Giftd;
import org.jeecg.modules.scan.mapper.GiftdMapper;
import org.jeecg.modules.scan.service.IGiftdService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 礼品鞋明细表
 * @Author: jeecg-boot
 * @Date:   2019-08-20 14:36:40
 * @Version: V1.0
 */
@Service
public class GiftdServiceImpl extends ServiceImpl<GiftdMapper, Giftd> implements IGiftdService {
	
	@Autowired
	private GiftdMapper giftdMapper;
	
	@Override
	public List<Giftd> selectByMainId(String mainId) {
		return giftdMapper.selectByMainId(mainId);
	}
}
