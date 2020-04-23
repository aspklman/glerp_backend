package org.jeecg.modules.payment.entity;

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
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 汇率表
 * @Author: jeecg-boot
 * @Date:   2019-12-06 14:42:53
 * @Version: V1.0
 */
@Data
@TableName("exratem")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="exratem对象", description="汇率表")
public class Exratem {
    
	/**汇率ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "汇率ID")
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
	/**主币别*/
	@Excel(name = "主币别", width = 15)
    @ApiModelProperty(value = "主币别")
	@Dict(dicCode="coin_kind")
	private java.lang.String mainCoinKind;
	/**换算币别*/
	@Excel(name = "换算币别", width = 15)
    @ApiModelProperty(value = "换算币别")
	@Dict(dicCode="coin_kind")
	private java.lang.String exchCoinKind;
	/**生效年月*/
	@Excel(name = "生效年月", width = 15)
    @ApiModelProperty(value = "生效年月")
	private java.lang.String validDate;
	/**汇率*/
	@Excel(name = "汇率", width = 15)
    @ApiModelProperty(value = "汇率")
	private java.math.BigDecimal exchRate;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
}
