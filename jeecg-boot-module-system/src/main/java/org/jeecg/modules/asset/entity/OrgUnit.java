package org.jeecg.modules.asset.entity;

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
 * @Description: OA组织信息表
 * @Author: jeecg-boot
 * @Date:   2023-04-28 11:14:45
 * @Version: V1.0
 */
@Data
@TableName("v_org_unit")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_org_unit对象", description="OA组织信息表")
public class OrgUnit {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private java.lang.Integer id;
	/**factNo*/
	@Excel(name = "factNo", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**companyNo*/
	@Excel(name = "companyNo", width = 15)
    @ApiModelProperty(value = "companyNo")
	private java.lang.String companyNo;
	/**companyName*/
	@Excel(name = "companyName", width = 15)
    @ApiModelProperty(value = "companyName")
	private java.lang.String companyName;
	/**departmentNo*/
	@Excel(name = "departmentNo", width = 15)
    @ApiModelProperty(value = "departmentNo")
	private java.lang.String departmentNo;
	/**departmentName*/
	@Excel(name = "departmentName", width = 15)
    @ApiModelProperty(value = "departmentName")
	private java.lang.String departmentName;
	/**superId*/
	@Excel(name = "superId", width = 15)
    @ApiModelProperty(value = "superId")
	private java.lang.String superId;
	/**superOrderNo*/
	@Excel(name = "superOrderNo", width = 15)
    @ApiModelProperty(value = "superOrderNo")
	private java.lang.Integer superOrderNo;
	/**orderNo*/
	@Excel(name = "orderNo", width = 15)
    @ApiModelProperty(value = "orderNo")
	private java.lang.Integer orderNo;
}
