
"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
$(function() {

    var _pageSize; // 存储用于搜索

    // 开启下拉框
    $('.selectpicker').selectpicker();

    // 根据用户名、页面索引、页面大小获取用户列表
    function getBlogsByTitleAndCategory(pageIndex, pageSize) {
        $.ajax({
            url: "/admin/blogs",
            contentType : 'application/json',
            data:{
                "async":true,
                "pageIndex":pageIndex,
                "pageSize":pageSize,
                "title":$("#searchTitle").val(),
                "categoryname":$('#ctgSelected').selectpicker('val')
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
        getBlogsByTitleAndCategory(pageIndex, pageSize);
        _pageSize = pageSize;
    });

    // 搜索
    $("#searchNameBtn").click(function() {
        console.log("点击了查询按钮");
        getBlogsByTitleAndCategory(0, _pageSize);
    });

    // 上线博客
    $(".hjx-online-blog").click(function () {
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/admin/blogs/online/" + $(this).attr("blogId") ,
            type: 'POST',
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
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
                toastr.error("error!");
            }
        });
    });

    // 下线博客
    $(".hjx-offline-blog").click(function () {
        console.log("点击了查询按钮");
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/admin/blogs/offline/" + $(this).attr("blogId") ,
            type: 'POST',
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
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
                toastr.error("error!");
            }
        });
    });

    // 删除博客
    $(".hjx-delete-blog").click(function () {
        console.log("点击了查询按钮");
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/admin/blogs/delete/" + $(this).attr("blogId") ,
            type: 'DELETE',
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
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
                toastr.error("error!");
            }
        });
    });

    //获取被选中的option标签选项
    $('#ctgSelected').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
        getBlogsByTitleAndCategory(0, _pageSize);
    });


});