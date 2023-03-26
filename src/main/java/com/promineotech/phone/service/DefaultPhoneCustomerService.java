/**
 * 
 */
package com.promineotech.phone.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.phone.dao.PhoneCustomerDao;
import com.promineotech.phone.entity.Customer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Blestro
 *
 */

@Service
@Slf4j
public class DefaultPhoneCustomerService implements PhoneCustomerService {

  @Autowired
  private PhoneCustomerDao phoneCustomerDao;
  
  @Override
  public Customer fetchCustomer(String email) {
    log.info("fetchCustomer method was called with email={}", email);
    Customer customer = getCustomer(email);
    
    return customer;
  }
  
  protected Customer getCustomer(String email) {
    return phoneCustomerDao.fetchCustomer(email)
        .orElseThrow(() -> new NoSuchElementException("Customer with email=" + email + " was not found"));
  }

}
