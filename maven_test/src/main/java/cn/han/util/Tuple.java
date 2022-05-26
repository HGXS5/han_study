package cn.han.util;

public abstract class Tuple<L, R> {

	private final L left;

	private final R right;

	private Tuple(L left, R right) {
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}

	public R getRight() {
		return right;
	}

	public static <L, R> Tuple<L, R> of(L left, R right) {
		return new Tuple<L, R>(left, right) {
		};
	}

}
