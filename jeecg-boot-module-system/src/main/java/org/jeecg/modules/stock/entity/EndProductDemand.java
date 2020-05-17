package org.jeecg.modules.stock.entity;

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
 * @Description: 成品需求表
 * @Author: jeecg-boot
 * @Date:   2020-05-06 09:13:25
 * @Version: V1.0
 */
@Data
@TableName("v_stock_endproduct_demand")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_stock_endproduct_demand对象", description="成品需求表")
public class EndProductDemand {
    
	/**factNo*/
	@Excel(name = "factNo", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**proDept*/
	@Excel(name = "proDept", width = 15)
    @ApiModelProperty(value = "proDept")
	private java.lang.String proDept;
	/**订单编号*/
	@Excel(name = "订单编号", width = 15)
    @ApiModelProperty(value = "订单编号")
	private java.lang.String 订单编号;
	/**型体*/
	@Excel(name = "型体", width = 15)
    @ApiModelProperty(value = "型体")
	private java.lang.String 型体;
	/**颜色*/
	@Excel(name = "颜色", width = 15)
    @ApiModelProperty(value = "颜色")
	private java.lang.String 颜色;
	/**接单日期*/
	@Excel(name = "接单日期", width = 15)
    @ApiModelProperty(value = "接单日期")
	private java.lang.String 接单日期;
	/**客户交期*/
	@Excel(name = "客户交期", width = 15)
    @ApiModelProperty(value = "客户交期")
	private java.lang.String 客户交期;
	/**size编号*/
	@Excel(name = "size编号", width = 15)
    @ApiModelProperty(value = "size编号")
	private java.lang.String size编号;
//	/**size序号*/
//	@Excel(name = "size序号", width = 15)
//    @ApiModelProperty(value = "size序号")
//	private java.lang.String size序号;
	/**订单数*/
	@Excel(name = "订单数", width = 15)
    @ApiModelProperty(value = "订单数")
	private java.lang.Integer 订单数;
	/**生产数*/
	@Excel(name = "生产数", width = 15)
    @ApiModelProperty(value = "生产数")
	private java.lang.Integer 生产数;
	/**入库数*/
	@Excel(name = "入库数", width = 15)
    @ApiModelProperty(value = "入库数")
	private java.lang.Integer 入库数;
	/**调整数*/
	@Excel(name = "调整数", width = 15)
    @ApiModelProperty(value = "调整数")
	private java.lang.Integer 调整数;
	/**库存数*/
	@Excel(name = "库存数", width = 15)
    @ApiModelProperty(value = "库存数")
	private java.lang.Integer 库存数;
	/**出库数*/
	@Excel(name = "出库数", width = 15)
	@ApiModelProperty(value = "出库数")
	private java.lang.Integer 出库数;
	/**欠数*/
	@Excel(name = "欠数", width = 15)
    @ApiModelProperty(value = "欠数")
	private java.lang.String 欠数;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
}
