package entity;

public class Exam {
    private String date;
    private String location;
    private int vehicleCount;
    private String licenseType;
    private String examForm;

    public Exam(String date, String location, int vehicleCount, String licenseType, String examForm) {
        this.date = date;
        this.location = location;
        this.vehicleCount = vehicleCount;
        this.licenseType = licenseType;
        this.examForm = examForm;
    }

    // Getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(int vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getExamForm() {
        return examForm;
    }

    public void setExamForm(String examForm) {
        this.examForm = examForm;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", vehicleCount=" + vehicleCount +
                ", licenseType='" + licenseType + '\'' +
                ", examForm='" + examForm + '\'' +
                '}';
    }
}
