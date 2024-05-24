package InsuranceApplication.Models;

/**
 * Class represents a data transfer object for Insurance
 */
public class InsuranceDTO {
    //attributes//
    private String insuranceType;
    private String startDate;
    private String endDate;
    private String insuredObject;
    private int insuranceValue;

    //getters and setters//
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
}
