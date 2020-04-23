package org.jeecg.modules.scan.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.oss.OssBootUtil;
import org.jeecg.modules.scan.entity.PurSupPictureDetail;
import org.jeecg.modules.scan.mapper.PurSupPictureDetailMapper;
import org.jeecg.modules.scan.service.IPurSupPictureDetailService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 子表-SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-25 09:08:43
 * @Version: V1.0
 */
@Service
@Slf4j
public class PurSupPictureDetailServiceImpl extends ServiceImpl<PurSupPictureDetailMapper, PurSupPictureDetail> implements IPurSupPictureDetailService {
	
	@Autowired
	private PurSupPictureDetailMapper purSupPictureDetailMapper;

	@Override
	public void upload(MultipartFile multipartFile, String sku, String basicDataId) throws IOException {
		String fileName = multipartFile.getOriginalFilename();
		PurSupPictureDetail purSupPictureDetail = new PurSupPictureDetail();
		purSupPictureDetail.setSku(sku);
		purSupPictureDetail.setBasicDataId(basicDataId);
		purSupPictureDetail.setPictureFileName(fileName);
		log.info("上传图片前。。。");
		String url = OssBootUtil.upload(multipartFile,"upload/test");
		log.info("上传图片后。。。");
		purSupPictureDetail.setPicturePath(url);
		this.save(purSupPictureDetail);
	}

	@Override
	public void remove(String fileName) throws IOException {
//		String fileName = multipartFile.getOriginalFilename();
		PurSupPictureDetail purSupPictureDetail = new PurSupPictureDetail();
		String picturePath = purSupPictureDetail.getPicturePath();
//		purSupPictureDetail.setSku(sku);
//		purSupPictureDetail.setBasicDataId(basicDataId);
//		purSupPictureDetail.setPictureFileName(fileName);
		log.info("移除图片前。。。");
		OssBootUtil.delete(fileName);
		log.info("移除图片后。。。");
//		purSupPictureDetail.setPicturePath(url);
//		this.save(purSupPictureDetail);
	}

	@Override
	public List<PurSupPictureDetail> selectByMainId(String mainId) {
		return purSupPictureDetailMapper.selectByMainId(mainId);
	}
}
