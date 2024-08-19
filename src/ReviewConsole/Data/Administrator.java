package ReviewConsole.Data;

import java.util.ArrayList;

public class Administrator {
    private String account;
    private String password;

    public Administrator()
    {
    }

    public Administrator(String account, String password)
    {
        this.account = account;
        this.password = password;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
