package ReviewConsole.Data;

public class Student {
    private String number;
    private String name;
    private String major;
    private String password;

    public Student() {}

    public Student(String number, String name, String major, String password) {
        this.number = number;
        this.name = name;
        this.major = major;
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return number + " " + name + " " + major + " " + password;
    }

}
