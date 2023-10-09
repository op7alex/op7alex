import java.io.*;
import java.util.*;

public class TextAnalyzer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the file path: ");

        String fileName = sc.nextLine();

        try {

            String text = readTextFromFile(fileName);

            int charCount = countCharacters(text);

            System.out.println("Number of characters: " + charCount);

            Map<Character, Integer> charFreqMap = calculateCharFrequency(text);

            List<Map.Entry<Character, Integer>> sortedCharFreqList = sortCharFrequency(charFreqMap);

            System.out.println("Letter frequency in descending order:");

            for (Map.Entry<Character, Integer> entry : sortedCharFreqList) {

                System.out.println(entry.getKey() + ": " + entry.getValue());

            }

            int wordCount = countWords(text);

            System.out.println("Number of words: " + wordCount);

            String[] words = text.split("\\s+");

            int shortestWordLength = findShortestWordLength(words);

            System.out.println("Shortest word length: " + shortestWordLength);

            int longestWordLength = findLongestWordLength(words);

            System.out.println("Longest word length: " + longestWordLength);

            Map<String, Integer> uniGramMap = generateNGram(text, 1);

            List<Map.Entry<String, Integer>> sortedUniGramList = sortNGramFrequency(uniGramMap);

            System.out.println("Most repeated uni-grams (single words):");

            for (int i = 0; i < 20 && i < sortedUniGramList.size(); i++) {

                Map.Entry<String, Integer> entry = sortedUniGramList.get(i);

                System.out.println(entry.getKey() + ": " + entry.getValue());

            }

            Map<String, Integer> biGramMap = generateNGram(text, 2);

            List<Map.Entry<String, Integer>> sortedBiGramList = sortNGramFrequency(biGramMap);

            System.out.println("Most repeated bi-grams (pairs of words):");

            for (int i = 0; i < 20 && i < sortedBiGramList.size(); i++) {

                Map.Entry<String, Integer> entry = sortedBiGramList.get(i);

                System.out.println(entry.getKey() + ": " + entry.getValue());

            }

        } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());

        }

    }

    public static String readTextFromFile(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        StringBuilder sb = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {

            sb.append(line);

            sb.append(" ");

        }

        reader.close();

        return sb.toString();

    }

    public static int countCharacters(String text) {

        return text.replaceAll("\\s+", "").length();

    }

    public static Map<Character, Integer> calculateCharFrequency(String text) {

        Map<Character, Integer> charFreqMap = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {

            char c = text.charAt(i);

            if (Character.isLetter(c)) {

                charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);

            }

        }

        return charFreqMap;

    }

    public static List<Map.Entry<Character, Integer>> sortCharFrequency(Map<Character, Integer> charFreqMap) {

        List<Map.Entry<Character, Integer>> charFreqList = new ArrayList<>(charFreqMap.entrySet());

        Collections.sort(charFreqList, (a, b) -> b.getValue() - a.getValue());

        return charFreqList;

    }

    public static int countWords(String text) {

        String[] words = text.split("\\s+");

        return words.length;

    }

    public static int findShortestWordLength(String[] words) {

        int shortestWordLength = Integer.MAX_VALUE;

        for (String word : words) {

            int length = word.length();

            if (length < shortestWordLength) {

                shortestWordLength = length;

            }

        }

        return shortestWordLength;

    }

    public static int findLongestWordLength(String[] words) {

        int longestWordLength = 0;

        for (String word : words) {

            int length = word.length();

            if (length > longestWordLength) {

                longestWordLength = length;

            }

        }

        return longestWordLength;

    }

    public static Map<String, Integer> generateNGram(String text, int n) {

        Map<String, Integer> nGramMap = new HashMap<>();

        String[] words = text.split("\\s+");

        for (int i = 0; i <= words.length - n; i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = i; j < i + n; j++) {

                sb.append(words[j]);

                sb.append(" ");

            }

            String nGram = sb.toString().trim();

            nGramMap.put(nGram, nGramMap.getOrDefault(nGram, 0) + 1);

        }

        return nGramMap;

    }

    public static List<Map.Entry<String, Integer>> sortNGramFrequency(Map<String, Integer> nGramMap) {

        List<Map.Entry<String, Integer>> nGramList = new ArrayList<>(nGramMap.entrySet());

        Collections.sort(nGramList, (a, b) -> b.getValue() - a.getValue());

        return nGramList;

    }
}
