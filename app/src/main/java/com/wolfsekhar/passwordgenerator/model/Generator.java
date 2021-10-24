package com.wolfsekhar.passwordgenerator.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Generator {
    private Random randomize = new Random();
    private final String[] smallAlphabets = {
      "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
    };
    private final String[] capitalAlphabets = {
      "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
    };
    private final String[] characters = {"!","@","#","$","%","^","&","*","(",")","{","}","[","]","<",">","?","/","|","~"};
    private final String[] numbers = {"0","1","2","3","4","5","6","7","8","9","0"};
    private List<List<String>> listsToGenerate;
    private final List<List<String>> listsAll = new ArrayList<List<String>>(){{
        add(Arrays.asList(smallAlphabets));
        add(Arrays.asList(capitalAlphabets));
        add(Arrays.asList(characters));
        add(Arrays.asList(numbers));
    }};
    public Generator(){

    }
    public String generatePassword(boolean isSmallAlphabets,
                            boolean isCapitalAlphabets,
                            boolean isCharacters,boolean isNumbers,int length){
        listsToGenerate = new ArrayList<>();
        List<Boolean> isIncluded = new ArrayList<Boolean>(){{
            add(isCapitalAlphabets);
            add(isSmallAlphabets);
            add(isCharacters);
            add(isNumbers);
        }};
        for (int i = 0; i < isIncluded.size(); i++) {
            if (isIncluded.get(i)){
                listsToGenerate.add(listsAll.get(i));
            }
        }
        return generate(length);
    }
    private String generate(int length){
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomCollection = randomizeCollections(listsToGenerate.size());
            int sizeOfTheSelectedList = listsToGenerate.get(randomCollection).size();
            password.append(listsToGenerate.get(randomCollection).get(randomize(sizeOfTheSelectedList)));
        }
        return password.toString();
    }
    private int randomizeCollections(int bound){
        return randomize.nextInt(bound);
    }
    private int randomize(int bound){
        return randomize.nextInt(bound);
    }
}
