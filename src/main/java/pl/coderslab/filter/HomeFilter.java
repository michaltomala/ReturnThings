package pl.coderslab.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "HomeFilter", urlPatterns = {"/home","/"})
public class HomeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        String url = request.getRequestURI();

        if(url.equals("/")){
            if(session.getAttribute("user")== null){
                response.sendRedirect(request.getContextPath()+"/landingPage");
                return;
            }else{
                response.sendRedirect(request.getContextPath()+"/home");
                return;
            }
        }

        if( (url.equals("/home"))) {
            if(session.getAttribute("user") == null){
                response.sendRedirect(request.getContextPath()+"/login");
                return;
            }
        }


        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }

}
