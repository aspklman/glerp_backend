package org.jeecg.modules.scan.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.oss.OssBootUtil;
import org.jeecg.modules.scan.entity.PurSupPicture;
import org.jeecg.modules.scan.mapper.PurSupPictureMapper;
import org.jeecg.modules.scan.service.IPurSupPictureService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-24
 * @Version: V1.0
 */
@Service
@Slf4j
public class PurSupPictureServiceImpl extends ServiceImpl<PurSupPictureMapper, PurSupPicture> implements IPurSupPictureService {

    @Override
    public void upload(MultipartFile multipartFile, String sku, String basicDataId) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        PurSupPicture purSupPicture = new PurSupPicture();
        purSupPicture.setSku(sku);
        purSupPicture.setBasicDataId(basicDataId);
        purSupPicture.setPictureFileName(fileName);
        log.info("上传图片前。。。");
        String url = OssBootUtil.upload(multipartFile,"upload/test");
        log.info("上传图片后。。。");
        purSupPicture.setPicturePath(url);
        this.save(purSupPicture);
    }

}
