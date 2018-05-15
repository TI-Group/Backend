# database
对数据库结构的几个想法
## mysql & mongodb

### 用户
				* 用户id integer(20) primary key
				* 用户名 varchar(20)
				* 密码 varchar(20)
				* 角色 int(1)
				* 手机 integer(11)
	* 今日卡路里 integer(6)
        
    （角色包含普通用户、管理员等）

### 冰箱与用户联系
	* 联系id integer(21) primary key
	* 用户id integer(20) foreign key
				* 冰箱id integer(20)

### 物品信息
	* 物品id integer(20) primary key
	* 名称 varchar(20)
	* 预计保存时间（day） integer(3)
	* 卡路里 integer（5）
	
### 冰箱物品联系
	* 联系id integer(20) primary key
	* 物品id integer(20) foreign key
	* 物品名称（id=1即其他时，需要填写）varchar(20)
	* 数量 integer(10)
	* 冰箱id integer(20)
	* 剩余保存时间 integer(5)
    （如何判断是同一件物品？按照名称还是其他方式？或者取消数量属性？）

### 食谱
				* 食谱id
				* 食谱内容？（字符串？）


================================================================================

每半小时一次上传的数据的结构假想？
```
{ 
  冰箱:id, 
  时间:time, 
  物品列表:[
            { 
              品种:id, 
              名称:name, 
              剩余保存时间:rem_time, 
              卡路里:k,
              ...
             },
            {
              品种:id, 
              名称:name, 
              剩余保存时间:rem_time, 
              卡路里:k,
              ...
             },
             ...
           ] 
  ...
}
```