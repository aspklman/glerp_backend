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
 * @Description: 半成品产量表
 * @Author: jeecg-boot
 * @Date:   2019-12-10 14:40:34
 * @Version: V1.0
 */
@Data
@TableName("stgscan_molding_half")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="stgscan_molding_half对象", description="半成品产量表")
public class Stgscan_molding_half {
    
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**生产日期*/
	@Excel(name = "生产日期", width = 15)
    @ApiModelProperty(value = "生产日期")
	private java.lang.String recDate;
	/**组别编号*/
	@Excel(name = "组别编号", width = 15)
    @ApiModelProperty(value = "组别编号")
	private java.lang.String secNo;
	/**时段08:00-09:30*/
	@Excel(name = "时段08:00-09:30", width = 15)
    @ApiModelProperty(value = "时段08:00-09:30")
	private java.math.BigDecimal a0800;
	/**时段09:30-11:30*/
	@Excel(name = "时段09:30-11:30", width = 15)
    @ApiModelProperty(value = "时段09:30-11:30")
	private java.math.BigDecimal a0930;
	/**时段11:30-12:00*/
	@Excel(name = "时段11:30-12:00", width = 15)
    @ApiModelProperty(value = "时段11:30-12:00")
	private java.math.BigDecimal a1130;
	/**时段13:00-15:00*/
	@Excel(name = "时段13:00-15:00", width = 15)
    @ApiModelProperty(value = "时段13:00-15:00")
	private java.math.BigDecimal a1300;
	/**时段15:00-17:00*/
	@Excel(name = "时段15:00-17:00", width = 15)
    @ApiModelProperty(value = "时段15:00-17:00")
	private java.math.BigDecimal a1500;
	/**时段17:00-18:30*/
	@Excel(name = "时段17:00-18:30", width = 15)
    @ApiModelProperty(value = "时段17:00-18:30")
	private java.math.BigDecimal a1700;
	/**时段OT*/
	@Excel(name = "时段OT", width = 15)
    @ApiModelProperty(value = "时段OT")
	private java.math.BigDecimal ot;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
	/**半成品产量ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "半成品产量ID")
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
