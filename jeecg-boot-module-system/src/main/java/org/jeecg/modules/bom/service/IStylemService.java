package org.jeecg.modules.bom.service;

import org.jeecg.modules.bom.entity.Styles;
import org.jeecg.modules.bom.entity.Stylem;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 鞋型基本资料表
 * @Author: jeecg-boot
 * @Date:   2019-08-07 09:08:29
 * @Version: V1.0
 */
public interface IStylemService extends IService<Stylem> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Stylem stylem,List<Styles> stylesList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Stylem stylem,List<Styles> stylesList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
