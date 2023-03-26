/**
 * 
 */
package com.promineotech.phone.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.phone.entity.Customer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Blestro
 *
 */

@Service
@Slf4j
public class DefaultPhoneCustomerDao implements PhoneCustomerDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public Optional<Customer> fetchCustomer(String email) {
    log.info("DAO: Email={}", email);
    
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM customers "
        + "WHERE email = :email";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("email", email.toString());
    
    return Optional.ofNullable(jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
  }
  
  class CustomerResultSetExtractor implements ResultSetExtractor<Customer>{

    @Override
    public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
      rs.next();
      return Customer.builder()
          .customerPK(rs.getLong("customer_pk"))
          .customerId(rs.getString("customer_id"))
          .firstName(rs.getString("first_name"))
          .lastName(rs.getString("last_name"))
          .email(rs.getString("email"))
          .build();
    }
    
  }
  

}
