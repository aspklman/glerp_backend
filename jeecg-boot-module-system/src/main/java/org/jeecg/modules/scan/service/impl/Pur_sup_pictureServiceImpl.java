package org.jeecg.modules.scan.service.impl;

import org.jeecg.modules.scan.entity.Pur_sup_picture;
import org.jeecg.modules.scan.mapper.Pur_sup_pictureMapper;
import org.jeecg.modules.scan.service.IPur_sup_pictureService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-20 09:37:27
 * @Version: V1.0
 */
@Service
public class Pur_sup_pictureServiceImpl extends ServiceImpl<Pur_sup_pictureMapper, Pur_sup_picture> implements IPur_sup_pictureService {
	
	@Autowired
	private Pur_sup_pictureMapper pur_sup_pictureMapper;
	
	@Override
	public List<Pur_sup_picture> selectByMainId(String mainId) {
		return pur_sup_pictureMapper.selectByMainId(mainId);
	}
}
