package org.jeecg.modules.quality.entity;

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
 * @Description: 验货抽检标准
 * @Author: jeecg-boot
 * @Date:   2020-08-07 11:06:03
 * @Version: V1.0
 */
@Data
@TableName("v_sample_inspect_std_m")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_sample_inspect_std_m对象", description="验货抽检标准")
public class SampleInspectStd {
    
	/**factNo*/
	@Excel(name = "factNo", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**proDept*/
	@Excel(name = "proDept", width = 15)
    @ApiModelProperty(value = "proDept")
	private java.lang.String proDept;
	/**工厂订单*/
	@Excel(name = "工厂订单", width = 15)
    @ApiModelProperty(value = "工厂订单")
	private java.lang.String 工厂订单;
	/**客户订单*/
	@Excel(name = "客户订单", width = 15)
    @ApiModelProperty(value = "客户订单")
	private java.lang.String 客户订单;
	/**型体编号*/
	@Excel(name = "型体编号", width = 15)
    @ApiModelProperty(value = "型体编号")
	private java.lang.String 型体编号;
	/**型体名称*/
	@Excel(name = "型体名称", width = 15)
    @ApiModelProperty(value = "型体名称")
	private java.lang.String 型体名称;
	/**订单总箱数*/
	@Excel(name = "订单总箱数", width = 15)
    @ApiModelProperty(value = "订单总箱数")
	private java.lang.Integer 订单总箱数;
	/**订单总双数*/
	@Excel(name = "订单总双数", width = 15)
    @ApiModelProperty(value = "订单总双数")
	private java.lang.Integer 订单总双数;
	/**aql标准箱数*/
	@Excel(name = "aql标准箱数", width = 15)
    @ApiModelProperty(value = "aql标准箱数")
	private java.lang.Integer aql标准箱数;
	/**aql标准双数*/
	@Excel(name = "aql标准双数", width = 15)
    @ApiModelProperty(value = "aql标准双数")
	private java.lang.Integer aql标准双数;
	/**功能性检测双数*/
	@Excel(name = "功能性检测双数", width = 15)
    @ApiModelProperty(value = "功能性检测双数")
	private java.lang.Integer 功能性检测双数;
	/**结构性检测双数*/
	@Excel(name = "结构性检测双数", width = 15)
    @ApiModelProperty(value = "结构性检测双数")
	private java.lang.Integer 结构性检测双数;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
}
