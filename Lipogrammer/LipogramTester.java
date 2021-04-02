public class LipogramTester {
    public static void main(String[] args) {

        String text = "I like to eat apple and bananas";
        char letter = 't';
        String list = "";
        int last = 0;

        for (int i = 0; i < text.length() - 1; i++) {
            String listPlaceholder = "";
            Character c = text.charAt(i);

            if (!Character.isLetter(c)) {
                listPlaceholder = text.substring(last, i);


                for (int j = 0; i < listPlaceholder.length() - 1;
                     i++) {
                    System.out.println(listPlaceholder);
                    char e = text.charAt(i);
                    if (e == letter) {
                        listPlaceholder = listPlaceholder.trim();
                        list += listPlaceholder + '\n';
                        j = listPlaceholder.length() + 5;
                    }
                }
                last = i;
            }

        }
        System.out.println(list);
    }
}


