package controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.LoggedInUserBean;

import model.User;

@Named("controllerLogin")
@ViewScoped
public class ControllerLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LoggedInUserBean loggedInUserBean;
	
	@PersistenceContext
	private EntityManager em;
	
	public String validation() {
		String result = "";
		String typedPwd = loggedInUserBean.getUser().getPassword();
		User userRegister = em.find(User.class, this.loggedInUserBean.getUser().getEmail());
		if(userRegister != null && userRegister.getPassword().equals(typedPwd)) {
			loggedInUserBean.setLoggedUser(userRegister);
			loggedInUserBean.setLoggedIn(true);
			result = "success";
		}else {
			FacesContext.getCurrentInstance().addMessage("loginForm:loginButton", new FacesMessage("Login failed! Wrong Email or Password."));
			result = "failed";
		}
		return result;
	}
}
