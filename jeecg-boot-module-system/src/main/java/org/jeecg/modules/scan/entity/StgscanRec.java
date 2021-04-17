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
 * @Description: 成品入库扫描(卡洛驰)
 * @Author: jeecg-boot
 * @Date:   2021-03-11 17:12:21
 * @Version: V1.0
 */
@Data
@TableName("stgscan_rec")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="stgscan_rec对象", description="成品入库扫描(卡洛驰)")
public class StgscanRec {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**阶段编号*/
	@Excel(name = "阶段编号", width = 15)
    @ApiModelProperty(value = "阶段编号")
	private java.lang.String stgNo;
	/**条码编号*/
	@Excel(name = "条码编号", width = 15)
    @ApiModelProperty(value = "条码编号")
	private java.lang.String barNo;
	/**接收日期*/
	@Excel(name = "接收日期", width = 15)
    @ApiModelProperty(value = "接收日期")
	private java.lang.String recDate;
	/**接收时间*/
	@Excel(name = "接收时间", width = 15)
    @ApiModelProperty(value = "接收时间")
	private java.lang.String recTime;
	/**组别编号*/
	@Excel(name = "组别编号", width = 15)
    @ApiModelProperty(value = "组别编号")
	private java.lang.String secNo;
	/**入库单号*/
	@Excel(name = "入库单号", width = 15)
    @ApiModelProperty(value = "入库单号")
	private java.lang.String inNo;
	/**库别*/
	@Excel(name = "库别", width = 15)
    @ApiModelProperty(value = "库别")
	private java.lang.String stkNo;
	/**存放区编号*/
	@Excel(name = "存放区编号", width = 15)
    @ApiModelProperty(value = "存放区编号")
	private java.lang.String locateNo;
	/**电脑IP*/
	@Excel(name = "电脑IP", width = 15)
    @ApiModelProperty(value = "电脑IP")
	private java.lang.String scanIp;
	/**生产日期*/
	@Excel(name = "生产日期", width = 15)
    @ApiModelProperty(value = "生产日期")
	private java.lang.String proDate;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**生产厂别*/
	@Excel(name = "生产厂别", width = 15)
    @ApiModelProperty(value = "生产厂别")
	private java.lang.String proFact;
	/**产生入库单标记(0:未产生 1:已产生 2:待产生)*/
	@Excel(name = "产生入库单标记(0:未产生 1:已产生 2:待产生)", width = 15)
    @ApiModelProperty(value = "产生入库单标记(0:未产生 1:已产生 2:待产生)")
	private java.lang.String genInStockRmk;
	/**存放地点*/
	@Excel(name = "存放地点", width = 15)
    @ApiModelProperty(value = "存放地点")
	private java.lang.String locArea;
	/**locNoOut*/
	@Excel(name = "locNoOut", width = 15)
    @ApiModelProperty(value = "locNoOut")
	private java.lang.String locNoOut;
	/**locAreaOut*/
	@Excel(name = "locAreaOut", width = 15)
    @ApiModelProperty(value = "locAreaOut")
	private java.lang.String locAreaOut;
}
