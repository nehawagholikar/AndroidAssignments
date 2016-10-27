package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper
{

	private static final int DATABASE_VERSION=1;

	private static final String DATABASE_NAME="JSON_Sample";

	private static final String TABLE_CONTACTS="json_contact";

	private static final String CONTACT_ID = "CONTACT_ID";
	private static final String FIRST_NAME = "FIRST_NAME";
	private static final String LAST_NAME = "LAST_NAME";
	private static final String AGE = "AGE";
	private static final String ADDRESS = "ADDRESS";
	private static final String CITY = "CITY";
	private static final String STATE = "STATE";
	private static final String POSTALCODE = "POSTALCODE";
	private static final String TYPE = "TYPE";
	private static final String NUMBER = "NUMBER";
	

	public DataBase(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db)              
	{
		db.execSQL("drop table if exists "+TABLE_CONTACTS);
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE " + TABLE_CONTACTS + "(" +CONTACT_ID  + " TEXT,"+ FIRST_NAME +  " TEXT," + LAST_NAME + " TEXT," + AGE + " TEXT,"
		+ADDRESS  +" TEXT," + CITY + " TEXT," + STATE + " TEXT," + POSTALCODE + " TEXT," + TYPE + " TEXT," + NUMBER + " TEXT" +")";
		db.execSQL(sql);
	}       

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists "+TABLE_CONTACTS);
		onCreate(db);

	}


	public void addContacts(String contact_id,String firstname, String lastname, String age, String streetadd,String city,String state,String postalcode,String type,String number)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		
		ContentValues  values= new ContentValues();
		values.put(CONTACT_ID,contact_id);
		values.put(FIRST_NAME,firstname);
		values.put(LAST_NAME,lastname);
		values.put(AGE,age);
		values.put(ADDRESS,streetadd);
		values.put(CITY,city);
		values.put(STATE,state);
		values.put(POSTALCODE,postalcode);                            
		values.put(TYPE, type);
		values.put(NUMBER,number);
		
		db.insert(TABLE_CONTACTS, null, values);

	}
	
	public Cursor getContacts()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		
		String sql = "SELECT rowid _id,* FROM "+TABLE_CONTACTS;
		Cursor cursor=db.rawQuery(sql, null);
		/*if(cursor.moveToFirst())
		{
			do
			{
				System.out.println(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5)+" "+cursor.getString(6)+" "+cursor.getString(7)+" "+cursor.getString(8)+" "+cursor.getString(9));
			}while(cursor.moveToNext());
		}*/
		
		return cursor;
	}

}
