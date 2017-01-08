package com.lzw.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.lzw.model.User;

public class UserDao extends AbstractDao {
	private static Logger rootLogger = Logger.getLogger("rootLogger");

	public UserDao() {
		super();
	}

	public User getUser(String name, String password) {
		Session session = getSession();
		Transaction tx = null;
		User user = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("loginName", name));
			criteria.add(Restrictions.eq("password", password));
			List list = criteria.list();
			tx.commit();

			if (list.size() > 0) {
				user = (User) list.get(0);
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return user;
	}

	public boolean isLoginNameExisted(String loginname) {
		Session session = getSession();
		Transaction tx = null;
		boolean isExisted = false;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("loginName", loginname));
			List<User> list = criteria.list();
			tx.commit();

			if (list.size() > 0) {
				isExisted = true;
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return isExisted;
	}
}
