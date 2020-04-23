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
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户表
 * @Author: jeecg-boot
 * @Date:   2019-07-26
 * @Version: V1.0
 */
@Data
@TableName("custom")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="custom对象", description="客户表")
public class Custom {
    
	/**单箱记录注记(一个箱号一条记录)*/
	@Excel(name = "单箱记录注记(一个箱号一条记录)", width = 15)
    @ApiModelProperty(value = "单箱记录注记(一个箱号一条记录)")
	private java.lang.String perCtnRmk;
	/**同交期箱号相连注记(Y.相连 N.不相连)*/
	@Excel(name = "同交期箱号相连注记(Y.相连 N.不相连)", width = 15)
    @ApiModelProperty(value = "同交期箱号相连注记(Y.相连 N.不相连)")
	private java.lang.String ctnContinueRmk;
	/**纸箱规格抓取方式*/
	@Excel(name = "纸箱规格抓取方式", width = 15)
    @ApiModelProperty(value = "纸箱规格抓取方式")
	private java.lang.String ctnGetKind;
	/**材积系数*/
	@Excel(name = "材积系数", width = 15)
    @ApiModelProperty(value = "材积系数")
	private java.lang.String seqGetKind;
	/**odrContinueRmk*/
	@Excel(name = "odrContinueRmk", width = 15)
    @ApiModelProperty(value = "odrContinueRmk")
	private java.lang.String odrContinueRmk;
	/**cbmRate*/
	@Excel(name = "cbmRate", width = 15)
    @ApiModelProperty(value = "cbmRate")
	private java.math.BigDecimal cbmRate;
	/**销货会计科目*/
	@Excel(name = "销货会计科目", width = 15)
    @ApiModelProperty(value = "销货会计科目")
	private java.lang.String salAccNo;
	/**币别*/
	@Excel(name = "币别", width = 15)
    @ApiModelProperty(value = "币别")
	@Dict(dicCode="coin_kind")
	private java.lang.String coinKind;
	/**联络人电话*/
	@Excel(name = "联络人电话", width = 15)
    @ApiModelProperty(value = "联络人电话")
	private java.lang.String contactTel;
	/**负责人*/
	@Excel(name = "负责人", width = 15)
    @ApiModelProperty(value = "负责人")
	private java.lang.String cusBoss;
	/**法人代表*/
	@Excel(name = "法人代表", width = 15)
    @ApiModelProperty(value = "法人代表")
	private java.lang.String cusLegalp;
	/**品质负责人*/
	@Excel(name = "品质负责人", width = 15)
    @ApiModelProperty(value = "品质负责人")
	private java.lang.String cusQualityp;
	/**财务负责人*/
	@Excel(name = "财务负责人", width = 15)
    @ApiModelProperty(value = "财务负责人")
	private java.lang.String cusFinancep;
	/**采购负责人*/
	@Excel(name = "采购负责人", width = 15)
    @ApiModelProperty(value = "采购负责人")
	private java.lang.String cusPurchasep;
	/**负责人电话*/
	@Excel(name = "负责人电话", width = 15)
    @ApiModelProperty(value = "负责人电话")
	private java.lang.String cusBossTel;
	/**法人代表电话*/
	@Excel(name = "法人代表电话", width = 15)
    @ApiModelProperty(value = "法人代表电话")
	private java.lang.String cusLegalpTel;
	/**采购负责人电话*/
	@Excel(name = "采购负责人电话", width = 15)
    @ApiModelProperty(value = "采购负责人电话")
	private java.lang.String cusPurchasepTel;
	/**品质负责人电话*/
	@Excel(name = "品质负责人电话", width = 15)
    @ApiModelProperty(value = "品质负责人电话")
	private java.lang.String cusQualitypTel;
	/**财务负责人电话*/
	@Excel(name = "财务负责人电话", width = 15)
    @ApiModelProperty(value = "财务负责人电话")
	private java.lang.String cusFinancepTel;
	/**所属行业*/
	@Excel(name = "所属行业", width = 15)
    @ApiModelProperty(value = "所属行业")
	@Dict(dicCode="industry_type")
	private java.lang.String cusIndustry;
	/**设立登记日期*/
	@Excel(name = "设立登记日期", width = 15)
    @ApiModelProperty(value = "设立登记日期")
	private java.lang.String cusFounddate;
	/**资本型态*/
	@Excel(name = "资本型态", width = 15)
    @ApiModelProperty(value = "资本型态")
	@Dict(dicCode="capital_type")
	private java.lang.String cusCapitaltype;
	/**年营业额*/
	@Excel(name = "年营业额", width = 15)
    @ApiModelProperty(value = "年营业额")
	private java.lang.String cusAnnualsalesamt;
	/**资本额*/
	@Excel(name = "资本额", width = 15)
    @ApiModelProperty(value = "资本额")
	private java.lang.String cusCapitalamt;
	/**厂房面积*/
	@Excel(name = "厂房面积", width = 15)
    @ApiModelProperty(value = "厂房面积")
	private java.lang.String cusFactarea;
	/**厂房*/
	@Excel(name = "厂房", width = 15)
    @ApiModelProperty(value = "厂房")
	@Dict(dicCode="fact_kind")
	private java.lang.String cusFactkind;
	/**员工状况*/
	@Excel(name = "员工状况", width = 15)
    @ApiModelProperty(value = "员工状况")
	private java.lang.String cusEmployeeDes;
	/**主要设备*/
	@Excel(name = "主要设备", width = 15)
    @ApiModelProperty(value = "主要设备")
	private java.lang.String cusMainequipment;
	/**主要产品*/
	@Excel(name = "主要产品", width = 15)
    @ApiModelProperty(value = "主要产品")
	private java.lang.String cusMainproduction;
	/**原料需求量*/
	@Excel(name = "原料需求量", width = 15)
    @ApiModelProperty(value = "原料需求量")
	private java.lang.String cusMaterialrequired;
	/**客户性质*/
	@Excel(name = "客户性质", width = 15)
    @ApiModelProperty(value = "客户性质")
	@Dict(dicCode="custom_character")
	private java.lang.String cusCharacter;
	/**付款地点*/
	@Excel(name = "付款地点", width = 15)
    @ApiModelProperty(value = "付款地点")
	@Dict(dicCode="pay_address")
	private java.lang.String cusPayaddress;
	/**收款方式*/
	@Excel(name = "收款方式", width = 15)
    @ApiModelProperty(value = "收款方式")
	private java.lang.String cusReceivedkind;
	/**付款方式*/
	@Excel(name = "付款方式", width = 15)
    @ApiModelProperty(value = "付款方式")
	@Dict(dicCode="pay_kind")
	private java.lang.String cusPaykind;
	/**请款日*/
	@Excel(name = "请款日", width = 15)
    @ApiModelProperty(value = "请款日")
	private java.lang.String cusReceiptday;
	/**收款日期 */
	@Excel(name = "收款日期 ", width = 15)
    @ApiModelProperty(value = "收款日期 ")
	private java.lang.String cusReceiptdate;
	/**审核状态*/
	@Excel(name = "审核状态", width = 15)
    @ApiModelProperty(value = "审核状态")
	@Dict(dicCode="audit_status")
	private java.lang.String cusIfapproved;
	/**审核人*/
	@Excel(name = "审核人", width = 15)
    @ApiModelProperty(value = "审核人")
	private java.lang.String cusApprovedby;
	/**审核日期*/
	@Excel(name = "审核日期", width = 15)
    @ApiModelProperty(value = "审核日期")
	private java.lang.String cusApproveddate;
	/**客户ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "客户ID")
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
	@Excel(name = "厂区编号", width = 4)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private java.lang.String customNo;
	/**客户全称*/
	@Excel(name = "客户全称", width = 15)
    @ApiModelProperty(value = "客户全称")
	private java.lang.String customGnm;
	/**客户英文名称*/
	@Excel(name = "客户英文名称", width = 15)
    @ApiModelProperty(value = "客户英文名称")
	private java.lang.String customEnm;
	/**客户简称*/
	@Excel(name = "客户简称", width = 15)
    @ApiModelProperty(value = "客户简称")
	private java.lang.String customFnm;
	/**工商备案号*/
	@Excel(name = "工商备案号", width = 15)
    @ApiModelProperty(value = "工商备案号")
	private java.lang.String customInvno;
	/**折扣比率*/
	@Excel(name = "折扣比率", width = 15)
    @ApiModelProperty(value = "折扣比率")
	private java.lang.Integer discountRate;
	/**付款方式*/
	@Excel(name = "付款方式", width = 15)
    @ApiModelProperty(value = "付款方式")
	private java.lang.String paymentKind;
	/**交易条件*/
	@Excel(name = "交易条件", width = 15)
    @ApiModelProperty(value = "交易条件")
	@Dict(dicCode="trade_condition")
	private java.lang.String tradeCondition;
	/**集团注记*/
	@Excel(name = "集团注记", width = 15)
    @ApiModelProperty(value = "集团注记")
	@Dict(dicCode="bloc_rmk")
	private java.lang.String blocRmk;
	/**应收款DR(借)*/
	@Excel(name = "应收款DR(借)", width = 15)
    @ApiModelProperty(value = "应收款DR(借)")
	private java.lang.String recaccDr;
	/**应收款CR(贷)*/
	@Excel(name = "应收款CR(贷)", width = 15)
    @ApiModelProperty(value = "应收款CR(贷)")
	private java.lang.String recaccCr;
	/**折扣款DR(借)*/
	@Excel(name = "折扣款DR(借)", width = 15)
    @ApiModelProperty(value = "折扣款DR(借)")
	private java.lang.String disaccDr;
	/**折扣款CR(贷)*/
	@Excel(name = "折扣款CR(贷)", width = 15)
    @ApiModelProperty(value = "折扣款CR(贷)")
	private java.lang.String disaccCr;
	/**现付折扣*/
	@Excel(name = "现付折扣", width = 15)
    @ApiModelProperty(value = "现付折扣")
	private java.lang.Integer cashRebate;
	/**客户地址*/
	@Excel(name = "客户地址", width = 15)
    @ApiModelProperty(value = "客户地址")
	private java.lang.String customAddr;
	/**联络人*/
	@Excel(name = "联络人", width = 15)
    @ApiModelProperty(value = "联络人")
	private java.lang.String contactEer;
	/**联络电话*/
	@Excel(name = "联络电话", width = 15)
    @ApiModelProperty(value = "联络电话")
	private java.lang.String vendTel;
	/**传真号码*/
	@Excel(name = "传真号码", width = 15)
    @ApiModelProperty(value = "传真号码")
	private java.lang.String vendFax;
	/**EMAIL*/
	@Excel(name = "EMAIL", width = 15)
    @ApiModelProperty(value = "EMAIL")
	private java.lang.String email;
	/**海关客户编号*/
	@Excel(name = "海关客户编号", width = 15)
    @ApiModelProperty(value = "海关客户编号")
	private java.lang.String tarCustom;
	/**请款明细格式*/
	@Excel(name = "请款明细格式", width = 15)
    @ApiModelProperty(value = "请款明细格式")
	private java.lang.String rptFmt1;
	/**记重注记*/
	@Excel(name = "记重注记", width = 15)
    @ApiModelProperty(value = "记重注记")
	private java.lang.String wgtRmk;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
	/**香港客户编号*/
	@Excel(name = "香港客户编号", width = 15)
    @ApiModelProperty(value = "香港客户编号")
	private java.lang.String hkcustomNo;
	/**收货方*/
	@Excel(name = "收货方", width = 15)
    @ApiModelProperty(value = "收货方")
	private java.lang.String recCustFnm;
	/**收货厂别*/
	@Excel(name = "收货厂别", width = 15)
    @ApiModelProperty(value = "收货厂别")
	private java.lang.String recFact;
	/**LOSS请款注记*/
	@Excel(name = "LOSS请款注记", width = 15)
    @ApiModelProperty(value = "LOSS请款注记")
	private java.lang.String lossPayRmk;
	/**海关交易注记(1.直接出口 2.转厂 3.内销)*/
	@Excel(name = "海关交易注记(1.直接出口 2.转厂 3.内销)", width = 15)
    @ApiModelProperty(value = "海关交易注记(1.直接出口 2.转厂 3.内销)")
	private java.lang.String cusTradeRmk;
	/**PACKING产生注记*/
	@Excel(name = "PACKING产生注记", width = 15)
    @ApiModelProperty(value = "PACKING产生注记")
	private java.lang.String packingRmk;
	/**纸箱订购注记*/
	@Excel(name = "纸箱订购注记", width = 15)
    @ApiModelProperty(value = "纸箱订购注记")
	private java.lang.String ctnPurRmk;
	/**PACKINGLIST格式*/
	@Excel(name = "PACKINGLIST格式", width = 15)
    @ApiModelProperty(value = "PACKINGLIST格式")
	private java.lang.String packlistForm;
	/**箱号前缀文字*/
	@Excel(name = "箱号前缀文字", width = 15)
    @ApiModelProperty(value = "箱号前缀文字")
	private java.lang.String textBeforeCtn;
	/**SIZE混装注记(N.不混装/Y.混装)*/
	@Excel(name = "SIZE混装注记(N.不混装/Y.混装)", width = 15)
    @ApiModelProperty(value = "SIZE混装注记(N.不混装/Y.混装)")
	private java.lang.String sizeMixRmk;
	/**LOSS产生PACKING注记(N.不产生 Y.产生)*/
	@Excel(name = "LOSS产生PACKING注记(N.不产生 Y.产生)", width = 15)
    @ApiModelProperty(value = "LOSS产生PACKING注记(N.不产生 Y.产生)")
	private java.lang.String lossPackRmk;
	/**LOSS单独装箱注记(N.不单独装/Y.单独装)*/
	@Excel(name = "LOSS单独装箱注记(N.不单独装/Y.单独装)", width = 15)
    @ApiModelProperty(value = "LOSS单独装箱注记(N.不单独装/Y.单独装)")
	private java.lang.String lossMixRmk;
}
