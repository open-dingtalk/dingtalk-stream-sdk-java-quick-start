# DingTalk Stream Mode 介绍

----

Java SDK for DingTalk Stream Mode API, Compared with the webhook mode, it is easier to access the DingTalk chatbot

钉钉支持 Stream 模式接入事件推送、机器人收消息以及卡片回调，该 SDK 实现了 Stream 模式。相比 Webhook 模式，Stream 模式可以更简单的接入各类事件和回调。

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
可以搜索共创群，答疑交流。共创群ID：35365014813 （钉钉搜索群号入群）；
也可以扫码入群：

![img_2.png](https://gw.alicdn.com/imgextra/i1/O1CN01Cl10lw1OrfW9LdIgQ_!!6000000001759-0-tps-585-765.jpg)

