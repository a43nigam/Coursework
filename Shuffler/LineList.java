import java.util.ArrayList;

public class LineList {

    private ArrayList<String> List  = new ArrayList<>();

    public LineList() {
        List.clear();
    }

    public int size() {
        return List.size();
    }

    public String get(int k) {
        return List.get(k);
    }

    public void add(String line) {
        List.add(line);
    }

    public String remove(int k) {
        String x = List.get(k);
        List.remove(k);
        return x;
    }

    public void move(int index, int newIndex) {
        String indexValue = List.get(index);
        if (newIndex >= index) {
            List.add(newIndex + 1, indexValue);
            List.remove(index);
        } else {
            List.add(newIndex, indexValue);
            List.remove(index + 1);
        }
    }

    public void shuffle() {
        List.trimToSize();
        int n = List.size();
        for (int i = n; i >= 2; i--) {
            int x = (int) Math.floor(Math.random() * i);
            System.out.println(x);
            String xValue = List.get(x);
            String nValue = List.get(n-1);
            List.remove(n-1);
            List.add(xValue);
            List.remove(x);
            List.add(x, nValue);
        }
    }

//    public void test() {
//        String xValue = List.get(2);
//        String nValue = List.get(5);
//        List.remove(5);
//        List.add(xValue);
//        List.remove(2);
//        List.add(2, nValue);
//    }

//    public static void main(String[] args) {
//        LineList x = new LineList();
//        for (int i = 0; i <= 5; i++) {
//            x.add("string " + i);
//        }
//
//        for (int i = 0; i <= 5; i++) {
//            System.out.println(x.get(i));
//        }
//
//        System.out.println("");
//        x.test();
//
//        for (int i = 0; i <= 5; i++) {
//            System.out.println(x.get(i));
//        }
//    }
}
