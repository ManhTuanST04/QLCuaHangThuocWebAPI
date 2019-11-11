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
import com.api.entity.Control;
import com.api.entity.Permission;
import com.api.entity.Product;
import com.api.entity.Role;
import com.api.entity.User;
import com.api.model.*;

@Repository
@Transactional
public class ControlDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<ControlModel> GetAllControl(){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Control";
		Query query = session.createQuery(hql);
		
		List<Control> lst = query.list();
		List<ControlModel> lstModel = new ArrayList<ControlModel>();
		if(lst.size() > 0) {
			ControlModel controlModel;
			for (Control control : lst) {
				controlModel = new ControlModel(control.getId(), control.getName(), control.getCode());
				lstModel.add(controlModel);
			}
		}
		
		return lstModel;
	}
	
	public List<ControlModel> GetControlPer(int perId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "select p.lstControl FROM Permission p where p.id = :perId";
		Query query = session.createQuery(hql);
		query.setParameter("perId", perId);
		
		List<Control> lst = query.list();
		List<ControlModel> lstModel = new ArrayList<ControlModel>();
		if(lst.size() > 0) {
			ControlModel controlModel;
			for (Control control : lst) {
				controlModel = new ControlModel(control.getId(), control.getName(), control.getCode());
				lstModel.add(controlModel);
			}
		}
		
		return lstModel;
	}
	
	
	
	
	
	
	
	
}
