package pl.coderslab.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "AuthFilter", urlPatterns = {"/login","/register","/home"})
public class AuthFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        String url = request.getRequestURI();

        if( (url.equals("/login")) || (url.equals("/register")) ) {
            if(session.getAttribute("user") != null){
                response.sendRedirect(request.getContextPath()+"/");
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

    public void init(FilterConfig config) throws ServletException {

    }

}
