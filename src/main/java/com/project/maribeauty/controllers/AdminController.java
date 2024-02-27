package com.project.maribeauty.controllers;

import com.project.maribeauty.dto.RequestDto;
import com.project.maribeauty.dto.UserDto;
import com.project.maribeauty.services.RequestService;
import com.project.maribeauty.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {
    private final UserService userService;
    private final RequestService requestService;
        //handler method to get list of users
        @GetMapping("/users")
        public String users(Model model){
            List<UserDto> users = userService.findAllUsers();
            model.addAttribute("users", users);
            return "admin/users_list";
        }

        //handler method to get list of requests
        @GetMapping(path = "/view_bookings")
        public String requests(Model model){
            List<RequestDto> requests = requestService.findAllRequests();
            model.addAttribute("requests", requests);
            return "admin/view_bookings";
        }
}
