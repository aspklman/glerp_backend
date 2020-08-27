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
 * @Description: 成品中转库存子档
 * @Author: jeecg-boot
 * @Date:   2020-06-27 08:49:00
 * @Version: V1.0
 */
@Data
@TableName("s_stockd_transfer")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="s_stockd_transfer对象", description="成品中转库存子档")
public class SStockdTransfer {
    
	/**成品中转库存子档ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "成品中转库存子档ID")
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
	/**库别编号*/
	@Excel(name = "库别编号", width = 15)
    @ApiModelProperty(value = "库别编号")
	private java.lang.String stkNo;
	/**库存年月*/
	@Excel(name = "库存年月", width = 15)
    @ApiModelProperty(value = "库存年月")
	private java.lang.String stockYymm;
	/**订单批号*/
	@Excel(name = "订单批号", width = 15)
    @ApiModelProperty(value = "订单批号")
	private java.lang.String factOdrNo;
	/**存放区编号*/
	@Excel(name = "存放区编号", width = 15)
    @ApiModelProperty(value = "存放区编号")
	private java.lang.String locNo;
	/**成本部位*/
	@Excel(name = "成本部位", width = 15)
    @ApiModelProperty(value = "成本部位")
	private java.lang.String cpartNo;
	/**SIZE编号*/
	@Excel(name = "SIZE编号", width = 15)
    @ApiModelProperty(value = "SIZE编号")
	private java.lang.String sizeNo;
	/**现在库存量*/
	@Excel(name = "现在库存量", width = 15)
    @ApiModelProperty(value = "现在库存量")
	private java.lang.Integer stockQty;
	/**期初库存量*/
	@Excel(name = "期初库存量", width = 15)
    @ApiModelProperty(value = "期初库存量")
	private java.lang.Integer startQty;
	/**本月入库量*/
	@Excel(name = "本月入库量", width = 15)
    @ApiModelProperty(value = "本月入库量")
	private java.lang.Integer inQty;
	/**本月出库量*/
	@Excel(name = "本月出库量", width = 15)
    @ApiModelProperty(value = "本月出库量")
	private java.lang.Integer outQty;
	/**调整入库量*/
	@Excel(name = "调整入库量", width = 15)
    @ApiModelProperty(value = "调整入库量")
	private java.lang.Integer fallotInQty;
	/**调整出库量*/
	@Excel(name = "调整出库量", width = 15)
    @ApiModelProperty(value = "调整出库量")
	private java.lang.Integer fallotOutQty;
	/**盘盈亏数量*/
	@Excel(name = "盘盈亏数量", width = 15)
    @ApiModelProperty(value = "盘盈亏数量")
	private java.lang.Integer waxQty;
	/**存放地点*/
	@Excel(name = "存放地点", width = 15)
    @ApiModelProperty(value = "存放地点")
	private java.lang.String locArea;
}
