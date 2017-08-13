package de.marcheinig.testbutton;

import android.content.*;
import android.database.sqlite.*;

 

public class AlarmOpenHelper extends SQLiteOpenHelper
{
	private static final String _ID = "_id";
	private static final String TABLE_NAME = "alarmList";
	private static final String ALARMLIST_TIME = "timeMillis";
	private static final String ALARMLIST_NAME = "";
	private static final String ALARMLIST_ALARMACTION_ID = "";
	private static final String ALARMLIST_REPEAT = "";
	
	private static final String CREATE_TABLE = "CREATE TABLE " +
	TABLE_NAME + "("+ _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ALARMLIST_TIME + " INTEGER, " + ALARMLIST_REPEAT + " INTEGER, " + ALARMLIST_NAME + " STRING, " + ALARMLIST_ALARMACTION_ID + " INTEGER);";
	
	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		p1.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{
		// TODO: Implement this method
	}
	
	AlarmOpenHelper (Context context)
	{
		super(context, "", null, 1);
	}
}
