package org.jeecg.modules.bom.vo;

import java.util.List;
import org.jeecg.modules.bom.entity.Stylem;
import org.jeecg.modules.bom.entity.Styles;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 鞋型基本资料表
 * @Author: jeecg-boot
 * @Date:   2019-08-07 09:08:29
 * @Version: V1.0
 */
@Data
public class StylemPage {
	
	/**型体ID*/
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
	/**厂区编号*/
  	@Excel(name = "厂区编号", width = 15)
	private java.lang.String factNo;
	/**型体编号*/
  	@Excel(name = "型体编号", width = 15)
	private java.lang.String styleNo;
	/**客户型体编号*/
  	@Excel(name = "客户型体编号", width = 15)
	private java.lang.String articNo;
	/**型体名称*/
  	@Excel(name = "型体名称", width = 15)
	private java.lang.String styleNm;
	/**财务型体简称,财务成本中心维护*/
  	@Excel(name = "财务型体简称,财务成本中心维护", width = 15)
	private java.lang.String styleShorten;
	/**底模编号*/
  	@Excel(name = "底模编号", width = 15)
	private java.lang.String moldNo;
	/**超始BOM确认日*/
  	@Excel(name = "超始BOM确认日", width = 15)
	private java.lang.String bomStcfmDate;
	/**起始用量确认日*/
  	@Excel(name = "起始用量确认日", width = 15)
	private java.lang.String stdqStcfmDate;
	/**性别*/
  	@Excel(name = "性别", width = 15)
	private java.lang.String gender;
	/**楦头编号*/
  	@Excel(name = "楦头编号", width = 15)
	private java.lang.String lastNo;
	/**英文颜色*/
  	@Excel(name = "英文颜色", width = 15)
	private java.lang.String styleEngColor;
	/**型体颜色*/
  	@Excel(name = "型体颜色", width = 15)
	private java.lang.String styleColor;
	/**型体工艺类别*/
  	@Excel(name = "型体工艺类别", width = 15)
	private java.lang.String solColor;
	/**商品编号*/
  	@Excel(name = "商品编号", width = 15)
	private java.lang.String goodsNo;
	/**序号*/
  	@Excel(name = "序号", width = 15)
	private java.lang.String goodsSeq;
	/**品牌编号*/
  	@Excel(name = "品牌编号", width = 15)
	private java.lang.String brandNo;
	/**鞋型类别编号*/
  	@Excel(name = "鞋型类别编号", width = 15)
	private java.lang.String styletypeNo;
	/**异动人*/
  	@Excel(name = "异动人", width = 15)
	private java.lang.String userNo;
	/**异动时间*/
  	@Excel(name = "异动时间", width = 15)
	private java.lang.String modifyDt;
	/**型体单价*/
  	@Excel(name = "型体单价", width = 15)
	private java.math.BigDecimal price;
	/**通用型体名称*/
  	@Excel(name = "通用型体名称", width = 15)
	private java.lang.String univModelName;
	/**报价SIZE编号*/
  	@Excel(name = "报价SIZE编号", width = 15)
	private java.lang.String quotationSizeNo;
	/**报价楦头肥度*/
  	@Excel(name = "报价楦头肥度", width = 15)
	private java.lang.String quotationLastWidth;
	/**纸版编号*/
  	@Excel(name = "纸版编号", width = 15)
	private java.lang.String paperNo;
	/**客户型体代码*/
  	@Excel(name = "客户型体代码", width = 15)
	private java.lang.String vampPartNo;
	/**客户颜色代码*/
  	@Excel(name = "客户颜色代码", width = 15)
	private java.lang.String cusColorNo;
	/**来源型体*/
  	@Excel(name = "来源型体", width = 15)
	private java.lang.String sourNo;
	/**转印纸注记*/
  	@Excel(name = "转印纸注记", width = 15)
	private java.lang.String htpRmk;
	/**转印纸用量*/
  	@Excel(name = "转印纸用量", width = 15)
	private java.lang.Integer htpQty;
	/**取消注记*/
  	@Excel(name = "取消注记", width = 15)
	private java.lang.String cnlRmk;
	/**取消日期*/
  	@Excel(name = "取消日期", width = 15)
	private java.lang.String cnlDate;
	/**CUSTRAN_RMK*/
  	@Excel(name = "CUSTRAN_RMK", width = 15)
	private java.lang.String custranRmk;
	/**CUSTRAN_DT*/
  	@Excel(name = "CUSTRAN_DT", width = 15)
	private java.lang.String custranDt;
	/**ARTIC_NM*/
  	@Excel(name = "ARTIC_NM", width = 15)
	private java.lang.String articNm;
	/**BOM审核人*/
  	@Excel(name = "BOM审核人", width = 15)
	private java.lang.String bomVerifyUser;
	/**BOM审核时间*/
  	@Excel(name = "BOM审核时间", width = 15)
	private java.lang.String bomVerifyDt;
	/**型体单位*/
  	@Excel(name = "型体单位", width = 15)
	private java.lang.String styleUnit;
	/**soletypeNo*/
  	@Excel(name = "soletypeNo", width = 15)
	private java.lang.String soletypeNo;
	
	@ExcelCollection(name="鞋型SIZE表")
	private List<Styles> stylesList;
	
}
