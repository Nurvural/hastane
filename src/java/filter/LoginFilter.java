
package filter;

import entity.User;
import util.NavigationBean;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sabiha
 */
@WebFilter("/*")
public class LoginFilter implements Filter, Serializable {

    private User user;
    private NavigationBean nb;

    public NavigationBean getNb() {
        return nb;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //test etmek icin request and response 
        //burada süzgeç görevi üstlenilmiştir.
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        

        String url = req.getRequestURI();

        User u = (User) req.getSession().getAttribute("valid_user");
        //eğer kullanıcı yoksa 
        if (u == null) {

            if (url.contains("secret") || url.contains("logout") || url.contains("View") || url.contains("module")) {
                res.sendRedirect(req.getContextPath() + "/login.xhtml");

            } else {
                chain.doFilter(request, response);

            }

        } else //eğer kullanıcı var ise 
        {
            if (url.contains("register") || url.contains("login")) {//
                if (u.getUturu().equals("Admin")) {//giris yapan eğer admin ise
                    res.sendRedirect(req.getContextPath() + "/secret/secret.xhtml");
                   

                } else if (u.getUturu().equals("User")) {//giris yapan eğer kullanıcı ise 
                    res.sendRedirect(req.getContextPath() + "/Users/View.xhtml");

                } else {
                    res.sendRedirect(req.getContextPath() + "/login.xhtml");
                }

            } else if (url.contains("logout")) {
                req.getSession().invalidate();
                res.sendRedirect(req.getContextPath() + "/index.xhtml");

            } else if (u.getUturu().equals("User") && url.contains("secret")) {
                res.sendRedirect(req.getContextPath() + "/index.xhtml");
            } else {

                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
