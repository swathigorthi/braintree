package bt;


import java.util.Map;
import java.util.TreeMap;

public class AccountBook {
	private final static AccountBook accountBookInstance;
	
	static {
		accountBookInstance = new AccountBook();
	}
	
	private final Map<String, Account> accountMap = new TreeMap<>();
	
	private AccountBook(){	}
	
	// this object is made to be single instance, so we have only one map of accounts all the time. 
	public static AccountBook getInstance(){
		return accountBookInstance;
	}
	
	private void addAccount(String name, String ccard, long limit){
		synchronized (accountMap) {
			if(!accountMap.containsKey(name)){
				accountMap.put(name, new Account(name, ccard, limit));
			}
		}
	}
	
	private void chargeAccount(String name, long charge){
		accountMap.get(name).charge(charge);
	}
	
	private void creditAccount(String name, long credit){
		accountMap.get(name).credit(credit);
	}
	
	public void processLine(String inputLine){
		String str[] = inputLine.split(" ");
		long amount;
		switch (str[0]) {
		case "Add" :
			amount = Long.valueOf(str[3].substring(1));
			addAccount(str[1], str[2], amount);
			break;
		case "Charge" :
			amount = Long.valueOf(str[2].substring(1));
			chargeAccount(str[1], amount);
			break;
		case "Credit" : 
			amount = Long.valueOf(str[2].substring(1));
			creditAccount(str[1], amount);
			break;
		}			
	}
	
	public void printSummary(){
		for(String name : accountMap.keySet()){
    		System.out.println(name + " : " + accountMap.get(name).getBalanceString());
    	}
	}
}
