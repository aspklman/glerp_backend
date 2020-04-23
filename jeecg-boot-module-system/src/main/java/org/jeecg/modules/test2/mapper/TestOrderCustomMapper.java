package org.jeecg.modules.test2.mapper;

import java.util.List;
import org.jeecg.modules.test2.entity.TestOrderCustom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户明细
 * @Author: jeecg-boot
 * @Date:   2019-07-19
 * @Version: V1.0
 */
public interface TestOrderCustomMapper extends BaseMapper<TestOrderCustom> {

	public boolean deleteByMainId(String mainId);
    
	public List<TestOrderCustom> selectByMainId(String mainId);
}
