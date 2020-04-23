package org.jeecg.modules.order.service.impl;

import org.jeecg.modules.order.entity.Odrm;
import org.jeecg.modules.order.entity.Odrd;
import org.jeecg.modules.order.mapper.OdrdMapper;
import org.jeecg.modules.order.mapper.OdrmMapper;
import org.jeecg.modules.order.service.IOdrmService;
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
 * @Description: 订单主表
 * @Author: jeecg-boot
 * @Date:   2019-07-29 14:47:47
 * @Version: V1.0
 */
@Service
public class OdrmServiceImpl extends ServiceImpl<OdrmMapper, Odrm> implements IOdrmService {

	@Autowired
	private OdrmMapper odrmMapper;
	@Autowired
	private OdrdMapper odrdMapper;
	@Autowired
	private ISysDataLogService sysDataLogService;							  //数据快照第1步：导入ISysDataLogService

	@Override
	@Transactional
	public void saveMain(Odrm odrm, List<Odrd> odrdList) {
		odrmMapper.insert(odrm);
		int sizeQtys = 0;	//订单主表之订单数量
		for(Odrd entity:odrdList) {
			//外键设置
			entity.setOrderId(odrm.getId());
			entity.setFactOdrNo(odrm.getFactOdrNo());	//取订单主表之订单编号，填写至订单明细表之订单编号列
			sizeQtys = sizeQtys + entity.getSizeQty();	//订单明细表之订单数量累加
			odrdMapper.insert(entity);
		}
		odrm.setOdrQty(sizeQtys);		//设置订单主表之订单数量
		odrmMapper.updateById(odrm);	//更新订单主表
	}

	@Override
	@Transactional
	public void updateMain(Odrm odrm,List<Odrd> odrdList) {
		sysDataLogService.addDataLog("odrm", odrm.getId(), JSONObject.toJSONString(odrm));		  //数据快照第2步：记录数据快照(主表)
		odrmMapper.updateById(odrm);

		//1.先删除子表数据
		odrdMapper.deleteByMainId(odrm.getId());

		int sizeQtys = 0;		//订单主表这订单数量

		//2.子表数据重新插入
		for(Odrd entity:odrdList) {
			//外键设置
			entity.setOrderId(odrm.getId());
			entity.setFactOdrNo(odrm.getFactOdrNo());	//取订单主表之订单编号，填写至订单明细表之订单编号列
			sizeQtys = sizeQtys + entity.getSizeQty();
			sysDataLogService.addDataLog("odrd", entity.getId(), JSONObject.toJSONString(entity));  //数据快照第3步：记录数据快照(子表)
			odrdMapper.insert(entity);
		}
		odrm.setOdrQty(sizeQtys);		//设置订单主表之订单数量
		odrmMapper.updateById(odrm);	//更新订单主表
	}

	@Override
	@Transactional
	public void delMain(String id) {
		odrdMapper.deleteByMainId(id);
		odrmMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			odrdMapper.deleteByMainId(id.toString());
			odrmMapper.deleteById(id);
		}
	}

}
