package de.marcheinig.testbutton;

import android.content.Context;

public class LightController
{
    ConnectionParam connection;

    private int status;
    private boolean switchStatus;
    private Context context;

    LightController(String host, Context gContext, int port, boolean switchStatus)
    {
        this.connection = new ConnectionParam(host, port);
        this.switchStatus = switchStatus;
        this.context = gContext;
    }

    public boolean getSwitchStatus()
    {
        return this.switchStatus;
    }

    public String getHost()
    {
        return this.connection.getHost();
    }

    public int getPort()
    {
        return this.connection.getPort();
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public Context getContext()
    {
        return this.getContext();
    }
}