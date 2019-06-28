
/** Classe Borrowing contenant toute les données et méthodes relatifs à nos emprunts **/
public class Borrowing {
	
	private String userFirstName;
	private String userLastName;
	private String bookReference;
	private String borrowingDate;
	private String returnDate;
	
	public Borrowing(String userfirstname, String userlastname, String bookreference, String borrowingdate) {
		this.userFirstName = userfirstname;
		this.userLastName = userlastname;
		this.bookReference = bookreference;
		this.borrowingDate = borrowingdate;
		this.returnDate = "";
	}
	
	public Borrowing(String userfirstname, String userlastname, String bookreference, String borrowingdate, String returndate) {
		this.userFirstName = userfirstname;
		this.userLastName = userlastname;
		this.bookReference = bookreference;
		this.borrowingDate = borrowingdate;
		this.returnDate = returndate;
	}

	/**
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * @param userLastName the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * @return the userFirstName
	 */
	public String getUserFirstName() {
		return userFirstName;
	}

	/**
	 * @param userFirstName the userFirstName to set
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	/**
	 * @return the bookReference
	 */
	public String getBookReference() {
		return bookReference;
	}

	/**
	 * @param bookReference the bookReference to set
	 */
	public void setBookReference(String bookReference) {
		this.bookReference = bookReference;
	}

	/**
	 * @return the borrowingDate
	 */
	public String getBorrowingDate() {
		return borrowingDate;
	}

	/**
	 * @param borrowingDate the borrowingDate to set
	 */
	public void setBorrowingDate(String borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	/**
	 * @return the returnDate
	 */
	public String getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	
}
