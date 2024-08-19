package ReviewConsole.Data;

public class Major {
    private String major;
    private String majorId;

    public Major(String major, String majorId) {
        this.major = major;
        this.majorId = majorId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }
}
