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
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 生产班别表
 * @Author: jeecg-boot
 * @Date:   2019-12-10 09:49:03
 * @Version: V1.0
 */
@Data
@TableName("stgteam")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="stgteam对象", description="生产班别表")
public class Stgteam {
    
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**班别编号*/
	@Excel(name = "班别编号", width = 15)
    @ApiModelProperty(value = "班别编号")
	private java.lang.String teamNo;
	/**班别名称*/
	@Excel(name = "班别名称", width = 15)
    @ApiModelProperty(value = "班别名称")
	private java.lang.String teamNm;
	/**派工阶段*/
	@Excel(name = "派工阶段", width = 15)
    @ApiModelProperty(value = "派工阶段")
	private java.lang.String deptNo;
	/**产量阶段*/
	@Excel(name = "产量阶段", width = 15)
    @ApiModelProperty(value = "产量阶段")
	private java.lang.String stgNo;
	/**生产组别*/
	@Excel(name = "生产组别", width = 15)
    @ApiModelProperty(value = "生产组别")
	private java.lang.String secNo;
	/**归属多个组别*/
	@Excel(name = "归属多个组别", width = 15)
    @ApiModelProperty(value = "归属多个组别")
	private java.lang.String secNos;
	/**锁定注记*/
	@Excel(name = "锁定注记", width = 15)
    @ApiModelProperty(value = "锁定注记")
	@Dict(dicCode="lock_rmk_yn")
	private java.lang.String lockRmk;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String rmk;
	/**生产班别ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "生产班别ID")
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
