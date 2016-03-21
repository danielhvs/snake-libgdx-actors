package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.entity.Entity;
import br.com.danielhabib.snake.entity.EventFirerEntity;
import br.com.danielhabib.snake.entity.Piece;
import br.com.danielhabib.snake.entity.builders.EntityBuilder;
import br.com.danielhabib.snake.listeners.SnakeEvent;

public class BaseTest {
	@Mock
	EventFirerEntity eventFirerEntity;

	@Mock
	EntityBuilder entityBuilder;

	@Mock
	WorldManager worldManager;

	@Mock
	TiledMapTileLayer layer;

	@Mock
	Texture texture;

	@Mock
	SpriteBatch batch;

	@Mock
	Entity apple;

	@Mock
	Stage stage;

	@Mock
	Actor actor;

	@Captor
	ArgumentCaptor<SnakeEvent> eventCaptor;

	@Before
	public void superSetup() {
		MockitoAnnotations.initMocks(this);
		when(texture.getWidth()).thenReturn(32);
		when(texture.getHeight()).thenReturn(32);
	}

	protected Snake newSnake(int x, int y, Vector2 velocity) {
		Array<Piece> pieces = new Array<Piece>();
		pieces.add(new Piece(new Vector2(x, y), texture));
		return new Snake(pieces, texture, velocity);
	}

	protected Snake newSnake(int x, int y) {
		return newSnake(new Vector2(x, y));
	}

	protected Snake newSnake(Vector2 point) {
		return newSnake(point.x, point.y);
	}

	protected Snake newSnake(float x, float y) {
		return newSnake(new Vector2(32, 0));
	}

	protected Snake newSnake(Array<Piece> pieces) {
		return new Snake(pieces, texture, new Vector2(32, 0));
	}

	protected Piece newPiece(Vector2 vector) {
		return new Piece(vector, texture);
	}

	protected Piece newPiece(int x, int y) {
		return newPiece(new Vector2(x, y));
	}

	protected void assertPoints(Vector2 expected, Vector2 actual) {
		assertEquals("X differs", expected.x, actual.x, 0.01f);
		assertEquals("Y differs", expected.y, actual.y, 0.01f);
	}

	protected void assertSnake(Snake expected, Snake actual) {
		Array<Piece> expectedPieces = expected.getPieces();
		Array<Piece> actualPieces = actual.getPieces();
		assertEquals("Pieces size", expectedPieces.size, actualPieces.size);
		for (int i = 0; i < expectedPieces.size; i++) {
			assertPiece(expectedPieces.get(i), actualPieces.get(i));
		}
	}

	protected void assertPiece(Piece expectedPiece, Piece actualPiece) {
		assertPoints(expectedPiece.getPosition(), actualPiece.getPosition());
	}

	protected AFruitRule newFruitRule(Vector2 position, IRule rule) {
		Mockito.when(apple.getPosition()).thenReturn(position);
		return null;// new AFruitRule(null, drawingManager, snake);
	}

	protected void touchDown(InputListener listener) {
		listener.touchDown(null, 0, 0, 0, 0);
	}
}
