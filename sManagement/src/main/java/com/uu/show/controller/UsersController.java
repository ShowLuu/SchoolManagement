package com.uu.show.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uu.show.entity.Users;
import com.uu.show.entity.Yuanxi;
import com.uu.show.service.UsersService;
import com.uu.show.service.YuanxiService;

@WebServlet(name = "user", urlPatterns = "/user")
public class UsersController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UsersService userService = new UsersService();
	public YuanxiService yuanxiService=new YuanxiService();

	public UsersController() {
		System.out.println("user-start");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operator = req.getParameter("operator");
		System.out.println(operator + "--------");
		if (operator.equals("login")) {
			doPost(req, resp);
		} else if (operator.equals("regist")) {
			preRegist(req, resp);
		} else if (operator.equals("save_user")) {
			regist(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String code = req.getParameter("code");
		String checkCode = req.getSession().getAttribute("code").toString();

		String host = req.getRemoteHost();
		int port = req.getRemotePort();

		String errorMessage = "";
		boolean flag = false;

		if (null != code && code.equalsIgnoreCase(checkCode)) {
			Users user = userService.findByUserName(username);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					flag = true;
					System.out.println("用户" + user.getUsername() + ",通过" + host + ":" + port + "登录了本系统");
				} else {
					errorMessage = "密码输入错误";
				}
			} else {
				errorMessage = "用户不存在";
			}
		} else {
			errorMessage = "验证码输入错误";
		}
		
		if(flag){//登录成功,跳转到列表页面
			List<Yuanxi> yuanxi=yuanxiService.findAll();
			req.setAttribute("yuanxi",yuanxi );
			RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/views/yuanxi/init.jsp");
			dis.forward(req, resp);//
		}else{//跳转到注册页面，加入错误提示信息
			req.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/views/users/login.jsp");
			dis.forward(req, resp);
		}
	}

	private void preRegist(HttpServletRequest req, HttpServletResponse resp) {
		try {
			System.out.println("preRegist");
			req.getRequestDispatcher("/WEB-INF/views/users/regist.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void regist(HttpServletRequest req, HttpServletResponse resp) {
		try {
			System.out.println("yongRe");
			String username = req.getParameter("username");
			String newPassword = req.getParameter("newPassword");
			System.out.println(username);
			System.out.println(newPassword);
			Users user = new Users();
			user.setUsername(username);
			user.setPassword(newPassword);
			userService.insert(user);
			req.getRequestDispatcher("/WEB-INF/views/users/login.jsp").forward(req, resp);
		} catch (Exception e) {
			try {
				req.getRequestDispatcher("/WEB-INF/views/users/regist.jsp").forward(req, resp);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
