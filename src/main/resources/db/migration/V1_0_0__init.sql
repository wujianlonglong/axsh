CREATE TABLE t_menu (
  id          BIGINT PRIMARY KEY  NOT NULL AUTO_INCREMENT,
  expanded    BOOLEAN             NOT NULL
  COMMENT '是否打开，在菜单树中使用',
  is_parent   BOOLEAN             NOT NULL
  COMMENT '是否是父菜单',
  leaf        BOOLEAN             NOT NULL
  COMMENT '是否是子菜单',
  text        VARCHAR(255) COMMENT '菜单名称',
  url         VARCHAR(255) COMMENT '绑定地址',
  parent_menu BIGINT COMMENT '父菜单',
  sort        INT COMMENT '展示顺序'
);

INSERT INTO t_menu VALUES (1, 0, 1, 0, '系统设置', '', NULL, 3), (2, 0, 0, 1, '部门管理', 'orglist', 1, 31),
  (3, 0, 0, 1, '人员管理', 'userlist', 1, 32), (4, 0, 0, 1, '角色管理', 'rolelist', 1, 33),
  (5, 0, 0, 1, '权限资源管理', 'authoritylist', 1, 35), (6, 0, 0, 1, '菜单管理', 'menulist', 1, 34),
  (8, 0, 1, 0, '用户管理', '', NULL, 1), (9, 0, 0, 1, '用户查询', 'sjes_userlist', 8, 11),
  (10, 0, 0, 1, '用户统计', 'userstatistics', 8, 12), (11, 0, 0, 1, '积分管理', 'sjes_integallist', 8, 13),
  (12, 0, 0, 1, '黑名单管理', 'sjes_blacklist', 8, 14), (13, 0, 1, 0, '订单管理', '', NULL, 2),
  (14, 0, 0, 1, '自动审核机制', 'autoauditmechanism', 13, 21),
  (15, 0, 0, 1, '订单查询', 'orderSearch_list', 13, 22),
  (16, 0, 0, 1, '订单审核', 'orderToExamine_list', 13, 23);


CREATE TABLE t_authority (
  id            BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  authorityname VARCHAR(255) COMMENT '权限名称',
  authoritytype INT COMMENT '权限种类 1:列表 2:保存  3:删除',
  description   VARCHAR(255) COMMENT '描述',
  displayref    VARCHAR(255) COMMENT '显示名称',
  menu          BIGINT COMMENT '所属菜单'
);

INSERT INTO t_authority VALUES (1, 'ROLE_LIST', 1, '角色列表', '角色列表', 4), (2, 'ROLE_SAVE', 2, '角色保存', '角色保存', 4),
  (3, 'ROLE_DELETE', 3, '角色删除', '角色删除', 4), (4, 'ORG_LIST', 1, '部门列表', '部门列表', 2),
  (5, 'ORG_SAVE', 2, '部门保存', '部门保存', 2), (6, 'ORG_DELETE', 3, '部门删除', '部门删除', 2),
  (7, 'USER_LIST', 1, '用户列表', '用户列表', 3), (8, 'USER_SAVE', 2, '用户保存', '用户保存', 3),
  (9, 'USER_DELETE', 3, '用户删除', '用户删除', 3), (10, 'ROLE_CONFIGURE', 4, '角色配置', '角色配置', 4),
  (11, 'AUTHORITY_LIST', 1, '权限资源列表', '权限资源列表', 5), (12, 'AUTHORITY_SAVE', 2, '权限资源保存', '权限资源保存', 5),
  (13, 'AUTHORITY_DELETE', 3, '权限资源删除', '权限资源删除', 5), (14, 'MENU_LIST', 1, '菜单列表', '菜单列表', 6),
  (15, 'MENU_SAVE', 2, '菜单保存', '菜单保存', 6), (16, 'MENU_DELETE', 3, '菜单删除', '菜单删除', 6),
  (17, 'SJESUSER_LIST', 1, '商城用户查询', '商城用户查询', 9), (18, 'SJESUSER_SAVE', 2, '商城用户保存', '商城用户保存', 9),
  (19, 'SJESUSER_DELETE', 3, '商城用户删除', '商城用户删除', 9), (20, 'USERSTATISTICS_LIST', 1, '用户统计查询', '用户统计查询', 10),
  (21, 'USERSTATISTICS_REPORT', 5, '用户统计导出', '用户统计导出', 10), (22, 'INTEGRAL_LIST', 1, '  积分管理', '  积分管理', 11),
  (23, 'BLACK_LIST', 1, '黑名单列表', '黑名单列表', 12), (24, 'BLACK_SAVE', 2, '黑名单保存', '黑名单保存', 12),
  (25, 'BLACK_DELETE', 3, '黑名单删除', '黑名单删除', 12), (26, 'AUTOAUDITMECHANISM_SAVE', 2, '自动审核机制保存', '自动审核机制保存', 14),
  (27, 'ORDERSEARCH_LIST', 1, '订单查询', '订单查询', 15), (28, 'ORDERTOEXAMINE_LIST', 1, '订单审核列表', '订单审核列表', 16),
  (29, 'ORDERTOEXAMINE_SAVE', 2, '订单审核操作', '订单审核操作', 16);


CREATE TABLE t_org (
  id          BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  description VARCHAR(255) COMMENT '描述',
  org_name    VARCHAR(255) COMMENT '部门名称',
  org_num     VARCHAR(255) COMMENT '部门编号',
  manager     BIGINT COMMENT '部门管理员',
  parentorg   BIGINT COMMENT '父部门'
);

INSERT INTO t_org VALUES
  (1, '三江购物', '三江购物', '001', NULL, NULL),
  (2, '全渠道', '全渠道', '002', NULL, 1),
  (3, '市场部', '市场部', '003', NULL, 1),
  (4, '人事部', '人事部', '004', NULL, 1);


CREATE TABLE t_user (
  id                  BIGINT(20) NOT NULL AUTO_INCREMENT,
  account_enabled     INT COMMENT '帐户是否过期',
  account_locked      INT COMMENT '帐户是否锁住',
  credentials_expired INT COMMENT '凭证是否过期',
  description         VARCHAR(255) COMMENT '描述',
  full_name           VARCHAR(255) COMMENT '全名',
  mobile              VARCHAR(255) COMMENT '手机号',
  password            VARCHAR(255) COMMENT '密码',
  user_mgr_type       INT COMMENT '用户类型',
  username            VARCHAR(255) COMMENT '用户名',
  org                 BIGINT(20) COMMENT '所属部门',
  expired_date        VARCHAR(45) COMMENT '过期时间',
  PRIMARY KEY (id)
);


INSERT INTO t_user VALUES
  (1, 1, 0, 0, 'bb', 'bb', '13658459685', '9b26519936c512d317d089220fa71982', 1402, 'bb', 2, '2015-12-16 00:00:00'),
  (2, 1, 0, 0, '超级管理员', '超级管理员', '13254784512', '5621c456da3b2298eb1d10a70832c08a', 1400, 'administrator', 2,
   '2015-12-16 00:00:00'),
  (6, 1, 0, 0, 'aa', 'aa', '13212121212', '02460058f588996b3636da29eb771b88', 1401, 'aa', 2, '2015-12-31 00:00:00'),
  (13, 1, 0, 0, 'dd', 'dd', '13212121212', 'df8367628a73dab3c796534d692cd219', 1402, 'dd', 3, '2015-11-30 00:00:00'),
  (14, 1, 0, 0, 'ee', 'ee', '13212121212', '3fd0651a1b03e15ae298adbf030e3c9b', 1402, 'ee', 4, '2015-11-30 00:00:00');

CREATE TABLE t_role (
  id          BIGINT(20) NOT NULL AUTO_INCREMENT,
  description VARCHAR(255) COMMENT '描述',
  displayref  VARCHAR(255) COMMENT '显示名称',
  name        VARCHAR(255) COMMENT '角色名称',
  PRIMARY KEY (id)
);


INSERT INTO t_role VALUES (1, '系统管理员', '系统管理员', 'manager'), (2, '普通用户', '普通用户', 'normal');


CREATE TABLE t_roleauthority (
  id        BIGINT NOT NULL AUTO_INCREMENT,
  authority BIGINT COMMENT '权限',
  role      BIGINT COMMENT '角色',
  PRIMARY KEY (id)
);

INSERT INTO t_roleauthority
VALUES (1, 1, 1), (2, 2, 1), (3, 3, 1), (4, 4, 1), (5, 5, 1), (6, 6, 1), (7, 7, 1), (8, 8, 1), (9, 9, 1), (20, 10, 1),
  (69, 17, 2), (70, 19, 2), (71, 18, 2), (72, 27, 2);


CREATE TABLE t_user_authority (
  id             BIGINT NOT NULL AUTO_INCREMENT,
  authority      BIGINT,
  role_authority BIGINT,
  securityuser   BIGINT,
  PRIMARY KEY (id)
);

INSERT INTO t_user_authority
VALUES (1, 1, 1, 6), (2, 2, 2, 6), (3, 3, 3, 6), (4, 4, 4, 6), (5, 5, 5, 6), (6, 6, 6, 6), (7, 7, 7, 6), (8, 8, 8, 6),
  (9, 9, 9, 6), (21, 10, 20, 6), (101, 17, 69, 1), (102, 18, 71, 1), (103, 19, 70, 1), (104, 19, 70, 13),
  (105, 17, 69, 13), (106, 18, 71, 13), (107, 17, 69, 14), (108, 19, 70, 14), (109, 18, 71, 14), (110, 27, 72, 1),
  (111, 27, 72, 13), (112, 27, 72, 14);


CREATE TABLE t_user_role (
  id           BIGINT(20) NOT NULL AUTO_INCREMENT,
  role         BIGINT(20)          DEFAULT NULL,
  securityuser BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO t_user_role VALUES (1, 1, 6), (3, 2, 1), (4, 2, 13), (5, 2, 14);