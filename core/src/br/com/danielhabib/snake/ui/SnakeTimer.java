package br.com.danielhabib.snake.ui;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class SnakeTimer extends Actor {
	private Label label;
	private float time;

	public SnakeTimer(Label label) {
		this.label = label;
	}

	@Override
	public void act(float delta) {
		time += delta;
		label.setText(String.valueOf(Math.round(time)));
		Camera camera = getStage().getCamera();
		// FIXME: HardCoded
		float labelWidth = 15f;
		float xOffset = 3f;
		label.setPosition(xOffset + camera.position.x - camera.viewportWidth / 2, camera.position.y + camera.viewportHeight / 2 - labelWidth);
		label.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		label.draw(batch, parentAlpha);
	}
}
