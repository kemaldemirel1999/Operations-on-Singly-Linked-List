public class LimitedString{
    private String S="";
    private final int size = 10;
    public LimitedString(String s) throws Exception {
        if (s.length() > size) {
            throw new Exception("Large Stack Element");
            //System.exit(0);
        }
        S = s;
    }
    public String getElement(){return S;}
}