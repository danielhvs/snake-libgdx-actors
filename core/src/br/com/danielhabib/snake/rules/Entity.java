package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	public static int SIZE = 32;
	protected Vector2 pos;
	private Sprite sprite;

	public Entity(Texture texture, Vector2 pos, Vector2 direction) {
		this.pos = pos;
		this.sprite = new Sprite(texture);
	}

	public abstract void update();

	public void render(SpriteBatch batch) {
		sprite.draw(batch);
	}

	public Vector2 getPosition() {
		return pos.cpy();
	}

	public Rectangle getBounds() {
		return new Rectangle(pos.x * SIZE, pos.y * SIZE, sprite.getWidth(), sprite.getHeight());
	}


}
