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
import com.api.model.PermissionModel;
import com.api.model.RoleModel;
import com.api.model.UserRoleModel;

@Repository
@Transactional
public class PermissionDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<PermissionModel> GetAllPermission(){
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM Permission";
		Query query = session.createQuery(hql);
		
		List<Permission> lst = query.list();
		List<PermissionModel> lstPer = new ArrayList<PermissionModel>();
		if(lst.size() > 0) {
			PermissionModel permissionModel;
			for (Permission permission : lst) {
				permissionModel = new PermissionModel(permission.getId(), permission.getName(), permission.getCode());
				lstPer.add(permissionModel);
			}
		}
		
		return lstPer;
	}
	
	//Lấy danh sách các quyền của Nhóm quyền
	public List<PermissionModel> GetPermissionRole(int roleId){
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select r.permissions FROM Role r where r.id = :roleId";
		Query query = session.createQuery(hql);
		query.setParameter("roleId", roleId);
		
		List<Permission> lst = query.list();
		List<PermissionModel> lstPer = new ArrayList<PermissionModel>();
		if(lst.size() > 0) {
			PermissionModel permissionModel;
			for (Permission permission : lst) {
				permissionModel = new PermissionModel(permission.getId(), permission.getName(), permission.getCode());
				lstPer.add(permissionModel);
			}
		}
		
		return lstPer;
	}
	
	//Gán quyền cho Nhóm quyền
	public int AssignPermissionForRole(int roleId, int perId) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role = session.get(Role.class, roleId);
		Permission per = session.get(Permission.class, perId);
		role.AddPerForRole(per);
		return 1;
	}
	
	public int RemovePerForRole(int roleId, int perId) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role = session.get(Role.class, roleId);
		Permission per = session.get(Permission.class, perId);
		role.RemovePerForRole(per);
		return 1;
	}
	
	
	//Thêm quyền
	public int AddNewPer(PermissionModel perModel) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Permission per = new Permission();
		per.setCode(perModel.getCode());
		per.setName(perModel.getName());
		
		session.save(per);
		return 1;
	}
	
	
	
	
}
