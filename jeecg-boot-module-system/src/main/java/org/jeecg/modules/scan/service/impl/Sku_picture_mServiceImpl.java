package org.jeecg.modules.scan.service.impl;

import org.jeecg.modules.scan.entity.Sku_picture_m;
import org.jeecg.modules.scan.entity.Sku_picture_d;
import org.jeecg.modules.scan.mapper.Sku_picture_dMapper;
import org.jeecg.modules.scan.mapper.Sku_picture_mMapper;
import org.jeecg.modules.scan.service.ISku_picture_mService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: SKU图片主表
 * @Author: jeecg-boot
 * @Date:   2020-01-13 13:36:27
 * @Version: V1.0
 */
@Service
public class Sku_picture_mServiceImpl extends ServiceImpl<Sku_picture_mMapper, Sku_picture_m> implements ISku_picture_mService {

	@Autowired
	private Sku_picture_mMapper sku_picture_mMapper;
	@Autowired
	private Sku_picture_dMapper sku_picture_dMapper;
	
	@Override
	@Transactional
	public void saveMain(Sku_picture_m sku_picture_m, List<Sku_picture_d> sku_picture_dList) {
		sku_picture_mMapper.insert(sku_picture_m);
		for(Sku_picture_d entity:sku_picture_dList) {
			//外键设置
			entity.setSkuPictureId(sku_picture_m.getId());
			sku_picture_dMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Sku_picture_m sku_picture_m,List<Sku_picture_d> sku_picture_dList) {
		sku_picture_mMapper.updateById(sku_picture_m);
		
		//1.先删除子表数据
		sku_picture_dMapper.deleteByMainId(sku_picture_m.getId());
		
		//2.子表数据重新插入
		for(Sku_picture_d entity:sku_picture_dList) {
			//外键设置
			entity.setSkuPictureId(sku_picture_m.getId());
			sku_picture_dMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		sku_picture_dMapper.deleteByMainId(id);
		sku_picture_mMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			sku_picture_dMapper.deleteByMainId(id.toString());
			sku_picture_mMapper.deleteById(id);
		}
	}
	
}
