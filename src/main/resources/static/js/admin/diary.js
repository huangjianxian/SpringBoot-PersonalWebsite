
"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
var hjxEditor;
$(function() {
    hjxEditor = editormd("hjx-editormd", {
        width: "100%",
        height: 650,
        syncScrolling: "single",
        path: "../../../editormd/lib/",
        emoji: true,
        saveHTMLToTextarea: true
    });

    var _pageSize; // 存储用于搜索
    // 根据用户名、页面索引、页面大小获取用户列表
    function getDiaries(pageIndex, pageSize) {
        $.ajax({
            url: "/admin/diaries",
            contentType : 'application/json',
            data:{
                "async":true,
                "pageIndex":pageIndex,
                "pageSize":pageSize
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
        getDiaries(pageIndex, pageSize);
        _pageSize = pageSize;
    });


    // 获取编辑用户的界面
    $("#mainContainer").on("click",".hjx-edit-diary", function () {
        $.ajax({
            url: "/admin/diaries/edit/" + $(this).attr("userId"),
            success: function(data){
                $("#userInfo").html(data);
            },
            error : function() {
                toastr.error("error!");
            }
        });
    });

    // 提交变更后 直接发布
    $("#saveBtn").click(function() {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/admin/diaries",
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                "title": $('#title').val(),
                "content": $('#content').val(),
                "state": "1"
            }),
            beforeSend: function (request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function (data) {
                if (data.success) {
                    toastr.success(data.message);
                    // 停顿1秒后刷新
                    window.setTimeout(function () {
                        window.location = data.body;
                    }, 1000);
                } else {
                    toastr.error("error!" + data.message);
                }

            },
            error: function () {
                toastr.error("error!");
            }
        })
    })

    $("#saveDraft").click(function() {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/admin/diaries",
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({
                "title": $('#title').val(),
                "content": $('#content').val(),
                "state": "0",
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
                    toastr.error("error!"+data.message);
                }

            },
            error : function() {
                toastr.error("error!");
            }
        })
    });

    // 更新日记
    $("#updateDiary").click(function() {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/admin/diaries/update",
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({
                "id": $('#diaryId').val(),
                "title": $('#title').val(),
                "content": $('#content').val(),
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
                    toastr.error("error!"+data.message);
                }

            },
            error : function() {
                toastr.error("error!");
            }
        })
    });

    // 下线日记
    $(".hjx-offline-diary").click(function () {
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/admin/diaries/offline/" + $(this).attr("diaryId") ,
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

    // 上线日记
    $(".hjx-online-diary").click(function () {
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/admin/diaries/online/" + $(this).attr("diaryId") ,
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

});