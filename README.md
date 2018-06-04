# Backend
server side

## 目前App可用的接口
- 注册：
    - url: '/fridge/userAction/userSignup'
    - 方法: post
    - 传入数据示例: 
        ```
        {
            username: 'uname',
            password: 'pw',
            tel: '54749110',
            role: 1 // 用户身份，0表示管理员，1表示普通用户
        }
        ```
    - 返回数据示例:
        ```
        {
            result: true
        }
        ```

- 用户登录
    - url: '/fridge/userAction/userLogin'
    - 方法: post
    - 传入数据示例:
        ```
        {
            username: '54749110', // 这里的username实际上是用户的手机号
            password: 'pw'
        }
        ```
    - 返回数据示例:
        ```
        {
            user_id: 1, // 用户id, 登录失败时为0
            token: "WLaO4VHgwHZyR2hEjYxY1Q==" // 一个Token字符串, 登录失败时为null(用户身份为'管理员'时也会登录失败, 管理员登录应该使用'adminLogin'方法但我想App应该不会用到它)
        }
        ```

## 尚未实现的接口
- 查看某一冰箱内所有物品
    - url: 'fridge/fridgeAction/getItems'
    - 方法: get
    - 传入数据示例:
        ```
        {
            user_id: 1, // 用户id
            token: 'WLaO4VHgwHZyR2hEjYxY1Q==', // token
            fridge: 1 // 冰箱id
        }
        ```
    - 返回数据示例: 
        ```
        {
            {
                success: true,
                result: [
                {
                    itemId: 1,
                    itemName: '苹果',
                    amount: 1,
                    remainTime: 1  // 预计还能存放的时间
                },
                {
                    itemId: 2,
                    itemName: '橘子',
                    amount: 1,
                    remainTime: 1  // 预计还能存放的时间
                }
                ]
            }
        }
        ```

- 改变某一冰箱内某一物品的数量
    - url: 'fridge/fridgeAction/changeItem'
    - 方法: post
    - 传入数据示例:
        ```
        {
            user_id: 1, // 用户id
            token: 'WLaO4VHgwHZyR2hEjYxY1Q==', // token
            fridge: 1 // 冰箱id,
            itemId: 1,
            amount: -1
        }
        ```
    - 返回数据示例:
        ```
        {
            success: true
        }
        ```

- 获取某一冰箱今日的物品变化情况
    - url: 'fridge/fridgeAction/getDailyChange'
    - 方法: get
    - 传入数据示例:
        ```
        {
            user_id: 1, // 用户id
            token: 'WLaO4VHgwHZyR2hEjYxY1Q==', // token
            fridge: 1 // 冰箱id
        }
        ```
    - 返回数据示例:
        ```
        {
            success: true,
            result: 
            [
            {
                changeId: 1,
                fridgeId: 1,
                itemId: 1,
                userId: 1,
                amount: 1,
                time: 
            }
            ]
        }
        ```