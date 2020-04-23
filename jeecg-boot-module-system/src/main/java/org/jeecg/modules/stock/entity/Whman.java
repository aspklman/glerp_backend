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
 * @Description: 仓管员表
 * @Author: jeecg-boot
 * @Date:   2019-12-05 17:43:54
 * @Version: V1.0
 */
@Data
@TableName("whman")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="whman对象", description="仓管员表")
public class Whman {
    
	/**仓管员ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "仓管员ID")
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
	/**仓管员编号*/
	@Excel(name = "仓管员编号", width = 15)
    @ApiModelProperty(value = "仓管员编号")
	private java.lang.String whmanNo;
	/**仓管员名称*/
	@Excel(name = "仓管员名称", width = 15)
    @ApiModelProperty(value = "仓管员名称")
	private java.lang.String whmanNm;
	/**仓管员工号*/
	@Excel(name = "仓管员工号", width = 15)
    @ApiModelProperty(value = "仓管员工号")
	private java.lang.String whmanGh;
	/**进厂日期*/
	@Excel(name = "进厂日期", width = 15)
    @ApiModelProperty(value = "进厂日期")
	private java.lang.String factInDate;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private java.lang.String telNum;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
	/**停用注记*/
	@Excel(name = "停用注记", width = 15)
    @ApiModelProperty(value = "停用注记")
	@Dict(dicCode="yn_status")
	private java.lang.String stopRmk;
}
