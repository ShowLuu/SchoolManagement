package com.uu.show.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uu.show.entity.Stu;
import com.uu.show.service.StuService;

@WebServlet(name ="stu", urlPatterns = "/stu")
public class StuController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private StuService stuService = new StuService();

	private String insert="";
	private String delete="";
	private String update="";
	private String query="";
	private String byID="";
	private String operator="";
	
	private String stuID="";
	private String stuName="";
	private String stuSex="";
	private String stuTime="";
	private String stuBirth="";
	private String stuPhone="";
	private String stuAddr="";
	private String stuCls="";
	
	private String back="";
	private String likeQuery="";
	private String button="";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		stuID = req.getParameter("stuID");
		stuName = req.getParameter("stuName");
		stuSex = req.getParameter("stuSex");
		stuTime = req.getParameter("stuTime");
		stuBirth = req.getParameter("stuBirth");
		stuPhone = req.getParameter("stuPhone");
		stuAddr = req.getParameter("stuAddr");
		stuCls = req.getParameter("stuCls");
		
		operator=req.getParameter("operator");
		byID=req.getParameter("byID");
		
		insert=req.getParameter("insert");
		delete=req.getParameter("delete");
		update=req.getParameter("update");
		query=req.getParameter("query");
		back=req.getParameter("back");
		likeQuery=req.getParameter("likeQuery");
		
		 if(insert!=null){
		     button=insert;
		 }else if(delete!=null){
			 button=delete;
		 }else if(update!=null){
			 button=update;
		 }else if(query!=null){
			 button=query;
		 }else if(back!=null){
			 button=back;
		 }else if(likeQuery!=null){
			 button=likeQuery;
		 }else if(operator!=null){
			 button=operator;
		 }else if(byID!=null){
			 button=byID;
		 }
		 
		switch (button) {
		case "新增":
			ClsInsert(req, resp);
			break;
		case "删除":
			ClsDelete(req, resp);
			break;
		case "修改":
			ClsUpdate(req, resp);
			break;
		case "模糊查询":
			ClsLikeQuery(req, resp);
			break;
		case "返回":
			initLogin(req, resp);
			break;
		case "查找":
			ClsLikeQueryID(req, resp);
			break;
		case "init":
			init(req, resp);
			break;

		default:
			break;
		}

	}

	private void initLogin(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Stu> cls = stuService.findAll();
			req.setAttribute("stu", cls);
			req.getRequestDispatcher("/WEB-INF/views/stu/init.jsp").forward(req, resp);
			return;
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void init(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Stu> cls = stuService.findAll();
			req.setAttribute("stu", cls);
			req.getRequestDispatcher("/WEB-INF/views/stu/init.jsp").forward(req, resp);
			return;
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void ClsInsert(HttpServletRequest req, HttpServletResponse resp) {
		String stuIDs = stuID.replace(" ", "");
		String stuNames = stuName.replace(" ", "");
		String stuSexs = stuSex.replace(" ", "");
		String stuTimes = stuTime.replace(" ", "");
		String stuBirths = stuBirth.replace(" ", "");
		String stuPhones = stuPhone.replace(" ", "");
		String stuAddrs = stuAddr.replace(" ", "");
		String stuClss = stuCls.replace(" ", "");
		
		String erroMsgStu = "";
		boolean flag = false;

		Stu cls = stuService.findByStuID(stuID);

		if (!stuIDs.equals("")) {
			if (cls==null) {
				flag=true;
			} else {
				erroMsgStu = "学生已存在";
			}
		} else {
			erroMsgStu = "请填入学生学号新增";
		}
		if (flag) {
			Stu clss=new Stu();
			clss.setStuID(stuIDs);
			clss.setStuName(stuNames);
			clss.setStuSex(stuSexs);
			clss.setStuTime(stuTimes);
			clss.setStuBirth(stuBirths);
			clss.setStuPhone(stuPhones);
			clss.setStuAddr(stuAddrs);
			clss.setStuCls(stuClss);
			System.out.println(stuIDs + "--1");
			stuService.insert(clss);
			initLogin(req, resp);
		} else {
			req.setAttribute("erroMsgStu", erroMsgStu);
			initLogin(req, resp);
		}
	}

	private void ClsDelete(HttpServletRequest req, HttpServletResponse resp) {
		String stuIDs = stuID.replace(" ", "");
		Stu cls = stuService.findByStuID(stuIDs);
		String erroMsgStu = "";
		if (cls!=null) {
		stuService.deletes(stuIDs);
		initLogin(req, resp);
		}else {
			erroMsgStu = "学生不存在";
			req.setAttribute("erroMsgStu", erroMsgStu);
			initLogin(req, resp);
		}
	}

	private void ClsUpdate(HttpServletRequest req, HttpServletResponse resp) {
		String stuIDs = stuID.replace(" ", "");
		String stuNames = stuName.replace(" ", "");
		String stuSexs = stuSex.replace(" ", "");
		String stuTimes = stuTime.replace(" ", "");
		String stuBirths = stuBirth.replace(" ", "");
		String stuPhones = stuPhone.replace(" ", "");
		String stuAddrs = stuAddr.replace(" ", "");
		String stuClss = stuCls.replace(" ", "");
		String erroMsgStu = "";
		Stu clsx = stuService.findByStuID(stuIDs);
		if (!stuIDs.equals("")) {
			if (clsx!=null) {
				clsx.setStuID(stuIDs);
				clsx.setStuName(stuNames);
				clsx.setStuSex(stuSexs);
				clsx.setStuTime(stuTimes);
				clsx.setStuBirth(stuBirths);
				clsx.setStuPhone(stuPhones);
				clsx.setStuAddr(stuAddrs);
				clsx.setStuCls(stuClss);
				stuService.update(clsx);
				initLogin(req, resp);
			}else {
				erroMsgStu = "学生不存在";
				req.setAttribute("erroMsgStu", erroMsgStu);
				initLogin(req, resp);
			}
		}else {
			erroMsgStu = "请填入学生学号修改";
			req.setAttribute("erroMsgStu", erroMsgStu);
			initLogin(req, resp);
		}
	}

	private void ClsLikeQuery(HttpServletRequest req, HttpServletResponse resp) {
		String stuNames = stuName.replace(" ", "");
		try {
			if(!stuNames.equals("")){
				Stu clsx = new Stu();
				clsx.setStuName(stuNames);
				List<Stu> cls=stuService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgStu = "信息无匹配";
					req.setAttribute("erroMsgStu", erroMsgStu);
					initLogin(req, resp);
				}
				req.setAttribute("stu", cls);
				req.getRequestDispatcher("/WEB-INF/views/stu/likeQuery.jsp").forward(req, resp);
				return;
			}
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(!stuSex.equals("")){
				Stu clsx = new Stu();
				clsx.setStuSex(stuSex);
				List<Stu> cls=stuService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgStu = "信息无匹配";
					req.setAttribute("erroMsgStu", erroMsgStu);
					initLogin(req, resp);
				}
				req.setAttribute("stu", cls);
				req.getRequestDispatcher("/WEB-INF/views/stu/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(!stuTime.equals("")){
				Stu clsx = new Stu();
				clsx.setStuSex(stuSex);
				List<Stu> cls=stuService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgStu = "信息无匹配";
					req.setAttribute("erroMsgStu", erroMsgStu);
					initLogin(req, resp);
				}
				req.setAttribute("stu", cls);
				req.getRequestDispatcher("/WEB-INF/views/stu/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(!stuBirth.equals("")){
				Stu clsx = new Stu();
				clsx.setStuSex(stuSex);
				List<Stu> cls=stuService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgStu = "信息无匹配";
					req.setAttribute("erroMsgStu", erroMsgStu);
					initLogin(req, resp);
				}
				req.setAttribute("stu", cls);
				req.getRequestDispatcher("/WEB-INF/views/stu/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(!stuPhone.equals("")){
				Stu clsx = new Stu();
				clsx.setStuSex(stuSex);
				List<Stu> cls=stuService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgStu = "信息无匹配";
					req.setAttribute("erroMsgStu", erroMsgStu);
					initLogin(req, resp);
				}
				req.setAttribute("stu", cls);
				req.getRequestDispatcher("/WEB-INF/views/stu/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(!stuAddr.equals("")){
				Stu clsx = new Stu();
				clsx.setStuSex(stuSex);
				List<Stu> cls=stuService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgStu = "信息无匹配";
					req.setAttribute("erroMsgStu", erroMsgStu);
					initLogin(req, resp);
				}
				req.setAttribute("stu", cls);
				req.getRequestDispatcher("/WEB-INF/views/stu/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(!stuCls.equals("")){
				Stu clsx = new Stu();
				clsx.setStuSex(stuSex);
				List<Stu> cls=stuService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgStu = "信息无匹配";
					req.setAttribute("erroMsgStu", erroMsgStu);
					initLogin(req, resp);
				}
				req.setAttribute("stu", cls);
				req.getRequestDispatcher("/WEB-INF/views/stu/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void ClsLikeQueryID(HttpServletRequest req, HttpServletResponse resp) {
		Stu yuanxix = new Stu();
		yuanxix.setStuID(stuID);
		List<Stu> yuanxi=stuService.preQuery(yuanxix);
		if(yuanxi.isEmpty()){
			String erroMsgInsert = "信息无匹配";
			req.setAttribute("erroMsgStu", erroMsgInsert);
			initLogin(req, resp);
		}
		req.setAttribute("stu", yuanxi);
		try {
			req.getRequestDispatcher("/WEB-INF/views/stu/likeQuery.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
