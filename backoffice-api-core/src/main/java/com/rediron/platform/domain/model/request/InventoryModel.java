package com.rediron.platform.domain.model.request;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InventoryModel {
	
//	 private Integer attribute1Id;
//	 private String attribute1Value;
//	 private Integer attribute2Id;
//	 private String attribute2Value;
//	 private Integer attribute3Id;
//	 private String attribute3Value;
	 
	 @JsonProperty(value = "packup_action")
	 private Integer packUpAction;
	 
	 @NotNull
	 @Size(min = 5, max = 30)
	 @JsonProperty(value = "item_description")
	 private String itemDescription;

	 @NotNull
	 @Digits(integer=4, fraction=0)
	 @JsonProperty(value = "owner_id")
	 private Integer ownerId;
	 
//	 private String mfgCd; inside item identifiers model 
	 
	 @Valid
	 @JsonProperty(value = "product_model")
	 private ProductModel productModel;
	 
	 @Valid
	 @JsonProperty(value = "description_model")
	 private DescriptionModel descriptionModel;
	 
	 @Valid
	 @JsonProperty(value = "report_info_model")
	 private ReportInfoModel reportInfoModel;
	 
	 @Valid
	 @JsonProperty(value = "measurement_info_model")
	 private MeasurementInfoModel measurementInfoModel;
	 
	 @Valid
	 @JsonProperty(value = "item_identifiers_info_model")
	 private ItemIdentifiersInfoModel itemIdentifiersInfoModel;
	 
	 @Valid
	 @JsonProperty(value = "regional_info_model")
	 private RegionalInfoModel regionalInfoModel;
	 
	 @Valid
	 @JsonProperty(value = "inner_pack_info_model")
	 private InnerPackInfoModel innerPackInfoModel;
	 
	 @Valid
	 @JsonProperty(value = "label_info_model")
	 private LabelInfoModel labelInfoModel;
	 
	 @Valid
	 @JsonProperty(value = "tender_certificate_type_info_model")
	 private TenderCertificateTypeInfoModel tenderCertificateTypeInfoModel;
	 
	 @Valid
	 @JsonProperty(value = "serial_items_info_model")
	 private SerialItemsInfoModel serialItemsInfoModel;
	 
	 @Valid
	 @JsonProperty(value = "item_type_flags_info_model")
	 private ItemTypeFlagsInfoModel itemTypeFlagsInfoModel;
	 
	 @Valid
	 @JsonProperty(value = "general_status_flags_info_model")
	 private GeneralStatusFlagsInfoModel generalStatusFlagsInfoModel;
	 
	 @Valid
	 @JsonProperty(value = "pos_flags_info_model")
	 private POSFlagsInfoModel posFlagsInfoModel;
	 
	 @Valid
	 @JsonProperty(value = "hazardous_info_model")
	 private HazardousInfoModel hazardousInfoModel;
}
