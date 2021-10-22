public class PriorityNode {

	public PriorityNode next;
	private MyQueue queue;
	
	public PriorityNode() {
		queue = new MyQueue();
	}
	
	public MyQueue getQeueue() {
		return queue;
	}
	
}
