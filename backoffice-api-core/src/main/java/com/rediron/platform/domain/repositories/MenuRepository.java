package com.rediron.platform.domain.repositories;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.rediron.platform.domain.model.request.MenuItem;
import com.rediron.platform.domain.todo.OracleObjectMapper;
import com.tomax.api.UserIdentity;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleConnection;
import oracle.sql.StructDescriptor;

/**
 * @author lovanya.chaudhary
 *
 */
@Repository
public class MenuRepository {

	private static Logger LOGGER = LoggerFactory.getLogger(MenuRepository.class);

	@Autowired
	private DataSource dataSource;

	public List<HashMap<String, String>> getdisplayMenu(UserIdentity domainUser, int siteNo, int menuSeq,
			String userName) {
		LOGGER.info("=== Calling procedure to display menu items ===");
		Connection connection = DataSourceUtils.getConnection(dataSource);
		CallableStatement callableStatement = null;
		OracleCallableStatement ocs = null;
		HashMap<String, String> hm = new HashMap<>();
		List<HashMap<String, String>> menuList = new ArrayList<HashMap<String,String>>();
		try {
			callableStatement = connection.prepareCall("{call b_menuitems.display_menu_items(?,?,?,?,?)}");
			OracleConnection oracleConnection = (OracleConnection) callableStatement.getConnection();

			Array dtlArray = OracleObjectMapper.getArray(oracleConnection, "MENUITEM_O", "MENUITEM_TO",
					new ArrayList<MenuItem>());

			ocs = (OracleCallableStatement) oracleConnection
					.prepareCall("{call b_menuitems.display_menu_items(?,?,?,?,?)}");
			// userName will be provided by domainUser after testing phase
//			 ocs.setString(1, domainUser.getUserName());
			ocs.setString(1, userName);
			ocs.setInt(2, siteNo);

			if (menuSeq > 0)
				ocs.setInt(3, menuSeq);
			else
				ocs.setNull(3, menuSeq);
			ocs.setArray(4, dtlArray);
			ocs.setNull(5, 0);
			
			ocs.registerOutParameter(4, OracleTypes.ARRAY, "MENUITEM_TO");
			ocs.registerOutParameter(5, OracleTypes.VARCHAR);

			ocs.execute();
			StructDescriptor structDescriptor = StructDescriptor.createDescriptor("MENUITEM_O", oracleConnection);
			ResultSetMetaData metaData = structDescriptor.getMetaData();

			Object[] data = (Object[]) ((Array) ocs.getObject(4)).getArray();
			LOGGER.info("Other out param value ===== "+ocs.getString(5));
			int length = data.length;
			System.out.println("Length ======== " + length);
			for (Object tmp : data) {
				Struct row = (Struct) tmp;
				// Attributes are index 1 based...
				int idx = 1;

				if (row != null) {
					for (Object attribute : row.getAttributes()) {
						hm.put(metaData.getColumnName(idx), String.valueOf(attribute));
						LOGGER.info(metaData.getColumnName(idx) + " = " + attribute);
						++idx;
					}
					menuList.add(hm);
					hm = new HashMap<>();
				}
				LOGGER.info("=======================================================");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return menuList;
	}

}
