public class Node<E>
{
	private Node<E> next;
	private Node<E> prev;
	private E value;
	
	public Node(E e,Node<E> p,Node<E> n)
	{
		value = e;
		prev = p;
		next = n;
	}
	public void setNext(Node<E> n)
	{
		next = n;
	}
	public Node<E> getNext()
	{
		return next;
	}
	public Node<E> getPrev()
	{
		return prev;
	}
	public void setPrev(Node<E> p)
	{
		prev = p;
	}
	public void setValue(E value)
	{
		this.value = value;
	}
	public E getValue()
	{
		return value;
	}
}
