package com.DailyGroceryShop.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.DailyGroceryShop.domain.ProdInventory;

@Service
public class ProdInventoryDaoImpl implements ProdInventoryDao {

		@Autowired
	    JdbcTemplate jdbcTemplate;

		@Override
		public void updateProdInvetoryBatch(List<ProdInventory> listProdInventory) {
		System.out.println("ProdInventoryDaoImpl updateProdInvetoryBatch "+ listProdInventory);
	        int[] updateCounts = jdbcTemplate.batchUpdate(
	                "update ProdInventory set quantity = ? where id = ?",
	                new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, listProdInventory.get(i).getQuantity());
	                        ps.setLong(2, listProdInventory.get(i).getId());
							
						}
						 public int getBatchSize() {
		                        return listProdInventory.size();
		                    }
	                } );
	    }

	}
	
	
	

