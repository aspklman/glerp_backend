package org.jeecg.modules.scan.service;

import org.jeecg.modules.scan.entity.Giftd;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 礼品鞋明细表
 * @Author: jeecg-boot
 * @Date:   2019-08-20 14:36:40
 * @Version: V1.0
 */
public interface IGiftdService extends IService<Giftd> {

	public List<Giftd> selectByMainId(String mainId);
}
