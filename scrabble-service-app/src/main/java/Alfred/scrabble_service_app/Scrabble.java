package Alfred.scrabble_service_app;

import java.util.ArrayList;

public class Scrabble {

	private final String letterList;
	private ArrayList<String> possibleWords;
	private OxDictionaryServiceConnector dictionaryService;
	
	public Scrabble(String letterList){
		this.letterList = letterList;
		this.possibleWords = new ArrayList<String>();
		this.dictionaryService = new OxDictionaryServiceConnector();
	}
	
	public String getWord(){
		
		 String Letters = searchLetters(letterList);
		 permute("",Letters);

		 for(String word : possibleWords){
			 if(dictionaryService.searchWord(word)) return word;
		 }
		 
		 return "Invalid input!"; 
	}
	
	private String searchLetters(String letterList){
	   String letters = "";
	   int size = letterList.length();
	   for (int i = 0; i < size ; i++) {
		  if(Character.isLetter(letterList.charAt(i))){
			  letters += (String.valueOf(letterList.charAt(i)));
		  }
	   }
	   return letters;
	}
	
	private void permute(String prefix, String letters) {
	    int n = letters.length();
	    if (n == 0 && !possibleWords.contains(prefix)){ 
	    	possibleWords.add(prefix);
	    }
	    else {
	        for (int i = 0; i < n; i++){
	        	if(!prefix.isEmpty() && !possibleWords.contains(prefix)){
	        		possibleWords.add(prefix);
	        	}
	            permute(prefix + letters.charAt(i), letters.substring(0, i) + letters.substring(i+1, n));
	        }
	    }
	}
	
}