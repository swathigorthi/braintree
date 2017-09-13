package bt;


public class Account {
   private final String name;
   private final String ccard;
   private final long limit;
   private long balance;
   private final boolean isValid;
   
   public Account(String name, String ccard, long limit){
	   this.name = name;
	   this.isValid = validateCreditCardNumber(ccard);// store if card is valid or not
	   this.ccard = ccard;
	   this.limit = limit;	  
	   this.balance = 0;
   }
   
   //Only getter present and the variable is marked final so no one can alter the value.
   public String getName() {
		return name;
	}
   
   //Only getter present. So no one can alter the balance value. Only charge and credit methods can alter this value.
   public long getBalance(){
	   return balance;
   }
   
   //Only getter present and the variable is marked final so no one can alter the value.
   public long getLimit(){
	   return limit;
   }
   
   public synchronized void charge(long charge){
	   //if card is not valid or charge is not positive, charge iss ignored
	   if(!isValid || charge <= 0){
		   return;
	   }
	   // charges that increase the balance over limit are ignored. 
	   if(this.balance + charge > limit){
		   return;
	   }
	   this.balance = this.balance + charge;
   }
   
   public synchronized void credit(long credit){
	 //if card is not valid or credit is not positive, credit is ignored
	   if(!isValid || credit <= 0){
		   return;
	   }
	   // it is okay to have negative balance;
	   this.balance = this.balance - credit;
   }
   
   public String getBalanceString(){
	   return isValid ? "$" + String.valueOf(balance) : "error";
   }
   
   public static boolean validateCreditCardNumber(String str) {       
       int[] ints = new int[str.length()];
       for(int i = 0;i< str.length(); i++){
           ints[i] = Integer.parseInt(str.substring(i, i+1));
       }
       for(int i = ints.length-2; i>=0; i=i-2){
           int j = ints[i];
           j = j*2;
           if(j>9){
               j = j%10 + 1;
           }
           ints[i]=j;
       }
       int sum=0;
       for(int i = 0;i< ints.length; i++){
           sum+=ints[i];
       }
       return sum != 0 && sum % 10 == 0;

   }

}
