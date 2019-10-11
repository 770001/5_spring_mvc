package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //для получения атрибута из сервлета
        HttpSession session = req.getSession();

        //получаем атрибуты из сервлета
        User userLogin = (User) session.getAttribute("userLogin");
        String userRole = userLogin.getRole();

        //перенаправляем на /admin/read
        if (userRole.equals("admin")) {
            chain.doFilter(req, resp);
        }
        //перенаправляем на страницу /user/user
        else if (userRole.equals("user")) {
            req.setAttribute("userLogin", userLogin);
            resp.sendRedirect("/user/user");
        }
        //перенаправляем на /index
        else if (userRole == null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}

