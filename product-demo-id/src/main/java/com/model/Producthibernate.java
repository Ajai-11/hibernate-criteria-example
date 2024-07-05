package com.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class Producthibernate {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory sessionFactory = Hbutil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Product.class);
		List<Product> prList = criteria.list();
		for(Product pr : prList) {
			System.out.println("Slno= "+ pr.getSlno()+" "+ pr.getName()+ "  "+ pr.getQty()+ "  "+pr.getPrice());
		}
		//select the product greater than 500
	   criteria = session.createCriteria(Product.class).add(Restrictions.gt("price", 500.0));
	   prList = criteria.list();
        for(Product pr : prList) {
          System.out.println("Price > 500 -> Slno= " + pr.getSlno() + " " + pr.getName() + " " + pr.getQty() + " " + pr.getPrice());
        }
        //have greater than 10 products
        criteria = session.createCriteria(Product.class).add(Restrictions.gt("qty", 10));
        prList = criteria.list();
        for(Product pr : prList) {
           System.out.println("Qty > 10 -> Slno= " + pr.getSlno() + " " + pr.getName() + " " + pr.getQty() + " " + pr.getPrice());
        }
        //Select number of products available
       criteria = session.createCriteria(Product.class).setProjection(Projections.rowCount());
       Long count = (Long) criteria.uniqueResult();
       System.out.println("Number of products available = " + count);

       // Select highest and lowest amount product
       criteria = session.createCriteria(Product.class).setProjection(Projections.max("price"));
       Double maxPrice = (Double) criteria.uniqueResult();
       System.out.println("Highest amount product price = " + maxPrice);

       criteria = session.createCriteria(Product.class).setProjection(Projections.min("price"));
       Double minPrice = (Double) criteria.uniqueResult();
       System.out.println("Lowest amount product price = " + minPrice);

       // Select sum of all available products
       criteria = session.createCriteria(Product.class).setProjection(Projections.sum("price"));
       Double totalSum = (Double) criteria.uniqueResult();
       System.out.println("Sum of all available products = " + totalSum);  
		
		
		tx.commit();
		sessionFactory.close();
	}
}