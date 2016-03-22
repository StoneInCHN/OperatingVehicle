package com.ov.service;

import java.util.Date;

import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.BaseService;
import com.ov.entity.*;

/**
 * 角色 Service
 * 
 */
public interface RoleService extends BaseService<Role, Long> {

  Page<Role> searchByFilter(String name_roleSearch, Date beginDate_roleSearch,
      Date endDate_roleSearch, Pageable pageable);

}
