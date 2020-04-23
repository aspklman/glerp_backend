package org.jeecg.modules.product.entity;

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
 * @Description: 派工阶段表
 * @Author: jeecg-boot
 * @Date:   2019-12-16 09:56:14
 * @Version: V1.0
 */
@Data
@TableName("deptm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="deptm对象", description="派工阶段表")
public class Deptm {
    
	/**阶段编号*/
	@Excel(name = "阶段编号", width = 15)
    @ApiModelProperty(value = "阶段编号")
	private java.lang.String stgNo;
	/**派工阶段ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "派工阶段ID")
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
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**派工阶段编号*/
	@Excel(name = "派工阶段编号", width = 15)
    @ApiModelProperty(value = "派工阶段编号")
	private java.lang.String deptNo;
	/**派工阶段说明*/
	@Excel(name = "派工阶段说明", width = 15)
    @ApiModelProperty(value = "派工阶段说明")
	private java.lang.String deptNm;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;
}
