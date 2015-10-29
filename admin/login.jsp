<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>Login Page | Amaze UI Example</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <style>
    .header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
  </style>
</head>
<body>
<div class="header">
  <div class="am-g">
    <h1>后台管理系统</h1>
<!--     <p>Integrated Development Environment<br/>代码编辑，代码生成，界面设计，调试，编译</p> -->
  </div>
  <hr />
</div>
<div class="am-g" style="background-color:#CCCCFF">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
<!--     <h3>登录</h3> -->
    <hr>
    <div class="am-btn-group">
      <a href="#" class="am-btn am-btn-secondary am-btn-sm"><i class="am-icon-github am-icon-sm"></i> 帐号登入</a>
      <a href="#" class="am-btn am-btn-success am-btn-sm"><i class="am-icon-google-plus-square am-icon-sm"></i> 邮箱登入</a>
    </div>
    <br>
    <br>

    <form method="post" action="dologin.html" class="am-form">
      <label for="email">邮箱:</label>
      <input type="text" name="username" id="email" value="">
      <br>
      <label for="password">密码:</label>
      <input type="password" name="password" id="password" value="">
      <br>
      <br />
      <div class="am-cf">
        <input type="button" name="" value="忘记密码 ^_^? " class="am-btn am-btn-default am-btn-sm ">
        <input type="submit" name="" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fr">
      </div>
    </form>
    <hr>
    <p>© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
  </div>
</div>
</body>
</html>