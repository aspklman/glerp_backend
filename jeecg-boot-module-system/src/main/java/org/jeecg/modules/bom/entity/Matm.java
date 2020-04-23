package org.jeecg.modules.bom.entity;

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
 * @Description: 材料表
 * @Author: jeecg-boot
 * @Date:   2019-11-29 14:43:26
 * @Version: V1.0
 */
@Data
@TableName("matm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="matm对象", description="材料表")
public class Matm {
    
	/**材料ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "材料ID")
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
	/**材料编号*/
	@Excel(name = "材料编号", width = 15)
    @ApiModelProperty(value = "材料编号")
	private java.lang.String matNo;
	/**材料名称*/
	@Excel(name = "材料名称", width = 15)
    @ApiModelProperty(value = "材料名称")
	private java.lang.String matNm;
	/**材料英文名称*/
	@Excel(name = "材料英文名称", width = 15)
    @ApiModelProperty(value = "材料英文名称")
	private java.lang.String matEngNm;
	/**材料单位*/
	@Excel(name = "材料中文单位", width = 15)
    @ApiModelProperty(value = "材料中文单位")
	@Dict(dicCode="mat_cn_unit")
	private java.lang.String matUnit;
	/**材料英文单位*/
	@Excel(name = "材料英文单位", width = 15)
    @ApiModelProperty(value = "材料英文单位")
	@Dict(dicCode="mat_en_unit")
	private java.lang.String matEngUnit;
	/**材料颜色*/
	@Excel(name = "材料颜色", width = 15)
    @ApiModelProperty(value = "材料颜色")
	private java.lang.String matColor;
	/**英文颜色*/
	@Excel(name = "英文颜色", width = 15)
    @ApiModelProperty(value = "英文颜色")
	private java.lang.String matEngColor;
	/**采购政策*/
	@Excel(name = "采购政策", width = 15)
    @ApiModelProperty(value = "采购政策")
	@Dict(dicCode="pur_policy")
	private java.lang.String purPolicy;
	/**平均单价锁定*/
	@Excel(name = "平均单价锁定", width = 15)
    @ApiModelProperty(value = "平均单价锁定")
	private java.lang.String accNo;
	/**资材代号*/
	@Excel(name = "资材代号", width = 15)
    @ApiModelProperty(value = "资材代号")
	private java.lang.String accMatno;
	/**安全库存*/
	@Excel(name = "安全库存", width = 15)
    @ApiModelProperty(value = "安全库存")
	private java.lang.Integer safeStkQty;
	/**采购提前期*/
	@Excel(name = "采购提前期", width = 15)
    @ApiModelProperty(value = "采购提前期")
	private java.lang.Integer leadTime;
	/**托外注记*/
	@Excel(name = "托外注记", width = 15)
    @ApiModelProperty(value = "托外注记")
	@Dict(dicCode="yn_status")
	private java.lang.String preprdRmk;
	/**尺寸注记*/
	@Excel(name = "尺寸注记", width = 15)
    @ApiModelProperty(value = "尺寸注记")
	@Dict(dicCode="size_rmk")
	private java.lang.String sizeRmk;
	/**超交允收*/
	@Excel(name = "超交允收", width = 15)
    @ApiModelProperty(value = "超交允收")
	private java.lang.String overRcvRmk;
	/**商品编号*/
	@Excel(name = "商品编号", width = 15)
    @ApiModelProperty(value = "商品编号")
	private java.lang.String goodsNo;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private java.lang.String goodsSeq;
	/**单重*/
	@Excel(name = "单重", width = 15)
    @ApiModelProperty(value = "单重")
	private java.math.BigDecimal unitWeight;
	/**已订未交量*/
	@Excel(name = "已订未交量", width = 15)
    @ApiModelProperty(value = "已订未交量")
	private java.math.BigDecimal onOrderQty;
	/**已收未验量*/
	@Excel(name = "已收未验量", width = 15)
    @ApiModelProperty(value = "已收未验量")
	private java.math.BigDecimal unqcQty;
	/**最近进料日*/
	@Excel(name = "最近进料日", width = 15)
    @ApiModelProperty(value = "最近进料日")
	private java.lang.String lastInDate;
	/**最近出库日*/
	@Excel(name = "最近出库日", width = 15)
    @ApiModelProperty(value = "最近出库日")
	private java.lang.String lastOutDate;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
	/**锁定注记*/
	@Excel(name = "锁定注记", width = 15)
    @ApiModelProperty(value = "锁定注记")
	@Dict(dicCode="lock_status")
	private java.lang.String lockStatus;
	/**锁定日期*/
	@Excel(name = "锁定日期", width = 15)
    @ApiModelProperty(value = "锁定日期")
	private java.lang.String lockDate;
	/**换算单位*/
	@Excel(name = "换算单位", width = 15)
    @ApiModelProperty(value = "换算单位")
	private java.math.BigDecimal unitRate;
	/**共用注记*/
	@Excel(name = "共用注记", width = 15)
    @ApiModelProperty(value = "共用注记")
	private java.lang.String shareRmk;
	/**物性测试注记*/
	@Excel(name = "物性测试注记", width = 15)
    @ApiModelProperty(value = "物性测试注记")
	private java.lang.String matphyRmk;
	/**检验注记*/
	@Excel(name = "检验注记", width = 15)
    @ApiModelProperty(value = "检验注记")
	private java.lang.String sgsRmk;
	/**材料类别编号*/
	@Excel(name = "材料类别编号", width = 15)
    @ApiModelProperty(value = "材料类别编号")
	private java.lang.String matkindNo;
	/**材料简称*/
	@Excel(name = "材料简称", width = 15)
    @ApiModelProperty(value = "材料简称")
	private java.lang.String uMat;
	/**预购注记*/
	@Excel(name = "预购注记", width = 15)
    @ApiModelProperty(value = "预购注记")
	private java.lang.String apRmk;
	/**单重审核注记(Y/N)*/
	@Excel(name = "单重审核注记(Y/N)", width = 15)
    @ApiModelProperty(value = "单重审核注记(Y/N)")
	private java.lang.String wgtVerifyRmk;
	/**单重审核人*/
	@Excel(name = "单重审核人", width = 15)
    @ApiModelProperty(value = "单重审核人")
	private java.lang.String wgtVerifyUser;
	/**单重审核日*/
	@Excel(name = "单重审核日", width = 15)
    @ApiModelProperty(value = "单重审核日")
	private java.lang.String wgtVerifyDate;
	/**关务材料名*/
	@Excel(name = "关务材料名", width = 15)
    @ApiModelProperty(value = "关务材料名")
	private java.lang.String matNmGw;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15)
    @ApiModelProperty(value = "创建日期")
	private java.lang.String creatDate;
	/**前次收货日*/
	@Excel(name = "前次收货日", width = 15)
    @ApiModelProperty(value = "前次收货日")
	private java.lang.String lRecDate;
	/**送测进料批次*/
	@Excel(name = "送测进料批次", width = 15)
    @ApiModelProperty(value = "送测进料批次")
	private java.lang.Integer incomQty;
	/**盘点单价*/
	@Excel(name = "盘点单价", width = 15)
    @ApiModelProperty(value = "盘点单价")
	private java.math.BigDecimal avgUnitprice;
	/**保税注记1.保税 2.非保税*/
	@Excel(name = "保税注记1.保税 2.非保税", width = 15)
    @ApiModelProperty(value = "保税注记1.保税 2.非保税")
	@Dict(dicCode="tax_rmk")
	private java.lang.String taxRmk;
	/**IN_ACC_NO*/
	@Excel(name = "IN_ACC_NO", width = 15)
    @ApiModelProperty(value = "IN_ACC_NO")
	private java.lang.String inAccNo;
	/**材料材质属性*/
	@Excel(name = "材料材质属性", width = 15)
    @ApiModelProperty(value = "材料材质属性")
	private java.lang.String matSpec;
	/**loss比率*/
	@Excel(name = "loss比率", width = 15)
    @ApiModelProperty(value = "loss比率")
	private java.math.BigDecimal lossRate;
	/**币别*/
	@Excel(name = "币别", width = 15)
    @ApiModelProperty(value = "币别")
	private java.lang.String coinKind;
	/**最小用量*/
	@Excel(name = "最小用量", width = 15)
    @ApiModelProperty(value = "最小用量")
	private java.math.BigDecimal minLot;
	/**审核人*/
	@Excel(name = "审核人", width = 15)
    @ApiModelProperty(value = "审核人")
	private java.lang.String verifyUser;
	/**审核时间*/
	@Excel(name = "审核时间", width = 15)
    @ApiModelProperty(value = "审核时间")
	private java.lang.String verifyDt;
	/**去掉空格的名称*/
	@Excel(name = "去掉空格的名称", width = 15)
    @ApiModelProperty(value = "去掉空格的名称")
	private java.lang.String nmTrim;
	/**是否测试*/
	@Excel(name = "是否测试", width = 15)
    @ApiModelProperty(value = "是否测试")
	private java.lang.String istest;
}
