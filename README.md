# AndroidMvpFrame
一个App快速开发Mvp Android框架: 整体MVP结构，包括工具包、基类、控件包、通信、存储等，可以快速进行项目开发。
特点：以MVP作为框架，网络通信使用Retrofit/RxJava2.0, 组件通信EventBus, 图片加载Fresco。

采用的开源库包括：

- RxJava
- Retrofit2
- OKHttp3
- Slidr

 提供MVP基类封装，使用者直接继承即可使用。



## 使用

Module gradle文件加入如下引用：

```
implementation 'com.blink:framelibrary:1.0.x'
```