package org.jeecg.modules.quality.vo;

import java.util.List;
import org.jeecg.modules.quality.entity.SampleInspectReportM;
import org.jeecg.modules.quality.entity.SampleInspectReportD;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 验货报告主表
 * @Author: jeecg-boot
 * @Date:   2020-08-15 14:58:40
 * @Version: V1.0
 */
@Data
public class SampleInspectReportMPage {
	
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
	/**Pace编码*/
  	@Excel(name = "Pace编码", width = 15)
	private java.lang.String paceCode;
	/**模具名称和颜色*/
  	@Excel(name = "模具名称和颜色", width = 15)
	private java.lang.String modelColour;
	/**订单类型*/
  	@Excel(name = "订单类型", width = 15)
	private java.lang.String orderType;
	/**验货日期*/
  	@Excel(name = "验货日期", width = 15, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date inspectDate;
	/**验货结果*/
  	@Excel(name = "验货结果", width = 15)
	private java.lang.String inspectorDecision;
	/**验货报告主表ID*/
	private java.lang.String id;
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
	
	@ExcelCollection(name="验货报告子表")
	private List<SampleInspectReportD> sampleInspectReportDList;
	
}
