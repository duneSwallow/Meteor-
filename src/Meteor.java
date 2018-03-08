import java.awt.Color;
import java.awt.Graphics;


public class Meteor {
	
	int y, x, yVel, xVel, color;
	boolean gemTaken = false;
	
	public Meteor(){
		y = 0;
		x = randomxPos();
		yVel = randomSpeed();
		color = 3;
	}
	
	public void reset(){
		y = 0;
		x = randomxPos();
		yVel = randomSpeed();
		gemTaken =false;
		color = 3;
	}
	
	private int randomxPos(){
		return (int)(Math.random()*Galaxy.WIDTH + 10);
	}
	
	private int randomSpeed(){
		return (int)(Math.random()*6+ 2);
	}
	
	private int randomColor(){
		return (int)(Math.random()*4 + 1);
	}

	private int chanceGem() { return (int)(Math.random()*10+1);}
	
	public void draw(Graphics g){
		if(color == 1){
			g.setColor(Color.RED);
		}else if(color == 2){
			g.setColor(Color.BLUE);
		}else{
			g.setColor(Color.BLACK);
		}
		if(gemTaken){
			g.setColor(Color.gray);
		}
			g.fillOval(x + 25, y + 25, 50, 50);

	}
	
	public void move(){
		y += yVel;
		
		if(y > Galaxy.HEIGHT){
			color = 3;
			int gemChance = chanceGem();
			if(gemChance==1){
				color = 2;
			}
			y=0;
			yVel = randomSpeed();
			x=randomxPos();
			gemTaken = false;
		}
	}
	//FIX!!!!!!!!!!!
	public boolean checkCollision(Flyer f){
		int boundary = 40;
		if(x>f.getX()-boundary&&x<f.getX()+boundary){
			if(y>f.getY()-boundary&&y<f.getY()+boundary){
				if(color == 2){
					if(gemTaken == true){
						return false;
					}
					gemTaken = true;
					Galaxy.score++;
					return false;
				}
				return true;
			}
		}
		
		
		return false;
	}
}
