import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class FileProcessor {

	WordWithCountList list = new WordWithCountList();
	Scanner fileScanner;


	public static void main(String[] args) {
		new FileProcessor().start();

	}

	private void start() {
		// prompt the user for the file to process.  This will be the contents
		// of a novel (text file).

		//System.out.println(standardize("!!!he!!ll..o?"));

		promptAndOpenFile();
		WordWithCountList list = new WordWithCountList();
		// The file will be scanned (using a scanner) inputting one token at a time
		// cleaning and standardizing the token, then adding the token to our 
		// WordWithCountList. 

		// we will use fileScanner methods hasNext() which tells us if there is more 
		// to be scanned from the file, and the    next() method which returns the latest
		// token and advances the file pointer.
		WordWithCount.setSortToAlpha();
		int lineNum = 0;
		while(fileScanner.hasNext()) {
			String line = fileScanner.next();
			if (!(standardize(line).equals(" (BLANK_SPACE)"))){
				WordWithCount w = new WordWithCount(standardize(line));
				list.add((w));
			}


			//System.out.println(++lineNum + ":"+line);
		}
		//WordWithCount.setSortToFreq();
		//list = list.sort();
		//WordWithCount.setSortToLength();
		//list= list.sort();
		for (int i = 0; i< list.size();i++){
			System.out.println(list.get(i));
		}
		System.out.println("Total Uniqe Words: "+list.size());
		System.out.println("Total Words: "+list.getTotalWords());
		System.out.println("Word Variance: "+ 100*(double)(list.size())/(double)(list.getTotalWords())+"%");
		fileScanner.close();

		//After the file is done being processed, the results 
		// will be displayed.  This will include displaying the words alphabetically, 
		// or by frequency or by length (depending on the user's choice).


	}

	/**
	 * Prompts the user for a file to be processed.  JFileChooser Objects are 
	 * GREAT for this!!  
	 * 
	 * After prompting for the file, this method assigns the class variable
	 * fileScanner to a new Scanner that points to the desired file.
	 */
	private void promptAndOpenFile() {
		// TODO Auto-generated method stub
		JFileChooser jfc = new JFileChooser("directory");
		jfc.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = jfc.showOpenDialog(jfc);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();

			if (selectedFile.exists()){
				try {
					fileScanner = new Scanner(selectedFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * This method lowercases the String.  
	 * This method cleans off any characters from the front and back of the 
	 * specified String.   Example:  if s = "Happy,", "happy" is returned
	 * @param s  The String to be standardized.  
	 * @return  the standardized String
	 */
	private String standardize(String s) {
		String result = s.toLowerCase().trim();
		String str = "";
		for (int i =0; i<result.length();i++){
			if ((result.charAt(i)<='z' && result.charAt(i)>='a')||(result.charAt(i)=='\'')){
				str+=result.charAt(i);
				//System.out.println(str);

			}
		}
		if (str.length()==0){
			return " (BLANK_SPACE)";
		}
		if ((str.charAt(0)=='\'')&&(str.charAt(str.length()-1)=='\'')){
			str = str.substring(1, str.length()-1);
		}
		//		if (result.charAt(0)>'z' || result.charAt(0)<'a'){
		//			result = result.substring(1, result.length());
		//			System.out.println(result);
		//		}
		return str;
	}


}
