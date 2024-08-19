package ReviewConsole.Data;

public class Course
{
    private String name;
    private String capacity;

    public Course(String name, String major)
    {
        this.name = name;
        this.capacity = major;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCapacity()
    {
        return capacity;
    }

    public void setCapacity(String capacity)
    {
        this.capacity = capacity;
    }

    @Override
    public String toString()
    {
        return name + " " + capacity;
    }
}
