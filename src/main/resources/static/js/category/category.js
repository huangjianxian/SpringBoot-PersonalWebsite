"use strict";
$(function() {
    // 获取编辑用户的界面
    $("#categoryContainer").on("click",".hjx-list-blog", function () {
        $.ajax({
            url: "/catalogs/" + $(this).attr("ctgId"),
            success: function(data){
                $("#hjx-ctg-blogs").html(data);
            },
            error : function() {
                toastr.error("error!");
            }
        });
    });
})

