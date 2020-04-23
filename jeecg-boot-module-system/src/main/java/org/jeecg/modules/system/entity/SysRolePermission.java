package org.jeecg.modules.system.entity;

import java.io.Serializable;

import org.jeecgframework.poi.excel.annotation.Excel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;
    
    /**
	 * 工厂编号
	 */
    @Excel(name="工厂编号",width=4)
	private String factNo;				//增加工厂编号
    
    /**
     * 角色id
     */
    private String roleId;

    /**
     * 权限id
     */
    private String permissionId;
    
    /**
     * 数据权限
     */
    private String dataRuleIds;

    public SysRolePermission() {
   	}
       
   	public SysRolePermission(String roleId, String permissionId) {
   		this.roleId = roleId;
   		this.permissionId = permissionId;
   	}

}
