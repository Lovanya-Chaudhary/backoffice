package com.rediron.platform.domain.services;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.entities.Adhdr;
import com.rediron.platform.domain.entities.AdhdrPK;
import com.rediron.platform.domain.entities.PromoExcept;
import com.rediron.platform.domain.entities.PromoExceptPK;
import com.rediron.platform.domain.repositories.AdhdrRepository;
import com.rediron.platform.domain.repositories.ItemRepository;
import com.rediron.platform.domain.repositories.PromoExceptRepository;
import com.rediron.platform.domain.response.SkuPromo;
import com.rediron.platform.domain.todo.OracleObjectMapper;
import com.rediron.platform.domain.todo.PriceChangePojo;
import com.tomax.api.UserIdentity;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleConnection;

/**
 * @author lovanya.chaudhary
 *
 */
@Service
public class ItemService {
	
	Logger logger = LoggerFactory.getLogger(ItemService.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	AdhdrRepository adhdrRepository;
	
	@Autowired
	UtilityService testService;
	
	@Autowired
	LookUpService lookUpService;
	
	@Autowired
	PromoExceptRepository promoRepository;
	
	public BigDecimal getSkuNumber(int skuNumber, String itemType) {

		jdbcTemplate.setResultsMapCaseInsensitive(true);
		
		// overloaded function need to check with Venkat / Girish

		SimpleJdbcCall jdbcCall1 = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName("tmx")
				.withCatalogName("b_item") // package procedure																														// name
				.withFunctionName("get_sku_no")				
				.declareParameters(new SqlOutParameter("number", Types.DECIMAL))
				.declareParameters(new SqlParameter("p_item_id", Types.BIGINT))
				.declareParameters(new SqlParameter("p_item_type", Types.VARCHAR))
				.withoutProcedureColumnMetaDataAccess();
		
		SqlParameterSource paramMap = new MapSqlParameterSource()
				.addValue("p_item_id", skuNumber)
				.addValue("p_item_type", itemType);
		
		BigDecimal value = testService.getDataFromFunction("tmx", "b_item", "get_sku_no", paramMap, BigDecimal.class,
				new SqlOutParameter("number", Types.DECIMAL),
				new SqlParameter("p_item_id", Types.BIGINT),
				new SqlParameter("p_item_type", Types.VARCHAR));
		
		System.out.println("test service value ==>> "+value);

		BigDecimal skuNumberExisting = jdbcCall1.executeFunction(BigDecimal.class, paramMap);

		if(skuNumberExisting != null)
			System.out.println("Is sku "+skuNumber+" existing ==>> " + skuNumberExisting.intValue());
		
		return skuNumberExisting;
	}
	
	// movement function verify once
	public BigDecimal checkMovement(int skuNumber) {

		jdbcTemplate.setResultsMapCaseInsensitive(true);
		
		// overloaded function need to check with Venkat / Girish

		SimpleJdbcCall jdbcCall1 = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName("tmx")
				.withCatalogName("b_item") // package procedure																														// name
				.withFunctionName("item_has_movement");
		
		SqlParameterSource paramMap = new MapSqlParameterSource()
				.addValue("pn_sku_no", skuNumber);

		BigDecimal moveCount = jdbcCall1.executeFunction(BigDecimal.class, paramMap);
		
		BigDecimal value = testService.getDataFromFunction("tmx", "b_item", "item_has_movement", paramMap, BigDecimal.class,
				new SqlOutParameter("number", Types.DECIMAL),
				new SqlParameter("pn_sku_no", Types.BIGINT));
		
		System.out.println("test service value ==>> "+value);

		if(moveCount != null)
			System.out.println("Sku "+skuNumber+" is in movement process if value is 1 else not ==>> " + moveCount.intValue());
		
		return moveCount;
	}
	
	// test proc starts here	
	public String processPromoDtl(List<PriceChangePojo> promoDtlList) throws Exception {
		Connection connection = DataSourceUtils.getConnection(dataSource);
		CallableStatement callableStatement = null;
		OracleCallableStatement ocs = null;
        try {
            callableStatement = connection.prepareCall("{call b_promotion.process(?,?,?)}");
            OracleConnection oracleConnection = (OracleConnection) callableStatement.getConnection();
            Array dtlArray = OracleObjectMapper.getArray(oracleConnection, "PRICECHG_O", "PRICECHG_TO", promoDtlList);
            ocs = (OracleCallableStatement) oracleConnection.prepareCall("{call b_promotion.process(?,?,?)}");
            ocs.setArray(1, dtlArray);
            ocs.setNull(2, OracleTypes.ARRAY, "ERROR_TO");
            ocs.setInt(3, 0);
            ocs.registerOutParameter(1, OracleTypes.ARRAY, "PRICECHG_TO");
            ocs.registerOutParameter(2, OracleTypes.ARRAY, "ERROR_TO");
            
            ocs.execute();
            Array errorArray = ocs.getArray(1);
            System.out.println(errorArray);
        } catch (Exception e) {
            e.printStackTrace();
//            errorPojoList.add(ErrorUtil.buildErrorPojo(1, null, e.getMessage()));
        } finally {
//            DBConnection.close(ocs);
        }
        return "SUCCESS";
    }
	// test proc ends here
	
	// testing proc with registerOutParameter as record type 
	// Oracle JDBC drivers do not support Boolean / Record type In Out stored procedure parameters
	// JOOQ 3.9 can be helpful
	public Map<String, Object> executeProc(int siteNo, int adId, int adSiteNo, int skuNo) {
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		
		// ref EI-Manager Promotions.java processPromoDtl() L.No. : 123
//		List<SqlParameter> declaredParameters= new ArrayList<SqlParameter>();
//		declaredParameters.add(new SqlParameter("PN_DEPT", deptNo));
//		declaredParameters.add(new SqlParameter("PN_CLASS", classNo));
//		declaredParameters.add(new SqlParameter("PN_LINE", lineNo));
//		
//		// array of co
//		declaredParameters.add(new SqlOutParameter("adhdr_ptrec", Types.ARRAY, BeanPropertyRowMapper.newInstance(List.class)));
//		
//		CallableStatementCreator csc = new CallableStatementCreator() {
//			
//			@Override
//			public CallableStatement createCallableStatement(Connection con) throws SQLException {
//				// TODO Auto-generated method stub
//				
//				CallableStatement stmt = con.prepareCall("{ CALL TMX.B_PROMOTION.CHK_NEW_SKU_PROMO(?, ? , ?, ?) }");
//				stmt.setInt(1, deptNo);
//				stmt.setInt(2, classNo);
//				stmt.setInt(3, lineNo);
//				OracleConnection conn = (OracleConnection) stmt.getConnection();
//				OracleArray adHdrStruct = null;
//				
//				List<AdhdrModel> reqList = new ArrayList<>();
//				reqList.add(new AdhdrModel());
//				try {
		// pass oracle connection in the below call
//					adHdrStruct = OracleObjectMapper.getArray(conn, "adhdr_ptrec", "adhdr_ptrec", reqList );
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
////				Object[] elements = model.toArray(); 
////				Array array = conn.createArrayOf("adhdr_ptrec", elements);
////				stmt.setObject(4, array);
//				stmt.registerOutParameter(4, Types.ARRAY);
//				return stmt;
//			}
//		};
//		Map<String, Object> test = jdbcTemplate.call(csc , declaredParameters);
		
		// Approach 1 for boolean
		try {
			SqlParameterSource paramMap = new MapSqlParameterSource()
					.addValue("pn_Site_no", siteNo)
					.addValue("pc_Ad_id", adId)
					.addValue("pn_Ad_Site_no", adSiteNo)
					.addValue("pn_Sku_no", skuNo);
			
			Map<String, Object> flag = testService.getDataFromProcedure("tmx", "b_promotion", "insert_promo_except", paramMap,
					new SqlOutParameter("flag",OracleTypes.BOOLEAN),
					new SqlParameter("pn_Site_no", Types.BIGINT),
					new SqlParameter("pc_Ad_id", Types.BIGINT),
					new SqlParameter("pn_Ad_Site_no", Types.BIGINT),
					new SqlParameter("pn_Sku_no", Types.BIGINT));
			
			System.out.println("Check map value "+flag);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// Approach 2 for boolean
		Connection connection = DataSourceUtils.getConnection(dataSource);
		CallableStatement call;
		try {
			call = connection.prepareCall("{CALL tmx.b_promotion.insert_promo_except(?, ?, ?, ?)}");
			call.setInt(1, siteNo);
			call.setInt(2, adId);
			call.setInt(3, adSiteNo);
			call.setInt(4, skuNo);
			call.execute();
			System.out.println("executed == "+call.getBoolean(0));
			System.out.println("executed == "+call.getBoolean(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		jdbcTemplate.setResultsMapCaseInsensitive(true);
//		
//		// overloaded function need to check with Venkat / Girish
//
//		SimpleJdbcCall jdbcCall1 = new SimpleJdbcCall(jdbcTemplate)
//				.withSchemaName("tmx")
//				.withCatalogName("b_promotion") // package procedure																														// name
//				.withProcedureName("chk_new_sku_promo");
//
//		Map<String, Object> moveCount = jdbcCall1.execute(hm);
		
		
//		
//		Map<String, Object> map = null;
		return null;
	}

	public boolean handlePromotions(UserIdentity domainUser, int deptNo, int classNo, int lineNo, int skuNo) {
		boolean flag1 = checkNewSkuPromotions(domainUser, deptNo, classNo, lineNo);
		boolean flag2 = insertSkuException(domainUser, deptNo, classNo, lineNo, skuNo);
		return true;
	}

	// return type will change
	public boolean checkNewSkuPromotions(UserIdentity domainUser, int deptNo, int classNo, int lineNo) {
		// TODO Auto-generated method stub
		
		// 1. pass DCL, DC, D and extract the list of SkuPromo and add all records in a list
		// 2. find adhdr rec by passing site no and ad id as both make a PK
		// 3. find the adhdr rec on the basis of the status P and if count > 1 then alert user to include / exclude the promos
		
		List<Adhdr> adhdrList = new ArrayList<>();
		List<SkuPromo> checkList = getPromoSkuList(deptNo, classNo , lineNo, "check");

		if(!checkList.isEmpty()) {
			for(SkuPromo skuPromo : checkList) {
				
				AdhdrPK id = new AdhdrPK();
				id.setAdId(skuPromo.getAdId());
				id.setSiteNo(skuPromo.getAdSiteNo());
				
				Optional<Adhdr> adhdrRec = adhdrRepository.findById(id);
				
				if(adhdrRec != null)
					adhdrList = adhdrRepository.findByStatus("P");
				
				// as per the size of the list with status P exception will be thrown to alert user
				// to check with promos press OK or Cancel
				if(!adhdrList.isEmpty()) {
					
					if(adhdrList.size() >= 1)
						logger.info("Ask user to press OK or Cancel to proceed with the promotions.");
				}
			}
		}
		// to insert sku promos ask the user for the input
		return false;
	}
	
	private List<SkuPromo> getPromoSkuList(int deptNo, int classNo, int lineNo, String mode) {
		// TODO Auto-generated method stub
		List<SkuPromo> skuPromo1 = itemRepository.getPromoSkuDetails(deptNo, classNo , lineNo, mode);
		List<SkuPromo> skuPromo2 = itemRepository.getPromoSkuDetails(deptNo, classNo , 0, mode);
		List<SkuPromo> skuPromo3 = itemRepository.getPromoSkuDetails(deptNo, 0 , 0, mode);
		
		// merge in single list then loop in for the adhdr records
		List<SkuPromo> reqList = Stream.of(skuPromo1, skuPromo2, skuPromo3)
				.filter(sp -> sp != null)
				.flatMap(Collection :: stream)
				.collect(Collectors.toList());
		return reqList;
	}

	// return type will change
	public boolean insertSkuException(UserIdentity domainUser, int deptNo, int classNo, int lineNo, int skuNo) {
		// TODO Auto-generated method stub
		List<SkuPromo> checkList = getPromoSkuList(deptNo, classNo , lineNo, "check");
		List<SkuPromo> insertSkuPromoList = getPromoSkuList(deptNo, classNo , lineNo, "insert");
		List<Integer> sitesList = null;

		if(!insertSkuPromoList.isEmpty()) {
			
			for(SkuPromo sp : insertSkuPromoList) {
				sitesList = lookUpService.getSitesFromSiteGroupId(sp.getSiteGroupId());
				
				for(Integer siteNo : sitesList){
					PromoExceptPK id = new PromoExceptPK();
					id.setSiteNo(siteNo);
					id.setAdId(sp.getAdId());
					id.setAdSiteNo(sp.getAdSiteNo());
					id.setSkuNo(skuNo);
					
					PromoExcept pe= new PromoExcept();
					pe.setId(id);
					pe.setUserModified(domainUser.getUserName());
					pe.setDateChanged(new Date());
					
					try {
						promoRepository.save(pe);
					} catch (Exception e) {
						// TODO: handle exception
						logger.info("Exception in creating the promos.");
					}
				}
			}
		}
		// condition to check the promos list size and the status P list size then display the message to user
		return false;
	}

}
