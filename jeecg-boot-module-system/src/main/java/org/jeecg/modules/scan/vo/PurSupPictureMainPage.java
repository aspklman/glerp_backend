package org.jeecg.modules.scan.vo;

import java.util.List;
import org.jeecg.modules.scan.entity.PurSupPictureMain;
import org.jeecg.modules.scan.entity.PurSupPictureDetail;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 主表-SKU基础资料表
 * @Author: jeecg-boot
 * @Date:   2020-02-25 09:08:44
 * @Version: V1.0
 */
@Data
public class PurSupPictureMainPage {
	
	/**公司编号*/
  	@Excel(name = "公司编号", width = 15)
	private java.lang.String factNo;
	/**SKU*/
  	@Excel(name = "SKU", width = 15)
	private java.lang.String sku;
	/**SPU*/
  	@Excel(name = "SPU", width = 15)
	private java.lang.String spu;
	/**EAN*/
  	@Excel(name = "EAN", width = 15)
	private java.lang.String ean;
	/**质检标准*/
  	@Excel(name = "质检标准", width = 15)
	private java.lang.String qualityInspectStd;
	/**所属分类类目*/
  	@Excel(name = "所属分类类目", width = 15)
	private java.lang.String classification;
	/**开发序列号*/
  	@Excel(name = "开发序列号", width = 15)
	private java.lang.String developSn;
	/**开发链接*/
  	@Excel(name = "开发链接", width = 15)
	private java.lang.String developLink;
	/**状态*/
  	@Excel(name = "状态", width = 15)
	private java.lang.String status;
	/**SKU采购名称*/
  	@Excel(name = "SKU采购名称", width = 15)
	private java.lang.String skuPurchaseNm;
	/**英文名称*/
  	@Excel(name = "英文名称", width = 15)
	private java.lang.String engNm;
	/**速卖通英文名称*/
  	@Excel(name = "速卖通英文名称", width = 15)
	private java.lang.String engNmSmt;
	/**速卖通最佳LISTING*/
  	@Excel(name = "速卖通最佳LISTING", width = 15)
	private java.lang.String bestListingSmt;
	/**EBAY最佳LISTING*/
  	@Excel(name = "EBAY最佳LISTING", width = 15)
	private java.lang.String bestListingEbay;
	/**长(自带包装)*/
  	@Excel(name = "长(自带包装)", width = 15)
	private java.math.BigDecimal netLength;
	/**宽(自带包装)*/
  	@Excel(name = "宽(自带包装)", width = 15)
	private java.math.BigDecimal netWidth;
	/**高(自带包装)*/
  	@Excel(name = "高(自带包装)", width = 15)
	private java.math.BigDecimal netHeight;
	/**长(打包后)*/
  	@Excel(name = "长(打包后)", width = 15)
	private java.math.BigDecimal grossLength;
	/**宽(打包后)*/
  	@Excel(name = "宽(打包后)", width = 15)
	private java.math.BigDecimal grossWidth;
	/**高(打包后)*/
  	@Excel(name = "高(打包后)", width = 15)
	private java.math.BigDecimal grossHeight;
	/**SKU物流属性*/
  	@Excel(name = "SKU物流属性", width = 15)
	private java.lang.String wlAttribute;
	/**海关编码*/
  	@Excel(name = "海关编码", width = 15)
	private java.lang.String customsCode;
	/**税率*/
  	@Excel(name = "税率", width = 15)
	private java.math.BigDecimal taxRate;
	/**参考价*/
  	@Excel(name = "参考价", width = 15)
	private java.math.BigDecimal refPrice;
	/**重量(自带包装)*/
  	@Excel(name = "重量(自带包装)", width = 15)
	private java.math.BigDecimal netWeight;
	/**重量(打包后)*/
  	@Excel(name = "重量(打包后)", width = 15)
	private java.math.BigDecimal grossWeight;
	/**储位*/
  	@Excel(name = "储位", width = 15)
	private java.lang.String storageArea;
	/**采购开发*/
  	@Excel(name = "采购开发", width = 15)
	private java.lang.String purchaseDevelopEmp;
	/**订货员*/
  	@Excel(name = "订货员", width = 15)
	private java.lang.String orderGoodsEmp;
	/**订货主管*/
  	@Excel(name = "订货主管", width = 15)
	private java.lang.String orderGoodsDirector;
	/**计划员*/
  	@Excel(name = "计划员", width = 15)
	private java.lang.String planner;
	/**备注*/
  	@Excel(name = "备注", width = 15)
	private java.lang.String remarks;
	/**关键词*/
  	@Excel(name = "关键词", width = 15)
	private java.lang.String keywords;
	/**容量(液体或电池类产品)*/
  	@Excel(name = "容量(液体或电池类产品)", width = 15)
	private java.math.BigDecimal capacitys;
	/**产品属性*/
  	@Excel(name = "产品属性", width = 15)
	private java.lang.String prodAttribute;
	/**产品多属性信息*/
  	@Excel(name = "产品多属性信息", width = 15)
	private java.lang.String prodAttributeInfo;
	/**产品信息*/
  	@Excel(name = "产品信息", width = 15)
	private java.lang.String prodInfo;
	/**合规图*/
  	@Excel(name = "合规图", width = 15)
	private java.lang.String compliancePicture;
	/**合规审核结果*/
  	@Excel(name = "合规审核结果", width = 15)
	private java.lang.String complianceAuditResult;
	/**风险等级*/
  	@Excel(name = "风险等级", width = 15)
	private java.lang.String riskLevel;
	/**不可上架平台站点*/
  	@Excel(name = "不可上架平台站点", width = 15)
	private java.lang.String noUploadSite;
	/**制式*/
  	@Excel(name = "制式", width = 15)
	private java.lang.String form;
	/**型号*/
  	@Excel(name = "型号", width = 15)
	private java.lang.String modelNo;
	/**产品说明书*/
  	@Excel(name = "产品说明书", width = 15)
	private java.lang.String prodInstructions;
	/**执行标准*/
  	@Excel(name = "执行标准", width = 15)
	private java.lang.String execStandard;
	/**摄影师*/
  	@Excel(name = "摄影师", width = 15)
	private java.lang.String photographer;
	/**图片美工*/
  	@Excel(name = "图片美工", width = 15)
	private java.lang.String photoProcesser;
	/**英文编辑*/
  	@Excel(name = "英文编辑", width = 15)
	private java.lang.String engEditor;
	/**效果跟进员*/
  	@Excel(name = "效果跟进员", width = 15)
	private java.lang.String effectFollower;
	/**序号*/
  	@Excel(name = "序号", width = 15)
	private java.lang.Integer sN;
	/**批号*/
  	@Excel(name = "批号", width = 15)
	private java.lang.Integer lotNo;
	/**SKU基础资料ID*/
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
	
	@ExcelCollection(name="子表-SKU图片表")
	private List<PurSupPictureDetail> purSupPictureDetailList;
	
}
