package br.com.danielhabib.snake.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.Texture;

/**
 * Simple cache for textures.
 */
public class TextureManager {

	private Map<String, Texture> map;

	public TextureManager() {
		map = new HashMap<String, Texture>();
	}

	public Texture getTexture(String textureName) {
		return map.get(textureName);
	}

	public void put(String textureName, Texture texture) {
		map.put(textureName, texture);
	}

	public void dispose() {
		for (Entry<String, Texture> entry : map.entrySet()) {
			entry.getValue().dispose();
		}
		map.clear();
	}
}
