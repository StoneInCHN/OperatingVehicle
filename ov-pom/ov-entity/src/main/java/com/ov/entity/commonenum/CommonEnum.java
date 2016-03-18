package com.ov.entity.commonenum;

/**
 * 公共枚举数据
 * 
 * @author shijun
 *
 */
public class CommonEnum {

  /**
   * 配置元属性基本数据类型
   * 
   * @author shijun
   *
   */
  public enum MetaDataType {
    SHORT, INT, LONG, DOUBLE, DECIMAL, STRING
  }

  /**
   * 配置元之间关系
   */
  public enum MetaRelation {
    /**
     * 依赖
     */
    DEPEND,
    /**
     * 包含
     */
    INCLUDE,
    /**
     * 关联
     */
    ASSOCIATION
  }



  /**
   * 帐号状态
   */
  public enum AccountStatus {

    /** 帐号正常 */
    ACTIVED,

    /** 帐号禁用 */
    LOCKED,

    /** 帐号删除 */
    DELETE
  }

  /**
   * 性别
   */
  public enum Gender {

    /** 男 */
    MALE,

    /** 女 */
    FEMALE
  }

  /**
   * 员工状态
   */
  public enum StaffStatus {

    /** 在职 */
    INSERVICE,

    /** 离职 */
    OUTSERVICE
  }



  /**
   * 通用状态
   * 
   * @author shijun
   *
   */
  public enum Status {
    /**
     * 启用
     */
    ENABLE,
    /**
     * 禁用
     */
    DISABLE
  }



  /**
   * 逻辑删除标记
   * 
   * @author shijun
   *
   */
  public enum DeleteStatus {
    /**
     * 没有删除
     */
    NOT_DELETED,
    /**
     * 删除
     */
    DELETED
  }


  /**
   * 版本状态
   * 
   * @author huyong
   *
   */
  public enum VersionStatus {
    /**
     * 可用
     */
    ENABLE,
    /**
     * 禁用
     */
    DISABLE
  }

  /**
   * 自增类型
   */
  public enum IdentifierType {
    /**
     * 租户机构代码自增
     */
    ORGCODE
  }
  /**
   * 树节点展开状态
   * 
   * @author tanbiao
   *
   */
  public enum TreeNodeState {
    /**
     * 展开
     */
    open,
    /**
     * 关闭
     */
    closed
  }

  public enum SystemType {
    /**
     * 运营管理系统
     */
    OPERATION,
    /**
     * 多租户系统
     */
    ENTERPRISE,

  }


  public enum ImageType {

    /**
     * 头像
     */
    PHOTO

  }


  /**
   * 服务状态
   * 
   * @author sujinxuan
   *
   */
  public enum ServiceStatus {

    /** 可用 */
    ENABLED,

    /** 禁用 */
    DISABLED,

  }
  /**
   * 审核状态
   * 
   * @author tanbiao
   *
   */
  public enum ApplyStatus {
    /** 待审核 */
    AUDIT_WAITING,

    /** 审核通过 */
    AUDIT_PASSED,

    /** 审核退回 */
    AUDIT_FAILED

  }
  /**
   * 预约信息来源
   * 
   * @author huyong
   *
   */
  public enum ReservationInfoFrom {
    /**
     * 来自app
     */
    APP,
    /**
     * 电话预约
     */
    CALL
  }
}
