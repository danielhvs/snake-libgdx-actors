package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class SoundConfigInputListener extends InputListener {
	private SoundManager soundManager;

	public SoundConfigInputListener(SoundManager soundManager) {
		this.soundManager = soundManager;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		toggleSound();
		return super.touchDown(event, x, y, pointer, button);
	}

	private void toggleSound() {
		if (soundManager.isEnabled()) {
			soundManager.disable();
		} else {
			soundManager.enable();
		}
	}
}
