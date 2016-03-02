package br.com.danielhabib.snake.game;

public enum ScreenEnum {

	MAIN_MENU {
		@Override
		public AbstractScreen getScreen(Object... params) {
			return new Splash();
		}
	},

	LEVEL_SELECT {
		@Override
		public AbstractScreen getScreen(Object... params) {
			return new LevelSelectScreen();
		}
	},

	GAME {
		@Override
		public AbstractScreen getScreen(Object... params) {
			// TODO: add level.
			return new SnakeScreen(params);
		}
	},

	CONFIG {
		@Override
		public AbstractScreen getScreen(Object... params) {
			return new ConfigScreen();
		}
	};

	public abstract AbstractScreen getScreen(Object... params);
}
