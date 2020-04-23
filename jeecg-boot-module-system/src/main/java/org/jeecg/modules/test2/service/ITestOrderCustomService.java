package org.jeecg.modules.test2.service;

import org.jeecg.modules.test2.entity.TestOrderCustom;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 客户明细
 * @Author: jeecg-boot
 * @Date:   2019-07-19
 * @Version: V1.0
 */
public interface ITestOrderCustomService extends IService<TestOrderCustom> {

	public List<TestOrderCustom> selectByMainId(String mainId);
}
