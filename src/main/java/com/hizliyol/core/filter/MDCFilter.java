package com.hizliyol.core.filter;

import com.hizliyol.core.session.SessionBean;
import com.hizliyol.core.spring.SpringAware;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by bilge_gilleez on 18.01.2018.
 */
public class MDCFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if(SpringAware.getContext().getBean(SessionBean.class) != null){
            String user = SpringAware.getContext().getBean(SessionBean.class).getUserName();
            if(user != null)
                MDC.put("username",user);
            else
                MDC.put("username","");
        }
        if(req.getSession() != null)
        MDC.put("sessionId",req.getSession().getId());
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
