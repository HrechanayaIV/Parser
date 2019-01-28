package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


public class WordCountService {
    private String text;
    private String wordToFind;

    public WordCountService(String text, String wordToFind) {
        this.text = text;
        this.wordToFind = wordToFind;
    }

    public List<String> getWords(String text){
        List<String> words = new ArrayList<>();
        StringTokenizer token = new StringTokenizer(text);
        while (token.hasMoreTokens()){
            words.add(token.nextToken());
        }

        return words;
    }

    public Map<String, Integer> getFrequencyMap(List<String> words){

        Map<String, Integer> frequencyMap;
        frequencyMap = words.stream()
                .collect(Collectors.toMap(
                 s -> s,
                 s -> 1,
                 Integer::sum));
        return frequencyMap;
    }

}
