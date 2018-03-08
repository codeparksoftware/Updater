/**
 * 
 */
package external;

/*
 * @author selami
 *Observer pattern implementation.Observable class notify two things.
 *First notify current thread id to manage sub process
 *second notify work progress state  as percent value
 *
 */
public interface Observable {
	public void notifyServer(long val);

	public void add(Observer o);

	public void remove(Observer o);

	public void addThreadId(long val);

	public void removeThreadId(long val);

}
