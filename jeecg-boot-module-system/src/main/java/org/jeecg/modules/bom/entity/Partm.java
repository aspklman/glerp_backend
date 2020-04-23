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
 * @Description: 部位表
 * @Author: jeecg-boot
 * @Date:   2019-07-29 11:33:12
 * @Version: V1.0
 */
@Data
@TableName("partm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="partm对象", description="部位表")
public class Partm {
    
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 4)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**部位编号*/
	@Excel(name = "部位编号", width = 15)
    @ApiModelProperty(value = "部位编号")
	private java.lang.String partNo;
	/**部位名称*/
	@Excel(name = "部位名称", width = 15)
    @ApiModelProperty(value = "部位名称")
	private java.lang.String partNm;
	/**部位种类*/
	@Excel(name = "部位种类", width = 15)
    @ApiModelProperty(value = "部位种类")
	@Dict(dicCode = "part_type")
	private java.lang.String partMode;
	/**主料注记编码*/
	@Excel(name = "主料注记编码", width = 15)
    @ApiModelProperty(value = "主料注记编码")
	@Dict(dicCode="yn_status")
	private java.lang.String sameRmk;
	/**部位英文名*/
	@Excel(name = "部位英文名", width = 15)
    @ApiModelProperty(value = "部位英文名")
	private java.lang.String partEngNm;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
	/**成本部位编号*/
	@Excel(name = "成本部位编号", width = 15)
    @ApiModelProperty(value = "成本部位编号")
	private java.lang.String cpartNo;
	/**成本部位名称*/
	@Excel(name = "成本部位名称", width = 15)
    @ApiModelProperty(value = "成本部位名称")
	private java.lang.String cpartNm;
	/**预计用量可修改*/
	@Excel(name = "预计用量可修改", width = 15)
    @ApiModelProperty(value = "预计用量可修改")
	private java.lang.String purplanRmk;
	/**依订单计算用量时用*/
	@Excel(name = "依订单计算用量时用", width = 15)
    @ApiModelProperty(value = "依订单计算用量时用")
	private java.lang.String odrqRmk;
	/**部位ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "部位ID")
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
}
