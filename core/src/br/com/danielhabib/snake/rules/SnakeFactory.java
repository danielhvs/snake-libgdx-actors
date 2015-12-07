package br.com.danielhabib.snake.rules;

import java.util.Stack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.game.Head;
import br.com.danielhabib.snake.game.Tail;

public class SnakeFactory {

	public static Snake newSnakeAtXY(int x, int y, Direction direction, Texture headTexture, Texture pieceTexture, Texture tailTexture) {
		Stack<Piece> pieces = new Stack<Piece>();
		pieces.push(new Head(new Vector2(x, y), direction, headTexture));
		int size = 4;
		int i = 1;
		for (i = 1; i < size - 1; i++) {
			pieces.push(new Piece(new Vector2(x - i, y), direction, pieceTexture));
		}
		pieces.push(new Tail(new Vector2(x - i, y), direction, tailTexture));
		Snake snake = new Snake(pieces, pieceTexture);
		return snake;
	}
}
