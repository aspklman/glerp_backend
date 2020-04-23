package org.jeecg.modules.purchase.entity;

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
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 供应商表
 * @Author: jeecg-boot
 * @Date:   2019-12-05 09:02:46
 * @Version: V1.0
 */
@Data
@TableName("vendorm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="vendorm对象", description="供应商表")
public class Vendorm {
    
	/**供应商ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "供应商ID")
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
	/**转厂注记*/
	@Excel(name = "转厂注记", width = 15)
    @ApiModelProperty(value = "转厂注记")
	@Dict(dicCode="yn_status")
	private java.lang.String traFact;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
	/**供应商注记*/
	@Excel(name = "供应商注记", width = 15)
    @ApiModelProperty(value = "供应商注记")
	@Dict(dicCode="vend_rmk")
	private java.lang.String mocRmk;
	/**汇款方式*/
	@Excel(name = "汇款方式", width = 15)
    @ApiModelProperty(value = "汇款方式")
	private java.lang.String salBy;
	/**付款类别*/
	@Excel(name = "付款类别", width = 15)
    @ApiModelProperty(value = "付款类别")
	private java.lang.String payKind;
	/**送货厂商*/
	@Excel(name = "送货厂商", width = 15)
    @ApiModelProperty(value = "送货厂商")
	private java.lang.String sendVendFnm;
	/**送货厂别*/
	@Excel(name = "送货厂别", width = 15)
    @ApiModelProperty(value = "送货厂别")
	private java.lang.String sendFact;
	/**海关交易注记(1.直接进口 2.转厂 3.国内购买)*/
	@Excel(name = "海关交易注记(1.直接进口 2.转厂 3.国内购买)", width = 15)
    @ApiModelProperty(value = "海关交易注记(1.直接进口 2.转厂 3.国内购买)")
	@Dict(dicCode="cus_rmk")
	private java.lang.String cusTradeRmk;
	/**指定供应商*/
	@Excel(name = "指定供应商", width = 15)
    @ApiModelProperty(value = "指定供应商")
	private java.lang.String nbVendor;
	/**发货地点*/
	@Excel(name = "发货地点", width = 15)
    @ApiModelProperty(value = "发货地点")
	private java.lang.String shipFromLocation;
	/**贸易商简称*/
	@Excel(name = "贸易商简称", width = 15)
    @ApiModelProperty(value = "贸易商简称")
	private java.lang.String agentCompanyName;
	/**统购注记*/
	@Excel(name = "统购注记", width = 15)
    @ApiModelProperty(value = "统购注记")
	@Dict(dicCode="yn_status")
	private java.lang.String purRmk;
	/**税率*/
	@Excel(name = "税率", width = 15)
    @ApiModelProperty(value = "税率")
	private java.math.BigDecimal taxRate;
	/**折扣比率*/
	@Excel(name = "折扣比率", width = 15)
    @ApiModelProperty(value = "折扣比率")
	private java.math.BigDecimal discountRate;
	/**填表日期*/
	@Excel(name = "填表日期", width = 15)
    @ApiModelProperty(value = "填表日期")
	private java.lang.String filledDate;
	/**英文名称*/
	@Excel(name = "英文名称", width = 15)
    @ApiModelProperty(value = "英文名称")
	private java.lang.String vendEnm;
	/**工厂地址*/
	@Excel(name = "工厂地址", width = 15)
    @ApiModelProperty(value = "工厂地址")
	private java.lang.String vendFactadress;
	/**填表日期*/
	@Excel(name = "填表日期", width = 15)
    @ApiModelProperty(value = "填表日期")
	private java.lang.String vendFilleddate;
	/**公司成立日期 */
	@Excel(name = "公司成立日期 ", width = 15)
    @ApiModelProperty(value = "公司成立日期 ")
	private java.lang.String vendReviseddate;
	/**是否是一般纳税人*/
	@Excel(name = "是否是一般纳税人", width = 15)
    @ApiModelProperty(value = "是否是一般纳税人")
	@Dict(dicCode="sf_status_sf")
	private java.lang.String vendIfcommontax;
	/**资本经营模式*/
	@Excel(name = "资本经营模式", width = 15)
    @ApiModelProperty(value = "资本经营模式")
	@Dict(dicCode="capital_form")
	private java.lang.String vendCapitalform;
	/**资本额*/
	@Excel(name = "资本额", width = 15)
    @ApiModelProperty(value = "资本额")
	private java.lang.String vendCapitalization;
	/**平均月营业额*/
	@Excel(name = "平均月营业额", width = 15)
    @ApiModelProperty(value = "平均月营业额")
	private java.lang.String vendAveragemonturnover;
	/**厂房建筑面积*/
	@Excel(name = "厂房建筑面积", width = 15)
    @ApiModelProperty(value = "厂房建筑面积")
	private java.lang.String vendFactarea;
	/**厂房类别：自有，租赁*/
	@Excel(name = "厂房类别：自有，租赁", width = 15)
    @ApiModelProperty(value = "厂房类别：自有，租赁")
	@Dict(dicCode="fact_kind")
	private java.lang.String vendFactkind;
	/**往来银行*/
	@Excel(name = "往来银行", width = 15)
    @ApiModelProperty(value = "往来银行")
	private java.lang.String vendBank;
	/**往来银行分行*/
	@Excel(name = "往来银行分行", width = 15)
    @ApiModelProperty(value = "往来银行分行")
	private java.lang.String vendBankbranch;
	/**往来银行帐号*/
	@Excel(name = "往来银行帐号", width = 15)
    @ApiModelProperty(value = "往来银行帐号")
	private java.lang.String vendBankaccount;
	/**主要产品*/
	@Excel(name = "主要产品", width = 15)
    @ApiModelProperty(value = "主要产品")
	private java.lang.String vendMainproduction;
	/**主要客户*/
	@Excel(name = "主要客户", width = 15)
    @ApiModelProperty(value = "主要客户")
	private java.lang.String vendMaincust;
	/**员工概况*/
	@Excel(name = "员工概况", width = 15)
    @ApiModelProperty(value = "员工概况")
	private java.lang.String vendGsituationp;
	/**技术合作厂商名称*/
	@Excel(name = "技术合作厂商名称", width = 15)
    @ApiModelProperty(value = "技术合作厂商名称")
	private java.lang.String vendNmofteccoopsupplier;
	/**关系企业名称*/
	@Excel(name = "关系企业名称", width = 15)
    @ApiModelProperty(value = "关系企业名称")
	private java.lang.String vendNmofrelenterprise;
	/**主要仪器设备*/
	@Excel(name = "主要仪器设备", width = 15)
    @ApiModelProperty(value = "主要仪器设备")
	private java.lang.String vendMainequipment;
	/**是否审核*/
	@Excel(name = "是否审核", width = 15)
    @ApiModelProperty(value = "是否审核")
	@Dict(dicCode="audit_status")
	private java.lang.String vendIfapproved;
	/**审核人*/
	@Excel(name = "审核人", width = 15)
    @ApiModelProperty(value = "审核人")
	private java.lang.String vendApprovedby;
	/**审核日期*/
	@Excel(name = "审核日期", width = 15)
    @ApiModelProperty(value = "审核日期")
	private java.lang.String vendApproveddate;
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**供应商编号*/
	@Excel(name = "供应商编号", width = 15)
    @ApiModelProperty(value = "供应商编号")
	private java.lang.String vendNo;
	/**供应商类别*/
	@Excel(name = "供应商类别", width = 15)
    @ApiModelProperty(value = "供应商类别")
	@Dict(dicCode="vend_kind")
	private java.lang.String vendKind;
	/**供应商简称*/
	@Excel(name = "供应商简称", width = 15)
    @ApiModelProperty(value = "供应商简称")
	private java.lang.String vendFnm;
	/**供应商全名*/
	@Excel(name = "供应商全名", width = 15)
    @ApiModelProperty(value = "供应商全名")
	private java.lang.String vendGnm;
	/**工商备案号*/
	@Excel(name = "工商备案号", width = 15)
    @ApiModelProperty(value = "工商备案号")
	private java.lang.String vendInvno;
	/**付款方式*/
	@Excel(name = "付款方式", width = 15)
    @ApiModelProperty(value = "付款方式")
	@Dict(dicCode="payment_kind")
	private java.lang.String paymentKind;
	/**交易条件*/
	@Excel(name = "交易条件", width = 15)
    @ApiModelProperty(value = "交易条件")
	@Dict(dicCode="trade_condition")
	private java.lang.String tradeCondition;
	/**供应商地址*/
	@Excel(name = "供应商地址", width = 15)
    @ApiModelProperty(value = "供应商地址")
	private java.lang.String vendAddr;
	/**联络电话*/
	@Excel(name = "联络电话", width = 15)
    @ApiModelProperty(value = "联络电话")
	private java.lang.String vendTel;
	/**传真号码*/
	@Excel(name = "传真号码", width = 15)
    @ApiModelProperty(value = "传真号码")
	private java.lang.String vendFax;
	/**供应商网址*/
	@Excel(name = "供应商网址", width = 15)
    @ApiModelProperty(value = "供应商网址")
	private java.lang.String vendUrl;
	/**联络人*/
	@Excel(name = "联络人", width = 15)
    @ApiModelProperty(value = "联络人")
	private java.lang.String contactEer;
	/**电子信箱*/
	@Excel(name = "电子信箱", width = 15)
    @ApiModelProperty(value = "电子信箱")
	private java.lang.String email;
	/**负责人*/
	@Excel(name = "负责人", width = 15)
    @ApiModelProperty(value = "负责人")
	private java.lang.String vendBoss;
	/**海关供应商编号*/
	@Excel(name = "海关供应商编号", width = 15)
    @ApiModelProperty(value = "海关供应商编号")
	private java.lang.String tarVend;
}
