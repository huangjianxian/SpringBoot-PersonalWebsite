"use strict";

$(function () {

    $(".delete-vote").click(function () {
        console.log("点击删除点赞")
        console.log("点击删除点赞+id="+$(this).attr("voteId"))
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: '/votes/'+$(this).attr('voteId')+'?blogId='+$(this).attr('blogId'),
            type: 'DELETE',
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
                if (data.success) {
                    toastr.success(data.message);
                    // 停顿1秒后刷新
                    window.setTimeout(function () {
                        window.location.reload();
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
})