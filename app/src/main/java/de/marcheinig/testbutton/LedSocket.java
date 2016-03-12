package de.marcheinig.testbutton;
import java.net.*;

// Diese Klasse definiert die Netzwerkschnittstelle
// zur Kontrolle eines RGB-LED-Controllers

public class LedSocket
{
	private String host;
	private int port;

	private int rColor;
	private int gColor;
	private int bColor;

	private int mode;

	private int status;
	
	private Socket socket;


	public int getStatus()
	{
		return status;
	}

	
	public boolean setColor(int r, int g, int b)
	{
		this.rColor = r;
		this.gColor = g;
		this.bColor = b;
		
		return true;
	}

	public int[] getColor()
	{
		int colorArray [] = {rColor,gColor,bColor};
		return colorArray;
	}


	public boolean connect()
	{
		if (this.port > 0 || this.host.isEmpty())
		{
			return false;
		}
		return true;
	}
	
	public boolean connect(String host)
	{
		return true;
	}

	public boolean connect(String host, int port)
	{
		return true;
	}

	
	public void setPort(int port)
	{
		this.port = port;
	}

	public int getPort()
	{
		return port;
	}


	public void setHost(String host)
	{
		this.host = host;
	}

	public String getServer()
	{
		return host;
	}
}
