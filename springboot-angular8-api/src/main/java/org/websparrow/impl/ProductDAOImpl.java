package org.websparrow.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.websparrow.dao.ProductDAO;
import org.websparrow.entity.ClientProducts;
import org.websparrow.entity.Info;
import org.websparrow.entity.OurProducts;

@Repository("ProductDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@PersistenceContext
	@Autowired
	private EntityManager em;

	@Override
	public boolean addOurProducts(OurProducts ourProducts) {
		try {
			em.persist(ourProducts);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

/*	@Override
	public boolean updateOurProducts(OurProducts ourProducts) {
		try {
			em.merge(ourProducts);
			return true;
		} catch (Exception ex) {
			 //ex.printStackTrace();
			return false;
		}
	}*/

	@Override
	public boolean update(OurProducts ourProducts) {
		try {			
			em
						.merge(ourProducts);
			return true;
		}
		catch(Exception ex) {		
			//ex.printStackTrace();
			return false;
		}					
	}
	
	@Override
	public boolean deleteOurProducts(OurProducts ourProducts) {
		try {
			em.merge(ourProducts);
			return true;
		} catch (Exception ex) {
			// ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<OurProducts> getOurProductbyID(int id) {
		String query = "FROM OurProducts WHERE id = :id";
		System.out.println("hiiiii" + query);

		return em.createQuery(query, OurProducts.class).setParameter("id", id).getResultList();
	}

	@Override
	public List<OurProducts> getAllOurProducts() {
		String query = "FROM OurProducts";
		return em.createQuery(query, OurProducts.class).getResultList();
	}

	@Override
	public OurProducts getid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// Client Products //
	
	@Override
	public boolean addClientProducts(ClientProducts clientProducts) {
		try {
			em.persist(clientProducts);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateClientProducts(ClientProducts clientProducts) {
		try {
			em.merge(clientProducts);
			return true;
		} catch (Exception ex) {
			// ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteClientProducts(ClientProducts clientProducts) {
		try {
			em.merge(clientProducts);
			return true;
		} catch (Exception ex) {
			// ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ClientProducts> getClientproductbyID(int id) {
		String query = "FROM ClientProducts WHERE id = :id";
		System.out.println("hiiiii" + query);

		return em.createQuery(query, ClientProducts.class).setParameter("id", id).getResultList();
	}
	
	@Override
	public List<ClientProducts> getAllCLientproducts() {
		String query = "FROM ClientProducts";
		return em.createQuery(query, ClientProducts.class).getResultList();
	}

	@Override
	public ClientProducts getbyId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
