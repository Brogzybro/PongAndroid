package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class GameStateManager {
    int scoreP1 = 0;
    int scoreP2 = 0;
    Texture texture = new Texture("ball.png");
    Sprite ball = new Sprite(texture);
    float ballSpeedX = 8;
    float ballSpeedY = 20;
    BitmapFont scorep1 = new BitmapFont();
    BitmapFont scorep2 = new BitmapFont();


    float ballSpeedMultiplyer = 1;

    public GameStateManager(){
        ball.setBounds(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 30, 30);
        scorep1.getData().setScale(5);
        scorep2.getData().setScale(5);
        scorep1.setColor(Color.WHITE);
        scorep2.setColor(Color.WHITE);
    }

    public int getScoreP1() {
        return scoreP1;
    }

    public void setScoreP1(int scoreP1) {
        this.scoreP1 = scoreP1;
    }

    public int getScoreP2() {
        return scoreP2;
    }

    public void setScoreP2(int scoreP2) {
        this.scoreP2 = scoreP2;
    }

    public void moveBall(Player p1, Player p2){
        Rectangle b = ball.getBoundingRectangle();
        Rectangle p1R = p1.sprite.getBoundingRectangle();
        Rectangle p2R = p2.sprite.getBoundingRectangle();
        if (b.overlaps(p1R) || b.overlaps(p2R)){
            ballSpeedY = -ballSpeedY;
        }
        ball.translate(ballSpeedX, ballSpeedY);
    }

    public void checkIfGoal(Sprite background){
        com.badlogic.gdx.math.Rectangle ballRect = ball.getBoundingRectangle();
        Rectangle bgRect = background.getBoundingRectangle();
        Boolean goal = false;
        if (!bgRect.contains(ballRect)){
            if (ball.getX() <= ball.getX() || ball.getX()+ball.getWidth() >= background.getX()+background.getWidth()) { // Ball hits wall
                ballSpeedX = -ballSpeedX;
            }
            if (ball.getY() < background.getY()) {
                setScoreP2(getScoreP2() + 1);
                System.out.println(getScoreP1() + " - " + getScoreP2());
                goal = true;
            }
            if (ball.getY() > background.getHeight()) {
                setScoreP1(getScoreP1() + 1);
                System.out.println(getScoreP1() + " - " + getScoreP2());
                goal = true;
            }
            if (goal){
                if (getScoreP1() == 21 || getScoreP2() == 21){
                    endGame();
                }else{
                    resetBall();
                }
            }

        }else{
            return;
        }
    }

    public void resetBall(){
        ball.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }

    public void endGame(){
        System.out.println("Game is over");
        ballSpeedY = 0;
        ballSpeedX = 0;
    }

    public float getBallSpeedMultiplyer() {
        return ballSpeedMultiplyer;
    }

    public void setBallSpeedMultiplyer(float ballSpeedMultiplyer) {
        this.ballSpeedMultiplyer = ballSpeedMultiplyer;
    }
}
