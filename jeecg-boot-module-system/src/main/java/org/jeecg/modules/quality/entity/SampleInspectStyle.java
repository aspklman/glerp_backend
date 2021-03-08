package org.jeecg.modules.quality.entity;

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
 * @Description: 型体检验项目
 * @Author: jeecg-boot
 * @Date:   2020-12-22 15:52:14
 * @Version: V1.0
 */
@Data
@TableName("v_sample_inspect_style")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_sample_inspect_style对象", description="型体检验项目")
public class SampleInspectStyle {
    
	/**factNo*/
	@Excel(name = "factNo", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**proDept*/
	@Excel(name = "proDept", width = 15)
    @ApiModelProperty(value = "proDept")
	private java.lang.String proDept;
	/**customFnm*/
	@Excel(name = "customFnm", width = 15)
    @ApiModelProperty(value = "customFnm")
	private java.lang.String customFnm;
	/**styleShorten*/
	@Excel(name = "styleShorten", width = 15)
    @ApiModelProperty(value = "styleShorten")
	private java.lang.String styleShorten;
	/**styleMark*/
	@Excel(name = "styleMark", width = 15)
    @ApiModelProperty(value = "styleMark")
	private java.lang.Integer styleMark;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
}
