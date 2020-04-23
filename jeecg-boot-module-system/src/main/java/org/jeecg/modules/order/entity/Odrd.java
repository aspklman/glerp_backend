package org.jeecg.modules.order.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 订单子表
 * @Author: jeecg-boot
 * @Date:   2020-03-10 09:37:02
 * @Version: V1.0
 */
@Data
@TableName("odrd")
public class Odrd implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**接单双数*/
    @Excel(name = "接单双数", width = 15)
	private java.lang.Integer cusQty;
	/**订单明细ID*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**订单主ID*/
	private java.lang.String orderId;
	/**创建人*/
    @Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
    @Excel(name = "修改人", width = 15)
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**厂区编号*/
    @Excel(name = "厂区编号", width = 15)
	private java.lang.String factNo;
	/**订单批号*/
    @Excel(name = "订单批号", width = 15)
	private java.lang.String factOdrNo;
	/**SIZE 编号*/
    @Excel(name = "SIZE 编号", width = 15)
	private java.lang.String sizeNo;
	/**生产双数*/
    @Excel(name = "生产双数", width = 15)
	private java.lang.Integer sizeQty;
	/**出货数量*/
    @Excel(name = "出货数量", width = 15)
	private java.lang.Integer shipQty;
}
