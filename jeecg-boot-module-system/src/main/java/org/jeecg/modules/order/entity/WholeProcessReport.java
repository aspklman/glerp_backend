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
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 全流程报表
 * @Author: jeecg-boot
 * @Date:   2020-07-31 11:54:55
 * @Version: V1.0
 */
@Data
@TableName("v_whole_process_order")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_whole_process_order对象", description="全流程报表")
public class WholeProcessReport {
    
	/**factNo*/
//	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**proDept*/
//	@Excel(name = "生产部别", width = 15)
    @ApiModelProperty(value = "proDept")
	private java.lang.String proDept;
//	/**customNo*/
////	@Excel(name = "客户编号", width = 15)
//    @ApiModelProperty(value = "customNo")
//	private java.lang.String customNo;
	/**接单日期*/
	@Excel(name = "接单日期", width = 15)
    @ApiModelProperty(value = "接单日期")
	private java.lang.String 接单日期;
	/**订单交期*/
	@Excel(name = "订单交期", width = 15)
    @ApiModelProperty(value = "订单交期")
	private java.lang.String 订单交期;
	/**抵扣工厂订单*/
//	@Excel(name = "抵扣工厂订单", width = 15)
    @ApiModelProperty(value = "抵扣工厂订单")
	private java.lang.String 抵扣工厂订单;
	/**工厂订单*/
	@Excel(name = "工厂订单", width = 15)
    @ApiModelProperty(value = "工厂订单")
	private java.lang.String factOdrNoIn;
	/**客户订单*/
	@Excel(name = "客户订单", width = 15)
    @ApiModelProperty(value = "客户订单")
	private java.lang.String 客户订单;
	/**型体编号*/
	@Excel(name = "型体编号", width = 15)
    @ApiModelProperty(value = "型体编号")
	private java.lang.String 型体编号;
	/**型体名称*/
//	@Excel(name = "型体名称", width = 15)
    @ApiModelProperty(value = "型体名称")
	private java.lang.String 型体名称;
	/**颜色*/
	@Excel(name = "颜色", width = 15)
    @ApiModelProperty(value = "颜色")
	private java.lang.String 颜色;
	/**订单类别*/
	@Excel(name = "订单类别", width = 15)
	@ApiModelProperty(value = "订单类别")
	private java.lang.String 订单类别;
	/**目的地*/
	@Excel(name = "目的地", width = 15)
    @ApiModelProperty(value = "目的地")
	private java.lang.String 目的地;
	/**订单数*/
	@Excel(name = "订单数", width = 15)
    @ApiModelProperty(value = "订单数")
	private java.lang.Integer 订单数;
	/**满箱日期*/
	@Excel(name = "满箱日期", width = 15)
	@ApiModelProperty(value = "满箱日期")
	private java.lang.String 满箱日期;
    /**入库欠数*/
    @Excel(name = "入库欠数", width = 15)
    @ApiModelProperty(value = "入库欠数")
    private java.lang.Integer oweTqty;
	/**满箱状态*/
	@Excel(name = "满箱状态", width = 15, dicCode = "sf_status_yn")
	@ApiModelProperty(value = "满箱状态")
    @Dict(dicCode="sf_status_yn")
	private java.lang.String finishMark;
	/**出货日期*/
	@Excel(name = "出货日期", width = 15)
	@ApiModelProperty(value = "出货日期")
	private java.lang.String 出货日期;
	/**出货状况*/
	@Excel(name = "出货状况", width = 15, dicCode = "sf_status_yn")
	@ApiModelProperty(value = "出货状况")
	private java.lang.String 出货状况;
    /**验货日期*/
    @Excel(name = "验货日期", width = 15)
    @ApiModelProperty(value = "验货日期")
    private java.lang.String inspectDate;
	/**验货结果*/
	@Excel(name = "验货结果", width = 15, dicCode = "inspector_decision")
	@ApiModelProperty(value = "验货结果")
	private java.lang.String 验货结果;
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**createTime*/
//	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
    /**未提前2天满箱*/
//    @Excel(name = "未提前2天满箱", width = 15)
    @ApiModelProperty(value = "未提前2天满箱")
    @Dict(dicCode="sf_status_yn")
    private java.lang.String finishNoTwoDays;
}
