package com.newer.supervision.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.util.SecurityCode;
import com.newer.supervision.util.SecurityImage;





//@CrossOrigin(allowedHeaders="*",allowCredentials="true", origins="*",maxAge=3600) 
@RestController
@RequestMapping("/gc")
public class GlobController {
	@GetMapping("/imagecode")
	public void imgcode(HttpSession session, HttpServletResponse response) {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 生成图形验证码
		String securityCode = SecurityCode.getSecurityCode();
		session.setAttribute("imgcode", securityCode);
		BufferedImage bufImg = SecurityImage.getImage(securityCode);

		try {
			ImageIO.write(bufImg, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
