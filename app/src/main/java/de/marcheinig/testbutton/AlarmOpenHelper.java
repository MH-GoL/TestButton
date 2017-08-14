package de.marcheinig.testbutton;

import android.content.*;
import android.database.sqlite.*;

 

public class AlarmOpenHelper extends SQLiteOpenHelper
{
	private static final String ID = "id";
	private static final String TABLE_NAME = "alarmList";
	private static final String ALARMLIST_TIME = "timeMillis";
	private static final String ALARMLIST_NAME = "name";
	private static final String ALARMLIST_ALARMACTION_ID = "actionId";
	private static final String ALARMLIST_REPEAT = "repeat";
	
	private static final String ALARMLIST_CREATE_TABLE = "CREATE TABLE " +
	TABLE_NAME + "("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ALARMLIST_TIME + " INTEGER, " + ALARMLIST_REPEAT + " INTEGER, " + ALARMLIST_NAME + " STRING, " + ALARMLIST_ALARMACTION_ID + " INTEGER);";
	private static final String ALARMLIST_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(ALARMLIST_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db1, int db2, int db3)
	{
		// TODO: Implement this method
	}
	
	AlarmOpenHelper (Context context)
	{
		super(context, "", null, 1);
	}
	
	public void deleteTable()
	{
		SQLiteDatabase db = getReadableDatabase();
		db.execSQL(ALARMLIST_DROP_TABLE);
	}
	
	public boolean insertDataAlarmlist(int timeMillis, int repeat, String name, int actionId)
	{
		return true;
	}
}
