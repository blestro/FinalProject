/**
 * 
 */
package com.promineotech.phone.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.phone.entity.Phone;
import com.promineotech.phone.entity.PhoneType;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Blestro
 *
 */
@Service
@Slf4j
public class DefaultPhoneSalesDao implements PhoneSalesDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Phone> fetchPhones(String phone) {
    log.info("DAO: phone={}", phone);
    
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM phones "
        + "WHERE phone_id = :phone_id";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("phone_id", phone.toString());
    
    return jdbcTemplate.query(sql, params, new RowMapper<>() {

      @Override
      public Phone mapRow(ResultSet rs, int rowNum) throws SQLException {
        // formatter:off
        return Phone.builder()
            .phonePk(rs.getLong("phone_pk"))
            .phoneId(PhoneType.valueOf(rs.getString("phone_id")))
            .trimLevel(rs.getString("trim_level"))
            .storage(rs.getLong("storage"))
            .brand(rs.getString("brand"))
            .color(rs.getString("color"))
            .basePrice(rs.getBigDecimal("base_price"))
            .build();
        // formatter:on
      }});
  }

}
