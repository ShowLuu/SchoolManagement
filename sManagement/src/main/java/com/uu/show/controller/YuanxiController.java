package com.uu.show.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uu.show.entity.Yuanxi;
import com.uu.show.service.YuanxiService;

@WebServlet(name ="yuanxi", urlPatterns = "/yuanxi")
public class YuanxiController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private YuanxiService yuanxiService = new YuanxiService();
	
	private String operator="";

	private String insert="";
	private String delete="";
	private String update="";
	private String query="";
	private String byID="";
	
	private String xiID="";
	private String xiName="";
	private String yuan="";
	private String back="";
	private String likeQuery="";
	private String button="";
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		xiID = req.getParameter("xiID"); 
		xiName = req.getParameter("xiName"); 
		yuan = req.getParameter("yuan");
		byID=req.getParameter("byID");
		operator=req.getParameter("operator");
		
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
			yuanxiInsert(req, resp);
			break;
		case "删除":
			yuanxiDelete(req, resp);
			break;
		case "修改":
			yuanxiUpdate(req, resp);
			break;
		case "查询":
			yuanxiQuery(req, resp);
			break;
		case "模糊查询":
			yuanxiLikeQuery(req, resp);
			break;
		case "返回":
			initLogin(req, resp);
			break;
		case "查找":
			yuanxiLikeQueryID(req, resp);
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
			List<Yuanxi> yuanxi = yuanxiService.findAll();
			req.setAttribute("yuanxi", yuanxi);
			req.getRequestDispatcher("/WEB-INF/views/yuanxi/init.jsp").forward(req, resp);
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
	
	private void init(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Yuanxi> yuanxi = yuanxiService.findAll();
			req.setAttribute("yuanxi", yuanxi);
			req.getRequestDispatcher("/WEB-INF/views/yuanxi/init.jsp").forward(req, resp);
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

	private void yuanxiInsert(HttpServletRequest req, HttpServletResponse resp) {
		String erroMsgInsert = "";
		boolean flag = false;
		
		String xiIDs = xiID.replace(" ", "");
		String xiNames = xiName.replace(" ", "");
		String yuans = yuan.replace(" ", "");
		
//		if(xiID.length()==xiIDs.length()){
//			if(xiName.length()==xiNames.length()){
//				if(yuan.length()==yuans.length()){
//					
//				}else{
//					erroMsgInsert="所在学院不能有空格";
//					initLogin(req, resp);
//				}
//			}else{
//				erroMsgInsert="院系名称不能有空格";
//				initLogin(req, resp);
//			}
//		}else{
//			erroMsgInsert="院系编号插入不能有空格";
//			initLogin(req, resp);
//		}
		


		Yuanxi yuanxi = yuanxiService.findByXiID(xiIDs);

		if (!xiIDs.equals("")) {
			if (yuanxi==null) {
				flag=true;
			} else {
				erroMsgInsert = "院系已存在";
			}
		} else {
			erroMsgInsert = "请填入院系编号新增";
		}
		if (flag) {
			Yuanxi yuanxiInsert = new Yuanxi();
			yuanxiInsert.setXiID(xiIDs);
			yuanxiInsert.setXiName(xiNames);
			yuanxiInsert.setYuan(yuans);
			System.out.println(xiIDs + "--1");
			yuanxiService.insert(yuanxiInsert);
			initLogin(req, resp);
		} else {
			req.setAttribute("erroMsgInsert", erroMsgInsert);
			initLogin(req, resp);
		}
	}

	private void yuanxiDelete(HttpServletRequest req, HttpServletResponse resp) {
		String xiIDs = xiID.replace(" ", "");
		Yuanxi yuanxi = yuanxiService.findByXiID(xiIDs);
		String erroMsgInsert = "";
		if (yuanxi!=null) {
		yuanxiService.delete(xiIDs);
		initLogin(req, resp);
		}else {
			erroMsgInsert = "院系不存在";
			req.setAttribute("erroMsgInsert", erroMsgInsert);
			initLogin(req, resp);
		}
	}

	private void yuanxiUpdate(HttpServletRequest req, HttpServletResponse resp) {
		String xiIDs = xiID.replace(" ", "");
		String xiNames = xiName.replace(" ", "");
		String yuans = yuan.replace(" ", "");
		String erroMsgInsert = "";
		Yuanxi yuanxix = yuanxiService.findByXiID(xiIDs);
		if (!xiIDs.equals("")) {
			if (yuanxix!=null) {
				yuanxix.setXiID(xiIDs);
				yuanxix.setXiName(xiNames);
				yuanxix.setYuan(yuans);
				yuanxiService.update(yuanxix);
				initLogin(req, resp);
			}else {
				erroMsgInsert = "院系不存在";
				req.setAttribute("erroMsgInsert", erroMsgInsert);
				initLogin(req, resp);
			}
		}else {
			erroMsgInsert = "请填入院系编号修改";
			req.setAttribute("erroMsgInsert", erroMsgInsert);
			initLogin(req, resp);
		}
	}

	private void yuanxiQuery(HttpServletRequest req, HttpServletResponse resp) {
		String xiIDs = xiID.replace(" ", "");
		String xiNames = xiName.replace(" ", "");
		String yuans = yuan.replace(" ", "");
		String erroMsgInsert = "";
		boolean flag = false;


		if (xiIDs.equals("")) {
				if (!xiNames.equals("")) {
					if (!yuans.equals("")) {
						flag = true;

					} else {
						erroMsgInsert = "请填入所在学院查询";
					}
				} else {
					erroMsgInsert = "请填入院系名称查询";
				}
		} else {
			erroMsgInsert = "不可填入院系编号查询";
		}
		if (flag) {
			Yuanxi yuanxix=new Yuanxi();
			yuanxix.setXiName(xiNames);
			yuanxix.setYuan(yuans);
			List<Yuanxi> yuanxi = yuanxiService.query(yuanxix);
			if(yuanxi.isEmpty()){
				erroMsgInsert = "信息无匹配";
				req.setAttribute("erroMsgInsert", erroMsgInsert);
				initLogin(req, resp);
			}
			try {
				req.setAttribute("yuanxi", yuanxi);
				req.getRequestDispatcher("/WEB-INF/views/yuanxi/init.jsp").forward(req, resp);
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
			req.setAttribute("erroMsgInsert", erroMsgInsert);
			initLogin(req, resp);
		}
		
	}

	private void yuanxiLikeQuery(HttpServletRequest req, HttpServletResponse resp) {
		String xiNames = xiName.replace(" ", "");
		String yuans = yuan.replace(" ", "");
		try {
			if(!xiNames.equals("")){
				Yuanxi yuanxix = new Yuanxi();
				yuanxix.setXiName(xiNames);
				List<Yuanxi> yuanxi=yuanxiService.preQuery(yuanxix);
				if(yuanxi.isEmpty()){
					String erroMsgInsert = "信息无匹配";
					req.setAttribute("erroMsgInsert", erroMsgInsert);
					initLogin(req, resp);
				}
				req.setAttribute("yuanxi", yuanxi);
				req.getRequestDispatcher("/WEB-INF/views/yuanxi/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(!yuans.equals("")){
				Yuanxi yuanxix = new Yuanxi();
				yuanxix.setYuan(yuans);
				List<Yuanxi> yuanxi=yuanxiService.preQuery(yuanxix);
				if(yuanxi.isEmpty()){
					String erroMsgInsert = "信息无匹配";
					req.setAttribute("erroMsgInsert", erroMsgInsert);
					initLogin(req, resp);
				}
				req.setAttribute("yuanxi", yuanxi);
				req.getRequestDispatcher("/WEB-INF/views/yuanxi/likeQuery.jsp").forward(req, resp);
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
	
	private void yuanxiLikeQueryID(HttpServletRequest req, HttpServletResponse resp) {
				Yuanxi yuanxix = new Yuanxi();
				yuanxix.setXiID(xiID);
				List<Yuanxi> yuanxi=yuanxiService.preQuery(yuanxix);
				if(yuanxi.isEmpty()){
					String erroMsgInsert = "信息无匹配";
					req.setAttribute("erroMsgInsert", erroMsgInsert);
					initLogin(req, resp);
				}
				req.setAttribute("yuanxi", yuanxi);
				try {
					req.getRequestDispatcher("/WEB-INF/views/yuanxi/likeQuery.jsp").forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
}
