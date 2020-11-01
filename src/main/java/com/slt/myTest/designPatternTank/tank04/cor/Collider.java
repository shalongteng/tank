package com.slt.myTest.designPatternTank.tank04.cor;


import com.slt.myTest.designPatternTank.tank04.GameObject;

/**
 * 碰撞器
 * 负责 两个 物体直接的碰撞
 */
public interface Collider {
	boolean collide(GameObject o1, GameObject o2);
}
