package org.jeecg.modules.finance.entity;

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
 * @Description: 应收单表头
 * @Author: jeecg-boot
 * @Date:   2020-08-28 22:42:16
 * @Version: V1.0
 */
@Data
@TableName("t_ar_receivable")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="t_ar_receivable对象", description="应收单表头")
public class TArReceivable {
    
	/**factNo*/
	@Excel(name = "factNo", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**fid*/
	@Excel(name = "fid", width = 15)
    @ApiModelProperty(value = "fid")
	private java.lang.Integer fid;
	/**fbilltypeid*/
	@Excel(name = "fbilltypeid", width = 15)
    @ApiModelProperty(value = "fbilltypeid")
	private java.lang.String fbilltypeid;
	/**fbillno*/
	@Excel(name = "fbillno", width = 15)
    @ApiModelProperty(value = "fbillno")
	private java.lang.String fbillno;
	/**fbusinesstype*/
	@Excel(name = "fbusinesstype", width = 15)
    @ApiModelProperty(value = "fbusinesstype")
	private java.lang.String fbusinesstype;
	/**fdate*/
	@Excel(name = "fdate", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "fdate")
	private java.util.Date fdate;
	/**fenddate*/
	@Excel(name = "fenddate", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "fenddate")
	private java.util.Date fenddate;
	/**fcustomerid*/
	@Excel(name = "fcustomerid", width = 15)
    @ApiModelProperty(value = "fcustomerid")
	private java.lang.Integer fcustomerid;
	/**fcurrencyid*/
	@Excel(name = "fcurrencyid", width = 15)
    @ApiModelProperty(value = "fcurrencyid")
	private java.lang.Integer fcurrencyid;
	/**fsettleorgid*/
	@Excel(name = "fsettleorgid", width = 15)
    @ApiModelProperty(value = "fsettleorgid")
	private java.lang.Integer fsettleorgid;
	/**fsaleorgid*/
	@Excel(name = "fsaleorgid", width = 15)
    @ApiModelProperty(value = "fsaleorgid")
	private java.lang.Integer fsaleorgid;
	/**fownertype*/
	@Excel(name = "fownertype", width = 15)
    @ApiModelProperty(value = "fownertype")
	private java.lang.String fownertype;
	/**fownerid*/
	@Excel(name = "fownerid", width = 15)
    @ApiModelProperty(value = "fownerid")
	private java.lang.Integer fownerid;
	/**fallamountfor*/
	@Excel(name = "fallamountfor", width = 15)
    @ApiModelProperty(value = "fallamountfor")
	private java.math.BigDecimal fallamountfor;
	/**fwrittenoffstatus*/
	@Excel(name = "fwrittenoffstatus", width = 15)
    @ApiModelProperty(value = "fwrittenoffstatus")
	private java.lang.String fwrittenoffstatus;
	/**fopenstatus*/
	@Excel(name = "fopenstatus", width = 15)
    @ApiModelProperty(value = "fopenstatus")
	private java.lang.String fopenstatus;
	/**fwrittenoffamountfor*/
	@Excel(name = "fwrittenoffamountfor", width = 15)
    @ApiModelProperty(value = "fwrittenoffamountfor")
	private java.math.BigDecimal fwrittenoffamountfor;
	/**fnotwrittenoffamountfor*/
	@Excel(name = "fnotwrittenoffamountfor", width = 15)
    @ApiModelProperty(value = "fnotwrittenoffamountfor")
	private java.math.BigDecimal fnotwrittenoffamountfor;
	/**frelatehadpayamount*/
	@Excel(name = "frelatehadpayamount", width = 15)
    @ApiModelProperty(value = "frelatehadpayamount")
	private java.math.BigDecimal frelatehadpayamount;
	/**fwrittenoffamount*/
	@Excel(name = "fwrittenoffamount", width = 15)
    @ApiModelProperty(value = "fwrittenoffamount")
	private java.math.BigDecimal fwrittenoffamount;
	/**fnotwrittenoffamount*/
	@Excel(name = "fnotwrittenoffamount", width = 15)
    @ApiModelProperty(value = "fnotwrittenoffamount")
	private java.math.BigDecimal fnotwrittenoffamount;
	/**fdocumentstatus*/
	@Excel(name = "fdocumentstatus", width = 15)
    @ApiModelProperty(value = "fdocumentstatus")
	private java.lang.String fdocumentstatus;
	/**fcreatorid*/
	@Excel(name = "fcreatorid", width = 15)
    @ApiModelProperty(value = "fcreatorid")
	private java.lang.Integer fcreatorid;
	/**fcreatedate*/
	@Excel(name = "fcreatedate", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "fcreatedate")
	private java.util.Date fcreatedate;
	/**fmodifierid*/
	@Excel(name = "fmodifierid", width = 15)
    @ApiModelProperty(value = "fmodifierid")
	private java.lang.Integer fmodifierid;
	/**fmodifydate*/
	@Excel(name = "fmodifydate", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "fmodifydate")
	private java.util.Date fmodifydate;
	/**faccountsystem*/
	@Excel(name = "faccountsystem", width = 15)
    @ApiModelProperty(value = "faccountsystem")
	private java.lang.Integer faccountsystem;
	/**fistax*/
	@Excel(name = "fistax", width = 15)
    @ApiModelProperty(value = "fistax")
	private java.lang.String fistax;
	/**fbyverify*/
	@Excel(name = "fbyverify", width = 15)
    @ApiModelProperty(value = "fbyverify")
	private java.lang.String fbyverify;
	/**fcancelstatus*/
	@Excel(name = "fcancelstatus", width = 15)
    @ApiModelProperty(value = "fcancelstatus")
	private java.lang.String fcancelstatus;
	/**fcancellerid*/
	@Excel(name = "fcancellerid", width = 15)
    @ApiModelProperty(value = "fcancellerid")
	private java.lang.Integer fcancellerid;
	/**fcanceldate*/
	@Excel(name = "fcanceldate", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "fcanceldate")
	private java.util.Date fcanceldate;
	/**fapproverid*/
	@Excel(name = "fapproverid", width = 15)
    @ApiModelProperty(value = "fapproverid")
	private java.lang.Integer fapproverid;
	/**fapprovedate*/
	@Excel(name = "fapprovedate", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "fapprovedate")
	private java.util.Date fapprovedate;
	/**fpayconditon*/
	@Excel(name = "fpayconditon", width = 15)
    @ApiModelProperty(value = "fpayconditon")
	private java.lang.Integer fpayconditon;
	/**fsaledeptid*/
	@Excel(name = "fsaledeptid", width = 15)
    @ApiModelProperty(value = "fsaledeptid")
	private java.lang.Integer fsaledeptid;
	/**fsalegroupid*/
	@Excel(name = "fsalegroupid", width = 15)
    @ApiModelProperty(value = "fsalegroupid")
	private java.lang.Integer fsalegroupid;
	/**fsaleerid*/
	@Excel(name = "fsaleerid", width = 15)
    @ApiModelProperty(value = "fsaleerid")
	private java.lang.Integer fsaleerid;
	/**fcreditcheckresult*/
	@Excel(name = "fcreditcheckresult", width = 15)
    @ApiModelProperty(value = "fcreditcheckresult")
	private java.lang.String fcreditcheckresult;
	/**fisinit*/
	@Excel(name = "fisinit", width = 15)
    @ApiModelProperty(value = "fisinit")
	private java.lang.String fisinit;
	/**fisretail*/
	@Excel(name = "fisretail", width = 15)
    @ApiModelProperty(value = "fisretail")
	private java.lang.String fisretail;
	/**fcashsale*/
	@Excel(name = "fcashsale", width = 15)
    @ApiModelProperty(value = "fcashsale")
	private java.lang.String fcashsale;
	/**fsalesbustype*/
	@Excel(name = "fsalesbustype", width = 15)
    @ApiModelProperty(value = "fsalesbustype")
	private java.lang.String fsalesbustype;
	/**fpayorgid*/
	@Excel(name = "fpayorgid", width = 15)
    @ApiModelProperty(value = "fpayorgid")
	private java.lang.Integer fpayorgid;
	/**fmatchmethodid*/
	@Excel(name = "fmatchmethodid", width = 15)
    @ApiModelProperty(value = "fmatchmethodid")
	private java.lang.Integer fmatchmethodid;
	/**fispriceexcludetax*/
	@Excel(name = "fispriceexcludetax", width = 15)
    @ApiModelProperty(value = "fispriceexcludetax")
	private java.lang.String fispriceexcludetax;
	/**fremark*/
	@Excel(name = "fremark", width = 15)
    @ApiModelProperty(value = "fremark")
	private java.lang.String fremark;
	/**fsetaccounttype*/
	@Excel(name = "fsetaccounttype", width = 15)
    @ApiModelProperty(value = "fsetaccounttype")
	private java.lang.String fsetaccounttype;
	/**fishookmatch*/
	@Excel(name = "fishookmatch", width = 15)
    @ApiModelProperty(value = "fishookmatch")
	private java.lang.String fishookmatch;
	/**fiswriteoff*/
	@Excel(name = "fiswriteoff", width = 15)
    @ApiModelProperty(value = "fiswriteoff")
	private java.lang.String fiswriteoff;
	/**fisinvoicearlier*/
	@Excel(name = "fisinvoicearlier", width = 15)
    @ApiModelProperty(value = "fisinvoicearlier")
	private java.lang.String fisinvoicearlier;
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private java.lang.String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private java.lang.String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
}
