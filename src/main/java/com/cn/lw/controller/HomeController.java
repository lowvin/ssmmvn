package com.cn.lw.controller;

import com.cn.lw.pojo.User;
import com.cn.lw.service.UserService;
import com.cn.lw.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

/**
 * Created by 罗文 on 2016/12/26.
 */
@Controller
//@RequestMapping("/user")
public class HomeController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping(value = "/showUser" , method = RequestMethod.GET)
    public String home(HttpServletRequest request,Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
        return "showUser";
    }
    @RequestMapping(value = "/test" , method = RequestMethod.GET)
    public ModelAndView test(String name){
        Map map = new HashMap();
        System.out.println(name);
        map.put("name",name);
        return new ModelAndView(new MappingJackson2JsonView(),map);

    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(String userName, String password, Model model) throws Exception {
        User user = new User();
        user=userService.checkLogin(userName, password);
        if(user!=null){
           // return user;// 路径 WEB-INF/pages/welcome.jsp
            System.out.println("ssssssss");
            model.addAttribute(user.getUserName());
            return "forward:/";
        }
        return null;
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

//    @RequestMapping(value = "/register",method = RequestMethod.GET)
//    public String register(){
//        return "register";
//    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public void registering(@RequestBody User user){
//        System.out.println(userName);
//        User user = new User();
//        user.setUserName(userName);
//        user.setPassword(password);
//        user.setEmail(email);
        user = userService.register(user);
        //System.out.println(user.getUserName());
        //return 0;
    }

    @RequestMapping(value = "/logreg",method = RequestMethod.GET)
    public String logreg(){
        return "logining";
    }
}
