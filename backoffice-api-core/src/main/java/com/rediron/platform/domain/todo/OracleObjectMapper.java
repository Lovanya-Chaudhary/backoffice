package com.rediron.platform.domain.todo;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleArray;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleStruct;
import oracle.jdbc.OracleTypeMetaData;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * This class helps translate between Java and Oracle objects.
 */
// For testing only
public class OracleObjectMapper {

    private static final Log OOM_LOG = LogFactory.getLog(OracleObjectMapper.class);
    @SuppressWarnings("deprecation")
    private static Map<String, ArrayDescriptor> arrayDescHash = new Hashtable<String, ArrayDescriptor>();
    @SuppressWarnings("deprecation")
    private static Map<String, StructDescriptor> structDescHash = new Hashtable<String, StructDescriptor>();
    private static Map<String, List<OraMetaData>> structMetaData = new Hashtable<String, List<OraMetaData>>();

    protected OracleObjectMapper() {
    }

    /**
     * This method created a {@link OraMetaData} for the given Struct.
     * 
     *  <p><strong>NB:</strong> This method should go away as we migrate everything
     *  to the Oracle 12c APIs.</p>
     * 
     * @param objectName
     * @param sd
     * @throws SQLException
     */
    @SuppressWarnings("deprecation")
    private static void setMetaData(String objectName, StructDescriptor sd) throws SQLException {
        ResultSetMetaData meta = sd.getMetaData();
        List<OraMetaData> dataList = new ArrayList<OracleObjectMapper.OraMetaData>();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            OraMetaData metaData = new OraMetaData();
            metaData.setColumnNumber(i);
            metaData.setColumnName(meta.getColumnName(i).replaceAll("_", ""));
            metaData.setColumnType(meta.getColumnTypeName(i));
            dataList.add(metaData);
        }
        structMetaData.put(objectName, dataList);
    }

    /**
     * This method obtains a {@link StructDescriptor} for the given Struct type.
     * 
     *  <p><strong>NB:</strong> This method should go away as we migrate everything
     *  to the Oracle 12c APIs.</p>
     * 
     * @param con
     * @param objectName the name of the Struct type.
     * @throws SQLException
     */
    @SuppressWarnings("deprecation")
    private static StructDescriptor getStructDescriptor(Connection con, String objectName) throws SQLException {
        if (!structDescHash.containsKey(objectName)) {
            StructDescriptor sd = StructDescriptor.createDescriptor(objectName, con);
            setMetaData(objectName, sd);
            structDescHash.put(objectName, sd);
        }
        return structDescHash.get(objectName);
    }

    /**
     * This method obtains a {@link ArrayDescriptor} for the given Struct type.
     * 
     *  <p><strong>NB:</strong> This method should go away as we migrate everything
     *  to the Oracle 12c APIs.</p>
     * 
     * @param con
     * @param objectName the name of the Struct type.
     * @throws SQLException
     */
    @SuppressWarnings("deprecation")
    private static ArrayDescriptor getArrayDescriptor(Connection con, String objectName) throws SQLException {
        if (!arrayDescHash.containsKey(objectName)) {
            arrayDescHash.put(objectName, ArrayDescriptor.createDescriptor(objectName, con));
        }
        return arrayDescHash.get(objectName);
    }

    /**
     * This method converts a POJO to an array suitable for an SQL Struct.
     * 
     *  <p><strong>NB:</strong> This method should go away as we migrate everything
     *  to the Oracle 12c APIs.</p>
     *  
     * @param sd
     * @param objectName
     * @param obj
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    private static Object[] getObjectAttributes(StructDescriptor sd, String objectName, Object obj) throws Exception {
        Object[] attributes = new Object[sd.getLength()];
        if (obj == null) {
        	return attributes;
        }
        List<OraMetaData> metaList = structMetaData.get(objectName);
        Class<?> javaClass = obj.getClass();
        Map<String,Field> fieldMap = getFieldMap(javaClass);
        
        for(OraMetaData meta : metaList) {
        	Integer columnNumber = meta.getColumnNumber() - 1;
        	String columnType = meta.getColumnType();
        	String columnName = meta.getColumnName().toUpperCase();
        	String fixedName = columnName.replace("_", "");
        	Object value = null;
        	
        	Field field = fieldMap.get(columnName);
        	if (field == null) {
        		field = fieldMap.get(fixedName);
        	}
        	if (field != null) {
        		field.setAccessible(true);
        		value = field.get(obj);
        		if (value != null && columnType != null && (columnType.contains("DATE") || columnType.contains("TIMESTAMP")) ) {
        			value = new Timestamp(((java.util.Date)value).getTime());
        		}
        	} else {
        		OOM_LOG.debug("Java class " + javaClass.getName() + " does not have field " + meta.getColumnName());
        	}
        	attributes[columnNumber] = value;
        }
        
        return attributes;
    }

    /**
     * Static method used to get a STRUCT object from the java object passed.
     * 
     *  <p><strong>NB:</strong> This method should go away as we migrate
     *  everything to the Oracle 12c APIs.</p>
     *  
     * @param con
     *            - Database connection
     * @param objectName
     *            - Single object type Example: <tt>ITEM_O</tt>
     * @param obj
     *            - Java object to be transformed
     * @return - Returns a STRUCT of the object passed
     * @throws Exception
     * 
     * @deprecated as of EI Commons 1.11 use {@link #getStruct(Connection, String, Object)} instead.
     */
    public static STRUCT getSTRUCT(OracleConnection con, String objectName, Object obj) throws Exception {
        if (OOM_LOG.isTraceEnabled()) {
            OOM_LOG.trace("Beginning getStruct: " + new Date().getTime());
        }
        OracleConnection oracleCon =  con;
        Object[] attributes = getObjectAttributes(getStructDescriptor(oracleCon, objectName), objectName, obj);
        STRUCT s = new STRUCT(getStructDescriptor(oracleCon, objectName), oracleCon, attributes);
        if (OOM_LOG.isTraceEnabled()) {
            OOM_LOG.trace("Ending getStruct: " + new Date().getTime());
        }
        return s;
    }
    
    /**
     * Static method used to get an OracleStruct object from the Java object passed.
     * 
     * @param con
     *            - Database connection
     * @param objectName
     *            - Single object type Example: <tt>ITEM_O</tt>
     * @param obj
     *            - Java object to be transformed
     * @return - Returns a STRUCT of the object passed
     * @throws Exception
     * 
     * @Since EI Commons 1.11
     */
    public static OracleStruct getStruct(OracleConnection con, String objectName, Object obj) throws Exception {
    	/*
    	 * TODO: we need to find an appropriate method for creating the struct without
    	 * relying on deprecated classes
    	 */
    	return getSTRUCT(con, objectName, obj);
    }
    
    /**
     * Used to convert a Java {@code Object} to something you can pass to Oracle.
     * This will convert simple types (such as {@code VARCHAR}) to simple values,
     * while converting complex types to a {@code Struct}.
     * 
     * @param con
     * @param objectName
     * @param obj
     * @return
     * @throws Exception
     * 
     * @since EI Commons 1.11
     */
    private static Object toOracle(OracleConnection con, String objectName, Object obj) throws Exception {
    	if (obj == null) {
    		return null;
    	}
    	if ("VARCHAR".equalsIgnoreCase(objectName) || "VARCHAR2".equalsIgnoreCase(objectName)) {
    		return SimpleConversion.convertToString(obj);
    	} else {
    		/*
    		 * TODO: other simple types
    		 */
    		return getStruct(con, objectName, obj);
    	}
    }
    
    private static Map<String,Field> getFieldMap(Class<?> javaClass) {
    	Map<String,Field> fieldMap = new HashMap<String,Field>();
    	Field[] fields = ReflectUtil.getAllFields(javaClass);
		for(Field field : fields) {
			String name = field.getName().toUpperCase();
			String fixedName = name.replace("_", "");
			if (! fieldMap.containsKey(name)) {
				fieldMap.put(name, field);
			}
			if (! fieldMap.containsKey(fixedName)) {
				fieldMap.put(fixedName, field);
			}
		}
		return fieldMap;
    }

    /**
     * Static method used to get an ARRAY object from the Map object passed.
     * 
     *  <p><strong>NB:</strong> This method should go away as we migrate
     *  everything to the Oracle 12c APIs.</p>
     *  
     * @param con
     *            - Database connection
     * @param objectName
     *            - Single object type. Example: ITEM_O
     * @param arrayObjectName
     *            - Array object type. Example: ITEM_TO
     * @param objectMap
     *            - Map of objects passed
     * @return - Returns an ARRAY object of the Map object passed
     * @throws Exception
     * 
     * @deprecated as of EI Commons 1.11 use {@link #getArray(Connection, String, String, Map)} instead
     */
    public static ARRAY getARRAY(OracleConnection con, String objectName, String arrayObjectName, Map<String, ?> objectMap) throws Exception {
        if (OOM_LOG.isTraceEnabled()) {
            OOM_LOG.trace("Beginning Map getArray: " + new Date().getTime());
        }
        OracleConnection oracleCon = con;
        ARRAY objArray = null;
        int i = 0;
        if (objectMap != null) {
            Object[] structArray = new Object[objectMap.size()];
            for (Map.Entry<String, ?> structEntry : objectMap.entrySet()) {
                structArray[i++] = toOracle(oracleCon, objectName, objectMap.get(structEntry.getKey()));
            }
            objArray = new ARRAY(getArrayDescriptor(oracleCon, arrayObjectName), oracleCon, structArray);
        }
        if (OOM_LOG.isTraceEnabled()) {
            OOM_LOG.trace("Ending Map getArray: " + new Date().getTime());
        }
        return objArray;
    }

    /**
     * Static method used to get an Array object from the Map object passed.
     * 
     * @param con
     *            - Database connection
     * @param objectName
     *            - Single object type. Example: {@code ITEM_O}
     * @param arrayObjectName
     *            - Array object type. Example: {@code ITEM_TO}
     * @param objectMap
     *            - Map of objects passed
     * @return - Returns an Array object of the Map object passed
     * @throws Exception
     * 
     * @Since EI Commons 1.11
     */
    public static OracleArray getArray(OracleConnection con, String objectName, String arrayObjectName, Map<String, ?> objectMap) throws Exception {
    	/*
    	 * TODO: create Array using non-deprecated classes
    	 */
    	return getARRAY(con, objectName, arrayObjectName, objectMap);
    }
    
    /**
     * Static method used to get an ARRAY object from the list of objects
     * passed.
     * 
     *  <p><strong>NB:</strong> This method should go away as we migrate
     *  everything to the Oracle 12c APIs.</p>
     *  
     * @param con
     *            - Database connection
     * @param objectName
     *            - Single object type Example: {@code ITEM_O} or {@code VARCHAR}
     * @param arrayObjectName
     *            - Array object type. Example: {@code ITEM_TO} or {@code STRARRAY}
     * @param objectList
     *            - List of objects passed.
     * @return - Returns an ARRAY object of the list of objects passed.
     * @throws Exception
     * 
     * @deprecated as of EI Commons 1.11 use {@link #getArray(Connection, String, String, List)} instead
     */
    public static ARRAY getARRAY(OracleConnection con, String objectName, String arrayObjectName, List<?> objectList) throws Exception {
        if (OOM_LOG.isTraceEnabled()) {
            OOM_LOG.trace("Beginning List getArray: " + new Date().getTime());
        }
        OracleConnection oracleCon = con;
        ARRAY objArray = null;
        int i = 0;
        if (objectList != null) {
            Object[] structArray = new Object[objectList.size()];
            for (Object o : objectList) {
                structArray[i++] = toOracle(oracleCon, objectName, o);
            }
            objArray = new ARRAY(getArrayDescriptor(oracleCon, arrayObjectName), oracleCon, structArray);
        }
        if (OOM_LOG.isTraceEnabled()) {
            OOM_LOG.trace("Ending List getArray: " + new Date().getTime());
        }
        return objArray;
    }
    
    /**
     * Static method used to get an ARRAY object from the list of objects
     * passed.
     * 
     * @param con
     *            - Database connection
     * @param objectName
     *            - Single object type Example: {@code ITEM_O} or {@code VARCHAR}
     * @param arrayObjectName
     *            - Array object type. Example: {@code ITEM_TO} or {@code STRARRAY}
     * @param objectList
     *            - List of objects passed.
     * @return - Returns an ARRAY object of the list of objects passed.
     * @throws Exception
     */
    public static OracleArray getArray(OracleConnection con, String objectName, String arrayObjectName, List<?> objectList) throws Exception {
    	/*
    	 * TODO: create array without using deprecated classes
    	 */
    	return getARRAY(con, objectName, arrayObjectName, objectList);
    }
    
    //FIXME:
    
    /**
     * Static method used to get an ARRAY object from the list of objects
     * passed.
     * 
     *  <p><strong>NB:</strong> This method should go away as we migrate
     *  everything to the Oracle 12c APIs.</p>
     *  
     * @param con
     *            - Database connection
     * @param objectName
     *            - Single object type Example: {@code ITEM_O}
     * @param arrayObjectName
     *            - Array object type. Example: {@code ITEM_TO}
     * @param objectList
     *            - List of objects passed.
     * @return - Returns an ARRAY object of the list of objects passed.
     * @throws Exception
     * 
     * @deprecated as of EI Commons 1.11 use {@link #getArray(Connection, String, String, List)} instead
     */
    public static <T> ARRAY getARRAY(OracleConnection con, String objectName, String arrayObjectName, T[] objectList) throws Exception {
        if (OOM_LOG.isTraceEnabled()) {
            OOM_LOG.trace("Beginning List getArray: " + new Date().getTime());
        }
        OracleConnection oracleCon = con;
        ARRAY objArray = null;
        int i = 0;
        if (objectList != null) {
            Object[] structArray = new Object[objectList.length];
            for (Object o : objectList) {
                structArray[i++] = toOracle(oracleCon, objectName, o);
            }
            objArray = new ARRAY(getArrayDescriptor(oracleCon, arrayObjectName), oracleCon, structArray);
        }
        if (OOM_LOG.isTraceEnabled()) {
            OOM_LOG.trace("Ending List getArray: " + new Date().getTime());
        }
        return objArray;
    }
    
    /**
     * Static method used to get an ARRAY object from the list of objects
     * passed.
     * 
     * @param con
     *            - Database connection
     * @param objectName
     *            - Single object type Example: {@code ITEM_O}
     * @param arrayObjectName
     *            - Array object type. Example: {@code ITEM_TO}
     * @param objectList
     *            - List of objects passed.
     * @return an ARRAY object of the list of objects passed.
     * @throws Exception
     * 
     * @since EI Commons 1.11
     */
    public static <T> OracleArray getArray(OracleConnection con, String objectName, String arrayObjectName, T[] objectList) throws Exception {
    	/*
    	 * TODO: use non-deprecated classes / methods
    	 */
    	return getARRAY(con, objectName, arrayObjectName, objectList);
    }
    
    /**
     * 
     *  <p><strong>NB:</strong> This method should go away as we migrate
     *  everything to the Oracle 12c APIs.</p>
     *  
     * @param objArray
     * @param javaClass
     * @return
     * @throws Exception
     * 
     * @deprecated as of EI Commons 1.11 use {@link #getList(OracleArray, Class)} instead.
     */
    public static <T> List<T> getList(ARRAY objArray, Class<? extends T> javaClass) throws Exception {
    	if (objArray == null) {
    		return null;
    	}
    	//TODO: handle simple types
    	Map<String,Field> fieldMap = getFieldMap(javaClass);

    	List<T> objects = new ArrayList<T>();
    	Object[] array = (Object[])objArray.getArray();
    	for (int i = 0; i < array.length; i++) {
    		T object = javaClass.newInstance();
    		STRUCT struct = (STRUCT) array[i];
    		Object[] attributes = struct.getAttributes();
    		StructDescriptor sd = struct.getDescriptor();
    		ResultSetMetaData meta = sd.getMetaData();
            int colCount = meta.getColumnCount();
			for (int j = 1; j <= colCount; j++) {
            	String colName = meta.getColumnName(j).toUpperCase();
            	String fixedName = colName.replaceAll("_", "");
            	Field field = fieldMap.get(colName);
            	if (field == null) {
            		field = fieldMap.get(fixedName);
            	}
            	if (field != null) {
            		field.setAccessible(true);
                   	field.set(object, SimpleConversion.convert(attributes[j-1], field.getType()));
            	}
            }
            objects.add(object);
    	}
    	return objects;
    }
    
    /**
     * Converts an {@link OracleArray} to a {@link List} of POJOs.
     * 
     * @param objArray
     * @param javaClass
     * @return
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * 
     * @since EI Commons 1.11
     */
    public static <T> List<T> getList(OracleArray objArray, Class<? extends T> javaClass) throws SQLException, InstantiationException, IllegalAccessException {
    	if (objArray == null) {
    		return null;
    	}
    	
    	//TODO: handle simple types
    	
    	Map<String,Field> fieldMap = getFieldMap(javaClass);

    	List<T> objects = new ArrayList<T>();
    	Object[] array = (Object[])objArray.getArray();
    	for (int i = 0; i < array.length; i++) {
    		T object = javaClass.newInstance();
    		OracleStruct struct = (OracleStruct) array[i];
    		Object[] attributes = struct.getAttributes();
    		OracleTypeMetaData.Struct typeMetaData = (OracleTypeMetaData.Struct)struct.getOracleMetaData();
    		ResultSetMetaData meta = typeMetaData.getMetaData();
            int colCount = meta.getColumnCount();
    		for (int j = 1; j <= colCount; j++) {
            	String colName = meta.getColumnName(j).toUpperCase();
            	String fixedName = colName.replaceAll("_", "");
            	Field field = fieldMap.get(colName);
            	if (field == null) {
            		field = fieldMap.get(fixedName);
            	}
            	if (field != null) {
            		field.setAccessible(true);
                   	field.set(object, SimpleConversion.convert(attributes[j-1], field.getType()));
            	}
            }
            objects.add(object);
    	}
    	return objects;
    }

    private static class OraMetaData {
        private Integer columnNumber;
        private String columnName;
        private String columnType;

        public OraMetaData() {
        }

		public Integer getColumnNumber() {
            return columnNumber;
        }

        public void setColumnNumber(Integer columnNumber) {
            this.columnNumber = columnNumber;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnType() {
            return columnType;
        }

        public void setColumnType(String columnType) {
            this.columnType = columnType;
        }

    }
}