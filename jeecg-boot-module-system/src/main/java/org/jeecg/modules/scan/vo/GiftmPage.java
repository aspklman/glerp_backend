package org.jeecg.modules.scan.vo;

import java.util.List;
import org.jeecg.modules.scan.entity.Giftm;
import org.jeecg.modules.scan.entity.Giftd;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 礼品鞋主表
 * @Author: jeecg-boot
 * @Date:   2019-08-20 14:36:40
 * @Version: V1.0
 */
@Data
public class GiftmPage {
	
	/**厂别编号*/
  	@Excel(name = "厂别编号", width = 15)
	private java.lang.String factNo;
	/**礼品鞋编号*/
  	@Excel(name = "礼品鞋编号", width = 15)
	private java.lang.String giftNo;
	/**部门*/
  	@Excel(name = "部门", width = 15)
	private java.lang.String deptNm;
	/**姓名*/
  	@Excel(name = "姓名", width = 15)
	private java.lang.String customNm;
	/**生产部别*/
  	@Excel(name = "生产部别", width = 15)
	private java.lang.String proDept;
	/**生产日期*/
  	@Excel(name = "生产日期", width = 15)
	private java.lang.String proDate;
	/**异动人*/
  	@Excel(name = "异动人", width = 15)
	private java.lang.String userNo;
	/**异动时间*/
  	@Excel(name = "异动时间", width = 15)
	private java.lang.String modifyDt;
	/**礼品鞋主ID*/
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
	
	@ExcelCollection(name="礼品鞋明细表")
	private List<Giftd> giftdList;
	
}
