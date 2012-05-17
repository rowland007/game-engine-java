package com.randarlabs.java.GameEngine.input;

import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.randarlabs.java.GameEngine.core.Core;

public class MouseInput extends Core implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener {
	
	private String mess = "";
	
	public void init() {
		super.init();
		Window w = sm.getFullScreenWindow();
		w.addMouseListener(this);
		w.addMouseMotionListener(this);
		w.addMouseWheelListener(this);
		w.addKeyListener(this);
	}
	
	public synchronized void draw(Graphics2D g) {
		Window w = sm.getFullScreenWindow();
		g.setColor(w.getBackground());
		g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
		g.setColor(w.getForeground());
		g.drawString(mess, 40, 50);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		mess = "Moving mouse wheel";
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//do not implement		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//do not implement
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mess = "You pressed down the mouse";
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mess = "You released the mouse";	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mess = "You are dragging the mouse";	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mess = "You are moving the mouse: " + e.getXOnScreen() + ", " + e.getYOnScreen();	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE) {
			stop();
		}else{
			mess = "Pressed : " + KeyEvent.getKeyText(keyCode);
			e.consume();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		mess = "Released : " + KeyEvent.getKeyText(keyCode);
		e.consume();
	}
	
	public void keyTyped(KeyEvent e) {
		e.consume();
	}
}