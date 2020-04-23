package org.jeecg.modules.scan.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: SKU图片表
 * @Author: jeecg-boot
 * @Date:   2020-02-24 17:32:08
 * @Version: V1.0
 */
@Data
@TableName("pur_sup_picture")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="pur_sup_picture对象", description="SKU图片表")
public class PurSupPicture {
    
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**SKU编号*/
	@Excel(name = "SKU编号", width = 15)
    @ApiModelProperty(value = "SKU编号")
	private java.lang.String sku;
	/**图片文件名称*/
	@Excel(name = "图片文件名称", width = 15)
    @ApiModelProperty(value = "图片文件名称")
	private java.lang.String pictureFileName;
	/**图片存储路径*/
	@Excel(name = "图片存储路径", width = 15)
    @ApiModelProperty(value = "图片存储路径")
	private java.lang.String picturePath;
	/**SKU基础资料ID*/
	@Excel(name = "SKU基础资料ID", width = 15)
    @ApiModelProperty(value = "SKU基础资料ID")
	private java.lang.String basicDataId;
	/**SKU图片ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "SKU图片ID")
	private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;
}
