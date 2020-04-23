package org.jeecg.modules.order.vo;

import java.util.List;
import org.jeecg.modules.order.entity.Odrm;
import org.jeecg.modules.order.entity.Odrd;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 订单主表
 * @Author: jeecg-boot
 * @Date:   2020-03-10 09:37:03
 * @Version: V1.0
 */
@Data
public class OdrmPage {
	
	/**需出货量*/
  	@Excel(name = "需出货量", width = 15)
	private java.lang.Integer cusQty;
	/**税率*/
  	@Excel(name = "税率", width = 15)
	private java.math.BigDecimal taxRate;
	/**未税价*/
  	@Excel(name = "未税价", width = 15)
	private java.math.BigDecimal taxPrice;
	/**合同交期*/
  	@Excel(name = "合同交期", width = 15)
	private java.lang.String chd;
	/**实际交期*/
  	@Excel(name = "实际交期", width = 15)
	private java.lang.String ahd;
	/**订单主ID*/
	private java.lang.String id;
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
	/**客户编号*/
  	@Excel(name = "客户编号", width = 15)
	private java.lang.String customNo;
	/**唛头编号*/
  	@Excel(name = "唛头编号", width = 15)
	private java.lang.String markNo;
	/**线别编号*/
  	@Excel(name = "线别编号", width = 15)
	private java.lang.String lineNo;
	/**型体编号*/
  	@Excel(name = "型体编号", width = 15)
	private java.lang.String styleNo;
	/**品牌编号*/
  	@Excel(name = "品牌编号", width = 15)
	private java.lang.String brandNo;
	/**楦头肥度*/
  	@Excel(name = "楦头肥度", width = 15)
	private java.lang.String lastWidth;
	/**冲销po信息*/
  	@Excel(name = "冲销po信息", width = 15)
	private java.lang.String poNo;
	/**生产部别*/
  	@Excel(name = "生产部别", width = 15)
	private java.lang.String proDept;
	/**客户订单编号*/
  	@Excel(name = "客户订单编号", width = 15)
	private java.lang.String custOdrNo;
	/**客户要求交期*/
  	@Excel(name = "客户要求交期", width = 15)
	private java.lang.String custReqDate;
	/**接单日期*/
  	@Excel(name = "接单日期", width = 15)
	private java.lang.String revOdrDate;
	/**预计用量编号*/
  	@Excel(name = "预计用量编号", width = 15)
	private java.lang.String purplanNo;
	/**第一次确认日*/
  	@Excel(name = "第一次确认日", width = 15)
	private java.lang.String fCfmDate;
	/**最后确认日*/
  	@Excel(name = "最后确认日", width = 15)
	private java.lang.String lCfmDate;
	/**SIZE种类*/
  	@Excel(name = "SIZE种类", width = 15)
	private java.lang.String sizeKind;
	/**订单双数*/
  	@Excel(name = "订单双数", width = 15)
	private java.lang.Integer odrQty;
	/**每箱双数*/
  	@Excel(name = "每箱双数", width = 15)
	private java.lang.Integer ctnPair;
	/**总箱数*/
  	@Excel(name = "总箱数", width = 15)
	private java.lang.Integer odrTotCtn;
	/**安排验货日*/
  	@Excel(name = "安排验货日", width = 15)
	private java.lang.String arrInsDate;
	/**实际验货日*/
  	@Excel(name = "实际验货日", width = 15)
	private java.lang.String actInsDate;
	/**实际出货日*/
  	@Excel(name = "实际出货日", width = 15)
	private java.lang.String shipDate;
	/**取消日期*/
  	@Excel(name = "取消日期", width = 15)
	private java.lang.String cancelDate;
	/**目的地*/
  	@Excel(name = "目的地", width = 15)
	private java.lang.String destCountry;
	/**目的港口*/
  	@Excel(name = "目的港口", width = 15)
	private java.lang.String destPort;
	/**订单年月*/
  	@Excel(name = "订单年月", width = 15)
	private java.lang.String odrYymm;
	/**订单类别1.正式 2.销样*/
  	@Excel(name = "订单类别1.正式 2.销样", width = 15)
	private java.lang.String odrKind;
	/**订单性质1.正式 2.客户PO 3.预购*/
  	@Excel(name = "订单性质1.正式 2.客户PO 3.预购", width = 15)
	private java.lang.String odrType;
	/**原订单厂别*/
  	@Excel(name = "原订单厂别", width = 15)
	private java.lang.String oriFctryNo;
	/**代工厂别*/
  	@Excel(name = "代工厂别", width = 15)
	private java.lang.String outworkFctryNo;
	/**用量产生时间*/
  	@Excel(name = "用量产生时间", width = 15)
	private java.lang.String purplanDate;
	/**主料加成比*/
  	@Excel(name = "主料加成比", width = 15)
	private java.math.BigDecimal mainmatAddRate;
	/**副料加成比*/
  	@Excel(name = "副料加成比", width = 15)
	private java.math.BigDecimal submatAddRate;
	/**厂内交期*/
  	@Excel(name = "厂内交期", width = 15)
	private java.lang.String factReqDate;
	/**出货数量*/
  	@Excel(name = "出货数量", width = 15)
	private java.lang.Integer shipQty;
	/**币别*/
  	@Excel(name = "币别", width = 15)
	private java.lang.String coinKind;
	/**含税单价*/
  	@Excel(name = "含税单价", width = 15)
	private java.math.BigDecimal unitPrice;
	/**异动人*/
  	@Excel(name = "异动人", width = 15)
	private java.lang.String userNo;
	/**异动时间*/
  	@Excel(name = "异动时间", width = 15)
	private java.lang.String modifyDt;
	/**预计用量版次*/
  	@Excel(name = "预计用量版次", width = 15)
	private java.lang.String edition;
	/**用量转档注记*/
  	@Excel(name = "用量转档注记", width = 15)
	private java.lang.String tranRmk;
	/**暂定版SIZE编号*/
  	@Excel(name = "暂定版SIZE编号", width = 15)
	private java.lang.String sizeNo;
	/**验货数量*/
  	@Excel(name = "验货数量", width = 15)
	private java.lang.Integer insTqty;
	/**SKU NO 开发版本编号*/
  	@Excel(name = "SKU NO 开发版本编号", width = 15)
	private java.lang.String skuNo;
	/**固定装注记1斩刀用量 2订单主档*/
  	@Excel(name = "固定装注记1斩刀用量 2订单主档", width = 15)
	private java.lang.String ctnFixRmk;
	/**翻箱次数*/
  	@Excel(name = "翻箱次数", width = 15)
	private java.lang.Integer siCheckNum;
	/**生产年月*/
  	@Excel(name = "生产年月", width = 15)
	private java.lang.String productYymm;
	/**生产周次*/
  	@Excel(name = "生产周次", width = 15)
	private java.lang.String productWeek;
	/**销售注记*/
  	@Excel(name = "销售注记", width = 15)
	private java.lang.String salRmk;
	/**生产指令确认注记*/
  	@Excel(name = "生产指令确认注记", width = 15)
	private java.lang.String proeRmk;
	/**生产指令确认人*/
  	@Excel(name = "生产指令确认人", width = 15)
	private java.lang.String proeUser;
	/**生产指令确认时间*/
  	@Excel(name = "生产指令确认时间", width = 15)
	private java.lang.String proeDt;
	/**实际生产栋别*/
  	@Excel(name = "实际生产栋别", width = 15)
	private java.lang.String aproDept;
	/**增值代码*/
  	@Excel(name = "增值代码", width = 15)
	private java.lang.String vaCode;
	/**条码批号*/
  	@Excel(name = "条码批号", width = 15)
	private java.lang.String lotNo;
	/**备注*/
  	@Excel(name = "备注", width = 15)
	private java.lang.String remark;
	/**销售类别(财务)*/
  	@Excel(name = "销售类别(财务)", width = 15)
	private java.lang.String salKind;
	/**请款数量*/
  	@Excel(name = "请款数量", width = 15)
	private java.lang.Integer payQty;
	/**审核注记*/
  	@Excel(name = "审核注记", width = 15)
	private java.lang.String verifyRmk;
	/**审核人*/
  	@Excel(name = "审核人", width = 15)
	private java.lang.String verifyUser;
	/**审核时间*/
  	@Excel(name = "审核时间", width = 15)
	private java.lang.String verifyDt;
	/**需成品鞋利库*/
  	@Excel(name = "需成品鞋利库", width = 15)
	private java.lang.String avaRmk;
	/**外包bom配色*/
  	@Excel(name = "外包bom配色", width = 15)
	private java.lang.String matchNo;
	
	@ExcelCollection(name="订单子表")
	private List<Odrd> odrdList;
	
}
