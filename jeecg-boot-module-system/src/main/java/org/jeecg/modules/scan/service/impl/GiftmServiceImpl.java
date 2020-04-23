package org.jeecg.modules.scan.service.impl;

import org.jeecg.modules.scan.entity.Giftm;
import org.jeecg.modules.scan.entity.Giftd;
import org.jeecg.modules.scan.mapper.GiftdMapper;
import org.jeecg.modules.scan.mapper.GiftmMapper;
import org.jeecg.modules.scan.service.IGiftmService;
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
 * @Description: 礼品鞋主表
 * @Author: jeecg-boot
 * @Date:   2019-08-20 14:36:40
 * @Version: V1.0
 */
@Service
public class GiftmServiceImpl extends ServiceImpl<GiftmMapper, Giftm> implements IGiftmService {

	@Autowired
	private GiftmMapper giftmMapper;
	@Autowired
	private GiftdMapper giftdMapper;
	@Autowired
	private ISysDataLogService sysDataLogService;		//数据快照第1步：导入ISysDataLogService
	
	@Override
	@Transactional
	public void saveMain(Giftm giftm, List<Giftd> giftdList) {
		giftm.setGiftNo(giftmMapper.selectGiftNo());	//编号(gift_no)：获取礼品鞋主表之最大编号+1，作为新增记录之编号
		giftmMapper.insert(giftm);
		for(Giftd entity:giftdList) {
			//外键设置
			entity.setGiftId(giftm.getId());
			entity.setGiftNo(giftm.getGiftNo());		//取礼品鞋主表之编号，填写至礼品鞋明细表之编号列
			entity.setDeptNm(giftm.getDeptNm());		//取礼品鞋主表之部门，填写至礼品鞋明细表之部门列
			entity.setCustomNm(giftm.getCustomNm());	//取礼品鞋主表之姓名，填写至礼品鞋明细表之姓名列
			giftdMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Giftm giftm,List<Giftd> giftdList) {
		sysDataLogService.addDataLog("giftm", giftm.getId(), JSONObject.toJSONString(giftm));		//数据快照第2步：记录数据快照(主表)
		giftmMapper.updateById(giftm);
		
		//1.先删除子表数据
		giftdMapper.deleteByMainId(giftm.getId());
		
		//2.子表数据重新插入
		for(Giftd entity:giftdList) {
			//外键设置
			entity.setGiftId(giftm.getId());
			entity.setGiftNo(giftm.getGiftNo());		//取礼品鞋主表之编号，填写至礼品鞋明细表之编号列
			entity.setDeptNm(giftm.getDeptNm());		//取礼品鞋主表之部门，填写至礼品鞋明细表之部门列
			entity.setCustomNm(giftm.getCustomNm());	//取礼品鞋主表之姓名，填写至礼品鞋明细表之姓名列
			sysDataLogService.addDataLog("giftd", entity.getId(), JSONObject.toJSONString(entity));		//数据快照第3步：记录数据快照(子表)
			giftdMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		giftdMapper.deleteByMainId(id);
		giftmMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			giftdMapper.deleteByMainId(id.toString());
			giftmMapper.deleteById(id);
		}
	}
	
}
