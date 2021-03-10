package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.transaction.UserTransaction;


import model.Phone;
import model.User;

@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LoggedInUserBean loggedInUser;
	
	@Inject
	private User user;

	@Inject
	private PhoneBean phoneBean;

	@PersistenceContext
	private EntityManager em;

	@Resource
	private UserTransaction transaction;

	private List<User> userList = new ArrayList<User>();

	public boolean persistUser() {
		try {

			em.persist(user);
			em.flush();
			em.clear();

			return true;
		} catch (SecurityException | IllegalStateException e) {

			e.printStackTrace();
			return false;
		}
	}

	public void editUser() {
		try {

			em.merge(user);
			em.flush();

		} catch (SecurityException | IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeUser() {
		try {
			
			
			if(this.user.getPhones()==null || this.user.getPhones().isEmpty()) {
				retrieveNumbers();
			}
			for(Phone phone: this.user.getPhones()) {
				phoneBean.setPhone(phone);
				phoneBean.removePhone();
				em.flush();
			}
			User toRemove = em.find(User.class, this.user.getEmail());
			em.remove(toRemove);
			userList.remove(this.user);
			clear();
			phoneBean.clear();
			
		} catch (SecurityException | IllegalStateException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public void retrieveList() {
		
		Query query = em.createQuery("SELECT x FROM User x");
		userList = query.getResultList();
		User loggedUser= loggedInUser.getLoggedUser();
		if(loggedUser != null) {
			for(int i = 0; i < userList.size(); i++) {
				if(userList.get(i).getEmail().equals(loggedUser.getEmail())) {
					 userList.remove(i);
				}
			}
			userList.remove(loggedUser);
		}
		
		retrieveNumbers();
	}

//	@SuppressWarnings("unused")
	private void retrieveNumbers() {
		for (User user : userList) {
			String email = user.getEmail();
			Query query = em.createQuery("SELECT x FROM Phone x WHERE x.email=:email").setParameter("email", email);
			List phones = query.getResultList();
			user.getPhones().addAll(phones);
		}

	}

	public void clear() {
		user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		if (userList.size() == 0) {
			retrieveList();
		}
		for (User user : userList) {
			if (user.isAdmin()) {
				userList.remove(user);
				break;
			}
		}
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public PhoneBean getPhoneBean() {
		return phoneBean;
	}

	public void setPhoneBean(PhoneBean phoneBean) {
		this.phoneBean = phoneBean;
	}


}
