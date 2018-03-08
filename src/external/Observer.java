
package external;

/**
 * @author Selami Observer pattern implementation.Observer class obeserves two
 *         things. First observes thread id to manage sub process second observes
 *         work progress state
 */
public interface Observer {
	public void update(int value);

	public void addThreadId(long val);

	public void removeThreadId(long val);

}
