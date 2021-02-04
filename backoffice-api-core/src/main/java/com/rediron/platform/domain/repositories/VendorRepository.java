package com.rediron.platform.domain.repositories;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.rediron.platform.domain.constants.IQueryConstants;
import com.rediron.platform.domain.entities.SupplierItemEntity;
import com.rediron.platform.domain.entities.VendorItemEntity;
import com.rediron.platform.domain.entities.VendorItemEntityId;
import com.rediron.platform.domain.model.request.SupplierUpdate;
import com.rediron.platform.domain.model.request.SuppliersToBeUpdated;
import com.rediron.platform.domain.model.request.VendorItemModel;
import com.rediron.platform.domain.model.response.SupplierDetailsResponse;
import com.rediron.platform.domain.rnet.VendorValidator;
import com.rediron.platform.domain.services.UtilityService;
import com.rediron.platform.domain.services.VendorService;
import com.tomax.api.UserIdentity;

/**
 * @author lovanya.chaudhary
 *
 */
@Repository
public class VendorRepository {

	Logger logger = LoggerFactory.getLogger(VendorRepository.class);

	@Autowired
	UtilityService utilityService;

	@Autowired
	SupplierItemRepository supplierItemRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private VendorValidator vendorValidator;
	
	@Autowired
	private VendorItemRepository vendorItemRepository;
	
	@Autowired
	private VendorService bVendorService;

	// verify function

	public BigDecimal getOwnerIdFromVendor(int siteNo) {

		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("p_site_in", siteNo);

		BigDecimal ownerId = utilityService.getDataFromFunction("tmx", "b_vendor", "get_owner_id", paramMap,
				BigDecimal.class, new SqlOutParameter("number", Types.DECIMAL),
				new SqlParameter("p_site_in", Types.BIGINT));

		if (ownerId != null)
			logger.info("Owner Id " + ownerId);

		return ownerId;
	}

	public SupplierItemEntity saveSupplier(UserIdentity domainUser, int skuNo, String supplierName) {
		SupplierItemEntity entity = new SupplierItemEntity();
		entity.setSkuNo(skuNo);
		entity.setSupplierId(supplierName);
		entity.setDateCreated(new Date(System.currentTimeMillis()));
		entity.setDateModified(new Date(System.currentTimeMillis()));
		entity.setUserCreated(domainUser.getUserName());
		entity.setUserModified(domainUser.getUserName());
		return supplierItemRepository.save(entity);
	}

	public List<SupplierDetailsResponse> getAllSuppliersForSku(UserIdentity domainUser, int skuNo) {
		// TODO Auto-generated method stub
		List<SupplierDetailsResponse> list = jdbcTemplate.query(IQueryConstants.SUPPLIERS_SKU, ps -> {
			ps.setInt(1, skuNo);
		}, rs -> {
			List<SupplierDetailsResponse> supplierList = new ArrayList<>();
			while (rs.next()) {
				SupplierDetailsResponse sd = new SupplierDetailsResponse();
				sd.setSupplierId(rs.getString("supplier_id"));
				sd.setSupplierDescription(rs.getString("name"));
				supplierList.add(sd);
			}
			return supplierList;
		});
		return list;
	}

	public List<SupplierDetailsResponse> saveSuppliers(UserIdentity domainUser, int skuNo, List<String> supplierNames) {
		// TODO Auto-generated method stub
		int count = 0;
		if (!supplierNames.isEmpty()) {
			for (String str : supplierNames) {
				jdbcTemplate.update(IQueryConstants.ADD_SUPPLIERS, ps -> {
					ps.setInt(1, skuNo);
					ps.setString(2, str);
					ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
					ps.setString(4, domainUser.getUserName());
					ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
					ps.setString(6, domainUser.getUserName());
				});
				count++;
			}
		}
		logger.info("No of suppliers inserted ==>> " + count);
		return getAllSuppliersForSku(domainUser, skuNo);
	}

	public List<SupplierDetailsResponse> updateSupplier(UserIdentity domainUser, int skuNo, SuppliersToBeUpdated suppliers) {
		// TODO Auto-generated method stub
		int count = 0;
		List<SupplierUpdate> list = suppliers.getUpdateSuppliers();
		if (!list.isEmpty()) {
			for (SupplierUpdate supp : list) {
				jdbcTemplate.update(IQueryConstants.UPDATE_SUPPLIERS, ps -> {
					ps.setString(1, supp.getNewSupplier());
					ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
					ps.setString(3, domainUser.getUserName());
					ps.setInt(4, skuNo);
					ps.setString(5, supp.getPreviousSupplier());
				});
				count++;
			}
		}
		logger.info("No of suppliers updated ==>> " + count);
		return getAllSuppliersForSku(domainUser, skuNo);
	}

	public List<SupplierDetailsResponse> deleteSupplier(UserIdentity domainUser, int skuNo, List<String> supplierNames) {
		// TODO Auto-generated method stub
		int count = 0;
		if (!supplierNames.isEmpty()) {
			for (String str : supplierNames) {
				jdbcTemplate.update(IQueryConstants.DELETE_SUPPLIERS, ps -> {
					ps.setString(1, str);
					ps.setInt(2, skuNo);
				});
				count++;
			}
		}
		logger.info("No of suppliers deleted ==>> " + count);
		return getAllSuppliersForSku(domainUser, skuNo);
	}

	public BigDecimal getNextVendorItemSeqNo(UserIdentity domainUser) {
		BigDecimal seqNo = utilityService.getDataFromFunction("tmx", "b_vendor", "get_next_vitm_seq_no", null,
				BigDecimal.class, new SqlOutParameter("number", Types.NUMERIC));
		return seqNo;
	}

	public VendorItemEntity createVendorItemBySkuAndSite(UserIdentity domainUser, VendorItemModel vendorItemModel, int siteNo, int skuNo) {
		VendorItemEntity vi = mapToVendorEntity(vendorItemModel);
		vi.setSiteNo(siteNo);
		vi.setSkuNo(skuNo);
		vendorValidator.validateVendorItemCurrencyPackCost(domainUser, vi);
		vi.setSeqNo(bVendorService.getNextVendorItemSeqNo(domainUser).toBigInteger());
		return vendorItemRepository.save(vi);
	}
	
	public VendorItemEntity mapToVendorEntity(VendorItemModel vendorItemModel) {

		VendorItemEntity venEntity = new VendorItemEntity();
		venEntity.setVendorId(vendorItemModel.getVendorId());
		venEntity.setVendorItem(vendorItemModel.getVendorItem());
		
		venEntity.setPackQty(vendorItemModel.getPackQty());
		venEntity.setPackUm(vendorItemModel.getPackUm());
		venEntity.setMasterPackQty(vendorItemModel.getMasterPackQty());
		venEntity.setLeadTime(vendorItemModel.getLeadTime());
		venEntity.setEoq(vendorItemModel.getEoq());
		
		if(StringUtils.isNotBlank(vendorItemModel.getOrderAvailabilityStatus())) {
			venEntity.setOrderAvailabilityStatus(vendorItemModel.getOrderAvailabilityStatus());
			venEntity.setOrderAvailabilityActiveDt(new Date());
		}
		
		venEntity.setPackWeight(vendorItemModel.getPackWeight());
		venEntity.setPackCube(vendorItemModel.getPackCube());
		
		if(vendorItemModel.isMainVendor())
			venEntity.setMainVendorItemFl("Y");
		else
			venEntity.setMainVendorItemFl("N");
		
		if(vendorItemModel.isPrimaryVendor())
			venEntity.setVenType("P");
		else
			venEntity.setVenType("S");
		
		venEntity.setVendorCurrencyPackCost(vendorItemModel.getPackCost());
		venEntity.setPackCost(getVendorCurrencyFactorByVendorId(vendorItemModel.getVendorId()).multiply(vendorItemModel.getPackCost()));
		
		venEntity.setOperationType("I");
		venEntity.setCostFactor(new BigDecimal(1));
		venEntity.setUnitCost(new BigDecimal(0));
		return venEntity;
	}

	public VendorItemEntity updateVendorItemBySeqNo(UserIdentity domainUser, VendorItemModel vendorItemModel,
			String seqNo) {
		if(!StringUtils.isNotBlank(seqNo) || !StringUtils.isNumeric(seqNo))
			System.out.println("Not Allowed throw exception");
		BigInteger seqNum = new BigInteger(seqNo); 
		VendorItemEntity updatedVI = null;
		Optional<VendorItemEntity> viToBeUpdated = vendorItemRepository.findById(new VendorItemEntityId(seqNum));
		if(viToBeUpdated.isPresent()) {
			VendorItemEntity origVI = viToBeUpdated.get();
			origVI.setVendorItem(vendorItemModel.getVendorItem());
			
			origVI.setVendorCurrencyPackCost(vendorItemModel.getPackCost());
			origVI.setPackCost(getVendorCurrencyFactorByVendorId(vendorItemModel.getVendorId()).multiply(vendorItemModel.getPackCost()));
			origVI.setUnitCost(new BigDecimal(0));
			origVI.setPackQty(vendorItemModel.getPackQty());
			origVI.setPackUm(vendorItemModel.getPackUm());
			origVI.setMasterPackQty(vendorItemModel.getMasterPackQty());
			origVI.setLeadTime(vendorItemModel.getLeadTime());
			origVI.setEoq(vendorItemModel.getEoq());
			
			if(StringUtils.isNotBlank(vendorItemModel.getOrderAvailabilityStatus())) {
				origVI.setOrderAvailabilityStatus(vendorItemModel.getOrderAvailabilityStatus());
				origVI.setOrderAvailabilityActiveDt(new Date());
			}
			
			origVI.setPackWeight(vendorItemModel.getPackWeight());
			origVI.setPackCube(vendorItemModel.getPackCube());
			
			if(vendorItemModel.isMainVendor())
				origVI.setMainVendorItemFl("Y");
			else
				origVI.setMainVendorItemFl("N");
			
			if(vendorItemModel.isPrimaryVendor())
				origVI.setVenType("P");
			else
				origVI.setVenType("S");
			vendorValidator.validateVendorItem(domainUser, origVI);
			updatedVI = vendorItemRepository.save(origVI);
		}
		return updatedVI;
	}
	
	public BigDecimal getVendorCurrencyFactorByVendorId(String vendorId) {

		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("p_vendor_id", vendorId);

		BigDecimal currencyFactor = utilityService.getDataFromFunction("tmx", "b_landed_cost", "vendor_currency_factor", paramMap,
				BigDecimal.class, new SqlOutParameter("number", Types.DECIMAL),
				new SqlParameter("p_vendor_id", Types.VARCHAR));

		if (currencyFactor != null)
			logger.info("Currency Factor for the vendor id : "+vendorId+" is : " + currencyFactor);

		return currencyFactor;
	}
	
	public List<VendorItemEntity> updateAllVendorItemsForSkuAndVendorId(UserIdentity domainUser, VendorItemModel vendorItemModel, int skuNo) {
		
		VendorItemEntity updatedVI = null;
		List<VendorItemEntity> listOfVI = new ArrayList<>();
		List<VendorItemEntity> viToBeUpdated = vendorItemRepository.getVendorItemsForSkuAndVendorId(skuNo, vendorItemModel.getVendorId());
		if(!CollectionUtils.isEmpty(viToBeUpdated)) {
			
			for(VendorItemEntity vi : viToBeUpdated) {
				VendorItemEntity origVI = new VendorItemEntity(vi); 
				origVI.setVendorItem(vendorItemModel.getVendorItem());
				
				origVI.setVendorCurrencyPackCost(vendorItemModel.getPackCost());
				origVI.setPackCost(getVendorCurrencyFactorByVendorId(vendorItemModel.getVendorId()).multiply(vendorItemModel.getPackCost()));
				origVI.setUnitCost(new BigDecimal(0));
				origVI.setPackQty(vendorItemModel.getPackQty());
				origVI.setPackUm(vendorItemModel.getPackUm());
				origVI.setMasterPackQty(vendorItemModel.getMasterPackQty());
				origVI.setLeadTime(vendorItemModel.getLeadTime());
				origVI.setEoq(vendorItemModel.getEoq());
				
				if(StringUtils.isNotBlank(vendorItemModel.getOrderAvailabilityStatus())) {
					origVI.setOrderAvailabilityStatus(vendorItemModel.getOrderAvailabilityStatus());
					origVI.setOrderAvailabilityActiveDt(new Date());
				}
				
				origVI.setPackWeight(vendorItemModel.getPackWeight());
				origVI.setPackCube(vendorItemModel.getPackCube());
				
				if(vendorItemModel.isMainVendor())
					origVI.setMainVendorItemFl("Y");
				else
					origVI.setMainVendorItemFl("N");
				
				if(vendorItemModel.isPrimaryVendor())
					origVI.setVenType("P");
				else
					origVI.setVenType("S");
				vendorValidator.validateVendorItem(domainUser, origVI);
				updatedVI = vendorItemRepository.save(origVI);
				listOfVI.add(updatedVI);
			}
		}
		return listOfVI;
	}
}
