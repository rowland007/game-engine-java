package com.randarlabs.java.GameEngine.video;

import java.awt.*;
import javax.swing.*;

import com.randarlabs.java.GameEngine.core.*;

public class Images extends Core {
	
	private SpriteManager sprite;
	private ScreenManager s;
	private Animation a;
	private Image shut;
	private Image blank;
	private Image open;
	private Image bg;
	
	public void run() {		
		s = new ScreenManager();
		try {
			DisplayMode dm = s.findFirstCompatibleMode(modes1);
			
			s.setFullScreen(dm);
			loadPics();
			movieLoop();
		}finally {
			s.restoreScreen();
		}
	}
	
	private void movieLoop() {
		long startingTime = System.currentTimeMillis();
		long cumlTime = startingTime;
		
		while(cumlTime - startingTime < 75000) {
			long timePassed = System.currentTimeMillis() - cumlTime;
			cumlTime += timePassed;
			update(timePassed);
			
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
			try{
				Thread.sleep(20);
			}catch(Exception ex) {}
		}
	}

	public void draw(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		g.drawImage(a.getImage(),(s.getWidth()/2)-a.getImage().getWidth(null),(s.getHeight()/2)-a.getImage().getHeight(null),null);
		g.drawImage(sprite.getImage(),Math.round(sprite.getX()),Math.round(sprite.getY()),null);
	}

	public void loadPics() {
		bg = new ImageIcon("res\\bg.jpg").getImage();
		blank = new ImageIcon("res\\blankFace.png").getImage();
		open = new ImageIcon("res\\openFace.png").getImage();
		shut = new ImageIcon("res\\shutFace.png").getImage();
		a = new Animation();
		a.addScene(shut, 800);
		a.addScene(blank, 250);
		a.addScene(open, 800);
		a.addScene(blank, 250);
		
		sprite = new SpriteManager(a);
		sprite.setVelocityX(0.3f);
		sprite.setVelocityY(0.3f);
	}

	public void paint(Graphics g) {
		if(g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		} 
	}
	
	public void update(long timePassed) {
		if(sprite.getX() < 0) {
			sprite.setVelocityX(Math.abs(sprite.getVelocityX()+0.1f));
		}else if(sprite.getX() + sprite.getWidth() >= s.getWidth()) {
			sprite.setVelocityX(-Math.abs(sprite.getVelocityX()-0.1f));
		}
		
		if(sprite.getY() < 0) {
			sprite.setVelocityY(Math.abs(sprite.getVelocityY()+0.5f));
		}else if(sprite.getY() + sprite.getHeight() >= s.getHeight()) {
			sprite.setVelocityY(-Math.abs(sprite.getVelocityY()-0.5f));
		}
		
		sprite.update(timePassed);
 	}
	
	private static final DisplayMode modes1[] = {
		new DisplayMode(1366,768,32,0),
		new DisplayMode(1366,768,24,0),
		new DisplayMode(1366,768,16,0),
		new DisplayMode(1360,768,32,0),
		new DisplayMode(1360,768,24,0),
		new DisplayMode(1360,768,16,0),
		new DisplayMode(1280,768,32,0),
		new DisplayMode(1280,768,24,0),
		new DisplayMode(1280,768,16,0),
		new DisplayMode(1280,720,32,0),
		new DisplayMode(1280,720,24,0),
		new DisplayMode(1280,720,16,0),
		new DisplayMode(1280,600,32,0),
		new DisplayMode(1280,600,24,0),
		new DisplayMode(1280,600,16,0),
		new DisplayMode(1024,768,32,0),
		new DisplayMode(1024,768,24,0),
		new DisplayMode(1024,768,16,0),
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,24,0),
		new DisplayMode(640,480,16,0),
	};

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(bg, 0, 0, null);
		g.drawImage(a.getImage(),(s.getWidth()/2)-a.getImage().getWidth(null),(s.getHeight()/2)-a.getImage().getHeight(null),null);
		g.drawImage(sprite.getImage(),Math.round(sprite.getX()),Math.round(sprite.getY()),null);		
	}
}
