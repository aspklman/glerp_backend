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
 * @Description: 应收明细
 * @Author: jeecg-boot
 * @Date:   2020-08-28 17:01:44
 * @Version: V1.0
 */
@Data
@TableName("t_ar_receivableentry")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="t_ar_receivableentry对象", description="应收明细")
public class TArReceivableentry {
    
	/**factNo*/
	@Excel(name = "factNo", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**fentryid*/
	@Excel(name = "fentryid", width = 15)
    @ApiModelProperty(value = "fentryid")
	private java.lang.Integer fentryid;
	/**fid*/
	@Excel(name = "fid", width = 15)
    @ApiModelProperty(value = "fid")
	private java.lang.Integer fid;
	/**fseq*/
	@Excel(name = "fseq", width = 15)
    @ApiModelProperty(value = "fseq")
	private java.lang.Integer fseq;
	/**fsourcetype*/
	@Excel(name = "fsourcetype", width = 15)
    @ApiModelProperty(value = "fsourcetype")
	private java.lang.String fsourcetype;
	/**fsourcebillno*/
	@Excel(name = "fsourcebillno", width = 15)
    @ApiModelProperty(value = "fsourcebillno")
	private java.lang.String fsourcebillno;
	/**fsrcrowid*/
	@Excel(name = "fsrcrowid", width = 15)
    @ApiModelProperty(value = "fsrcrowid")
	private java.lang.Integer fsrcrowid;
	/**fmaterialid*/
	@Excel(name = "fmaterialid", width = 15)
    @ApiModelProperty(value = "fmaterialid")
	private java.lang.Integer fmaterialid;
	/**fprice*/
	@Excel(name = "fprice", width = 15)
    @ApiModelProperty(value = "fprice")
	private java.math.BigDecimal fprice;
	/**ftaxprice*/
	@Excel(name = "ftaxprice", width = 15)
    @ApiModelProperty(value = "ftaxprice")
	private java.math.BigDecimal ftaxprice;
	/**fpricecoefficient*/
	@Excel(name = "fpricecoefficient", width = 15)
    @ApiModelProperty(value = "fpricecoefficient")
	private java.math.BigDecimal fpricecoefficient;
	/**fpriceunitid*/
	@Excel(name = "fpriceunitid", width = 15)
    @ApiModelProperty(value = "fpriceunitid")
	private java.lang.Integer fpriceunitid;
	/**fpriceqty*/
	@Excel(name = "fpriceqty", width = 15)
    @ApiModelProperty(value = "fpriceqty")
	private java.math.BigDecimal fpriceqty;
	/**fentrydiscountrate*/
	@Excel(name = "fentrydiscountrate", width = 15)
    @ApiModelProperty(value = "fentrydiscountrate")
	private java.math.BigDecimal fentrydiscountrate;
	/**fdiscountamountfor*/
	@Excel(name = "fdiscountamountfor", width = 15)
    @ApiModelProperty(value = "fdiscountamountfor")
	private java.math.BigDecimal fdiscountamountfor;
	/**fentrytaxrate*/
	@Excel(name = "fentrytaxrate", width = 15)
    @ApiModelProperty(value = "fentrytaxrate")
	private java.math.BigDecimal fentrytaxrate;
	/**fpricewithtax*/
	@Excel(name = "fpricewithtax", width = 15)
    @ApiModelProperty(value = "fpricewithtax")
	private java.math.BigDecimal fpricewithtax;
	/**fnotaxamountfor*/
	@Excel(name = "fnotaxamountfor", width = 15)
    @ApiModelProperty(value = "fnotaxamountfor")
	private java.math.BigDecimal fnotaxamountfor;
	/**ftaxamountfor*/
	@Excel(name = "ftaxamountfor", width = 15)
    @ApiModelProperty(value = "ftaxamountfor")
	private java.math.BigDecimal ftaxamountfor;
	/**fallamountfor*/
	@Excel(name = "fallamountfor", width = 15)
    @ApiModelProperty(value = "fallamountfor")
	private java.math.BigDecimal fallamountfor;
	/**fdiscountamount*/
	@Excel(name = "fdiscountamount", width = 15)
    @ApiModelProperty(value = "fdiscountamount")
	private java.math.BigDecimal fdiscountamount;
	/**fnotaxamount*/
	@Excel(name = "fnotaxamount", width = 15)
    @ApiModelProperty(value = "fnotaxamount")
	private java.math.BigDecimal fnotaxamount;
	/**ftaxamount*/
	@Excel(name = "ftaxamount", width = 15)
    @ApiModelProperty(value = "ftaxamount")
	private java.math.BigDecimal ftaxamount;
	/**fallamount*/
	@Excel(name = "fallamount", width = 15)
    @ApiModelProperty(value = "fallamount")
	private java.math.BigDecimal fallamount;
	/**fbasicunitid*/
	@Excel(name = "fbasicunitid", width = 15)
    @ApiModelProperty(value = "fbasicunitid")
	private java.lang.Integer fbasicunitid;
	/**fbasicunitqty*/
	@Excel(name = "fbasicunitqty", width = 15)
    @ApiModelProperty(value = "fbasicunitqty")
	private java.math.BigDecimal fbasicunitqty;
	/**fwrittenoffstatus*/
	@Excel(name = "fwrittenoffstatus", width = 15)
    @ApiModelProperty(value = "fwrittenoffstatus")
	private java.lang.String fwrittenoffstatus;
	/**fwrittenoffamountfor*/
	@Excel(name = "fwrittenoffamountfor", width = 15)
    @ApiModelProperty(value = "fwrittenoffamountfor")
	private java.math.BigDecimal fwrittenoffamountfor;
	/**fwrittenoffqty*/
	@Excel(name = "fwrittenoffqty", width = 15)
    @ApiModelProperty(value = "fwrittenoffqty")
	private java.math.BigDecimal fwrittenoffqty;
	/**fopenqty*/
	@Excel(name = "fopenqty", width = 15)
    @ApiModelProperty(value = "fopenqty")
	private java.math.BigDecimal fopenqty;
	/**fopenstatus*/
	@Excel(name = "fopenstatus", width = 15)
    @ApiModelProperty(value = "fopenstatus")
	private java.lang.String fopenstatus;
	/**fopenamountfor*/
	@Excel(name = "fopenamountfor", width = 15)
    @ApiModelProperty(value = "fopenamountfor")
	private java.math.BigDecimal fopenamountfor;
	/**fbuyivqty*/
	@Excel(name = "fbuyivqty", width = 15)
    @ApiModelProperty(value = "fbuyivqty")
	private java.math.BigDecimal fbuyivqty;
	/**fbuyivbasicqty*/
	@Excel(name = "fbuyivbasicqty", width = 15)
    @ApiModelProperty(value = "fbuyivbasicqty")
	private java.math.BigDecimal fbuyivbasicqty;
	/**fivallamountfor*/
	@Excel(name = "fivallamountfor", width = 15)
    @ApiModelProperty(value = "fivallamountfor")
	private java.math.BigDecimal fivallamountfor;
	/**fordernumber*/
	@Excel(name = "fordernumber", width = 15)
    @ApiModelProperty(value = "fordernumber")
	private java.lang.String fordernumber;
	/**fcomment*/
	@Excel(name = "fcomment", width = 15)
    @ApiModelProperty(value = "fcomment")
	private java.lang.String fcomment;
	/**fbasejoinqty*/
	@Excel(name = "fbasejoinqty", width = 15)
    @ApiModelProperty(value = "fbasejoinqty")
	private java.math.BigDecimal fbasejoinqty;
	/**ftaxcombination*/
	@Excel(name = "ftaxcombination", width = 15)
    @ApiModelProperty(value = "ftaxcombination")
	private java.lang.Integer ftaxcombination;
	/**fsrccreditamountfor*/
	@Excel(name = "fsrccreditamountfor", width = 15)
    @ApiModelProperty(value = "fsrccreditamountfor")
	private java.math.BigDecimal fsrccreditamountfor;
	/**fbillcreditamountfor*/
	@Excel(name = "fbillcreditamountfor", width = 15)
    @ApiModelProperty(value = "fbillcreditamountfor")
	private java.math.BigDecimal fbillcreditamountfor;
	/**fexeccreditamountfor*/
	@Excel(name = "fexeccreditamountfor", width = 15)
    @ApiModelProperty(value = "fexeccreditamountfor")
	private java.math.BigDecimal fexeccreditamountfor;
	/**fclosecreditamountfor*/
	@Excel(name = "fclosecreditamountfor", width = 15)
    @ApiModelProperty(value = "fclosecreditamountfor")
	private java.math.BigDecimal fclosecreditamountfor;
	/**fmaterialdesc*/
	@Excel(name = "fmaterialdesc", width = 15)
    @ApiModelProperty(value = "fmaterialdesc")
	private java.lang.String fmaterialdesc;
	/**ftemptaxrate*/
	@Excel(name = "ftemptaxrate", width = 15)
    @ApiModelProperty(value = "ftemptaxrate")
	private java.math.BigDecimal ftemptaxrate;
	/**fmatchnottaxamtfor*/
	@Excel(name = "fmatchnottaxamtfor", width = 15)
    @ApiModelProperty(value = "fmatchnottaxamtfor")
	private java.math.BigDecimal fmatchnottaxamtfor;
	/**fhadmatchamountfor*/
	@Excel(name = "fhadmatchamountfor", width = 15)
    @ApiModelProperty(value = "fhadmatchamountfor")
	private java.math.BigDecimal fhadmatchamountfor;
	/**fspecialqty*/
	@Excel(name = "fspecialqty", width = 15)
    @ApiModelProperty(value = "fspecialqty")
	private java.math.BigDecimal fspecialqty;
	/**fspecialamountfor*/
	@Excel(name = "fspecialamountfor", width = 15)
    @ApiModelProperty(value = "fspecialamountfor")
	private java.math.BigDecimal fspecialamountfor;
	/**fbflowid*/
	@Excel(name = "fbflowid", width = 15)
    @ApiModelProperty(value = "fbflowid")
	private java.lang.String fbflowid;
	/**fmonumber*/
	@Excel(name = "fmonumber", width = 15)
    @ApiModelProperty(value = "fmonumber")
	private java.lang.String fmonumber;
	/**fmoentryseq*/
	@Excel(name = "fmoentryseq", width = 15)
    @ApiModelProperty(value = "fmoentryseq")
	private java.lang.Integer fmoentryseq;
	/**fopno*/
	@Excel(name = "fopno", width = 15)
    @ApiModelProperty(value = "fopno")
	private java.lang.String fopno;
	/**fseqnumber*/
	@Excel(name = "fseqnumber", width = 15)
    @ApiModelProperty(value = "fseqnumber")
	private java.lang.String fseqnumber;
	/**fopernumber*/
	@Excel(name = "fopernumber", width = 15)
    @ApiModelProperty(value = "fopernumber")
	private java.lang.Integer fopernumber;
	/**fprocessid*/
	@Excel(name = "fprocessid", width = 15)
    @ApiModelProperty(value = "fprocessid")
	private java.lang.Integer fprocessid;
	/**ffprodepartmentid*/
	@Excel(name = "ffprodepartmentid", width = 15)
    @ApiModelProperty(value = "ffprodepartmentid")
	private java.lang.Integer ffprodepartmentid;
	/**fcustmatid*/
	@Excel(name = "fcustmatid", width = 15)
    @ApiModelProperty(value = "fcustmatid")
	private java.lang.String fcustmatid;
	/**fcustmatname*/
	@Excel(name = "fcustmatname", width = 15)
    @ApiModelProperty(value = "fcustmatname")
	private java.lang.String fcustmatname;
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
