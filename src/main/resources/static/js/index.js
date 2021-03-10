"use strict";
$(function () {
    // 启动提示框
    $("[data-toggle='tooltip']").tooltip();
    hjxNotice();
    // 公告栏
    function hjxNotice() {
        var speed = 150
        var _hjxNotice = document.getElementById("hjxNotice");
        var _hjxRollData = document.getElementById("hjxRollData");
        var _hjxRoll = document.getElementById("hjxRoll");
        hjxRoll.innerHTML=hjxRollData.innerHTML;
        function Marquee(){
            if(_hjxRoll.offsetTop-_hjxNotice.scrollTop<=0){
                _hjxNotice.scrollTop-=_hjxRollData.offsetHeight
            }else{
                _hjxNotice.scrollTop++
            }
        }
        var MyMar=setInterval(Marquee,speed)
        _hjxNotice.onmouseover=function() {clearInterval(MyMar)}
        _hjxNotice.onmouseout=function() {MyMar=setInterval(Marquee,speed)}
    }

    // 返回顶部
    $(".hjx-goToTop").hide();
    //检测屏幕高度
    var height=$(window).height();
    //scroll() 方法为滚动事件
    $(window).scroll(function(){
        if ($(window).scrollTop()>height){
            $(".hjx-goToTop").fadeIn(500);
        }else{
            $(".hjx-goToTop").fadeOut(500);
        }
    });

    $(".hjx-goToTop .top").click(function(){
        $('body,html').animate({scrollTop:0},100);
        return false;
    });

// 返回顶部end

    var _pageSize; // 存储用于搜索
    var ctgName = "";    //分类名称
    var tagName = "";    //标签名称
    var keyWord = "";
    // 根据用户名、页面索引、页面大小获取用户列表
    function getBlogsByName(pageIndex, pageSize) {
        $.ajax({
            url: "/",
            contentType : 'application/json',
            data:{
                "async":true,
                "pageIndex":pageIndex,
                "pageSize":pageSize,
                "keyword": keyWord,
                "ctgName":ctgName,
                "tagName": tagName,
            },
            success: function(data){
                $("#mainContainer").html(data);
                // 清空搜索框内容
                $("#PcKeyword").val('');
                $("#phoneKeyword").val('');
            },
            error : function() {
                toastr.error("error!");
            }
        });
    }

    // 分页
    $.tbpage("#mainContainer", function (pageIndex, pageSize) {
        getBlogsByName(pageIndex, pageSize);
        _pageSize = pageSize;
    });

    // 关键字搜索
    $(".hjx-search-btn").click(function() {
        ctgName = "";
        tagName = "";
        keyWord = $("#PcKeyword").val()+$("#phoneKeyword").val();
        getBlogsByName(0, _pageSize);
    });

    // 关键字搜索
    $(".hjx-index-tag").click(function() {
        tagName = $(this).attr("tagName");
        ctgName = "";
        keyWord = "";
        getBlogsByName(0, _pageSize);
    });

    // 关键字搜索
    $(".hjx-index-ctg").click(function() {
        ctgName = $(this).attr("ctgName");
        tagName = "";
        keyWord = "";
        getBlogsByName(0, _pageSize);
    });
});