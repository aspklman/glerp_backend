package org.jeecg.modules.bom.service.impl;

import org.jeecg.modules.bom.entity.Styles;
import org.jeecg.modules.bom.mapper.StylesMapper;
import org.jeecg.modules.bom.service.IStylesService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 鞋型SIZE表
 * @Author: jeecg-boot
 * @Date:   2019-08-07 09:08:29
 * @Version: V1.0
 */
@Service
public class StylesServiceImpl extends ServiceImpl<StylesMapper, Styles> implements IStylesService {
	
	@Autowired
	private StylesMapper stylesMapper;
	
	@Override
	public List<Styles> selectByMainId(String mainId) {
		return stylesMapper.selectByMainId(mainId);
	}
}
