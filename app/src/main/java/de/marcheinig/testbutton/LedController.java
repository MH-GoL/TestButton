package de.marcheinig.testbutton;

import android.content.Context;

/**
 * Created by Marc on 10.08.2017.
 */

public class LedController
{
    ConnectionParam connection;

    private int rColor;
    private int gColor;
    private int bColor;


    private int status;
    private Context context;

    LedController(String host, Context gContext, int port, int r, int g, int b)
    {
        this.connection = new ConnectionParam(host, port);
        this.rColor = r;
        this.gColor = g;
        this.bColor = b;
        this.context = gContext;

    }

    int getRedValue()
    {
        return this.rColor;
    }

    int getGreenValue()
    {
        return this.gColor;
    }

    int getBlueValue()
    {
        return this.bColor;
    }

    String getHost()
    {
        return this.connection.getHost();
    }

    int getPort()
    {
        return this.connection.getPort();
    }

    int getStatus()
    {
        return status;
    }

    void setStatus(int status)
    {
        this.status = status;
    }

    Context getContext()
    {
        return this.context;
    }
}

