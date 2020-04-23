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
 * @Description: 存放地点表
 * @Author: jeecg-boot
 * @Date:   2019-12-07 09:42:05
 * @Version: V1.0
 */
@Data
@TableName("areas")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="areas对象", description="存放地点表")
public class Areas {
    
	/**生产厂别*/
	@Excel(name = "生产厂别", width = 15)
    @ApiModelProperty(value = "生产厂别")
	@Dict(dicCode="pro_dept")
	private java.lang.String proFact;
	/**存放地点ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "存放地点ID")
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
	/**仓库编号*/
	@Excel(name = "仓库编号", width = 15)
    @ApiModelProperty(value = "仓库编号")
	private java.lang.String stkNo;
	/**区域编号*/
	@Excel(name = "区域编号", width = 15)
    @ApiModelProperty(value = "区域编号")
	private java.lang.String locNo;
	/**仓库栋别*/
	@Excel(name = "仓库栋别", width = 15)
    @ApiModelProperty(value = "仓库栋别")
	@Dict(dicCode="building_no")
	private java.lang.String buildingNo;
	/**仓库楼层*/
	@Excel(name = "仓库楼层", width = 15)
    @ApiModelProperty(value = "仓库楼层")
	@Dict(dicCode="floor_no")
	private java.lang.String floorNo;
	/**地点编号*/
	@Excel(name = "地点编号", width = 15)
    @ApiModelProperty(value = "地点编号")
	private java.lang.String locArea;
	/**地点名称*/
	@Excel(name = "地点名称", width = 15)
    @ApiModelProperty(value = "地点名称")
	private java.lang.String locAreaDesc;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String memo;
}
