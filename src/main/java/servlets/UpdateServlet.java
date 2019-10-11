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

public class UpdateServlet extends HttpServlet {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //получаем данные
        String reqStringId = req.getParameter("id");
        if (ValidateHelper.isValidate(reqStringId)) {
            long reqId = Long.parseLong(reqStringId);
            User existingUser = userService.getUserById(reqId);
            //перенаправляем на JSP
            req.setAttribute("existingUser", existingUser);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/admin/update.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
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
        String reqStringId = req.getParameter("id");
        if (ValidateHelper.isValidate(reqStringId)) {
            long reqId = Long.parseLong(reqStringId);
            String reqUpdateName = req.getParameter("name");
            String reqUpdateSurname = req.getParameter("surname");
            String reqUpdateRole = req.getParameter("role");
            String reqUpdateStringAge = req.getParameter("age");
            if (ValidateHelper.isValidate(reqUpdateName, reqUpdateSurname, reqUpdateRole, reqUpdateStringAge)) {
                int reqUpdateAge = Integer.parseInt(reqUpdateStringAge);
                //обновляем
                userService.updateUser(new User(reqId, reqUpdateName, reqUpdateSurname, reqUpdateRole, reqUpdateAge));
            }
            try {
                //переадресация
                resp.sendRedirect("read");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}