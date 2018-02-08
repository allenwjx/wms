# 卓尔航云物流平台
## 模块介绍
```
app/
	biz/ #核心服务模块，包含领域模型和商业逻辑代码
	model/ #模型模块，包含VO、枚举类
	dal/ #数据访问模块，数据库访问操作
	facade/ #服务API模块，提供给外部调用，仅包含API和DTO
	facade-impl/ #服务API的具体实现
	integration/ #外部服务集成模块，调用外部服务接口
	test/ #测试模块
	web/ #web 控制层
assembly/ #打包脚本
conf/ #配置文件
dalgen/ #dal sql语句编写模块
deploy/ #项目启动脚本
webdocs/ #静态资源模块
pom.xml
```