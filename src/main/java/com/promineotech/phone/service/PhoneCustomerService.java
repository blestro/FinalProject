/**
 * 
 */
package com.promineotech.phone.service;

import com.promineotech.phone.entity.Customer;

/**
 * @author Blestro
 *
 */
public interface PhoneCustomerService {

  /**
   * @param email
   * @return
   */
  Customer fetchCustomer(String email);

}
