package ReviewConsole.Data;

public class CourseCompatibility
{
    private String courseName;
    private String major;

    public CourseCompatibility(String courseName, String major) {
        this.courseName = courseName;
        this.major = major;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
