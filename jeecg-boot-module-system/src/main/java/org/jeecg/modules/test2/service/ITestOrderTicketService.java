package org.jeecg.modules.test2.service;

import org.jeecg.modules.test2.entity.TestOrderTicket;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 产品明细
 * @Author: jeecg-boot
 * @Date:   2019-07-19
 * @Version: V1.0
 */
public interface ITestOrderTicketService extends IService<TestOrderTicket> {

	public List<TestOrderTicket> selectByMainId(String mainId);
}
