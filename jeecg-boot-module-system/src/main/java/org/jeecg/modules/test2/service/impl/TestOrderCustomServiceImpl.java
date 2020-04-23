package org.jeecg.modules.test2.service.impl;

import org.jeecg.modules.test2.entity.TestOrderCustom;
import org.jeecg.modules.test2.mapper.TestOrderCustomMapper;
import org.jeecg.modules.test2.service.ITestOrderCustomService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 客户明细
 * @Author: jeecg-boot
 * @Date:   2019-07-19
 * @Version: V1.0
 */
@Service
public class TestOrderCustomServiceImpl extends ServiceImpl<TestOrderCustomMapper, TestOrderCustom> implements ITestOrderCustomService {
	
	@Autowired
	private TestOrderCustomMapper testOrderCustomMapper;
	
	@Override
	public List<TestOrderCustom> selectByMainId(String mainId) {
		return testOrderCustomMapper.selectByMainId(mainId);
	}
}
