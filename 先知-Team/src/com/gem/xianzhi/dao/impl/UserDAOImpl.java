package com.gem.xianzhi.dao.impl;

import java.util.List;

import com.gem.xianzhi.dao.UserDAO;
import com.gem.xianzhi.dao.base.impl.BaseDAO;
import com.gem.xianzhi.pojo.User;
import com.gem.xianzhi.util.StringUtil;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

	@Override
	public User login(String loginId, String pwd) {
		String hql="from User u where u.loginId=? and u.pwd=? ";
		List<User> userList =(List<User>) getHibernateTemplate().find(hql, loginId , pwd);
		if(userList!=null&userList.size()>0){
			User user=userList.get(0);
			return user;
		}
		return null;
	}

	@Override
	public int getUserCount(String keyword) {
		String hql = "select count(*) from User u where u.nickName like ? or u.type like ? ";
		return ((Long)getHibernateTemplate().find(hql,"%"+keyword+"%","%"+keyword+"%").get(0)).intValue() ;
	}

	@Override
	public List<User> getUserList(String keyword, int pageSize, int pageNum) {
		if(StringUtil.isEmpty(keyword)){
			keyword="";
		}
		String hql = "from User u where u.nickName like ? or u.type like ?" ; 
		return getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createQuery(hql).setParameter(0, "%"+keyword+"%")
								.setParameter(1, "%"+keyword+"%")
								.setFirstResult((pageNum-1)*pageSize )
								.setMaxResults(pageSize).list();														
	}

	@Override
	public int getUserCount() {
		String hql="select count(*) from User u ";
		return ((Long)getHibernateTemplate().find(hql).get(0)).intValue();
	}

	@Override
	public List<User> getUserByType(String keyword,int pageSize, int pageNum) {
		if(StringUtil.isEmpty(keyword)){
			keyword="";
		}
		String hql = "from User u where u.type like ?" ; 
		return getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createQuery(hql).setParameter(0, "%"+keyword+"%")
								.setFirstResult((pageNum-1)*pageSize )
								.setMaxResults(pageSize).list();
	}

	@Override
	public int getUserCountByType(String keyword) {
		String hql = "select count(*) from User u where u.type like ? ";
		return ((Long)getHibernateTemplate().find(hql,"%"+keyword+"%").get(0)).intValue() ;
	}

	@Override
	public User getUserByLN(String loginId, String nickName) {
		String hql = "from User u where u.loginId=? or u.nickName=?";
		List<User> userList= (List<User>)getHibernateTemplate().find(hql, loginId,nickName);
		if(userList==null ||userList.size()<=0){
			return null;		
		}else{
			return userList.get(0);
		}
	}

	@Override
	public User login(String loginId) {
		String hql = "from User u where u.loginId=?";
		List<User>userList = (List<User>)getHibernateTemplate().find(hql, loginId);
		if(userList!=null&userList.size()>0){
			User user=userList.get(0);
			return user;
		}
		return null;
	}

	@Override
	public void updateUser(User user) {
		String hql = "update User u set u.nickName=? where u.id=?";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql)
																	.setParameter(0, user.getNickName())
																	.setParameter(1, user.getId())
																	.executeUpdate();
		
	}

	@Override
	public List<User> getUserList(String keyword) {
		if(StringUtil.isEmpty(keyword)){
			keyword = "" ;
		}
		String hql = "from User u where u.nickName like ?" ;
		return getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createQuery(hql).setParameter(0, "%"+keyword+"%").list();
	}

	@Override
	public void saveOrUpdate(User user) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().saveOrUpdate(user);
		
	}




}
