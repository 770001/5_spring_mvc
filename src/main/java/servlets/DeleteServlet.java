package servlets;

import service.UserService;
import service.UserServiceImpl;
import util.ValidateHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //получаем данные
        String reqStringId = req.getParameter("id");
        if (ValidateHelper.isValidate(reqStringId)) {
            long reqId = Long.parseLong(reqStringId);
            //удаляем
            userService.deleteUser(reqId);
        }
        try {
            //переадресация
            resp.sendRedirect("read");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}