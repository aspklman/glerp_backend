package org.jeecg.modules.bom.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 鞋型基本资料表
 * @Author: jeecg-boot
 * @Date:   2019-08-07 09:08:29
 * @Version: V1.0
 */
@Data
@TableName("stylem")
public class Stylem implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**型体ID*/
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
	/**厂区编号*/
	private java.lang.String factNo;
	/**型体编号*/
	private java.lang.String styleNo;
	/**客户型体编号*/
	private java.lang.String articNo;
	/**型体名称*/
	private java.lang.String styleNm;
	/**财务型体简称,财务成本中心维护*/
	private java.lang.String styleShorten;
	/**底模编号*/
	private java.lang.String moldNo;
	/**超始BOM确认日*/
	private java.lang.String bomStcfmDate;
	/**起始用量确认日*/
	private java.lang.String stdqStcfmDate;
	/**性别*/
	private java.lang.String gender;
	/**楦头编号*/
	private java.lang.String lastNo;
	/**英文颜色*/
	private java.lang.String styleEngColor;
	/**型体颜色*/
	private java.lang.String styleColor;
	/**型体工艺类别*/
	private java.lang.String solColor;
	/**商品编号*/
	private java.lang.String goodsNo;
	/**序号*/
	private java.lang.String goodsSeq;
	/**品牌编号*/
	private java.lang.String brandNo;
	/**鞋型类别编号*/
	private java.lang.String styletypeNo;
	/**异动人*/
	private java.lang.String userNo;
	/**异动时间*/
	private java.lang.String modifyDt;
	/**型体单价*/
	private java.math.BigDecimal price;
	/**通用型体名称*/
	private java.lang.String univModelName;
	/**报价SIZE编号*/
	private java.lang.String quotationSizeNo;
	/**报价楦头肥度*/
	private java.lang.String quotationLastWidth;
	/**纸版编号*/
	private java.lang.String paperNo;
	/**客户型体代码*/
	private java.lang.String vampPartNo;
	/**客户颜色代码*/
	private java.lang.String cusColorNo;
	/**来源型体*/
	private java.lang.String sourNo;
	/**转印纸注记*/
	private java.lang.String htpRmk;
	/**转印纸用量*/
	private java.lang.Integer htpQty;
	/**取消注记*/
	private java.lang.String cnlRmk;
	/**取消日期*/
	private java.lang.String cnlDate;
	/**CUSTRAN_RMK*/
	private java.lang.String custranRmk;
	/**CUSTRAN_DT*/
	private java.lang.String custranDt;
	/**ARTIC_NM*/
	private java.lang.String articNm;
	/**BOM审核人*/
	private java.lang.String bomVerifyUser;
	/**BOM审核时间*/
	private java.lang.String bomVerifyDt;
	/**型体单位*/
	private java.lang.String styleUnit;
	/**soletypeNo*/
	private java.lang.String soletypeNo;
}
