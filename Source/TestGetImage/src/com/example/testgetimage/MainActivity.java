package com.example.testgetimage;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Bitmap bitmap;
	private ImageView show;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				show.setImageBitmap(bitmap);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (ImageView) this.findViewById(R.id.imageView);

		new Thread() {
			 
			@Override  
			public void run() {
			    super.run();  
				try {
					URL url = new URL(
							"http://a.hiphotos.baidu.com/image/h%3D220/sign=bd9eb2ea07087bf462ec50ebc2d2575e/d439b6003af33a87495b8dbbc35c10385343b559.jpg");
					InputStream inputStream = url.openStream();
					bitmap = BitmapFactory.decodeStream(inputStream);
					handler.sendEmptyMessage(1);
					inputStream.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

			};

		}.start();
	}

}
