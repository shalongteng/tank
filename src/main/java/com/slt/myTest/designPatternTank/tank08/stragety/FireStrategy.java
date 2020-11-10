package com.slt.myTest.designPatternTank.tank08.stragety;

import com.slt.myTest.designPatternTank.tank08.Tank;

import java.io.Serializable;

/**
 *
 */
public interface FireStrategy extends Serializable {
    void fire(Tank tank);
}
