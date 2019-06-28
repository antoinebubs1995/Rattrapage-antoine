import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/** Classe library contenant toute les données et méthodes relatifs à notre bibliothèque **/
public class Library {
	
	/** Liste de tous les utilisateurs de la bibliothèque **/
	private List<User> usersList;
	/** Liste de tous les livres de la bibliothèque **/
	private List<Book> booksList;
	/** Liste de tous les emprunts de la bibliothèque **/
	private List<Borrowing> borrowsList;
	//HashMap<String, Borrowing> borrowsList = new HashMap<String, Borrowing>();
	
	public Library() {
		usersList = new ArrayList<User>();
		booksList = new ArrayList<Book>();
		borrowsList = new ArrayList<Borrowing>();
	}
	
	
	/** Renvois l'index de l'utilisateur en paramètre ou -1 si il n'existe pas **/
	public int getIndexUser(User user) {
		
		for(int i = 0; i < usersList.size(); i++) {
			if(usersList.get(i).equals(user)) {
				return i;
			}
		}
		return -1;
	}
	
	/** Ajoute un utilisateur à la liste **/
	public void addUser(User user) {
		usersList.add(user);
	}
	
	/** Renvoi l'utilisateur avec l'index en paramètre **/
	public User getUser(int index) {
		return usersList.get(index);
	}
	
	/** Supprime de la liste l'utilisateur avec l'index en paramètre **/
	public void removeUser(int index) {
		usersList.remove(index);
	}
	
	/** Renvoi un boolean disant si l'utilisateur en paramètre a un emprunt en cours **/
	public boolean userHasBorrow(User user) {
		String borrowerFirstName;
		String borrowerLastName;
		for(int i = 0; i < borrowsList.size(); i++) {
			borrowerFirstName = borrowsList.get(i).getUserFirstName();
			borrowerLastName = borrowsList.get(i).getUserLastName();
			if(user.equals(new User(borrowerFirstName, borrowerLastName)) && borrowsList.get(i).getReturnDate().length() == 0) {
				return true;
			}
		}
		return false;
	}
	
	/** Affiche la liste des utilisateurs trié par date de naissance **/
	public void displayUsersListSorted() {
		
		if(usersList.size() > 0) {
			Collections.sort(usersList, new Comparator<User>(){
				public int compare(User user1, User user2) {
					Integer annee1 = user1.getYearOfBirth();
					Integer annee2 = user2.getYearOfBirth();
					int result = annee1.compareTo(annee2);
					
					if(result == 0){
						Integer month1 = user1.getMonthOfBirth();
						Integer month2 = user2.getMonthOfBirth();
						result = month1.compareTo(month2);
						
						if(result == 0){
							Integer day1 = user1.getDayOfBirth();
							Integer day2 = user2.getDayOfBirth();
							result = day1.compareTo(day2);
							
							if(result == 0){
								return 1;
							}
						}
					}
					return result;
				}
			});
			
			Iterator<User> it = usersList.iterator();
			User displayUser;
			while(it.hasNext()) {
				displayUser = it.next();
				System.out.println(displayUser.getFirstName()+" "+displayUser.getLastName()+" "+displayUser.getDayOfBirth()+" "+displayUser.getMonthOfBirth()+" "+displayUser.getYearOfBirth());
			}
		} else {
			System.out.println("No registered users.");
		}

	}
	
	/** Renvois l'index du livre ayant la référence en paramètre ou -1 si il n'existe pas **/
	public int getIndexBook(String ref) {
		
		for(int i = 0; i < booksList.size(); i++) {
			if(booksList.get(i).getReference().equals(ref)) {
				return i;
			}
		}
		return -1;
	}
	
	/** Ajoute un livre à la liste **/
	public void addBook(Book book) {
		booksList.add(book);
	}
	
	/** Renvoi le livre avec l'index en paramètre **/
	public Book getBook(int index) {
		return booksList.get(index);
	}
	
	/** Supprime de la liste le livre avec l'index en paramètre **/
	public void removeBook(int index) {
		booksList.remove(index);
	}
	
	/** Renvoi un boolean disant si le livre en paramètre est actuellement emprunté **/
	public boolean bookHasBorrow(String ref) {
		String borrowerBookRef;
		for(int i = 0; i < borrowsList.size(); i++) {
			borrowerBookRef = borrowsList.get(i).getBookReference();
			if(ref.equals(borrowerBookRef) && borrowsList.get(i).getReturnDate().length() == 0) {
				return true;
			}
		}
		return false;
	}
	
	/** Affiche la liste des livres trié par référence **/
	public void displayBooksListSorted() {
		
		if(booksList.size() > 0) {
			Collections.sort(booksList, new Comparator<Book>(){
				public int compare(Book book1, Book book2) {
					String ref1 = book1.getReference();
					String ref2 = book2.getReference();
					int result = ref1.compareTo(ref2);
					
					if(result == 0){
						return 1;
					}
					return result;
				}
			});
			
			Iterator<Book> it = booksList.iterator();
			Book displayBook;
			while(it.hasNext()) {
				displayBook = it.next();
				System.out.println(displayBook.getTitle()+" "+displayBook.getReference()+" "+displayBook.getPublicationYear()+" "+displayBook.getEditorName());
			}
		} else {
			System.out.println("No registered books.");
		}

	}
	
	/** Ajoute un emprunt à la liste **/
	public void addBorrowing(Borrowing borrowing) {
		borrowsList.add(borrowing);
	}
	
	/** Renvoi l'emprunt avec l'index en paramètre **/
	public Borrowing getBorrowing(int index) {
		return borrowsList.get(index);
	}
	
	/** Renvois l'index de l'emprunt ayant la référence en paramètre ou -1 si il n'existe pas **/
	public int getIndexBorrow(String ref) {
		
		for(int i = 0; i < borrowsList.size(); i++) {
			if(borrowsList.get(i).getBookReference().equals(ref)) {
				return i;
			}
		}
		return -1;
	}
	
	/** Affiche la liste des emprunts trié par date **/
	public void displayBorrowsListSorted() {
		
		if(borrowsList.size() > 0) {
			Collections.sort(borrowsList, new Comparator<Borrowing>(){
				public int compare(Borrowing borrow1, Borrowing borrow2) {
					String[] date1 = borrow1.getBorrowingDate().split("-");
					String[] date2 = borrow2.getBorrowingDate().split("-");
					
					Integer annee1 = Integer.parseInt(date1[2]);
					Integer annee2 = Integer.parseInt(date2[2]);
					int result = annee1.compareTo(annee2);
					
					if(result == 0){
						Integer month1 = Integer.parseInt(date1[1]);
						Integer month2 = Integer.parseInt(date2[1]);
						result = month1.compareTo(month2);
						
						if(result == 0){
							Integer day1 = Integer.parseInt(date1[0]);
							Integer day2 = Integer.parseInt(date2[0]);
							result = day1.compareTo(day2);
							
							if(result == 0){
								return 1;
							}
						}
					}
					return result;
				}
			});
			
			Iterator<Borrowing> it = borrowsList.iterator();
			Borrowing displayBorrow;
			while(it.hasNext()) {
				displayBorrow = it.next();
				String returnDate = displayBorrow.getReturnDate();
				if(returnDate.length() != 0) {
					returnDate = ", return: "+returnDate;
				}
				int indexBook = getIndexBook(displayBorrow.getBookReference());
				System.out.println(displayBorrow.getUserFirstName()+" "+displayBorrow.getUserLastName()+", Titre: "+getBook(indexBook).getTitle()+", reference: "+displayBorrow.getBookReference()+", Borrowing date: "+displayBorrow.getBorrowingDate()+returnDate);
			}
		} else {
			System.out.println("No registered borrows.");
		}

	}
	
	/** Sauvegarde toute les données dans des fichiers **/
	public void save() {
		File f = new File("users.txt");
		try {
			FileWriter fw = new FileWriter(f);
			Iterator<User> it = usersList.iterator();
			User user;
			while(it.hasNext()) {
				user = it.next();
				fw.write(user.getFirstName()+" "+user.getLastName()+" "+user.getDayOfBirth()+" "+user.getMonthOfBirth()+" "+user.getYearOfBirth()+"\n");
				
				fw.flush();
			}
			fw.close();
		} catch(IOException e) {
			System.out.println("Error during save.");
		}
		
		f = new File("books.txt");
		try {
			FileWriter fw = new FileWriter(f);
			Iterator<Book> it = booksList.iterator();
			Book book;
			while(it.hasNext()) {
				book = it.next();
				fw.write(book.getTitle()+" "+book.getReference()+" "+book.getPublicationYear()+" "+book.getEditorName()+"\n");
				
				fw.flush();
			}
			fw.close();
		} catch(IOException e) {
			System.out.println("Error during save.");
		}
		
		f = new File("borrows.txt");
		try {
			FileWriter fw = new FileWriter(f);
			Iterator<Borrowing> it = borrowsList.iterator();
			Borrowing borrow;
			while(it.hasNext()) {
				borrow = it.next();
				fw.write(borrow.getUserFirstName()+" "+borrow.getUserLastName()+" "+borrow.getBookReference()+" "+borrow.getBorrowingDate()+" "+borrow.getReturnDate()+"\n");
				
				fw.flush();
			}
			fw.close();
		} catch(IOException e) {
			System.out.println("Error during save.");
		}
	}
	
	/** Restaure toutes les données sauvegardé **/
	public void restore() {
		try {
			File f = new File("users.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			try {
				usersList = new ArrayList<User>();
				String line = br.readLine();
				String[] lineSplit;
				while(line != null) {
					
					if(line != null) {
						lineSplit = line.split(" ");
						usersList.add(new User(lineSplit[0], lineSplit[1], Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4])));
					}
					line = br.readLine();
				}
				br.close();
				fr.close();
			} catch(IOException e) {
				System.out.println("Error during restore.");
			}
			
		} catch(FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
		try {
			File f = new File("books.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			try {
				booksList = new ArrayList<Book>();
				String line = br.readLine();
				String[] lineSplit;
				while(line != null) {
					if(line != null) {
						lineSplit = line.split(" ");
						booksList.add(new Book(lineSplit[0], lineSplit[1], Integer.parseInt(lineSplit[2]), lineSplit[3]));
					}
					line = br.readLine();
					
				}
				br.close();
				fr.close();
			} catch(IOException e) {
				System.out.println("Error during restore.");
			}
			
		} catch(FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
		try {
			File f = new File("borrows.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			try {
				borrowsList = new ArrayList<Borrowing>();
				String line = br.readLine();
				String[] lineSplit;
				while(line != null) {
					if(line != null) {
						lineSplit = line.split(" ");
						if(lineSplit.length == 4) {
							borrowsList.add(new Borrowing(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3]));
						} else {
							borrowsList.add(new Borrowing(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3], lineSplit[4]));
						}
					}
					line = br.readLine();
				}
				br.close();
				fr.close();
			} catch(IOException e) {
				System.out.println("Error during restore.");
			}
			
		} catch(FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
}
