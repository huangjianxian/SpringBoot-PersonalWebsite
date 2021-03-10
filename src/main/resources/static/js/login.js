"use strict";

$().ready(function() {
    // 表单认证
    $("#loginForm").validate({
        rules:{
            username:{
                required:true,
                remote:{
                    url: "/verify/usernameOrState", //后台处理程序
                    type: "GET",  //数据发送方式
                    contentType: "application/json;charset=UTF-8",
                    data:  {                     //要传递的数据
                        username: function() {
                            return $("#username").val();
                        }
                    }
                }
            }
        },
        messages: {
            username: {
                required: "请输入您的用户名",
                remote: "该用户还未注册或者未激活"
            }
        }

    });

});
