
/** Classe User contenant toute les données et méthodes relatifs à nos utilisateurs **/
public class User {
	
	private String firstName;
	private String lastName;
	private int dayOfBirth;
	private int monthOfBirth;
	private int yearOfBirth;
	
	public User(String firstname, String lastname, int daybirth, int monthbirth, int yearbirth) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.dayOfBirth = daybirth;
		this.monthOfBirth = monthbirth;
		this.yearOfBirth = yearbirth;
	}
	
	public User(String firstname, String lastname) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.dayOfBirth = 0;
		this.monthOfBirth = 0;
		this.yearOfBirth = 0;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dayOfBirth
	 */
	public int getDayOfBirth() {
		return this.dayOfBirth;
	}

	/**
	 * @param dayOfBirth the dayOfBirth to set
	 */
	public void setDayOfBirth(int dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	/**
	 * @return the monthOfBirth
	 */
	public int getMonthOfBirth() {
		return this.monthOfBirth;
	}

	/**
	 * @param monthOfBirth the monthOfBirth to set
	 */
	public void setMonthOfBirth(int monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}

	/**
	 * @return the yearOfBirth
	 */
	public int getYearOfBirth() {
		return this.yearOfBirth;
	}

	/**
	 * @param yearOfBirth the yearOfBirth to set
	 */
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
	/** Remplace les attributs de l'utilisateur par ceux en paramètre **/
	public void edit(String firstname, String lastname, int daybirth, int monthbirth, int yearbirth) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.dayOfBirth = daybirth;
		this.monthOfBirth = monthbirth;
		this.yearOfBirth = yearbirth;
	}

	/** Vérifis si l'utilisateur en paramètre est le même que cet objet **/
	public boolean equals(User user) {
		if(user.getFirstName().equals(this.getFirstName()) && user.getLastName().equals(this.getLastName())) {
			return true;
		}
		return false;
	}
}
