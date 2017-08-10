package de.marcheinig.testbutton;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Marc on 10.08.2017.
 */

public class LightSocket extends AsyncTask<LightController, Integer, LightController>
{

    @Override
    protected void onProgressUpdate(Integer... progress)
    {

        //Toast.makeText(getApplicationContext(), "Farbe wird geändert", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected LightController doInBackground(LightController... lightController)
    {
        Socket mSocket;
        //BufferedReader mBufferedReader;
        BufferedWriter mBufferedWriter;
        Log.d("doInBackground", "gestartet");

        try
        {
            mSocket = new Socket(lightController[0].getHost(), lightController[0].getPort());


            //mBufferedReader = new BufferedReader(
            //        new InputStreamReader(mSocket.getInputStream())
            //);

            mBufferedWriter = new BufferedWriter(
                    new OutputStreamWriter((mSocket.getOutputStream()))
            );

            if (lightController[0].getSwitchStatus())
            {
                mBufferedWriter.write("GET /AN HTTP/1.1\r\n\r\n");
            }
            else
            {
                mBufferedWriter.write("GET /AUS HTTP/1.1\r\n\r\n");
            }

            mBufferedWriter.flush();
            mSocket.close();
            publishProgress(10, 10);

        }
        catch (UnknownHostException e)
        {
            Log.d(lightController[0].getHost() + "-UhE", e.getMessage());
            lightController[0].setStatus(-1);
            return lightController[0];
        }
        catch (IOException e)
        {
            Log.d(lightController[0].getHost() + "-IoE", e.getMessage());
            lightController[0].setStatus(-2);
            return lightController[0];
        }
        catch (NullPointerException e)
        {
            Log.d(lightController[0].getHost() + "-NpE", e.getMessage());
            lightController[0].setStatus(-3);
            return lightController[0];
        }
        lightController[0].setStatus(1);
        return lightController[0];
    }

    @Override
    protected void onPostExecute(LightController result)
    {
        Log.d("onPostExecute", result.toString());
        if (result.getStatus() <= 0)
        {
            Toast.makeText(result.getContext(), "LightController \"" + result.getHost() + "\" ist nicht erreichbar!", Toast.LENGTH_SHORT).show();
        }
    }
}