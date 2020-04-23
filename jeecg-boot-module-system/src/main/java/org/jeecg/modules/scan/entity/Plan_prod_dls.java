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
 * @Description: 成型目标产量(大罗沙)表
 * @Author: jeecg-boot
 * @Date:   2019-12-09 15:01:46
 * @Version: V1.0
 */
@Data
@TableName("plan_prod_dls")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="plan_prod_dls对象", description="成型目标产量(大罗沙)表")
public class Plan_prod_dls {
    
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**组别编号*/
	@Excel(name = "组别编号", width = 15)
    @ApiModelProperty(value = "组别编号")
	private java.lang.String secNo;
	/**生效日期*/
	@Excel(name = "生效日期", width = 15)
    @ApiModelProperty(value = "生效日期")
	private java.lang.String effectiveDate;
	/**目标产量*/
	@Excel(name = "目标产量", width = 15)
    @ApiModelProperty(value = "目标产量")
	private java.math.BigDecimal planQty;
	/**应到人数*/
	@Excel(name = "应到人数", width = 15)
    @ApiModelProperty(value = "应到人数")
	private java.lang.Integer headcount;
	/**目标直通率*/
	@Excel(name = "目标直通率", width = 15)
    @ApiModelProperty(value = "目标直通率")
	private java.lang.Integer fpyGoal;
	/**产线组长*/
	@Excel(name = "产线组长", width = 15)
    @ApiModelProperty(value = "产线组长")
	private java.lang.String lineLeader;
	/**产线厂长*/
	@Excel(name = "产线厂长", width = 15)
    @ApiModelProperty(value = "产线厂长")
	private java.lang.String productionSupervisor;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
	/**型体*/
	@Excel(name = "型体", width = 15)
    @ApiModelProperty(value = "型体")
	private java.lang.String style;
	/**型体描述*/
	@Excel(name = "型体描述", width = 15)
    @ApiModelProperty(value = "型体描述")
	private java.lang.String styleDescription;
	/**鞋子类型*/
	@Excel(name = "鞋子类型", width = 15)
    @ApiModelProperty(value = "鞋子类型")
	private java.lang.String shoeCategory;
	/**目标人均产量*/
	@Excel(name = "目标人均产量", width = 15)
    @ApiModelProperty(value = "目标人均产量")
	private java.math.BigDecimal pphGoal;
	/**实到人数*/
	@Excel(name = "实到人数", width = 15)
    @ApiModelProperty(value = "实到人数")
	private java.lang.Integer actualWorkers;
	/**直接人工*/
	@Excel(name = "直接人工", width = 15)
    @ApiModelProperty(value = "直接人工")
	private java.lang.Integer directLabor;
	/**间接人工*/
	@Excel(name = "间接人工", width = 15)
    @ApiModelProperty(value = "间接人工")
	private java.lang.Integer directLaborN;
	/**多能工人数*/
	@Excel(name = "多能工人数", width = 15)
    @ApiModelProperty(value = "多能工人数")
	private java.lang.Integer multiSkillWorkers;
	/**非多能工人数*/
	@Excel(name = "非多能工人数", width = 15)
    @ApiModelProperty(value = "非多能工人数")
	private java.lang.Integer multiSkillWorkersN;
	/**线长度*/
	@Excel(name = "线长度", width = 15)
    @ApiModelProperty(value = "线长度")
	private java.lang.Integer lineLength;
	/**节拍时间*/
	@Excel(name = "节拍时间", width = 15)
    @ApiModelProperty(value = "节拍时间")
	private java.lang.Integer tt;
	/**总周期时间*/
	@Excel(name = "总周期时间", width = 15)
    @ApiModelProperty(value = "总周期时间")
	private java.math.BigDecimal tct;
	/**MFG_LT*/
	@Excel(name = "MFG_LT", width = 15)
    @ApiModelProperty(value = "MFG_LT")
	private java.math.BigDecimal mfgLt;
	/**生日祝福语*/
	@Excel(name = "生日祝福语", width = 15)
    @ApiModelProperty(value = "生日祝福语")
	private java.lang.String blessing;
	/**目标产量达成率*/
	@Excel(name = "目标产量达成率", width = 15)
    @ApiModelProperty(value = "目标产量达成率")
	private java.lang.Integer achieveRate;
	/**目标B品数*/
	@Excel(name = "目标B品数", width = 15)
    @ApiModelProperty(value = "目标B品数")
	private java.lang.Integer bGrade;
	/**目标返修数*/
	@Excel(name = "目标返修数", width = 15)
    @ApiModelProperty(value = "目标返修数")
	private java.lang.Integer rework;
	/**成型目标产量ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "成型目标产量ID")
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
