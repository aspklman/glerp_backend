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
 * @Description: 退货原因表
 * @Author: jeecg-boot
 * @Date:   2019-12-06 09:34:02
 * @Version: V1.0
 */
@Data
@TableName("retrsn")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="retrsn对象", description="退货原因表")
public class Retrsn {
    
	/**退货原因ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "退货原因ID")
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
	/**FACT_NO*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**RETRSN_NO*/
	@Excel(name = "退货原因编号", width = 15)
    @ApiModelProperty(value = "退货原因编号")
	private java.lang.String retrsnNo;
	/**RETRSN_DESC*/
	@Excel(name = "退货原因描述", width = 15)
    @ApiModelProperty(value = "退货原因描述")
	private java.lang.String retrsnDesc;
}
