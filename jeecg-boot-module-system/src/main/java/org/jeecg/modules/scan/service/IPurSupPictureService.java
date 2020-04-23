package org.jeecg.modules.scan.service;

import org.jeecg.modules.scan.entity.PurSupPicture;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description: SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-24
 * @Version: V1.0
 */
public interface IPurSupPictureService extends IService<PurSupPicture> {

    public void upload(MultipartFile multipartFile, String sku, String basicDataId) throws IOException;

}
