package de.marcheinig.testbutton;

public class ConnectionParam
{
    private String host;
    private int port;

    ConnectionParam(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    String getHost()
    {
        return host;
    }

    int getPort()
    {
        return this.port;
    }
}
