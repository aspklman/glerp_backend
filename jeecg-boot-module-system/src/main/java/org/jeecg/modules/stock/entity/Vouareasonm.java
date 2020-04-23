package org.jeecg.modules.stock.entity;

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
 * @Description: 领料原因表
 * @Author: jeecg-boot
 * @Date:   2019-12-06 10:37:33
 * @Version: V1.0
 */
@Data
@TableName("vouareasonm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="vouareasonm对象", description="领料原因表")
public class Vouareasonm {
    
	/**领料原因ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "领料原因ID")
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
	/**领料注记*/
	@Excel(name = "领料注记", width = 15)
    @ApiModelProperty(value = "领料注记")
	@Dict(dicCode="voua_rmk")
	private java.lang.String vouaRmk;
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**原因编号*/
	@Excel(name = "原因编号", width = 15)
    @ApiModelProperty(value = "原因编号")
	private java.lang.String vouareasonNo;
	/**原因名称*/
	@Excel(name = "原因名称", width = 15)
    @ApiModelProperty(value = "原因名称")
	private java.lang.String vouareasonNm;
	/**原因说明*/
	@Excel(name = "原因说明", width = 15)
    @ApiModelProperty(value = "原因说明")
	private java.lang.String vouareasonDesc;
	/**TRANACC_NO*/
	@Excel(name = "TRANACC_NO", width = 15)
    @ApiModelProperty(value = "TRANACC_NO")
	private java.lang.String tranaccNo;
	/**NEWACC_NM*/
	@Excel(name = "NEWACC_NM", width = 15)
    @ApiModelProperty(value = "NEWACC_NM")
	private java.lang.String newaccNm;
	/**OLDACC_NM*/
	@Excel(name = "OLDACC_NM", width = 15)
    @ApiModelProperty(value = "OLDACC_NM")
	private java.lang.String oldaccNm;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
}
