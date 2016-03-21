package br.com.danielhabib.snake.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class CompositeInputListener extends InputListener {

	private InputListener[] listeners;

	public CompositeInputListener(InputListener... listeners) {
		this.listeners = listeners;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		for (InputListener listener : listeners) {
			listener.touchDown(event, x, y, pointer, button);
		}
		return super.touchDown(event, x, y, pointer, button);
	}

}
