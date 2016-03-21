package br.com.danielhabib.snake.ui;

import com.kotcrab.vis.ui.widget.VisLabel;

public class SnakeTimer extends VisLabel {
	private float time;

	@Override
	public void act(float delta) {
		time += delta;
		setText(String.valueOf(Math.round(time)));
		super.act(delta);
	}
}
