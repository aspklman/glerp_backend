package org.jeecg.modules.scan.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: SKU图片明细表
 * @Author: jeecg-boot
 * @Date:   2020-01-13 13:36:26
 * @Version: V1.0
 */
@Data
@TableName("sku_picture_d")
public class Sku_picture_d implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**厂区编号*/
    @Excel(name = "厂区编号", width = 15)
	private java.lang.String factNo;
	/**SKU编号*/
    @Excel(name = "SKU编号", width = 15)
	private java.lang.String sku;
	/**图片类型*/
    @Dict(dicCode="picture_type")
    @Excel(name = "图片类型", width = 15)
	private java.lang.String pictureType;
	/**图片存放路径*/
    @Excel(name = "图片存放路径", width = 15)
	private java.lang.String picturePath;
	/**SKU图片主id*/
	private java.lang.String skuPictureId;
	/**SKU图片明细id*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**创建人*/
    @Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人*/
    @Excel(name = "更新人", width = 15)
	private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
}
