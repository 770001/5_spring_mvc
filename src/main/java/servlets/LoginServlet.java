package servlets;

import model.User;
import service.UserService;
import service.UserServiceImpl;
import util.ValidateHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoginServlet extends HttpServlet {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //задаем UTF8
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //получаем данные с формы login
        String reqName = req.getParameter("name");
        String reqSurname = req.getParameter("surname");
        User userLogin = null;
        if (ValidateHelper.isValidate(reqName, reqSurname)) {
            //получаем юзера из БД
            userLogin = userService.getUserByNameAndSurname(reqName, reqSurname);
        }

        //через сессию задаем атрибуты для проверки в фильтре
        req.getSession().setAttribute("userLogin", userLogin);

        //перенаправляем на read
        resp.sendRedirect("/admin/read");
    }
}