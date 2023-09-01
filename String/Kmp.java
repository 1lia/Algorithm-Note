import java.util.ArrayList;

class Kmp{
    public ArrayList<Integer> run(String all , String s){
        ArrayList<Integer> res = new ArrayList<>();
        int allSize = all.length();
        int size = s.length();
        int[] table = makeTable(s);

        for (int i = 0 , j = 0 ; i < allSize; i++) {
            while(j > 0 && all.charAt(i) != s.charAt(j)) {
                j = table[j-1];
            }
            if(all.charAt(i) == s.charAt(j)) {
                if(j == size - 1) {
                    res.add(i - size + 2);
                    j = table[j];
                }	else {
                    j++;
                }
            }
        }

        return res;
    }

    public static int[] makeTable(String s) {
        int[] table = new int[s.length()];
        for (int i = 1 , j = 0; i < s.length(); i++) {
            while(j > 0 && s.charAt(i) != s.charAt(j)) {
                j = table[j-1];
            }

            if(s.charAt(i) == s.charAt(j)) {
                table[i] = ++j;
            }
        }
        return table;
    }
}