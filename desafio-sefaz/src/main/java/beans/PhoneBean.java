package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import model.Phone;


@Named("phoneBean")
@ViewScoped
public class PhoneBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Phone phone;


	@PersistenceContext
	private EntityManager em;


	private List<Phone> phoneList = new ArrayList<Phone>();
	
	public boolean persistPhone() {
		try {
			em.persist(phone);
			em.flush();
			em.clear();

			return true;
		} catch (SecurityException | IllegalStateException e) {

			e.printStackTrace();
			return false;
		}
	}

	
	public void editPhone() {
		try {
			
			em.merge(phone);
			em.flush();
	
		} catch (SecurityException | IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	 public void removePhone() {
		try {

			Phone toRemove = em.find(Phone.class, this.getPhone().getNumber());
			em.remove(toRemove);

		} catch (SecurityException | IllegalStateException e) {
			e.printStackTrace();
		}

	}

	public void clear() {
		phone = new Phone();
	}

	@SuppressWarnings("unchecked")
	public void retrieveList() {
		Query query = em.createQuery("SELECT x FROM Phone x");
		setPhoneList(query.getResultList());
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}


	public List<Phone> getPhoneList() {
		return phoneList;
	}


	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}

}
