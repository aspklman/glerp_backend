package org.jeecg.modules.test2.service.impl;

import org.jeecg.modules.test2.entity.TestOrderTicket;
import org.jeecg.modules.test2.mapper.TestOrderTicketMapper;
import org.jeecg.modules.test2.service.ITestOrderTicketService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 产品明细
 * @Author: jeecg-boot
 * @Date:   2019-07-19
 * @Version: V1.0
 */
@Service
public class TestOrderTicketServiceImpl extends ServiceImpl<TestOrderTicketMapper, TestOrderTicket> implements ITestOrderTicketService {
	
	@Autowired
	private TestOrderTicketMapper testOrderTicketMapper;
	
	@Override
	public List<TestOrderTicket> selectByMainId(String mainId) {
		return testOrderTicketMapper.selectByMainId(mainId);
	}
}
