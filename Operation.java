public class Operation{
    private String opName; 
    private Integer param1 = null;
    private Integer param2 = null;

    public Operation(String n, Integer p1, Integer p2){
        opName = n;
        param1 = p1;
        param2 = p2;
    }

    public String getName(){return opName;}
    public Integer getParam1(){return param1;}
    public Integer getParam2(){return param2;}
    public String toString(){return opName+" "+param1+" "+param2;}
}