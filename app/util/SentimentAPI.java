package util;

import java.util.Arrays;
import java.util.List;

/**
 * this class represents EmoijIcons Sentiments analysis methods
 * @author Hussein
 */
public class SentimentAPI {

	/**
	 * represents list of happy Emoij codes
	 * @return list happy Emoij codes 
	 * @author Hussein
	 */
	public static List<String> getHappyEmoij()
    {
    	return Arrays.asList("😆","😀","😃","😄","😅","😂","😉","😊","😍","😗","☺️","😳","😇","😎",
    						 "👌","👍","👌","👏",
    						 "❤","💕","💓","💖","💝","💗","💟","💋");
    }
	
	/**
	 * represents list of sad Emoij codes
	 * @return list sad Emoij codes 
	 * @author Hussein
	 */
    public static List<String> getSadEmoij()
    {
    	return Arrays.asList("😒","😬","😪","😴","😕","☹","️😦","😠","😓","😣","😰","😥","😢","😭","😓",
    			"👎","💔",
    			"😈");
    }
    /**
     * process list of video comment strings and return number of happy Emoij codes found
     * @param lst_comments list of video comments
     * @return number of happy Emoij codes
     * @author Hussein
     */
    public static int getHappyEmoijCount(List<String> lst_comments)
    {
    	return getHappyEmoij().stream().map(emoj->
		    	{
		    		Integer sub_count=lst_comments.stream()
		        	.map(com->
		        	{
		        		int index=0;
		        		int count=0;
		        		while(index != -1){
		                    index = com.indexOf(emoj, index);
		                    if (index != -1) 
		                    {
		                        index++;
		                        count++;
		                    }
		        		}
		        		return count;
		        	}).reduce(0,Integer::sum);
		    		return sub_count;
		    	}
    			).reduce(0,Integer::sum);
    }
    /**
     * process list of video comment strings and return string contains happy Emoij codes found
     * @param lst_comments list of video comments
     * @return string of happy Emoij codes found
     * @author Hussein
     */
    public static String getHappyEmoij(List<String> lst_comments)
    {
    	return getHappyEmoij().stream().map(emoj->
		    	{
		    		String  emojs=lst_comments.stream()
		        	.map(com->
		        	{
		        		int index=0;
		        		String subemoj="";
		        		while(index != -1){
		                    index = com.indexOf(emoj, index);
		                    if (index != -1) 
		                    {
		                    	subemoj+=emoj;
		                        index++;
		                    }
		        		}
		        		return subemoj;
		        	}).reduce("",String::concat);
		    		return emojs;
		    	}
    			).reduce("",String::concat);
    }
    /**
     * process list of video comment strings and return string contains sad Emoij codes found
     * @param lst_comments list of video comments
     * @return string of sad Emoij codes found
     * @author Hussein
     */
    public static String getSadEmoij(List<String> lst_comments)
    {
    	return getSadEmoij().stream().map(emoj->
		    	{
		    		String  emojs=lst_comments.stream()
		        	.map(com->
		        	{
		        		int index=0;
		        		String subemoj="";
		        		while(index != -1){
		                    index = com.indexOf(emoj, index);
		                    if (index != -1) 
		                    {
		                        subemoj+=emoj;
		                        index++;
		                    }
		        		}
		        		return subemoj;
		        	}).reduce("",String::concat);
		    		return emojs;
		    	}
    			).reduce("",String::concat);
    }
    
    /**
     * process list of video comment strings and return number of sad Emoij codes found
     * @param lst_comments list of video comments
     * @return number of sad Emoij codes
     * @author Hussein
     */
    public static int getSadEmoijCount(List<String> lst_comments)
    {
    	return getSadEmoij().stream().map(emoj->
		    	{
		    		Integer sub_count=lst_comments.stream()
		        	.map(com->
		        	{
		        		int index=0;
		        		int count=0;
		        		while(index != -1){
		                    index = com.indexOf(emoj, index);
		                    if (index != -1) 
		                    {
		                        index++;
		                        count++;
		                    }
		        		}
		        		return count;
		        	}).reduce(0,Integer::sum);
		    		return sub_count;
		    	}
    			).reduce(0,Integer::sum);
    }
    /**
     * process list of video comments and return 1 if 70% are happy, -1 70% are sad and 0 otherwise
     * @param lst_comments list of video comments
     * @return 1 if 70% are happy, -1 70% are sad and 0 otherwise
     * @author Hussein
     */
    public static int getCommentsSentiment(List<String> lst_comments)
    {
    	int happy_count=getHappyEmoijCount(lst_comments);
    	int sad_count=getSadEmoijCount(lst_comments);
    	float total=happy_count+sad_count;
    	return (happy_count/total)>0.7?1:(sad_count/total)>0.7?-1:0;
    }
    
    /**
     * main method to test sentiment Api
     * @param args list of arguments
     * @author Hussein
     */
    public static void main(String[] args)
    {
    	int sum=getCommentsSentiment(Arrays.asList("we 😆 are 😀happy", "they are 😀happy 😃","😃😃😃","👍",
    			"this is too boring 😒😒😒😒😒😒😒"," this is 😬","no 😪","😴😴😴 😴","😕😕","this bug ☹ 👎👎👎"));
    	
    	System.out.println("happy count="+sum);
    }
}
