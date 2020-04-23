package org.jeecg.modules.test2.vo;

import java.util.List;
import org.jeecg.modules.test2.entity.TestOrderMain;
import org.jeecg.modules.test2.entity.TestOrderCustom;
import org.jeecg.modules.test2.entity.TestOrderTicket;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-19
 * @Version: V1.0
 */
@Data
public class TestOrderMainPage {
	
	/**主键*/
	private java.lang.String id;
	/**订单号*/
  	@Excel(name = "订单号", width = 15)
	private java.lang.String orderCode;
	/**订单类型*/
  	@Excel(name = "订单类型", width = 15)
	private java.lang.String ctype;
	/**订单日期*/
  	@Excel(name = "订单日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date orderDate;
	/**订单金额*/
  	@Excel(name = "订单金额", width = 15)
	private java.lang.Integer orderMoney;
	/**订单备注*/
  	@Excel(name = "订单备注", width = 15)
	private java.lang.String content;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date createTime;
	/**修改人*/
  	@Excel(name = "修改人", width = 15)
	private java.lang.String updateBy;
	/**修改时间*/
  	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date updateTime;
	
	@ExcelCollection(name="客户明细")
	private List<TestOrderCustom> testOrderCustomList;
	@ExcelCollection(name="产品明细")
	private List<TestOrderTicket> testOrderTicketList;
	
}
