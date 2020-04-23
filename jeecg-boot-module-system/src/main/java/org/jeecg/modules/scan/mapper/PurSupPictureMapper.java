package org.jeecg.modules.scan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.scan.entity.PurSupPicture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-24
 * @Version: V1.0
 */
public interface PurSupPictureMapper extends BaseMapper<PurSupPicture> {

    public List<PurSupPicture> selectByMainId(String mainId);

}
