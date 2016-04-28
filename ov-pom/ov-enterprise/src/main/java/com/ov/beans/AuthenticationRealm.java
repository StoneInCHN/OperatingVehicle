package com.ov.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.ov.beans.Setting.CaptchaType;
import com.ov.entity.ConfigMeta;
import com.ov.entity.TenantAccount;
import com.ov.entity.TenantInfo;
import com.ov.entity.commonenum.CommonEnum.AccountStatus;
import com.ov.service.CaptchaService;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantInfoService;

/**
 * 权限认证
 * 
 */
public class AuthenticationRealm extends AuthorizingRealm {

	@Resource(name = "tenantAccountServiceImpl")
	private TenantAccountService tenantAccountService;
	@Resource(name = "tenantInfoServiceImpl")
	private TenantInfoService tenantInfoService;
	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;

	/**
	 * 获取认证信息
	 * 
	 * @param token
	 *            令牌
	 * @return 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
		String orgCode = authenticationToken.getOrgCode();
		String captchaId = authenticationToken.getCaptchaId();
		String captcha = authenticationToken.getCaptcha();
		String ip = authenticationToken.getHost();
		
		if (!captchaService.isValid(CaptchaType.LOGIN, captchaId, captcha)) {
          throw new UnsupportedTokenException();
		}
		
		if (username != null && password != null && orgCode != null) {
		    
//		    TenantInfo tenantInfo = tenantInfoService.findTenantWithOrgCode(orgCode);
		    
		    TenantAccount tenantAccount = tenantAccountService.findByNameAndOrgCode(username, orgCode);
		    TenantInfo tenantInfo = null;
		    
		    if (tenantAccount == null) {
		    	throw new UnknownAccountException();
			}else {
				tenantInfo = tenantInfoService.find(tenantAccount.getTenantID());
				if (tenantInfo == null) {
					throw new UnknownAccountException();
				}else if(tenantInfo.getAccountStatus().equals(AccountStatus.LOCKED)){
					throw new DisabledAccountException();
		        }
				if (tenantAccount.getAccoutStatus().equals(AccountStatus.LOCKED)) {
                    throw new DisabledAccountException();
                }
                if (!DigestUtils.md5Hex(password).equals(tenantAccount.getPassword())) {
                    throw new IncorrectCredentialsException();
                }				
			}
		    if (tenantAccount.getLoginIp() != null) {
		      tenantAccount.setLastLoginIp(tenantAccount.getLoginIp());
            }
		    if (tenantAccount.getLoginDate() != null) {
		      tenantAccount.setLastLoginDate(tenantAccount.getLoginDate());
            }
			tenantAccount.setLoginIp(ip);
			tenantAccount.setLoginDate(new Date());
			tenantAccountService.update(tenantAccount);
			
			return new SimpleAuthenticationInfo(new Principal(tenantAccount.getId(), username,tenantInfo), password, getName());
		}
		throw new UnknownAccountException();
	}

	/**
	 * 获取授权信息
	 * 
	 * @param principals
	 *            principals
	 * @return 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		if (principal != null) {
			List<ConfigMeta> authorityResources = tenantAccountService.findAuthorities(principal.getId());
			List<String> strAuthorities = new ArrayList<String>();
			for(ConfigMeta auth:authorityResources){
			  strAuthorities.add(auth.getConfigKey ());
			}
			if (authorityResources != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(strAuthorities);
				return authorizationInfo;
			}
		}
		return null;
	}

}