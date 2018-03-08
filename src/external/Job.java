package external;

import java.util.ArrayList;
import java.util.List;

import common.Level;
 

public abstract class Job implements Observable, Runnable, IJob {

	protected Thread t;
	protected ArrayList<Observer> lst;
	protected Object MUTEX;
	private static final common.Logger logger = common.Logger.getLogger(Job.class.getName());

	public Job() {

		init();
	}

	public void percent(long currentValue, long totalMax) {

		notifyServer((currentValue * 100) / totalMax);

	}

	private void init() {

		this.t = new Thread(this);
		this.MUTEX = new Object();
		this.lst = new ArrayList<Observer>();
	}

	@Override
	public void start() {
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			logger.log(Level.Error, e.getMessage());
		}
	}

	@Override
	public abstract void doJob();

	@Override
	public void interrupt() {
		t.interrupt();

	}

	@Override
	public void finish() {
		lst.clear();

	}

	@Override
	public void add(Observer o) {
		if (o == null)
			throw new NullPointerException("Null Observer");
		synchronized (MUTEX) {
			if (!lst.contains(o))
				lst.add(o);
		}

	}

	@Override
	public void remove(Observer o) {
		synchronized (MUTEX) {
			lst.remove(o);
		}

	}

	public void addThreadId(long val) {

		List<Observer> list;
		synchronized (MUTEX) {
			list = new ArrayList<>(this.lst);
		}
		for (Observer o : list) {
			o.addThreadId(val);
		}
	}

	public void removeThreadId(long val) {

		List<Observer> list;
		synchronized (MUTEX) {
			list = new ArrayList<>(this.lst);
		}
		for (Observer o : list) {
			o.removeThreadId(val);
		}

	}

	@Override
	public void notifyServer(long val) {
		List<Observer> list;
		synchronized (MUTEX) {
			list = new ArrayList<>(this.lst);
		}
		for (Observer o : list) {
			o.update((int) val);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				logger.log(Level.Error, e.getMessage());
			}
		}

	}

	@Override
	public void run() {
		doJob();

	}

}
