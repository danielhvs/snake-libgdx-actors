package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ToggleButtonTextInputListener extends InputListener {

	private TextButton textButton;
	private String textOn;
	private String textOff;

	public ToggleButtonTextInputListener(TextButton textButton, String textOn, String textOff) {
		this.textButton = textButton;
		this.textOn = textOn;
		this.textOff = textOff;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		String text = textButton.getText().toString();
		if (text.equals(textOn)) {
			textButton.setText(textOff);
		} else if (text.equals(textOff)) {
			textButton.setText(textOn);
		}
		return super.touchDown(event, x, y, pointer, button);
	}
}

