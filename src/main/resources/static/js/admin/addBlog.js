"use strict";

var hjxEditor;
var csrfToken = $("meta[name='_csrf_token']").attr("content");
$(function () {
    hjxEditor = editormd("hjx-editormd", {
        width: "100%",
        height: 650,
        syncScrolling: "single",
        path: "../../editormd/lib/",
        emoji: true,
        saveHTMLToTextarea: true,
        tocm : true, // Using [TOCM]
        tex : true, // 开启科学公式TeX语言支持，默认关闭
        flowChart : true, // 开启流程图支持，默认关闭
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp","JPG","JPEG","GIF","PNG","BMP","WEBP"],
        imageUploadURL : "/uploadFile?csrf_token="+csrfToken,
    });

    $('.selectpicker').selectpicker();

    // 开启选择框
    $("[name='copyrightState']").bootstrapSwitch({
        onSwitchChange: function(event, state) {
            if (state == true) {
                $(this).val("1");
            } else {
                $(this).val("2");
            }
        }
    });
    $("[name='commentState']").bootstrapSwitch();

    // 提交变更后，清空表单
    $("#submitEdit").click(function() {
        console.log("点击了提交保存按钮")
        console.log("获取到的标签数据："+$('#tagSelected').selectpicker('val'))
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf_token']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/admin/blogs",
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({
                "id":$('#blogId').val(),
                "title": $('#title').val(),
                "summary": $('#summary').val() ,
                "content": $('#content').val(),
                "copyrightState": $('#copyrightState').bootstrapSwitch('state'),
                "commentState": $('#commentState').bootstrapSwitch('state'),
                "flag": {"id":$('#flagSelected').selectpicker('val')},
                "category":{"id":$('#ctgSelected').selectpicker('val')},
                "tagIds":$('#tagSelected').selectpicker('val').toString()
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

})