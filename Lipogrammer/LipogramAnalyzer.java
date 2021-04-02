public class LipogramAnalyzer {


    private final String text;


    //    constructor to create an object using the input String text
    public LipogramAnalyzer(String text) {
        this.text = text;
    }


    //  Method to mark all offending letters with the character '#'
    public String mark(char letter) {
//        String s2 = text.replace(letter, '#');
//        return s2;
        return text.replace(letter, '#');
    }


    //    Method to create a list of all offending words
    public String allWordsWith(char letter) {
//        text = text.replace('#', letter);
        StringBuilder list = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == letter) {
                String placeHolder = extractWord(i);
                placeHolder = placeHolder.replaceAll("\\p{Punct}+", "");
                list.append(placeHolder);
            }
        }
//        String filteredList = filterList(list);
//        return filteredList;
        return filterList(list.toString());
    }

    //    method used to filter out duplicates from String list
    private String filterList(String unfilteredList) {
//        I learned how to use the .split() and regex methods from a course
//        taken previously.
        String[] words = unfilteredList.split("\\s");
        StringBuilder filteredList = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    if (i != j)
                        words[i] = "";
                }
            }
        }
        for (String word : words) {

            if (!word.equals("")) {
                filteredList.append(word);
                filteredList.append('\n');
            }
        }

        return filteredList.toString();
    }


    //    method used to extract offending words from the input string
    private String extractWord(int y) {
        int fSpace = 0;
        int bSpace = -1;
        for (int j = 0; fSpace == 0; j++) {
            if (0 <= (y + j) && (y + j) < text.length()) {
                if (text.charAt(y + j) == (' ')) {
                    fSpace = y + j;

                }
            } else {
                fSpace = text.length();
            }
        }
        for (int k = 0; bSpace == -1; k++) {
            if (0 <= (y - k) && (y - k) < text.length()) {
                if (text.charAt(y - k) == (' ')) {
                    bSpace = y - k;
                }
            } else {
                bSpace = 0;
            }
        }
//        String tempString = text.substring(bSpace, fSpace);
//        return tempString;
        return text.substring(bSpace, fSpace);

    }
}



