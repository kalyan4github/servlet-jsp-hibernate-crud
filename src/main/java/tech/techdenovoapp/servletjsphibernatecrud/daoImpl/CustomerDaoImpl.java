package tech.techdenovoapp.servletjsphibernatecrud.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tech.techdenovoapp.servletjsphibernatecrud.dao.CustomerDao;
import tech.techdenovoapp.servletjsphibernatecrud.domain.Customer;
import tech.techdenovoapp.servletjsphibernatecrud.util.HibernateUtil;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private static CustomerDaoImpl customerDaoImpl = null;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    public Long saveCustomer(Customer customer) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(customer);
        transaction.commit();
        session.close();
        return id;
    }


    public void updateCustomer(Customer customer) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }


    public void deleteCustomer(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
        transaction.commit();
        session.close();
    }


    public Customer findCustomerById(Long id) {
        Session session =  this.sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }


    public List<Customer> findAllCustomers() {
        Session session = this.sessionFactory.openSession();
        List<Customer> customerList = session.createCriteria(Customer.class).list();
        session.close();
        return customerList;
    }
    public static CustomerDao getInstance(){
        if(customerDaoImpl == null)
            customerDaoImpl =  new CustomerDaoImpl();
        return customerDaoImpl;
    }
}
