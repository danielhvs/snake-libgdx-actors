package br.com.danielhabib.snake.rules;

import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.math.Vector2;

public class MapMovingRules extends AMovingRules {

	private IRule ruleWhenCollided;
	private List<Entity> map;
	private IRule ruleWhenFree;

	public MapMovingRules(IRule ruleWhenCollided, IRule ruleWhenFree, List<Entity> map) {
		this.ruleWhenCollided = ruleWhenCollided;
		this.ruleWhenFree = ruleWhenFree;
		this.map = map;
	}

	// FIXME: Add a rule when colided with itself? Maybe can be used to notify
	// the event.
	@Override
	public Snake update(Snake snake) {
		return snakeWouldEatItSelf(snake) ? snake
				: snakeWouldColide(snake) ? ruleWhenCollided.update(snake)
										  : ruleWhenFree.update(snake);
	}

	private boolean snakeWouldColide(Snake snake) {
		Vector2 nextPositionSnake = snake.getNextPosition();
		for (Entity entity : map) {
			if (entity.getPosition().epsilonEquals(nextPositionSnake, 0.01f)) {
				return true;
			}
		}
		return false;
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
