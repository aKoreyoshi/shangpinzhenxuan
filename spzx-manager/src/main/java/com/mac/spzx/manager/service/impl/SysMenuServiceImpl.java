package com.mac.spzx.manager.service.impl;

import com.mac.spzx.common.exception.KoreyoshiException;
import com.mac.spzx.manager.mapper.SysMenuMapper;
import com.mac.spzx.manager.mapper.SysRoleMenuMapper;
import com.mac.spzx.manager.service.SysMenuService;
import com.mac.spzx.model.entity.system.SysMenu;
import com.mac.spzx.model.entity.system.SysUser;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import com.mac.spzx.model.vo.system.SysMenuVo;
import com.mac.spzx.util.utils.AuthContextUtil;
import com.mac.spzx.util.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月05日, 21:11:36
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    private SysMenuMapper sysMenuMapper;
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    public void setSysMenuMapper(SysMenuMapper sysMenuMapper,
                                 SysRoleMenuMapper sysRoleMenuMapper) {
        this.sysMenuMapper = sysMenuMapper;
        this.sysRoleMenuMapper = sysRoleMenuMapper;
    }

    /**
     * 构建树形菜单结构
     *
     * @return
     */
    @Override
    public List<SysMenu> buildTreeMenu() {

        // 先查询出来所有菜单数据
        List<SysMenu> sysMenus = sysMenuMapper.findAll();
        // 构建树形菜单
        List<SysMenu> menuTree = MenuHelper.buildMenuTree(sysMenus);
        // TODO 测试结果
        System.out.println(menuTree);
        return menuTree;
    }

    /**
     * 新增菜单
     *
     * @param sysMenu
     */
    @Transactional
    @Override
    public void saveMenu(SysMenu sysMenu) {
        sysMenuMapper.saveMenu(sysMenu);
        // 修改父菜单
        updateIsHalf(sysMenu);
    }

    private void updateIsHalf(SysMenu sysMenu) {
        // 查询是否存在父菜单
        SysMenu parent = sysMenuMapper.selectParentMenu(sysMenu.getParentId());
        if (parent != null) {
            // 将其设置为半菜单
            sysMenuMapper.updateIsHalf(parent.getId());
            // 递归
            updateIsHalf(parent);
        }
    }

    /**
     * 修改菜单
     *
     * @param sysMenu
     */
    @Override
    public void updateMenu(SysMenu sysMenu) {
        sysMenuMapper.updateMenu(sysMenu);
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @Override
    public void deleteMenu(Long id) {
        // 首先需要判断当前菜单是否有子菜单，若有子菜单则不能删除
        Integer count = sysMenuMapper.selectChildMenu(id);
        if (count > 0) {
            throw new KoreyoshiException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.deleteById(id);
    }

    @Override
    public Map<String, Object> getMenuList(Long roleId) {
        // 根据roleId获取到已分配的菜单
        List<Long> roleMenuIds = sysRoleMenuMapper.selectByRoleId(roleId);
        // 拿到所有菜单数据
        List<SysMenu> sysMenus = buildTreeMenu();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("menuList", sysMenus);
        resultMap.put("roleMenuIds", roleMenuIds);
        return resultMap;
    }

    /**
     * 动态菜单
     * @return
     */
    @Override
    public List<SysMenuVo> dynamicMenu() {
        // 先获取到用户id
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();
        // 根据用户id查询可用菜单
        List<SysMenu> sysMenus = sysMenuMapper.selectByUserId(userId);
        // 处理数据
        List<SysMenu> menuTrees = MenuHelper.buildMenuTree(sysMenus);
        // 转换成vo对象
        List<SysMenuVo> sysMenuVos = buildMenus(menuTrees);
        return sysMenuVos;
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}