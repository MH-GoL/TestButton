package de.marcheinig.testbutton;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends Activity {

    private Socket mSocket;
    private volatile BufferedReader mBufferedReader;
    private BufferedWriter mBufferedWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonRegal = (Button) findViewById(R.id.buttonRegal);
        final Button buttonTisch = (Button) findViewById(R.id.buttonTisch);
        final Button buttonBett = (Button) findViewById(R.id.buttonBett);
        final Button buttonAlle = (Button) findViewById(R.id.buttonAlle);
        final Button buttonSunrise = (Button) findViewById(R.id.buttonSunrise);

        final TextView textView = (TextView) findViewById(R.id.textView);

        final SeekBar seekBarRed = (SeekBar) findViewById(R.id.seekBarRed);
        final SeekBar seekBarGreen = (SeekBar) findViewById(R.id.seekBarGreen);
        final SeekBar seekBarBlue = (SeekBar) findViewById(R.id.seekBarBlue);

        final RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        buttonRegal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST_BUTTON", "Button pressed");

                Runnable rTcp = new Runnable() {
                    @Override
                    public void run() {
                        Log.d("TEST_RUNNABLE", "started");
                        sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledregal.fritz.box");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mainLayout.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
                            }
                        });
                        //return;
                    }
                };

                Thread tTcp = new Thread(rTcp);
                tTcp.start();
                Log.d("TEST_THREAD", "started");

                textView.setText("Regal an");
            }
        });

        buttonTisch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST_BUTTON", "Button pressed");

                Runnable rTcp = new Runnable() {
                    @Override
                    public void run() {
                        Log.d("TEST_RUNNABLE", "started");
                        sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledtisch.fritz.box");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mainLayout.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
                            }
                        });
                    }
                };

                Thread tTcp = new Thread(rTcp);
                tTcp.start();
                Log.d("TEST_THREAD", "started");

                textView.setText("Tisch an");
            }
        });

        buttonBett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST_BUTTON", "Button pressed");

                Runnable rTcp = new Runnable() {
                    @Override
                    public void run() {
                        Log.d("TEST_RUNNABLE", "started");
                        sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledbett.fritz.box");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mainLayout.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
                            }
                        });
                    }
                };

                Thread tTcp = new Thread(rTcp);
                tTcp.start();
                Log.d("TEST_THREAD", "started");

                textView.setText("Bett an");
            }
        });

        buttonAlle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledregal.fritz.box");
                sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledtisch.fritz.box");
                sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledbett.fritz.box");

                textView.setText("Alle an");
            }
        });

        buttonSunrise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST_BUTTON", "Test-Button-gedrückt");

                sendColor(255, 255, 255, "ledtisch.fritz.box");

                textView.setText("Test");
            }
        });

        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainLayout.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
                        //sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress());
                    }
                });
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainLayout.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
                    }
                });
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainLayout.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
                    }
                });
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean sendColor(int colorR, int colorG, int colorB, String server) {
        LedController ledController = new LedController(server, 80, colorR, colorG, colorB);
        int status = 0;

        LedSocket ledTischSocket = new LedSocket();
        ledTischSocket.execute(ledController);

        return true;
    }

    public boolean sendSunrise(String Server) {
        try {
            mSocket = new Socket(Server, 80);

            if (mSocket != null) {
                mBufferedReader = new BufferedReader(
                        new InputStreamReader(mSocket.getInputStream())
                );

                mBufferedWriter = new BufferedWriter(
                        new OutputStreamWriter((mSocket.getOutputStream()))
                );
                mBufferedWriter.write("GET /sunrise HTTP/1.1\r\n\r\n");
                mBufferedWriter.flush();
                mSocket.close();
                mSocket = null;
            }
        } catch (UnknownHostException e) {
            Log.d("TCP", e.getMessage());
            mSocket = null;
            return false;
        } catch (IOException e) {
            Log.d("TCP", e.getMessage());
            mSocket = null;
            return false;
        }
        return true;
    }

    private static class ConnectionParam {
        private String host;
        private int port;

        ConnectionParam(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public String getHost() {
            return host;
        }

        public int getPort() {
            return port;
        }
    }

    private static class LedController {
        ConnectionParam connection;

        private int rColor;
        private int gColor;
        private int bColor;


        private int status;

        LedController(String host, int port, int r, int g, int b) {
            this.connection = new ConnectionParam(host, port);
            this.rColor = r;
            this.gColor = g;
            this.bColor = b;

        }

        public int[] getRGB() {
            int[] color = {this.rColor, this.gColor, this.bColor};
            return color;
        }

        public int getRedValue() {
            return this.rColor;
        }

        public int getGreenValue() {
            return this.gColor;
        }

        public int getBlueValue() {
            return this.bColor;
        }

        public String getHost() {
            return this.connection.getHost();
        }

        public int getPort() {
            return this.connection.getPort();
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public class LedSocket extends AsyncTask<LedController, Integer, LedController> {
        private String host;
        private int port;

        private int rColor;
        private int gColor;
        private int bColor;

        @Override
        protected void onProgressUpdate(Integer... progress) {

            //Toast.makeText(getApplicationContext(), "Farbe wird geändert", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected LedController doInBackground(LedController... ledController) {

            Log.d("doInBackground", "gestartet");

            try {
                mSocket = new Socket(ledController[0].getHost(), 80);

                if (mSocket != null) {
                    mBufferedReader = new BufferedReader(
                            new InputStreamReader(mSocket.getInputStream())
                    );

                    mBufferedWriter = new BufferedWriter(
                            new OutputStreamWriter((mSocket.getOutputStream()))
                    );
                    mBufferedWriter.write("GET /rgb?r=" + ledController[0].getRedValue() + "&g=" + ledController[0].getGreenValue() + "&b=" + ledController[0].getBlueValue() + " HTTP/1.1\r\n\r\n");
                    mBufferedWriter.flush();
                    mSocket.close();
                    mSocket = null;
                    publishProgress(10, 10);
                }
            } catch (UnknownHostException e) {
                Log.d("TCP", e.getMessage());
                mSocket = null;
                ledController[0].setStatus(-1);
                return ledController[0];
            } catch (IOException e) {
                Log.d("TCP", e.getMessage());
                mSocket = null;
                ledController[0].setStatus(-2);
                return ledController[0];
            }
            ledController[0].setStatus(1);
            return ledController[0];
        }

        @Override
        protected void onPostExecute(LedController result) {
            Log.d("onPostExecute", result.toString());
            if (result.getStatus() <= 0)
                Toast.makeText(getApplicationContext(), "Controller \"" + result.getHost() + "\" ist nicht erreichbar!", Toast.LENGTH_SHORT).show();
        }
    }
}
