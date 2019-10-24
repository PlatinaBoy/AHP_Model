package com.man.priest.problem;

/**
 * ClassName: DataRecord <br/>
 * Description: <br/>
 * date: <br/>
 *
 * @author PlatinaBoy<br />
 */
public class DataRecord {
    private String id;
    private String state;
    private Integer register;
    private String relative;
    private String education;



    private String medicare;
    private Boolean hasComInsurance;



    private Double yearlyIncome;
    private String fixedYearIncome;
    private String dayJobYearIncome;
    private String farmIncome;
    private String cultureIncome;
    private String otherIncomeWithPension;
    private String otherIncomeWithLowGold;
    private String otherIncomeWithRental;
    private Double allCost;
    private Double xinNongHe;
    private Integer donation;



    private Boolean hasLoan;
    private Double loan;



    private Boolean hasHouse;
    private String houseType;
    private Boolean Renting;
    private Integer tenancyPeriod;
    private Double useCostPerMonth;
    private Boolean hasCar;
    private String carType;


    public DataRecord() {
    }

    public DataRecord(String[] initStr) {
        if (initStr.length == 27)
            try {
                setId(initStr[0].replace("'", ""));
                setState(initStr[1]);
                setRegister(initStr[2]);
                setRelative(initStr[3]);
                setEducation(initStr[4]);
                setMedicare(initStr[5]);
                setHasComInsurance(initStr[6]);
                setYearlyIncome(initStr[7]);
                setFixedYearIncome(initStr[8]);
                setDayJobYearIncome(initStr[9]);
                setFarmIncome(initStr[10]);
                setCultureIncome(initStr[11]);
                setOtherIncomeWithPension(initStr[12]);
                setOtherIncomeWithLowGold(initStr[13]);
                setOtherIncomeWithRental(initStr[14]);
                setAllCost(initStr[15]);
                setXinNongHe(initStr[16]);
                setDonation(initStr[17]);
                setHasLoan(initStr[18]);
                setLoan(initStr[19]);
                setHasHouse(initStr[20]);
                setHouseType(initStr[21]);
                setRenting(initStr[22]);
                setTenancyPeriod(initStr[23]);
                setUseCostPerMonth(initStr[24]);
                setHasCar(initStr[25]);
                setCarType(initStr[26]);

            } catch (Exception e) {
                System.out.println("参数数据类型错误，请检查输入！");
            }
    }

    public Boolean String2Boolean(String s) {
        if (s.equals("1")) {
            return true;
        }
        if (s.equals("0")) {
            return false;
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getRegister() {
        return this.register;
    }

    public void setRegister(String register) {
        this.register = register.isEmpty() ? 0 : Integer.valueOf(register);
    }

    public Double getYearlyIncome() {
        return this.yearlyIncome;
    }

    public void setYearlyIncome(String yearlyIncome) {
        this.yearlyIncome = yearlyIncome.isEmpty() ? null : Double.valueOf(yearlyIncome);
    }

    public String getFixedYearIncome() {
        return this.fixedYearIncome;
    }

    public void setFixedYearIncome(String fixedYearIncome) {
        this.fixedYearIncome = fixedYearIncome;
    }

    public String getDayJobYearIncome() {
        return this.dayJobYearIncome;
    }

    public void setDayJobYearIncome(String dayJobYearIncome) {
        this.dayJobYearIncome = dayJobYearIncome;
    }

    public String getFarmIncome() {
        return this.farmIncome;
    }

    public void setFarmIncome(String farmIncome) {
        this.farmIncome = farmIncome;
    }

    public String getCultureIncome() {
        return this.cultureIncome;
    }

    public void setCultureIncome(String cultureIncome) {
        this.cultureIncome = cultureIncome;
    }

    public String getOtherIncomeWithPension() {
        return this.otherIncomeWithPension;
    }

    public void setOtherIncomeWithPension(String otherIncomeWithPension) {
        this.otherIncomeWithPension = otherIncomeWithPension;
    }

    public String getOtherIncomeWithLowGold() {
        return this.otherIncomeWithLowGold;
    }

    public void setOtherIncomeWithLowGold(String otherIncomeWithLowGold) {
        this.otherIncomeWithLowGold = otherIncomeWithLowGold;
    }

    public String getOtherIncomeWithRental() {
        return this.otherIncomeWithRental;
    }

    public void setOtherIncomeWithRental(String otherIncomeWithRental) {
        this.otherIncomeWithRental = otherIncomeWithRental;
    }

    public Boolean getHasComInsurance() {
        return this.hasComInsurance;
    }

    public void setHasComInsurance(String hasComInsurance) {
        this.hasComInsurance = (hasComInsurance.isEmpty() ? null : String2Boolean(hasComInsurance));
    }

    public Boolean isHasHouse() {
        return this.hasHouse;
    }

    public void setHasHouse(String hasHouse) {
        this.hasHouse =
                (hasHouse.isEmpty() ? null : String2Boolean(hasHouse));
    }

    public Double getAllCost() {
        return this.allCost;
    }

    public void setAllCost(String allCost) {
        this.allCost =
                allCost.isEmpty() ? 0.0D : Double.valueOf(allCost);
    }

    public Double getLoan() {
        return this.loan;
    }

    public void setLoan(String loan) {
        this.loan =
                (loan.isEmpty() ? null : Double.valueOf(loan));
    }

    public String getHouseType() {
        return this.houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Integer getTenancyPeriod() {
        return this.tenancyPeriod;
    }

    public void setTenancyPeriod(String tenancyPeriod) {
        this.tenancyPeriod =
                tenancyPeriod.isEmpty() ? 0 : Integer.valueOf(tenancyPeriod);
    }

    public Double getUseCostPerMonth() {
        return this.useCostPerMonth;
    }

    public void setUseCostPerMonth(String useCostPerMonth) {
        this.useCostPerMonth =
                useCostPerMonth.isEmpty() ? 0.0D : Double.valueOf(useCostPerMonth);
    }

    public Boolean isHasCar() {
        return this.hasCar;
    }

    public void setHasCar(String hasCar) {
        this.hasCar =
                (hasCar.isEmpty() ? null : String2Boolean(hasCar));
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getRelative() {
        return this.relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    public String getEducation() {
        return this.education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMedicare() {
        return this.medicare;
    }

    public void setMedicare(String medicare) {
        this.medicare = medicare;
    }

    public Double getXinNongHe() {
        return this.xinNongHe;
    }

    public void setXinNongHe(String xinNongHe) {
        this.xinNongHe = xinNongHe.isEmpty() ? 0.0D : Double.valueOf(xinNongHe);
    }

    public Integer getDonation() {
        return this.donation;
    }

    public void setDonation(String donation) {
        this.donation = donation.isEmpty() ? 0 : Integer.valueOf(donation);

    }

    public Boolean getHasLoan() {
        return this.hasLoan;
    }

    public void setHasLoan(String hasLoan) {
        this.hasLoan = (hasLoan.isEmpty() ? null : String2Boolean(hasLoan));
    }

    public Boolean getRenting() {
        return this.Renting;
    }

    public void setRenting(String renting) {
        this.Renting = (renting.isEmpty() ? null : String2Boolean(renting));
    }

    @Override
    public String toString() {
        return "DataRecord{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", register=" + register +
                ", relative='" + relative + '\'' +
                ", education='" + education + '\'' +
                ", medicare='" + medicare + '\'' +
                ", hasComInsurance=" + hasComInsurance +
                ", yearlyIncome=" + yearlyIncome +
                ", fixedYearIncome='" + fixedYearIncome + '\'' +
                ", dayJobYearIncome='" + dayJobYearIncome + '\'' +
                ", farmIncome='" + farmIncome + '\'' +
                ", cultureIncome='" + cultureIncome + '\'' +
                ", otherIncomeWithPension='" + otherIncomeWithPension + '\'' +
                ", otherIncomeWithLowGold='" + otherIncomeWithLowGold + '\'' +
                ", otherIncomeWithRental='" + otherIncomeWithRental + '\'' +
                ", allCost=" + allCost +
                ", xinNongHe=" + xinNongHe +
                ", donation=" + donation +
                ", hasLoan=" + hasLoan +
                ", loan=" + loan +
                ", hasHouse=" + hasHouse +
                ", houseType=" + houseType +
                ", Renting=" + Renting +
                ", tenancyPeriod=" + tenancyPeriod +
                ", useCostPerMonth=" + useCostPerMonth +
                ", hasCar=" + hasCar +
                ", carType='" + carType + '\'' +
                '}';
    }
}