"use strict";
$(function () {

    var replyId = $(this).attr("data-replyid");
    // 获取评论列表
    function getLeaveMessage() {
        $.ajax({
            url: '/leaveMessages',
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

    // 提交留言
    $("#submitLeaveMessage").click(function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        console.log("parentLeaveMessageId: "+$("#parentLeaveMessageId").val())
        $.ajax({
            url: '/leaveMessages',
            type: 'POST',
            data:{
                "parentLeaveMessage.id": $("#parentLeaveMessageId").val(),
                "replyUser.id": replyId,
                "content":$('#leaveMessageContent').val()
            },
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
                // 清空评论框
                $('#leaveMessageContent').val('');
                $("#parentLeaveMessageId").val(-1);
                $("#leaveMessageContent").attr("placeholder","请输入评论信息...");
                toastr.success(data.message);
                window.setTimeout(function () {
                    window.location.reload();
                },1000);

            },
            error : function() {
                console.log("来到外部error")
                toastr.error("error!");
            }
        });
    });

    // 回复
    function reply(obj){
        var leavemessageid = $(obj).data('leavemessageid');
        var leavemessagenickname = $(obj).data('leavemessagenickname');
        $("#leaveMessageContent").attr("placeholder","@"+leavemessagenickname).focus();
        $("#parentLeaveMessageId").val(leavemessageid);
        // $(window).scrollTo('#comment-container',500)

    }

    $(".reply").click(function () {
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
                    getLeaveMessage(blogId);
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
    // getLeaveMessage(blogId);


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

