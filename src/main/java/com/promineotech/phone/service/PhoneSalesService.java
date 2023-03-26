/**
 * 
 */
package com.promineotech.phone.service;

import java.util.List;
import com.promineotech.phone.entity.Phone;

/**
 * @author Blestro
 *
 */
public interface PhoneSalesService {
  List<Phone> fetchPhones(String phone);
}
