import java.util.*;
public class CCC14 {
    //PROBLEM 1
    void S1(int size, int [] removeInterval) {
        ArrayList<Integer> invites = fillList(10);
        for(int removal : removeInterval) {
            System.out.println("REMOVAL TYPE: " + removal);
            for(int i = removal-1; i < invites.size(); i += removal) {
                System.out.println("removed index: " + i + ", removed element: " + invites.get(i));
                invites.remove(i);
                i--;
            }
        }

        System.out.println(invites);
    }

    ArrayList<Integer> fillList(int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        for(int i = 1; i <= size; i++) {
            ret.add(i-1, i);
        }

        return ret;
    }
    //PROBLEM 1 end

    //PROBLEM 2
    boolean S2(String line1, String line2) {
        String [] names1 = line1.trim().split(" ");
        String [] names2 = line2.trim().split(" ");
        List<String> list1 = Arrays.asList(names1);
        List<String> list2 = Arrays.asList(names2);
        ArrayList<S2_Helper> partners = new ArrayList<>();
        for(int i = 0; i < list1.size(); i++) {
            partners.add(i, new S2_Helper(list1.get(i), list2.get(i)));
        }

        for(int i = 0; i < partners.size(); i++) {
            for(int j = i+1; j < partners.size(); j++) {
                S2_Helper pair1 = partners.get(i);
                S2_Helper pair2 = partners.get(j);
                if(pair1.name1.equals(pair2.name1) && !pair1.name2.equals(pair2.name2)) {
                    return false;
                    //System.out.println("bad");
                } else if(pair1.name1.equals(pair2.name2) && !pair1.name2.equals(pair2.name1)) {
                    return false;
                    //System.out.println("bad");
                }
            }
        }

        return true;
    }
    //PROBLEM 2 end

    public static void main(String[]args) {
        CCC14 problems = new CCC14();
        problems.S1(10, new int [] {2,3});
        System.out.println(
            problems.S2("Rich Graeme Michelle Sandy Vlado Ron Jacob", "Ron Vlado Sandy Michelle Rich Graeme Jacob")?"good":"bad");
    }
}

class S2_Helper {
    String name1, name2;

    S2_Helper(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }
}
