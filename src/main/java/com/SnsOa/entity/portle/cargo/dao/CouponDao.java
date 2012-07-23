package com.SnsOa.entity.portle.cargo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SnsOa.entity.portle.cargo.Coupon;
@Repository("CouponDao")
public interface CouponDao {
	public boolean save(Coupon comObj);
	public boolean update(Coupon comObj);
	public boolean delete(long id);
	public List<Coupon> query(String sqlstr);
	public Coupon getCompany(long id);
	public List<Coupon> queryPage(String sqlstr,int nowpage,int maxRow);
}
