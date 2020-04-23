package org.jeecg.modules.scan.service;

import org.jeecg.modules.scan.entity.Giftd;
import org.jeecg.modules.scan.entity.Giftm;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 礼品鞋主表
 * @Author: jeecg-boot
 * @Date:   2019-08-20 14:36:40
 * @Version: V1.0
 */
public interface IGiftmService extends IService<Giftm> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Giftm giftm,List<Giftd> giftdList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Giftm giftm,List<Giftd> giftdList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
