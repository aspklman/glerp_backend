package org.jeecg.modules.scan.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 主表-SKU基础资料表
 * @Author: jeecg-boot
 * @Date:   2020-02-25 09:08:43
 * @Version: V1.0
 */
@Data
@TableName("pur_sup_basic_data")
public class PurSupPictureMain implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**公司编号*/
	private java.lang.String factNo;
	/**SKU*/
	private java.lang.String sku;
	/**SPU*/
	private java.lang.String spu;
	/**EAN*/
	private java.lang.String ean;
	/**质检标准*/
	private java.lang.String qualityInspectStd;
	/**所属分类类目*/
	private java.lang.String classification;
	/**开发序列号*/
	private java.lang.String developSn;
	/**开发链接*/
	private java.lang.String developLink;
	/**状态*/
	private java.lang.String status;
	/**SKU采购名称*/
	private java.lang.String skuPurchaseNm;
	/**英文名称*/
	private java.lang.String engNm;
	/**速卖通英文名称*/
	private java.lang.String engNmSmt;
	/**速卖通最佳LISTING*/
	private java.lang.String bestListingSmt;
	/**EBAY最佳LISTING*/
	private java.lang.String bestListingEbay;
	/**长(自带包装)*/
	private java.math.BigDecimal netLength;
	/**宽(自带包装)*/
	private java.math.BigDecimal netWidth;
	/**高(自带包装)*/
	private java.math.BigDecimal netHeight;
	/**长(打包后)*/
	private java.math.BigDecimal grossLength;
	/**宽(打包后)*/
	private java.math.BigDecimal grossWidth;
	/**高(打包后)*/
	private java.math.BigDecimal grossHeight;
	/**SKU物流属性*/
	private java.lang.String wlAttribute;
	/**海关编码*/
	private java.lang.String customsCode;
	/**税率*/
	private java.math.BigDecimal taxRate;
	/**参考价*/
	private java.math.BigDecimal refPrice;
	/**重量(自带包装)*/
	private java.math.BigDecimal netWeight;
	/**重量(打包后)*/
	private java.math.BigDecimal grossWeight;
	/**储位*/
	private java.lang.String storageArea;
	/**采购开发*/
	private java.lang.String purchaseDevelopEmp;
	/**订货员*/
	private java.lang.String orderGoodsEmp;
	/**订货主管*/
	private java.lang.String orderGoodsDirector;
	/**计划员*/
	private java.lang.String planner;
	/**备注*/
	private java.lang.String remarks;
	/**关键词*/
	private java.lang.String keywords;
	/**容量(液体或电池类产品)*/
	private java.math.BigDecimal capacitys;
	/**产品属性*/
	private java.lang.String prodAttribute;
	/**产品多属性信息*/
	private java.lang.String prodAttributeInfo;
	/**产品信息*/
	private java.lang.String prodInfo;
	/**合规图*/
	private java.lang.String compliancePicture;
	/**合规审核结果*/
	private java.lang.String complianceAuditResult;
	/**风险等级*/
	private java.lang.String riskLevel;
	/**不可上架平台站点*/
	private java.lang.String noUploadSite;
	/**制式*/
	private java.lang.String form;
	/**型号*/
	private java.lang.String modelNo;
	/**产品说明书*/
	private java.lang.String prodInstructions;
	/**执行标准*/
	private java.lang.String execStandard;
	/**摄影师*/
	private java.lang.String photographer;
	/**图片美工*/
	private java.lang.String photoProcesser;
	/**英文编辑*/
	private java.lang.String engEditor;
	/**效果跟进员*/
	private java.lang.String effectFollower;
	/**序号*/
	private java.lang.Integer sN;
	/**批号*/
	private java.lang.Integer lotNo;
	/**SKU基础资料ID*/
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
}
