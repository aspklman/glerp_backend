package org.jeecg.modules.bom.service.impl;

import org.jeecg.modules.bom.entity.Stylem;
import org.jeecg.modules.bom.entity.Styles;
import org.jeecg.modules.bom.mapper.StylesMapper;
import org.jeecg.modules.bom.mapper.StylemMapper;
import org.jeecg.modules.bom.service.IStylemService;
import org.jeecg.modules.system.service.ISysDataLogService;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 鞋型基本资料表
 * @Author: jeecg-boot
 * @Date:   2019-08-07 09:08:29
 * @Version: V1.0
 */
@Service
public class StylemServiceImpl extends ServiceImpl<StylemMapper, Stylem> implements IStylemService {

	@Autowired
	private StylemMapper stylemMapper;
	@Autowired
	private StylesMapper stylesMapper;
	@Autowired
	private ISysDataLogService sysDataLogService;		//数据快照第1步：导入ISysDataLogService
	
	@Override
	@Transactional
	public void saveMain(Stylem stylem, List<Styles> stylesList) {
		stylemMapper.insert(stylem);
		for(Styles entity:stylesList) {
			//外键设置
			entity.setStyleId(stylem.getId());
			entity.setStyleNo(stylem.getStyleNo());	//取型体主表之型体编号，填写至型体明细表之型体编号列
			stylesMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Stylem stylem,List<Styles> stylesList) {
		sysDataLogService.addDataLog("stylem", stylem.getId(), JSONObject.toJSONString(stylem));		  	//数据快照第2步：记录数据快照(主表)
		stylemMapper.updateById(stylem);
		
		//1.先删除子表数据
		stylesMapper.deleteByMainId(stylem.getId());
		
		//2.子表数据重新插入
		for(Styles entity:stylesList) {
			//外键设置
			entity.setStyleId(stylem.getId());
			entity.setStyleNo(stylem.getStyleNo());	//取型体主表之型体编号，填写至型体明细表之型体编号列
			sysDataLogService.addDataLog("styles", entity.getId(), JSONObject.toJSONString(entity));  		//数据快照第3步：记录数据快照(子表)
			stylesMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		stylesMapper.deleteByMainId(id);
		stylemMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			stylesMapper.deleteByMainId(id.toString());
			stylemMapper.deleteById(id);
		}
	}
	
}
