package com.rediron.platform.domain.todo;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.Struct;
import java.sql.Types;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
 
@Component
public class OracleTest {
	
	@Autowired
	DataSource dataSource;
	
	public void executeProcedure() throws Exception {
		
		Connection connection = DataSourceUtils.getConnection(dataSource);
 
		final String typeName = "T_DEMO_OBJECT";
		final String typeTableName = "T_DEMO_OBJECTS";
 
		// Get a description of your type (Oracle specific)
		final StructDescriptor structDescriptor = StructDescriptor.createDescriptor(typeName.toUpperCase(), connection);		
		final ResultSetMetaData metaData = structDescriptor.getMetaData();
 
		// Call the procedure (or whatever else) that returns the table of a custom type
		CallableStatement cs = connection.prepareCall("{ CALL TMX.B_PROMOTION.CHK_NEW_SKU_PROMO(?, ? , ?, ?) }");
		cs.setInt(1, 400);
		cs.setInt(2, 110);
		cs.setInt(3, 10);
		// Result is an java.sql.Array...
		cs.registerOutParameter(4, Types.ARRAY, typeTableName);		
		cs.execute();
 
		// ...who's elements are java.sql.Structs
		Object[] data = (Object[]) ((Array) cs.getObject(4)).getArray();
		for(Object tmp : data) {
			Struct row = (Struct) tmp;
			// Attributes are index 1 based...
			int idx = 1;
			for(Object attribute : row.getAttributes()) {				
				System.out.println(metaData.getColumnName(idx) + " = " + attribute);											
				++idx;
			}
			System.out.println("---");
		}
		cs.close();	
 
		// See sandes comment, passing arrays of Objects
		// We need the array descriptor as well
		final ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor(typeTableName.toUpperCase(), connection);
		// Create generic object array
 
		// Create ARRAY from array of STRUCTS
		final ARRAY demoObjectsFromJava = new ARRAY(
			arrayDescriptor, 
			connection, 
			new STRUCT[] {
			    // STRUCTS are created with the struct descriptor and a generic object array containing the values of the
			    // attributes of the T_DEMO_OBJECT
			    new STRUCT(structDescriptor, connection, new Object[] {23, "Testobject 1"}),
			    new STRUCT(structDescriptor, connection, new Object[] {42, "Testobject 2"})
			}
		);
		cs = connection.prepareCall("{call p_receive_demo_objects(?)}");
		// setObject with the designated sql type
		cs.setObject(1, demoObjectsFromJava, Types.ARRAY);
		cs.execute();
		// no output here, have a look in table demo
		cs.close();
 
		connection.close();
	}
}
