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
 * @Description: 收款条件多语言
 * @Author: jeecg-boot
 * @Date:   2020-08-29 20:53:36
 * @Version: V1.0
 */
@Data
@TableName("T_BD_RecCondition_l")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="T_BD_RecCondition_l对象", description="收款条件多语言")
public class TBDRecConditionl {
    
	/**fpkid*/
	@Excel(name = "fpkid", width = 15)
    @ApiModelProperty(value = "fpkid")
	private java.lang.Integer fpkid;
	/**flocaleid*/
	@Excel(name = "flocaleid", width = 15)
    @ApiModelProperty(value = "flocaleid")
	private java.lang.Integer flocaleid;
	/**fname*/
	@Excel(name = "fname", width = 15)
    @ApiModelProperty(value = "fname")
	private java.lang.String fname;
	/**fid*/
	@Excel(name = "fid", width = 15)
    @ApiModelProperty(value = "fid")
	private java.lang.Integer fid;
	/**fdescription*/
	@Excel(name = "fdescription", width = 15)
    @ApiModelProperty(value = "fdescription")
	private java.lang.String fdescription;
}
