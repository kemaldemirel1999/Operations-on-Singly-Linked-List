public class Stack<E>
{
	// LimitedString de isimleri minimalize etmek icin isimleri boyle kodladim
	// addFirst = A;  addLast = B;  addAfter = C; remove = D; sort = F;
	// null = N;
	public static Stack<LinkedList<Integer>> K = new Stack<LinkedList<Integer>>();// Sort icin yaratilmis bir stackdir.Sortu undo yaptigimiz zaman listeyi eski haline cevirir.
	private static class SNode<E>
	{
		private SNode<E> next;
		private E value;
		public SNode(E e){ next= null;	value = e;}
		public SNode<E> getNext(){	return next;}
		public void setNext(SNode<E> n) {	next = n;}
		public E getValue() {	return value;}
		public void setValue(E value) {	this.value = value;}
	}
	
	private SNode<E> head;
	private int size = 0;
	public Stack()
	{
		head = new SNode<E>(null);
	}
	public int size() {	return size;}
	public boolean isEmpty() {	return size == 0; }	
	
	public void push(E e)
	{
		SNode<E> newest_node = new SNode<>(e);
		LinkedList<Integer> newest_list = new LinkedList<Integer>();
		if(isEmpty())
		{
			head.setNext(newest_node);
			size ++;
			return;
		}
		newest_node.setNext(head.getNext());
		head.setNext(newest_node);
		size ++;
	}
	public E top() throws InvalidOperationException
	{
		if(isEmpty())	throw new InvalidOperationException("Liste Bos");
		return head.getNext().getValue();
	}
	public E pop()	throws InvalidOperationException
	{
		if(isEmpty())	throw new InvalidOperationException("Liste Bos");
		E current_value = head.getNext().getValue();
		head.setNext(head.getNext().getNext());
		size --;
		return current_value;
	}
	/*	undo() metodu cagirildigi zaman stack'e attigimiz degeri pop() yaparak en son hangi islemi yaptigimizi bulmus oluruz.
	 * 	Daha sonra yaptigim kodlamaya gore gerekli metod cagirilarak geri alma islemi gerceklestirilmis olur.
	 * */
	// LimitedString de isimleri minimalize etmek icin isimleri boyle kodladim
	// addFirst = A;  addLast = B;  addAfter = C; remove = D; sort = F;
	// null = N;
	public void undo(Operation op) throws InvalidOperationException
	{
		if(isEmpty())	throw new InvalidOperationException("Invalid Operaion : undo null null");
		LimitedString opr = null;
		try 
		{
			LimitedString tmp = (LimitedString)pop();
			opr = new LimitedString(tmp.getElement());
		}
		catch(Exception e) {System.out.println("63 Exception ");}
		String operation = opr.getElement();
		Integer param1 = null, param2 = null;
		switch(operation.charAt(0))
		{
		case 'A':
			param1 = Integer.parseInt(operation.substring(1, operation.indexOf('-') ) );
			Hw1Test.L.addFirst_undo();//	Ilk bastaki elemani siler
			break;
		case 'B':
			param1 = Integer.parseInt(operation.substring(1, operation.indexOf('-')));
			Hw1Test.L.addLast_undo();//		En sondaki elemani siler
			break;
		case 'C':
			param1 = Integer.parseInt(operation.substring(1,operation.indexOf('-')));
			param2 = Integer.parseInt(operation.substring(operation.indexOf('-')+1 ) );
			Hw1Test.L.addAfter_undo(param1, param2); 	//	eklenen elemani siler
			break;
		case 'D':
			param1 = Integer.parseInt(operation.substring(1,operation.indexOf('-')));
			Integer index = Integer.parseInt(operation.substring(operation.length()-1));
			Hw1Test.L.remove_undo(param1, index);	//	Silmis oldugumuz elemani tekrar ekler
			break;
		case 'F':
			Hw1Test.L.sort_undo();	//	Siralama yaptigimiz tam sayi listesini eski haline dondurur.
			break;
		}
		
	}

	
}
