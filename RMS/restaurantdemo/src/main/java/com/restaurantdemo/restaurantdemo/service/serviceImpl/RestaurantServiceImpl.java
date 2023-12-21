package com.restaurantdemo.restaurantdemo.service.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.restaurantdemo.restaurantdemo.model.RestaurantModel;
import com.restaurantdemo.restaurantdemo.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static final String selectTbQuery = "SELECT table_no, table_name FROM table_list;";
	public static final String selectAllQuery = "SELECT items_id, drinks.drinks_id, food, price, drinks, d_price FROM drinks FULL OUTER JOIN items ON items.drinks_id = drinks.drinks_id; ";
	public static final String selectOrderQuery = "SELECT o.table_no, i.items_id, o.drinks_id, i.food, i.price, o.qty FROM orders o INNER JOIN items i USING (items_id) WHERE o.table_no = ? UNION SELECT o.table_no, o.items_id, d.drinks_id, d.drinks, d.d_price, o.d_qty FROM orders o INNER JOIN drinks d USING (drinks_id) WHERE o.table_no = ?;";
	public static final String foodOrderQuery = "INSERT INTO orders (table_no, items_id, qty) SELECT ?, items_id, ? AS qty  FROM items WHERE items_id=? ON CONFLICT (table_no, items_id) DO UPDATE SET qty = orders.qty + EXCLUDED.qty;";
	public static final String drinkOrderQuery = "INSERT INTO orders (table_no, drinks_id, d_qty) SELECT ?, drinks_id, ? AS d_qty  FROM items WHERE drinks_id=? ON CONFLICT (table_no, drinks_id) DO UPDATE SET d_qty = orders.d_qty + EXCLUDED.d_qty;";
	public static final String desFoodQuery = "UPDATE orders SET qty=? WHERE table_no = ? AND items_id=?;";
	public static final String desDrinksQuery = "UPDATE orders SET d_qty=? WHERE table_no = ? AND drinks_id=?;";
	public static final String celFoodQuery = "DELETE FROM orders WHERE  table_no=? AND items_id=?;";
	public static final String celDrinkQuery = "DELETE FROM orders WHERE  table_no=? AND drinks_id=?;";
	public static final String deleteAllQuery = "DELETE FROM orders WHERE  table_no=?;";
	
	@Override
	public List<RestaurantModel> tableList() {
		List<RestaurantModel> tableList = jdbcTemplate.query(selectTbQuery, new RowMapper<RestaurantModel>() {

			@Override
			public RestaurantModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantModel resModel = new RestaurantModel();
				resModel.setTbId(rs.getInt("table_no"));
				resModel.setTableList(rs.getString("table_name"));
				return resModel;
			}
		});
		return tableList;
	}

	@Override
	public List<RestaurantModel> selectAllData() {
		List<RestaurantModel> menuList = jdbcTemplate.query(selectAllQuery, new RowMapper<RestaurantModel>() {

			@Override
			public RestaurantModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantModel resModel = new RestaurantModel();
				resModel.setFId(rs.getInt("items_id"));
				resModel.setDId(rs.getInt("drinks_id"));
				resModel.setFood(rs.getString("food"));
				resModel.setPrice(rs.getInt("price"));
				resModel.setDrink(rs.getString("drinks"));
				resModel.setDprice(rs.getInt("d_price"));
				return resModel;
			}

		});
		return menuList;
	}

	@Override
	public List<RestaurantModel> orderList(int table) {
		List<RestaurantModel>	orderList = jdbcTemplate.query(selectOrderQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, table);
				ps.setInt(2, table);
			}
		}, new RowMapper<RestaurantModel>() {

			@Override
			public RestaurantModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					RestaurantModel resModel = new RestaurantModel();
					resModel.setTbId(rs.getInt("table_no"));
					resModel.setFId(rs.getInt("items_id"));
					resModel.setDId(rs.getInt("drinks_id"));
					resModel.setFood(rs.getString("food"));
					resModel.setPrice(rs.getInt("price"));
					resModel.setItemNumber(rs.getInt("qty"));
					return resModel;
			}
		});
		return orderList;
	}

	@Override
	public int foodOrder(int fId, RestaurantModel resModel) {
		int result = jdbcTemplate.update(foodOrderQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, resModel.getTbId());
				ps.setInt(2, resModel.getItemNumber());
				ps.setInt(3, fId);
				
			}
		});
		return result;
	}

	@Override
	public int drinkOrder(int dId, RestaurantModel resModel) {
		int result = jdbcTemplate.update(drinkOrderQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, resModel.getTbId());
				ps.setInt(2, resModel.getItemNumber());
				ps.setInt(3, dId);
				
			}
		});
		return result;
	}

	@Override
	public int orderDecrease(int desOrder, RestaurantModel resModel) {
		if(resModel.getFId() != 0) {
			int result = jdbcTemplate.update(desFoodQuery, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, resModel.getItemNumber());
					ps.setInt(2, desOrder);
					ps.setInt(3, resModel.getFId());
				
				}
			});
			return result;
		}
		if(resModel.getDId() != 0) {
				int result = jdbcTemplate.update(desDrinksQuery, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, resModel.getItemNumber());
					ps.setInt(2, desOrder);
					ps.setInt(3, resModel.getDId());
				}
			});
			return result;
		}
		return 1;
	}

	@Override
	public int orderCancel(int cancelOrder, RestaurantModel resModel) {
		if(resModel.getFId() != 0) {
			int result = jdbcTemplate.update(celFoodQuery, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, cancelOrder);
					ps.setInt(2, resModel.getFId());
				
				}
			});
			return result;
		}
		if(resModel.getDId() != 0) {
				int result = jdbcTemplate.update(celDrinkQuery, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, cancelOrder);
					ps.setInt(2, resModel.getDId());
				}
			});
			return result;
		}
		return 1;
	}
// ShowCheck
	@Override
	public List<RestaurantModel> showCheck(int check) {
		List<RestaurantModel>	checkList = jdbcTemplate.query(selectOrderQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, check);
				ps.setInt(2, check);
			}
		}, new RowMapper<RestaurantModel>() {

			@Override
			public RestaurantModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					RestaurantModel resModel = new RestaurantModel();
					resModel.setTbId(rs.getInt("table_no"));
					resModel.setFId(rs.getInt("items_id"));
					resModel.setDId(rs.getInt("drinks_id"));
					resModel.setFood(rs.getString("food"));
					resModel.setPrice(rs.getInt("price"));
					resModel.setItemNumber(rs.getInt("qty"));
					int result = rs.getInt("price") *  rs.getInt("qty");
					resModel.setATotal(result);
					
					return resModel;
			}
		});
		 int totalAmount = total(checkList); 
		 for (RestaurantModel resModel : checkList) {
		        resModel.setTotal(totalAmount);
		    }
		return checkList;
	}
// this is method to sum total
	private int total(List<RestaurantModel> resModelList) {
	    int total = 0;
	    for (RestaurantModel resModel : resModelList) {
	        total += resModel.getATotal();
	    }
	    return total;
	}
// Show Check end here
	@Override
	public int deleteAll(int delete) {
		int result = jdbcTemplate.update(deleteAllQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, delete);
			}
		});
		return result;
	}
}

/*SELECT o.table_no, i.items_id, i.food, i.price, o.qty
FROM
    orders o
INNER JOIN items i USING (items_id)
WHERE o.table_no = ?
UNION
SELECT o.table_no, d.drinks_id, d.drinks, d.d_price, o.d_qty
FROM
    orders o
INNER JOIN drinks d USING (drinks_id);
WHERE o.table_no = ?*/ 
/*@Override
	public RestaurantModel orderList(int table) {
	RestaurantModel	resModel = jdbcTemplate.query(selectOrderQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, table);
				ps.setInt(2, table);
			}
		}, new ResultSetExtractor<RestaurantModel>() {
			
			@Override
			public RestaurantModel extractData(ResultSet rs) throws SQLException, DataAccessException {
				RestaurantModel resModel = new RestaurantModel();
				while (rs.next()) {
					resModel.setTbId(rs.getInt("table_no"));
					resModel.setFood(rs.getString("food"));
					resModel.setPrice(rs.getInt("price"));
					resModel.setItemNumber(rs.getInt("qty"));
				}
				return resModel;
			}
		});
		return resModel;
	}*//*SELECT o.table_no, i.items_id, i.food, i.price, o.qty FROM orders o INNER JOIN items i USING (items_id) WHERE o.table_no = ? UNION SELECT o.table_no, d.drinks_id, d.drinks, d.d_price, o.d_qty FROM orders o INNER JOIN drinks d USING (drinks_id) WHERE o.table_no = ?;*/
//SELECT o.table_no, i.items_id, o.drinks_id, i.food, i.price, o.qty FROM orders o INNER JOIN items i USING (items_id) WHERE o.table_no = 1 UNION SELECT o.table_no, o.items_id, d.drinks_id, d.drinks, d.d_price, o.d_qty FROM orders o INNER JOIN drinks d USING (drinks_id) WHERE o.table_no = 1;