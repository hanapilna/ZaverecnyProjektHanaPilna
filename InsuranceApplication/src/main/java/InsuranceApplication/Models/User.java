package InsuranceApplication.Models;

import jakarta.persistence.*;

import java.util.Set;

/**
 * Class represents a User
 * Class corresponds with the table "insured_users" in the database
 */
@Entity
@Table(name="insured_users")
public class User {
    //attributes//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String birthDate;
    private String email;
    private String phone;
    private String street;
    private int houseNumber;
    private String city;
    private int zipCode;
    private String country;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Insurance> insuranceSet;

    //getters and setters//
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Insurance> getInsuranceSet() {
        return insuranceSet;
    }
    public void setInsuranceSet(Set<Insurance> insuranceSet) {
        this.insuranceSet = insuranceSet;
    }

    /**
     * Method sets attributes acquired from the UserDTO
     * @param userDTO
     */
    public void setAttributesFromRegistrationDTO(UserDTO userDTO){
        this.setName(userDTO.getName());
        this.setSurname(userDTO.getSurname());
        this.setBirthDate(userDTO.getBirthDate());
        this.setEmail(userDTO.getEmail());
        this.setPhone(userDTO.getPhone());
        this.setStreet(userDTO.getStreet());
        this.setHouseNumber(userDTO.getHouseNumber());
        this.setCity(userDTO.getCity());
        this.setZipCode(userDTO.getZipCode());
        this.setCountry(userDTO.getCountry());
    }
}
