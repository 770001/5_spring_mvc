package Controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired(required = true) //автоматическое связывание(завсимость класса UserController и интерфейса UserService)
    @Qualifier(value = "userService") //уточнитель (автосвязывание на класс реализующий интерфейс UserService)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //маппимся на ссылку read, метод GET для получения списка всех юзеров
    //аналог ReadServlet
    @RequestMapping(value = "read", method = RequestMethod.GET)
    public String allUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allUsers", this.userService.getAllUsers());
        return "read";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            this.userService.addUser(user);
        } else {
            this.userService.updateUser(user);
        }
        return "redirect:/read";
    }

    @RequestMapping(value = "delete/{id}")


}
