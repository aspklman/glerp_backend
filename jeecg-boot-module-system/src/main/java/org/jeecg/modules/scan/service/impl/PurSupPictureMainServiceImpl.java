package org.jeecg.modules.scan.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.scan.entity.PurSupPictureMain;
import org.jeecg.modules.scan.entity.PurSupPictureDetail;
import org.jeecg.modules.scan.mapper.PurSupPictureDetailMapper;
import org.jeecg.modules.scan.mapper.PurSupPictureMainMapper;
import org.jeecg.modules.scan.service.IPurSupPictureMainService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 主表-SKU基础资料表
 * @Author: jeecg-boot
 * @Date:   2020-02-25 09:08:43
 * @Version: V1.0
 */
@Service
@Slf4j
public class PurSupPictureMainServiceImpl extends ServiceImpl<PurSupPictureMainMapper, PurSupPictureMain> implements IPurSupPictureMainService {

	@Autowired
	private PurSupPictureMainMapper purSupPictureMainMapper;
	@Autowired
	private PurSupPictureDetailMapper purSupPictureDetailMapper;

	@Override
	@Transactional
	public void saveMain(PurSupPictureMain purSupPictureMain, List<PurSupPictureDetail> purSupPictureDetailList) {
		purSupPictureMainMapper.insert(purSupPictureMain);
		for(PurSupPictureDetail entity:purSupPictureDetailList) {
			//外键设置
			entity.setBasicDataId(purSupPictureMain.getId());
			purSupPictureDetailMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(PurSupPictureMain purSupPictureMain,List<PurSupPictureDetail> purSupPictureDetailList) {
		purSupPictureMainMapper.updateById(purSupPictureMain);
		
		//1.先删除子表数据
		purSupPictureDetailMapper.deleteByMainId(purSupPictureMain.getId());
		
		//2.子表数据重新插入
		for(PurSupPictureDetail entity:purSupPictureDetailList) {
			//外键设置
			entity.setBasicDataId(purSupPictureMain.getId());
			purSupPictureDetailMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		purSupPictureDetailMapper.deleteByMainId(id);
		purSupPictureMainMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			purSupPictureDetailMapper.deleteByMainId(id.toString());
			purSupPictureMainMapper.deleteById(id);
		}
	}
	
}
