import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.net.ServerSocket;
public class Client extends JFrame implements ActionListener {

	JPanel p1;
	JTextField t1;
	JButton b1;
	static JTextArea a1;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	boolean typing;
	Client() throws Exception
	{
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,450,70);
		add(p1);

		ImageIcon i1 = new ImageIcon("3.png");
       	Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 = new JLabel(i3);
		l1.setBounds(5,17,30,30);
		
		l1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me)
			{
				System.exit(0);
			}
		});
		p1.add(l1);

		ImageIcon i4 = new ImageIcon("2.png");
       	Image i5 = i4.getImage().getScaledInstance(50, 45, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel l2 = new JLabel(i6);
		l2.setBounds(40,5,60,60);
		p1.add(l2);

		ImageIcon i7 = new ImageIcon("video.png");
       	Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel l5 = new JLabel(i9);
		l5.setBounds(290,20,30,30);
		p1.add(l5);

		ImageIcon i10 = new ImageIcon("phone.png");
       	Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel l6 = new JLabel(i12);
		l6.setBounds(350,20,35,30);
		p1.add(l6);

		ImageIcon i13 = new ImageIcon("3icon.png");
       	Image i14 = i13.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel l7 = new JLabel(i15);
		l7.setBounds(410,20,13,25);
		p1.add(l7);

		JLabel l3 = new JLabel("Ramesh");
		l3.setFont(new Font("SAN_SERIF",Font.BOLD,15));
		l3.setForeground(Color.white);
		l3.setBounds(110, 15, 100, 18);
		p1.add(l3);

		JLabel l4 = new JLabel("Active Now");
		l4.setFont(new Font("SAN_SERIF",Font.PLAIN,10));
		l4.setForeground(Color.white);
		l4.setBounds(110, 35, 100, 20);
		p1.add(l4);

		Timer t = new Timer(1,new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				if(typing)
				{
					l4.setText("Active Now");
				}
			}
		});

		t.setInitialDelay(2000);

		a1 = new JTextArea();
		a1.setBounds(5,75,440,475);
		a1.setEditable(false);
		a1.setLineWrap(true);
		a1.setWrapStyleWord(true);
		a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		add(a1);

		t1 = new JTextField();
		t1.setBounds(5,560,350,30);
		t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		add(t1);

		t1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ae){
				l4.setText("typing...");
				t.stop();
				typing = true;
			}

			public void keyReleased(KeyEvent ae){
				typing = false;

				if(!t.isRunning())
				{
					t.start();
				}
			}
		});

		b1 = new JButton("Send");
		b1.setBounds(370,560,75,30);
		b1.setBackground(new Color(7,94,84));
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		b1.addActionListener(this);
		add(b1);

		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
        setSize(450,600);
        setLocation(900,200);
		setUndecorated(true);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae)
	{
		try{
		String out = t1.getText();
		a1.setText(a1.getText() +"\n\t\t\t" + out);
		dout.writeUTF(out);
		t1.setText("");}
		catch(Exception e){}
	}

	public static void main(String args[]) throws Exception
	{
		new Client().setVisible(true);

		String msg = "";
		try{
			s = new Socket("localhost",1234);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());

			msg = din.readUTF();
			a1.setText(a1.getText() +"\n" + msg);

		}catch(Exception e){}
	}

}
