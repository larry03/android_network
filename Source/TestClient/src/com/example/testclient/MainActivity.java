package com.example.testclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Handler handler;
	// �����������ͨ�ŵ����߳�
	ClientThread clientThread;
	TextView show;
	Button send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		show = (TextView) this.findViewById(R.id.show);
		send = (Button) this.findViewById(R.id.send);

		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					// ���û����°�ť֮�󣬽��û���������ݷ�װ��Message
					// Ȼ���͸����߳�Handler
					Message msg = new Message();
					msg.what = 0x345;
					msg.obj = "Android ������--Socketͨ�ű��";
					clientThread.revHandler.sendMessage(msg);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// �����Ϣ�������߳�
				if (msg.what == 0x123) {
					// ����ȡ������׷����ʾ���ı�����
					show.append("\n" + msg.obj.toString());
					show.setTextSize(50);
				}
			}
		};
		clientThread = new ClientThread(handler);
		// �ͻ�������ClientThread�̴߳����������ӡ���ȡ���Է�����������
		new Thread(clientThread).start();

	}


}
