package com.randarlabs.java.game.Mario;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import com.randarlabs.java.GameEngine.core.*;
import com.randarlabs.java.GameEngine.video.Animation;

public class Main extends Core implements KeyListener {
	public static void main(String[] args) {
		new Main().run();
	}
	
	private SpriteManager sprite;
	private Animation a;
	private Image hero;
	private Image bg;
	//private Image bgb;
	private Point heroPoint;
	private Point center;
	private Point image;
	private boolean centering;
	private final String mess = "ESC to exit; Arrows to move";
	
	public void init() {
		super.init();
		heroPoint = new Point();
		center = new Point();
		image = new Point();
		centering = false;
		
		try {
			recenterHero();
			heroPoint.x = center.x;
			heroPoint.y = (bg.getHeight(null) - 80) - hero.getHeight(null);
		} catch(Exception ex) {
			System.out.println("Exception 1");
		}
		
		Window w = sm.getFullScreenWindow();
		w.addKeyListener(this);
		w.setFocusTraversalKeysEnabled(false);
		bg = new ImageIcon("res\\mario-wall-01.png").getImage();
		//bgb = new ImageIcon("res\\mario-wall-01b.png").getImage();
		hero = new ImageIcon("res\\8_Bit_Mario.png").getImage();
		a = new Animation();
		a.addScene(hero.getScaledInstance(hero.getWidth(null) / 25, hero.getHeight(null) / 25, 25), 800);
		
		sprite = new SpriteManager(a);
		sprite.setY((sm.getHeight() / 2 - sprite.getHeight()));
		
	}
	
	public void update(long timePassed) {
		if(sprite.getX() < 0) {
			sprite.setVelocityX(Math.abs(sprite.getVelocityX() + 0.3f));
		}else if(sprite.getX() + sprite.getWidth() >= sm.getWidth()) {
			sprite.setVelocityX(-Math.abs(sprite.getVelocityX() - 0.3f));
		}
		
		if(sprite.getY() < 0) {
			sprite.setY((sm.getHeight() / 4) * 3 - sprite.getHeight());
		}else if(sprite.getY() < sm.getHeight() / 3 - sprite.getHeight()) {
			sprite.setVelocityY(Math.abs(sprite.getVelocityY() + 0.3f));
		}else if(sprite.getY() >= (sm.getHeight() - sprite.getHeight())) {
			sprite.setY((sm.getHeight() / 4) * 3 - sprite.getHeight());
		}
		
		sprite.update(timePassed);
 	}
	
	

	@Override
	public void draw(Graphics2D g) {
		int w = sm.getWidth();
		int h = sm.getHeight();
		
		image.x %= w;
		image.y %= h;
		if(image.x < 0) {
			image.x += w;
		}
		if(image.y < 0) {
			image.y += h;
		}
		
		int x = image.x;
		int y = image.y;
		
		g.drawImage(bg, x, y, null);
		g.drawImage(bg, x - w, y, null);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, sm.getWidth()/4, 30);
		g.setColor(Color.WHITE);
		g.drawString(mess, 20, 20);
		g.drawImage(hero, center.x - (hero.getWidth(null) / 2), (int) sprite.getY() /*(bg.getHeight(null) - 80) - hero.getHeight(null)*/, null);		
	}
	
	private void recenterHero() {
		Window w = sm.getFullScreenWindow();
		if(w.isShowing()) {
			center.x = w.getWidth() / 2;
			SwingUtilities.convertPointToScreen(center, w);
			centering = true;
			sprite.setX(center.x);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		long startingTime = System.currentTimeMillis();
		long cumlTime = startingTime;
		long timePassed = System.currentTimeMillis() - cumlTime;
		cumlTime += timePassed;
		
		if(keyCode == KeyEvent.VK_ESCAPE) {
			stop();
		}
		if(keyCode == KeyEvent.VK_RIGHT) {
			sprite.setVelocityX(-0.3f);
			cumlTime += timePassed;
			sprite.update(timePassed);
			if(centering && center.x == sprite.getX() /*&& center.y == sprite.getY()*/) {
				centering = false;
			}else{
				int dx = (int) (sprite.getX() - heroPoint.x);
				//int dy = (int) (sprite.getY() - heroPoint.y);
				image.x += dx;
				//image.y += dy;
				recenterHero();
			}
			
			heroPoint.x = (int) sprite.getX();
			//heroPoint.y = (int) sprite.getY();
			e.consume();
		} 
		if(keyCode == KeyEvent.VK_LEFT) {
			sprite.setVelocityX(0.3f);
			cumlTime += timePassed;
			sprite.update(timePassed);
			if(centering && center.x == sprite.getX() /*&& center.y == sprite.getY()*/) {
				centering = false;
			}else{
				int dx = (int) (sprite.getX() - heroPoint.x);
				//int dy = (int) (sprite.getY() - heroPoint.y);
				image.x += dx;
				//image.y += dy;
				recenterHero();
			}
			
			heroPoint.x = (int) sprite.getX();
			//heroPoint.y = (int) sprite.getY();
			e.consume();
		}
		if(keyCode == KeyEvent.VK_UP) {
			if(sprite.getY() >= (sm.getHeight() - sprite.getHeight()))
			{
				sprite.setVelocityY(0.3f);
			}else if(sprite.getY() > image.y - sprite.getHeight()){
				sprite.setVelocityY(-0.3f);
			}
			cumlTime += timePassed;
			sprite.update(timePassed);
			
			
			e.consume();
		}
		if(keyCode == KeyEvent.VK_DOWN) {
			// TODO Make main sprite duck
			// TODO Move down in a menu
			e.consume();
		}
		else{
			e.consume();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_UP) {
			sprite.setVelocityY(0.0f);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//do not implement
		
	}
}
