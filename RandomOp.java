import java.util.Random;
import java.util.ArrayList;

public class RandomOp{

    private String[] ops = {"addFirst", "addLast", "addAfter", "remove", "undo", "sort"};
    private ArrayList<Integer> insertedElements = new ArrayList<Integer>();
    private int size=0;
    private final int MAX_ELEMENT=1000;
    private Random random = new Random();

    //returns a random operation
    public Operation next_op(){
        int opindex = random.nextInt(ops.length);
        Integer p1 = null;
        Integer p2 = null;
        switch (ops[opindex]){
            case "addFirst":
                p1 = random.nextInt(MAX_ELEMENT);
                insertedElements.add(p1);
                size++;
                break;
            case "addLast":
                p1 = random.nextInt(MAX_ELEMENT);
                insertedElements.add(p1);
                size++;
                break;
            case "addAfter":
                p1 = random.nextInt(MAX_ELEMENT);
                if (size > 0) {
                    p2 = insertedElements.get(random.nextInt(insertedElements.size()));
                    insertedElements.add(p1);
                    size++;
                } else
                    p2 = random.nextInt(MAX_ELEMENT);
                break;
            case "remove":
                if (size > 0) {
                    p1 = insertedElements.get(random.nextInt(insertedElements.size()));
                    insertedElements.remove(p1);
                    size--;
                }
                else
                    p1 = random.nextInt(MAX_ELEMENT);
        }        
        return new Operation(ops[opindex], p1, p2);
    }
}
