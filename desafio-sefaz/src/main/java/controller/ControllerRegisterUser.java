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

import beans.PhoneBean;
import beans.UserBean;
import model.Phone;


@Named("controllerRegisterUser")
@ViewScoped
public class ControllerRegisterUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	UserBean userBean;
	
	@Inject
	PhoneBean phoneBean;
	
	@Inject
	Phone phoneToAdd;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Resource
	private UserTransaction transaction;
	
	public String registerUser() {
		String email = userBean.getUser().getEmail();
		phoneBean.getPhone().setEmail(email);
		try {
			transaction.begin();
			userBean.persistUser();
			phoneBean.persistPhone();
			//userBean.getUserList().add(userBean.getUser());
			userBean.clear();
			transaction.commit();
			return "success";
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	
	}
	
	public String updateUser() {
		if(userBean.getUser() == null) {
			return "";
		}
		
		try {
			transaction.begin();
			userBean.editUser();
			userBean.clear();
			transaction.commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";

	}
	
	public String removePhone() {
		if(userBean.getUser() != null && userBean.getUser().getPhones().size() > 1) {
			try {
				transaction.begin();
				phoneBean.removePhone();
				transaction.commit();
			} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "success";
		}else {
			return "fail";
		}
		
		

	}
	
	public String addPhone() {
		if(userBean.getUser() != null) {
			phoneBean.getPhone().setEmail(userBean.getUser().getEmail());
			try {
				transaction.begin();
				phoneBean.persistPhone();
				transaction.commit();
			} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "success";
		}else {
			return "fail";
		}
		
		

	}
	

}
