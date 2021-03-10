/**
 * @Date: 2019/04/09
 * @Author: JONTY HUA
 * @Description: 用于注册表单认证，使用 jquery validate 插件
 */

"use strict";

$().ready(function() {
    // 表单认证
    $("#registerForm").validate({
        rules:{
            email:{
                required:true,
                email:true,
                rangelength:[1,100],
                remote:{
                    url: "/verify/email", //后台处理程序
                    type: "GET",  //数据发送方式
                    contentType: "application/json;charset=UTF-8",
                    data:  {                     //要传递的数据
                        email: function() {
                            return $("#email").val();
                        }
                    }
                }
            },
            username:{
                required:true,
                rangelength:[1,20],
                availableusername:true,
                remote:{
                    url: "/verify/username", //后台处理程序
                    type: "GET",  //数据发送方式
                    contentType: "application/json;charset=UTF-8",
                    data:  {                     //要传递的数据
                        username: function() {
                            return $("#username").val();
                        }
                    }
                }
            },
            nickname:{
                required:true,
                rangelength:[1,20],
                remote:{
                    url: "/verify/nickname", //后台处理程序
                    type: "GET",  //数据发送方式
                    contentType: "application/json;charset=UTF-8",
                    data:  {                     //要传递的数据
                        nickname: function() {
                            return $("#nickname").val();
                        }
                    }
                }
            },
            password:{
                required:true,
                rangelength:[1,100]
            },
            confirmpassword:{
                required:true,
                rangelength:[1,100],
                equalTo:'#password' //表示和id="password"的值相同
            }
        },
        messages: {
            email: {
                required: "请输入您的邮箱",
                email: "请输入正确的邮箱",
                rangelength: "请输入1-100以内的字符串",
                remote: "该邮箱已注册，请前往登录"
            },
            username: {
                required: "请设置您的用户名",
                availableusername: "仅支持英文字母和数字",
                rangelength: "请输入1-20以内的字符串",
                remote: "该用户名已被使用"
            },
            nickname: {
                required: "请设置您的昵称",
                rangelength: "请输入1-20以内的字符串",
                remote: "该昵称已被使用，请更换一个"
            },
            password: {
                required: "请设置您的密码",
                rangelength: "请输入1-100以内的字符串"
            },
            confirmpassword: {
                required: "请重新输入密码",
                equalTo: "两次密码输入不一致",
                rangelength: "请输入1-100以内的字符串"
            }
        }

    })
});

// 出生年份认证
$('.yearpicker').datepicker({
    startView: 'decade',
    minView: 'decade',
    format: 'yyyy',
    maxViewMode: 2,
    minViewMode:2,
    autoclose: true,
    endDate : new Date()
});
