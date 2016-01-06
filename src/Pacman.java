
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import javax.swing.*;
import javax.swing.Timer;

public class Pacman extends JFrame {
	public Pacman() {
		Pacmanblok a = new Pacmanblok();
		addKeyListener(a);
		add(a);
	}

	public static void main(String[] args) {
		Pacman frame = new Pacman();
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		JMenu game = new JMenu("游戏"); // 目录1：游戏
		JMenuItem newgame = game.add("新游戏");
		JMenuItem pause = game.add("暂停");
		JMenuItem goon = game.add("继续");
		JMenuItem exit = game.add("退出");
		JMenu help = new JMenu("帮助"); // 目录2：帮助
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

// 吃豆豆板块
class Pacmanblok extends JPanel implements KeyListener {
	// int state; //游戏状态：0继续，1失败，2胜利
	int state = 0; // 标识此时是否有操作
	int wholescore = 100; // 总共100个豆子
	int[][] map = new int[44][50]; // 地图(80*50)：0为路径（无豆豆），1为路径（有豆豆），2为墙
	String []point = new String[100];
	int i, j;
	int x, y;
	Player player1 = new Player();

	public Pacmanblok() {
		newmap();
		// Player player1 = new Player();
		player1.setx(4); // player初始化地点
		player1.sety(10);
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
	}

	// 初始化地图
	public void newmap() {
		for (i = 0; i < 44; i++) {
			for (j = 0; j < 50; j++) // txt中记录路径
			{
				map[i][j] = 2;

			}
		}

		File file = new File("E:/eclipsework/Pacman/file/Path.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;

			while ((tempString = reader.readLine()) != null) {
				point = tempString.split(";");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		
		
		
		for (int i = 0; i < point.length; i++) {
			String[] p = point[i].split(",");
			if (!p[0].equals("") && !p[1].equals("")) {
				x=Integer.parseInt(p[0]);
				y=Integer.parseInt(p[1]);
				//System.out.println("x:"+x+" y:"+y);
				map[x][y] = 1;
			}
		}

	}

	// 左
	public void left() {
		x = player1.getx();
		y = player1.gety();
		if (x != 0 && map[x - 1][y] != 2) {
			player1.setx(x - 1);
			player1.setdirection(1);

			if (map[x-1][y] == 1) {
				map[x - 1][y] = 0;
				player1.addscore();
				Ifgameover();

				repaint();
			}

		}
	}

	// 右
	public void right() {
		x = player1.getx();
		y = player1.gety();
		if (x != 39 && map[x + 1][y] != 2) {
			player1.setx(x + 1);
			player1.setdirection(2);

			if (map[x+1][y] == 1) {
				map[x + 1][y] = 0;
				player1.addscore();
				Ifgameover();

				repaint();
			}
		}
	}

	// 上
	public void up() {
		x = player1.getx();
		y = player1.gety();
		if (y != 0 && map[x][y - 1] != 2) {
			player1.sety(y - 1);
			player1.setdirection(3);

			if (map[x][y-1] == 1) {
				map[x][y - 1] = 0;
				player1.addscore();
				Ifgameover();

				repaint();
			}
		}
	}

	// 下
	public void down() {
		x = player1.getx();
		y = player1.gety();
		if (x != 39 && map[x][y + 1] != 2) {
			player1.sety(y + 1);
			player1.setdirection(4);

			if (map[x][y+1] == 1) {
				map[x][y + 1] = 0;
				player1.addscore();
				Ifgameover();

				repaint();
			}
		}
	}

	// 判断游戏状态
	public void Ifgameover() {
		if (player1.getscore() == wholescore) {
			newmap();
			JOptionPane.showMessageDialog(null, "You Win!");
		}

		// else if()

		else {

		}
	}

	// 绘制
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Image beanimage = new ImageIcon("bean.JPG").getImage();
		Image playerimage = new ImageIcon("player.JPG").getImage();
		
		Font f = new Font("Serif", Font.BOLD, 15);
		g2.setFont(f);
		g.drawString("score:" + player1.getscore(), 10, 15);

		// 画豆豆
		for (i = 0; i < 44; i++) {
			for (j = 0; j < 50; j++) {
				if (map[i][j] == 1) {
					g.drawOval(i*10,j*10, 10, 10);
					//g2.drawImage(beanimage, i, j, this);
				}
				else if(map[i][j] == 2)
				{
					g.drawRect(i*10,j*10, 10, 10);
				}
			}
		}

		// 画player
		x = player1.getx();
		y = player1.gety();
		g.fillOval(x*10, y*10, 10, 10);
		//g2.drawImage(playerimage, x, y, this);

	}

	// 键盘监听
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			state = 1;
			down();
			state = 0;
			break;
		case KeyEvent.VK_UP:
			state = 1;
			up();
			state = 0;
			break;
		case KeyEvent.VK_RIGHT:
			state = 1;
			right();
			state = 0;
			break;
		case KeyEvent.VK_LEFT:
			state = 1;
			left();
			state = 0;
			break;
		}
	}

	// 无用
	public void keyReleased(KeyEvent e) {

	}

	// 无用
	public void keyTyped(KeyEvent e) {

	}

	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			repaint();
			if (state == 0) {
				switch (player1.getdirection()) {
				case 1:
					left();
					break;
				case 2:
					right();
					break;
				case 3:
					up();
					break;
				case 4:
					down();
					break;
				default: // 刚开始还未选择方向的时候
					break;
				}
			}

		}
	}

}