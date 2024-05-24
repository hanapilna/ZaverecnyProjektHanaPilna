package InsuranceApplication.Controllers;

import InsuranceApplication.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class represents controller for pages pertaining to insurance administration
 */
@Controller
public class InsurancePagesController {
    @Autowired
    private UserService userService;
    @Autowired
    private InsuranceService insuranceService;

    /**
     * Method checks the data entered into a new insurance form and saves a new insurance for the respective user if there are no errors detected
     * @param id of the user for which the insurance is created
     * @param insuranceDTO
     * @return modelAndView "insurance-feedback" with status message for the new insurance including list of errors if there are any
     */
    @PostMapping(value="insured-users/{id}/new-insurance/save")
    public ModelAndView postNewInsurance(@PathVariable(name="id")int id, @ModelAttribute InsuranceDTO insuranceDTO){
        ModelAndView modelAndView = new ModelAndView("insurance-feedback");
        User user = userService.get(id);
        modelAndView.addObject("user", user);
        InsuranceValidator validator = new InsuranceValidator();
        //Method checkForErrors called on validator checks the form inputs and sets the error messages = validator attributes as applicable.
        //The conditions that follow, work with these attributes
        validator.checkForErrors(insuranceDTO);
        boolean formHasErrors = validator.getErrorMessageAboutInsuranceDates()!=null || validator.getErrorMessageAboutInsuranceValue()!=null || validator.getErrorMessageAboutNumbers()!=null;

        if(formHasErrors){
            if(validator.getErrorMessageAboutNumbers()!=null){
                modelAndView.addObject("errorMessageAboutNumbers", validator.getErrorMessageAboutNumbers());
            }
            if(validator.getErrorMessageAboutInsuranceDates()!=null){
                modelAndView.addObject("errorMessageAboutInsuranceDates", validator.getErrorMessageAboutInsuranceDates());
            }
            if(validator.getErrorMessageAboutInsuranceValue()!=null){
                modelAndView.addObject("errorMessageAboutInsuranceValue", validator.getErrorMessageAboutInsuranceValue());
            }
        }else{
            try{
                Insurance insurance = new Insurance();
                insurance.setAttributesFromInsuranceDTO(insuranceDTO);
                insurance.setUser(user);
                insuranceService.save(insurance);
                String successMessage = "Pojištění vytvořeno :)";
                modelAndView.addObject("successMessage", successMessage);
            }catch (Exception e){
                String errorMessage = "Došlo k chybě při ukládání. Pojištění nebylo uloženo.";
                modelAndView.addObject("errorMessage", errorMessage);
            }
        }
        return modelAndView;
    }

    /**
     * Method deletes existing insurance
     * @param id of the insurance to be deleted
     * @return redirects to the main page with insured users
     */
    @GetMapping("/insured-users/{id}/delete")
    public String deleteInsurance(@PathVariable(name = "id")int id){
        try{
            insuranceService.delete(id);
            return "redirect:/insured-users";
        }catch (Exception e){
            return "redirect:/delete-error";
        }
    }

    /**
     * Method shows page with a form for updating existing insurance
     * @param id of the insurance to be edited
     * @return template "edit-insurance"
     */
    @GetMapping("/insured-users/{id}/edit-insurance")
    public ModelAndView showInsuranceEditPage(@PathVariable(name="id")int id){
        ModelAndView modelAndView = new ModelAndView("edit-insurance");
        Insurance insurance = insuranceService.get(id);
        modelAndView.addObject("insurance", insurance);
        return modelAndView;
    }

    /**
     * Method checks data entered into the form for updating existing insurance and saves the update if there are no detected errors
     * @param id of the insurance to be edited
     * @param insuranceDTO
     * @param insurance
     * @return modelAndView "insurance-feedback" with status report on the update including list of detected errors if there are any
     */
    @PostMapping(value = "/insured-users/{id}/edit-insurance/save")
    public ModelAndView postUpdatedInsurance(@PathVariable(name = "id")int id, @ModelAttribute InsuranceDTO insuranceDTO, @ModelAttribute Insurance insurance){
        ModelAndView modelAndView = new ModelAndView("insurance-feedback");
        Insurance insuranceToGetUser = insuranceService.get(id);
        User user = insuranceToGetUser.getUser();
        modelAndView.addObject("user", user);

        InsuranceValidator validator = new InsuranceValidator();
        //Method checkForErrors called on validator checks the form inputs and sets the error messages = validator attributes as applicable.
        //The conditions that follow, work with these attributes
        validator.checkForErrors(insuranceDTO);
        boolean formHasErrors = validator.getErrorMessageAboutInsuranceDates()!=null || validator.getErrorMessageAboutInsuranceValue()!=null || validator.getErrorMessageAboutNumbers()!=null;

        if(formHasErrors){
            if(validator.getErrorMessageAboutNumbers()!=null){
                modelAndView.addObject("errorMessageAboutNumbers", validator.getErrorMessageAboutNumbers());
            }
            if(validator.getErrorMessageAboutInsuranceDates()!=null){
                modelAndView.addObject("errorMessageAboutInsuranceDates", validator.getErrorMessageAboutInsuranceDates());
            }
            if(validator.getErrorMessageAboutInsuranceValue()!=null){
                modelAndView.addObject("errorMessageAboutInsuranceValue", validator.getErrorMessageAboutInsuranceValue());
            }
        }else{
            try{
                insurance.setAttributesFromInsuranceDTO(insuranceDTO);
                insurance.setUser(user);
                insuranceService.save(insurance);
                String successMessage = "Pojištění upraveno :)";
                modelAndView.addObject("successMessage", successMessage);
            }catch (Exception e){
                String errorMessage = "Došlo k chybě při ukládání. Změna v pojištění nebyla uložena.";
                modelAndView.addObject("errorMessage", errorMessage);
            }
        }
        return modelAndView;
    }
}
