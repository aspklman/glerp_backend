package org.jeecg.modules.order.entity;

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
 * @Description: 目的地表
 * @Author: jeecg-boot
 * @Date:   2019-08-02 16:41:11
 * @Version: V1.0
 */
@Data
@TableName("country")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="country对象", description="目的地表")
public class Country {
    
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 4)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**国家代号*/
	@Excel(name = "国家代号", width = 15)
    @ApiModelProperty(value = "国家代号")
	private java.lang.String countryNo;
	/**国名*/
	@Excel(name = "国名", width = 15)
    @ApiModelProperty(value = "国名")
	private java.lang.String country;
	/**英文国名*/
	@Excel(name = "英文国名", width = 15)
    @ApiModelProperty(value = "英文国名")
	private java.lang.String countryEng;
	/**集团目的地代码*/
	@Excel(name = "集团目的地代码", width = 15)
    @ApiModelProperty(value = "集团目的地代码")
	private java.lang.String hkdestNm;
	/**XPC国家*/
	@Excel(name = "XPC国家", width = 15)
    @ApiModelProperty(value = "XPC国家")
	private java.lang.String market;
	/**半成品成型地代号*/
	@Excel(name = "半成品成型地代号", width = 15)
    @ApiModelProperty(value = "半成品成型地代号")
	private java.lang.String assmNo;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
	/**目的地ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "目的地ID")
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
