package entity;

public class Student {
    private String name;
    private String cccd;
    private String registeredLicenseType;
    private String examDate;
    private String result;

    public Student(String name, String cccd, String registeredLicenseType, String examDate, String result) {
        this.name = name;
        this.cccd = cccd;
        this.registeredLicenseType = registeredLicenseType;
        this.examDate = examDate;
        this.result = result;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getRegisteredLicenseType() {
        return registeredLicenseType;
    }

    public void setRegisteredLicenseType(String registeredLicenseType) {
        this.registeredLicenseType = registeredLicenseType;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", cccd='" + cccd + '\'' +
                ", registeredLicenseType='" + registeredLicenseType + '\'' +
                ", examDate='" + examDate + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
