package com.boot.awsbook.springboot.web;

import com.boot.awsbook.springboot.config.auth.LoginUser;
import com.boot.awsbook.springboot.config.auth.dto.SessionUser;
import com.boot.awsbook.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "home";
    }

}
