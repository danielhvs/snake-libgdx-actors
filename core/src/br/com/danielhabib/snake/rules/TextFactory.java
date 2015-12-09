package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class TextFactory {

	public static void addNotifyAnimation(Label title, Actor source, String string, Color color) {
		title.setFontScale(0.75f);
		float x = source.getX() * Entity.SIZE;
		title.addAction(Actions.moveTo(x, source.getY() * Entity.SIZE + Entity.SIZE, 0));
		title.addAction(Actions.alpha(1.0f));
		title.setText(string);
		title.setColor(color);
		title.addAction(Actions.moveTo(x, source.getY() * Entity.SIZE + 2.5f * Entity.SIZE, 1.0f));
		title.addAction(Actions.fadeOut(1.5f));
	}

}
