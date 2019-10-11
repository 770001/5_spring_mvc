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

public class CreateServlet extends HttpServlet {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //перенаправляем на JSP
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/admin/create.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //задаем UTF8
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //получаем данные
        String reqName = req.getParameter("name");
        String reqSurname = req.getParameter("surname");
        String reqRole = req.getParameter("role");
        String reqStringAge = req.getParameter("age");
        if (ValidateHelper.isValidate(reqName, reqSurname, reqRole, reqStringAge)) {
            int reqAge = Integer.parseInt(reqStringAge);
            //добавляем
            userService.addUser(new User(reqName, reqSurname, reqRole, reqAge));
        }
        try {
            //переадресация
            resp.sendRedirect("read");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}