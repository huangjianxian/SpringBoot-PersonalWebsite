"use strict";

$(function() {
    // 提交按钮 保存公告
    $("#submitBtn").click(function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: '/admin/notices',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({
                "content":$('#noticeContent').val()
            }),
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
    })

    // 删除公告
    $(".hjx-delete-notice").click(function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: '/admin/notices/delete/'+$(this).attr('noticeId'),
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
    })

    // 下线公告
    $(".hjx-offline-notice").click(function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: '/admin/notices/offline/'+$(this).attr('noticeId'),
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
    })

    // 上线公告
    $(".hjx-online-notice").click(function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: '/admin/notices/online/'+$(this).attr('noticeId'),
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
    })

});