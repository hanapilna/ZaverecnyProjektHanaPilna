package InsuranceApplication.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class represents controller for error message page
 */
@Controller
public class ErrorMessageController {

    /**
     * Method shows page with error message that may occur during the process of deleting items from the database
     * @return template "delete-error"
     */
    @GetMapping("/delete-error")
    public String showDeleteErrorPage(){
        return "delete-error";
    }
}
