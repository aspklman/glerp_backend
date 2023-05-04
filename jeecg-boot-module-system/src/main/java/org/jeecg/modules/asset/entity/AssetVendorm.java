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
 * @Description: 供应商表
 * @Author: jeecg-boot
 * @Date:   2023-04-25 14:16:19
 * @Version: V1.0
 */
@Data
@TableName("v_asset_vendorm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_asset_vendorm对象", description="供应商表")
public class AssetVendorm {
    
	/**factNo*/
	@Excel(name = "factNo", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**vendNo*/
	@Excel(name = "vendNo", width = 15)
    @ApiModelProperty(value = "vendNo")
	private java.lang.String vendNo;
	/**vendFnm*/
	@Excel(name = "vendFnm", width = 15)
    @ApiModelProperty(value = "vendFnm")
	private java.lang.String vendFnm;
	/**vendGnm*/
	@Excel(name = "vendGnm", width = 15)
    @ApiModelProperty(value = "vendGnm")
	private java.lang.String vendGnm;
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private java.lang.Integer id;
}
