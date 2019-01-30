package services;

import java.util.*;
import java.util.stream.Collectors;


public class WordCountService {

    public WordCountService() {
    }

    public List<String> getWords(String text) {
        List<String> words = new ArrayList<>();
        StringTokenizer token = new StringTokenizer(text);
        while (token.hasMoreTokens()) {
            words.add(token.nextToken().toLowerCase());
        }

        return words;
    }

    public Map<String, Integer> getFrequencyMap(List<String> textWords) {
        Map<String, Integer> frequencyMap;
        frequencyMap = textWords.stream()
                .collect(Collectors.toMap(
                        s -> s,
                        s -> 1,
                        Integer::sum));
        return frequencyMap;
    }

}
