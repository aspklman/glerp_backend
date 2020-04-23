package org.jeecg.modules.bom.entity;

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
 * @Description: 鞋型SIZE表
 * @Author: jeecg-boot
 * @Date:   2019-08-07 09:08:29
 * @Version: V1.0
 */
@Data
@TableName("styles")
public class Styles implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**厂区编号*/
    @Excel(name = "厂区编号", width = 4)
	private java.lang.String factNo;
	/**型体编号*/
    @Excel(name = "型体编号", width = 15)
	private java.lang.String styleNo;
	/**SIZE编号*/
    @Excel(name = "SIZE编号", width = 15)
	private java.lang.String sizeNo;
	/**SKU_NO*/
    @Excel(name = "SKU_NO", width = 15)
	private java.lang.String skuNo;
	/**型体ID*/
	@TableId(type = IdType.UUID)
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
	/**鞋型基本资料表ID*/
	private java.lang.String styleId;
}
