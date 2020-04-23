package org.jeecg.modules.scan.entity;

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
 * @Description: 礼品鞋明细表
 * @Author: jeecg-boot
 * @Date:   2019-08-20 14:36:39
 * @Version: V1.0
 */
@Data
@TableName("giftd")
public class Giftd implements Serializable {
    private static final long serialVersionUID = 1L;
    
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
	/**型体颜色号码*/
    @Excel(name = "型体颜色号码", width = 15)
	private java.lang.String skuNumber;
	/**数量*/
    @Excel(name = "数量", width = 15)
	private java.lang.Integer qty;
	/**礼品鞋明细ID*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**礼品鞋主ID*/
	private java.lang.String giftId;
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
