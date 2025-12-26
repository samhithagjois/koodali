package koodali.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class HomeController {
    //Login to Admin view
    //login to teachers view
    //Leaderboard for students

    @RequestMapping("/home")
    public void homeSetting(){

    }
}
