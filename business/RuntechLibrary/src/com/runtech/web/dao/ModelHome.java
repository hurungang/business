package com.runtech.web.dao;

import static com.runtech.util.AssociationExample.create;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.runtech.util.HibernateUtil;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;

public class ModelHome{

	protected static final Log log = LogFactory.getLog(ModelHome.class);
	final Session session = getSession();
	private Transaction transaction;
	
	public ModelHome() {
		super();
	}


	protected Session getSession() {
		try {
			return HibernateUtil.getCurrentSession();
		} catch (Exception e) {
			log.error("Could not locate Session", e);
			throw new IllegalStateException(
					"Could not locate Session");
		}
	}

	public void closeSession(){
		HibernateUtil.closeSession();
	}
	
	public void beginTransaction(){
		transaction = this.session.beginTransaction();
	}
	
	public void refresh(Object object){
		this.session.refresh(object);
	}

	public void commit(){
		if(this.transaction!=null){
			this.transaction.commit();
		}
	}

	public void rollback() {
		this.transaction.rollback();
	}
	
	public List<Object> findByExample(Object instance) {
		log.debug("finding Object instance by example");
		try {
			List<Object> results = (List<Object>) session
					.createCriteria(instance.getClass()).add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Object> findByExample(Object instance, String propertyName, boolean ascending) {
		log.debug("finding Object instance by example");
		try {
			Order order;
			if(ascending){
				order = Order.asc(propertyName);
			}else{
				order = Order.desc(propertyName);
			}
			List<Object> results = (List<Object>) session
					.createCriteria(instance.getClass()).add(
							create(instance)).addOrder(order).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	public Integer getCount(Object instance){
		try {
			Criteria criteria = session.createCriteria(instance.getClass());
			
			criteria = criteria.setProjection(Projections.rowCount()).add(create(instance));
			Integer count= (Integer) criteria.uniqueResult();
			return count;
		} catch (RuntimeException re) {
			log.error("get count failed", re);
			throw re;
		}
	}
	public List<Object> list(Object instance,Order order, Integer start, Integer fetchSize) {
		log.debug("finding Object instance by example");
		try {
			Criteria criteria = session.createCriteria(instance.getClass());
			criteria = criteria.add(create(instance));
			if(order!=null){
				criteria = criteria.addOrder(order);
			}
			if(start!=null){
				criteria = criteria.setFirstResult(start);
				if(fetchSize!=null){
					criteria = criteria.setMaxResults(fetchSize);
				}
			}
			List<Object> results = (List<Object>)criteria.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
		
	public Object findByKeyExample(Object instance) throws ModelException {
		log.debug("finding Object instance by example");
		try {
			List results = (List) session
					.createCriteria(instance.getClass()).add(
							create(instance)).list();

			if(results.size()==0){
				return null;
			}else if(results.size()>1){
				throw new ModelException("More than one record retrieved by code");
			}else{
				log.debug("find by key example successful, result size: "
						+ results.size());
				return results.get(0);
			}
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
		
	public Serializable save(Object transientInstance) {
		log.debug("persisting Object instance");
		try {
			Serializable id = session.save(transientInstance);
			log.debug("persist successful");
			return id;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void persist(Object transientInstance) {
		log.debug("persisting Object instance");
		try {
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Object instance) {
		log.debug("attaching dirty Object instance");
		try {
			session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Object instance) {
		log.debug("attaching clean Object instance");
		try {
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Object persistentInstance) {
		log.debug("deleting Object instance");
		try {
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Object merge(Object detachedInstance) {
		log.debug("merging Object instance");
		try {
			Object result = (Object) session.merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Object detachedInstance) {
		log.debug("saveOrUpdate Object instance");
		try {
			session.saveOrUpdate(
					detachedInstance);
			log.debug("saveOrUpdate successful");
		} catch (RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			throw re;
		}
	}
	public Object findById(Object Object, Serializable id) {
		log.debug("getting Object instance with id: " + id);
		try {
			if(id==null){
				return null;
			}
			Object instance = (Object) session.get(
					Object.getClass(), id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public Object findById(String objectName, Serializable id) {
		log.debug("getting Object instance with id: " + id);
		try {
			if(id==null){
				return null;
			}
			Object instance = (Object) session.get(objectName, id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public List<ModelForm> listForm(ModelForm modelForm,
			Order order, Integer start, Integer fetchSize) throws ModelException {
		List<Object> list = this.list(modelForm.getModel(), order, start, fetchSize);
		List<ModelForm> formList = new ArrayList<ModelForm>();
		try {
			for (Object model : list) {
					ModelForm tempModelForm = modelForm.getClass().newInstance();
					tempModelForm.copyFrom(model);
					formList.add(tempModelForm);
			}
		} catch (IllegalAccessException e) {
			log.error(e);
			throw new ModelException(e.fillInStackTrace());
		} catch (InstantiationException e) {
			log.error(e);
			throw new ModelException(e.fillInStackTrace());
		}
		return formList;
	}
	
	public List executeQuery(String queryString, Integer start, Integer fetchSize) {
		queryString = replaceSpecialString(queryString);
		Query query = session.createQuery(queryString);			
		if(start!=null){
			query = query.setFirstResult(start);
			if(fetchSize!=null){
				query = query.setMaxResults(fetchSize);
			}
		}
		List list = query.list();
		return list;
	}
	
	public Object executeQuery(String queryString) {
		queryString = replaceSpecialString(queryString);
		Query query = session.createQuery(queryString);			
		Object result = query.uniqueResult();
		return result;
	}
	
	public int executeUpdate(String updateString){
		updateString = replaceSpecialString(updateString);
		Query query = session.createQuery(updateString);			
		return query.executeUpdate();
	}
	
	public Integer getCount(String queryString){
		try {
			queryString = replaceSpecialString(queryString);
			int indexOfFrom = queryString.toLowerCase().indexOf("from");
			String fromCause = null;
			if(indexOfFrom!=-1){
				fromCause = queryString.substring(indexOfFrom);
				Query query = session.createQuery("select count(*) "+fromCause);
				Long count = (Long) query.uniqueResult();
				return count.intValue();
			}else{
				return (int)0;
			}
		} catch (RuntimeException re) {
			log.error("get count failed", re);
			throw re;
		}
	}


	private String replaceSpecialString(String queryString) {
		queryString = queryString.replace("\"", "'");
		return queryString;
	}


	public void deleteById(Object object,
			Integer deletingId) {
		// TODO Auto-generated method stub
		if(object!=null&&deletingId!=null){
			Object result = this.findById(object, deletingId);
			this.delete(result);
		}
	}

}