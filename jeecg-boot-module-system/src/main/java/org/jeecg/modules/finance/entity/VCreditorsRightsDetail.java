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
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 债权明细表
 * @Author: jeecg-boot
 * @Date:   2020-08-31 10:16:06
 * @Version: V1.0
 */
@Data
@TableName("v_creditors_rights_detail")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_creditors_rights_detail对象", description="债权明细表")
public class VCreditorsRightsDetail {
    
	/**factNo*/
	@Excel(name = "factNo", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**settleOrg*/
	@Dict(dicCode="settle_org")
	@Excel(name = "settleOrg", width = 15)
	@ApiModelProperty(value = "settleOrg")
	private java.lang.String settleOrg;
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
//	/**fDate*/
//	@Excel(name = "fDate", width = 15)
//	private java.lang.String fDate;
	/**fDate*/
	@Excel(name = "fDate", width = 20, format = "yyyy-MM-dd HH:mm:ss HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss HH:mm:ss")
	private java.util.Date fDate;
	/**fEndDate*/
	@Excel(name = "fEndDate", width = 20, format = "yyyy-MM-dd HH:mm:ss HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss HH:mm:ss")
	private java.util.Date fEndDate;
	/**amountReceivable*/
	@Excel(name = "amountReceivable", width = 15)
    @ApiModelProperty(value = "amountReceivable")
	private java.math.BigDecimal amountReceivable;
	/**overdueDays*/
	@Excel(name = "overdueDays", width = 15)
    @ApiModelProperty(value = "overdueDays")
	private java.lang.Integer overdueDays;
	/**analysisEndDate*/
	@Excel(name = "analysisEndDate", width = 20)
    @ApiModelProperty(value = "analysisEndDate")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss HH:mm:ss")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss HH:mm:ss")
	private java.lang.String analysisEndDate;
	/**accountPeriodRange*/
	@Excel(name = "accountPeriodRange", width = 15)
    @ApiModelProperty(value = "accountPeriodRange")
	private java.lang.String accountPeriodRange;
	/**createTime*/
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
}
