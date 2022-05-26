package cn.han.msg.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ShutdownableThread extends Thread {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private AtomicBoolean isRunning = new AtomicBoolean(true);
	private AtomicBoolean paused = new AtomicBoolean(false);
	private CountDownLatch shutdownLatch = new CountDownLatch(1);
	private boolean isInterruptible = true;

	public ShutdownableThread() {
	}

	public ShutdownableThread(boolean isInterruptible) {
		this.isInterruptible = isInterruptible;
	}

	public void shutdown() {
		try {
			initiateShutdown();
			awaitShutdown();
		} catch (Exception e) {
			logger.error("Error due to ", e);
		}
	}

	private boolean initiateShutdown() {
		if (isRunning.compareAndSet(true, false)) {
			logger.info("Shutting down");
			isRunning.set(false);
			if (isInterruptible)
				interrupt();
			return true;
		}
		return false;
	}

	public synchronized void pauseThenInvoke(Runnable runnable) {
		if (paused.compareAndSet(false, true)) {
			paused.set(true);
			try {
				runnable.run();
			} catch (Throwable t) {
				logger.error(t.getMessage(), t);
			}
			paused.set(false);
		}
	}

	private void awaitShutdown() throws InterruptedException {
		shutdownLatch.await();
		logger.info("Shutdown completed");
	}

	protected abstract void doWork();

	@Override
	public void run() {
		logger.info("Starting ");
		try {
			while (isRunning.get()) {
				if (!paused.get())
					doWork();
			}
		} catch (Throwable e) {
			if (isRunning.get()) {
				logger.error("Error due to ", e);
			}
		}

		shutdownLatch.countDown();
		logger.info("Stopped");
	}

}
