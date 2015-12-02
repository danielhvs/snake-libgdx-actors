package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Vector2;

// Stateful rule!
public class FruitRule implements IRule {

	private Vector2 fruitPosition;

	public FruitRule(Vector2 fruitPosition) {
		this.fruitPosition = fruitPosition;
	}

	@Override
	public Snake update(Snake snake) {
		return snake.getPosition().equals(fruitPosition) ? consumesFruit(snake) : snake;
	}

	private Snake consumesFruit(Snake snake) {
		fruitPosition = new Vector2(-1, -1); // NOP: state change!
		return snake.addTail();
	}

	// FIXME: Remove later, generalize access to a "drawable point"?
	public Vector2 getFruitPosition() {
		return fruitPosition;
	}
}
