"use strict";
$(function () {

    var blogId = $("#blogId").val();
    var commentId = $(this).attr("data-commentid");
    var replyId = $(this).attr("data-replyid");
    // var blogUrl = '/blogs/'+ [[${blog.id}]] ;
    var blogEditor;


    // 获取评论列表
    function getComments(blogId) {
        $.ajax({
            url: '/comments/'+blogId,
            type: 'GET',
            success: function(data){
                console.log("来到获取评论列表success")
                $("#mainContainer").html(data);
            },
            error : function() {
                console.log("来到获取评论error")
                toastr.error("error!");
            }
        });
    }

    // 提交评论
    $("#submitComment").click(function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        console.log("parentCommentId: "+$("#parentCommentId").val())
        $.ajax({
            url: '/comments',
            type: 'POST',
            data:{
                "blog.id":$("#blogId").val(),
                "parentComment.id": $("#parentCommentId").val(),
                "replyUser.id": replyId,
                "content":$('#commentContent').val()
            },
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
                console.log("来到评论success")
                if (data.success) {
                    // 清空评论框
                    $('#commentContent').val('');
                    $("#parentCommentId").val(-1);
                    $("#commentContent").attr("placeholder","请输入评论信息...");
                    toastr.success(data.message);
                    window.setTimeout(function () {
                        window.location.reload();
                    },1000);
                } else {
                    console.log("来到error")
                    toastr.error(data.message);
                }
            },
            error : function() {
                toastr.error("error!");
            }
        });
    });

    // 回复
    function reply(obj){
        var commentId = $(obj).data('commentid');
        var commentNickname = $(obj).data('commentnickname');
        $("#commentContent").attr("placeholder","@"+commentNickname).focus();
        $("#parentCommentId").val(commentId);
        // $(window).scrollTo('#comment-container',500)

    }

    $(".reply").click(function () {
        var commentId = $(this).data('commentid');
        var commentNickname = $(this).data('commentnickname');
        console.log("回复的评论id是"+commentId)
        reply(this);
    })

    // 删除评论
    $(".blog-content-container").on("click",".blog-delete-comment", function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            url: '/comments/'+$(this).attr("commentId")+'?blogId='+blogId,
            type: 'DELETE',
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
                if (data.success) {
                    // 获取评论列表
                    getComments(blogId);
                } else {
                    toastr.error(data.message);
                }
            },
            error : function() {
                toastr.error("error!");
            }
        });
    });

    // 初始化 博客评论
    // getComments(blogId);


    // 提交点赞
    $("#submitVote").click(function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            url: '/votes',
            type: 'POST',
            data:{"blogId":blogId},
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
                if (data.success) {
                    toastr.info(data.message);
                    // 成功后，重定向
                    window.location = blogUrl;
                } else {
                    toastr.error(data.message);
                }
            },
            error : function() {
                toastr.error("error!");
            }
        });
    })


    $("#cancelVote").click(function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            url: '/votes/'+$(this).attr('voteId')+'?blogId='+blogId,
            type: 'DELETE',
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
                if (data.success) {
                    toastr.info(data.message);
                    // 成功后，重定向
                    window.location = blogUrl;
                } else {
                    toastr.error(data.message);
                }
            },
            error : function() {
                toastr.error("error!");
            }
        });
    });

})

