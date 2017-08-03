package com.lavor.springboot.others;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.URLDecoder;

/**
 * 测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootOthersApplicationTests {
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 测试发送简单邮件
	 * @throws Exception
     */
	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("oraclejack@163.com");
		message.setTo("1746898017@qq.com");
		message.setSubject("SpringBoot发送简单邮件");
		message.setText("测试邮件");
		mailSender.send(message);
	}

	/**
	 * 测试发送帶附件的邮件
	 * @throws Exception
     */
	@Test
	public void sendAttachmentsMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("oraclejack@163.com");
		helper.setTo("1746898017@qq.com");
		helper.setSubject("SpringBoot发送有附件的邮件");
		helper.setText("有附件的邮件");
		//获取classes目录路径，可能需要进行解码操作
		String dir=SpringbootOthersApplicationTests.class.getClassLoader().getResource("").getPath();
		String filePath=dir+"lavor.png";
		//对文件路径进行解码操作
		filePath= URLDecoder.decode(filePath, "UTF-8");
		File file = new File(filePath);
		FileSystemResource fileSystemResource = new FileSystemResource(file);
		helper.addAttachment("附件-lavor.jpg", fileSystemResource);
		mailSender.send(mimeMessage);
	}

	/**
	 * 测试发送嵌入静态资源的邮件
	 * @throws Exception
     */
	@Test
	public void sendInlineMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("oraclejack@163.com");
		helper.setTo("1746898017@qq.com");
		helper.setSubject("SpringBoot发送嵌入静态资源的邮件");
		helper.setText("<html><body><img src='cid:lavor' ></body></html>", true);
		//获取classes目录路径，可能需要进行解码操作
		String dir=SpringbootOthersApplicationTests.class.getClassLoader().getResource("").getPath();
		String filePath=dir+"lavor.png";
		//对文件路径进行解码操作
		filePath= URLDecoder.decode(filePath, "UTF-8");
		File file = new File(filePath);
		FileSystemResource fileSystemResource = new FileSystemResource(file);
		//注意这里的第一个参数（lavor）必须与静态资源(cid:lavor)相对应
		helper.addInline("lavor", file);
		mailSender.send(mimeMessage);
	}

}
