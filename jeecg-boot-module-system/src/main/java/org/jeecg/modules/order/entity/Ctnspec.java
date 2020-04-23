package org.jeecg.modules.order.entity;

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
 * @Description: 外箱规格表
 * @Author: jeecg-boot
 * @Date:   2019-11-29 10:36:23
 * @Version: V1.0
 */
@Data
@TableName("ctnspec")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ctnspec对象", description="外箱规格表")
public class Ctnspec {
    
	/**外箱规格ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "外箱规格ID")
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
	/**外箱规格编号*/
	@Excel(name = "外箱规格编号", width = 15)
    @ApiModelProperty(value = "外箱规格编号")
	private java.lang.String ctnspecNo;
	/**材料编号*/
	@Excel(name = "材料编号", width = 15)
    @ApiModelProperty(value = "材料编号")
	private java.lang.String matNo;
	/**外箱(mm)长*/
	@Excel(name = "外箱(mm)长", width = 15)
    @ApiModelProperty(value = "外箱(mm)长")
	private java.math.BigDecimal ctnspecLen;
	/**外箱(mm)宽*/
	@Excel(name = "外箱(mm)宽", width = 15)
    @ApiModelProperty(value = "外箱(mm)宽")
	private java.math.BigDecimal ctnspecWid;
	/**外箱(mm)高*/
	@Excel(name = "外箱(mm)高", width = 15)
    @ApiModelProperty(value = "外箱(mm)高")
	private java.math.BigDecimal ctnspecHig;
	/**材积(CBM)*/
	@Excel(name = "材积(CBM)", width = 15)
    @ApiModelProperty(value = "材积(CBM)")
	private java.math.BigDecimal ctnMeasu;
	/**重量(KG)*/
	@Excel(name = "重量(KG)", width = 15)
    @ApiModelProperty(value = "重量(KG)")
	private java.math.BigDecimal ctnWgt;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
}
