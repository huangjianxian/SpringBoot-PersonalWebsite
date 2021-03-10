"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
$(function() {
    var _pageSize; // 存储用于搜索
    // 根据用户名、页面索引、页面大小获取用户列表
    function getUersByName(pageIndex, pageSize) {
        $.ajax({
            url: "/admin/users",
            contentType : 'application/json',
            data:{
                "async":true,
                "pageIndex":pageIndex,
                "pageSize":pageSize,
                "username":$("#searchName").val()
            },
            success: function(data){
                $("#mainContainer").html(data);
            },
            error : function() {
                toastr.error("error!");
            }
        });
    }

    // 分页
    $.tbpage("#mainContainer", function (pageIndex, pageSize) {
        getUersByName(pageIndex, pageSize);
        _pageSize = pageSize;
    });

    // 搜索
    $("#searchNameBtn").click(function() {
        getUersByName(0, _pageSize);
    });

    // 修改
    $("#submitBtn").click(function () {
        console.log("来到了修改用户")
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        console.log("来到了ajax")
        $.ajax({
            url: '/admin/users',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            // datatype:"json",
            data:JSON.stringify({
                "id": $("#userId").val(),
                "nickname":$('#nickname').val(),
                "birth":$('#birth').val(),
                "email":$('#email').val(),
                "password":$('#password').val()
            }),
            beforeSend: function(request) {
                console.log("beforeSend")
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
                console.log("修改用户，来到了这里")
                if (data.success) {
                    toastr.success(data.message);
                    // 停顿1秒后刷新
                    window.setTimeout(function () {
                        window.location = data.body;
                    },1000);
                } else {
                    toastr.error(data.message);
                }
            },
            error : function() {
                console.log("error")
                toastr.error("error!");
            }
        });
    })

    // 获取编辑用户的界面
    $("#hjxContainer").on("click",".hjx-edit-user", function () {
        $.ajax({
            url: "/users/edit/" + $(this).attr("userId"),
            success: function(data){
                $("#userInfo").html(data);
            },
            error : function() {
                toastr.error("error!");
            }
        });
    });

    // 删除用户
    $("#mainContainer").on("click",".hjx-delete-user", function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/admin/users/" + $(this).attr("userId") ,
            type: 'DELETE',
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
                if (data.success) {
                    toastr.success(data.message);
                    window.setTimeout(function () {
                        window.location = data.body;
                    },1000);
                } else {
                    toastr.error(data.message);
                }
            },
            error : function() {
                toastr.error("error!");
            }
        });
    });



});
