package com.jontyhua.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
public class SendEmailUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /** 从配置文件中读取 */
    @Value("${spring.mail.username}")
    private String from;

    private SendEmailUtil(){

    }

    /**
     * 发送注册邮件
     * @param username
     * @param email
     * @param code
     * @return
     */
    public boolean sendRegisterMail(String username, String email, String code){
        MimeMessage message = mailSender.createMimeMessage();
        //创建邮件正文
        Context context = new Context();
        context.setVariable("username",username);
        context.setVariable("email",email);
        context.setVariable("code",code);
        String emailContent = templateEngine.process("sentEmail", context); //sentEmail 为发送邮件内容
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject("JontyHua | 华瑾熙 网站注册验证邮件");
            helper.setText(emailContent, true);
            mailSender.send(message);
            logger.info("html邮件发送成功");
            return true;
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
            return false;
        }
    }
}