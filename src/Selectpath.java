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
		
		JMenu game = new JMenu("游戏");	//目录1：游戏
		JMenuItem newgame = game.add("新游戏");
		JMenuItem pause = game.add("暂停");
		JMenuItem goon = game.add("继续");
		JMenuItem exit = game.add("退出");
		JMenu help = new JMenu("帮助");	//目录2：帮助
		JMenuItem about = help.add("关于");
		menu.add(game);
		menu.add(help);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(440, 550);
		frame.setTitle("吃豆豆小游戏");
		frame.setVisible(true);
		frame.setResizable(false);
		
	}
}

//吃豆豆板块
class Path extends JPanel implements KeyListener
{
	//int state;	//游戏状态：0继续，1失败，2胜利
	int state=0;	//标识此时是否有操作
	int[][] map = new int[44][55]; //地图(440*500,10*10一格)：0为路径（无豆豆），1为路径（有豆豆），2为墙
	int i,j;
	int x,y;
	Player player1 = new Player();

	public Path()
	{
		newmap();
		//Player player1 = new Player();
		player1.setx(5);	//player初始化地点
		player1.sety(10);
		Writepath();
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
	}
	
	//初始化地图
	public void newmap()
  {
		for (i = 0; i < 44; i++)
      {
			for (j = 0; j < 50; j++)	//txt中记录路径
          {
				map[i][j] = 2;

          }
		}
	}
	
	//左
	public void left()
	{
		x=player1.getx();
		y=player1.gety();
		player1.setx(x-1);
		Writepath();
		System.out.println("x:"+x+"	y:"+y);
		repaint();
	}
	
	//右
	public void right()
	{
		x=player1.getx();
		y=player1.gety();
		player1.setx(x+1);
		Writepath();
		System.out.println("x:"+x+"	y:"+y);
		repaint();
	}
	
	//上
	public void up()
	{
		x=player1.getx();
		y=player1.gety();
		player1.sety(y-1);
		Writepath();
		System.out.println("x:"+x+"	y:"+y);
		repaint();
	}
	
	//下
	public void down()
	{
		x=player1.getx();
		y=player1.gety();
		player1.sety(y+1);
		Writepath();
		System.out.println("x:"+x+"	y:"+y);
		repaint();
			}

	
	//绘制
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//Graphics2D g2 = (Graphics2D)g;
		//Image playerimage = new ImageIcon("Player.JPG").getImage();
		//Toolkit tool = this.getToolkit();
		Image image1 = Toolkit.getDefaultToolkit().getImage("Player.jpg");
		//画player
		x=player1.getx();
		y=player1.gety(); 
		
		g.fillRect(x*10, y*10, 10, 10);
		//g.drawImage(image1, x, y, 10                                , 10, this);
		//g2.drawImage(playerimage, x, y, this);
		
	}
	
	// 键盘监听
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
	// 无用
	public void keyReleased(KeyEvent e)
	    {

		}
	// 无用
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