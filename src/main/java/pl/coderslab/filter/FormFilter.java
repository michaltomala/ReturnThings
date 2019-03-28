package pl.coderslab.filter;

import pl.coderslab.entity.Bounty;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.Reception;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "FormFilter ", urlPatterns = "/form/*" )
public class FormFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        String url = request.getRequestURI();


        if(url.equals("/form/step2")){
            Bounty bounty = (Bounty) session.getAttribute("bounty");
            if(bounty==null){
                response.sendRedirect(request.getContextPath()+"/home");
                return;
            }
            if(bounty.getBountyType().isEmpty()){
                response.sendRedirect(request.getContextPath()+"/home");
                return;
            }
        }

        if(url.equals("/form/step3")){
            Bounty bounty = (Bounty) session.getAttribute("bounty");
            if(bounty==null){
                response.sendRedirect(request.getContextPath()+"/home");
                return;
            }
            if(bounty.getQuantityOfBags() == null){
                response.sendRedirect(request.getContextPath()+"/form/step2");
                return;
            }
        }

        if(url.equals("/form/step4")  ){
            Institution institution= (Institution) session.getAttribute("institution");
            if(institution==null){
                response.sendRedirect(request.getContextPath()+"/form/step3");
                return;
            }
        }

        if(url.equals("/form/step5")){
            Institution institution = (Institution) session.getAttribute("chosenInstitution");
            if(institution == null){
                response.sendRedirect(request.getContextPath()+"/form/step4");
                return;
            }
        }

        if(url.equals("/form/step6")){
            Reception reception = (Reception) session.getAttribute("reception");
            if(reception == null){
                response.sendRedirect(request.getContextPath()+"/form/step5");
                return;
            }
        }

        if(url.equals("/form/saveForm") || url.equals("/form/finallyStep")){

            Bounty bounty = (Bounty) session.getAttribute("bounty");
            if(bounty==null){
                response.sendRedirect(request.getContextPath()+"/home");
                return;
            }

            Reception reception = (Reception) session.getAttribute("reception");
            Institution chosenInstitution= (Institution) session.getAttribute("chosenInstitution");
            Institution institution = (Institution) session.getAttribute("institution");

            if(reception == null || chosenInstitution == null || institution ==null ||
                    bounty.getQuantityOfBags()==null || bounty.getBountyType().isEmpty()){
                response.sendRedirect(request.getContextPath()+"/form/step5");
                return;
            }
        }

        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }

}
