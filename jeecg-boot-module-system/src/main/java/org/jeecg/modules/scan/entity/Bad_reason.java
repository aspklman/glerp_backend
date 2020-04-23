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
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 不良原因(新材)表
 * @Author: jeecg-boot
 * @Date:   2019-12-09 09:02:55
 * @Version: V1.0
 */
@Data
@TableName("bad_reason")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="bad_reason对象", description="不良原因(新材)表")
public class Bad_reason {
    
	/**不良原因ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "不良原因ID")
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
	/**不良等级*/
	@Excel(name = "不良等级", width = 15)
    @ApiModelProperty(value = "不良等级")
	@Dict(dicCode="bad_level")
	private java.lang.String badLevel;
	/**原因编号*/
	@Excel(name = "原因编号", width = 15)
    @ApiModelProperty(value = "原因编号")
	private java.lang.String badNo;
	/**中文原因说明*/
	@Excel(name = "中文原因说明", width = 15)
    @ApiModelProperty(value = "中文原因说明")
	private java.lang.String badCause;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
	/**英文原因说明*/
	@Excel(name = "英文原因说明", width = 15)
    @ApiModelProperty(value = "英文原因说明")
	private java.lang.String badCauseEn;
}
