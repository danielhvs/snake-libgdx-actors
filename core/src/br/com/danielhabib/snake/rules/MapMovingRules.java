package br.com.danielhabib.snake.rules;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class MapMovingRules extends AMovingRules {

	private Map<Entity, IRule> map;
	private IRule ruleWhenFree;
	private IRule ruleWhenCollidedWithItSelf;

	public MapMovingRules(IRule ruleWhenFree, IRule ruleWhenCollidedWithItSelf) {
		this(ruleWhenFree, ruleWhenCollidedWithItSelf, new HashMap<Entity, IRule>());
	}

	public MapMovingRules(IRule ruleWhenFree, IRule ruleWhenCollidedWithItSelf, Map<Entity, IRule> map) {
		this.ruleWhenFree = ruleWhenFree;
		this.ruleWhenCollidedWithItSelf = ruleWhenCollidedWithItSelf;
		this.map = map;
	}

	@Override
	public Snake update(Snake snake) {
		int lastX = -1 + Gdx.graphics.getWidth() / Entity.SIZE;
		int lastY = -1 + Gdx.graphics.getHeight() / Entity.SIZE;
		if (snakeWouldEatItSelf(snake)) {
			return ruleWhenCollidedWithItSelf.update(snake);
		} else {
			Entity entity = snakeWouldColide(snake);
			if (!Entity.NOEntity.equals(entity)) {
				return map.get(entity).update(snake);
			}
		}
		// Mirror
		Vector2 nextPosition = snake.getNextPosition();
		if (nextPosition.x > lastX) {
			return snake.move(new Vector2(0, snake.getPosition().y));
		} else if (nextPosition.x < 0) {
			return snake.move(new Vector2(lastX, snake.getPosition().y));
		} else if (nextPosition.y > lastY) {
			return snake.move(new Vector2(snake.getPosition().x, 0));
		} else if (nextPosition.y < 0) {
			return snake.move(new Vector2(snake.getPosition().x, lastY));
		}

		return ruleWhenFree.update(snake);
	}

	private Entity snakeWouldColide(Snake snake) {
		Vector2 nextPositionSnake = snake.getNextPosition();
		for (Entity entity : map.keySet()) {
			if (entity.getPosition().epsilonEquals(nextPositionSnake, 0.01f)) {
				return entity;
			}
		}
		return Entity.NOEntity;
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

}
