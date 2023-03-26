/**
 * 
 */
package com.promineotech.phone.dao;

import java.util.List;
import com.promineotech.phone.entity.Phone;

/**
 * @author Blestro
 *
 */
public interface PhoneSalesDao {

  /**
   * @param model
   * @return
   */
  List<Phone> fetchPhones(String phone);

}
