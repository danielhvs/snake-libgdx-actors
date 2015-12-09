package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class TextFactory {

	public static void addNotifyAnimation(Label label, Actor source, String string, Color color) {
		label.setFontScale(0.75f);
		float x = source.getX() * Entity.SIZE;
		label.addAction(Actions.moveTo(x, source.getY() * Entity.SIZE + Entity.SIZE, 0));
		label.addAction(Actions.alpha(1.0f));
		label.setText(string);
		label.setColor(color);
		label.addAction(Actions.moveTo(x, source.getY() * Entity.SIZE + 2.5f * Entity.SIZE, 1.0f));
		label.addAction(Actions.fadeOut(1.5f));
	}

	public static void addCountingAnimation(Label label, String string, Color color, float x, float y) {
		label.setFontScale(0.75f);
		label.addAction(Actions.moveTo(x, y, 0));
		label.addAction(Actions.alpha(0f));
		label.setText(string);
		label.setColor(color);
		label.addAction(Actions.fadeIn(.75f));
	}

}
