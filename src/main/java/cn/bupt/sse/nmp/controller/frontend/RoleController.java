package cn.bupt.sse.nmp.controller.frontend;

import cn.bupt.sse.nmp.dao.RoleMapper;
import cn.bupt.sse.nmp.dao.RolePermissionMapper;
import cn.bupt.sse.nmp.entity.Role;
import cn.bupt.sse.nmp.entity.RolePermission;
import cn.bupt.sse.nmp.result.CodeMsg;
import cn.bupt.sse.nmp.result.Result;
import cn.bupt.sse.nmp.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.beans.binding.ObjectExpression;
import javafx.geometry.Pos;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: nationalMuseum
 * @description:
 * @author: Ljx
 * @create: 2020-07-01 13:59
 **/
@RestController
@RequestMapping(value = "/role")
@RequiresRoles("super admin")
@Api(tags = "角色管理")
public class RoleController {
    @Autowired
    private RoleService roleService;
    /**
     * 授予角色权限
     * @param map 存储roleId ：角色ID ; permIds: 权限的集合
     * @return
     */
    @PostMapping(value = "/assignPerms")
    public Result assignPerms(@RequestBody Map<String, Object> map){
        Integer roleId = (Integer) map.get("roleId");
        List<Integer> permIds = (List<Integer>) map.get("permIds");
        roleService.assignPerms(roleId,permIds);
        return Result.success("");
    }

    /**
     * 添加角色
     * @param role Role
     * @return
     */
    @ApiOperation(value = "添加角色", notes = "无需添加角色Id")
    @PostMapping(value = "/addRole")
    public Result addRole(@RequestBody Role role){
        roleService.addRole(role);
        return Result.success("");
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @ApiOperation(value = "修改角色")
    @PostMapping(value = "/updateRole")
    public Result updateRole(@RequestBody Role role){
        roleService.updateRole(role);
        return Result.success("");
    }

    /**
     * 按照id删除角色
     * @param roleId
     * @return
     */
    @ApiOperation(value = "根据id删除角色")
    @PostMapping(value = "/delRole")
    public Result delRoleById(@RequestParam  Integer roleId){
        roleService.delRoleById(roleId);
        return Result.success("");
    }

    /**
     *查找角色
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询所有角色")
    @GetMapping(value = "selectAll")
    public Result selectAll(@RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Role> pageInfo = new PageInfo<>(roleService.selectAll());
        return Result.success(pageInfo);
    }

}
