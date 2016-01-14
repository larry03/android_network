import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

	// ���屣�����е�Socket����ͻ��˽������ӵõ�һ��Socket
	public static List<Socket> socketList = new ArrayList<Socket>();

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(8888);

		while (true) {
			System.out.println("start listening port 8888");
			Socket socket = server.accept();
			System.out.println("connect succeed.");
			socketList.add(socket);
			//ÿ���ͻ�������֮������һ��ServerThread�߳�Ϊ�ÿͻ��˷���    
			 new Thread(new MyServerRunnable(socket)).start();   


		}
	}

	public static class MyServerRunnable implements Runnable {
		// ���嵱ǰ�̴߳����Socket
		Socket socket = null;
		// ���߳��������Socket����Ӧ��������
		BufferedReader bufferedReader = null;

		public MyServerRunnable(Socket socket) {
			this.socket = socket;
			try {
				// ���������˵������������ݷ����Buffer��
				bufferedReader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Override
		public void run() {
			String content = null;
			// ����ѭ�����ϵĴ�Socket�ж�ȡ�ͻ��˷��͹���������
			while ((content = readFromClient()) != null) {
				// ����socketList�е�ÿһ��Socket������ȡ��������ÿ��Socket����һ��
				for (Socket socket : MyServer.socketList) {
					OutputStream outputStream;
					try {
						outputStream = socket.getOutputStream();
						outputStream.write(("Server: " + content + "\n").getBytes("utf-8"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}

		// �����ȡ�ͻ��˵���Ϣ
		public String readFromClient() {
			try {
				return bufferedReader.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}

}
