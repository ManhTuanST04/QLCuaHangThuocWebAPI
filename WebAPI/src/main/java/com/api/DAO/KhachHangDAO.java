package com.api.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.entity.KhachHang;
import com.api.model.KhachHangModel;

@Repository
@Transactional
public class KhachHangDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public KhachHangModel KhachHangLogin(String dienthoai, String matkhau) {
		Session session = this.sessionFactory.getCurrentSession();
		//session.beginTransaction();
		String hql = "FROM KhachHang where dienthoai = :dienThoai and matkhau = :matKhau";
		Query query = session.createQuery(hql);
		query.setParameter("dienThoai", dienthoai);
		query.setParameter("matKhau", matkhau);
		
		List<KhachHang> a = query.list();
		KhachHangModel khModel = null;
		if(a.size() > 0) {
			KhachHang u = a.get(0);
			khModel = new KhachHangModel(u.getId(), u.getDienThoai() , u.getMatKhau(), u.getTen(), u.getEmail(), u.getDiaChi());
		}
		//session.getTransaction().commit();
		//session.close();
		return khModel;
	}
}
