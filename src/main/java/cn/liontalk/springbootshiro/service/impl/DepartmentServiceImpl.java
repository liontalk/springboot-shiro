package cn.liontalk.springbootshiro.service.impl;

import cn.liontalk.springbootshiro.dao.DepartmentDao;
import cn.liontalk.springbootshiro.entity.DepartEntity;
import cn.liontalk.springbootshiro.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @projectName springboot-shiro
 * @description:
 * @date 2019/4/23 14:07
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

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
    public DepartEntity queryDeptById(int deptId) {
        return departmentDao.queryDeptById(deptId);
    }
}
