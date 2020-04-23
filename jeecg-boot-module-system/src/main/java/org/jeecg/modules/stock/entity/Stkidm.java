package org.jeecg.modules.stock.entity;

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
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 仓库表
 * @Author: jeecg-boot
 * @Date:   2019-12-05 09:39:03
 * @Version: V1.0
 */
@Data
@TableName("stkidm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="stkidm对象", description="仓库表")
public class Stkidm {
    
	/**仓库ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "仓库ID")
	private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;
	/**厂区编号*/
	@Excel(name = "厂区编号", width = 15)
    @ApiModelProperty(value = "厂区编号")
	private java.lang.String factNo;
	/**库别编号*/
	@Excel(name = "库别编号", width = 15)
    @ApiModelProperty(value = "库别编号")
	private java.lang.String stkNo;
	/**库别说明*/
	@Excel(name = "库别说明", width = 15)
    @ApiModelProperty(value = "库别说明")
	private java.lang.String stkDesc;
	/**仓库类别*/
	@Excel(name = "仓库类别", width = 15)
    @ApiModelProperty(value = "仓库类别")
	@Dict(dicCode="stk_kind")
	private java.lang.String stkKind;
	/**归属厂别*/
	@Excel(name = "归属厂别", width = 15)
    @ApiModelProperty(value = "归属厂别")
	@Dict(dicCode="pro_dept")
	private java.lang.String proFact;
	/**仓库性质 1.自有 2.客供 3.易耗品*/
	@Excel(name = "仓库性质 1.自有 2.客供 3.易耗品", width = 15)
    @ApiModelProperty(value = "仓库性质 1.自有 2.客供 3.易耗品")
	@Dict(dicCode="stk_ori")
	private java.lang.String oriStk;
	/**入库制程*/
	@Excel(name = "入库制程", width = 15)
    @ApiModelProperty(value = "入库制程")
	private java.lang.String borNo;
	/**归属库别*/
	@Excel(name = "归属库别", width = 15)
    @ApiModelProperty(value = "归属库别")
	private java.lang.String belStk;
	/**工厂类别*/
	@Excel(name = "工厂类别", width = 15)
    @ApiModelProperty(value = "工厂类别")
	private java.lang.String factId;
	/**关务类型(1.原物料 2.在制品 3.成品)*/
	@Excel(name = "关务类型(1.原物料 2.在制品 3.成品)", width = 15)
    @ApiModelProperty(value = "关务类型(1.原物料 2.在制品 3.成品)")
	@Dict(dicCode="cus_type")
	private java.lang.String cusType;
	/**公司代号*/
	@Excel(name = "公司代号", width = 15)
    @ApiModelProperty(value = "公司代号")
	private java.lang.String compNo;
	/**保税注记*/
	@Excel(name = "保税注记", width = 15)
    @ApiModelProperty(value = "保税注记")
	private java.lang.String taxRmk;
	/**批次管理注记(Y/N)*/
	@Excel(name = "批次管理注记(Y/N)", width = 15)
    @ApiModelProperty(value = "批次管理注记(Y/N)")
	private java.lang.String lotRmk;
	/**仓库备注*/
	@Excel(name = "仓库备注", width = 15)
    @ApiModelProperty(value = "仓库备注")
	private java.lang.String remark;
}
