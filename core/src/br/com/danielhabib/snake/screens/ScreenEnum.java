package br.com.danielhabib.snake.screens;

import br.com.danielhabib.snake.sound.SoundManager;

public enum ScreenEnum {

	MAIN_MENU {
		@Override
		public AbstractScreen getScreen(SoundManager soundManager, Object... params) {
			return new Splash(soundManager);
		}
	},

	LEVEL_SELECT {
		@Override
		public AbstractScreen getScreen(SoundManager soundManager, Object... params) {
			return new LevelSelectScreen(soundManager);
		}
	},

	GAME {
		@Override
		public AbstractScreen getScreen(SoundManager soundManager, Object... params) {
			return new SnakeScreen(soundManager, params);
		}
	},

	CONFIG {
		@Override
		public AbstractScreen getScreen(SoundManager soundManager, Object... params) {
			return new ConfigScreen(soundManager);
		}
	};

	public abstract AbstractScreen getScreen(SoundManager soundManager, Object... params);
}
