//mongeez formatted javascript

//changeset fudongwei:01-init runAlways:true // todo must remove at production
(function () {
    // todo remove at production

    db.authority.remove({});
    db.menu.remove({});
    db.role.remove({});
    db.user.remove({});


    // @formatter:off

    var authorities = [
        {_id: ObjectId(), code: 'LIST', name: '列表', description: '', enabled: true},
        {_id: ObjectId(), code: 'SAVE', name: '保存', description: '', enabled: true},
        {_id: ObjectId(), code: 'DELETE', name: '删除', description: '', enabled: true},
        {_id: ObjectId(), code: 'EXPORT', name: '导出', description: '', enabled: true},
        {_id: ObjectId(), code: 'PRINT', name: '打印', description: '', enabled: true},
        {_id: ObjectId(), code: 'SEPARATE', name: '拆单', description: '', enabled: true}
    ];
    db.authority.insert(authorities);

    var menus = [
        {_id: ObjectId(), text: '门店管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(1)},
        {_id: ObjectId(), text: '商品管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(2)},
        {_id: ObjectId(), text: '安鲜商品管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(3)},
        {_id: ObjectId(), text: '安鲜APP管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(4)},
        {_id: ObjectId(), text: '安鲜促销管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(5)},
        {_id: ObjectId(), text: '安鲜用户管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(6)},
        {_id: ObjectId(), text: '价格管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(7)},
        {_id: ObjectId(), text: '库存管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(8)},
        {_id: ObjectId(), text: '订单管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(9)},
        {_id: ObjectId(), text: '财务管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(10)},
        {_id: ObjectId(), text: '系统管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(11)},


        // 安鲜商品管理
        {
            _id: ObjectId(),
            text: '分类维护',
            expanded: true,
            url: 'anxian/sjes_category/category',
            isParent: false,
            leaf: true,
            sort: NumberInt(31)
        },
        {
            _id: ObjectId(),
            text: '分类显示标签维护',
            expanded: true,
            url: 'anxian/sjes_category/categoryTag',
            isParent: false,
            leaf: true,
            sort: NumberInt(32)
        },
        {
            _id: ObjectId(),
            text: '商品信息维护',
            expanded: true,
            url: '/anxian/sjes_product/informationList?page=1&limit=10',
            isParent: false,
            leaf: true,
            sort: NumberInt(33)
        },
        {
            _id: ObjectId(),
            text: '商品上下架',
            expanded: true,
            url: '/anxian/sjes_product/informationList?page=0&limit=10',
            isParent: false,
            leaf: true,
            sort: NumberInt(34)
        },
        {
            _id: ObjectId(),
            text: '商品定时上下架',
            expanded: true,
            url: '/anxian/productStatusPlan/search',
            isParent: false,
            leaf: true,
            sort: NumberInt(35)
        },
        // 安鲜APP管理
        {
            _id: ObjectId(),
            text: '版本管理',
            expanded: true,
            url: 'anxian/versions',
            isParent: false,
            leaf: true,
            sort: NumberInt(41)
        },
        {
            _id: ObjectId(),
            text: 'APP首页热销管理',
            expanded: true,
            url: 'anxian/appHotgood/main',
            isParent: false,
            leaf: true,
            sort: NumberInt(42)
        },
        {
            _id: ObjectId(),
            text: 'APP首页菜单管理',
            expanded: true,
            url: 'anxian/appMenu/main',
            isParent: false,
            leaf: true,
            sort: NumberInt(43)
        },
        {
            _id: ObjectId(),
            text: 'APP首页楼层管理',
            expanded: true,
            url: 'anxian/appFloor/main',
            isParent: false,
            leaf: true,
            sort: NumberInt(44)
        },
        // 安鲜促销管理
        {
            _id: ObjectId(),
            text: '促销同步',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(51)
        },
        // 安鲜用户管理
        {
            _id: ObjectId(),
            text: '用户查询',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(61)
        },
        {
            _id: ObjectId(),
            text: '黑名单管理',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(62)
        },
        // 价格管理
        {
            _id: ObjectId(),
            text: '批量导入',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(71)
        },
        {
            _id: ObjectId(),
            text: '价格管理',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(72)
        },

        // 库存管理
        {
            _id: ObjectId(),
            text: '库存同步管理',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(81)
        },
        {
            _id: ObjectId(),
            text: '虚拟库存管理',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(82)
        },
        {
            _id: ObjectId(),
            text: '库存锁定管理',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(83)
        },
        // 订单管理
        {
            _id: ObjectId(),
            text: '订单查询',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(91)
        },
        {
            _id: ObjectId(),
            text: '取消订单',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(92)
        },
        {
            _id: ObjectId(),
            text: '在线支付',
            expanded: true,
            url: '/anXian/pay/payList',
            isParent: false,
            leaf: true,
            sort: NumberInt(101)
        },
        // 系统管理
        {
            _id: ObjectId(),
            text: '用户管理',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(111)
        },
        {
            _id: ObjectId(),
            text: '角色管理',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(112)
        },
        {
            _id: ObjectId(),
            text: '菜单管理',
            expanded: true,
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(113)
        }
    ];
    db.menu.insert(menus);

    // var menuForShop = [
    //     {
    //         _id: ObjectId("59954b9afc887f7225411e9c"),
    //         text: '商品信息维护',
    //         expanded: true,
    //         url: '',
    //         parentId: ObjectId("59954b9afc887f7225411e96"),
    //         isParent: false,
    //         leaf: true,
    //         sort: NumberInt(33)
    //     },
    //     {
    //         _id: ObjectId("59954b9afc887f7225411e9d"),
    //         text: '商品属性模板管理',
    //         expanded: true,
    //         parentId: ObjectId("59954b9afc887f7225411e96"),
    //         url: '',
    //         isParent: false,
    //         leaf: true,
    //         sort: NumberInt(34)
    //     }
    // ];

    var menuForAdmin = [
        // 安鲜商品管理
        {
            _id: ObjectId(),
            text: '分类维护',
            expanded: true,
            url: 'anxian/sjes_category/category',
            parentId: ObjectId("59a7861c658eb391d8bfca57"),
            isParent: false,
            leaf: true,
            sort: NumberInt(31)
        },
        {
            _id: ObjectId(),
            text: '分类显示标签维护',
            expanded: true,
            url: 'anxian/sjes_category/categoryTag',
            parentId: ObjectId("59a7861c658eb391d8bfca57"),
            isParent: false,
            leaf: true,
            sort: NumberInt(32)
        },
        {
            _id: ObjectId(),
            text: '商品信息维护',
            expanded: true,
            url: '/anxian/sjes_product/informationList?page=1&limit=10',
            parentId: ObjectId("59a7861c658eb391d8bfca57"),
            isParent: false,
            leaf: true,
            sort: NumberInt(33)
        },
        {
            _id: ObjectId(),
            text: '商品上下架',
            expanded: true,
            url: '/anxian/sjes_product/informationList?page=0&limit=10',
            parentId: ObjectId("59a7861c658eb391d8bfca57"),
            isParent: false,
            leaf: true,
            sort: NumberInt(34)
        },
        {
            _id: ObjectId(),
            text: '商品定时上下架',
            expanded: true,
            url: '/anxian/productStatusPlan/search',
            parentId: ObjectId("59a7861c658eb391d8bfca57"),
            isParent: false,
            leaf: true,
            sort: NumberInt(35)
        },
        // 安鲜APP管理
        {
            _id: ObjectId(),
            text: '版本管理',
            expanded: true,
            url: 'anxian/versions',
            parentId: ObjectId("59a7861c658eb391d8bfca58"),
            isParent: false,
            leaf: true,
            sort: NumberInt(41)
        },
        {
            _id: ObjectId(),
            text: 'APP首页热销管理',
            expanded: true,
            url: 'anxian/appHotgood/main',
            parentId: ObjectId("59a7861c658eb391d8bfca58"),
            isParent: false,
            leaf: true,
            sort: NumberInt(42)
        },
        {
            _id: ObjectId(),
            text: 'APP首页菜单管理',
            expanded: true,
            url: 'anxian/appMenu/main',
            parentId: ObjectId("59a7861c658eb391d8bfca58"),
            isParent: false,
            leaf: true,
            sort: NumberInt(43)
        },
        {
            _id: ObjectId(),
            text: 'APP首页楼层管理',
            expanded: true,
            url: 'anxian/appFloor/main',
            parentId: ObjectId("59a7861c658eb391d8bfca58"),
            isParent: false,
            leaf: true,
            sort: NumberInt(44)
        },
        // 安鲜促销管理
        {
            _id: ObjectId(),
            text: '促销同步',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca59"),
            isParent: false,
            leaf: true,
            sort: NumberInt(51)
        },
        // 安鲜用户管理
        {
            _id: ObjectId(),
            text: '用户查询',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5a"),
            isParent: false,
            leaf: true,
            sort: NumberInt(61)
        },
        {
            _id: ObjectId(),
            text: '黑名单管理',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5a"),
            isParent: false,
            leaf: true,
            sort: NumberInt(62)
        },
        // 价格管理
        {
            _id: ObjectId(),
            text: '批量导入',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5b"),
            isParent: false,
            leaf: true,
            sort: NumberInt(71)
        },
        {
            _id: ObjectId(),
            text: '价格管理',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5b"),
            isParent: false,
            leaf: true,
            sort: NumberInt(72)
        },

        // 库存管理
        {
            _id: ObjectId(),
            text: '库存同步管理',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5c"),
            isParent: false,
            leaf: true,
            sort: NumberInt(81)
        },
        {
            _id: ObjectId(),
            text: '虚拟库存管理',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5c"),
            isParent: false,
            leaf: true,
            sort: NumberInt(82)
        },
        {
            _id: ObjectId(),
            text: '库存锁定管理',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5c"),
            isParent: false,
            leaf: true,
            sort: NumberInt(83)
        },
        // 订单管理
        {
            _id: ObjectId(),
            text: '订单查询',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5d"),
            isParent: false,
            leaf: true,
            sort: NumberInt(91)
        },
        {
            _id: ObjectId(),
            text: '取消订单',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5d"),
            isParent: false,
            leaf: true,
            sort: NumberInt(92)
        },
        // 系统管理
        {
            _id: ObjectId(),
            text: '用户管理',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5f"),
            isParent: false,
            leaf: true,
            sort: NumberInt(111)
        },
        {
            _id: ObjectId(),
            text: '角色管理',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5f"),
            isParent: false,
            leaf: true,
            sort: NumberInt(112)
        },
        {
            _id: ObjectId(),
            text: '菜单管理',
            expanded: true,
            url: '',
            parentId: ObjectId("59a7861c658eb391d8bfca5f"),
            isParent: false,
            leaf: true,
            sort: NumberInt(113)
        }
    ];

    var admin = {
        _id: ObjectId(),
        code: 'ADMIN',
        displayName: '高级管理员',
        description: '',
        newAuthorities: authorities,
        newMenus: menuForAdmin
    };

    // var shop = {
    //     _id: ObjectId(),
    //     code: 'SHOP',
    //     displayName: '门店权限',
    //     description: '',
    //     newAuthorities: authorities,
    //     newMenus: menuForShop
    // };

    var roles = [
        admin
    ];
    db.role.insert(roles);

    // @formatter:on

    db.user.insert({
        username: 'admin',
        password: '$2a$10$jWpdqT3iJtQG5PxhIqI5jeCzaX2LrNkuX/x03MnjK6N6AMI2Amjnm', // password: '123456a'
        newRole: admin,
        fullName: '高级管理员',
        accountLocked: false
    });

    db.user.insert({
        username: 'shop',
        password: '$2a$10$jWpdqT3iJtQG5PxhIqI5jeCzaX2LrNkuX/x03MnjK6N6AMI2Amjnm', // password: '123456a'
        newRole: shop,
        fullName: '门店员工1',
        accountLocked: false
    });

})();
