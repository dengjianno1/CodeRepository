package UDP传输;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo4_GUI extends Frame{
	private Button send;
	private Button log;
	private Button clear;
	private Button shake;
	private TextArea sendText;
	private TextArea viewText;
	private TextField textField;
	private DatagramSocket socket;
	private BufferedWriter bw;
	public Demo4_GUI() {
		init();
		southPanel();
		centerPanel();
		event();
		this.setVisible(true);
	}
	public void event() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				socket.close();
				try {
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					send();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					logFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				viewText.setText("");
			}
		});
		shake.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					send(new byte[]{-1}, textField.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		sendText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER && e.isControlDown()) {
					try {
						send();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	private void shake() {
		int x=this.getLocation().x;
		int y=this.getLocation().y;
		for(int i=0;i<3;i++){
			try {
				this.setLocation(x+2, y+20);
				Thread.sleep(50);
				this.setLocation(x+20, y-20);
				Thread.sleep(50);
				this.setLocation(x-20, y+20);
				Thread.sleep(50);
				this.setLocation(x-20, y-20);
				Thread.sleep(50);
				this.setLocation(x, y);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	private void logFile() throws IOException {
		bw.flush();
		FileInputStream fis=new FileInputStream("config.txt");
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		int len;
		byte[] arr=new byte[8192];
		while ((len=fis.read(arr))!=-1) {
			baos.write(arr, 0, len);
		}
		String string =baos.toString();
		viewText.setText(string);
		fis.close();
	}
	private void send() throws IOException {
		String message=sendText.getText();
		String ip=textField.getText();
		ip=ip.trim().length()==0?"255.255.255.255":ip;
		
		send(message.getBytes(), ip);
		
		String time=getCurrentTime();
		String str = time+" 我对:"+(ip.equals("255.255.255.255")?"所有人":ip)+"说:\r\n"+message+"\r\n";
		viewText.append(str);
		bw.write(str);
		sendText.setText("");
	}
	private void send(byte[] arr,String ip) throws IOException{
		DatagramPacket packet=new DatagramPacket(arr,
				arr.length, InetAddress.getByName(ip), 9999);
		socket.send(packet);
	}
	private String getCurrentTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		return sdf.format(date);
	}
	public void centerPanel() {
		Panel center=new Panel();
		center.setBounds(1, 3, 24, 4);
		viewText = new TextArea();
		sendText = new TextArea(5,1);
		center.setLayout(new BorderLayout());
		center.add(sendText, BorderLayout.SOUTH);
		center.add(viewText, BorderLayout.CENTER);
		viewText.setEditable(false);
		viewText.setBackground(Color.WHITE);
		sendText.setFont(new Font("xxx", Font.PLAIN, 15));
		viewText.setFont(new Font("xxx", Font.PLAIN, 15));
		this.add(center,BorderLayout.CENTER);
	}
	public void southPanel() {
		Panel south=new Panel();
		textField = new TextField(15);
		textField.setText("127.0.0.1");
		send = new Button("发送");
		log = new Button("记录");
		clear = new Button("清屏");
		shake = new Button("震动");
		south.add(textField);
		south.add(send);
		south.add(log);
		south.add(clear);
		south.add(shake);
		this.add(south,BorderLayout.SOUTH);
	}
	private void init() {
		this.setLocation(500, 50);
		this.setSize(400, 600);
		new Receive().start();
		try {
			socket = new DatagramSocket();
			bw=new BufferedWriter(new FileWriter("config.txt",true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private class Receive extends Thread{
		@Override
		public void run() {
			try {
				DatagramSocket socket=new DatagramSocket(9999);
				DatagramPacket packet=new DatagramPacket(new byte[8192], 8192);
				while (true) {
					socket.receive(packet);
					byte[] arr = packet.getData();
					int len = packet.getLength();
					if (arr[0]==-1&&len==1) {
						shake();
						continue;
					}
					String message = new String(arr, 0, len);
					String ip = packet.getAddress().getHostAddress();
					String time = getCurrentTime();
					String str = time + " " + ip + "对我说:\r\n" + message + "\r\n";
					viewText.append(str);
					bw.write(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new Demo4_GUI();

	}

}
