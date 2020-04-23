package org.jeecg.modules.scan.service;

import org.jeecg.modules.scan.entity.PurSupPictureDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Description: 子表-SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-25 09:08:43
 * @Version: V1.0
 */
public interface IPurSupPictureDetailService extends IService<PurSupPictureDetail> {

	public void upload(MultipartFile multipartFile, String sku, String basicDataId) throws IOException;

	public void remove(String fileName) throws IOException;

	public List<PurSupPictureDetail> selectByMainId(String mainId);

}
