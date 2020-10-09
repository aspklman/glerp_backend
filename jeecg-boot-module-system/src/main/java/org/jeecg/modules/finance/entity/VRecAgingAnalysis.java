package org.jeecg.modules.finance.entity;

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
 * @Description: 应收账龄分析表
 * @Author: jeecg-boot
 * @Date:   2020-08-31 15:19:04
 * @Version: V1.0
 */
@Data
@TableName("v_rec_aging_analysis")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_rec_aging_analysis对象", description="应收账龄分析表")
public class VRecAgingAnalysis {
    
	/**factNo*/
	@Excel(name = "factNo", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**deptNm*/
	@Excel(name = "deptNm", width = 15)
    @ApiModelProperty(value = "deptNm")
	private java.lang.String deptNm;
	/**custShortNm*/
	@Excel(name = "custShortNm", width = 15)
    @ApiModelProperty(value = "custShortNm")
	private java.lang.String custShortNm;
	/**custFullNm*/
	@Excel(name = "custFullNm", width = 15)
    @ApiModelProperty(value = "custFullNm")
	private java.lang.String custFullNm;
	/**recCondition*/
	@Excel(name = "recCondition", width = 15)
    @ApiModelProperty(value = "recCondition")
	private java.lang.String recCondition;
	/**recConditionDays*/
	@Excel(name = "recConditionDays", width = 15)
    @ApiModelProperty(value = "recConditionDays")
	private java.lang.Integer recConditionDays;
	/**currencyNm*/
	@Excel(name = "currencyNm", width = 15)
    @ApiModelProperty(value = "currencyNm")
	private java.lang.String currencyNm;
	/**amountReceivable*/
	@Excel(name = "amountReceivable", width = 15)
    @ApiModelProperty(value = "amountReceivable")
	private java.math.BigDecimal amountReceivable;
	/**amountNoOverdue*/
	@Excel(name = "amountNoOverdue", width = 15)
    @ApiModelProperty(value = "amountNoOverdue")
	private java.math.BigDecimal amountNoOverdue;
	/**amountOverdue0*/
	@Excel(name = "amountOverdue0", width = 15)
    @ApiModelProperty(value = "amountOverdue0")
	private java.math.BigDecimal amountOverdue0;
	/**amountOverdue30*/
	@Excel(name = "amountOverdue30", width = 15)
    @ApiModelProperty(value = "amountOverdue30")
	private java.math.BigDecimal amountOverdue30;
	/**amountOverdue60*/
	@Excel(name = "amountOverdue60", width = 15)
    @ApiModelProperty(value = "amountOverdue60")
	private java.math.BigDecimal amountOverdue60;
	/**amountOverdue90*/
	@Excel(name = "amountOverdue90", width = 15)
    @ApiModelProperty(value = "amountOverdue90")
	private java.math.BigDecimal amountOverdue90;
	/**amountOverdue180*/
	@Excel(name = "amountOverdue180", width = 15)
    @ApiModelProperty(value = "amountOverdue180")
	private java.math.BigDecimal amountOverdue180;
	/**amountOverdue360*/
	@Excel(name = "amountOverdue360", width = 15)
    @ApiModelProperty(value = "amountOverdue360")
	private java.math.BigDecimal amountOverdue360;
	/**amountOverdue720*/
	@Excel(name = "amountOverdue720", width = 15)
    @ApiModelProperty(value = "amountOverdue720")
	private java.math.BigDecimal amountOverdue720;
	/**amountOverdueTtl*/
	@Excel(name = "amountOverdueTtl", width = 15)
    @ApiModelProperty(value = "amountOverdueTtl")
	private java.math.BigDecimal amountOverdueTtl;
	/**createTime*/
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
}
