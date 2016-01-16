import java.util.Arrays;

public class LexoSorting {

    /*
        In this first solution, one can take any sorting algorithm and change the comparison method
        The time complexity of this depends on the sorting algorithm.
        In this case, I used insertion sort, so the base algo is n*n, but one can easily use quicksort which is n log n
        The time complexity of the compare function depends on the length of the element m

        therefore the time complexity of this sorting method is O(mn log n)
        where n is the number of elements to be sorted
        and m is the length of the elements being sorted

     */
    public void oneSort(String[] words, String order){
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            int j = i - 1;
            while ((j > -1) && (compare(words[j], word, order))) {
                words[j + 1] = words[j];
                j--;
            }
            words[j + 1] = word;
        }
    }

    public boolean compare(String a, String b, String order){
        if (a.equals(""))
            return false;
        if (b.equals(""))
            return true;
        for (int i = 0; i<a.length();i++){
            if (i == a.length()-1)
                return false;
            if (i == b.length()-1)
                return true;
            if (a.charAt(i) == b.charAt(i))
                continue;
            else{
                return order.indexOf(a.charAt(i)) > order.indexOf(b.charAt(i));
            }
        }
        return true;
    }
    /*
        In this solution, the speed can be slightly improved if the alphabet is shorter
        This solution scrambles each character of the string with the  lexographic order against the alphabet "a-z"
        i.e. if the order is cba, all the 'a' of each element swaps position with 'c'
        Then that array of unscrambled elements are sorted through Java Sort which is n log n
        In the end, the strings are scrambled through the lexographic order against the alphabet again
        where n is the number of elements to sort
        and m is the length of the individual string elements

        The time complexity is O(n*m + n log n + n*m) - n log n is the main sorting method, but it depends on the sorting algorithm
        This is equivalent to O(n log n) for lists with many many elements
     */
    public void anotherSort(String[] words, String order){

        String alpha = "abcdefghijklmnopqrstuvwxyz";
        String[] tempAr = new String[words.length];
        String temp = "";
        char ch;
        int index, i = 0;
        //swapping positions from the arbitrary lexo order against the alphabet
        for(String x : words){
            temp = "";
            for(int j = 0 ;j < x.length(); j++){
                ch = x.charAt(j);
                index = order.indexOf(ch);
                temp = temp + alpha.charAt(index);
            }
            tempAr[i] = temp;
            i++;
        }

        //sort the array
        Arrays.sort(tempAr);

        //swapping back
        for(String x : tempAr){
            temp = "";
            for(int j = 0 ;j < x.length(); j++){
                ch = x.charAt(j);
                index = alpha.indexOf(ch);
                temp = temp + order.charAt(index);
            }
        }
    }
    public static void main(String args[] ) throws Exception {

        LexoSorting ls = new LexoSorting();

        String[] s1 =  {"acb", "abc", "bca"};
        ls.oneSort(s1 , "abc");

        String[] s2 =  {"acb", "abc", "bca"};
        ls.oneSort(s2 , "cba");

        String[] s3 =  {"", "aaa", "aa"};
        ls.oneSort(s3 , "a");
    }
}