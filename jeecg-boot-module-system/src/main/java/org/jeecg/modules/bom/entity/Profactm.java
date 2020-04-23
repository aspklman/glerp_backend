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
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 生产工厂表
 * @Author: jeecg-boot
 * @Date:   2019-11-28 14:00:19
 * @Version: V1.0
 */
@Data
@TableName("profactm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="profactm对象", description="生产工厂表")
public class Profactm {
    
	/**生产工厂ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "生产工厂ID")
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
	/**厂别*/
	@Excel(name = "厂别", width = 15)
    @ApiModelProperty(value = "厂别")
	private java.lang.String factNo;
	/**生产厂别编号*/
	@Excel(name = "生产厂别编号", width = 15)
    @ApiModelProperty(value = "生产厂别编号")
	private java.lang.String proFact;
	/**生产厂别名称*/
	@Excel(name = "生产厂别名称", width = 15)
    @ApiModelProperty(value = "生产厂别名称")
	private java.lang.String proFactNm;
	/**生产类别*/
	@Excel(name = "生产类别", width = 15)
    @ApiModelProperty(value = "生产类别")
	private java.lang.String proKind;
	/**FACT_NUM*/
	@Excel(name = "FACT_NUM", width = 15)
    @ApiModelProperty(value = "FACT_NUM")
	private java.lang.String factNum;
	/**PROD_KIND_NM*/
	@Excel(name = "PROD_KIND_NM", width = 15)
    @ApiModelProperty(value = "PROD_KIND_NM")
	private java.lang.String prodKindNm;
	/**排序编号*/
	@Excel(name = "排序编号", width = 15)
    @ApiModelProperty(value = "排序编号")
	private java.lang.String factSeq;
	/**生产类别串*/
	@Excel(name = "生产类别串", width = 15)
    @ApiModelProperty(value = "生产类别串")
	private java.lang.String proKinds;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private java.lang.String addr;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
	private java.lang.String tel;
	/**传真*/
	@Excel(name = "传真", width = 15)
    @ApiModelProperty(value = "传真")
	private java.lang.String fax;
	/**利润中心*/
	@Excel(name = "利润中心", width = 15)
    @ApiModelProperty(value = "利润中心")
	private java.lang.String belProfitCenter;
}
