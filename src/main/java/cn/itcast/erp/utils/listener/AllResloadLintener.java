package cn.itcast.erp.utils.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.vo.ResModel;

public class AllResloadLintener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		ResEbi resEbi = (ResEbi) ctx.getBean("resEbi");
		List<ResModel> resList = resEbi.getAll();
		StringBuilder sbf = new StringBuilder();
		for(ResModel res: resList) {
			sbf.append(res.getUrl());
			sbf.append(",");
		}
		sc.setAttribute("allRes", sbf.toString());
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {}
}
