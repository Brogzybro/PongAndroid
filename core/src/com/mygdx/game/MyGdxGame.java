package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture bg;
	Sprite bgSprite;
	Player player1;
	Player player2;
	GameStateManager gsm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Texture("bg.png");
		bgSprite = new Sprite(bg);
		bgSprite.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		player1 = new Player(1);
		player2 = new Player(2);
		gsm = new GameStateManager();
	}

	@Override
	public void render () {
		final float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		draw();
	}

	private void update(final float dt){
		if (Gdx.input.isTouched()){
			if (Gdx.input.getY() > Gdx.graphics.getHeight()/2){ //player1 is moving
				player1.movePlayer(Gdx.input.getX());
			}else{
				player2.movePlayer(Gdx.input.getX());
			}
		}
		gsm.moveBall(player1, player2);
		gsm.checkIfGoal(bgSprite);

	}

	private void draw(){
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bgSprite.draw(batch);
		player1.sprite.draw(batch);
		player2.sprite.draw(batch);
		gsm.scorep1.draw(batch, gsm.getScoreP1()+"",10, (bgSprite.getHeight()/2) - 60);
		gsm.scorep2.draw(batch, gsm.getScoreP2()+"",10, (bgSprite.getHeight()/2) + 60);
		gsm.ball.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		bg.dispose();
	}


}
