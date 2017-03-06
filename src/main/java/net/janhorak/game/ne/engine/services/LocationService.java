/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.janhorak.game.ne.engine.services;

import net.janhorak.game.ne.engine.GameEnvironment;
import net.janhorak.game.ne.entities.GameSprite;

/**
 *
 * @author Jan
 */
public class LocationService {
    
    public static final int BORDER_DISTANCE = 50;
    
    public static void setRandomPosition(GameSprite s){
        s.setPosition(getRandom(), getRandom());
    }
    
    private static int getRandom(){
        return (int) ((GameEnvironment.HEIGHT - BORDER_DISTANCE) * Math.random());
    }
    
}
