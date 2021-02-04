package com.rediron.platform.domain.todo;

// For testing only
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PojoUtils {
	
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PojoUtils.class);
	
    private PojoUtils() {
    }
    
    public static String addColMap(String colMap, String newField) {
        String tempColMap = colMap;
        if (newField != null && newField.length() > 0) {
            if (newField.charAt(0) != ',') {
                if (tempColMap == null || tempColMap.length() == 0 || tempColMap.charAt(tempColMap.length() - 1) != ',') {
                    newField = "," + newField;
                }
            }
            if (newField.charAt(newField.length() -1) != ',') {
                newField = newField + ",";
            }
            
            if (tempColMap == null || tempColMap.length() == 0) {
                tempColMap = newField;
            } else {
                tempColMap += newField;
            }
        }
        return tempColMap;
    }
    
    /**
     * Converts the <code>colMap</code> String as used in TIE imports into a <code>Map</code>
     * of the provided columns. Column names returned are in upper case.
     * 
     * @param colMap a comma-delimited list of column names
     * 
     * @return a set of column names
     */
    public static Set<String> getColumnNames(String colMap) {
        Set<String> set = new HashSet<String>();
        if (colMap != null && colMap.length() > 0) {
            String[] parts = colMap.split(",");
            for(String col : parts) {
                if (col != null && col.length() > 0) {
                    set.add(col.toUpperCase());
                }
            }
        }
        return set;
    }
}
