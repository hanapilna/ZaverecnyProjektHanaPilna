package InsuranceApplication.Models;

import java.time.LocalDate;

/**
 * Class represents validator for data entered during registration and editing of User
 */
public class UserValidator {
    //attributes//
    private String errorMessageAboutNumbers;
    private String errorMessageAboutBirthDate;
    private String errorMessageAboutPhoneNumber;
    private String errorMessageAboutAddress;

    //getters and setters//
    public String getErrorMessageAboutNumbers() {
        return errorMessageAboutNumbers;
    }
    public void setErrorMessageAboutNumbers(String errorMessageAboutNumbers) {
        this.errorMessageAboutNumbers = errorMessageAboutNumbers;
    }

    public String getErrorMessageAboutBirthDate() {
        return errorMessageAboutBirthDate;
    }
    public void setErrorMessageAboutBirthDate(String errorMessageAboutBirthDate) {
        this.errorMessageAboutBirthDate = errorMessageAboutBirthDate;
    }

    public String getErrorMessageAboutPhoneNumber() {
        return errorMessageAboutPhoneNumber;
    }
    public void setErrorMessageAboutPhoneNumber(String errorMessageAboutPhoneNumber) {
        this.errorMessageAboutPhoneNumber = errorMessageAboutPhoneNumber;
    }

    public String getErrorMessageAboutAddress() {
        return errorMessageAboutAddress;
    }
    public void setErrorMessageAboutAddress(String errorMessageAboutAddress) {
        this.errorMessageAboutAddress = errorMessageAboutAddress;
    }

    /**
     * Method checks all inputs from the UserDTO that are not supposed to include numbers, sets errorMessageAboutNumbers if there are any numbers found
     * @param userDTO
     */
    public void checkForNumber(UserDTO userDTO){
        String[] arrayForValidation = {userDTO.getName(), userDTO.getSurname(), userDTO.getStreet(), userDTO.getCity(), userDTO.getCountry()};
        for (int i = 0; i<arrayForValidation.length; i++){
            for (char s : arrayForValidation[i].toCharArray()){
                if(String.valueOf(s).matches("\\d+")){
                    this.errorMessageAboutNumbers = "Pole obsahuje čísla!";
                }
            }
        }
    }

    /**
     * Method checks all inputs from the UserDTO that are supposed to be positive numbers, sets errorMessageAboutAddress if the number is zero or negative
     * @param userDTO
     */
    public void checkIfNegative(UserDTO userDTO){
        int[] arrayForValidation = {userDTO.getHouseNumber(), userDTO.getZipCode()};
        for(int i = 0; i < arrayForValidation.length; i++){
            if(arrayForValidation[i] <= 0){
                this.errorMessageAboutAddress = "Číslo popisné ani poštovní směrovací číslo nesmí být nula!";
            }
        }
    }

    /**
     * Method checks if date of birth entered for user is not in the future, sets errorMessageAboutBirthDate, if the date is in the future
     * @param userDTO
     */
    public void checkIfAfter(UserDTO userDTO){
        LocalDate birthDate = LocalDate.parse(userDTO.getBirthDate());
        LocalDate today = LocalDate.now();
        if(birthDate.isAfter(today)){
            this.errorMessageAboutBirthDate = "Datum narození nesmí být v budoucnosti!";
        }
    }

    /**
     * Method checks if the entered phone number has the expected length and if it includes valid call code, which is either +420 or +421,
     * sets errorMessageAboutPhoneNumber if the phone number starts with anything but allowed call codes and/or does not have the expected length
     * @param userDTO
     */
    public void checkIfInvalid(UserDTO userDTO){
        String phoneNumber = userDTO.getPhone();
        if((!phoneNumber.startsWith("+420") && (phoneNumber.length()!=13)) || (!phoneNumber.startsWith("+421") && (phoneNumber.length()!=13))){
            this.errorMessageAboutPhoneNumber = "Chyba v telefoním čísle!";
        }
    }

    /**
     * Method combines the above listed methods for data validation into one
     * @param userDTO
     */
    public void checkForErrors(UserDTO userDTO){
        checkForNumber(userDTO);
        checkIfInvalid(userDTO);
        checkIfAfter(userDTO);
        checkIfNegative(userDTO);
    }
}
