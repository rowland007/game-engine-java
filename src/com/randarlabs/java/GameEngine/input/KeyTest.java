package com.randarlabs.java.GameEngine.input;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.randarlabs.java.GameEngine.core.Core;

public class KeyTest extends Core implements KeyListener {
	
	private String mess = "";
	
	public void init() {
		super.init();
		Window w = sm.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);
		mess = "Press escape to exit";
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE) {
			stop();
		} 
		if(keyCode == KeyEvent.VK_RIGHT) {
			mess = "Pressed : " + KeyEvent.getKeyText(keyCode);
			// TODO move screen left (Sprite goes right)
			// TODO go left in a menu
			e.consume();
		} 
		if(keyCode == KeyEvent.VK_LEFT) {
			mess = "Pressed : " + KeyEvent.getKeyText(keyCode);
			// TODO move screen to the right (Sprite goes left)
			// TODO go left in a menu
			e.consume();
		}
		if(keyCode == KeyEvent.VK_UP) {
			mess = "Pressed : " + KeyEvent.getKeyText(keyCode);
			// TODO Make main sprite jump 
			// TODO Move up in a menu
		}
		if(keyCode == KeyEvent.VK_DOWN) {
			mess = "Pressed : " + KeyEvent.getKeyText(keyCode);
			// TODO Make main sprite duck
			// TODO Move down in a menu
		}
		else{
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
	
	public void draw(Graphics2D g) {
	
	}
}