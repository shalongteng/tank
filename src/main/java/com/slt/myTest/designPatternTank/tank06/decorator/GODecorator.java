package com.slt.myTest.designPatternTank.tank06.decorator;


import com.slt.myTest.designPatternTank.tank06.GameObject;

import java.awt.*;

public abstract class GODecorator extends GameObject {
	
	GameObject go;
	
	public GODecorator(GameObject go) {
		
		this.go = go;
	}

	@Override
	public abstract void paint(Graphics g);

}
