package org.jeecg.modules.scan.entity;

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
 * @Description: 成品中转扫描表
 * @Author: jeecg-boot
 * @Date:   2020-06-12 10:47:05
 * @Version: V1.0
 */
@Data
@TableName("stgscan_rec_transfer")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="stgscan_rec_transfer对象", description="成品中转扫描表")
public class StgscanRecTransfer {
    
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**阶段编号*/
	@Excel(name = "阶段编号", width = 15)
    @ApiModelProperty(value = "阶段编号")
	private java.lang.String stgNo;
	/**库别编号*/
	@Excel(name = "库别编号", width = 15)
    @ApiModelProperty(value = "库别编号")
	private java.lang.String stkNo;
	/**条码编号*/
	@Excel(name = "条码编号", width = 15)
    @ApiModelProperty(value = "条码编号")
	private java.lang.String barNo;
	/**扫描时间*/
	@Excel(name = "扫描时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "扫描时间")
	private java.util.Date scanTime;
	/**存放区域*/
	@Excel(name = "存放区域", width = 15)
    @ApiModelProperty(value = "存放区域")
	private java.lang.String locNo;
	/**存放地点*/
	@Excel(name = "存放地点", width = 15)
    @ApiModelProperty(value = "存放地点")
	private java.lang.String locArea;
	/**更新标记*/
	@Excel(name = "更新标记", width = 15)
    @ApiModelProperty(value = "更新标记")
	private java.lang.String updateRmk;
	/**成品中转扫描表ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "成品中转扫描表ID")
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
