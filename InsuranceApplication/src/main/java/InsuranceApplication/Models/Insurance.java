package InsuranceApplication.Models;

import jakarta.persistence.*;
import static jakarta.persistence.ConstraintMode.CONSTRAINT;

/**
 * Class represents Insurance assigned to a respective User
 * Class corresponds with the table "insurance" in the database (table "insurance" is connected to the table "insured_users" via N:1 connection on "user_id")
 */
@Entity
@Table(name = "insurance")
public class Insurance {
    //attributes//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(value = CONSTRAINT, foreignKeyDefinition = "FOREIGN KEY (user_id) ON DELETE CASCADE"))
    private User user;
    private String insuranceType;
    private String startDate;
    private String endDate;
    private String insuredObject;
    private int insuranceValue;

    //getters and setters//
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getInsuranceType() {
        return insuranceType;
    }
    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInsuredObject() {
        return insuredObject;
    }
    public void setInsuredObject(String insuredObject) {
        this.insuredObject = insuredObject;
    }

    public int getInsuranceValue() {
        return insuranceValue;
    }
    public void setInsuranceValue(int insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    /**
     * Method sets attributes acquired from the Insurance DTO
     * @param insuranceDTO
     */
    public void setAttributesFromInsuranceDTO(InsuranceDTO insuranceDTO){
        this.setInsuranceType(insuranceDTO.getInsuranceType());
        this.setStartDate(insuranceDTO.getStartDate());
        this.setEndDate(insuranceDTO.getEndDate());
        this.setInsuredObject(insuranceDTO.getInsuredObject());
        this.setInsuranceValue(insuranceDTO.getInsuranceValue());
    }
}
