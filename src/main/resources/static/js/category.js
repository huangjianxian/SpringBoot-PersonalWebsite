"use strict";
$(function() {

    var _pageSize; // 存储用于搜索
    var ctgName;
    // 根据用户名、页面索引、页面大小获取用户列表
    function getBlogsByCategory(pageIndex, pageSize) {
        console.log("来到了getBlogsByCategory函数，pageIndex"+pageIndex+"pageSize"+pageSize)
        $.ajax({
            url: "/categories",
            contentType : 'application/json',
            data:{
                "async":true,
                "pageIndex":pageIndex,
                "pageSize":pageSize,
                "ctgName": ctgName,
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
        getBlogsByCategory(pageIndex, pageSize);
        _pageSize = pageSize;
    });


    // // 点击查询
    // $("#ctgDiv").on("click",".hjx-list-blog", function (pageIndex, pageSize) {
    //     console.log("点击了分类"+$(this).attr("ctgName"))
    //     ctgName = $(this).attr("ctgName")
    //     getBlogsByCategory(0, _pageSize)
    // });
    // 关键字搜索
    $(".hjx-list-blog").click(function() {
        console.log("点击了分类"+$(this).attr("ctgName"))

        $(".hjx-list-blog").removeClass("btn-primary");
        $(this).addClass("btn-primary");

        ctgName = $(this).attr("ctgName")
        getBlogsByCategory(0, _pageSize)
    });


    $(".hjx-list-blog:first").trigger("click");
})

