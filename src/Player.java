
public class Player {
	public int x;
	public int y;	//游戏人物坐标
	public int direction;
	public int score=0;
	
	public void setx(int tx)
	{
		this.x=tx;
	}

	public void sety(int ty)
	{
		this.y=ty;
	}

	public void setdirection(int dir)
	{
		this.direction=dir;
	}
	
	public void addscore()
	{
		this.score=this.score+1;
	}
	
	public int getx()
	{
		return this.x;
	}

	public int gety()
	{
		return this.y;
	}
	
	public int getdirection()
	{
		return this.direction;
	}
	
	public int getscore()
	{
		return this.score;
	}
	public static void main(String[] args)
	{
		int x,y,score,score1;
		Player player1 = new Player();
		player1.setx(100);
		player1.sety(50);
		x=player1.getx();
		y=player1.gety();
		score=player1.getscore();
		player1.addscore();
		score1=player1.getscore();
		System.out.println("x:"+x+"	y:"+y+"	score:"+score+" 	scorelater:"+score1);
		
	}
}
