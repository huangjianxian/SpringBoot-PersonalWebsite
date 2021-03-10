"use strict";

var hjxEditor;
var commentState;
var copyrightState;
var csrfToken = $("meta[name='_csrf_token']").attr("content");
$(function () {
    hjxEditor = editormd("hjx-editormd", {
        width: "100%",
        height: 650,
        syncScrolling: "single",
        path: "../../../editormd/lib/",
        emoji: true,
        saveHTMLToTextarea: true,
        imageUpload : true,
        tocm : true, // Using [TOCM]
        tex : true, // 开启科学公式TeX语言支持，默认关闭
        flowChart : true, // 开启流程图支持，默认关闭
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp","JPG","JPEG","GIF","PNG","BMP","WEBP"],
        imageUploadURL : "/uploadFile?csrf_token="+csrfToken,
        onload: function () {
            //console.log('onload', this);
            //this.fullscreen();
            //this.unwatch();
            //this.watch().fullscreen();
            // this.width("100%");
            // this.height(480);
            //this.resize("100%", 640);
        }
    });

    $('.selectpicker').selectpicker();

    // 多选框回显数据
    var str=$("#tagsStr").val();
    var arr=str.split(',');
    $('#tagSelected').selectpicker('val', arr);


    // 版权开关
    copyrightState = $("#copyrightStateVal").val(),
    $("#copyrightState").bootstrapSwitch({
        onText : "开启",
        offText : "关闭",
        onColor : "success",
        offColor : "danger",
        size : "small",
        state : copyrightState,
    });


    // 评论框
    commentState = $("#commentStateVal").val(),
    $("#commentState").bootstrapSwitch({
        onText : "开启",
        offText : "关闭",
        onColor : "success",
        offColor : "danger",
        size : "small",
        state : commentState,
    });

    // 提交变更后，清空表单
    $("#submitEdit").click(function() {
        console.log("点击了提交保存按钮")
        console.log("id"+$('#commentState').val())
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/admin/blogs/update",
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({
                "id":$('#blogId').val(),
                "title": $('#title').val(),
                "summary": $('#summary').val(),
                "content": $('#content').val(),
                "category":{"id":$('#ctgSelected').selectpicker('val')},
                "flag":{"id":$('#flagSelected').selectpicker('val')},
                "tagIds":$('#tagSelected').selectpicker('val').toString(),
                "copyrightState": $('#copyrightState').bootstrapSwitch('state'),
                "commentState": $('#commentState').bootstrapSwitch('state'),
            }),
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
                console.log("来到更新success")
                if (data.success) {
                    console.log("来到data.success")
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

})