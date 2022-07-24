public class LinkedList<E>
{
	private Node<E> head;
	private Node<E> tail;
	private int size = 0;
	
	public LinkedList()
	{
		head = new Node<>(null,null,null);
		tail = new Node<>(null,head,null);
		head.setNext(tail);
	}
	
	public int size() {	return size;}
	public boolean isEmpty() {	return size == 0; }
	public E first()
	{
		if(isEmpty())	return null;
		return head.getNext().getValue();
	}
	public E last()
	{
		if(isEmpty())	return null;
		return tail.getPrev().getValue();
	}
	public LinkedList<Integer> copy()
	{
		LinkedList<Integer> copy_list = new LinkedList<Integer>();
		if(isEmpty())
		{
			copy_list = Hw1Test.L;
			return copy_list;
		}
		
		Node<E>	iterator = head.getNext();
		while(iterator != tail && iterator!= null)
		{
			copy_list.addLast((Integer)iterator.getValue());
			iterator = iterator.getNext();
		}
		return copy_list;
	}
	/*	sort() Tam sayi listesini siraladigimiz metoddur.*/
	public void sort() throws InvalidOperationException
	{
		if(isEmpty() || size == 1)	throw new InvalidOperationException("Invalid operation : sort null null");
		LinkedList<Integer> copy_list = new LinkedList<Integer>();
		copy_list = copy();
		Stack.K.push(copy_list);
		LinkedList<Integer> newest_list = new LinkedList<Integer>();
		Integer[] values = new Integer[this.size];
		Node<E> iterator = head.getNext();
		for(int i=0; i<size(); i++)
		{
			if(iterator == null || iterator == tail)	break;
			values[i] = (Integer)iterator.getValue();
			iterator = iterator.getNext();
		}
		bubbleSort(values);
		for(int i=0; i<values.length; i++)
		{
			newest_list.addLast(values[i]);
		}
		Hw1Test.L = newest_list;
	}
	/*	bubbleSort() metodu  sort()	metoduna yardimci olarak olusturulmus bir metoddur.*/
	private void bubbleSort(Integer[] values)
	{
		for(int i=0; i<values.length-1; i++)
		{
			for(int j=0; j<values.length- i - 1; j++)
			{
				if( values[j] > values[j+1] )
				{
					Integer temp = values[j];
					values[j] = values[j+1];
					values[j+1] = temp;
				}				
			}
		}
	}
	public void addFirst(E e)
	{
		Node<E> newest_node = new Node<>(e,head,head.getNext());
		head.getNext().setPrev(newest_node);
		head.setNext(newest_node);
		size ++;
	}
	public void addLast(E e)
	{
		Node<E> newest_node = new Node<>(e,tail.getPrev(),tail);
		tail.getPrev().setNext(newest_node);
		tail.setPrev(newest_node);
		size ++;
	}
	/*listenin basindaki elemani siler yani undo yapar*/
	public void addFirst_undo() throws InvalidOperationException
	{
		if(isEmpty())	throw new InvalidOperationException("140 Invalid Operation : ");
		Node<E> iterator = head.getNext();
		LinkedList<Integer> newest_list = new LinkedList<Integer>();
		if(size == 1)
		{
			Hw1Test.L = newest_list;
		}
		iterator = iterator.getNext();
		while(iterator!=null && iterator!=tail)
		{
			newest_list.addLast((Integer)iterator.getValue());
			iterator = iterator.getNext();
		}
		Hw1Test.L = newest_list;
	}
	/*addLast_undo()	listenin en sonundaki elemani siler*/
	public void addLast_undo() throws InvalidOperationException
	{
		if(isEmpty())	throw new InvalidOperationException("140 Invalid Operation : ");
		Node<E> iterator = head.getNext();
		LinkedList<Integer> newest_list = new LinkedList<Integer>();
		int new_size = size() -1;
		for(int i=0; i<new_size && iterator!=null && iterator!=tail ; i++)
		{
			newest_list.addLast((Integer)iterator.getValue());
			iterator = iterator.getNext();
		}
		Hw1Test.L = newest_list;
	}
	/*addAfter_undo()	gerekli degeri siler*/
	public void addAfter_undo(E param1,E param2)	throws InvalidOperationException
	{
		if(isEmpty())	throw new InvalidOperationException(" 129 Invalid Operation : ");
		Node<E> iterator = head.getNext();
		LinkedList<Integer> newest_list = new LinkedList<Integer>();
		boolean is_it_okay = true;
		//System.out.print("AddAfterUndo param1:"+param1+" param2: "+param2);
		while(iterator!=null && iterator!=tail)
		{
			//System.out.print("AddAfterUndoVAlue:"+(Integer)iterator.getValue()+"  ");
			if(iterator.getValue().equals(param2) && is_it_okay && iterator.getNext().getValue().equals(param1))
			{
				newest_list.addLast((Integer)iterator.getValue());
				if(iterator.getNext().getNext() == null)	break;
				iterator = iterator.getNext().getNext();
				is_it_okay = false;
			}
			if(iterator == null || iterator == tail)
				break;
			newest_list.addLast((Integer)iterator.getValue());
			iterator = iterator.getNext();
		}
		if(!is_it_okay)
		Hw1Test.L = newest_list;
	}
	/*Silme islemini gerceklestirir remove metodu*/
	public String remove(Integer param1,Operation op,String newest_string)	throws InvalidOperationException
	{
		if(isEmpty())	throw new InvalidOperationException("Invalid Operation : " + op.toString());
		Node<E> iterator = head.getNext();
		Node<E>	index_iterator = head.getNext();
		int index;
		for(index =0; index_iterator!=null && index_iterator!=tail; index++)
		{
			if(index_iterator == null || index_iterator == tail)	break;
			if(index_iterator.getValue().equals(param1))
			{
				newest_string = newest_string + index;
				break;
			}
			index_iterator = index_iterator.getNext();
		}
		while(iterator!=null && iterator!=tail)
		{
			if(iterator.getValue().equals(param1))
			{
				LinkedList<Integer> newest_list = new LinkedList<Integer>();
				iterator = head.getNext();
				boolean you_can_add = true;
				while(iterator!=null && iterator!=tail)
				{
					if(you_can_add && iterator!=null && iterator.getValue().equals(param1))
					{
						iterator = iterator.getNext();
						you_can_add = false;
						continue;
					}
					newest_list.addLast((Integer)iterator.getValue());
					iterator = iterator.getNext();
				}
				size --;
				Hw1Test.L = newest_list;
				return newest_string;
			}
			iterator = iterator.getNext();
		}
		throw new InvalidOperationException("Invalid Operation : " + op.toString());
	}
	/*ekleme islemini gerceklestirir addAfter methodu*/
	public void addAfter(Integer param1, Integer param2)	throws InvalidOperationException
	{
		if(isEmpty())	throw new InvalidOperationException("Invalid Operation : addAfter "+ param1 +" " + param2 );
		Node<E> iterator = head.getNext();
		while(iterator!=null && iterator!= tail)
		{
			if(iterator == null || iterator == tail)	break;
			if(iterator.getValue().equals(param2))
			{
				LinkedList<Integer> newest_list = new LinkedList<Integer>();
				iterator = head.getNext();
				boolean you_can_add = true;
				while(iterator!=null && iterator!=tail)
				{
					if(you_can_add && iterator!=null && iterator.getValue().equals(param2))
					{
						newest_list.addLast((Integer)iterator.getValue());
						newest_list.addLast(param1);
						iterator = iterator.getNext();
						you_can_add = false;
						continue;
					}
					newest_list.addLast((Integer)iterator.getValue());
					iterator = iterator.getNext();
				}
				size ++;
				Hw1Test.L = newest_list;
				return;
			}
			iterator = iterator.getNext();
		}
		throw new InvalidOperationException("Invalid Operation : addAfter "+ param1 +" " + param2 );
	}
	/*remove isleminin tersini yapar yani remove_undo metodu listeye degeri tekrar ekler*/
	public void remove_undo(Integer param1, Integer Index)	throws InvalidOperationException
	{
		int index = (int)Index;
		int counter;
		LinkedList<Integer> newest_list = new LinkedList<Integer>();
		if(isEmpty())
		{
			newest_list.addFirst(param1);
			Hw1Test.L = newest_list;
			return;
		}
		Node<E> iterator = head.getNext();
		boolean you_can_add = true;
		for(counter =0; ; counter++)
		{
			if(counter == index && you_can_add)
			{
				newest_list.addLast(param1);
				you_can_add = false;
			}
			if(iterator == null || iterator == tail)	break;
			newest_list.addLast((Integer)iterator.getValue());
			iterator = iterator.getNext();
		}
		size ++;
		Hw1Test.L = newest_list;
	}
	/*sort yaptigimiz listeyi eski haline cevirir yani undo yapmis olur*/
	public void sort_undo()	throws InvalidOperationException
	{
		if(isEmpty() || size == 1)	throw new InvalidOperationException("Invalid Operation : sort null null");
		if(!Stack.K.isEmpty())
		{
			LinkedList<Integer> newest_list = Stack.K.pop();
			Hw1Test.L = newest_list;
			return;
		}
		throw new InvalidOperationException("Invalid Operation : sort null null");
	}
	public String toString()
	{
		String current = "";
		if(isEmpty())	return current;
		Node<E> iterator = head.getNext();
		while( iterator!= tail )
		{
			current = current  + iterator.getValue() + " ";
			iterator = iterator.getNext();
		}
		return current;
	}
	public void display()// Hata olup olmadigini anlamak icin kullandim.
	{
		System.out.print("LinkedList output: ");
		Node<E> iterator = head.getNext();
		while(iterator!=tail && iterator != null)
		{
			System.out.print(iterator.getValue()+"  ");
			iterator = iterator.getNext();
		}
		System.out.println();
	}
	
}
