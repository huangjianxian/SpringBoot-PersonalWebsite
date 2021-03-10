"use strict";
$(function() {
    var avatarApi;
    // 菜单事件
    $(".hjx-list-group .list-group-item").click(function() {
        var url = $(this).attr("url");
        avatarApi = url;
        // 先移除其他的点击样式，再添加当前的点击样式
        $(".hjx-list-group .list-group-item").removeClass("active");
        $(this).addClass("active");
        // 加载其他模块的页面到右侧工作区
        $.ajax({
            url: url,
            success: function(data){
                $("#rightContainer").html(data);
            },
            error : function() {
                alert("error");
            }
        });
    });

    $("#editAvatar").click(function () {
        var url = $(this).attr("url");
        // 加载其他模块的页面到右侧工作区
        $.ajax({
            url: url,
            success: function(data){
                $("#editAvatarContainer").html(data);

            },
            error : function() {
                alert("error");
            }
        });
    });

    /**
     * 将以base64的图片url数据转换为Blob
     * @param urlData
     * 用url方式表示的base64图片数据
     */
    function convertBase64UrlToBlob(urlData){

        var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte

        //处理异常,将ascii码小于0的转换为大于0
        var ab = new ArrayBuffer(bytes.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < bytes.length; i++) {
            ia[i] = bytes.charCodeAt(i);
        }
        return new Blob( [ab] , {type : 'image/png'});
    }

    $("#editAvatarBtn").click(function () {
        var form = $('#avatarformid')[0];
        var formData = new FormData(form);   //这里连带form里的其他参数也一起提交了,如果不需要提交其他参数可以直接FormData无参数的构造函数
        var base64Codes = $(".cropImg > img").attr("src");
        formData.append("file",convertBase64UrlToBlob(base64Codes));  //append函数的第一个参数是后台获取数据的参数名,和html标签的input的name属性功能相同
        console.log("formdata:"+formData)
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/uploadAvatar",
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false,
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
                console.log("上传error")
                alert("error");
            }
        });
    });



    // 选中菜单第一项
    $(".hjx-list-group .list-group-item:first").trigger("click");




});