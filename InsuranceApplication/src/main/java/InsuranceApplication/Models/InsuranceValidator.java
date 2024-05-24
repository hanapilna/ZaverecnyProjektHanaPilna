package InsuranceApplication.Models;

import java.time.LocalDate;

/**
 * Class represents validator for data entered during registration and editing of Insurance
 */
public class InsuranceValidator {
    //attributes//
    private String errorMessageAboutNumbers;
    private String errorMessageAboutInsuranceDates;
    private String errorMessageAboutInsuranceValue;

    //getters and setters//
    public String getErrorMessageAboutNumbers() {
        return errorMessageAboutNumbers;
    }
    public void setErrorMessageAboutNumbers(String errorMessageAboutNumbers) {
        this.errorMessageAboutNumbers = errorMessageAboutNumbers;
    }

    public String getErrorMessageAboutInsuranceDates() {
        return errorMessageAboutInsuranceDates;
    }
    public void setErrorMessageAboutInsuranceDates(String errorMessageAboutInsuranceDates) {
        this.errorMessageAboutInsuranceDates = errorMessageAboutInsuranceDates;
    }

    public String getErrorMessageAboutInsuranceValue() {
        return errorMessageAboutInsuranceValue;
    }
    public void setErrorMessageAboutInsuranceValue(String errorMessageAboutInsuranceValue) {
        this.errorMessageAboutInsuranceValue = errorMessageAboutInsuranceValue;
    }

    /**
     * Method checks all inputs from the Registration DTO that are not supposed to include numbers, sets errorMessageAboutNumbers if there are any numbers
     * @param insuranceDTO
     */
    public void checkForNumbers(InsuranceDTO insuranceDTO){
        String[] arrayForValidation = {insuranceDTO.getInsuranceType(), insuranceDTO.getInsuredObject()};
        for (int i = 0; i<arrayForValidation.length; i++){
            for (char s : arrayForValidation[i].toCharArray()){
                if(String.valueOf(s).matches("\\d+")){
                    this.errorMessageAboutNumbers = "Pole obsahuje čísla!";
                }
            }
        }
    }

    /**
     * Method checks input from the InsuranceDTO that is supposed to be a positive number, sets errorMessageAboutInsuranceValue if the value is 0 or negative
     * @param insuranceDTO
     */
    public void checkIfValidValue(InsuranceDTO insuranceDTO){
        int numberForValidation = insuranceDTO.getInsuranceValue();
        if(numberForValidation <= 0){
            this.errorMessageAboutInsuranceValue = "Hodnota pojištění musí být více než 0 Kč!";
        }
    }

    /**
     * Method checks if the start date of an insurance is after end date, sets errorMessageAboutInsuranceDates if it is so
     * @param insuranceDTO
     */
    public void checkIfStartDateIsAfterEndDate(InsuranceDTO insuranceDTO){
        LocalDate startDate = LocalDate.parse(insuranceDTO.getStartDate());
        LocalDate endDate = LocalDate.parse(insuranceDTO.getEndDate());
        LocalDate today = LocalDate.now();
        if(startDate.isAfter(endDate) || startDate.isBefore(today)){
            this.errorMessageAboutInsuranceDates = "Chyba v datu!";
        }
    }

    /**
     * Method combines the above listed methods for data validation into one
     * @param insuranceDTO
     */
    public void checkForErrors(InsuranceDTO insuranceDTO){
        checkForNumbers(insuranceDTO);
        checkIfValidValue(insuranceDTO);
        checkIfStartDateIsAfterEndDate(insuranceDTO);
    }
}
