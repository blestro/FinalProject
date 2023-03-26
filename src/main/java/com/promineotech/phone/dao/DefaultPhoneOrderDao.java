/**
 * 
 */
package com.promineotech.phone.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import com.promineotech.phone.entity.Customer;
import com.promineotech.phone.entity.Order;
import com.promineotech.phone.entity.OrderDeleteRequest;
import com.promineotech.phone.entity.OrderTypeRequest;
import com.promineotech.phone.entity.Phone;
import com.promineotech.phone.entity.PhoneType;

/**
 * @author Blestro
 *
 */

@Service
public class DefaultPhoneOrderDao implements PhoneOrderDao{

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public Order saveOrder(Customer customer, List<Phone> phones, BigDecimal price, String orderType) {
      SqlParams params =  generateInsertSql(customer, price, orderType);
      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(params.sql, params.source, keyHolder);
      
      Long orderPk = keyHolder.getKey().longValue();
      saveOptions(phones, orderPk);
      
      return Order.builder()
          .orderPK(orderPk)
          .customer(customer)
          .phones(phones)
          .build();
  }
  
  private void saveOptions(List<Phone> phones, Long orderPK) {
    for (Phone phone: phones) {
      SqlParams params = generateInsertSql(phone, orderPK);
      jdbcTemplate.update(params.sql, params.source);
    }
  }
  
  @Override
  public void updateOrderType(@Valid OrderTypeRequest orderTypeRequest) {
    // @formatter:off
    String sql = ""
        + "UPDATE orders "
        + "SET order_type = :order_type "
        + "WHERE order_pk = :order_pk";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("order_type", orderTypeRequest.getOrderType());
    params.put("order_pk", orderTypeRequest.getOrderNumber());
    
    jdbcTemplate.update(sql, params);
  }
  
  @Override
  public void deleteOrder(@Valid OrderDeleteRequest orderNumber) {
 // @formatter:off
    String sql = ""
        + "DELETE FROM orders "
        + "WHERE order_pk = :order_pk";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("order_pk", orderNumber.getOrderNumber());
    
    jdbcTemplate.update(sql, params);
    
  }
  
  private SqlParams generateInsertSql(Customer customer, BigDecimal price, String orderType) {
    // @formatter:off
    String sql = ""
        + "INSERT INTO orders ("
        + "customer_fk, price, order_type"
        + ") VALUES ("
        + ":customer_fk, :price, :order_type"
        + ")";
    // @formatter:on
    
    SqlParams params = new SqlParams();
    
    params.sql = sql;
    params.source.addValue("customer_fk", customer.getCustomerPK());
    params.source.addValue("price", price);
    params.source.addValue("order_type", orderType);
    
    return params;
  }
  
  private SqlParams generateInsertSql(Phone phone, Long orderPK) {
    SqlParams params = new SqlParams();
    
    // @formatter:off
    params.sql = ""
        + "INSERT INTO order_phones ("
        + "phone_fk, order_fk"
        + ") VALUES ("
        + ":option_fk, :order_fk"
        + ")";
    // @formatter:on
    
    params.source.addValue("option_fk", phone.getPhonePk());
    params.source.addValue("order_fk", orderPK);
    
    return params;
  }
  
  @Override
  public List<Phone> fetchPhones(List<@NotNull Phone> list) {
    List<Phone> phones = new LinkedList<>();
    
    for (Phone phone: list) {
      Phone opt_phone = fetchPhone(phone.getPhoneId(), 
          phone.getTrimLevel(), 
          phone.getStorage(), 
          phone.getBrand(), 
          phone.getColor())
          .orElseThrow(() -> new NoSuchElementException("Phone with ID=" + phone.getPhoneId() 
          + ", trim=" + phone.getTrimLevel()
          + ", storage=" + phone.getStorage()
          + ", brand=" + phone.getBrand()
          + " was not found"));
            
      
      phones.add(opt_phone);
    }
        
    return phones;
  }
  
  @Override
  public Optional<Phone> fetchPhone(PhoneType phone, String trimLevel, Long storage, String brand,
      String color) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM phones "
        + "WHERE phone_id = :phone_id "
        + "AND trim_level = :trim_level "
        + "AND storage = :storage "
        + "AND brand = :brand";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("phone_id", phone.toString());
    params.put("trim_level", trimLevel);
    params.put("storage", storage);
    params.put("brand", brand);

    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new PhoneResultSetExtractor()));
  }

  @Override
  public Optional<Customer> fetchCustomer(String customer) {
 // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM customers "
        + "WHERE customer_id = :customer_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customer);

    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
  }

  
  
 class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {
    
    @Override
    public Customer extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Customer.builder()
          .customerId(rs.getString("customer_id"))
          .customerPK(rs.getLong("customer_pk"))
          .firstName(rs.getString("first_name"))
          .lastName(rs.getString("last_name"))
          .email(rs.getString("email"))
          .build();
      // @formatter:on

    }
  }
 
 class PhoneResultSetExtractor implements ResultSetExtractor<Phone> {
   @Override
   public Phone extractData(ResultSet rs) throws SQLException {
     rs.next();

     // @formatter:off
     return Phone.builder()
         .phonePk(rs.getLong("phone_pk"))
         .phoneId(PhoneType.valueOf(rs.getString("phone_id")))
         .trimLevel(rs.getString("trim_level"))
         .storage(rs.getLong("storage"))
         .brand(rs.getString("brand"))
         .color(rs.getString("color"))
         .basePrice(rs.getBigDecimal("base_price"))
         .build();
     // @formatter:on
   }
 }
 
 
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }

  @Override
  public Order getOrder(Long orderNumber, Customer customer) {
 // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM orders "
        + "WHERE order_pk = :order_pk ";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("order_pk", orderNumber);

    return Order.builder()
        .orderPK(orderNumber)
        .customer(customer)
        .orderType(sql)
        .build();
  }

}
