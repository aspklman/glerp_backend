package org.jeecg.modules.scan.service;

import org.jeecg.modules.scan.entity.PurSupPictureDetail;
import org.jeecg.modules.scan.entity.PurSupPictureMain;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 主表-SKU基础资料表
 * @Author: jeecg-boot
 * @Date:   2020-02-25 09:08:43
 * @Version: V1.0
 */
public interface IPurSupPictureMainService extends IService<PurSupPictureMain> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(PurSupPictureMain purSupPictureMain,List<PurSupPictureDetail> purSupPictureDetailList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(PurSupPictureMain purSupPictureMain,List<PurSupPictureDetail> purSupPictureDetailList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
