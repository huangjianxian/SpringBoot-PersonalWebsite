"use strict";

$(function() {
    // 提交按钮 保存标签
    $("#submitBtn").click(function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: '/admin/friendlinks',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({
                "name":$('#friendLinkName').val(),
                "url": $("#url").val()
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

    // 删除
    $(".hjx-delete-friendlink").click(function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: '/admin/friendlinks/delete/'+$(this).attr('friendlinkId'),
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


});