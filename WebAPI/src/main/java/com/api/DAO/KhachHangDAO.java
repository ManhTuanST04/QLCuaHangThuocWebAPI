package com.api.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.entity.DonDatHang;
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
	
	public KhachHangModel GetKhachHangById(int idKH) {
		Session session = this.sessionFactory.getCurrentSession();
		KhachHang u = session.get(KhachHang.class, idKH);
		
		KhachHangModel khModel = new KhachHangModel(u.getId(), u.getDienThoai() , u.getMatKhau(), u.getTen(), u.getEmail(), u.getDiaChi());
		return khModel;
	} 
	
	public List<KhachHangModel> DanhSachKhachHang() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<KhachHang> lst = session.createCriteria(KhachHang.class).list();
		List<KhachHangModel> lstModel = new ArrayList<KhachHangModel>();
		KhachHangModel model;
		for (KhachHang u : lst) {
			model = new KhachHangModel(u.getId(), u.getDienThoai() , u.getMatKhau(), u.getTen(), u.getEmail(), u.getDiaChi());
			lstModel.add(model);
		}
		return lstModel;
	} 
	
	public int AddKhachHang(KhachHangModel khModel) {
		Session session = this.sessionFactory.getCurrentSession();
		KhachHang kh = new KhachHang();
		kh.setTen(khModel.getTen());
		kh.setMatKhau(khModel.getMatKhau());
		kh.setEmail(khModel.getEmail());
		kh.setDienThoai(khModel.getDienThoai());
		kh.setDiaChi(khModel.getDiaChi());
		session.save(kh);
		return 1;
	}
	
	public int EditKhachHang(KhachHangModel khModel) {
		Session session = this.sessionFactory.getCurrentSession();
		KhachHang kh = new KhachHang();
		kh.setId(khModel.getId());
		kh.setTen(khModel.getTen());
		kh.setMatKhau(khModel.getMatKhau());
		kh.setEmail(khModel.getEmail());
		kh.setDienThoai(khModel.getDienThoai());
		kh.setDiaChi(khModel.getDiaChi());
		session.saveOrUpdate(kh);
		return 1;
	}
	
	public int DeleteKhachHang(int idKH) {
		Session session = this.sessionFactory.getCurrentSession();
		//Xóa khách hàng thì xóa hết các đơn hàng của người này
		String hql = "delete from DonDatHang where idKH = :idKH";
		Query query = session.createQuery(hql);
		query.setParameter("idKH", idKH);
		query.executeUpdate();
		
		KhachHang kh = session.get(KhachHang.class, idKH);
		session.delete(kh);
		return 1;
	}
}
