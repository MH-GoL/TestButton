package de.marcheinig.testbutton;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class LedSocket extends AsyncTask<LedController, Integer, LedController>
{

    @Override
    protected void onProgressUpdate(Integer... progress)
    {

        //Toast.makeText(getApplicationContext(), "Farbe wird geändert", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected LedController doInBackground(LedController... ledController)
    {
        Socket mSocket;
        //BufferedReader mBufferedReader;
        BufferedWriter mBufferedWriter;
        Log.d("doInBackground", "gestartet");

        try
        {
            mSocket = new Socket(ledController[0].getHost(), ledController[0].getPort());


            //mBufferedReader = new BufferedReader(
            //        new InputStreamReader(mSocket.getInputStream())
            //);

            mBufferedWriter = new BufferedWriter(
                    new OutputStreamWriter((mSocket.getOutputStream()))
            );
            mBufferedWriter.write("GET /rgb?r=" + ledController[0].getRedValue() + "&g=" + ledController[0].getGreenValue() + "&b=" + ledController[0].getBlueValue() + " HTTP/1.1\r\n\r\n");
            mBufferedWriter.flush();
            mSocket.close();
            publishProgress(10, 10);

        }
        catch (UnknownHostException e)
        {
            Log.d(ledController[0].getHost() + "-UhE", e.getMessage());
            ledController[0].setStatus(-1);
            return ledController[0];
        }
        catch (IOException e)
        {
            Log.d(ledController[0].getHost() + "-IoE", e.getMessage());
            ledController[0].setStatus(-2);
            return ledController[0];
        }
        catch (NullPointerException e)
        {
            Log.d(ledController[0].getHost() + "-NpE", e.getMessage());
            ledController[0].setStatus(-3);
            return ledController[0];
        }
        ledController[0].setStatus(1);
        return ledController[0];
    }

    @Override
    protected void onPostExecute(LedController result)
    {
        Log.d("onPostExecute", result.toString());
        if (result.getStatus() <= 0)
        {
            Toast.makeText(result.getContext(), "Controller \"" + result.getHost() + "\" ist nicht erreichbar!", Toast.LENGTH_SHORT).show();
        }
		else
		{
			Toast.makeText(result.getContext(), "Controller \"" + result.getHost() + "\" ist nicht erreichbar!", Toast.LENGTH_SHORT).show();
		}
    }
}
