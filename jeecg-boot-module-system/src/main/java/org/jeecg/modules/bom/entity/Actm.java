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
 * @Description: 动作表
 * @Author: jeecg-boot
 * @Date:   2019-11-28 10:34:07
 * @Version: V1.0
 */
@Data
@TableName("actm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="actm对象", description="动作表")
public class Actm {
    
	/**动作ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "动作ID")
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
	/**动作编号*/
	@Excel(name = "动作编号", width = 15)
    @ApiModelProperty(value = "动作编号")
	private java.lang.String actNo;
	/**动作名称*/
	@Excel(name = "动作名称", width = 15)
    @ApiModelProperty(value = "动作名称")
	private java.lang.String actName;
	/**外包制程(Y_N)*/
	@Excel(name = "外包制程(Y_N)", width = 15)
    @ApiModelProperty(value = "外包制程(Y_N)")
	@Dict(dicCode="yn_status")
	private java.lang.String outworkRmk;
	/**使用工具说明*/
	@Excel(name = "使用工具说明", width = 15)
    @ApiModelProperty(value = "使用工具说明")
	private java.lang.String toolDesc;
}
