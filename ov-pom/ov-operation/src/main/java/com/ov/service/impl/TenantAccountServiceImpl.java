package com.ov.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ov.dao.ConfigMetaDao;
import com.ov.dao.MetaRelationDao;
import com.ov.dao.RoleDao;
import com.ov.dao.TenantAccountDao;
import com.ov.dao.TenantInfoDao;
import com.ov.entity.ConfigMeta;
import com.ov.entity.MetaRelation;
import com.ov.entity.Role;
import com.ov.entity.TenantAccount;
import com.ov.entity.TenantInfo;
import com.ov.entity.VersionConfig;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.MailService;
import com.ov.service.TenantAccountService;
import com.ov.utils.CommonUtils;
import com.ov.utils.SpringUtils;

@Service("tenantAccountServiceImpl")
public class TenantAccountServiceImpl extends BaseServiceImpl<TenantAccount, Long> implements
    TenantAccountService {

  @Resource(name = "tenantAccountDaoImpl")
  public void setBaseDao(TenantAccountDao tenantAccountDao) {
    super.setBaseDao(tenantAccountDao);
  }

  @Resource(name = "tenantAccountDaoImpl")
  private TenantAccountDao tenantAccountDao;

  @Resource(name = "tenantInfoDaoImpl")
  private TenantInfoDao tenantInfoDao;

  @Resource(name = "roleDaoImpl")
  private RoleDao roleDao;

//  @Resource(name = "mailServiceImpl")
//  private MailService mailService;

  // @Resource(name = "versionConfigDaoImpl")
  // private VersionConfigDao versionConfigDao;

  @Resource(name = "metaRelationDaoImpl")
  private MetaRelationDao metaRelationDao;
  
  @Resource(name = "configMetaDaoImpl")
  private ConfigMetaDao ConfigMetaDao;
  
  
  @Override
  public boolean usernameExists(String username) {
    return tenantAccountDao.usernameExists(username);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void save(TenantAccount tenantAccount) {
    TenantInfo tenantInfo = tenantInfoDao.find(tenantAccount.getTenantID());
    tenantAccount.setIsSystem(false);
    String password = CommonUtils.randomPwd();

   // LogUtil.debug(TenantAccountServiceImpl.class, "save", "password = %s", password);

    tenantAccount.setPassword(DigestUtils.md5Hex(password));
    tenantAccount.setRealName(tenantInfo.getTenantName());

    Role role = new Role();
    role.setIsSystem(false);
    role.setTenantID(tenantAccount.getTenantID());
    role.setDescription("超级管理员");
    role.setName("管理员");
//    role.setSystemType(SystemType.ENTERPRISE);
    VersionConfig versionConfig = tenantInfo.getVersionConfig();
    Set<ConfigMeta> configMetas = versionConfig.getConfigMeta();
    Set<ConfigMeta> configMetatemps = new HashSet<ConfigMeta>();
    for (ConfigMeta configMeta : configMetas) {
      configMetatemps.add(configMeta);
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter();
      filter.setProperty("mainID");
      filter.setValue(configMeta.getId());
      filter.setOperator(Operator.eq);
      filters.add(filter);
      List<MetaRelation> metaRelations =  metaRelationDao.findList(null, null, filters, null);
      for (MetaRelation metaRelation : metaRelations) {
        ConfigMeta meta  = metaRelation.getRelationID();
        configMetatemps.add(meta);
      }
      
    }
    role.setConfigMetas(configMetatemps);
     roleDao.persist(role);

    Set<Role> roles = new HashSet<Role>();
    roles.add(role);
    tenantAccount.setRoles(roles);

     tenantAccountDao.persist(tenantAccount);
    // send password
     //短信
   //  UcpaasUtil.SendAccountBySms(tenantInfo.getContactPhone(), tenantInfo.getOrgCode(), tenantAccount.getUserName(), password);
    
     // 邮件
    String subject = SpringUtils.getMessage("csh.tenantAccount.password.subject");
    String message =
        SpringUtils.getMessage("csh.tenantAccount.password.message", tenantAccount.getUserName(),
            password,tenantInfo.getOrgCode());

//    mailService.send(tenantInfo.getEmail(), subject, message);
  //  mailService.send("676397876@qq.com", subject, message);
//    tenantInfo.setIsHaveAccount(true);
     tenantInfoDao.merge(tenantInfo);
  }



}
