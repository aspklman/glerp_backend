package org.jeecg.modules.scan.service.impl;

import org.jeecg.modules.scan.entity.Sku_picture_d;
import org.jeecg.modules.scan.mapper.Sku_picture_dMapper;
import org.jeecg.modules.scan.service.ISku_picture_dService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: SKU图片明细表
 * @Author: jeecg-boot
 * @Date:   2020-01-13 13:36:27
 * @Version: V1.0
 */
@Service
public class Sku_picture_dServiceImpl extends ServiceImpl<Sku_picture_dMapper, Sku_picture_d> implements ISku_picture_dService {
	
	@Autowired
	private Sku_picture_dMapper sku_picture_dMapper;
	
	@Override
	public List<Sku_picture_d> selectByMainId(String mainId) {
		return sku_picture_dMapper.selectByMainId(mainId);
	}
}
