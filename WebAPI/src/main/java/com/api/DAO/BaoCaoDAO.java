package com.api.DAO;

import java.sql.Date;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.model.BaoCaoDonHangModel;

@Repository
@Transactional
public class BaoCaoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public BaoCaoDonHangModel BaoCaoDonHang(Date tuNgay, Date denNgay, int tinhTrangDon) {
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "select count(*) as SoLuong, sum(TongTien) as TongTien from dondathang where ";
		if(tinhTrangDon == 0) {
			sql += " NgayDat >= ? and  NgayDat <= ? and TinhTrangDon = 0 ";
		}
		else if(tinhTrangDon == 1) {
			sql += " NgayXuat >= ? and  NgayXuat <= ? and TinhTrangDon = 1 ";
		}

		Query query = session.createSQLQuery(sql).addScalar("SoLuong", IntegerType.INSTANCE).addScalar("TongTien", IntegerType.INSTANCE);
		query.setParameter(0, tuNgay);
		query.setParameter(1, denNgay);
		
		Object[] res = (Object[]) query.getSingleResult();
		return new BaoCaoDonHangModel((int)res[0], (int)res[1]);
	}
}
