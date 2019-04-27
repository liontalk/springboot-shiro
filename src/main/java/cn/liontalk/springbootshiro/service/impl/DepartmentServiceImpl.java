package cn.liontalk.springbootshiro.service.impl;

import cn.liontalk.springbootshiro.common.domain.Tree;
import cn.liontalk.springbootshiro.dao.DepartmentDao;
import cn.liontalk.springbootshiro.entity.DepartEntity;
import cn.liontalk.springbootshiro.service.DepartmentService;
import cn.liontalk.springbootshiro.util.BuildTreeUtils;
import cn.liontalk.springbootshiro.vo.DepartmentVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @projectName springboot-shiro
 * @description:
 * @date 2019/4/23 14:07
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public int insert(DepartEntity departEntity) {
        return departmentDao.insert(departEntity);
    }

    @Override
    public int delete(int id) {
        return departmentDao.delete(id);
    }


    @Override
    public int update(DepartEntity departEntity) {
        return departmentDao.update(departEntity);
    }

    @Override
    public List<DepartEntity> queryAllDepartment() {
        return departmentDao.queryAllDepartment();
    }

    @Override
    public DepartmentVO queryDeptById(int deptId) {
        DepartmentVO departmentVO = new DepartmentVO();
        DepartEntity departEntity = departmentDao.queryDeptById(deptId);
        if (null != departEntity ) {
            if(departEntity.getParentId()!=0){
                departmentVO.setParentName(departEntity.getName());
                BeanUtils.copyProperties(departEntity, departmentVO);
            }else{
                BeanUtils.copyProperties(departEntity, departmentVO);
            }
        }
        logger.info("获得departmentVo:" + departmentVO);
        return departmentVO;
    }

    @Override
    public Tree<DepartEntity> getTree() {
        List<Tree<DepartEntity>> trees = new ArrayList<Tree<DepartEntity>>();
        List<DepartEntity> sysDepts = departmentDao.list(new HashMap<String, Object>(16));
        for (DepartEntity sysDept : sysDepts) {
            Tree<DepartEntity> tree = new Tree<DepartEntity>();
            tree.setId(String.valueOf(sysDept.getDeptId()));
            tree.setParentId(String.valueOf(sysDept.getParentId()));
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为0，根据数据库实际情况调整
        Tree<DepartEntity> tree = BuildTreeUtils.build(trees);
        return tree;
    }
}
