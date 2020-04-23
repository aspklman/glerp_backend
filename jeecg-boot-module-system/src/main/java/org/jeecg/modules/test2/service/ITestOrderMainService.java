package org.jeecg.modules.test2.service;

import org.jeecg.modules.test2.entity.TestOrderCustom;
import org.jeecg.modules.test2.entity.TestOrderTicket;
import org.jeecg.modules.test2.entity.TestOrderMain;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-19
 * @Version: V1.0
 */
public interface ITestOrderMainService extends IService<TestOrderMain> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(TestOrderMain testOrderMain,List<TestOrderCustom> testOrderCustomList,List<TestOrderTicket> testOrderTicketList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TestOrderMain testOrderMain,List<TestOrderCustom> testOrderCustomList,List<TestOrderTicket> testOrderTicketList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
