package com.formation.image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MonActivite extends Activity {
    
	ImageView image;
	TextView page;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        image = (ImageView)findViewById(R.id.ivImage);
        page = (TextView)findViewById(R.id.tvContent);
        
        downloadImage();
        downloadPage();
    }
    
    private void downloadPage() {
    	try {
			URL urlPage = new URL("http://www.google.fr");
			HttpURLConnection connection = (HttpURLConnection) urlPage.openConnection();
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			StringBuffer stringBuffer = new StringBuffer();
			String ligne;
			
			while((ligne = bufferedReader.readLine()) != null) {
				stringBuffer.append(ligne);
			}
			
			page.setText(stringBuffer.toString());
			
			connection.disconnect();
			bufferedReader.close();
			inputStream.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void downloadImage() {
    	Bitmap bitmap = null;
    	
    	try {
			URL urlImage = new URL("http://www.google.fr/intl/en_com/images/srpr/logo1w.png");
			HttpURLConnection connection = (HttpURLConnection) urlImage.openConnection();
			InputStream inputStream = connection.getInputStream();
			
			bitmap = BitmapFactory.decodeStream(inputStream);
			
			image.setImageBitmap(bitmap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   	
    	
    }
}