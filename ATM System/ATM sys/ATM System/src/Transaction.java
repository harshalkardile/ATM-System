import java.util.Date;

public class Transaction {
	
	/**
	 * the amount of this transaction
	 */
	private double amount;
	/**
	 * the time and date of this transaction
	 */
	private Date timestamp;
	/**
	 * A memo of this transaction
	 */
	private String memo;
	/**
	 * The account in which tha transaction was performed 
	 */
	private Account inAccount;
	/**
	 * Create a new Transaction
	 * @param amount	the amount to be transacted
	 * @param inAccount	the account the transaction belong to 
	 */
	public Transaction(double amount, Account inAccount) { 
		
		this.amount=amount;
		this.inAccount=inAccount;
		this.timestamp=new Date();
		
		
	}
	/**
	 * Create a new transaction 
	 * @param amount	the amount to be transacted 
	 * @param memo		the for the transaction 
	 * @param inAccount	the account transaction belongs to
	 */
	public Transaction(double amount, String memo, Account inAccount) {
		this(amount, inAccount);
		this.memo=memo;
	}
	
	/**
	 * Get amount of the transaction 
	 * @return the amount
	 */
	public double getAmount() {
		return this.amount;
	}
	
	/**
	 * Get a string summarizing the transaction 
	 * @return	the summary string
	 */
	public String getSummaryLine() {
		
		if(this.amount >=0 ) {
			return String.format("%s : ₹%.02f: %s",this.timestamp.toString(),
					this.amount,this.memo);
		}else {
		return String.format("%s : ₹(%.02f): %s", this.timestamp.toString(),
				-this.amount,this.memo);
	 	}
	}
}