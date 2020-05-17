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
 * @Description: 订单分析
 * @Author: jeecg-boot
 * @Date:   2020-05-14 15:03:58
 * @Version: V1.0
 */
@Data
@TableName("v_order_order_analyze")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_order_order_analyze对象", description="订单分析")
public class OrderAnalyze {
    
	/**factNo*/
	@Excel(name = "factNo", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**proDept*/
	@Excel(name = "proDept", width = 15)
    @ApiModelProperty(value = "proDept")
	private java.lang.String proDept;
	/**customFnm*/
	@Excel(name = "customFnm", width = 15)
    @ApiModelProperty(value = "customFnm")
	private java.lang.String customFnm;
	/**brandNm*/
	@Excel(name = "brandNm", width = 15)
    @ApiModelProperty(value = "brandNm")
	private java.lang.String brandNm;
	/**articNo*/
	@Excel(name = "articNo", width = 15)
    @ApiModelProperty(value = "articNo")
	private java.lang.String articNo;
	/**revOdrDate*/
	@Excel(name = "revOdrDate", width = 15)
    @ApiModelProperty(value = "revOdrDate")
	private java.lang.String revOdrDate;
	/**custReqDate*/
	@Excel(name = "custReqDate", width = 15)
    @ApiModelProperty(value = "custReqDate")
	private java.lang.String custReqDate;
	/**odrQty*/
	@Excel(name = "odrQty", width = 15)
    @ApiModelProperty(value = "odrQty")
	private java.lang.Integer odrQty;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
}
