package org.jeecg.modules.order.service;

import org.jeecg.modules.order.entity.Odrd;
import org.jeecg.modules.order.entity.Odrm;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 订单主表
 * @Author: jeecg-boot
 * @Date:   2020-03-10 09:37:03
 * @Version: V1.0
 */
public interface IOdrmService extends IService<Odrm> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Odrm odrm,List<Odrd> odrdList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Odrm odrm,List<Odrd> odrdList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
