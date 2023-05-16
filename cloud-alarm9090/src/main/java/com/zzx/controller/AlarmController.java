package com.zzx.controller;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.zzx.domain.AlarmMessage;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * 告警控制层
 */
@RestController
@RequestMapping("alarm")
public class AlarmController {
    @Value("${dingding.webhook}")
    private String webhook;
    @Value("${dingding.secret}")
    private String secret;
    /**
     * 钩子服务 钉钉告警
     */
    @PostMapping("dingding")
    public void message(@RequestBody List<AlarmMessage> alarmMessages){
//        StringBuilder builder = new StringBuilder();
//        alarmMessages.forEach(info -> {
//            builder.append("\nscopeId:").append(info.getScopeId())
//                    .append("\nScope实体:").append(info.getScope())
//                    .append("\n告警消息:").append(info.getAlarmMessage())
//                    .append("\n告警规则:").append(info.getRuleName())
//                    .append("\n\n------------------------\n\n");
//        });
//        System.out.println(builder);
        alarmMessages.forEach(info -> {
            try {
                // 当前时间戳
                Long timestamp = System.currentTimeMillis();
                String stringToSign = timestamp + "\n" + secret;
                /**
                 * Mac算法是带有密钥的消息摘要算法
                 * 初始化HmacMD5摘要算法的密钥产生器
                 */
                Mac mac = Mac.getInstance("HmacSHA256");
                // 初始化mac
                mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
                // 执行消息摘要
                byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
                // 拼接签名
                String sign = "&timestamp=" + timestamp + "&sign=" + URLEncoder.encode(new String(Base64.encodeBase64(signData,false)), "UTF-8");
                // 构建钉钉发送客户端工具
                DingTalkClient client = new DefaultDingTalkClient(webhook + sign);
                // 设置消息类型
                OapiRobotSendRequest request = new OapiRobotSendRequest();
                request.setMsgtype("text");
                // 设置告警信息
                OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
                text.setContent("业务告警:\n" + info.getAlarmMessage());
                request.setText(text);
                // 接受人
                OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
                at.setAtMobiles(Arrays.asList("所有人"));
                request.setAt(at);
                OapiRobotSendResponse response = client.execute(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    @Autowired
    private JavaMailSender javaMailSender;
    /**
     * 钩子服务 邮件告警
     */
    @PostMapping("sendMail")
    public void sendMail(@RequestBody List<AlarmMessage> alarmMessages){
        alarmMessages.forEach(info -> {
            // 邮件客户端 MimeMailMessage可以携带附件，SimpleMailMessage不能携带附件
            MimeMailMessage mailMessage = new MimeMailMessage(javaMailSender.createMimeMessage());
            // 指定发件人的qq邮箱
            mailMessage.setFrom("1325684729@qq.com");
            // 指定收件人的邮箱
            mailMessage.setTo("1325684729@qq.com");
            // 邮件主题
            mailMessage.setSubject(info.getName());
            // 邮件内容
            mailMessage.setText(info.getAlarmMessage());
            // 发送邮件
            javaMailSender.send(mailMessage.getMimeMessage());

        });
    }

}
