public class Main{

    public static Stack<LimitedString> S = new Stack<LimitedString>();
    public static  LinkedList<Integer> L = new LinkedList<Integer>();
    public static void main(String[] args){
        RandomOp rop = new RandomOp();
        for (int i =0; i<Integer.parseInt(args[0]); i++) {
            Operation op = rop.next_op();
            System.out.println(op);
            try {
                Execute.execute_operation(op);  
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(L);
    }
}