package ru.lab3.Controllers.Api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class SessionController {

    @GetMapping("/api/login")
    public String login(HttpServletRequest request) {
        String name = request.getParameter("login");
        String password = request.getParameter("password");

        if (name.equals("abylai") && password.equals("abylai-aidarov") ) {
            return "user/profile";
        }
        return "error";
    }


}
