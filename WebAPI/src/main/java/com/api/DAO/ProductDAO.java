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
			model = new ProductModel(product.getId(), product.getName(), product.getPrice(), product.getWeight(), product.getColor(), product.getImage());
			lstpro.add(model);
		}
		//session.getTransaction().commit();
		//session.close();
		return lstpro;
	}
	
	public ProductModel GetProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = session.get(Product.class, id);
		ProductModel proModel = new ProductModel(product.getId(), product.getName(), product.getPrice(), product.getWeight(), product.getColor(), product.getImage());
		return proModel;
	}
	
	public int AddProduct(ProductModel model) {
		Session session = sessionFactory.getCurrentSession();
		Product pro = new Product();
		pro.setName(model.getName());
		pro.setPrice(model.getPrice());
		pro.setWeight(model.getWeight());
		pro.setColor(model.getColor());
		pro.setImage(model.getImage());
		
		session.save(pro);
		return 1;
	}
	
	public int UpdateProduct(ProductModel model) {
		Session session = sessionFactory.getCurrentSession();
		Product pro = new Product();
		pro.setId(model.getId());
		pro.setName(model.getName());
		pro.setPrice(model.getPrice());
		pro.setWeight(model.getWeight());
		pro.setColor(model.getColor());
		pro.setImage(model.getImage());
		
		session.saveOrUpdate(pro);
		return 1;
	}
}
