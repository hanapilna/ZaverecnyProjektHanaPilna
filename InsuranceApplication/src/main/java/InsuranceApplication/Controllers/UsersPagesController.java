package InsuranceApplication.Controllers;

import InsuranceApplication.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Class represents controller for pages pertaining to user administration
 */
@Controller
public class UsersPagesController {
    @Autowired
    private UserService userService;
    @Autowired
    private InsuranceService insuranceService;

    /**
     * Method shows the main page with a list of all current users
     * @param model
     * @return template "insured-users-main-page"
     */
    @GetMapping("/insured-users")
    public String showAdminPageInsured(Model model){
        List<User> listOfUsers = userService.listAll();
        model.addAttribute("listOfUsers", listOfUsers);
        return "insured-users-main-page";
    }

    /**
     * Method shows page with a form for creating a new user
     * @param userDTO
     * @return template "new-user"
     */
    @GetMapping("/new-user")
    public String showRegistrationForm(@ModelAttribute UserDTO userDTO){
        return "new-user";
    }

    /**
     * Method checks the data entered into the new user form and saves a new user if there are no errors detected.
     * @param userDTO
     * @return modelAndView "user-feedback" with status message for the new user including list of errors if there are any
     */
    @PostMapping(value = "/new-user/save")
    public ModelAndView postNewUserForm(@ModelAttribute UserDTO userDTO){
        ModelAndView modelAndView = new ModelAndView("user-feedback");
        UserValidator validator = new UserValidator();
        //Method checkForErrors called on validator checks the form inputs and sets the error messages = validator attributes as applicable.
        //The conditions that follow, work with these attributes
        validator.checkForErrors(userDTO);
        boolean formHasErrors = validator.getErrorMessageAboutAddress()!=null || validator.getErrorMessageAboutBirthDate()!=null || validator.getErrorMessageAboutNumbers()!=null || validator.getErrorMessageAboutPhoneNumber()!=null;

        if(formHasErrors){
            if(validator.getErrorMessageAboutNumbers()!=null){
                modelAndView.addObject("errorMessageAboutNumbers", validator.getErrorMessageAboutNumbers());
            }
            if(validator.getErrorMessageAboutBirthDate()!=null){
                modelAndView.addObject("errorMessageAboutBirthDate", validator.getErrorMessageAboutBirthDate());
            }
            if(validator.getErrorMessageAboutPhoneNumber()!=null){
                modelAndView.addObject("errorMessageAboutPhoneNumber", validator.getErrorMessageAboutPhoneNumber());
            }
            if(validator.getErrorMessageAboutAddress()!=null){
                modelAndView.addObject("errorMessageAboutAddress", validator.getErrorMessageAboutAddress());
            }
        }else{
            try{
                User user = new User();
                user.setAttributesFromRegistrationDTO(userDTO);
                userService.save(user);
                String successMessage = "Uživatel vytvořen :)";
                modelAndView.addObject("successMessage", successMessage);
            }catch (Exception e){
                String errorMessage = "Došlo k chybě při ukládání. Uživatel nebyl vytvořen.";
                modelAndView.addObject("errorMessage", errorMessage);
            }
        }
        return modelAndView;
    }

    /**
     * Method shows page with a form for editing existing user
     * @param id of the user to be edited
     * @return modelAndView "edit-user"
     */
    @GetMapping("/insured-users/edit/{id}")
    public ModelAndView showUserEditForm(@PathVariable(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView("edit-user");
        User user = userService.get(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /**
     * Method checks the data inserted into the form for updating an existing user and saves the update if there are no errors detected
     * @param userDTO
     * @param user
     * @return modelAndView "user-feedback" with status message for the updated user including list of errors if there are any
     */

    @PostMapping(value = "/edit-user/save")
    public ModelAndView postUpdatedUserForm(@ModelAttribute UserDTO userDTO, @ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView("user-feedback");
        UserValidator validator = new UserValidator();
        //Method checkForErrors called on validator checks the form inputs and sets the error messages = validator attributes as applicable.
        //The conditions that follow, work with these attributes
        validator.checkForErrors(userDTO);
        boolean formHasErrors = validator.getErrorMessageAboutAddress()!=null || validator.getErrorMessageAboutBirthDate()!=null || validator.getErrorMessageAboutNumbers()!=null || validator.getErrorMessageAboutPhoneNumber()!=null;

        if(formHasErrors){
            if(validator.getErrorMessageAboutNumbers()!=null){
                modelAndView.addObject("errorMessageAboutNumbers", validator.getErrorMessageAboutNumbers());
            }
            if(validator.getErrorMessageAboutBirthDate()!=null){
                modelAndView.addObject("errorMessageAboutBirthDate", validator.getErrorMessageAboutBirthDate());
            }
            if(validator.getErrorMessageAboutPhoneNumber()!=null){
                modelAndView.addObject("errorMessageAboutPhoneNumber", validator.getErrorMessageAboutPhoneNumber());
            }
            if(validator.getErrorMessageAboutAddress()!=null){
                modelAndView.addObject("errorMessageAboutAddress", validator.getErrorMessageAboutAddress());
            }
        }else{
            try {
                user.setAttributesFromRegistrationDTO(userDTO);
                userService.save(user);
                String successMessage = "Uživatel upraven :)";
                modelAndView.addObject("successMessage", successMessage);
            }catch (Exception e){
                String errorMessage = "Došlo k chybě při ukládání. Uživatel nebyl upraven.";
                modelAndView.addObject("errorMessage", errorMessage);
            }
        }
        return modelAndView;
    }

    /**
     * Method deletes existing user
     * @param id of the user to be deleted
     * @return redirects to the main page "insured-users-main-page"
     */
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id){
        try{
            userService.delete(id);
            return "redirect:/insured-users";
        }catch (Exception e){
            return "redirect:/delete-error";
        }
    }

    /**
     * Method shows user detail page including list of their insurances and a form for creating a new insurance
     * @param id of the user
     * @param insuranceDTO
     * @return ModelandView "user-detail" with the user's information
     */
    @GetMapping("/insured-users/{id}")
    public ModelAndView showDetailPage(@PathVariable(name = "id")int id, @ModelAttribute InsuranceDTO insuranceDTO){
        ModelAndView modelAndView = new ModelAndView("user-detail");
        User user = userService.get(id);
        modelAndView.addObject("user", user);
        List<Insurance> listOfInsuranceForUser = insuranceService.getAllInsurance(id);
        modelAndView.addObject("listOfInsuranceForUser", listOfInsuranceForUser);
        return modelAndView;
    }

}
