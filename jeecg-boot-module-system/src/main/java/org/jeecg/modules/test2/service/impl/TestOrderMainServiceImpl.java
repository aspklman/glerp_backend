package org.jeecg.modules.test2.service.impl;

import org.jeecg.modules.test2.entity.TestOrderMain;
import org.jeecg.modules.test2.entity.TestOrderCustom;
import org.jeecg.modules.test2.entity.TestOrderTicket;
import org.jeecg.modules.test2.mapper.TestOrderCustomMapper;
import org.jeecg.modules.test2.mapper.TestOrderTicketMapper;
import org.jeecg.modules.test2.mapper.TestOrderMainMapper;
import org.jeecg.modules.test2.service.ITestOrderMainService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-19
 * @Version: V1.0
 */
@Service
public class TestOrderMainServiceImpl extends ServiceImpl<TestOrderMainMapper, TestOrderMain> implements ITestOrderMainService {

	@Autowired
	private TestOrderMainMapper testOrderMainMapper;
	@Autowired
	private TestOrderCustomMapper testOrderCustomMapper;
	@Autowired
	private TestOrderTicketMapper testOrderTicketMapper;
	
	@Override
	@Transactional
	public void saveMain(TestOrderMain testOrderMain, List<TestOrderCustom> testOrderCustomList,List<TestOrderTicket> testOrderTicketList) {
		testOrderMainMapper.insert(testOrderMain);
		for(TestOrderCustom entity:testOrderCustomList) {
			//外键设置
			entity.setOrderId(testOrderMain.getId());
			testOrderCustomMapper.insert(entity);
		}
		for(TestOrderTicket entity:testOrderTicketList) {
			//外键设置
			entity.setOrderId(testOrderMain.getId());
			testOrderTicketMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(TestOrderMain testOrderMain,List<TestOrderCustom> testOrderCustomList,List<TestOrderTicket> testOrderTicketList) {
		testOrderMainMapper.updateById(testOrderMain);
		
		//1.先删除子表数据
		testOrderCustomMapper.deleteByMainId(testOrderMain.getId());
		testOrderTicketMapper.deleteByMainId(testOrderMain.getId());
		
		//2.子表数据重新插入
		for(TestOrderCustom entity:testOrderCustomList) {
			//外键设置
			entity.setOrderId(testOrderMain.getId());
			testOrderCustomMapper.insert(entity);
		}
		for(TestOrderTicket entity:testOrderTicketList) {
			//外键设置
			entity.setOrderId(testOrderMain.getId());
			testOrderTicketMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		testOrderCustomMapper.deleteByMainId(id);
		testOrderTicketMapper.deleteByMainId(id);
		testOrderMainMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			testOrderCustomMapper.deleteByMainId(id.toString());
			testOrderTicketMapper.deleteByMainId(id.toString());
			testOrderMainMapper.deleteById(id);
		}
	}
	
}
