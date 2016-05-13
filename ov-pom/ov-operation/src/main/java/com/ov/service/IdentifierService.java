package com.ov.service;

import com.ov.entity.Identifier;
import com.ov.entity.commonenum.CommonEnum.IdentifierType;
import com.ov.framework.service.BaseService;

public interface IdentifierService extends BaseService<Identifier, Long> {
  /**
   * 根据自增类型获取该数据最新的数字，并将数据库中的值加1
   * @param idType
   * @return
   */
  Long getLatestIdentifier(IdentifierType idType);
  
  String getLatestOrgCode();
  
}
