package org.jeecg.modules.quality.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 验货报告主表
 * @Author: jeecg-boot
 * @Date:   2020-08-15 14:58:39
 * @Version: V1.0
 */
@Data
@TableName("sample_inspect_report_m")
public class SampleInspectReportM implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**厂区编号*/
	private java.lang.String factNo;
	/**客户订单编号*/
	private java.lang.String custOdrNo;
	/**版本编号*/
	private java.lang.Integer versionNo;
	/**型体编号*/
	private java.lang.String styleShorten;
	/**Pace编码*/
	private java.lang.String paceCode;
	/**模具名称和颜色*/
	private java.lang.String modelColour;
	/**订单类型*/
	@Dict(dicCode="odr_type_inspect")
	private java.lang.String orderType;
	/**订单数量*/
	private java.lang.String odrQty;
	/**验货日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date inspectDate;
	/**验货结果*/
	@Dict(dicCode="inspector_decision")
	private java.lang.String inspectorDecision;
	/**验货报告主表ID*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
	private java.lang.String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
}
