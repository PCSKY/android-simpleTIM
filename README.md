# android-simpleTIM
![Logo](https://github.com/PCSKY/android-simpleTIM/blob/master/img/ic_launcher.png)<br/>
这是一个模拟腾讯TIM客户端的一个Android app，实现了一个登陆注册的Demo，也实现了使用侧滑抽屉UI的主界面，该app仅用于熟悉Android开发的基础操作。
<br/><br/><br/>
# 开发环境
Windows10 + Android studio 3.3.0 + SQLite
<br/><br/><br/>
# 依赖包
| 依赖包        | 介绍           |
|:---------------:|:----------------------------|
| [pinyin4j.jar](https://github.com/belerweb/pinyin4j) | Pinyin4j是一个流行的Java库，支持中文字符和拼音之间的转换。拼音输出格式可以定制 |
# 登陆注册效果
* 登录流程<br/>
<img width="350" height="600" src="https://github.com/PCSKY/android-simpleTIM/blob/master/img/login.gif"/>

* 注册流程<br/>
<img width="350" height="600" src="https://github.com/PCSKY/android-simpleTIM/blob/master/img/regist.gif"/>

<br/><br/>
# 主要界面
<img width="350" height="600" src="https://github.com/PCSKY/android-simpleTIM/blob/master/img/loginUI.png"/>
<img width="350" height="600" src="https://github.com/PCSKY/android-simpleTIM/blob/master/img/registUI.png"/>
<img width="350" height="600" src="https://github.com/PCSKY/android-simpleTIM/blob/master/img/navigationView.png"/>
<img width="350" height="600" src="https://github.com/PCSKY/android-simpleTIM/blob/master/img/menuUI.png"/>

<br/><br/>
# 功能实现
* 登陆注册，数据存储使用SQLite
* 登陆注册信息提示，若不符合条件则会有警告
* 主界面的联系人信息表，导航侧滑菜单，浮动按钮，顶部menu
* 联系人列表侧边字母导航栏，通过把用户名转化为拼音进行实现
<br/><br/><br/>
# 数据库表结构
| 序号 | 字段名 | 字段类型 | 说明 | 备注 |
|:---------:|:---------|:---------|:---------|:---------|
| 1 | username | nvarchar(30) not null | 账号(用户名) | 关键字 |
| 2 | password | nvarchar(30) not null | 密码 |  |
| 3 |   name   | nvarchar(30) not null | 真实姓名 |  |
# 待完善
- [ ] 密码存储时通过加密算法进行加密存储
- [ ] 实现侧滑菜单的对应功能
- [ ] 注册进行限制，只有合法的QQ号，手机号，邮箱号可以允许注册(正则表达式)
- [ ] 登陆注册界面排布进行优化
- [ ] 密码错误次数达到上限则退出app
<br/><br/><br/>
# 参考链接
* [Android实现简易QQ](https://github.com/YEN-GitHub/Android_simpleQQ) <br/>
* [安卓项目SimpleQQ概述](https://blog.csdn.net/yen_csdn/article/details/53729978) <br/>
* [安卓客户端实现联网登录的功能](https://blog.csdn.net/qq_42289906/article/details/80715803) <br/>
* [TextInputLayout输入框](https://www.jianshu.com/p/27f9b5613bf9) <br/>
* [EditText设置IME动作问题](https://blog.csdn.net/mzhhzm008/article/details/51615592) <br/>
* [使用 pinyin4j API 将汉字转换为拼音](https://blog.csdn.net/sky_limitless/article/details/79443540) <br/>
* [Android Sqlite的使用](https://www.jianshu.com/p/7d098ef952d7) <br/>
* [Android Cursor 的使用细节](https://blog.csdn.net/zhw0596/article/details/80973268) <br/>
* [Android Handler之原理解析](https://www.jianshu.com/p/f7cabfe19720) <br/>
* [JAVA多线程](https://blog.csdn.net/zhanglong_4444/article/details/86579539) <br/>
* [Android 圆角按钮的实现](https://blog.csdn.net/tracydragonlxy/article/details/88552262) <br/>
* [CoordinatorLayout与Behavior使用指南](https://www.jianshu.com/p/488283f74e69) <br/>
* [Android Studio自带导航侧滑实现](https://blog.csdn.net/dzjin1234/article/details/79008269) <br/>
* [Toolbar + DrawerLayout实现侧滑菜单](https://www.cnblogs.com/Chenshuai7/p/5443358.html) <br/>
* [实现字母导航栏](https://blog.csdn.net/qq_30379689/article/details/52684312) <br/>
