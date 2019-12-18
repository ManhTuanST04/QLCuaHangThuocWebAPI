package com.api.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.entity.NhaCungCap;
import com.api.model.*;

@Repository
@Transactional
public class NhaCungCapDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	//danh sach
	public List<NhaCungCapModel> DanhSachNCC(){
		Session session = sessionFactory.getCurrentSession();
		String hql = "From NhaCungCap";
		
		Query query = session.createQuery(hql);
		List<NhaCungCap> lst = query.list();
		List<NhaCungCapModel> lstModel = new ArrayList<NhaCungCapModel>();
		NhaCungCapModel model = new NhaCungCapModel();
		for (NhaCungCap ncc : lst) {
			model = new NhaCungCapModel(ncc.getId(), ncc.getTen(), ncc.getDiaChi(), ncc.getDienThoai(), ncc.getWebsite(), ncc.getEmail(), ncc.getQuocGia());
			lstModel.add(model);
		}
		return lstModel;
		
	}
	
	//them
	public int ThemNhaCungCap(NhaCungCapModel model) {
		Session session = sessionFactory.getCurrentSession();
		NhaCungCap ncc = new NhaCungCap(model.getTen(), model.getDiaChi(), model.getDienThoai(), model.getWebsite(), model.getEmail(), model.getQuocGia());
		session.save(ncc);
		return 1;
	}
	
	//lay theo id
	public NhaCungCapModel GetNCCById(int idNCC) {
		Session session = sessionFactory.getCurrentSession();
		NhaCungCap ncc = session.get(NhaCungCap.class, idNCC);
		NhaCungCapModel model = new NhaCungCapModel(ncc.getId(), ncc.getTen(), ncc.getDiaChi(), ncc.getDienThoai(), ncc.getWebsite(), ncc.getEmail(), ncc.getQuocGia());
		return model;
	}
	
	//sua
	public int SuaNhaCungCap(NhaCungCapModel model) {
		Session session = sessionFactory.getCurrentSession();
		NhaCungCap ncc = new NhaCungCap(model.getId(),model.getTen(), model.getDiaChi(), model.getDienThoai(), model.getWebsite(), model.getEmail(), model.getQuocGia());
		session.saveOrUpdate(ncc);
		return 1;
	}
	
	//xoa
	public int XoaNhaCungCap(int idNCC) {
		Session session = sessionFactory.getCurrentSession();
		NhaCungCap ncc = session.get(NhaCungCap.class, idNCC);
		session.delete(ncc);
		return 1;
	}
}
