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
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 存放区域表
 * @Author: jeecg-boot
 * @Date:   2019-12-05 16:51:48
 * @Version: V1.0
 */
@Data
@TableName("locate")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="locate对象", description="存放区域表")
public class Locate {
    
	/**locakRmk*/
	@Excel(name = "locakRmk", width = 15)
    @ApiModelProperty(value = "locakRmk")
	private java.lang.String locakRmk;
	/**存放区ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "存放区ID")
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
	/**库别编号*/
	@Excel(name = "库别编号", width = 15)
    @ApiModelProperty(value = "库别编号")
	private java.lang.String stkNo;
	/**存放区编号*/
	@Excel(name = "存放区编号", width = 15)
    @ApiModelProperty(value = "存放区编号")
	private java.lang.String locNo;
	/**存放区说明*/
	@Excel(name = "存放区说明", width = 15)
    @ApiModelProperty(value = "存放区说明")
	private java.lang.String locDesc;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
	private java.lang.String dutyPer;
}
