package org.jeecg.modules.asset.entity;

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
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 资产档案表
 * @Author: jeecg-boot
 * @Date:   2023-04-24 16:45:49
 * @Version: V1.0
 */
@Data
@TableName("asset_record")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="asset_record对象", description="资产档案表")
public class AssetRecord {
    
	/**ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**资产编号*/
	@Excel(name = "资产编号", width = 15)
    @ApiModelProperty(value = "资产编号")
	private java.lang.String assetNo;
	/**资产名称*/
	@Excel(name = "资产名称", width = 15)
    @ApiModelProperty(value = "资产名称")
	private java.lang.String assetName;
	/**单位*/
	@Excel(name = "单位", width = 15)
    @ApiModelProperty(value = "单位")
	private java.lang.String unit;
	/**资产明细编号*/
	@Excel(name = "资产明细编号", width = 15)
    @ApiModelProperty(value = "资产明细编号")
	private java.lang.String catalogId;
	/**大分类编号*/
	@Excel(name = "大分类编号", width = 15)
    @ApiModelProperty(value = "大分类编号")
	private java.lang.String kindNo;
	/**中分类编号*/
	@Excel(name = "中分类编号", width = 15)
	@ApiModelProperty(value = "中分类编号")
	private java.lang.String midKind;
	/**采购厂别*/
	@Excel(name = "采购厂别", width = 15)
    @ApiModelProperty(value = "采购厂别")
	private java.lang.String fact;
	/**绩效部门*/
	@Excel(name = "绩效部门", width = 15)
    @ApiModelProperty(value = "绩效部门")
	private java.lang.String factCentre;
	/**使用部门*/
	@Excel(name = "使用部门", width = 15)
    @ApiModelProperty(value = "使用部门")
	private java.lang.String deptNo;
	/**资产来源*/
	@Excel(name = "资产来源", width = 15)
    @ApiModelProperty(value = "资产来源")
	@Dict(dicCode="asset_source")
	private java.lang.String kind;
	/**验收日期*/
	@Excel(name = "验收日期", width = 15)
    @ApiModelProperty(value = "验收日期")
	private java.lang.String acceptDate;
	/**原始单价*/
	@Excel(name = "原始单价", width = 15)
    @ApiModelProperty(value = "原始单价")
	private java.math.BigDecimal originalPrice;
	/**入账单价*/
	@Excel(name = "入账单价", width = 15)
    @ApiModelProperty(value = "入账单价")
	private java.math.BigDecimal price;
	/**折旧率*/
	@Excel(name = "折旧率", width = 15)
    @ApiModelProperty(value = "折旧率")
	private java.math.BigDecimal rate;
	/**折旧月数*/
	@Excel(name = "折旧月数", width = 15)
    @ApiModelProperty(value = "折旧月数")
	private java.math.BigDecimal num;
	/**残值率*/
	@Excel(name = "残值率", width = 15)
    @ApiModelProperty(value = "残值率")
	private java.math.BigDecimal residual;
	/**存放位置*/
	@Excel(name = "存放位置", width = 15)
    @ApiModelProperty(value = "存放位置")
	private java.lang.String location;
	/**使用状况*/
	@Excel(name = "使用状况", width = 15)
    @ApiModelProperty(value = "使用状况")
	@Dict(dicCode="asset_use_status")
	private java.lang.String state;
	/**折旧方法*/
	@Excel(name = "折旧方法", width = 15)
    @ApiModelProperty(value = "折旧方法")
	@Dict(dicCode = "asset_depreciation")
	private java.lang.String depreciation;
	/**保管人*/
	@Excel(name = "保管人", width = 15)
    @ApiModelProperty(value = "保管人")
	private java.lang.String custodian;
	/**制造商*/
	@Excel(name = "制造商", width = 15)
    @ApiModelProperty(value = "制造商")
	private java.lang.String manufacturer;
	/**供应商*/
	@Excel(name = "供应商", width = 15)
    @ApiModelProperty(value = "供应商")
	private java.lang.String supplier;
	/**产地*/
	@Excel(name = "产地", width = 15)
    @ApiModelProperty(value = "产地")
	private java.lang.String originPlace;
	/**型号规格*/
	@Excel(name = "型号规格", width = 15)
    @ApiModelProperty(value = "型号规格")
	private java.lang.String specifications;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String note;
	/**币别*/
	@Excel(name = "币别", width = 15)
    @ApiModelProperty(value = "币别")
	@Dict(dicCode = "coin_kind")
	private java.lang.String hb;
	/**品牌*/
	@Excel(name = "品牌", width = 15)
    @ApiModelProperty(value = "品牌")
	private java.lang.String brand;
	/**是否监管*/
	@Excel(name = "是否监管", width = 15)
    @ApiModelProperty(value = "是否监管")
	@Dict(dicCode = "yn_status")
	private java.lang.String lb;
	/**是否核算*/
	@Excel(name = "是否核算", width = 15)
    @ApiModelProperty(value = "是否核算")
	@Dict(dicCode = "yn_status")
	private java.lang.String torf;
	/**配件清单*/
	@Excel(name = "配件清单", width = 15)
    @ApiModelProperty(value = "配件清单")
	private java.lang.String accessory;
	/**设备类型*/
	@Excel(name = "设备类型", width = 15)
    @ApiModelProperty(value = "设备类型")
	private java.lang.String typeNo;
	/**使用厂别*/
	@Excel(name = "使用厂别", width = 15)
    @ApiModelProperty(value = "使用厂别")
	@Dict(dicCode = "asset_fact_location")
	private java.lang.String factLocation;
	/**存放厂区*/
	@Excel(name = "存放厂区", width = 15)
    @ApiModelProperty(value = "存放厂区")
	@Dict(dicCode = "asset_location_fact")
	private java.lang.String locationFact;
	/**存放区域*/
	@Excel(name = "存放区域", width = 15)
    @ApiModelProperty(value = "存放区域")
	@Dict(dicCode = "asset_location_dl")
	private java.lang.String locationDl;
	/**存放楼层*/
	@Excel(name = "存放楼层", width = 15)
    @ApiModelProperty(value = "存放楼层")
	@Dict(dicCode = "asset_location_fl")
	private java.lang.String locationFl;
	/**管理经理*/
	@Excel(name = "管理经理", width = 15)
    @ApiModelProperty(value = "管理经理")
	private java.lang.String custodianManager;
	/**使用人*/
	@Excel(name = "使用人", width = 15)
    @ApiModelProperty(value = "使用人")
	private java.lang.String custodianUser;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
}
