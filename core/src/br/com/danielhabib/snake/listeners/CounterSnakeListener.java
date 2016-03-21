package br.com.danielhabib.snake.listeners;

public class CounterSnakeListener extends SnakeListener {
	private int counter;

	public CounterSnakeListener(int counter) {
		this.counter = counter;
	}

	public void incrementCounter() {
		this.counter++;
	}

	public void decrementCounter() {
		this.counter--;
	}

	public int getCounter() {
		return counter;
	}
}
