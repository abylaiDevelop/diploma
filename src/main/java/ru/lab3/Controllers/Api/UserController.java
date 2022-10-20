package ru.lab3.Controllers.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lab3.DAO.UserDAO;
import ru.lab3.Models.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {
    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDAO.index());
        return "people/index";
    }

//    @GetMapping("/test")
//    private ResponseEntity<List<User>> createWithResponseEntityBuilder() {
//        List<User> user = new ArrayList<>();
//        user = userDAO.index();
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("header1", "value1");
//        responseHeaders.set("header2", "value2");
//
//        return ResponseEntity.
//                status(HttpStatus.OK).
//                headers(responseHeaders).
//                body(user);
//    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        userDAO.save(user);
        return "redirect:/api/user";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User person) {
        return "people/new";
    }
}
