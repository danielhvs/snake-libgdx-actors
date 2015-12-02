package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class HoleTest {
	@Test
	public void hole_ConstructorOnly_DefinesInitialAndFinalPosition() throws Exception {
		Hole hole = new Hole(new Vector2(0, 0), new Vector2(1, 1));

		assertEquals(new Vector2(0, 0), hole.getInitialPoint());
		assertEquals(new Vector2(1, 1), hole.getFinalPoint());
	}
}
