package br.com.danielhabib.snake.rules;

import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import br.com.danielhabib.snake.game.EventFirerEntity;

public class MapMovingRules extends AMovingRules implements WorldManager {

	private List<EventFirerEntity> wallsList;
	private AMovingRules ruleWhenFree;
	private IRule ruleWhenCollidedWithItSelf;
	private int lastX;
	private int lastY;
	private List<Actor> worldMap;

	public MapMovingRules(AMovingRules ruleWhenFree, IRule ruleWhenCollidedWithItSelf, List<Actor> worldMap, List<EventFirerEntity> wallsList,
			Snake snake, int lastX, int lastY) {
		super(snake);
		this.ruleWhenFree = ruleWhenFree;
		this.ruleWhenCollidedWithItSelf = ruleWhenCollidedWithItSelf;
		this.worldMap = worldMap;
		this.wallsList = wallsList;
		this.lastX = lastX;
		this.lastY = lastY;
	}

	private static float time = 0;

	@Override
	public void act(float delta) {
		super.act(delta);
		time += delta;
		if (time > 0.125) {
			update(delta);
			time = 0;
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		ruleWhenFree.draw(batch, parentAlpha);
	}

	public void update(float delta) {
		if (snakeWouldEatItSelf(snake)) {
			ruleWhenCollidedWithItSelf.fireEvent(snake);
			return;
		} else {
			EventFirerEntity entity = snakeWouldColideWithWall(snake);
			if (!EventFirerEntity.NOP.equals(entity)) {
				entity.fireEvent();
				return;
			}
		}
		// Mirror
		Vector2 nextPosition = snake.getNextPosition();
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

	private EventFirerEntity snakeWouldColideWithWall(Snake snake) {
		Vector2 nextPositionSnake = snake.getNextPosition();
		for (EventFirerEntity entity : wallsList) {
			if (entity.getPosition().epsilonEquals(nextPositionSnake, 0.01f)) {
				return entity;
			}
		}
		return EventFirerEntity.NOP;
	}

	private boolean snakeWouldEatItSelf(Snake snake) {
		Stack<Vector2> nextPositions = snake.getNextPositions();
		Vector2 headPosition = nextPositions.pop();
		for (Vector2 piecePosition : nextPositions) {
			if (headPosition.epsilonEquals(piecePosition, 0.1f)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Actor> getMap() {
		return worldMap;
	}

	@Override
	public void put(EventFirerEntity entity) {
		wallsList.add(entity);
		worldMap.add(entity);
		getStage().addActor(entity);
		Vector2 pos = entity.getPosition();
		entity.addAction(Actions.alpha(0f));
		entity.addAction(Actions.fadeIn(1f));
	}

}
