package com.jontyhua.web.controller;

import com.jontyhua.web.domain.User;
import com.jontyhua.web.service.UserService;
import com.jontyhua.web.util.AliyunOSSUtil;
import com.jontyhua.web.util.MD5Util;
import com.jontyhua.web.vo.Response;
import net.sf.json.JSONObject;
import org.bson.types.Binary;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Controller
public class AliyunOSSController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @Autowired
    private UserService userService;

    /**
     * 文件上传（供前端调用）
     * 参数名不能改
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public JSONObject uploadPicture(@RequestParam(value = "editormd-image-file",required = false) MultipartFile file) {
        logger.info("文件上传");
        System.out.println("来到了文件上传");
        // 获取文件名称
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        JSONObject res = new JSONObject();
        try {
            if (file != null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    String uploadUrl = aliyunOSSUtil.upLoad(newFile);
                    res.put("success", 1);
                    res.put("message", "上传成功！");
                    res.put("url",uploadUrl);

                }
            }
        } catch (Exception ex) {
            res.put("success", 0);
            res.put("message", "上传失败！");
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * 文件上传（供前端调用）
     * 参数名不能改
     */
    @RequestMapping("/uploadAvatar")
    public ResponseEntity<Response> uploadUserAvatar(@RequestParam(value = "file",required = false) MultipartFile file) {
        String filename = file.getOriginalFilename();
        String returnUrl = null;
        try {
            if (file != null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    String uploadUrl = aliyunOSSUtil.upLoad(newFile);
                    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    user.setAvatarurl(uploadUrl);
                    returnUrl = "/u/"+user.getUsername();
                    userService.saveUser(user);
                }
            }
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "成功修改头像",returnUrl));
    }


}
