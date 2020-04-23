package org.jeecg.modules.bom.service;

import org.jeecg.modules.bom.entity.Styles;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 鞋型SIZE表
 * @Author: jeecg-boot
 * @Date:   2019-08-07 09:08:29
 * @Version: V1.0
 */
public interface IStylesService extends IService<Styles> {

	public List<Styles> selectByMainId(String mainId);
}
