package beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.User;

@Named("loggedInUserBean")
@SessionScoped
public class LoggedInUserBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private User user;

	private User loggedUser;
	
	private boolean isLoggedIn = false;;
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	
	
	
	
}
