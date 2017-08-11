package de.marcheinig.testbutton;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmBroadcast extends BroadcastReceiver {

	final public static String ONE_TIME = "onetime";
	@Override
	public void onReceive(Context context, Intent intent) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK|PowerManager.ACQUIRE_CAUSES_WAKEUP, "YOUR TAG");
		//Acquire the lock
		wl.acquire();

		//You can do the processing here update the widget/remote views.
		StringBuilder msgStr = new StringBuilder();
		msgStr.append("Fucker: ");

		Format formatter = new SimpleDateFormat("HH:mm:ss");
		msgStr.append(formatter.format(new Date()));
		
		//sendLight(context, true, "lichtschalter.fritz.box");
		sendColor(context,1024,0,1024,"ledregal.fritz.box");
		sendColor(context,1024,0,1024,"ledtisch.fritz.box");
		sendColor(context,1024,0,1024,"ledbett.fritz.box");
		Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();

		//Release the lock
		wl.release();
	}

	public void SetAlarm(Context context)
    {
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmBroadcast.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        //After after 30 seconds
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 , pi); 
    }

    public void CancelAlarm(Context context)
    {
        Intent intent = new Intent(context, AlarmBroadcast.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    public void setOnetimeTimer(Context context, int hour, int minute){
    	AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmBroadcast.class);
        intent.putExtra(ONE_TIME, Boolean.TRUE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

		Calendar timeOff9 = Calendar.getInstance();

		timeOff9.setTimeInMillis(System.currentTimeMillis());
		timeOff9.set(Calendar.HOUR_OF_DAY, hour);
		timeOff9.set(Calendar.MINUTE, minute);
		timeOff9.set(Calendar.SECOND, 0);
		
		Format formatter = new SimpleDateFormat("HH:mm:ss");
		
		if(timeOff9.getTimeInMillis() < System.currentTimeMillis())
		{
			Toast.makeText(context,"@string/button_alleaus_text" + formatter.format(timeOff9.getTime()), Toast.LENGTH_SHORT).show();
			timeOff9.setTimeInMillis(timeOff9.getTimeInMillis()+24*60*60*1000);
		} else {
			Toast.makeText(context,"Wecker für Heute: " + formatter.format(timeOff9.getTime()), Toast.LENGTH_SHORT).show();
		}
			
        am.set(AlarmManager.RTC_WAKEUP, timeOff9.getTimeInMillis(), pi);
    }

	public boolean sendLight(Context context, boolean switchStatus, String server)
	{
		LightController lightController = new LightController(server, context, 80, switchStatus);
		LightSocket lightSocket = new LightSocket();
		lightSocket.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, lightController);
		if (lightController.getStatus() < 0)
		{
			Toast.makeText(context, "False Light", Toast.LENGTH_SHORT);
			return false;
		}
		else
		{
			Toast.makeText(context, "True Light", Toast.LENGTH_SHORT);
			return true;
		}
	}

	public boolean sendColor(Context context, int colorR, int colorG, int colorB, String server)
	{
		LedController ledController = new LedController(server, context, 80, colorR, colorG, colorB);
		LedSocket ledSocket = new LedSocket();
		ledSocket.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ledController);
		return true;
	}
}
