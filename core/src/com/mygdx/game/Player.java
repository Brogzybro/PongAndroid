package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    float width = 200;
    float height = 15;
    Texture texture = new Texture(Gdx.files.internal("Untitled.png"));
    Sprite sprite = new Sprite(texture);

    public Player(int playerNmbr){
        float startY = 15;
        if (playerNmbr == 2){
            startY = Gdx.graphics.getHeight() - 2*startY ;
        }
        sprite.setBounds(Gdx.graphics.getWidth()/2 - width/2, startY, width, height );
    }

    public void movePlayer(float inputX){
        if (inputX < sprite.getX()){
            sprite.translate(-10, 0);
        }
        if (inputX > sprite.getX()){
            sprite.translate(10, 0);
        }
        if (sprite.getX()+width > Gdx.graphics.getWidth()){
            sprite.translateX(-11);
        }
        if (sprite.getX() < 0){
            sprite.translateX(11);
        }
    }

}
