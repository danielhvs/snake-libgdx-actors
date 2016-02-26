package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import br.com.danielhabib.snake.game.UIFactory;
import br.com.danielhabib.snake.rules.SnakeEvent.Type;

public class NotificationPresenter extends Actor {

	public void addListeners() {
		addListener(new SnakeListener() {
			@Override
			public boolean handle(Actor source, Type type) {
				switch (type) {
				case speed:
					Label label = UIFactory.newLabel();
					getStage().addActor(label);
					TextFactory.addNotifyAnimation(label, source, "+speed!", Color.GREEN);
					break;
				default:
					break;
				}
				return false;
			}

			@Override
			public boolean colided(Actor source, Event event) {
				Label label = UIFactory.newLabel();
				getStage().addActor(label);
				label.toFront();
				label.setFontScale(1f);
				float x = source.getX() - source.getWidth() / 2;
				label.addAction(Actions.moveTo(x, source.getY() + source.getHeight(), 0));
				label.addAction(Actions.alpha(1.0f));
				label.setText("Sorry, dude, you died!");
				label.setColor(Color.RED);
				label.addAction(Actions.moveTo(x, source.getY() + 2.5f * source.getHeight(), 2.0f));
				return super.colided(source, event);
			}

			@Override
			public boolean addTail(Actor source, Event event) {
				Label label = UIFactory.newLabel();
				getStage().addActor(label);
				TextFactory.addNotifyAnimation(label, source, "yummi!", Color.WHITE);
				return false;
			}

			@Override
			public boolean removeTail(Actor source, Event event) {
				Label label = UIFactory.newLabel();
				getStage().addActor(label);
				TextFactory.addNotifyAnimation(label, source, "ick!", Color.RED);
				return false;
			}

			@Override
			public boolean revert(Actor source, Event event) {
				Label label = UIFactory.newLabel();
				getStage().addActor(label);
				TextFactory.addNotifyAnimation(label, source, "boing!", Color.YELLOW);
				return false;
			}
		});
	}
}
