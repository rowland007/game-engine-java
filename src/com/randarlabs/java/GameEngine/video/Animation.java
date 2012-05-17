package com.randarlabs.java.GameEngine.video;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	private ArrayList<OneScene> scenes;
	private int sceneIndex;
	private long movieTime;
	private long totalTime;
	
	public Animation() {
		scenes = new ArrayList<OneScene>();
		totalTime = 0;
		start();
	}
	
	public synchronized void addScene(Image i, long time) {
		totalTime += time;
		scenes.add(new OneScene(i, totalTime));
	}
	
	public synchronized void start() {
		movieTime = 0;
		sceneIndex = 0;
	}
	
	public synchronized void update(long timePassed) {
		if(scenes.size()>1) {
			movieTime += timePassed;
			if(movieTime >= totalTime) {
				movieTime = 0;
				sceneIndex = 0;
			}
			while(movieTime > getScene(sceneIndex).endTime) {
				sceneIndex++;
			}
		}
	}
	
	public synchronized Image getImage() {
		if(scenes.size() == 0) {
			return null;
		} else {
			return getScene(sceneIndex).pic;
		}
	}
	
	private OneScene getScene(int x) {
		return (OneScene)scenes.get(x);
	}
	
	public class OneScene {
		Image pic;
		long endTime;
		
		public OneScene(Image p, long time) {
			this.pic = p;
			this.endTime = time;
		}
	}
}
