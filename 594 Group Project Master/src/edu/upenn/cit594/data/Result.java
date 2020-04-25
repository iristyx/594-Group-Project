package edu.upenn.cit594.data;

public class Result<E> {

	protected E result;

	public Result(E result) {
		this.result = result;
	}

	public E getResult() {
		return result;
	}

	public void setResult(E result) {
		this.result = result;
	}
}
