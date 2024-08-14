package firstVersion;

public class Course {
    private String name;
    private String capacity;
    private String[] major;


    public Course(String name, String capacity, String[] major) {
        this.name = name;
        this.capacity = capacity;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String[] getMajor() {
        return major;
    }

    public void setMajor(String[] major) {
        this.major = major;
    }
}
