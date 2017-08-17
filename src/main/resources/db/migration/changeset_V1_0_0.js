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
        {_id: ObjectId(), text: '价格管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(4)},
        {_id: ObjectId(), text: '库存管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(5)},
        {_id: ObjectId(), text: '订单管理', expanded: true, url: '', isParent: true, leaf: false, sort: NumberInt(6)},
        {_id: ObjectId(), text: '分类维护', expanded: true, url: '', isParent: false, leaf: true, sort: NumberInt(31)},
        {_id: ObjectId(), text: '分类显示标签维护', expanded: true, url: '', isParent: false, leaf: true, sort: NumberInt(32)},
        {_id: ObjectId(), text: '商品信息维护', expanded: true, url: '', isParent: false, leaf: true, sort: NumberInt(33)},
        {_id: ObjectId(), text: '商品属性模板管理', expanded: true, url: '', isParent: false, leaf: true, sort: NumberInt(34)},
        {_id: ObjectId(), text: '商品上下架', expanded: true, url: '', isParent: false, leaf: true, sort: NumberInt(35)},
        {_id: ObjectId(), text: '商品定时上下架', expanded: true, url: '', isParent: false, leaf: true, sort: NumberInt(36)}
    ];
    db.menu.insert(menus);

    var menuForShop = [
        {
            _id: ObjectId("59954b9afc887f7225411e9c"),
            text: '商品信息维护',
            expanded: true,
            url: '',
            parentId: ObjectId("59954b9afc887f7225411e96"),
            isParent: false,
            leaf: true,
            sort: NumberInt(33)
        },
        {
            _id: ObjectId("59954b9afc887f7225411e9d"),
            text: '商品属性模板管理',
            expanded: true,
            parentId: ObjectId("59954b9afc887f7225411e96"),
            url: '',
            isParent: false,
            leaf: true,
            sort: NumberInt(34)
        }
    ];

    var admin = {
        _id: ObjectId(),
        code: 'ADMIN',
        displayName: '高级管理员',
        description: '',
        newAuthorities: authorities
    };

    var shop = {
        _id: ObjectId(),
        code: 'SHOP',
        displayName: '门店权限',
        description: '',
        newAuthorities: authorities,
        newMenus: menuForShop
    };

    var roles = [
        admin,
        shop
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