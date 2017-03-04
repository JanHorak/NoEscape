/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.janhorak.game.ne.engine;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Jan
 */
public class Renderer {

    Canvas c;
    private Set<GameSprite> sprites;
    final GraphicsContext gc;
    final Font FONT;
    final String FONT_KIND = "Helvetica";

    public Renderer(Canvas c) {
        this.c = c;
        this.gc = c.getGraphicsContext2D();
        this.sprites = new HashSet<>();

        FONT = Font.font(FONT_KIND, FontWeight.BOLD, 24);
        gc.setFont(FONT);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
    }

    public void updateSpriteList(Set<GameSprite> spriteList) {
        sprites.addAll(spriteList);
    }

    public void updateSpriteList(GameSprite sprite) {
        sprites.add(sprite);
    }

    public void cleanSpriteList() {
        this.sprites.clear();
    }

    public void render() {
        gc.clearRect(0, 0, GameEnvironment.WIDTH, GameEnvironment.HEIGHT);
        sprites.forEach((s) -> {
            s.render(gc);
        });
    }

    public void renderText(String text, int width, int heigt) {
        gc.fillText(text, width, heigt);
        gc.strokeText(text, width, heigt);
    }

}
