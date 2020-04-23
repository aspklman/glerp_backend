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
 * @Description: 生产组别表
 * @Author: jeecg-boot
 * @Date:   2019-12-10 09:48:43
 * @Version: V1.0
 */
@Data
@TableName("sec")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sec对象", description="生产组别表")
public class Sec {
    
	/**生产组别ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "生产组别ID")
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
	/**组别编号*/
	@Excel(name = "组别编号", width = 15)
    @ApiModelProperty(value = "组别编号")
	private java.lang.String secNo;
	/**组别名称*/
	@Excel(name = "组别名称", width = 15)
    @ApiModelProperty(value = "组别名称")
	private java.lang.String secNm;
	/**组别归属制程字符串*/
	@Excel(name = "组别归属制程字符串", width = 15)
    @ApiModelProperty(value = "组别归属制程字符串")
	private java.lang.String cardNm;
	/**组别类型*/
	@Excel(name = "组别类型", width = 15)
    @ApiModelProperty(value = "组别类型")
	private java.lang.String secKind;
	/**制程编号*/
	@Excel(name = "制程编号", width = 15)
    @ApiModelProperty(value = "制程编号")
	private java.lang.String borNo;
	/**SEC_LEVEL*/
	@Excel(name = "SEC_LEVEL", width = 15)
    @ApiModelProperty(value = "SEC_LEVEL")
	private java.lang.String secLevel;
	/**归属厂别式组别*/
	@Excel(name = "归属厂别式组别", width = 15)
    @ApiModelProperty(value = "归属厂别式组别")
	private java.lang.String belSec;
	/**SAL_RMK*/
	@Excel(name = "SAL_RMK", width = 15)
    @ApiModelProperty(value = "SAL_RMK")
	private java.lang.String salRmk;
	/**AIM_RATE*/
	@Excel(name = "AIM_RATE", width = 15)
    @ApiModelProperty(value = "AIM_RATE")
	private java.math.BigDecimal aimRate;
	/**AWARD_RMK*/
	@Excel(name = "AWARD_RMK", width = 15)
    @ApiModelProperty(value = "AWARD_RMK")
	private java.lang.String awardRmk;
	/**ID_NO*/
	@Excel(name = "ID_NO", width = 15)
    @ApiModelProperty(value = "ID_NO")
	private java.lang.String idNo;
	/**BOARD_NO*/
	@Excel(name = "BOARD_NO", width = 15)
    @ApiModelProperty(value = "BOARD_NO")
	private java.lang.String boardNo;
	/**组别备注*/
	@Excel(name = "组别备注", width = 15)
    @ApiModelProperty(value = "组别备注")
	private java.lang.String secDesc;
	/**异动人*/
	@Excel(name = "异动人", width = 15)
    @ApiModelProperty(value = "异动人")
	private java.lang.String userNo;
	/**异动时间*/
	@Excel(name = "异动时间", width = 15)
    @ApiModelProperty(value = "异动时间")
	private java.lang.String modifyDt;
	/**归属派工阶段deptm表*/
	@Excel(name = "归属派工阶段deptm表", width = 15)
    @ApiModelProperty(value = "归属派工阶段deptm表")
	private java.lang.String deptNo;
	/**生产厂别*/
	@Excel(name = "生产厂别", width = 15)
    @ApiModelProperty(value = "生产厂别")
	@Dict(dicCode="pro_dept")
	private java.lang.String proFact;
	/**UP_SEC*/
	@Excel(name = "UP_SEC", width = 15)
    @ApiModelProperty(value = "UP_SEC")
	private java.lang.String upSec;
	/**锁定注记*/
	@Excel(name = "锁定注记", width = 15)
    @ApiModelProperty(value = "锁定注记")
	@Dict(dicCode="lock_rmk_yn")
	private java.lang.String lockRmk;
	/**锁定日期*/
	@Excel(name = "锁定日期", width = 15)
    @ApiModelProperty(value = "锁定日期")
	private java.lang.String lockDate;
	/**MANAGER_PER*/
	@Excel(name = "MANAGER_PER", width = 15)
    @ApiModelProperty(value = "MANAGER_PER")
	private java.lang.String managerPer;
	/**REL_TEL*/
	@Excel(name = "REL_TEL", width = 15)
    @ApiModelProperty(value = "REL_TEL")
	private java.lang.String relTel;
	/**PROXY_PER*/
	@Excel(name = "PROXY_PER", width = 15)
    @ApiModelProperty(value = "PROXY_PER")
	private java.lang.String proxyPer;
	/**ASS_PER*/
	@Excel(name = "ASS_PER", width = 15)
    @ApiModelProperty(value = "ASS_PER")
	private java.lang.String assPer;
	/**SECOND_PROXY*/
	@Excel(name = "SECOND_PROXY", width = 15)
    @ApiModelProperty(value = "SECOND_PROXY")
	private java.lang.String secondProxy;
	/**归属产量阶段stgm表*/
	@Excel(name = "归属产量阶段stgm表", width = 15)
    @ApiModelProperty(value = "归属产量阶段stgm表")
	private java.lang.String borDept;
	/**LINE_NO*/
	@Excel(name = "LINE_NO", width = 15)
    @ApiModelProperty(value = "LINE_NO")
	private java.lang.String lineNo;
	/**GROUP_NO*/
	@Excel(name = "GROUP_NO", width = 15)
    @ApiModelProperty(value = "GROUP_NO")
	private java.lang.String groupNo;
	/**TIMING_KIND*/
	@Excel(name = "TIMING_KIND", width = 15)
    @ApiModelProperty(value = "TIMING_KIND")
	private java.lang.String timingKind;
	/**派工生产厂别注记*/
	@Excel(name = "派工生产厂别注记", width = 15)
    @ApiModelProperty(value = "派工生产厂别注记")
	@Dict(dicCode="yn_status")
	private java.lang.String proType;
	/**阶段名称*/
	@Excel(name = "阶段名称", width = 15)
    @ApiModelProperty(value = "阶段名称")
	private java.lang.String proflNo;
	/**COUNT_ITEM*/
	@Excel(name = "COUNT_ITEM", width = 15)
    @ApiModelProperty(value = "COUNT_ITEM")
	private java.lang.String countItem;
	/**AWARD_SEC*/
	@Excel(name = "AWARD_SEC", width = 15)
    @ApiModelProperty(value = "AWARD_SEC")
	private java.lang.String awardSec;
	/**secSimpleNm*/
	@Excel(name = "secSimpleNm", width = 15)
    @ApiModelProperty(value = "secSimpleNm")
	private java.lang.String secSimpleNm;
	/**secId*/
	@Excel(name = "secId", width = 15)
    @ApiModelProperty(value = "secId")
	private java.lang.Integer secId;
}
