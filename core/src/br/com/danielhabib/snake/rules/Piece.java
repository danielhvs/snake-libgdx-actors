package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Piece {
	private Vector2 point;
	private Direction direction;
	private Sprite sprite;
	private Texture texture;
	private static final int SIZE = 16;

	public Piece(Vector2 point, Direction direction, Texture texture) {
		// super(texture, point, direction);
		this.point = point;
		this.direction = direction;
		this.texture = texture;
		this.sprite = new Sprite(texture);
		sprite.setSize(SIZE, SIZE);
		sprite.flip(false, true);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
	}

	public Vector2 getPosition() {
		return point.cpy();
	}

	public Direction getNormDirection() {
		return direction;
	}

	public Vector2 getDirection() {
		return direction.getVector2().cpy();
	}

	public Piece move() {
		// FIXME: speed
		Vector2 newPoint = point.cpy().add(direction.getVector2());
		return new Piece(newPoint, direction, texture);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Piece other = (Piece) obj;
		if (direction != other.direction) {
			return false;
		}
		if (point == null) {
			if (other.point != null) {
				return false;
			}
		} else if (!point.equals(other.point)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Piece [" + point + ":" + direction + "]";
	}

	public Piece move(Vector2 finalPoint) {
		this.point = finalPoint;
		return this;
	}

	public Piece turn(Direction newDirection) {
		this.direction = newDirection;
		return this;
	}

	public void draw(SpriteBatch batch) {
		sprite.setPosition(point.x * SIZE, point.y * SIZE);
		sprite.setRotation(direction.getRotation());
		sprite.draw(batch);
	}

	public Texture getTexture() {
		return texture;
	}

	public Sprite getSprite() {
		return sprite;
	}
}
