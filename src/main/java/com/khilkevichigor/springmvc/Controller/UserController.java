package com.khilkevichigor.springmvc.Controller;

import com.khilkevichigor.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.khilkevichigor.springmvc.service.UserService;

@Controller //контроллер Spring MVC
public class UserController {

    private UserService userService;

    @Autowired//автоматическое связывание(завсимость класса UserController и интерфейса UserService)
    @Qualifier(value = "userService") //уточнитель (автосвязывание на класс реализующий интерфейс UserService)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //маппимся на ссылку users, метод GET для получения списка всех юзеров, аналог ReadServlet
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("allUsers", this.userService.getAllUsers());
        return "users";
    }

    //получаем страницу add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addPage(@ModelAttribute("add") User user, Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    //создаем юзера
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("add") User user) {
        this.userService.addUser(user);
        return "redirect:/users";
    }

    //получаем страницу edit
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(@PathVariable("id") long id, Model model) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    //обновляем юзера
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") User user, Model model) {
        this.userService.updateUser(user);
        model.addAttribute("allUsers", this.userService.getAllUsers());
        return "redirect:/users";
    }

    //удаляем юзера
    @RequestMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
        return "redirect:/users";
    }
}