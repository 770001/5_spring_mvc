package com.khilkevichigor.springmvc.Controller;

import com.khilkevichigor.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.khilkevichigor.springmvc.service.UserService;
import org.springframework.web.servlet.ModelAndView;

@Controller //контроллер Spring MVC
public class UserController {

    private UserService userService;

    @Autowired //автоматическое связывание(завсимость класса UserController и интерфейса UserService)
//    @Qualifier(value = "userService") //уточнитель (автосвязывание на класс реализующий интерфейс UserService)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "read", method = RequestMethod.GET)
    //маппимся на ссылку read, метод GET для получения списка всех юзеров, аналог ReadServlet
    public String allUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allUsers", this.userService.getAllUsers());
        return "read";
    }

    //получаем страницу create.jsp
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView addPage(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create");
        return modelAndView;
    }

    //создаем юзера
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
    public String deleteUser(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
        return "redirect:/read";
    }

    @RequestMapping(value = "update/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("allUsers", this.userService.getAllUsers());
        return "redirect:/read";
    }
}