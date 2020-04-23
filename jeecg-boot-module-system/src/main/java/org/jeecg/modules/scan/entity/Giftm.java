package org.jeecg.modules.scan.entity;

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
 * @Description: 礼品鞋主表
 * @Author: jeecg-boot
 * @Date:   2019-08-20 14:36:39
 * @Version: V1.0
 */
@Data
@TableName("giftm")
public class Giftm implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**厂别编号*/
	private java.lang.String factNo;
	/**礼品鞋编号*/
	private java.lang.String giftNo;
	/**部门*/
	private java.lang.String deptNm;
	/**姓名*/
	private java.lang.String customNm;
	/**生产部别*/
	@Dict(dicCode="pro_dept")
	private java.lang.String proDept;
	/**生产日期*/
	private java.lang.String proDate;
	/**异动人*/
	private java.lang.String userNo;
	/**异动时间*/
	private java.lang.String modifyDt;
	/**礼品鞋主ID*/
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
