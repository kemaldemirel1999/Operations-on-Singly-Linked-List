public class Execute 
{
    public Execute(){}
    
    public static void execute_operation(Operation op) throws InvalidOperationException
	{
		Integer newest_element1 = op.getParam1();
		Integer newest_element2 = op.getParam2();
		String temp_string = reduceString(op);
		switch(op.getName())
		{
			case "addFirst":
				Hw1Test.L.addFirst(newest_element1);	
				break;
			case "addLast":
				Hw1Test.L.addLast(newest_element1);
				break;
			case "addAfter":
				Hw1Test.L.addAfter(newest_element1,newest_element2);
				break;
			case "remove":
				temp_string = Hw1Test.L.remove(newest_element1, op, temp_string);
				break;
			case "undo":
				Hw1Test.S.undo(op);
				break;
			case "sort":
				Hw1Test.L.sort();
				break;
		}
		//Hw1Test.L.display();
		if(op.getName() == "undo")
		{
			return;
		}
		try
		{
			LimitedString newest_string = new LimitedString(temp_string);
			Hw1Test.S.push(newest_string);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
    /*	reduceString()	metodu cagirildigi zaman parametre olarak aldigi Operation nesnesindeki degerleri LimitedString icin kisaltir.
     * 	Kisaltmalari kodlamalar yaparak sagladim.
     * 	addFirst = A;  addLast = B;  addAfter = C; remove = D; sort = F;
     * */
    private static String reduceString(Operation op)
    {
    	String newest_string;
        String opName = op.getName(); 
        Integer param1 = op.getParam1();
        Integer param2 = op.getParam2();
        String param11 = "";
        String param22 = "";
		switch(op.getName())
		{
			case "addFirst":	opName = "A";		
				break;
			case "addLast": 	opName = "B";	
				break;
			case "addAfter":	opName = "C";	
				break;
			case "remove": 	opName = "D";	
				break;
			case "undo":	opName = "E";	
				break;
			case "sort":	opName = "F";	
				break;
		}
		if(param1 == null)	param11 = "N";
		else	param11 = param11 + param1;
		if(param2 == null)	param22 = "N";
		else	param22 = param22 + param2;
		newest_string = opName + param11+ "-"+ param22;
		if(opName == "D")	newest_string += "-";
		return newest_string;
    }
}
