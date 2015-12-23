package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * For DEBUG.
 */
public class FpsCountingLabel extends Label {

	public FpsCountingLabel(CharSequence text, LabelStyle style) {
		super(text, style);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		setText(String.valueOf(Gdx.graphics.getFramesPerSecond()));
		Camera camera = getStage().getCamera();
		Vector3 position = camera.position;
		setPosition(8f + position.x - camera.viewportWidth / 2, 8f + position.y - camera.viewportHeight / 2);
	}

}
