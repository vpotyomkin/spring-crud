package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.Service.UserService;
import web.models.User;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("userInfo",new User());
        return "users";}

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public String add(@ModelAttribute User user){
        userService.add(user);
        return "redirect:/users";
    }

    @RequestMapping(path = "/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/users";}

    @GetMapping(path = "/users/edit/{id}")
    public String getUser(Model model, @PathVariable("id") Integer id) {
        User oldUser = userService.getById(id);
        model.addAttribute("userInfo", oldUser);
        return "edit";}

    @PostMapping(path = "/users/edit/{id}")
    public String editUser(@ModelAttribute User user, @PathVariable("id") Integer id) {
        userService.edit(user, id);
        return "redirect:/users";}
}