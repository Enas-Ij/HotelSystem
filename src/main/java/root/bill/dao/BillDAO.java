package root.bill.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BillDAO {

   @Autowired
    JdbcTemplate jdbcTemplate;

  private static String INSERT = "INSERT INTO bill VALUES(?,?,?,?)";



  public int insert(Integer reservationId, Float roomCharge, Float otherCharges, Float totalCharge){

      return jdbcTemplate.update(INSERT, reservationId, roomCharge,otherCharges, totalCharge);
  }

}
