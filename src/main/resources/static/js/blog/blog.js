
"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
$(function() {

    var _pageSize; // 存储用于搜索

    // 根据用户名、页面索引、页面大小获取用户列表
    function getBlogsByTitle(pageIndex, pageSize) {
        $.ajax({
            url: "/blogs",
            contentType : 'application/json',
            data:{
                "async":true,
                "pageIndex":pageIndex,
                "pageSize":pageSize,
                "title":$("#searchName").val()
            },
            success: function(data){
                $("#mainContainer").html(data);
                for (var i = 0; i < data.length; i++) {
                    $('.selectpicker').append("<option value=" + data.catalogList[i].name + ">" + data.catalogList[i].name + "</option>");
                }
                $('#catalogSP').selectpicker('refresh');
                $('#catalogSP').selectpicker('render');
            },
            error : function() {
                toastr.error("error!");
            }
        });
    }

    // 分页
    $.tbpage("#mainContainer", function (pageIndex, pageSize) {
        getBlogsByTitle(pageIndex, pageSize);
        _pageSize = pageSize;
    });

    // 搜索
    $("#searchNameBtn").click(function() {
        console.log("点击了查询按钮");
        getBlogsByTitle(0, _pageSize);
    });

    // 获取新增博客的界面
    $("#addBlogBtn").click(function() {
        $.ajax({
            url: "/blogs/addBlog",
            success: function(data){
                console.log(data)
                $("#hjx-full-container").html(data);
            },
            error : function(data) {
                toastr.error("error!");
            }
        });
    });

    // 获取编辑用户的界面
    $("#hjxContainer").on("click",".hjx-edit-blog", function () {
        $.ajax({
            url: "/blogs/edit/" + $(this).attr("blogId"),
            success: function(data){
                $("#hjx-full-container").html(data);
            },
            error : function() {
                toastr.error("error!");
            }
        });
    });

    // 提交变更后，清空表单
    $("#submitEdit").click(function() {
        console.log("点击了提交保存按钮")
        $.ajax({
            url: "/blogs/update",
            type: 'POST',
            data:$('#blogForm').serialize(),
            success: function(data){
                if (data.success) {
                    console.log("提交成功")
                    $('#updateModel').modal('hide');
                    getBlogsByTitle(0, _pageSize);
                } else {
                    $('#blogForm')[0].reset();
                    toastr.error(data.message);
                }
            },
            error : function() {
                toastr.error("error!");
                $('#blogForm')[0].reset();
            }
        });
    });

    // 删除用户
    $("#hjxContainer").on("click",".hjx-delete-blog", function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/blogs/" + $(this).attr("blogId") ,
            type: 'DELETE',
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
                if (data.success) {
                    // 从新刷新主界面
                    getBlogsByTitle(0, _pageSize);
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