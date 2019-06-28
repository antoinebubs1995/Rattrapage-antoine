import java.util.Scanner;

/** Contrôle la lecture et l'execution des commandes **/
public class CommandsManager {
	
	/** Variable boolean qui détermine si l'on doit quitter le programme (true) ou pas (false) **/
	private boolean exit;
	/** Objet library contenant toute les données et méthodes relatifs à notre bibliothèque **/
	private Library library;
	
	public CommandsManager() {
		exit = false;
		library = new Library();
		startReadInput();
	}
	
	/** Démarre la lecture des entrées utilisateur **/
	private void startReadInput() {
		
		Scanner sc = new Scanner(System.in);
		String line;
		
		System.out.println("Welcome to the library manager.");
		while(!exit) {
			System.out.println("Please enter a command :");
			line = sc.nextLine();
			findCommand(line);
		}

	}
	
	/** Détermine quelle commande a été entrée puis lance son execution **/
	private void findCommand(String line) {
		
		String[] splitLine;
		
		splitLine = line.toLowerCase().split(" ");
		switch(splitLine[0]) {
			case "help":
				help(splitLine);
				break;
			case "exit":
				exit(splitLine);
				break;
			case "adduser":
				addUser(splitLine);
				break;
			case "edituser":
				editUser(splitLine);
				break;
			case "removeuser":
				removeUser(splitLine);
				break;
			case "listusers":
				listUsers(splitLine);
				break;
			case "addbook":
				addBook(splitLine);
				break;
			case "editbook":
				editBook(splitLine);
				break;
			case "removebook":
				removeBook(splitLine);
				break;
			case "listbooks":
				listBooks(splitLine);
				break;
			case "borrowbook":
				borrowBook(splitLine);
				break;
			case "returnbook":
				returnBook(splitLine);
				break;
			case "listborrow":
				listBorrow(splitLine);
				break;
			case "save":
				save(splitLine);
				break;
			case "restore":
				restore(splitLine);
				break;
			default:
				System.out.println("Unknow command, you can do 'help' to get the commands list.");
		}
	}
	
	/** Execution commande help **/
	private void help(String[] args) {
		if(args.length != 1) {
			System.out.println("Invalid number of arguments.");
		} else {
			System.out.println("Commands list :");
			System.out.println("- exit");
			System.out.println("- adduser <firstname> <lastname> <day> <month> <year> ");
			System.out.println("- edituser <firstname> <lastname>");
			System.out.println("- removeuser <firstname> <lastname>");
			System.out.println("- listusers");
			System.out.println("- addbook <title> <ref> <year> <publisher>");
			System.out.println("- editbook <ref>");
			System.out.println("- removebook <ref>");
			System.out.println("- listbooks");
			System.out.println("- borrowbook <firstname> <lastname> <ref>");
			System.out.println("- returnbook <ref>");
			System.out.println("- listborrow");
			System.out.println("- save");
			System.out.println("- restore");
		}
	}
	
	/** Execution commande exit **/
	private void exit(String[] args) {
		if(args.length != 1) {
			System.out.println("Invalid number of arguments.");
		} else {
			exit = true;
		}
	}
	
	/** Execution commande addUser **/
	private void addUser(String[] args) {
		
		if(args.length != 6) {
			System.out.println("Invalid number of arguments.");
		} else {
			
			try {
				int dayOfBirth = Integer.parseInt(args[3]);
				int monthOfBirth = Integer.parseInt(args[4]);
				int yearOfBirth = Integer.parseInt(args[5]);
				
				User user = new User(args[1], args[2], dayOfBirth, monthOfBirth, yearOfBirth);
				
				if(dayOfBirth < 1 || dayOfBirth > 31) {
					System.out.println("Invalid day of birth.");
				} else if(monthOfBirth < 1 || monthOfBirth > 12) {
					System.out.println("Invalid month of birth.");
				} else if(library.getIndexUser(user) != -1) {
					System.out.println("Already registered user.");
				} else {
					library.addUser(user);
				}
				
			} catch (NumberFormatException e){
				System.out.println("The last three parameters must be integers.");
			}
		}
	}
	
	/** Execution commande editUser **/
	private void editUser(String[] args) {

		if(args.length != 3) {
			System.out.println("Invalid number of arguments.");
		} else {

			int indexUser = library.getIndexUser(new User(args[1], args[2]));
			
			if(indexUser == -1) {
				System.out.println("Unregistered user.");
			} else {
				Scanner sc = new Scanner(System.in);
				System.out.println("Please enter the new first name.");
				String firstname = sc.nextLine();
				System.out.println("Please enter the new last name.");
				String lastname = sc.nextLine();
				System.out.println("Please enter the new day of birth.");
				String daybirth = sc.nextLine();
				System.out.println("Please enter the new month of birth.");
				String monthbirth = sc.nextLine();
				System.out.println("Please enter the new year of birth.");
				String yearbirth = sc.nextLine();
				
				boolean changeFirstName = true;
				if(firstname.length() == 0) {
					firstname = library.getUser(indexUser).getFirstName();
					changeFirstName = false;
				}
				boolean changeLastName = true;
				if(lastname.length() == 0) {
					lastname = library.getUser(indexUser).getLastName();
					changeLastName = false;
				}
				
				try {
					int dayOfBirth;
					int monthOfBirth;
					int yearOfBirth;
					
					if(daybirth.length() == 0) {
						dayOfBirth = library.getUser(indexUser).getDayOfBirth();
					} else {
						dayOfBirth = Integer.parseInt(daybirth);
					}
					if(monthbirth.length() == 0) {
						monthOfBirth = library.getUser(indexUser).getMonthOfBirth();
					} else {
						monthOfBirth = Integer.parseInt(monthbirth);
					}
					if(yearbirth.length() == 0) {
						yearOfBirth = library.getUser(indexUser).getYearOfBirth();
					} else {
						yearOfBirth = Integer.parseInt(yearbirth);
					}
					
					if(dayOfBirth < 1 || dayOfBirth > 31) {
						System.out.println("Invalid day of birth.");
					} else if(monthOfBirth < 1 || monthOfBirth > 12) {
						System.out.println("Invalid month of birth.");
					} else if(library.getIndexUser(new User(firstname, lastname)) != -1 && (changeFirstName || changeLastName)) {
						System.out.println("The new name already exist");
					} else {
						library.getUser(indexUser).edit(firstname, lastname, dayOfBirth, monthOfBirth, yearOfBirth);
					}
					
				} catch (NumberFormatException e){
					System.out.println("The last three inputs must be integers.");
				}
			}
				
		}
	}
	
	/** Execution commande removeUser **/
	private void removeUser(String[] args) {
		if(args.length != 3) {
			System.out.println("Invalid number of arguments.");
		} else {
			
			User user = new User(args[1], args[2]);
			int indexUser = library.getIndexUser(user);
			
			if(indexUser == -1) {
				System.out.println("Unregistered user.");
			} else {
				if(library.userHasBorrow(user)) {
					System.out.println("This user has borrow(s) in progress.");
				} else {
					library.removeUser(indexUser);
					System.out.println("User successfully removed.");
				}
			}
		}
	}
	
	/** Execution commande listUser **/
	private void listUsers(String[] args) {
		if(args.length != 1) {
			System.out.println("Invalid number of arguments.");
		} else {
			library.displayUsersListSorted();
		}
	}
	
	/** Execution commande addBook **/
	private void addBook(String[] args) {
		if(args.length != 5) {
			System.out.println("Invalid number of arguments.");
		} else {
			try {
				int publicationYear = Integer.parseInt(args[3]);
				
				if(library.getIndexBook(args[2]) != -1) {
					System.out.println("Already registered book.");
				} else {
					Book book = new Book(args[1], args[2], publicationYear, args[4]);
					library.addBook(book);
				}
				
			} catch (NumberFormatException e){
				System.out.println("The year parameter must be integers.");
			}
		}
	}
	
	/** Execution commande editBook **/
	private void editBook(String[] args) {
		if(args.length != 2) {
			System.out.println("Invalid number of arguments.");
		} else {

			int indexBook = library.getIndexBook(args[1]);
			
			if(indexBook == -1) {
				System.out.println("Unregistered book.");
			} else {
				Scanner sc = new Scanner(System.in);
				System.out.println("Please enter the new title.");
				String title = sc.nextLine();
				System.out.println("Please enter the new reference.");
				String ref = sc.nextLine();
				System.out.println("Please enter the new publication year.");
				String year = sc.nextLine();
				System.out.println("Please enter the new editor name.");
				String editor = sc.nextLine();
				
				boolean changeRef = true;
				if(title.length() == 0) {
					title = library.getBook(indexBook).getTitle();
				}
				if(ref.length() == 0) {
					ref = library.getBook(indexBook).getReference();
					changeRef = false;
				}
				if(editor.length() == 0) {
					editor = library.getBook(indexBook).getEditorName();
				}
				
				try {
					int publishYear;
					
					if(year.length() == 0) {
						publishYear = library.getBook(indexBook).getPublicationYear();
					} else {
						publishYear = Integer.parseInt(year);
					}
					
					if(library.getIndexBook(ref) != -1 && changeRef) {
						System.out.println("The new reference already exist");
					} else {
						library.getBook(indexBook).edit(title, ref, publishYear, editor);
					}
					
				} catch (NumberFormatException e){
					System.out.println("The year parameter must be integers.");
				}
			}
				
		}
	}
	
	/** Execution commande removeBook **/
	private void removeBook(String[] args) {
		if(args.length != 2) {
			System.out.println("Invalid number of arguments.");
		} else {
			
			int indexBook = library.getIndexBook(args[1]);
			
			if(indexBook == -1) {
				System.out.println("Unregistered book.");
			} else {
				if(library.bookHasBorrow(args[1])) {
					System.out.println("This book is currently borrowed.");
				} else {
					library.removeBook(indexBook);
					System.out.println("Book successfully removed.");
				}
			}
		}
	}
	
	/** Execution commande listBook **/
	private void listBooks(String[] args) {
		if(args.length != 1) {
			System.out.println("Invalid number of arguments.");
		} else {
			library.displayBooksListSorted();
		}
	}
	
	/** Execution commande borrowBook **/
	private void borrowBook(String[] args) {
		if(args.length != 4) {
			System.out.println("Invalid number of arguments.");
		} else {
			int indexUser = library.getIndexUser(new User(args[1], args[2]));
			int indexBook = library.getIndexBook(args[3]);
			
			if(indexBook == -1) {
				System.out.println("Unregistered book.");
			} else if(indexUser == -1) {
				System.out.println("Unregistered user.");
			} else if(library.bookHasBorrow(args[3])) {
				System.out.println("This book is currently borrowed.");
			} else {
				int randomYear = 2015 + (int)(Math.random() * ((2018 - 2015) + 1));
				int randomMonth = 1 + (int)(Math.random() * ((12 - 1) + 1));
				int randomDay = 1 + (int)(Math.random() * ((31 - 1) + 1));
				String date = randomDay + "-" + randomMonth + "-" + randomYear;
				Borrowing borrowing = new Borrowing(args[1], args[2], args[3], date);
				library.addBorrowing(borrowing);
				System.out.println(args[1]+" "+args[2]+" borrowed book "+args[3]+" on "+date);
			}
		}
	}
	
	/** Execution commande returnBook **/
	private void returnBook(String[] args) {
		if(args.length != 2) {
			System.out.println("Invalid number of arguments.");
		} else {
			int indexBorrow = library.getIndexBorrow(args[1]);
			if(indexBorrow == -1) {
				System.out.println("Unregistered book.");
			} else if(!library.bookHasBorrow(args[1])) {
				System.out.println("This book is not currently borrowed.");
			} else {
				int randomMonth = 1 + (int)(Math.random() * ((12 - 1) + 1));
				int randomDay = 1 + (int)(Math.random() * ((31 - 1) + 1));
				String date = randomDay + "-" + randomMonth + "-2019";
				Borrowing borrow = library.getBorrowing(indexBorrow);
				borrow.setReturnDate(date);
				System.out.println(borrow.getUserFirstName()+" "+borrow.getUserLastName()+" borrowed book "+borrow.getBookReference()+" on "+borrow.getBorrowingDate()+" and he returned it on "+borrow.getReturnDate());
			}
		}
	}
	
	/** Execution commande listBorrow **/
	private void listBorrow(String[] args) {
		if(args.length != 1) {
			System.out.println("Invalid number of arguments.");
		} else {
			library.displayBorrowsListSorted();
		}
	}
	
	/** Execution commande save **/
	private void save(String[] args) {
		if(args.length != 1) {
			System.out.println("Invalid number of arguments.");
		} else {
			library.save();
		}
	}
	
	/** Execution commande restore **/
	private void restore(String[] args) {
		if(args.length != 1) {
			System.out.println("Invalid number of arguments.");
		} else {
			library.restore();
		}
	}
}
