package com.ports.filter;


import com.ports.utils.ArrayUtils;
import com.ports.utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RequestProcessFilter implements Filter {
	 private String allowOrigin;
	 private String allowMethods;   
    @Override
    public void init(FilterConfig filterConfig){
    	 allowOrigin = filterConfig.getInitParameter("allowOrigin");
         allowMethods = filterConfig.getInitParameter("allowMethods");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (StringUtils.isEmpty(allowOrigin)) {
            List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
            if (ArrayUtils.isEmpty(allowOriginList.toArray())) {
                String currentOrigin = request.getHeader("Origin");
                if (allowOriginList.contains(currentOrigin)) {
                    response.setHeader("Access-Control-Allow-Origin", currentOrigin);
                }
            }
        }
        if (!StringUtils.isEmpty(allowMethods)) {  
            response.setHeader("Access-Control-Allow-Methods", allowMethods);  
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

}
