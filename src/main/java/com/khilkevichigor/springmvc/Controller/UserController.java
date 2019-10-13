package com.khilkevichigor.springmvc.Controller;

import com.khilkevichigor.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    //маппимся на ссылку read, метод GET для получения списка всех юзеров, аналог ReadServlet
    @RequestMapping(value = "read", method = RequestMethod.GET)
    public String allUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allUsers", this.userService.getAllUsers());
        return "read";
    }

    //получаем страницу create.jsp
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView addPage() {
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

    //получаем страницу update.jsp
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public ModelAndView updatePage(@RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        modelAndView.addObject("user", this.userService.getUserById(id));
        return modelAndView;
    }

    //обновляем юзера
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView(); 
        modelAndView.setViewName("redirect:/read");
        userService.updateUser(user);
        return modelAndView;
    }

    //удаляем юзера
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/read");
        userService.deleteUser(id);
        return modelAndView;
    }
}