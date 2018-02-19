package com.uu.show.checkcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckCodeServlet
 */
@WebServlet(name = "validate", urlPatterns = { "/checkCode" })
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Random dom = new Random();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckCodeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		System.out.println("产生验证?");
		int codeLength = 6; // 验证码长�?
		String validateCode = codeGenerate(codeLength);
		System.out.println("validateCode " + validateCode);
		HttpSession session = request.getSession();
		session.setAttribute("code", validateCode);

		Random random = new Random();

		int width = 25 + 10;
		int height = 30;

		BufferedImage image = new BufferedImage(width * codeLength, height, BufferedImage.TYPE_INT_RGB);

		// 获取图形上下�?
		Graphics graphics = image.getGraphics();

		// 设置背景�?
		// graphics.setColor(getRandColor(1,50,random));
		graphics.setColor(Color.WHITE);
		// 填充背景�?
		graphics.fillRect(0, 0, width * codeLength, height);

		// 设置边框颜色
		// graphics.setColor(new Color(255,255,255));
		// 画边�?
		// for (int i=0; i<2; i++)
		// graphics.drawRect(i, i, width * codeLength - i * 2 - 1, height - i *
		// 2 - 1);

		// 设置随机干扰线条颜色
		graphics.setColor(getRandColor(180, 200, random));
		// 产生50条干扰线�?
		for (int i = 0; i < 50; i++) {
			int x1 = random.nextInt(width * codeLength - 4) + 2;
			int y1 = random.nextInt(height - 4) + 2;
			int x2 = random.nextInt(width * codeLength - 2 - x1) + x1;
			int y2 = y1;
			graphics.drawLine(x1, y1, x2, y2);
		}

		// 设置字体
		graphics.setFont(new Font("Arial", Font.ITALIC, 35));
		// 画字符串
		for (int i = 0; i < codeLength; i++) {
			String temp = validateCode.substring(i, i + 1);
			graphics.setColor(getRandColor(50, 150, random));
			graphics.drawString(temp, 30 * i + 10, 26);
		}

		// 图像生效
		graphics.dispose();

		// 将ContentType设为"image/jpeg"，让浏览器识别图像格式�??
		response.setContentType("image/jpeg");
		// 设置页面不缓�?
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 2000);
		try {
			// 获得Servlet输出�?
			ServletOutputStream outStream = response.getOutputStream();
			// 创建可用来将图像数据编码为JPEG数据流的编码�?
			// JPEGImageEncoder encoder =
			// JPEGCodec.createJPEGEncoder(outStream);
			// 将图像数据进行编�?
			// encoder.encode(image);
			// 强行将缓冲区的内容输入到页面
			ImageIO.write(image, "JPEG", outStream);
			outStream.flush();
			// 关闭输出�?
			outStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String codeGenerate(int length) {
		// 随机字符串范�?
		String randRange = "abcdefghkmnpqrstuvwxyz" + "23456789" + "ABCDEFGHKMNPQRSTUVWXYZ";
		char[] chars = randRange.toCharArray();
		Random random = new Random();
		// 生成随机字符�?
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++)
			sb.append(chars[random.nextInt(chars.length)] + "");
		String validateCode = sb.toString();
		return validateCode;
	}

	public Color getRandColor(int ll, int ul, Random random) {
		if (ll > 255)
			ll = 255;
		if (ll < 1)
			ll = 1;
		if (ul > 255)
			ul = 255;
		if (ul < 1)
			ul = 1;
		if (ul == ll)
			ul = ll + 1;
		int r = random.nextInt(ul - ll) + ll;
		int g = random.nextInt(ul - ll) + ll;
		int b = random.nextInt(ul - ll) + ll;
		Color color = new Color(r, g, b);
		return color;
	}

	public static void main(String[] args) {
		CheckCodeServlet s = new CheckCodeServlet();
		System.out.println(s.codeGenerate(8));
	}
}
