package org.jeecg.modules.scan.entity;

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
 * @Description: 成型品质扫描
 * @Author: jeecg-boot
 * @Date:   2020-04-03 14:29:00
 * @Version: V1.0
 */
@Data
@TableName("stgscan_quality")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="stgscan_quality对象", description="成型品质扫描")
public class StgscanQuality {
    
	/**feedback1*/
	@Excel(name = "feedback1", width = 15)
    @ApiModelProperty(value = "feedback1")
	private java.lang.String feedback1;
	/**feedback2*/
	@Excel(name = "feedback2", width = 15)
    @ApiModelProperty(value = "feedback2")
	private java.lang.String feedback2;
	/**factNo*/
	@Excel(name = "factNo", width = 15)
    @ApiModelProperty(value = "factNo")
	private java.lang.String factNo;
	/**proFact*/
	@Excel(name = "proFact", width = 15)
    @ApiModelProperty(value = "proFact")
	private java.lang.String proFact;
	/**secNo*/
	@Excel(name = "secNo", width = 15)
    @ApiModelProperty(value = "secNo")
	private java.lang.String secNo;
	/**styleNo*/
	@Excel(name = "styleNo", width = 15)
    @ApiModelProperty(value = "styleNo")
	private java.lang.String styleNo;
	/**styleGender*/
	@Excel(name = "styleGender", width = 15)
    @ApiModelProperty(value = "styleGender")
	private java.lang.String styleGender;
	/**styleColor*/
	@Excel(name = "styleColor", width = 15)
    @ApiModelProperty(value = "styleColor")
	private java.lang.String styleColor;
	/**badLevel*/
	@Excel(name = "badLevel", width = 15)
    @ApiModelProperty(value = "badLevel")
	private java.lang.String badLevel;
	/**badNo*/
	@Excel(name = "badNo", width = 15)
    @ApiModelProperty(value = "badNo")
	private java.lang.String badNo;
	/**qty*/
	@Excel(name = "qty", width = 15)
    @ApiModelProperty(value = "qty")
	private java.math.BigDecimal qty;
	/**recDate*/
	@Excel(name = "recDate", width = 15)
    @ApiModelProperty(value = "recDate")
	private java.lang.String recDate;
	/**recTime*/
	@Excel(name = "recTime", width = 15)
    @ApiModelProperty(value = "recTime")
	private java.lang.String recTime;
	/**userNo*/
	@Excel(name = "userNo", width = 15)
    @ApiModelProperty(value = "userNo")
	private java.lang.String userNo;
}
