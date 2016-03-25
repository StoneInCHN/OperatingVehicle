package com.ov.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.DepartmentDao;
import com.ov.entity.Department;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.DepartmentService;
import com.ov.service.TenantAccountService;
import com.ov.utils.FieldFilterUtils;

/**
 * 部门
 * 
 * @author huyong
 *
 */
@Service("departmentServiceImpl")
public class DepartmentServiceImpl extends BaseServiceImpl<Department, Long> implements
    DepartmentService {

  @Resource(name = "departmentDaoImpl")
  private DepartmentDao departmentDao;

  @Resource(name = "tenantAccountServiceImpl")
  protected TenantAccountService tenantAccountService;

  @Resource
  public void setBaseDao(DepartmentDao departmentDao) {
    super.setBaseDao(departmentDao);
  }

  @Override
  public List<Map<String, Object>> findAllDepartments() {
    List<Filter> filters = new ArrayList<Filter>();
    Filter tenantIdFilter= new Filter();
    tenantIdFilter.setValue (tenantAccountService.getCurrentTenantID ());
    tenantIdFilter.setProperty ("tenantID");
    tenantIdFilter.setOperator (Operator.eq);
    filters.add (tenantIdFilter);
    
    List<Department> departmentList = departmentDao.findList(null, null, filters, null);
    String[] propertys = {"id", "name"};
    return FieldFilterUtils.filterCollectionMap(propertys, departmentList);
  }

  public List<Department> findRoots() {
    return departmentDao.findRoots(tenantAccountService.getCurrentTenantID(), null);
  }
  
  @Override
  public List<Department> findRoots(Boolean isTenant, Long tenantID) {
    return departmentDao.findRoots(tenantID, null);
  }
  public List<Department> findRoots(Integer count) {
    return departmentDao.findRoots(tenantAccountService.getCurrentTenantID(), count);
  }

  public List<Department> findRoots(Integer count, String cacheRegion) {
    return departmentDao.findRoots(tenantAccountService.getCurrentTenantID(), count);
  }

  public List<Department> findParents(Department department) {
    return departmentDao.findParents(department, null);
  }

  public List<Department> findParents(Department department, Integer count) {
    return departmentDao.findParents(department, count);
  }

  public List<Department> findParents(Department department, Integer count, String cacheRegion) {
    return departmentDao.findParents(department, count);
  }

  public List<Department> findTree() {
    return departmentDao.findChildren(null, null);
  }

  public List<Department> findChildren(Department department) {
    return departmentDao.findChildren(department, null);
  }

  public List<Department> findChildren(Department department, Integer count) {
    return departmentDao.findChildren(department, count);
  }

  public List<Department> findChildren(Department department, Integer count, String cacheRegion) {
    return departmentDao.findChildren(department, count);
  }


}
