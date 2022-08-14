package com.rdms.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityHeadersInterceptor implements HandlerInterceptor{



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

       if(requestURL.toString().contains(".css")){
          // response.setHeader("Content-Type", "text/css");
       }
        //response.setHeader("X-Content-Type-Options", "nosniff");
       // super.postHandle(request, response, handler, modelAndView);
        return  true;
    }


}
