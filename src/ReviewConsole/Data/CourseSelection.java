package ReviewConsole.Data;

public class CourseSelection {
    private String courseName;
    private String studentNumber;

    public CourseSelection(String courseName, String studentNumber) {
        this.studentNumber = studentNumber;
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
