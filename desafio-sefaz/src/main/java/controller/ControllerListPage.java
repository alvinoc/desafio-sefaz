package controller;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import beans.LoggedInUserBean;
import beans.UserBean;

import model.User;

@Named("controllerListPage")
@ViewScoped
public class ControllerListPage implements Serializable {

	@Inject
	private UserBean userBean;

	@Inject
	private LoggedInUserBean loggedInUserBean;
	@Resource
	private UserTransaction transaction;

	@PersistenceContext
	private EntityManager em;
	private static final long serialVersionUID = 1L;

	public void removeSelectedUser() {
		userBean.removeUser();
	}

	public void removePhoneFromUser() {
	//	boolean userIsAdmin = loginCDI.getUser().isAdmin();
		String currentUserEmail = loggedInUserBean.getUser().getEmail();
		User selectedUser = userBean.getUser();
		if (!selectedUser.getEmail().equals(currentUserEmail)) {
			return;
		}
//		userBean.getUser().getPhones().remove(userBean.getPhone());
		userBean.editUser();
	}
	
//	public void addPhone() {
////		boolean userIsAdmin = loginCDI.getUser().isAdmin();
//		String currentUserEmail = loginCDI.getUser().getEmail();
//		User selectedUser = userBean.getUser();
//		
//
//		userBean.editUser();
//	}
	
	public String updateUser() {
		if(userBean.getUser()==null) {
			return "fail";
		}
		else {
			return "update.xhtml?faces-redirect=true";
		}
	}
	
	public String removeUser() {
		if(userBean.getUser()==null) {
			return "";
		}
		try {
			
			transaction.begin();
			
			userBean.removeUser();
			userBean.clear();
			transaction.commit();
		} catch (SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
		

	}

		
		

		
	

}
