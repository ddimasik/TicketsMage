package common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
public class HelloController {
    @RequestMapping("/")
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }

    @RequestMapping("/bye")
    public String printBye(ModelMap model) {
        model.addAttribute("messageBye", "Bye good boy!");
        return "bye";
    }
}