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
 * @Description: 数据看板时间表
 * @Author: jeecg-boot
 * @Date:   2019-12-11 09:05:34
 * @Version: V1.0
 */
@Data
@TableName("data_board_time")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="data_board_time对象", description="数据看板时间表")
public class Data_board_time {
    
	/**数据看板时间ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "数据看板时间ID")
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
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**型体编号*/
	@Excel(name = "型体编号", width = 15)
    @ApiModelProperty(value = "型体编号")
	private java.lang.String style;
	/**产线长度*/
	@Excel(name = "产线长度", width = 15)
    @ApiModelProperty(value = "产线长度")
	private java.lang.Integer lineLength;
	/**节拍时间*/
	@Excel(name = "节拍时间", width = 15)
    @ApiModelProperty(value = "节拍时间")
	private java.math.BigDecimal tt;
	/**总周期时间*/
	@Excel(name = "总周期时间", width = 15)
    @ApiModelProperty(value = "总周期时间")
	private java.math.BigDecimal tct;
	/**MFG_LT*/
	@Excel(name = "MFG_LT", width = 15)
    @ApiModelProperty(value = "MFG_LT")
	private java.math.BigDecimal mfgLt;
	/**排线图*/
	@Excel(name = "排线图", width = 15)
    @ApiModelProperty(value = "排线图")
	private byte[] linePicture;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
}
