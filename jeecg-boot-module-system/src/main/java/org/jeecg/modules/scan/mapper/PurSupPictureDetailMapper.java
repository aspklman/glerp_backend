package org.jeecg.modules.scan.mapper;

import java.util.List;
import org.jeecg.modules.scan.entity.PurSupPictureDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 子表-SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-25 09:08:43
 * @Version: V1.0
 */
public interface PurSupPictureDetailMapper extends BaseMapper<PurSupPictureDetail> {

	public boolean deleteByMainId(String mainId);
    
	public List<PurSupPictureDetail> selectByMainId(String mainId);
}
