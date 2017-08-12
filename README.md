# IZhihuCollection
获取个人知乎收藏夹，本地离线保存和查看内容  


## 目前实现

获取个人收藏夹目录
  
  ![](https://github.com/Liamql/IZhihuCollection/blob/master/demo/d1.png)

获取收藏夹的信息和收藏的文章
  
  ![](https://github.com/Liamql/IZhihuCollection/blob/master/demo/d2.png)

获取文章内容
  
  ![](https://github.com/Liamql/IZhihuCollection/blob/master/demo/d3.png)


下拉刷新即可获取最新的数据（需要联网）
除此之外全程不需要网络连接，可离线使用
  

## 计划实现功能

* 解决请求获取收藏夹内文章时，因为网页动态加载产生的获取不全的问题
* 增加用户列表界面，可以添加你想要获取并保存收藏夹的用户
* 增加收藏夹界面显示文章第一张图片
* 完善UI
  
  

## 使用框架以及开源库

使用了我个人的一个MVP框架进行开发：[L-MVP](https://github.com/Liamql/L-MVP)  

开源库： retrofit2,Dagger2,Okhttp3,Glide,butterKnife,Gson

## TIPS

        目前只是个人使用，所以默认获取自己的知乎收藏夹，可以通过修改
        CollistActivity的userid改变获取的用户
        个人知乎收藏夹为:www.zhihu.com/people/xxx/collections
        把userid改为xxx即可