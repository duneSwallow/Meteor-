import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Galaxy extends Applet implements Runnable, KeyListener {

	final static int HEIGHT = 1000, WIDTH = 1000;
	static int score = 0;
	static int scores[] = {0,0,0};
	Thread thread;
	Graphics gfx;
	Image img;
	Flyer f1;
	Meteor m1, m2, m3;
	boolean gameOver, gameStarted;

	public void init() {
		gameOver = false;
		gameStarted = false;
		this.resize(WIDTH, HEIGHT);
		img = createImage(WIDTH, HEIGHT);
		this.addKeyListener(this);
		f1 = new Flyer(WIDTH/2, HEIGHT-60);
		m1 = new Meteor();
		m2 = new Meteor();
		m3 = new Meteor();
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {

		if (gameOver) {
			/*
			 * gfx.setColor(Color.orange); gfx.fillRect(0, 0, WIDTH, HEIGHT);
			 * gfx.setColor(Color.BLACK); gfx.drawString("Game Over", 350, 250);
			 */

			gfx.setColor(Color.red);
			gfx.fillRect(0, 0, WIDTH, HEIGHT);
			gfx.setColor(Color.WHITE);
			gfx.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			for(int i = 0; i < 3; i++){
				if(score >scores[i]){
					scores[i] = score;
					break;
				}
			}
			gfx.drawString("Score : " + score, WIDTH/2, HEIGHT/2);
			score = 0;
			gfx.drawString("Type Enter to Begin Again", WIDTH/2, HEIGHT/2+30);
			gfx.drawString("High Scores", WIDTH/2, HEIGHT-110);
			gfx.drawString("#1 : " + scores[0], WIDTH/2, HEIGHT-80);
			gfx.drawString("#2 : " + scores[1], WIDTH/2, HEIGHT-50);
			gfx.drawString("#3 : " + scores[2], WIDTH/2, HEIGHT-20);
			gameStarted = false;

		} else if (!gameStarted) {
			gfx.setColor(Color.green);
			gfx.fillRect(0, 0, WIDTH, HEIGHT);
			gfx.setColor(Color.BLACK);
			gfx.setFont(new Font("TimesRoman", Font.PLAIN, 24));

			gfx.drawString("Type Enter to Begin", WIDTH/2, HEIGHT/2);

		} else {
			gfx.setColor(Color.getHSBColor(277, 100, 71));
			gfx.fillRect(0, 0, WIDTH, HEIGHT);
			gfx.setColor(Color.red);
			gfx.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			gfx.drawString("Gems : " + score, 20, 20);
			f1.draw(gfx);
			m1.draw(gfx);
			m2.draw(gfx);
			m3.draw(gfx);
		}	 
		g.drawImage(img, 0, 0, this);
	}

	public void run() {
		while (true) {
			if (!gameOver && gameStarted) {
				f1.move();
				m1.move();
				m2.move();
				m3.move();
				if (m1.checkCollision(f1) || m2.checkCollision(f1)
						|| m3.checkCollision(f1))
					gameOver = true;
			}

			repaint();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			f1.setUpAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			f1.setDownAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			f1.setRightAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			f1.setLeftAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			gameStarted = true; 
			if(gameOver){
				f1.reset();
				m1.reset();
				m2.reset();
				m3.reset();

				gameOver = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			f1.setUpAccel(false);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			f1.setDownAccel(false);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			f1.setRightAccel(false);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			f1.setLeftAccel(false);
		}

	}
}
