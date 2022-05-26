package cn.han.util;

import java.io.Closeable;
import java.io.Flushable;

/**
 * @author binarii.dupp
 * @since 2016/03/31 17:48
 */
public class ResourceUtil {

	public static void release(Object releaseable) {
		if (releaseable == null)
			return;

		try {
			if (releaseable instanceof Flushable)
				((Flushable) releaseable).flush();

			if (releaseable instanceof Closeable)
				((Closeable) releaseable).close();
		} catch (Throwable t) {

		} finally {
			releaseable = null;
		}
	}

	public static void releaseAll(Object... releaseable) {
		for (Object res : releaseable)
			release(res);
	}

}
