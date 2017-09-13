package bt;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ProcessInput {

	public static void main(String[] args) {
		AccountBook book = AccountBook.getInstance();
		Scanner scan = null;
		if(args.length != 0){
			try{
				scan = new Scanner(new File(args[0]));
				 while (scan.hasNextLine()){
		             String line = scan.nextLine();
		             book.processLine(line);
			     }
			}
			catch(FileNotFoundException e){
				System.out.println(e.getMessage());
			}
			finally{
				if(scan !=null){
					scan.close();
				}
			}
		}
		scan = new Scanner(System.in);
	    try {
	        while (scan.hasNextLine()){
	            String line = scan.nextLine();
	            book.processLine(line);
	        }

	    } finally {
	    	//print summary here;
	    	book.printSummary();	    	
	        if (scan != null)
	        scan.close();
	    }

	}

}
