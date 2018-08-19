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

- 开冰箱门时上传数据
    - url：'/fridge/fridgeAction/openFridge'
    - 方法：post
    - 传入数据格式：multipart/form-data
        ```
        包含的字段：
        fridgeId：冰箱id
        userId：用户id
        token：用户的token
        uploadImage：图片的二进制数据
        ```
    - 返回数据示例：
        ```
        {
            success: true
        }
        ```
    - 参照src/main/webapp/test/testfile.html
    - 在本地测试时需修改src/main/java/dao/impl/FridgeDaoImpl.java的fileBasePath变量

- 获得已存储的冰箱图片
    - url：'/fridge/fridgeAction/getFridgeImage'
    - 方法：get或post
    - 传入数据格式：application/x-www-form-urlencoded
        ```
        包含的字段：
        fridgeId：冰箱id
        userId：用户id
        token：用户的token
        ```
    - 返回数据格式：二进制流，content-type为image/*
    - 参照src/main/webapp/test/testfile.html
    - 在本地测试时需修改src/main/java/dao/impl/FridgeDaoImpl.java的fileBasePath变量

- 查看某一冰箱内所有物品
    - url: 'fridge/fridgeAction/getItems'
    - 方法: get
    - 传入数据示例:
        ```
        {
            userId: 1, // 用户id
            token: 'WLaO4VHgwHZyR2hEjYxY1Q==', // token
            fridgeId: 1 // 冰箱id
        }
        ```
    - 返回数据示例: 
        ```
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
        ```

- 改变某一冰箱内某一物品的数量
    - url: 'fridge/fridgeAction/changeItem'
    - 方法: post
    - 传入数据示例:
        ```
        {
            userId: 1, // 用户id
            token: 'WLaO4VHgwHZyR2hEjYxY1Q==', // token
            fridgeId: 1 // 冰箱id,
            itemId: 1,
            amount: 5  // 修改后的数量
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
            userId: 1, // 用户id
            token: 'WLaO4VHgwHZyR2hEjYxY1Q==', // token
            fridgeId: 1 // 冰箱id
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
                time: "2018-06-05T22:24:47"
            }
            ]
        }
        ```
        
- 设置用户和冰箱的关联
    - url：'/fridge/userAction/setRelationToFridge'
    - 方法：get或post
    - 传入数据示例：
        ```
        {
            userId: 1,
            fridgeId: 1,
            token: 'WLaO4VHgwHZyR2hEjYxY1Q=='
        }
        ```
    - 返回数据示例：
        ```
        {
            success: true
        }
        ```
        
- 获得和用户关联的所有冰箱
    - url：'/fridge/userAction/getRelationToFridge'
    - 方法：get或post
    - 传入数据示例：
        ```
        {
            userId: 1,
            token: 'WLaO4VHgwHZyR2hEjYxY1Q=='
        }
        ```
    - 返回数据示例：
        ```
        {
            success: true,
            result: 
            [
            {
                id: 1,    // 关系的id，客户端直接忽略即可
                fridgeId: 1,
                userId: 1
            }
            ]
        }
        ```
        
- 取消用户和某个冰箱的关系
    - url：'/fridge/userAction/delRelationToFridge'
    - 传入数据示例：
        ```
        {
            userId: 1,
            fridgeId: 1,
            token: 'WLaO4VHgwHZyR2hEjYxY1Q=='
        }
        ```
    - 返回数据示例：
        ```
        {
            success: true
        }
        ```

- 冰箱某个物品数量加一
    - url：'/fridge/userAction/increaseItem'
    - 传入数据示例：
        ```
        {
            fridgeId: 1,
            itemName: 'name',
        }
        ```
    - 返回数据示例：
        ```
        {
            result: "success" (失败则为"fail")
        }
        ```

- 冰箱某个物品数量减一，同时记录dailychange
    - url：'/fridge/userAction/decreaseItem'
    - 传入数据示例：
        ```
        {
            userId: 1,
            fridgeId: 1,
            itemName: 'name',
        }
        ```
    - 返回数据示例：
        ```
        {
            result: "success" (失败则为"fail")
        }
        ```

- 添加物品
    - url: '/fridge/fridgeAction/addItem',
    - 传入数据示例：
        ```
        {
            fridgeId: 1,
            itemName: 'name',
            amount: 1
        }
        ```
    - 返回数据示例：
        ```
        {
            success: true (失败则为false)
        }
        ```

- 删除物品
    - url: '/fridge/fridgeAction/deleteItem',
    - 传入数据示例：
        ```
        {
            fridgeId: 1,
            itemName: 'name'
        }
        ```
    - 返回数据示例：
        ```
        {
            success: true (失败则为false)
        }
        ```
        
- 获取某一用户今日的取出的物品（和getDailyChange相似，但是能获得一个用户的所有冰箱的数据，以及物品item的名称）
    - url: 'fridge/userAction/getEatingRecords'
    - 方法: get或post
    - 传入数据示例:
        ```
        {
            userId: 1, // 用户id
            token: 'WLaO4VHgwHZyR2hEjYxY1Q==', // token，可以不传
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
                itemName: "grape"
                userId: 1,
                amount: 1,
                time: "2018-06-05T22:24:47"
            }
            ]
        }
        ```