"use strict";
$(function () {

    // 初始化弹出框
    $('[data-toggle="popover"]').popover();


    // 返回顶部
    $(".hjx-tools").hide();
    //检测屏幕高度
    var height=$(window).height();
    //scroll() 方法为滚动事件
    $(window).scroll(function(){
        if ($(window).scrollTop()>height){
            $(".hjx-tools").fadeIn(500);
        }else{
            $(".hjx-tools").fadeOut(500);
        }
    });

    $(".hjx-tools .top").click(function(){
        $('body,html').animate({scrollTop:0},200);
        return false;
    });

    // 返回顶部end
})

