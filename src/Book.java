
/** Classe Book contenant toute les données et méthodes relatifs à nos livres **/
public class Book {
	
	private String title;
	private String reference;
	private int publicationYear;
	private String editorName;
	
	public Book(String titl, String ref, int publicationyear, String editorname) {
		this.title = titl;
		this.reference = ref;
		this.publicationYear = publicationyear;
		this.editorName = editorname;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the publicationYear
	 */
	public int getPublicationYear() {
		return publicationYear;
	}

	/**
	 * @param publicationYear the publicationYear to set
	 */
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	/**
	 * @return the editorName
	 */
	public String getEditorName() {
		return editorName;
	}

	/**
	 * @param editorName the editorName to set
	 */
	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}
	
	/** Permet d'éditer le livre **/
	public void edit(String titl, String ref, int publicationyear, String editorname) {
		this.title = titl;
		this.reference = ref;
		this.publicationYear = publicationyear;
		this.editorName = editorname;
	}
}
