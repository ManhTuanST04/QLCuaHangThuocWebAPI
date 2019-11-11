package com.api.DAO;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.api.model.RoleModel;
import com.api.model.UserRoleModel;

@Repository
@Transactional
public class RoleDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<RoleModel> GetAllRole(){
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM Role";
		Query query = session.createQuery(hql);
		List<Role> lstRole = query.list();
		List<RoleModel> lstRoleModel = new ArrayList<RoleModel>();
		if(lstRole.size() > 0) {
			RoleModel roleModel;
			for (Role role : lstRole) {
				roleModel = new RoleModel(role.getId(), role.getName(), role.getCode());
				lstRoleModel.add(roleModel);
			}
		}
		return lstRoleModel;
	}
	
	public List<RoleModel> GetRoleUser(int userId){
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select u.roles from User u where u.id = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		List<Role> lstRole = query.list();
		List<RoleModel> lstRoleModel = new ArrayList<RoleModel>();
		if(lstRole.size() > 0) {
			RoleModel roleModel;
			for (Role role : lstRole) {
				roleModel = new RoleModel(role.getId(), role.getName(), role.getCode());
				lstRoleModel.add(roleModel);
			}
		}
		return lstRoleModel;
	}
	 
	public int AssignRoleForUser(UserRoleModel model) {
		Session session = this.sessionFactory.getCurrentSession();
		
		int userId = model.getUserId();
		User u = (User)session.get(User.class, userId);
		
		//Xóa các role cũ của user
		List<RoleModel> lstOldRole = GetRoleUser(userId);
		if(lstOldRole.size() > 0) {
			for (RoleModel roleModel : lstOldRole) {
				Role r = (Role)session.get(Role.class, roleModel.getId());
				u.RemoveRoleUser(r);
			}
		}
		
		//Thêm các role cập nhật mới
		String sRole = model.getsRole();
		if(sRole != "" && sRole != null) {
			List<String> lstNewRole = new ArrayList<String>(Arrays.asList(sRole.split(",")));
			for (String sRoleId : lstNewRole) {
				int iRoleId = Integer.parseInt(sRoleId);	
				Role r = (Role)session.get(Role.class, iRoleId);
				u.AddRoleUser(r);
			}
		}
		
		return 1;
	}
	
	public int AddRole(RoleModel roleModel) {
		Role role = new Role(roleModel.getId(), roleModel.getName(), roleModel.getCode());
		Session session = this.sessionFactory.getCurrentSession();
		session.save(role);
		return 1;
	}
	
	public RoleModel GetRoleById(int idRole) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role = session.get(Role.class, idRole);
		RoleModel roleModel = new RoleModel(role.getId(), role.getName(), role.getCode());
		return roleModel;
	}
	
	public int UpdateRole(RoleModel roleModel) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role = session.get(Role.class, roleModel.getId());
		role.setId(roleModel.getId());
		role.setName(roleModel.getName());
		role.setCode(roleModel.getCode());
		session.update(role);
		return 1;
	}
	
	public int DeleteRole(int idRole) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role = session.get(Role.class, idRole);
		role.RemoveAllUser();
		role.RemoveAllPermission();
		return 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
