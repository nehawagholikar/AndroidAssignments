

package com.json;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.database.DataBase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;


public class MainActivity extends Activity 
{
	Button ok;
	String myjsonstring;
	DataBase db;
	String contact_id,fname,lname,age,sAddr,city,state,postal,type,num;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

       db=new DataBase(MainActivity.this);

        ok = (Button) findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() 
        {

                        

                        @Override

                        public void onClick(View v) 
                        {
                        	
                        	StringBuffer sb = new StringBuffer();
                    		BufferedReader br = null;
                    		try
                    		{
                    			br = new BufferedReader(new InputStreamReader(getAssets().open(
                    					"contacts.json")));
                    			String temp;
                    			while ((temp = br.readLine()) != null)
                    				sb.append(temp);
                    		} 
                    		catch (IOException e)
                    		{
                    			e.printStackTrace();
                    		} 
                    		finally 
                    		{
                    			try 
                    			{
                    				br.close(); // stop reading
                    			} 
                    			catch (IOException e) 
                    			{
                    				e.printStackTrace();
                    			}
                    		}

                    		myjsonstring = sb.toString();

                    		// Try to parse JSON
                    		try 
                    		{
                    			// Creating JSONObject from String
                    			JSONObject jsonObjMain = new JSONObject(myjsonstring);

                    			// Creating JSONArray from JSONObject
                    			JSONArray jsonArray1 = jsonObjMain.getJSONArray("contact");


                    			// JSONArray has four JSONObject
                    			//int j=0;
                    			for (int i = 0; i < jsonArray1.length(); i++) 
                    			{

                    				// Creating JSONObject from JSONArray
                    				JSONObject jsonObj = jsonArray1.getJSONObject(i);

                    				// Getting data from individual JSONObject
                    				contact_id=jsonObj.getString("contactId");
                    				fname = jsonObj.getString("firstName");
                    				lname = jsonObj.getString("lastName");
                    				age = jsonObj.getString("age");

                    				JSONObject jsonObj1 = jsonObj.getJSONObject("address");




                    				sAddr = jsonObj1.getString("streetAddress");
                    				city = jsonObj1.getString("city");
                    				state = jsonObj1.getString("state");
                    				postal = jsonObj1.getString("postalCode");

                    				JSONObject jsonObj2 = jsonObj.getJSONObject("phoneNumber");
                    				type = jsonObj2.getString("type");
                    				num = jsonObj2.getString("number");

                    				
                 				    db.addContacts(contact_id,fname, lname, age, sAddr,city,state,postal,type,num);
                    				//db.getContacts();
                  




                    			}




                                Intent homepage = new Intent(MainActivity.this, SecondActivity.class);

                                startActivity(homepage);
                    		}
                    		catch (JSONException e) {
                    			// TODO Auto-generated catch block
                    			e.printStackTrace();
                    		}
                        }

                });

	}    

	
}




