package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.game.DrawableManager;

/**
 * FIXME: Duplication with FruitRule
 */
public class PoisonedFruitRule implements IRule {

	private Vector2 fruitPosition;
	private Entity apple;
	private DrawableManager drawingManager;

	public PoisonedFruitRule(Vector2 fruitPosition) {
		this.fruitPosition = fruitPosition;
	}

	public PoisonedFruitRule(Entity apple, DrawableManager drawingManager) {
		this.apple = apple;
		this.drawingManager = drawingManager;
		this.fruitPosition = apple.getPosition();
	}

	@Override
	public Snake update(Snake snake) {
		return snake.getPosition().equals(fruitPosition) ? consumesFruit(snake) : snake;
	}

	private Snake consumesFruit(Snake snake) {
		fruitPosition = new Vector2(-1, -1);
		drawingManager.remove(apple);
		return snake.removeTail();
	}

	public Vector2 getFruitPosition() {
		return fruitPosition;
	}
}
