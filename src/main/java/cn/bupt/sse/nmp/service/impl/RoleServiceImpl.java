package cn.bupt.sse.nmp.service.impl;

import cn.bupt.sse.nmp.dao.RoleMapper;
import cn.bupt.sse.nmp.dao.RolePermissionMapper;
import cn.bupt.sse.nmp.entity.Role;
import cn.bupt.sse.nmp.entity.RolePermission;
import cn.bupt.sse.nmp.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: nationalMuseum
 * @description:
 * @author: Ljx
 * @create: 2020-07-01 14:11
 **/
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public void assignPerms(Integer roleId, List<Integer> permIds) {
        for (Integer permId : permIds) {
            RolePermission rolePermission = new RolePermission(roleId, permId);
            rolePermissionMapper.insert(rolePermission);
        }
    }

    @Override
    public Role selectByRoleId(Integer roleId) {
        Role role = roleMapper.selectByRoleId(roleId);
        return role;
    }

    @Override
    public void addRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        Role roleOld = roleMapper.selectByRoleId(role.getRoleId());
        roleOld.setRoleName(role.getRoleName());
        roleOld.setDescription(role.getDescription());
        roleMapper.updateRole(roleOld);
    }

    @Override
    public void delRoleById(Integer roleId) {
        roleMapper.delRoleById(roleId);
    }

    @Override
    public Role selectByUserPhone(String phone) {
        return roleMapper.selectByUserPhone(phone);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }
}
