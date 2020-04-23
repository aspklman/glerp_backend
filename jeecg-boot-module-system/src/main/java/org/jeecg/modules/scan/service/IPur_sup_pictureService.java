package org.jeecg.modules.scan.service;

import org.jeecg.modules.scan.entity.Pur_sup_picture;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-20 09:37:27
 * @Version: V1.0
 */
public interface IPur_sup_pictureService extends IService<Pur_sup_picture> {

	public List<Pur_sup_picture> selectByMainId(String mainId);
}
