 	package com.uu.show.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uu.show.entity.Cls;
import com.uu.show.entity.Subject;
import com.uu.show.service.ClassService;

@WebServlet(name ="class", urlPatterns = "/class")
public class ClassController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private ClassService classService = new ClassService();

	private String insert="";
	private String delete="";
	private String update="";
	private String query="";
	private String byID;
	private String operator="";
	
	private String clsID="";
	private String clsName="";
	private String clsType="";
	private String back="";
	private String likeQuery="";
	private String button="";
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		clsID = req.getParameter("clsID");
		clsName = req.getParameter("clsName");
		clsType = req.getParameter("clsType");
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
		case "查询":
			ClsQuery(req, resp);
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
			List<Cls> cls = classService.findAll();
			req.setAttribute("cls", cls);
			req.getRequestDispatcher("/WEB-INF/views/class/init.jsp").forward(req, resp);
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
			List<Cls> cls = classService.findAll();
			req.setAttribute("cls", cls);
			req.getRequestDispatcher("/WEB-INF/views/class/init.jsp").forward(req, resp);
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
		String clsIDs = clsID.replace(" ", "");
		String clsNames = clsName.replace(" ", "");
		String clsTypes = clsType.replace(" ", "");
		String erroMsgClass = "";
		boolean flag = false;

		Cls cls = classService.findByClsID(clsID);

		if (!clsIDs.equals("")) {
			if (cls==null) {
				flag = true;
			} else {
				erroMsgClass = "班级已存在";
			}
		} else {
			erroMsgClass = "请填入班级编号新增";
		}
		if (flag) {
			Cls clss=new Cls();
			clss.setClsID(clsIDs);
			clss.setClsName(clsNames);
			clss.setClsType(clsTypes);
			System.out.println(clsIDs + "--1");
			classService.insert(clss);
			initLogin(req, resp);
		} else {
			req.setAttribute("erroMsgClass", erroMsgClass);
			initLogin(req, resp);
		}
	}

	private void ClsDelete(HttpServletRequest req, HttpServletResponse resp) {
		String clsIDs = clsID.replace(" ", "");
		Cls cls = classService.findByClsID(clsIDs);
		String erroMsgClass = "";
		if (cls!=null) {
		classService.deletes(clsIDs);
		initLogin(req, resp);
		}else {
			erroMsgClass = "班级不存在";
			req.setAttribute("erroMsgClass", erroMsgClass);
			initLogin(req, resp);
		}
	}

	private void ClsUpdate(HttpServletRequest req, HttpServletResponse resp) {
		String clsIDs = clsID.replace(" ", "");
		String clsNames = clsName.replace(" ", "");
		String clsTypes = clsType.replace(" ", "");
		String erroMsgClass = "";
		Cls clsx = classService.findByClsID(clsIDs);
		if (!clsIDs.equals("")) {
			if (clsx!=null) {
				clsx.setClsID(clsIDs);
				clsx.setClsName(clsNames);
				clsx.setClsType(clsTypes);
				classService.update(clsx);
				initLogin(req, resp);
			}else {
				erroMsgClass = "班级不存在";
				req.setAttribute("erroMsgClass", erroMsgClass);
				initLogin(req, resp);
			}
		}else {
			erroMsgClass = "请填入班级编号修改";
			req.setAttribute("erroMsgClass", erroMsgClass);
			initLogin(req, resp);
		}
	}

	private void ClsQuery(HttpServletRequest req, HttpServletResponse resp) {
		String clsIDs = clsID.replace(" ", "");
		String clsNames = clsName.replace(" ", "");
		String clsTypes = clsType.replace(" ", "");
		String erroMsgClass = "";
		boolean flag = false;


		if (clsIDs.equals("")) {
				if (!clsNames.equals("")) {
					if (!clsTypes.equals("")) {
						flag = true;

					} else {
						erroMsgClass = "请填入所在系查询";
					}
				} else {
					erroMsgClass = "请填入班级名称查询";
				}
		} else {
			erroMsgClass = "不可填入班级编号查询";
		}
		if (flag) {
			Cls clsx=new Cls();
			clsx.setClsName(clsNames);
			clsx.setClsType(clsTypes);
			List<Cls> cls = classService.query(clsx);
			if(cls.isEmpty()){
				erroMsgClass = "信息无匹配";
				req.setAttribute("erroMsgClass", erroMsgClass);
				initLogin(req, resp);
			}
			try {
				req.setAttribute("cls", cls);
				req.getRequestDispatcher("/WEB-INF/views/class/init.jsp").forward(req, resp);
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
		} else {
			req.setAttribute("erroMsgClass", erroMsgClass);
			initLogin(req, resp);
		}
		
	}
	
	private void ClsLikeQuery(HttpServletRequest req, HttpServletResponse resp) {
		String clsNames = clsName.replace(" ", "");
		try {
			if(!clsNames.equals("")){
				Cls clsx = new Cls();
				clsx.setClsName(clsNames);
				List<Cls> cls=classService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgClass = "信息无匹配";
					req.setAttribute("erroMsgClass", erroMsgClass);
					initLogin(req, resp);
				}
				req.setAttribute("cls", cls);
				req.getRequestDispatcher("/WEB-INF/views/class/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(!clsType.equals("")){
				Cls clsx = new Cls();
				clsx.setClsType(clsType);
				List<Cls> cls=classService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgClass = "信息无匹配";
					req.setAttribute("erroMsgClass", erroMsgClass);
					initLogin(req, resp);
				}
				req.setAttribute("cls", cls);
				req.getRequestDispatcher("/WEB-INF/views/class/likeQuery.jsp").forward(req, resp);
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
		Cls yuanxix = new Cls();
		yuanxix.setClsID(clsID);
		List<Cls> yuanxi=classService.preQuery(yuanxix);
		if(yuanxi.isEmpty()){
			String erroMsgInsert = "信息无匹配";
			req.setAttribute("erroMsgClass", erroMsgInsert);
			initLogin(req, resp);
		}
		req.setAttribute("cls", yuanxi);
		try {
			req.getRequestDispatcher("/WEB-INF/views/class/likeQuery.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
