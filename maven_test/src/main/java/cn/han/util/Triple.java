package cn.han.util;

public abstract class Triple<T1, T2, T3> {

	private final T1 left;

	private final T2 middle;

	private final T3 right;

	private Triple(T1 _1, T2 _2, T3 _3) {
		this.left = _1;
		this.middle = _2;
		this.right = _3;
	}

	public T1 getLeft() {
		return left;
	}

	public T2 getMiddle() {
		return middle;
	}

	public T3 getRight() {
		return right;
	}

	public static <T1, T2, T3> Triple<T1, T2, T3> of(T1 left, T2 middle, T3 right) {
		return new Triple<T1, T2, T3>(left, middle, right) {
		};
	}

}
