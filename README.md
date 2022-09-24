2.7.3 springboot  
jdk1.8
maven初始化的时候 跳过测试  

#####postman邀请地址:  
https://app.getpostman.com/join-team?invite_code=747aff31872c0821dcf6ab7212a652df

###注:  
1. 拦截器完成，未配置，整合时配置(优化，拦截时，session换线程)
2. 注册功能可优化，接口注解
3. 修改了数据库文件，可以重新配置一下数据库
4. 模拟用户访问功能不太完善，一个线程随机插入1-20条数据，总人数维持在100-160之间，插入条件还没加，比如博物馆人数达到上限停止访问，场馆总人数到达上限之后停止进入场馆。

###完成:  
1. 登录功能完成  
2. 注册功能完成
3. 个人信息页面功能完成
4. 数据模拟功能完成（能凑合用，可以完善）
5. 查询7日内数据（一半）


###自行插入场馆数据：    
INSERT INTO `tb_venue` VALUES (1, 'venue 1', 'level 1', 'First venue', 300);  
INSERT INTO `tb_venue` VALUES (2, 'venue 2', 'level 2', 'Second venue', 300);  
INSERT INTO `tb_venue` VALUES (3, 'venue 2', 'level 3', 'Third venue', 300);  
