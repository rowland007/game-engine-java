package com.randarlabs.java.GameEngine.input;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;

import com.randarlabs.java.GameEngine.core.Core;

public class MouseLook extends Core implements KeyListener, MouseMotionListener {
	
	private Image bg;
	private Robot robot;
	private Point mouse;
	private Point center;
	private Point image;
	private boolean centering;
	
	public void init() {
		super.init();
		mouse = new Point();
		center = new Point();
		image = new Point();
		centering = false;
		
		try {
			robot = new Robot();
			recenterMouse();
			mouse.x = center.x;
			mouse.y = center.y;
		} catch(Exception ex) {
			System.out.println("Exception 1");
		}
		
		Window w = sm.getFullScreenWindow();
		w.addMouseMotionListener(this);
		w.addKeyListener(this);
		bg = new ImageIcon("res\\bg.jpg").getImage();
		
	}
	
	public synchronized void draw(Graphics2D g) {
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
		g.drawImage(bg, x, y - h, null);
		g.drawImage(bg, x - w, y - h, null);
	}

	private synchronized void recenterMouse() {
		Window w = sm.getFullScreenWindow();
		if(robot != null && w.isShowing()) {
			center.x = w.getWidth() / 2;
			center.y = w.getHeight() / 2;
			SwingUtilities.convertPointToScreen(center, w);
			centering = true;
			robot.mouseMove(center.x, center.y);
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(centering && center.x == e.getX() && center.y == e.getY()) {
			centering = false;
		}else{
			int dx = e.getX() - mouse.x;
			int dy = e.getY() - mouse.y;
			image.x += dx;
			image.y += dy;
			recenterMouse();
		}
		
		mouse.x = e.getX();
		mouse.y = e.getY();
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE) {
			stop();
		}else{
			e.consume();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		//do not implement
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//do not implement
		
	}
}