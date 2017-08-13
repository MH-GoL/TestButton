package de.marcheinig.testbutton;

import android.app.Activity;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

//import android.widget.TextView;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;


public class MainActivity extends Activity
{
	private AlarmBroadcast alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		alarm = new AlarmBroadcast();

        final Button buttonRegal = (Button) findViewById(R.id.buttonRegal);
        final Button buttonTisch = (Button) findViewById(R.id.buttonTisch);
        final Button buttonBett = (Button) findViewById(R.id.buttonBett);
        final Button buttonAlle = (Button) findViewById(R.id.buttonAlle);
        final ToggleButton buttonDeckenlicht = (ToggleButton) findViewById(R.id.buttonDeckenlicht);
        final Button buttonAlleAus = (Button) findViewById(R.id.buttonAlleAus);
        final Button buttonSunrise = (Button) findViewById(R.id.buttonSunrise);

        //final TextView textView = (TextView) findViewById(R.id.textView);

        final SeekBar seekBarRed = (SeekBar) findViewById(R.id.seekBarRed);
        final SeekBar seekBarGreen = (SeekBar) findViewById(R.id.seekBarGreen);
        final SeekBar seekBarBlue = (SeekBar) findViewById(R.id.seekBarBlue);

        final RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        buttonRegal.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					Log.d("TEST_BUTTON", "Button pressed");
					sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledregal.fritz.box");
					buttonRegal.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
					buttonAlle.setBackgroundColor(Color.rgb(0, 0, 0));
				}
			});

        buttonTisch.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					Log.d("TEST_BUTTON", "Button pressed");
					sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledtisch.fritz.box");
					buttonTisch.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
					buttonAlle.setBackgroundColor(Color.rgb(0, 0, 0));
				}
			});

        buttonBett.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					Log.d("TEST_BUTTON", "Button pressed");
					sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledbett.fritz.box");
					buttonBett.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
					buttonAlle.setBackgroundColor(Color.rgb(0, 0, 0));
				}
			});

        buttonAlle.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledregal.fritz.box");
					sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledtisch.fritz.box");
					sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress(), "ledbett.fritz.box");

					buttonRegal.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
					buttonTisch.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
					buttonBett.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
					buttonAlle.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
				}
			});

        buttonDeckenlicht.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					Log.d("TEST_BUTTON", "Button pressed");

					if (buttonDeckenlicht.isChecked())
					{
						if (!sendLight(true, "lichtschalter.fritz.box")) buttonDeckenlicht.setChecked(false);
					}
					else
					{
						if (!sendLight(false, "lichtschalter.fritz.box")) buttonDeckenlicht.setChecked(true);
					}
				}
			});

        buttonAlleAus.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					sendColor(0, 0, 0, "ledregal.fritz.box");
					sendColor(0, 0, 0, "ledtisch.fritz.box");
					sendColor(0, 0, 0, "ledbett.fritz.box");
					sendLight(false, "lichtschalter.fritz.box");

					buttonRegal.setBackgroundColor(Color.rgb(0, 0, 0));
					buttonTisch.setBackgroundColor(Color.rgb(0, 0, 0));
					buttonBett.setBackgroundColor(Color.rgb(0, 0, 0));
					buttonAlle.setBackgroundColor(Color.rgb(0, 0, 0));
					buttonDeckenlicht.setChecked(false);
				}
			});

        buttonSunrise.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					Log.d("TEST_BUTTON", "Test-Button-gedrückt");
					sendColor(255, 255, 255, "ledtisch.fritz.box");
					Log.d("SUNRISE", Integer.toString(R.id.buttonSunrise));

				}
			});

        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
				{
					runOnUiThread(new Runnable() {
							@Override
							public void run()
							{
								mainLayout.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
								//sendColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress());
							}
						});
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar)
				{

				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar)
				{

				}
			});

		seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
				{
					runOnUiThread(new Runnable() {
							@Override
							public void run()
							{
								mainLayout.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
							}
						});
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar)
				{

				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar)
				{

				}
			});

        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
				{
					runOnUiThread(new Runnable() {
							@Override
							public void run()
							{
								mainLayout.setBackgroundColor(Color.rgb(seekBarRed.getProgress() / 4, seekBarGreen.getProgress() / 4, seekBarBlue.getProgress() / 4));
							}
						});
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar)
				{

				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar)
				{

				}
			});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
	{
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
	{
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_alarm)
		{
			TimePickerDialog Tp = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute)
					{
						alarm.setOnetimeTimer(getApplicationContext(), hourOfDay, minute);
						Log.d("Time", hourOfDay + ":" + minute);
					}
				}, 4, 30, true);
			Tp.show();
			return true;
        }
			else if (id == R.id.action_alarm_4_00_led)
		{
			alarm.setOnetimeTimer(getApplicationContext(), 4, 0);
		}
			else if  (id == R.id.action_alarm_4_00_light)
		{
			alarm.setOnetimeTimer(getApplicationContext(), 4, 0);
		}

        return super.onOptionsItemSelected(item);
    }

    public boolean sendColor(int colorR, int colorG, int colorB, String server)
	{
        LedController ledController = new LedController(server, getApplicationContext(), 80, colorR, colorG, colorB);
        LedSocket ledTischSocket = new LedSocket();
        ledTischSocket.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ledController);
		return true;
    }

    public boolean sendLight(boolean switchStatus, String server)
    {
        LightController lightController = new LightController(server, getApplicationContext(), 80, switchStatus);
        LightSocket lightSocket = new LightSocket();
        lightSocket.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, lightController);
        if (lightController.getStatus() < 0)
		{
			Toast.makeText(getApplicationContext(), "False Light", Toast.LENGTH_SHORT);
			return false;
		}
		else
		{
			Toast.makeText(getApplicationContext(), "True Light", Toast.LENGTH_SHORT);
			return true;
		}
    }
}
