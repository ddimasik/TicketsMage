package common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping(value = "/login")
    public String login(ModelMap model) {
        return "login";
    }

    @GetMapping(value = "/accessdenied")
    public String loginerror(ModelMap model) {
        logger.debug("Wrong login password");
        model.addAttribute("error", "true");
        return "denied";
    }

    @GetMapping(value = "/logout")
    public String logout(ModelMap model) {
        return "login";
    }
}
