
# KnowWeather
**[Deprecated 目前外部接口已不可用，在未找到新的天气接口时废弃]**

**美观、实用的天气app,基于谷歌最新的**
[Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)架构和[微信Android模块化架构重构实践](https://mp.weixin.qq.com/s?__biz=MzAwNDY1ODY2OQ==&mid=2649286672&idx=1&sn=4d9db00c496fcafd1d3e01d69af083f9)对项目进行了重构
实践[Hub](https://github.com/SilenceDut/Hub)在项目中的使用


建议先下载应用[**knowweather.apk**](https://github.com/SilenceDut/KnowWeather/blob/master/apk/knowweather.apk?raw=true) 体验下，以免浪费你的时间O(∩_∩)O。

# 效果图
![](http://ww2.sinaimg.cn/large/006y8lVagw1faiecmxxx2j312w0dwtci.jpg)

# 整体框架



# 特点
 * 多moudle架构的实践
 * Android Architecture Components 实践
 * 支持县级、区级天气
 * JobScheduler与JobService的使用
 * 轮询系统定时更新天气
 * 简洁的界面
 * ...
 

 
# 天气接口使用声明
 
现在这个天气的接口用的是和风天气的免费接口，key值也直接暴露出来了，建议自己去申请使用，因为免费版每天有一定的请求限制。
 

# License

Copyright 2016 SilenceDut

Licensed under the Apache License, Version 2.0 (the "License");you may not use this 

file except in compliance with the License.You may obtain a copy of the License at

       [http://www.apache.org/licenses/LICENSE-2.0]

Unless required by applicable law or agreed to in writing, software distributed under 

the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 

ANY KIND, either express or implied. See the License for the specific language 

governing permissions and limitations under the License.



问题：
1.城市搜索中，为什么采用R2去定位资源
bufferknife的要求，后续研究一下为什么
#TODO

2.城市列表中的头字母，是怎样被初始化放上去的。
在获取数据的时候
通过对城市拼音的首字母进行匹配，初始化CityInfoData中initial字段
在展示的时候会根据initial来判断是否需要展示字母

3.location是如何定位到的
采用高德地图sdk来定位，目前返回错误如下：
auth fail:INVALID_USER_SCODE#SHA1AndPackage#B6:8E:D2:CA:7A:1B:73:01:1D:83:46:1A:29:A3:4E:5A:74:D3:6E:3A:com.silencedut.knowweather #gsid#011018233188162089305847300011370686301 #csid#8390923ebe0244b8ae06fb6d2b0c8a77
看高德观望的介绍是开发者账号权限的问题，后面再调整一下这里的账号

4.第一次进入app，默认为什么会弹出城市列表的搜索？
？
#TODO


5.目前学习到的内容
a.room的数据存储
b.VM的代码组织形式 （所有的数据通过model的方式来提供）
c.模块结构的划分方式（划分出基础模块，业务模块，APP模块，依赖关系的解耦合）
d.recyclerview的代码组织形式（adapter基类，viewholder基类等，以及代码的组织结构）（onCreateViewHolder通过反射来创建viewHolder的方式）
e.协议与业务逻辑在数据结构层面的解耦。
f.GSON 数据与类的序列化与反序列化
g.逐步向上滑动的效果是如何实现的
Android自带控件CollapsingToolbarLayout实现了伸缩栏折叠的效果，配合AppBarLayout/NestedScrollView/RecyclerView使用，达到折叠效果
h.
i.
j.
l.
m.
n.

6.通知栏的更新效果实现方式
a.jobSchedule 和alarmManager 定时更新的任务

7.通过AppWidgetProvider实现窗口小工具（可在桌面布局小工具）

8.修改weather为独立的模块
a.模块的包名定义在AndroidManifest.xml中的package中，通过对应的包名可以找到R.java的文件位置（有别的办法修改吗？）
b.默认的资源放在src/main/res目录下面（目录位置是否可以修改？）
c.把weahter_fragment_main.xml放进drawable目录，会报错，错误显示recyclerview找不到。
d.butterknife需要使用R2才能引用资源，需要深入研究一下原因



