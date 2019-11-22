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
import com.api.entity.Permission;
import com.api.entity.Product;
import com.api.entity.Role;
import com.api.entity.User;
import com.api.model.*;

@Repository
@Transactional
public class UserDAO {
	@Autowired
    private SessionFactory sessionFactory;
	
	public UserModel Login(String userName, String passWord) {
		Session session = this.sessionFactory.getCurrentSession();
		//session.beginTransaction();
		String hql = "FROM User where username = :userName and password = :passWord";
		Query query = session.createQuery(hql);
		query.setParameter("userName", userName);
		query.setParameter("passWord", passWord);
		
		List<User> a = query.list();
		UserModel user = null;
		if(a.size() > 0) {
			User u = a.get(0);
			user = new UserModel(u.getId(), u.getUsername(), u.getPassword(), u.getName(), u.getMobile(), u.getEmail());
		}
		//session.getTransaction().commit();
		//session.close();
		return user;
	}
	
	public List<UserModel> GetListUser(){
		Session session = this.sessionFactory.getCurrentSession();
		//session.beginTransaction();
		Criteria ctr = session.createCriteria(User.class);
		List<User> lst = ctr.list();
		List<UserModel> lstUsr = new ArrayList<UserModel>();
		for (User user : lst) {
			UserModel um = new UserModel(user.getId(), user.getUsername(), user.getPassword(), user.getName(), user.getMobile(), user.getEmail());
			lstUsr.add(um);
		}
		//session.getTransaction().commit();
		//session.close();
		return lstUsr;
	}
	
	public int CheckDuplicateUser(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		//session.beginTransaction();
		String hql = "select count(*) FROM User where username = :userName";
		Query query = session.createQuery(hql);
		query.setParameter("userName", userName);
		
		List listResult = query.list();
		int res = 0;
		if(listResult.size() > 0) {
			res = ((Number)listResult.get(0)).intValue();
		}
		//session.getTransaction().commit();
		//session.close();
		return res;
	}
	
	public int InsertOrUpdateUser(UserModel user) {
		Session session = this.sessionFactory.getCurrentSession();
		//session.beginTransaction();
		String hql = "";
		if(user.getId() > 0) {
			hql = "update User set username = :userName, password = :passWord, name = :name, mobile = :mobile, email = :email where id = :id ";
			Query query = session.createQuery(hql);
			query.setParameter("userName", user.getUserName());
			query.setParameter("passWord", user.getPassWord());
			query.setParameter("name", user.getName());
			query.setParameter("mobile", user.getMobile());
			query.setParameter("email", user.getEmail());
			query.setParameter("id", user.getId());
			int res = query.executeUpdate();
			//session.getTransaction().commit();
			//session.close();
			return res;
		}else {
			User ur = new User(user.getId(), user.getUserName(), user.getPassWord(), user.getName(), user.getMobile(), user.getEmail());
			session.save(ur);
			//session.getTransaction().commit();
			//session.close();
			return 1;
		}
		
	}
	
	public UserModel GetUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		//session.beginTransaction();
		String hql = "FROM User where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		List<User> a = query.list();
		UserModel user = null;
		if(a.size() > 0) {
			User u = a.get(0);
			user = new UserModel(u.getId(), u.getUsername(), u.getPassword(), u.getName(), u.getMobile(), u.getEmail());
		}
		//session.getTransaction().commit();
		//session.close();
		return user;
	}
	
	public int DeleteUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		//session.beginTransaction();
		String hql = "delete from User where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		int res = query.executeUpdate();
		//session.getTransaction().commit();
		//session.close();
		return res;
	}
	
	public List<PermissionModel> GetPerUser(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select distinct(per.id) , per.name, per.code FROM Permission per INNER JOIN per.roles rol INNER JOIN rol.users user where user.id = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		List<Object[]> lstPer = query.list();
		List<PermissionModel> lstPerModel = new ArrayList<PermissionModel>();
		if(lstPer.size() > 0) {
			PermissionModel pm;
			for (Object[] obj : lstPer) {
				pm = new PermissionModel((int)obj[0], obj[1].toString(), obj[2].toString());
				lstPerModel.add(pm);
			}  
		}
		
		return lstPerModel;
	}
	
	public List<ControlModel> GetListControlUser(int userId){
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select c.id, c.name, c.code, c.permission.id FROM Control c where c.permission.id in (select distinct(per.id) FROM Permission per INNER JOIN per.roles rol INNER JOIN rol.users user where user.id = :userId)";

		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		List<Object[]> lstControl = query.list();
		List<ControlModel> lstControlModel = new ArrayList<ControlModel>();
		if(lstControl.size() > 0) {
			ControlModel controlModel;
			for (Object[] obj : lstControl) {
				controlModel = new ControlModel((int)obj[0], obj[1].toString(), obj[2].toString(), (int)obj[3]);
				lstControlModel.add(controlModel);
			}
		}
		
		String hql2 = "select c.id, c.name, c.code, c.permission.id from Control c INNER JOIN c.roles r INNER JOIN r.users u where u.id = :userId";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("userId", userId);
		List<Object[]> lstControl2 = query2.list();
		List<ControlModel> lstControlModel2 = new ArrayList<ControlModel>();
		if(lstControl2.size() > 0) {
			ControlModel controlModel;
			for (Object[] obj : lstControl2) {
				controlModel = new ControlModel((int)obj[0], obj[1].toString(), obj[2].toString(), (int)obj[3]);
				lstControlModel2.add(controlModel);
			}
		}
		
		if(lstControlModel2.size() > 0) {
			lstControlModel.addAll(lstControlModel2);
		}
		
		return lstControlModel;
	}
	
	
	
	
	
	
}
