/**
 * 
 */
package com.promineotech.phone.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.phone.dao.PhoneSalesDao;
import com.promineotech.phone.entity.Phone;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Blestro
 *
 */

@Service
@Slf4j
public class DefaultPhoneSalesService implements PhoneSalesService {
  
  @Autowired
  private PhoneSalesDao phoneSalesDao;
  
  @Override
  public List<Phone> fetchPhones(String phone) {
    log.info("FetchJeeps method was called with phone={}", phone);
    List<Phone> phones = phoneSalesDao.fetchPhones(phone);
    return phones;
  }
}
