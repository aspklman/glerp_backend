package org.jeecg.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * <p>
 * 租户表
 * </p>
 *
 * @Author Barry
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysTenant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;
    
    /**
     * 租户编号
     */
    @Excel(name = "工厂编号", width = 4)
    private String factNo;

    /**
     * 租户名称
     */
    @Excel(name = "租户名称", width = 32)
    private String factName;

//    /**
//     * 创建人
//     */
//    private String createBy;
//
//    /**
//     * 创建时间
//     */
//    private Date createTime;
//
//    /**
//     * 更新人
//     */
//    private String updateBy;
//
//    /**
//     * 更新时间
//     */
//    private Date updateTime;
    
}
