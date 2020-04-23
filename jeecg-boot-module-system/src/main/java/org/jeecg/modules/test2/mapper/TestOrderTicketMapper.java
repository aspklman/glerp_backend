package org.jeecg.modules.test2.mapper;

import java.util.List;
import org.jeecg.modules.test2.entity.TestOrderTicket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 产品明细
 * @Author: jeecg-boot
 * @Date:   2019-07-19
 * @Version: V1.0
 */
public interface TestOrderTicketMapper extends BaseMapper<TestOrderTicket> {

	public boolean deleteByMainId(String mainId);
    
	public List<TestOrderTicket> selectByMainId(String mainId);
}
