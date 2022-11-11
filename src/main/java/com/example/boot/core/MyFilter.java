package com.example.boot.core;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ltk
 */
public class MyFilter implements Filter {

  private static final String UN_KNOWN = "unKnown";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("===init my filter===");
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    //设置跨域
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    response.addHeader("Access-Control-Allow-Credentials", "true");
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Methods", "GET, POST");
    response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN");

    HttpServletRequest request = (HttpServletRequest) servletRequest;
    System.out.println("The path is : " + request.getRequestURI());
    System.out.println("The IP is : " + getIp(request));
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
    System.out.println("===destroy my filter===");
  }

  public static String getIp(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");
    if (!StringUtils.isEmpty(ip) && !UN_KNOWN.equalsIgnoreCase(ip)) {
      // 多次反向代理后会有多个ip值，第一个ip才是真实ip
      int index = ip.indexOf(",");
      if (index != -1) {
        return ip.substring(0, index);
      } else {
        return ip;
      }
    }
    ip = request.getHeader("X-Real-IP");
    if (!StringUtils.isEmpty(ip) && !UN_KNOWN.equalsIgnoreCase(ip)) {
      return ip;
    }
    return request.getRemoteAddr();
  }
}
