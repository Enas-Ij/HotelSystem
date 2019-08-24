package root.restaurant.menu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MenuDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERT="insert into menu" +
            "(ItemName,ItemPrice,section ) " +
            " values(?,?,?)";
    private static final String UPDATE_PRICE="update menu" +
            " set ItemPrice=? where ItemName=?";
    private static final String UPDATE_SECTION="update menu" +
            " set section=? where ItemName=?";
    private static final String SELECT_ITEM="select ItemName from menu where section!=\"removed\" ";
    private static final String SELECT_Price="select ItemPrice from menu where ItemName=?";
    private static final String SELECT_SECTION="select section from menu where ItemName=?";



    public int insertItem(String name, Float price, String section){

        return jdbcTemplate.update(INSERT, name, price, section);
    }

    public int updatePrice(String name, Float price){

        return jdbcTemplate.update(UPDATE_PRICE, price, name );
    }


    public int updateSection(String name, String section){

        return jdbcTemplate.update(UPDATE_SECTION, section, name);
    }


    public List<String> selectItemName(){

        List<String> items= jdbcTemplate.query(SELECT_ITEM

                ,new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        String item=resultSet.getString("ItemName");
                        return item;
                    }}

        );

        return items;
    }


    public  Float selectPrice(final String itemName){

        List<Float> priceList= jdbcTemplate.query(SELECT_Price


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,itemName);
                    }
                }


                ,new RowMapper<Float>() {

                    @Override
                    public Float mapRow(ResultSet resultSet, int i) throws SQLException {
                        Float price=resultSet.getFloat("ItemPrice");
                        return price;
                    }}

        );


        return priceList.get(0);
    }



    public  String selectSection(final String itemName){

        List<String> sectionList= jdbcTemplate.query(SELECT_SECTION


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,itemName);
                    }
                }


                ,new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        String section=resultSet.getString("section");
                        return section;
                    }}

        );


        return sectionList.get(0);
    }

}
