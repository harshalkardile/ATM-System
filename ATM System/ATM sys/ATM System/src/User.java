import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	/**
	 * the first name of the user 
	 */
	private String firstName;     
	/**
	 * the last name of the user 
	 */
	private String lastName;
	/**
	 * the ID number of the user 
	 */
	private String uuid;
	/**
	 * the MD5 hash number of the user 
	 */
	private byte pinHash[];
	/**
	 * the list of the accounts for this user  
	 */
	private ArrayList <Account> accounts;
	
	/**
	 * 
	 * @param firstName	the users first name
	 * @param lastName	the users last name
	 * @param pin		the users account pin number 
	 * @param theBank	the bank object that the user is customer of
	 */
	public User(String firstName, String lastName,String pin, Bank theBank) {
		
		//set user's name 
		this.firstName = firstName;
		this.lastName = lastName;
		
		// store the pins MD5 hash rather than the original value, for 
		//security reasons
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		//get a new, unique universal ID for the user 
		this.uuid = theBank.getNewUserUUID(); 
		
		//create empty list of accounts
		this.accounts = new ArrayList<Account>();
		
		//print log message
		System.out.printf("New user %s %s with ID %s created.\n", firstName, lastName, this.uuid);
	}
	
	/**
	 * add an account for the user
	 * @param anAcct 
	 */
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	/**
	 * Return the users UUID
	 * @return
	 */
	public String getUUID() {
		return this.uuid;
	}
	public boolean validatePin(String aPin) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	
	/**
	 * Return the user's firstName
	 * @return the first name
	 */ 
	public String getFirstName() {
		return this.firstName;
	}
	
	public void printAccountSummary() {
		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		
		for(int a=0; a<this.accounts.size(); a++) {
			
			System.out.printf("	%d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}
	/**
	 * Get the number of accounts of the user 
	 * @return	the number of accounts
	 */
	public int numAccounts() {
		return this.accounts.size();
	}
	
	/**
	 *Print transaction history for a particular account 
	 * @param acctIdx	the index of the account to use
	 */
	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}
	
	/**
	 * Get the balance of particular account 
	 * @param acctIdx	the index of he account to use 
	 * @return			the balance of the account
	 */
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	
	/**
	 * Get the UUID of a particular account
	 * @param acctIdx	the index of the account to use
	 * @return			the UUID of the account
	 */
	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}
	
	/**
	 * Add a transaction to a particular account
	 * @param acctIdx	the index of the account	
	 * @param amount	the amount of the account
	 * @param memo		the memo of the account
	 */
	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
}

