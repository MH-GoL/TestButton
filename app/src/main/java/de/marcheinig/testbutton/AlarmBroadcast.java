package de.marcheinig.testbutton;

import de.marcheinig.testbutton.MainActivity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import java.text.*;
import java.util.*;
import android.util.*;

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
		
		MainActivity mainAct = new MainActivity();
		mainAct.sendLight(true, "lichtschalter.fritz.box");
		
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

		Toast.makeText(context,formatter.format(timeOff9.getTime()),Toast.LENGTH_SHORT).show();
        am.set(AlarmManager.RTC_WAKEUP, timeOff9.getTimeInMillis(), pi);
    }
}
