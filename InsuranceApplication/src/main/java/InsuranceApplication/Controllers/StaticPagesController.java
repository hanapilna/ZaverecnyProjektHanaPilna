package InsuranceApplication.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class represents a controller for a static page
 */
@Controller
public class StaticPagesController {

    /**
     * Method shows the index page of the application
     * @return template "index"
     */
    @GetMapping("/home")
    public String showAboutApplication(){
        return "index";
    }

}
