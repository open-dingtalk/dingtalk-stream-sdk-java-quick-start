# DingTalk Stream Mode 介绍

----

Java SDK for DingTalk Stream Mode API, Compared with the webhook mode, it is easier to access the DingTalk chatbot

钉钉支持 Stream 模式接入事件推送、机器人收消息以及卡片回调，该 SDK 实现了 Stream 模式。相比 Webhook 模式，Stream 模式无需暴露公网服务即可快速接入各类事件和回调,可参考[Stream模式钉钉官方文档](https://open.dingtalk.com/document/resourcedownload/Introduction-to-stream-mode)。本示例工程将展示如何使用 Stream 模式快速创建钉钉机器人。


## Dingtalk Stream Java SDK

----
```xml
<dependency>
    <groupId>com.dingtalk.open</groupId>
    <artifactId>app-stream-client</artifactId>
    <version>1.0.5</version>
</dependency>
```


## 快速开始

----

1、创建企业内部应用

进入 [钉钉开发者后台](https://open-dev.dingtalk.com/#/) ，创建企业内部应用，获取ClientID（即 AppKey）和ClientSecret（ 即AppSecret）。

发布应用：在开发者后台左侧导航中，点击“版本管理与发布”，点击“确认发布”，并在接下来的可见范围设置中，选择“全部员工”，或者按需选择部分员工。

2、Stream 模式的机器人（可选）

如果不需要使用机器人功能的话，可以不用创建。

注意：消息接收模式中，选择 “Stream 模式”
![img.png](https://img.alicdn.com/imgextra/i3/O1CN01XL4piO1lkYX2F6sW6_!!6000000004857-0-tps-896-522.jpg)

点击“点击调试”按钮，可以创建测试群进行测试。

测试效果
![img_1.png](https://s1.ax1x.com/2023/05/16/p92jjIJ.png)

技术支持

[点击链接，加入Stream模式共创群交流](https://open-dingtalk.github.io/developerpedia/docs/explore/support/?via=moon-group)
