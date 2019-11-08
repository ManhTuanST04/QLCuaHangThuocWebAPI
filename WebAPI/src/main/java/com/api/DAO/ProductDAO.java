package com.api.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.HibernateUtils;
import com.api.entity.*;
import com.api.model.ProductModel;

@Repository
@Transactional
public class ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;
    
	public List<ProductModel> GetListProduct(){
		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		Criteria ctr = session.createCriteria(Product.class);
		List<Product> lst = ctr.list();
		ProductModel model;
		List<ProductModel> lstpro = new ArrayList<ProductModel>();
		for (Product product : lst) {
			model = new ProductModel(product.getId(), product.getName(), product.getPrice(), product.getWeight(), product.getColor());
			lstpro.add(model);
		}
		//session.getTransaction().commit();
		//session.close();
		return lstpro;
	}
	
//	public Product GetPro(int id) {
//		 Session session = this.sessionFactory.getCurrentSession();
//		 Product pro = session.get(Product.class, id);
//		 return pro;
//	}
//	
//	public List<Object[]> GetPer(int role) {
//		Session session = this.sessionFactory.getCurrentSession();
//		String hql = "select r.id, r.name FROM Role r INNER JOIN r.permissions as per where per.id = 1";
//		Query query = session.createQuery(hql);
//		List<Object[]> results = query.list();
//		return results;
//	}
//	
//	public List<Permission> GetPerUser(int user) {
//		Session session = this.sessionFactory.getCurrentSession();
//		String hql = "select distinct(per.id) , per.name FROM Permission per INNER JOIN per.roles rol INNER JOIN rol.users user where user.id = 1";
//		Query query = session.createQuery(hql);
//		List<Permission> results = query.list();
//		return results;
//	}
}
