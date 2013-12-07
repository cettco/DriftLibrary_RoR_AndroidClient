package com.zhang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cettco on 12/7/13.
 */
public class RegisterActivity   extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }
    public void onClick(View view)
    {
        EditText nameText = (EditText) findViewById(R.id.rName);
        EditText emailText = (EditText) findViewById(R.id.rEmail);
        EditText passwdText = (EditText) findViewById(R.id.rPasswd);
        EditText rpasswdText = (EditText) findViewById(R.id.rCPasswd);
        String result = postData(nameText.getText().toString(),emailText.getText().toString(),passwdText.getText().toString(),
                rpasswdText.getText().toString());
        if(result.indexOf("donate")==-1)
        {
            Toast toast=Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            Toast toast=Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public String postData(String name,String email,String passwd,String cpasswd) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://tzwm.oicp.net:3000/users");
        String result="";
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
            nameValuePairs.add(new BasicNameValuePair("user[name]", name));
            nameValuePairs.add(new BasicNameValuePair("user[email]", email));
            nameValuePairs.add(new BasicNameValuePair("user[password]", passwd));
            nameValuePairs.add(new BasicNameValuePair("user[password_confirmation]",cpasswd));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            result = response.toString();
            return result;
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return result;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
