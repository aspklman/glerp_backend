package org.jeecg.modules.scan.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-20 09:37:27
 * @Version: V1.0
 */
@Data
@TableName("pur_sup_picture")
public class Pur_sup_picture implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**factNo*/
    @Excel(name = "factNo", width = 15)
	private java.lang.String factNo;
	/**sku*/
    @Excel(name = "sku", width = 15)
	private java.lang.String sku;
	/**pictureFileName*/
    @Excel(name = "pictureFileName", width = 15)
	private java.lang.String pictureFileName;
	/**picturePath*/
    @Excel(name = "picturePath", width = 15)
	private java.lang.String picturePath;
	/**basicDataId*/
	private java.lang.String basicDataId;
	/**id*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**createBy*/
    @Excel(name = "createBy", width = 15)
	private java.lang.String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**updateBy*/
    @Excel(name = "updateBy", width = 15)
	private java.lang.String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
}
