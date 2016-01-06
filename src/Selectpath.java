import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import javax.swing.*;

public class Selectpath extends JFrame
{
	public Selectpath()
    {
		Path a = new Path();
		addKeyListener(a);
		add(a);
    }
	public static void main(String[] args)
    {
		Selectpath frame = new Selectpath();
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		
		JMenu game = new JMenu("��Ϸ");	//Ŀ¼1����Ϸ
		JMenuItem newgame = game.add("����Ϸ");
		JMenuItem pause = game.add("��ͣ");
		JMenuItem goon = game.add("����");
		JMenuItem exit = game.add("�˳�");
		JMenu help = new JMenu("����");	//Ŀ¼2������
		JMenuItem about = help.add("����");
		menu.add(game);
		menu.add(help);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(440, 550);
		frame.setTitle("�Զ���С��Ϸ");
		frame.setVisible(true);
		frame.setResizable(false);
		
	}
}

//�Զ������
class Path extends JPanel implements KeyListener
{
	//int state;	//��Ϸ״̬��0������1ʧ�ܣ�2ʤ��
	int state=0;	//��ʶ��ʱ�Ƿ��в���
	int[][] map = new int[44][55]; //��ͼ(440*500,10*10һ��)��0Ϊ·�����޶�������1Ϊ·�����ж�������2Ϊǽ
	int i,j;
	int x,y;
	Player player1 = new Player();

	public Path()
	{
		newmap();
		//Player player1 = new Player();
		player1.setx(5);	//player��ʼ���ص�
		player1.sety(10);
		Writepath();
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
	}
	
	//��ʼ����ͼ
	public void newmap()
  {
		for (i = 0; i < 44; i++)
      {
			for (j = 0; j < 50; j++)	//txt�м�¼·��
          {
				map[i][j] = 2;

          }
		}
	}
	
	//��
	public void left()
	{
		x=player1.getx();
		y=player1.gety();
		player1.setx(x-1);
		Writepath();
		System.out.println("x:"+x+"	y:"+y);
		repaint();
	}
	
	//��
	public void right()
	{
		x=player1.getx();
		y=player1.gety();
		player1.setx(x+1);
		Writepath();
		System.out.println("x:"+x+"	y:"+y);
		repaint();
	}
	
	//��
	public void up()
	{
		x=player1.getx();
		y=player1.gety();
		player1.sety(y-1);
		Writepath();
		System.out.println("x:"+x+"	y:"+y);
		repaint();
	}
	
	//��
	public void down()
	{
		x=player1.getx();
		y=player1.gety();
		player1.sety(y+1);
		Writepath();
		System.out.println("x:"+x+"	y:"+y);
		repaint();
			}

	
	//����
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//Graphics2D g2 = (Graphics2D)g;
		//Image playerimage = new ImageIcon("Player.JPG").getImage();
		//Toolkit tool = this.getToolkit();
		Image image1 = Toolkit.getDefaultToolkit().getImage("Player.jpg");
		//��player
		x=player1.getx();
		y=player1.gety(); 
		
		g.fillRect(x*10, y*10, 10, 10);
		//g.drawImage(image1, x, y, 10                                , 10, this);
		//g2.drawImage(playerimage, x, y, this);
		
	}
	
	// ���̼���
	public void keyPressed(KeyEvent e)
	    {
				switch (e.getKeyCode())
	        {
				case KeyEvent.VK_DOWN:
					down();
					break;
				case KeyEvent.VK_UP:
					up();
					break;
				case KeyEvent.VK_RIGHT:
					right();
					break;
				case KeyEvent.VK_LEFT:
	 				left();
					break;
	        }
		}
	// ����
	public void keyReleased(KeyEvent e)
	    {

		}
	// ����
	public void keyTyped(KeyEvent e)
	    {

		}	
	
	public void Writepath()
	{
		x=player1.getx();
		y=player1.gety();
		
		try{
			FileWriter fileWriter=new FileWriter("E:/eclipsework/Pacman/file/Path.txt",true);
			fileWriter.write(String.valueOf(x)+","+String.valueOf(y)+";");
			fileWriter.flush();
			fileWriter.close();			
		}catch (IOException e)
		{
			e.printStackTrace(); 
		}

	}
	
	class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			repaint();
		}
	}
	
}