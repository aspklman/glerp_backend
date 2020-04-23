package org.jeecg.modules.order.service.impl;

import org.jeecg.modules.order.entity.Odrd;
import org.jeecg.modules.order.mapper.OdrdMapper;
import org.jeecg.modules.order.service.IOdrdService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单子表
 * @Author: jeecg-boot
 * @Date:   2020-03-10 09:37:03
 * @Version: V1.0
 */
@Service
public class OdrdServiceImpl extends ServiceImpl<OdrdMapper, Odrd> implements IOdrdService {
	
	@Autowired
	private OdrdMapper odrdMapper;
	
	@Override
	public List<Odrd> selectByMainId(String mainId) {
		return odrdMapper.selectByMainId(mainId);
	}
}
