package com.ov.dao;

import java.util.List;

import com.ov.entity.Department;
import com.ov.framework.dao.BaseDao;

public interface DepartmentDao extends BaseDao<Department, Long> {

  List<Department> findRoots(Long tenantID,Integer count);


  List<Department> findParents(Department department, Integer count);


  List<Department> findChildren(Department department, Integer count);
}
