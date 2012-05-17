package com.randarlabs.java.GameEngine.core;

import java.awt.Image;

import com.randarlabs.java.GameEngine.video.Animation;

public class SpriteManager {
	private Animation a;
	private float x;
	private float y;
	private float vX;
	private float vY;

	public SpriteManager(Animation a) {
		this.a = a;
	}
	
	//change position
	public void update(long timePassed) {
		x += vX * timePassed;
		y += vY * timePassed;
		a.update(timePassed);
	}
	
	//get x position
	public float getX() {
		return x;
	}
	
	//get y position
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	//get sprite width
	public int getWidth() {
		return a.getImage().getWidth(null);
	}
	
	public int getHeight() {
		return a.getImage().getHeight(null);
	}
	
	public float getVelocityX() {
		return vX;
	}
	
	public float getVelocityY() {
		return vY;
	}
	
	public void setVelocityX(float vX) {
		this.vX = vX;
	}
	
	public void setVelocityY(float vY) {
		this.vY = vY;
	}
	
	public Image getImage() {
		return a.getImage();
	}
}
