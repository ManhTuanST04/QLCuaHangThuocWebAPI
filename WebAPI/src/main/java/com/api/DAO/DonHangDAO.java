package com.api.DAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.api.model.ChiTietDonHangModel;
import com.api.model.ChiTietDonHangModel2;
import com.api.model.DonDatHangModel;
import com.api.model.RoleModel;
import com.api.model.UserRoleModel;

@Repository
@Transactional
public class DonHangDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public int ThemDonHang(DonDatHangModel ddhModel) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			KhachHang kh = session.get(KhachHang.class, ddhModel.getIdKH());
			DonDatHang ddh = new DonDatHang(ddhModel.getId(), ddhModel.getNgayDat(), ddhModel.getNgayXuat(), ddhModel.getTinhTrangDon(), ddhModel.getTongTien(), kh);
			session.save(ddh);
			
			String sql = "SELECT * FROM dondathang ORDER BY id DESC LIMIT 1";
			Query query = session.createSQLQuery(sql);
			List<Object[]> res = query.list();
			Object[] obj = res.get(0);
			
			int tmp = (int)obj[0];
			return tmp;
		}
		catch(Exception ex) {
			return 0;
		}
	}
	
	public int ThemChiTietDonHang(ChiTietDonHangModel ctdhModel) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
//			Product pro = session.get(Product.class, idSP);
//			DonDatHang ddh = session.get(DonDatHang.class, idDH);
//			ddh.AddSanPham(pro);
			String sql = "insert into chitietdonhang (idSP, idDH, SoLuong, ThanhTien) values (?, ?, ?, ?)";
			Query query = session.createSQLQuery(sql);
			query.setParameter(0, ctdhModel.getIdSP());
			query.setParameter(1, ctdhModel.getIdDH());
			query.setParameter(2, ctdhModel.getSoLuong());
			query.setParameter(3, ctdhModel.getTongTien());
			
			query.executeUpdate();
			return 1;
		}
		catch(Exception ex) {
			return 0;
		}
	}
	
	//Lay danh sach don hang cua nguoi dung
	public List<DonDatHangModel> DonHangCuaNguoiDung(int idUser){
		Session session = this.sessionFactory.getCurrentSession();
		
		String hql = "Select kh.lstDDH from KhachHang kh where kh.id = :idUser";
		Query query = session.createQuery(hql);
		query.setParameter("idUser", idUser);
		
		List<DonDatHang> lstDDH = query.list();
		List<DonDatHangModel> lstddhModel = new ArrayList<DonDatHangModel>();
		DonDatHangModel model;
		
		for (DonDatHang item : lstDDH) {
			model = new DonDatHangModel(item.getId(), item.getNgayDat(), item.getNgayXuat(), item.getTinhTrangDon(), item.getTongTien());
			lstddhModel.add(model);
		}
		
		return lstddhModel;
	}
	
	//Lay chi tiet don hang tu ma don dat hang
	public List<ChiTietDonHangModel2> ChiTietDonHangTheoDon(int idDH){
		try {
			Session session = this.sessionFactory.getCurrentSession();
			
			String hql = "select sp.id, sp.name, sp.price, sp.image, ctdh.SoLuong, ctdh.ThanhTien from Product sp "
					+ " join chitietdonhang ctdh on sp.id = ctdh.idSP "
					+ " join dondathang dh on ctdh.idDH = dh.id "
					+ " where idDH = :idDH ";
	
			Query query = session.createSQLQuery(hql);
			query.setParameter("idDH", idDH);
			
			List<Object[]> res = query.list();
			ChiTietDonHangModel2 model;
			List<ChiTietDonHangModel2> lstCTDH = new ArrayList<ChiTietDonHangModel2>();
			for (Object[] obj : res) {
				model = new ChiTietDonHangModel2((int)obj[0], obj[1].toString(), (int)obj[2], obj[3].toString(),  (int)obj[4], (int)obj[5]);
				lstCTDH.add(model);
			}
			return lstCTDH;
		}
		catch(Exception ex) {
			return null;
		}

	}
	
	
	///Chuc nang dung cho trang Admin
	//Lay tat ca danh sach don hang
	public List<DonDatHangModel> LayTatCaDonHang(){
		try {
			Session session = this.sessionFactory.getCurrentSession();
			String hql = "From DonDatHang";
			Query query = session.createQuery(hql);
			
			List<DonDatHang> lstDDH = query.list();
			List<DonDatHangModel> lstModel = new ArrayList<DonDatHangModel>();
			DonDatHangModel model;
			for (DonDatHang item : lstDDH) {
				model = new DonDatHangModel(item.getId(),item.getKhachhang().getId() , item.getNgayDat(), item.getNgayXuat(), item.getTinhTrangDon(), item.getTongTien());
				lstModel.add(model);
			}
			return lstModel;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	
	public int XacNhanDonHang(int idDH) {
		Session session = this.sessionFactory.getCurrentSession();
		long millis=System.currentTimeMillis();  
		java.sql.Date date =new java.sql.Date(millis);  
		String sql = "Update DonDatHang set NgayXuat = ?, TinhTrangDon = 1 where id = ?";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, date);
		query.setParameter(1, idDH);
		
		return query.executeUpdate();
	}
	
	public int HuyDonHang(int idDH) {
		Session session = this.sessionFactory.getCurrentSession();
		long millis=System.currentTimeMillis();  
		java.sql.Date date =new java.sql.Date(millis);  
		String sql = "Update DonDatHang set TinhTrangDon = -1 where id = ?";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, idDH);
		
		return query.executeUpdate();
	}
	
	
	
	
}
