package org.jeecg.modules.scan.vo;

import java.util.List;
import org.jeecg.modules.scan.entity.Sku_picture_m;
import org.jeecg.modules.scan.entity.Sku_picture_d;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: SKU图片主表
 * @Author: jeecg-boot
 * @Date:   2020-01-13 13:36:27
 * @Version: V1.0
 */
@Data
public class Sku_picture_mPage {
	
	/**厂区编号*/
  	@Excel(name = "厂区编号", width = 15)
	private java.lang.String factNo;
	/**SKU编号*/
  	@Excel(name = "SKU编号", width = 15)
	private java.lang.String sku;
	/**SKU图片主id*/
	private java.lang.String id;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人*/
  	@Excel(name = "更新人", width = 15)
	private java.lang.String updateBy;
	/**更新时间*/
  	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	
	@ExcelCollection(name="SKU图片明细表")
	private List<Sku_picture_d> sku_picture_dList;
	
}
