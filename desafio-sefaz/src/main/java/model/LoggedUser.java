package model;

public class LoggedUser {
	private static User loggedUser;
	
	public static User getInstance() {
		if(loggedUser == null) {
			loggedUser = new User();
		}
		return loggedUser;
	}
	
	public static void setLoggedUser(User user) {
		loggedUser = user;
	}
}
