package org.jeecg.modules.scan.service;

import org.jeecg.modules.scan.entity.Sku_picture_d;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: SKU图片明细表
 * @Author: jeecg-boot
 * @Date:   2020-01-13 13:36:27
 * @Version: V1.0
 */
public interface ISku_picture_dService extends IService<Sku_picture_d> {

	public List<Sku_picture_d> selectByMainId(String mainId);
}
