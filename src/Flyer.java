import java.awt.Graphics;


public class Flyer {
	
	double y, yVel, startY;
	int x, xVel, startX;
	boolean upAccel, downAccel, rightAccel, leftAccel;
	
	public Flyer(int x, int y){
		upAccel = false;
		downAccel = false;
		rightAccel = false;
		leftAccel = false;
		this.y = y;
		this.x = x;
		startX = x;
		startY = y;
	}
	
	public void reset(){
		upAccel = false;
		downAccel = false;
		rightAccel = false;
		leftAccel = false;
		y = startY;
		x = startX;
		yVel = 0;
		xVel = 0;
	}
	
	public void draw(Graphics g){
		g.fill3DRect(x+15, (int)y+15, 30, 30, true);
	}
	
	public void move(){
		if(upAccel){
			y -= 10;
		}else if(downAccel){
			y += 10;
		}else if(rightAccel){
			x += 10;
		}else if(leftAccel){
			x -= 10;
		}else if(!upAccel && !downAccel && !rightAccel && !leftAccel){
			xVel*=0.12;
			yVel*=0.12;
		}


		if(yVel >= 5)
			yVel += 2;
		if(yVel <= -5)
			yVel -= 2;
		if(xVel >= 5)
			xVel += 2;
		if(xVel <= -5)
			xVel -= 2;
		
		y+=yVel;
		x+=xVel;
		
		if(x<1){
			x =Galaxy.WIDTH-1;
		}else if(x>Galaxy.WIDTH-1){
			x = 1;
		}	
			
		if(y>Galaxy.HEIGHT){
			y = 0;
		}else if(y<0){
			y = Galaxy.HEIGHT;
		}
	}
	
	public int getY(){
		return (int)y;
	}
	
	public int getX(){
		return x;
	}
	
	public void setUpAccel(boolean isUp){
		upAccel = isUp;
	}
	
	public void setDownAccel(boolean isDown){
		downAccel = isDown;
	}
	
	public void setRightAccel(boolean isRight){
		rightAccel = isRight;
	}
	
	public void setLeftAccel(boolean isLeft){
		leftAccel = isLeft;
	}
}
