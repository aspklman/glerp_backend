package org.jeecg.modules.order.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 订单主表
 * @Author: jeecg-boot
 * @Date:   2020-03-10 09:37:02
 * @Version: V1.0
 */
@Data
@TableName("odrm")
public class Odrm implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**需出货量*/
	private java.lang.Integer cusQty;
	/**税率*/
	private java.math.BigDecimal taxRate;
	/**未税价*/
	private java.math.BigDecimal taxPrice;
	/**合同交期*/
	private java.lang.String chd;
	/**实际交期*/
	private java.lang.String ahd;
	/**订单主ID*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
	private java.lang.String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**厂区编号*/
	private java.lang.String factNo;
	/**订单批号*/
	private java.lang.String factOdrNo;
	/**客户编号*/
	private java.lang.String customNo;
	/**唛头编号*/
	private java.lang.String markNo;
	/**线别编号*/
	private java.lang.String lineNo;
	/**型体编号*/
	private java.lang.String styleNo;
	/**品牌编号*/
	private java.lang.String brandNo;
	/**楦头肥度*/
	private java.lang.String lastWidth;
	/**冲销po信息*/
	private java.lang.String poNo;
	/**生产部别*/
	private java.lang.String proDept;
	/**客户订单编号*/
	private java.lang.String custOdrNo;
	/**客户要求交期*/
	private java.lang.String custReqDate;
	/**接单日期*/
	private java.lang.String revOdrDate;
	/**预计用量编号*/
	private java.lang.String purplanNo;
	/**第一次确认日*/
	private java.lang.String fCfmDate;
	/**最后确认日*/
	private java.lang.String lCfmDate;
	/**SIZE种类*/
	private java.lang.String sizeKind;
	/**订单双数*/
	private java.lang.Integer odrQty;
	/**每箱双数*/
	private java.lang.Integer ctnPair;
	/**总箱数*/
	private java.lang.Integer odrTotCtn;
	/**安排验货日*/
	private java.lang.String arrInsDate;
	/**实际验货日*/
	private java.lang.String actInsDate;
	/**实际出货日*/
	private java.lang.String shipDate;
	/**取消日期*/
	private java.lang.String cancelDate;
	/**目的地*/
	private java.lang.String destCountry;
	/**目的港口*/
	private java.lang.String destPort;
	/**订单年月*/
	private java.lang.String odrYymm;
	/**订单类别1.正式 2.销样*/
	private java.lang.String odrKind;
	/**订单性质1.正式 2.客户PO 3.预购*/
	private java.lang.String odrType;
	/**原订单厂别*/
	private java.lang.String oriFctryNo;
	/**代工厂别*/
	private java.lang.String outworkFctryNo;
	/**用量产生时间*/
	private java.lang.String purplanDate;
	/**主料加成比*/
	private java.math.BigDecimal mainmatAddRate;
	/**副料加成比*/
	private java.math.BigDecimal submatAddRate;
	/**厂内交期*/
	private java.lang.String factReqDate;
	/**出货数量*/
	private java.lang.Integer shipQty;
	/**币别*/
	private java.lang.String coinKind;
	/**含税单价*/
	private java.math.BigDecimal unitPrice;
	/**异动人*/
	private java.lang.String userNo;
	/**异动时间*/
	private java.lang.String modifyDt;
	/**预计用量版次*/
	private java.lang.String edition;
	/**用量转档注记*/
	private java.lang.String tranRmk;
	/**暂定版SIZE编号*/
	private java.lang.String sizeNo;
	/**验货数量*/
	private java.lang.Integer insTqty;
	/**SKU NO 开发版本编号*/
	private java.lang.String skuNo;
	/**固定装注记1斩刀用量 2订单主档*/
	private java.lang.String ctnFixRmk;
	/**翻箱次数*/
	private java.lang.Integer siCheckNum;
	/**生产年月*/
	private java.lang.String productYymm;
	/**生产周次*/
	private java.lang.String productWeek;
	/**销售注记*/
	private java.lang.String salRmk;
	/**生产指令确认注记*/
	private java.lang.String proeRmk;
	/**生产指令确认人*/
	private java.lang.String proeUser;
	/**生产指令确认时间*/
	private java.lang.String proeDt;
	/**实际生产栋别*/
	private java.lang.String aproDept;
	/**增值代码*/
	private java.lang.String vaCode;
	/**条码批号*/
	private java.lang.String lotNo;
	/**备注*/
	private java.lang.String remark;
	/**销售类别(财务)*/
	private java.lang.String salKind;
	/**请款数量*/
	private java.lang.Integer payQty;
	/**审核注记*/
	private java.lang.String verifyRmk;
	/**审核人*/
	private java.lang.String verifyUser;
	/**审核时间*/
	private java.lang.String verifyDt;
	/**需成品鞋利库*/
	private java.lang.String avaRmk;
	/**外包bom配色*/
	private java.lang.String matchNo;
}
