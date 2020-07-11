# CommenrView
简洁、高效、可自定义的开源的Android评论控件---CommentView

## 控件的使用
### 1、控件方法介绍

> **以下是控件对外公布的所有业务方法。**

| 方法 | 参数 | 说明 |
|--|--|--|
|  .callbackBuilder()| \ |获取CallbackBuilder实例设置需要的回调， **无论是否设置回调，都必须调用.callbackBuilder().buildCallback()方法完成初始化。并且初始化必须在loadComplete()调用之前完成。**| |
|  .loadComplete()|AbstractCommentModel |**首次加载数据**时调用此方法， **注意：设置回调，设置空数据视图、设置错误视图、设置自定义样式配置器，设置头布局，这5个方法都必须要在loadComplete()这个方法调用之前调用，否则这5个方法失效。**| |
|  .loadFailed()|boolean isShowErrorView |首次加载数据失败时调用，boolean参数表示是否显示错误视图。| |
|  .refreshComplete()|AbstractCommentModel  |刷新数据完成后调用，传入刷新后的数据实体类。该方法调用后OnPullRefreshCallback的complete()方法会被调用。| |
|  .refreshFailed()|String msg，boolean isShowErrorView |数据刷新失败时调用，msg表示错误信息，boolean参数表示是否显示错误视图。该方法调用后OnPullRefreshCallback的failure(String msg)方法会被调用。| |
|  .loadMoreComplete()|AbstractCommentModel  |加载更多评论数据完成后调用，传入一个数据实体类。该方法调用后OnCommentLoadMoreCallback的complete()方法会被调用。| |
|  .loadMoreFailed()|String msg，boolean isShowErrorView |加载更多评论数据失败时调用，msg表示错误信息，boolean参数表示是否显示错误视图。该方法调用后OnCommentLoadMoreCallback的failure(String msg)方法会被调用。| |
|  .loadMoreReplyComplete()|AbstractCommentModel  |加载更多回复数据完成后调用，传入一个数据实体类。该方法调用后OnReplyLoadMoreCallback的complete()方法会被调用。| |
|  .loadMoreReplyFailed()|String msg，boolean isShowErrorView |加载更多评论数据失败时调用，msg表示错误信息，boolean参数表示是否显示错误视图。该方法调用后OnReplyLoadMoreCallback的failure(String msg)方法会被调用。| |
|  .getCommentList()| \ |获取所有评论数据返回List| |
|  .getReplyList()| int position |根据position所在的评论获取所在评论的所有回复数据返回List| |
|  .addComment()|C<？extend CommentEnable> comment|添加一条评论数据到当前的评论数据集合中| |
|  .addReply()|R<？extend ReplyEnable> comment，int position|添加一条回复数据到position所在的评论的回复数据集合中| |
|  .setEmptyView()| View view |为控件设置空数据视图，**注意：此方法必须在loadComplete()方法调用前调用，也就是说要在首次加载数据前调用，否则此方法无效。**| |
|  .setErrorView()| View view |为控件设置错误视图，**注意：此方法必须在loadComplete()方法调用前调用，也就是说要在首次加载数据前调用，否则此方法无效。**| |
|  .addHeaderView()| View view，boolean canClickable |为控件添加头视图，并且设置该视图是否响应点击事件。**注意：此方法建议在loadComplete()方法调用前调用。**| |
|  .removeHeaderView()| View view |移除当前控件的对应的头视图| |
|  .setViewStyleConfigurator()| ViewStyleConfigurator |设置自定义的样式配置器，**注意：此方法必须在loadComplete()方法调用前调用，也就是说要在首次加载数据前调用，否则此方法无效。**| |
|--|--|--|



### 2、基本使用
**第一步：引入控件库：**

有两种方法：

**1、远程仓库**

在module的build.gradle中添加jcenter仓库：

```java
buildscript {
    repositories {
        jcenter()
    }
}
```
然后在dependencies模块中添加依赖即可：

```java
implementation 'com.jidcoo.android.widget.commentview:CommentView:1.0.0'
```
**2、本地仓库**

把源码包下载下来，把commentview库放在与当前module的同级。

然后在dependencies模块中添加本地依赖即可：

```java
implementation project(path: ':commentview')
```


**第二步：引入控件：**

控件的引入方法有两种：

1、XML布局文件中引入

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:id="@+id/container"
    android:layout_height="match_parent">


  <com.jidcoo.android.widget.commentview.CommentView
      android:id="@+id/commentView"
      android:layout_width="match_parent"
      android:layout_height="match_parent"/>
</LinearLayout>
```
然后在Activity中实例化控件。

2、Java代码中动态创建

```java
//控件容器
LinearLayout mContainer;
CommentView commentView=new CommentView(this,mContainer);

```
CommentView的构造方法：
public CommentView(Context context, ViewGroup attachTo)

第一个参数是Activity的上下文。第二个参数是ViewGroup，也就是**将控件挂靠在这个指定的布局上**。注意：**当attachTo参数为空时，需要手动把控件添加到布局中，否则控件将不会显示**。


**第三步：初始化控件：**

**设置自定义样式配置器**：

如果使用默认样式的话就不需要调用这个方法，如果使用自定义样式配置器时调用该方法必须在loadComplete()调用前调用，否则该方法无效。

```java
commentView.setViewStyleConfigurator(你的样式配置器);
```

**设置空数据视图**：
如果不需要设置空数据视图就不需要调用这个方法，如果需要设置空数据视图时调用该方法必须在loadComplete()调用前调用，否则该方法无效。
```java
commentView.setEmptyView(你的空数据视图);
```

**设置错误视图**：
如果不需要设置错误视图就不需要调用这个方法，如果需要设置错误视图时调用该方法必须在loadComplete()调用前调用，否则该方法无效。
```java
commentView.setErrorView(你的错误视图);
```

**添加控件头视图**：
如果不需要添加控件头视图就不需要调用这个方法，如果需要添加控件头视图时调用该方法建议在loadComplete()调用前调用。
```java
commentView.addHeaderView(你的错误视图，是否响应点击事件);
```

**第四步：初始化回调（非常重要，必须初始化）：**

**无论是否需要设置回调，都要调用.buildCallback()方法完成初始化。**

**并且初始化回调的工作必须要在loadComplete()方法调用之前（即首次加载数据之前）完成，否则控件将无法正常使用。**

支持的回调:
1、CustomCommentItemCallback：自定义评论布局回调
2、CustomReplyItemCallback：自定义回复布局回调
3、OnPullRefreshCallback：上拉刷新回调
4、OnCommentLoadMoreCallback：下拉加载更多评论回调
5、OnReplyLoadMoreCallback：加载更多回复回调
6、OnItemClickCallback：Item的点击事件回调
7、OnScrollCallback：控件滚动事件回调

当需要设置回调时：

**设置完回调后必须调用.buildCallback()方法，否则回调不会生效，控件也无法正常使用。**

```java
commentView.callbackBuilder()
           .setOnPullRefreshCallback(你的回调实例)
           .onItemClickCallback(你的回调实例)
           ......设置更多回调
           ......设置更多回调
           //设置完成后必须调用CallbackBuilder的buildCallback()方法，否则设置的回调无效，控件也无法正常显示。
           //无论是否设置回调，buildCallback()方法都必须调用。
           .buildCallback();
```

当不需要设置回调时：

**必须调用.buildCallback()方法，否则控件也无法正常使用。**

```java
commentView.callbackBuilder().buildCallback();
```

**第五步：设置数据：**

当所有的初始化工作都完成后，就可以请求后台返回评论数据或加载本地数据为控件设置数据了。完成设置数据后，控件就能正确显示评论数据了。

```java
commentView.loadComplete(你的数据模型实体类);
```
