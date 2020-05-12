//package mgw.web_lavage_auto.controller;
// 
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
// 
///**
// * Servlet Filter implementation class EncodingFilter
// */
//@WebFilter("/EncodingFilter")
//public class EncodingFilter implements Filter {
// 
//    /**
//     * Default constructor.
//     */
//    public EncodingFilter() { }
// 
//    /**
//     * @see Filter#destroy()
//     */
//    @Override
//    public void destroy() { }
// 
//    /**
//     * @param request
//     * @param response
//     * @param chain
//     * @throws java.io.IOException
//     * @throws javax.servlet.ServletException
//     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//     */
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        response.setCharacterEncoding("UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        chain.doFilter(request, response);
//    }
// 
//    /**
//     * @param fConfig
//     * @throws javax.servlet.ServletException
//     * @see Filter#init(FilterConfig)
//     */
//    @Override
//    public void init(FilterConfig fConfig) throws ServletException { }
// 
//}