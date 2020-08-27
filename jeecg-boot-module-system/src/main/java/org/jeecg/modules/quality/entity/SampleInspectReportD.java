package org.jeecg.modules.quality.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 验货报告子表
 * @Author: jeecg-boot
 * @Date:   2020-08-15 14:58:40
 * @Version: V1.0
 */
@Data
@TableName("sample_inspect_report_d")
public class SampleInspectReportD implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**厂区编号*/
    @Excel(name = "厂区编号", width = 15)
	private java.lang.String factNo;
	/**客户订单编号*/
    @Excel(name = "客户订单编号", width = 15)
	private java.lang.String custOdrNo;
	/**版本编号*/
    @Excel(name = "版本编号", width = 15)
	private java.lang.Integer versionNo;
	/**型体编号*/
    @Excel(name = "型体编号", width = 15)
	private java.lang.String styleShorten;
	/**主分类编号*/
    @Excel(name = "主分类编号", width = 15)
	private java.lang.String itemMainNo;
	/**中分类编号*/
    @Excel(name = "中分类编号", width = 15)
	private java.lang.String itemMediumNo;
	/**检查点编号*/
    @Excel(name = "检查点编号", width = 15)
	private java.lang.String checkPointsNo;
	/**次要*/
    @Excel(name = "次要", width = 15)
	private java.lang.Integer minor;
	/**主要*/
    @Excel(name = "主要", width = 15)
	private java.lang.Integer major;
	/**重要*/
    @Excel(name = "重要", width = 15)
	private java.lang.Integer critical;
	/**次要可用状态*/
    @Excel(name = "次要可用状态", width = 15)
	private java.lang.String minorEnable;
	/**主要可用状态*/
    @Excel(name = "主要可用状态", width = 15)
	private java.lang.String majorEnable;
	/**重要可用状态*/
    @Excel(name = "重要可用状态", width = 15)
	private java.lang.String criticalEnable;
	/**验货报告子表ID*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**验货报告主表ID*/
	private java.lang.String mainId;
	/**创建人*/
    @Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
    @Excel(name = "修改人", width = 15)
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
}
