/**
 * 
 */
package com.promineotech.phone.dao;

import java.util.Optional;
import com.promineotech.phone.entity.Customer;

/**
 * @author Blestro
 *
 */
public interface PhoneCustomerDao {

  /**
   * @param email
   * @return
   */
  Optional<Customer> fetchCustomer(String email);

}
