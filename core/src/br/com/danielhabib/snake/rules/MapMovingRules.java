package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.game.EventFirerEntity;

public class MapMovingRules extends AMovingRules implements WorldManager {

	private Array<EventFirerEntity> wallsList;
	private AMovingRules ruleWhenFree;
	private IRule ruleWhenCollidedWithItSelf;
	private float lastX;
	private float lastY;
	private Array<Actor> worldMap;

	public MapMovingRules(AMovingRules ruleWhenFree, IRule ruleWhenCollidedWithItSelf, Array<Actor> worldMap,
			Array<EventFirerEntity> wallsList,
			Snake snake, float lastX, float lastY) {
		super(snake);
		this.ruleWhenFree = ruleWhenFree;
		this.ruleWhenCollidedWithItSelf = ruleWhenCollidedWithItSelf;
		this.worldMap = worldMap;
		this.wallsList = wallsList;
		this.lastX = lastX;
		this.lastY = lastY;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		update(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		ruleWhenFree.draw(batch, parentAlpha);
	}

	public void update(float delta) {
		if (snake.hasActions()) {
			return;
		}
		if (snake.isEatingItSelf()) {
			ruleWhenCollidedWithItSelf.fireEvent(snake);
			// return;
		} else {
			EventFirerEntity entity = snakeWouldColideWithWall(snake, delta);
			if (!EventFirerEntity.NOP.equals(entity)) {
				entity.fireEvent();
				return;
			}
		}
		// Mirror
		Vector2 nextPosition = snake.getNextPosition(delta);
		if (nextPosition.x > lastX) {
			snake.move(new Vector2(0, snake.getPosition().y));
		} else if (nextPosition.x < 0) {
			snake.move(new Vector2(lastX, snake.getPosition().y));
		} else if (nextPosition.y > lastY) {
			snake.move(new Vector2(snake.getPosition().x, 0));
		} else if (nextPosition.y < 0) {
			snake.move(new Vector2(snake.getPosition().x, lastY));
		} else {
			ruleWhenFree.act(delta);
		}
	}

	private EventFirerEntity snakeWouldColideWithWall(Snake snake, float delta) {
		Vector2 nextPositionSnake = snake.getNextPosition(delta);
		Rectangle snakeBounds = snake.getBounds().setPosition(nextPositionSnake.x, nextPositionSnake.y);
		for (EventFirerEntity entity : wallsList) {
			if (snakeBounds.contains(entity.getBounds()) || snakeBounds.overlaps(entity.getBounds())) {
				return entity;
			}
		}
		return EventFirerEntity.NOP;
	}

	@Override
	public Array<Actor> getMap() {
		return worldMap;
	}

	@Override
	public void put(EventFirerEntity entity) {
		wallsList.add(entity);
		worldMap.add(entity);
		getStage().addActor(entity);
		entity.addAction(Actions.alpha(0f));
		entity.addAction(Actions.fadeIn(1f));
	}

}
