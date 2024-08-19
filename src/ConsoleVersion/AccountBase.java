package ConsoleVersion;

public abstract class AccountBase
{
    private String username;
    private String password;
    private String identity;

    public AccountBase(String username, String password, String identity)
    {
        this.username = username;
        this.password = password;
        this.identity = identity;
    }

    public AccountBase()
    {
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getIdentity()
    {
        return identity;
    }

    public void setIdentity(String identity)
    {
        this.identity = identity;
    }
}
