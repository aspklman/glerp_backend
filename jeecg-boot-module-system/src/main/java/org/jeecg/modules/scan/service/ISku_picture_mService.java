package org.jeecg.modules.scan.service;

import org.jeecg.modules.scan.entity.Sku_picture_d;
import org.jeecg.modules.scan.entity.Sku_picture_m;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: SKU图片主表
 * @Author: jeecg-boot
 * @Date:   2020-01-13 13:36:27
 * @Version: V1.0
 */
public interface ISku_picture_mService extends IService<Sku_picture_m> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Sku_picture_m sku_picture_m,List<Sku_picture_d> sku_picture_dList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Sku_picture_m sku_picture_m,List<Sku_picture_d> sku_picture_dList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
