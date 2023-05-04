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
 * @Description: 资产异动表
 * @Author: jeecg-boot
 * @Date:   2023-04-27 17:25:27
 * @Version: V1.0
 */
@Data
@TableName("asset_change")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="asset_change对象", description="资产异动表")
public class AssetChange {
    
	/**ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**异动单号*/
	@Excel(name = "异动单号", width = 15)
    @ApiModelProperty(value = "异动单号")
	private java.lang.String changeNo;
	/**异动日期*/
	@Excel(name = "异动日期", width = 15)
    @ApiModelProperty(value = "异动日期")
	private java.lang.String changeDt;
	/**资产编号*/
	@Excel(name = "资产编号", width = 15)
	@ApiModelProperty(value = "资产编号")
	private java.lang.String assetNo;
	/**原使用状况*/
	@Excel(name = "原使用状况", width = 15)
    @ApiModelProperty(value = "原使用状况")
	@Dict(dicCode = "asset_use_status")
	private java.lang.String stateOld;
	/**原厂别*/
	@Excel(name = "原厂别", width = 15)
    @ApiModelProperty(value = "原厂别")
	private java.lang.String factOld;
	/**原绩效中心*/
	@Excel(name = "原绩效中心", width = 15)
    @ApiModelProperty(value = "原绩效中心")
	private java.lang.String factCentreOld;
	/**原使用厂别*/
	@Excel(name = "原使用厂别", width = 15)
	@ApiModelProperty(value = "原使用厂别")
	@Dict(dicCode = "asset_fact_location")
	private java.lang.String factLocationOld;
	/**原使用部门*/
	@Excel(name = "原使用部门", width = 15)
    @ApiModelProperty(value = "原使用部门")
	private java.lang.String deptNoOld;
	/**原存放位置*/
	@Excel(name = "原存放位置", width = 15)
    @ApiModelProperty(value = "原存放位置")
	private java.lang.String locationOld;
	/**原保管人*/
	@Excel(name = "原保管人", width = 15)
    @ApiModelProperty(value = "原保管人")
	private java.lang.String custodianOld;
	/**原管理经理*/
	@Excel(name = "原管理经理", width = 15)
    @ApiModelProperty(value = "原管理经理")
	private java.lang.String custodianManagerOld;
	/**原使用人*/
	@Excel(name = "原使用人", width = 15)
    @ApiModelProperty(value = "原使用人")
	private java.lang.String custodianUserOld;
	/**原存放楼层*/
	@Excel(name = "原存放楼层", width = 15)
    @ApiModelProperty(value = "原存放楼层")
	private java.lang.String locationFlOld;
	/**原存放区域*/
	@Excel(name = "原存放区域", width = 15)
    @ApiModelProperty(value = "原存放区域")
	private java.lang.String locationDlOld;
	/**原存放厂区*/
	@Excel(name = "原存放厂区", width = 15)
    @ApiModelProperty(value = "原存放厂区")
	private java.lang.String locationFactOld;
	/**现使用状况*/
	@Excel(name = "现使用状况", width = 15)
    @ApiModelProperty(value = "现使用状况")
	@Dict(dicCode = "asset_use_status")
	private java.lang.String state;
	/**现厂别*/
	@Excel(name = "现厂别", width = 15)
    @ApiModelProperty(value = "现厂别")
	private java.lang.String fact;
	/**现绩效中心*/
	@Excel(name = "现绩效中心", width = 15)
    @ApiModelProperty(value = "现绩效中心")
	private java.lang.String factCentre;
	/**现使用厂别*/
	@Excel(name = "现使用厂别", width = 15)
	@ApiModelProperty(value = "现使用厂别")
	@Dict(dicCode = "asset_fact_location")
	private java.lang.String factLocation;
	/**现使用部门*/
	@Excel(name = "现使用部门", width = 15)
    @ApiModelProperty(value = "现使用部门")
	private java.lang.String deptNo;
	/**现存放位置*/
	@Excel(name = "现存放位置", width = 15)
    @ApiModelProperty(value = "现存放位置")
	private java.lang.String location;
	/**现保管人*/
	@Excel(name = "现保管人", width = 15)
    @ApiModelProperty(value = "现保管人")
	private java.lang.String custodian;
	/**现管理经理*/
	@Excel(name = "现管理经理", width = 15)
    @ApiModelProperty(value = "现管理经理")
	private java.lang.String custodianManager;
	/**现使用人*/
	@Excel(name = "现使用人", width = 15)
    @ApiModelProperty(value = "现使用人")
	private java.lang.String custodianUser;
	/**现存放楼层*/
	@Excel(name = "现存放楼层", width = 15)
    @ApiModelProperty(value = "现存放楼层")
	private java.lang.String locationFl;
	/**现存放区域*/
	@Excel(name = "现存放区域", width = 15)
    @ApiModelProperty(value = "现存放区域")
	private java.lang.String locationDl;
	/**现存放厂区*/
	@Excel(name = "现存放厂区", width = 15)
    @ApiModelProperty(value = "现存放厂区")
	private java.lang.String locationFact;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String notes;
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
