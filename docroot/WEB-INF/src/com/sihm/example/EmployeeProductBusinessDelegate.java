package fr.mhm.portal.businessdelegate.animanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import fr.mhm.portal.constants.AniConstants;
import fr.mhm.portal.constants.MhmConstants;
import fr.mhm.portal.exception.MhmCheckedTechnicalException;
import fr.mhm.portal.exception.MhmUncheckedTechnicalException;
import fr.mhm.portal.service.DocumentDetailsService;
import fr.mhm.portal.service.EmployeeProductCodesTarifService;
import fr.mhm.portal.service.EmployeeProductPeriodService;
import fr.mhm.portal.service.EmployeeProductPriceService;
import fr.mhm.portal.service.EmployeeProductService;
import fr.mhm.portal.service.EnterPriseProductService;
import fr.mhm.portal.service.MhmDateService;
import fr.mhm.portal.service.OfferService;
import fr.mhm.portal.service.WarrantyDetailsService;
import fr.mhm.portal.service.model.DocumentDetails;
import fr.mhm.portal.service.model.EmployeeProduct;
import fr.mhm.portal.service.model.EmployeeProductCodesTarif;
import fr.mhm.portal.service.model.EmployeeProductPeriod;
import fr.mhm.portal.service.model.EmployeeProductPrice;
import fr.mhm.portal.service.model.EnterpriseProduct;
import fr.mhm.portal.service.model.Offer;
import fr.mhm.portal.service.model.OfferZoneDepartment;
import fr.mhm.portal.service.model.Situation;
import fr.mhm.portal.service.model.Structure;
import fr.mhm.portal.service.model.StructureDetails;
import fr.mhm.portal.service.model.WarrantyDetails;
import fr.mhm.portal.service.model.WarrantyExamples;
import fr.mhm.portal.service.model.Zone;
import fr.mhm.portal.service.service.StructureDetailsLocalServiceUtil;
import fr.mhm.portal.vo.CodesTarifVO;
import fr.mhm.portal.vo.EmployeeProductPeriodVO;
import fr.mhm.portal.vo.EmployeeProductPriceDetailsVO;
import fr.mhm.portal.vo.EmployeeProductVO;
import fr.mhm.portal.vo.ProductDocumentsVO;
import fr.mhm.portal.vo.RefModelVO;
import fr.mhm.portal.vo.ani.EmpProdCodeTarifVO;
import fr.mhm.portal.vo.ani.EmpProdPeriodVO;
import fr.mhm.portal.vo.ani.EmpProdVO;
import fr.mhm.portal.vo.ani.WarrantyDocumentVO;
import fr.mhm.portal.vo.ani.WarrantyVO;

/**
 * EmployeeProduct Business Delegate class
 * @author h.h.kumar
 *
 */
public class EmployeeProductBusinessDelegate {

	private static final Logger LOGGER = Logger.getLogger(EmployeeProductBusinessDelegate.class);
	
	private static final String EMPTY_PRICE = "EMPTY";
	
	private EmployeeProductService employeeProductService;
	private EmployeeProductCodesTarifService employeeProductCodesTarifService;
	public EmployeeProductPeriodService employeeProductPeriodService;
	private EmployeeProductPriceService employeeProductPriceService;
	private EnterPriseProductService enterPriseProductService;
	private OfferService offerService;
	private DocumentDetailsService documentDetailsService;
	private WarrantyDetailsService warrantyDetailsService;
	private OfferManagementBusinessDelegate offerManagementBusinessDelegate;
	public MhmDateService dateService;

	/**
	 * @param dateService the dateService to set
	 */
	public void setDateService(MhmDateService dateService) {
		this.dateService = dateService;
	}

	/**
	 * 
	 * @param employeeProductService
	 */
	public void setEmployeeProductService(
			EmployeeProductService employeeProductService) {
		this.employeeProductService = employeeProductService;
	}

	/**
	 * @param employeeProductCodesTarifService the employeeProductCodesTarifService to set
	 */
	public void setEmployeeProductCodesTarifService(
			EmployeeProductCodesTarifService employeeProductCodesTarifService) {
		this.employeeProductCodesTarifService = employeeProductCodesTarifService;
	}

	/**
	 * 
	 * @param employeeProductPeriodService
	 */
	public void setEmployeeProductPeriodService(
			EmployeeProductPeriodService employeeProductPeriodService) {
		this.employeeProductPeriodService = employeeProductPeriodService;
	}

	/**
	 * 
	 * @param employeeProductPriceService
	 */
	public void setEmployeeProductPriceService(
			EmployeeProductPriceService employeeProductPriceService) {
		this.employeeProductPriceService = employeeProductPriceService;
	}

	/**
	 * @param enterPriseProductService the enterPriseProductService to set
	 */
	public void setEnterPriseProductService(
			EnterPriseProductService enterPriseProductService) {
		this.enterPriseProductService = enterPriseProductService;
	}

	/**
	 * @param offerService the offerService to set
	 */
	public void setOfferService(OfferService offerService) {
		this.offerService = offerService;
	}
	
	/**
	 * @param documentDetailsService the documentDetailsService to set
	 */
	public void setDocumentDetailsService(DocumentDetailsService documentDetailsService) {
		this.documentDetailsService = documentDetailsService;
	}
	
	/**
	 * @param warrantyDetailsService the warrantyDetailsService to set
	 */
	public void setWarrantyDetailsService(
			WarrantyDetailsService warrantyDetailsService) {
		this.warrantyDetailsService = warrantyDetailsService;
	}
	
	public void setOfferManagementBusinessDelegate(
			OfferManagementBusinessDelegate offerManagementBusinessDelegate) {
		this.offerManagementBusinessDelegate = offerManagementBusinessDelegate;
	}
	
	/**
	 * method to add new employee product
	 * @param name
	 * @param codeProduitTechnique
	 * @param enterpriseProductId
	 * @param employeeProductType
	 * @param regime
	 * @param createdDate
	 * @param modifiedDate
	 * @param contributor
	 * @param status
	 * @param completed
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public EmployeeProduct addEmployeeProduct(String name, String codeProduitTechnique, String enterpriseProductId, String employeeProductType,
			String regime, Date createdDate, Date modifiedDate, long contributor, String status, String completed) throws MhmCheckedTechnicalException
	{
		
		LOGGER.info(MhmConstants.START+"addEmployeeProduct");
		EmployeeProduct employeeProduct = employeeProductService.createEmployeeProduct();
		
		employeeProduct.setName(name);
		employeeProduct.setCodeProduitTechnique(codeProduitTechnique);
		employeeProduct.setEnterpriseProductId(enterpriseProductId);
		employeeProduct.setEmployeeProductType(employeeProductType);
		employeeProduct.setRegime(regime);
		employeeProduct.setCreatedDate(createdDate);
		employeeProduct.setModifiedDate(modifiedDate);
		employeeProduct.setContributor(contributor);
		employeeProduct.setStatus(status);
		employeeProduct.setCompleted(completed);

		employeeProduct = employeeProductService.addEmployeeProduct(employeeProduct, true);
		LOGGER.info(MhmConstants.END+"addEmployeeProduct");
		
		return employeeProduct;
	}

	/**
	 * method to add employee product validation period
	 * @param codeTarifId
	 * @param startDateString
	 * @param endDateString
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public EmployeeProductPeriod addEmployeeProductPeriod(long codeTarifId, String startDateString, String endDateString,String taxe, String status)
			throws MhmCheckedTechnicalException {
		
		LOGGER.info(MhmConstants.START+"addEmployeeProductPeriod");
		Date startDate = null;
		Date endDate = null;
		
		try {
			startDate = dateService.convertDateFromString(startDateString);
			endDate = dateService.convertDateFromString(endDateString);
		} catch (Exception e) {
			throw new MhmCheckedTechnicalException("MhmCheckedTechnicalException in addEmployeeProductPeriod of EmployeeProductBusinessDelegate");
		}

		EmployeeProductPeriod employeeProductPeriod = employeeProductPeriodService.createEmployeeProductPeriod();

		employeeProductPeriod.setEmployeeproductcodestarifId(codeTarifId);
		employeeProductPeriod.setStartDate(startDate);
		employeeProductPeriod.setEndDate(endDate);
		employeeProductPeriod.setStatus(status);
		employeeProductPeriod.setTaxe(taxe);
		LOGGER.info(MhmConstants.END+"addEmployeeProductPeriod");
		
		return employeeProductPeriodService.addEmployeeProductPeriod(employeeProductPeriod);

	}
	
	/**
	 * method to update employee product validation period
	 * @param employeeProductPeriodId
	 * @param startDateString
	 * @param endDateString
	 * @param status 
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public EmployeeProductPeriod updateEmployeeProductPeriod(long employeeProductPeriodId, String startDateString, String endDateString,String taxe, String status) throws MhmCheckedTechnicalException
	{
			LOGGER.info(MhmConstants.START+"updateEmployeeProductPeriod");
			EmployeeProductPeriod employeeProductPeriod = employeeProductPeriodService.getEmployeeProductPeriod(employeeProductPeriodId);
			
			Date startDate = null;
			Date endDate = null;
			try {
				startDate = dateService.convertDateFromString(startDateString);
				endDate = dateService.convertDateFromString(endDateString);
			} catch (Exception e) {
				throw new MhmCheckedTechnicalException("MhmCheckedTechnicalException in updateEmployeeProductPeriod of EmployeeProductBusinessDelegate");
			}
			employeeProductPeriod.setStartDate(startDate);
			employeeProductPeriod.setEndDate(endDate);
			employeeProductPeriod.setTaxe(taxe);
			employeeProductPeriod.setStatus(status);
			LOGGER.info(MhmConstants.END+"updateEmployeeProductPeriod");
			
			return employeeProductPeriodService.updateEmployeeProductPeriod(employeeProductPeriod);
	}
	
	/**
	 * method to add/update EmployeeProductPrice
	 * @param employeeProductPeriodId
	 * @param empPart
	 * @param adhPart
	 * @param structureDetailsId
	 * @param status
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public EmployeeProductPrice addEmployeeProductPrice(long employeeProductPeriodId, String empPart, String adhPart, long structureDetailsId, String status,boolean importation) throws MhmCheckedTechnicalException
	{
		LOGGER.info(MhmConstants.START+"addEmployeeProductPrice");
		EmployeeProductPrice employeeProductPrice = null;
		
		try {
			employeeProductPrice = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(employeeProductPeriodId, structureDetailsId, status);
		} catch (MhmCheckedTechnicalException e) {}
		
		if(Validator.isNull(employeeProductPrice))
		{
			employeeProductPrice = employeeProductPriceService.createEmployeeProductPrice();
		}
		
		employeeProductPrice.setEmployeeProductPeriodId(employeeProductPeriodId);
		
		if(importation && Validator.isNull(empPart)){
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("EMP PART IS NULL - EXISTING PRICE :: " + employeeProductPrice.getEmpPart());
			}
			employeeProductPrice.setEmpPart(employeeProductPrice.getEmpPart());
		} else {
			employeeProductPrice.setEmpPart(empPart);
		}
		if(importation && Validator.isNull(adhPart)){
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("ADH PART IS NULL - EXISTING PRICE :: " + employeeProductPrice.getAdhPart());
			}
			employeeProductPrice.setAdhPart(employeeProductPrice.getAdhPart());	
		} else {
			employeeProductPrice.setAdhPart(adhPart);
		}
		
		employeeProductPrice.setStructureDetailsId(structureDetailsId);
		employeeProductPrice.setStatus(status);
		LOGGER.info(MhmConstants.END+"addEmployeeProductPrice");
				
		return employeeProductPriceService.updateEmployeeProductPrice(employeeProductPrice);
	}
	
	/**
	 * method to populate EmployeeProductVO when Employee Product is added
	 * @param enterpriseProductId
	 * @param regime
	 * @param productStatus 
	 * @param offerStatus 
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public EmployeeProductVO populateEmployeeProductVOAdd(
			String enterpriseProductId, String regime, String offerStatus, String productStatus) throws MhmCheckedTechnicalException {
		
		LOGGER.info(MhmConstants.START+"populateEmployeeProductVOAdd");
		EmployeeProductVO employeeProductVO = new EmployeeProductVO();
		
		employeeProductVO.setEnterpriseProductId(enterpriseProductId);
		employeeProductVO.setRegime(regime);
		String employeeProductType = getEmployeeProductType(enterpriseProductId, regime, productStatus);
		employeeProductVO.setEmployeeProductType(employeeProductType);
		employeeProductVO.setAssociatedBaseId(getAssociatedBaseId(enterpriseProductId, regime));
		employeeProductVO.setProductStatus(productStatus);
		employeeProductVO.setOfferStatus(offerStatus);
		employeeProductVO.setFormAction(AniConstants.ADD);
		LOGGER.info(MhmConstants.END+"populateEmployeeProductVOAdd");
		
		return employeeProductVO;
	}
	
	/**
	 * method to populate EmployeeProductVO when Employee Product is added
	 * @param status 
	 * @param productStatus 
	 * @param offerStatus 
	 * @param epId
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public EmployeeProductVO populateEmployeeProductVOEdit(String employeeProductId, String offerStatus, String productStatus, String status) throws MhmCheckedTechnicalException {
		
		LOGGER.info(MhmConstants.START+"populateEmployeeProductVOEdit");
		EmployeeProduct employeeProduct = employeeProductService.findByemployeeProductId_status(employeeProductId, status);
		EnterpriseProduct enterpriseProduct = enterPriseProductService.getByEnterpriseProductIdStatus(employeeProduct.getEnterpriseProductId(), productStatus);
		Offer offer = offerService.getOffer(enterpriseProduct.getOfferId(), offerStatus);
		Situation situation = offerService.getSituation(offer.getSituationId());
		List<OfferZoneDepartment> offerZoneDepartments = offerService.getOfferZoneDepartmentsByOfferStatus(offer.getOfferId(), offerStatus);
		
		List<Long> zoneList = new ArrayList<Long>();
		
		for(OfferZoneDepartment offerZoneDepartment : offerZoneDepartments)
		{
			zoneList.add(offerZoneDepartment.getZoneId());
		}
		
		EmployeeProductVO employeeProductVO = new EmployeeProductVO();
		
		// Populating EmployeeProductVO
		employeeProductVO.setEmployeeProductId(employeeProductId);
		employeeProductVO.setEnterpriseProductId(employeeProduct.getEnterpriseProductId());
		employeeProductVO.setName(employeeProduct.getName());
		employeeProductVO.setCodeProduitTechnique(employeeProduct.getCodeProduitTechnique());
		employeeProductVO.setRegime(employeeProduct.getRegime());
		employeeProductVO.setSituationId(situation.getSituationId());
		employeeProductVO.setSituationName(situation.getSituationName());
		employeeProductVO.setEmployeeProductType(employeeProduct.getEmployeeProductType());
		employeeProductVO.setAssociatedBaseId(getAssociatedBaseId(employeeProduct.getEnterpriseProductId(), employeeProduct.getRegime()));
		employeeProductVO.setStructureId(offer.getStructureId());
		employeeProductVO.setStatus(status);
		employeeProductVO.setProductStatus(productStatus);
		employeeProductVO.setOfferStatus(offerStatus);
		employeeProductVO.setFormAction(AniConstants.EDIT);
		
		/*Change done due to MHM-5466 improvement*/ 
		employeeProductVO.setPosition(employeeProduct.getPosition());
		
		if(!zoneList.isEmpty())
		{
			if(zoneList.contains(AniConstants.ZONE_1_ID)){
				employeeProductVO.setZone1(true);
			}
			if(zoneList.contains(AniConstants.ZONE_2_ID)){
				employeeProductVO.setZone2(true);
			}
			if(zoneList.contains(AniConstants.ZONE_3_ID)){
				employeeProductVO.setZone3(true);
			}
		}
				
		// form part2
		employeeProductVO.setLevelOfProtectionDocument(employeeProduct.getLevelOfProtection());
		employeeProductVO.setProductSummaryEditorText(employeeProduct.getSummary());
		employeeProductVO.setPriceDetailseditorText(employeeProduct.getPricingDetails());
		employeeProductVO.setIncludedEditorText(employeeProduct.getIncludeWarrantyDesc());
		
		// populating DocumentDetailsVO
		List<DocumentDetails> documentDetailsList = documentDetailsService.findByEpId_Status(employeeProductId, status);
		
		List<ProductDocumentsVO> productDocumentsVOList = new ArrayList<ProductDocumentsVO>();
		ProductDocumentsVO productDocumentsVO = null;
		FileEntry fileEntry = null;
		
		for(DocumentDetails documentDetails : documentDetailsList)
		{
			productDocumentsVO = new ProductDocumentsVO();
			try{
				fileEntry = documentDetailsService.getFileEntry(documentDetails.getDocumentId());
				productDocumentsVO.setDocumentName(fileEntry.getTitle());
			}catch(Exception e){}
			
			productDocumentsVO.setDocumentDetailsId(documentDetails.getDocumentDetailsId());
			productDocumentsVOList.add(productDocumentsVO);
		}
		
		employeeProductVO.setProductDocumentsVOList(productDocumentsVOList);
		
		
		// set tranche age in employee product
		
		if(offer.getTrancheAge() && employeeProduct.getEmployeeProductType().equals(AniConstants.BASE)){		
			List<String> tranchesage = getTrancheAgeByOfferId(offer.getOfferId(), true);
			if(!(tranchesage.size()==1 && tranchesage.contains(AniConstants.NO_TRANCHE_AGE))){
				employeeProductVO.setTranchesAges(tranchesage);
			}
		}
				
		LOGGER.info(MhmConstants.END+"populateEmployeeProductVOEdit");
		
		return employeeProductVO;
		
	}
	
	/**
	 * method to return the type of employee product 
	 * @param enterpriseProductId
	 * @param regime
	 * @param productStatus 
	 * @param offerStatus 
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public String getEmployeeProductType(String enterpriseProductId,
			String regime, String productStatus) throws MhmCheckedTechnicalException {
		
		LOGGER.info(MhmConstants.START+"getEmployeeProductType");
		EnterpriseProduct enterpriseProduct = enterPriseProductService.getByEnterpriseProductIdStatus(enterpriseProductId, productStatus);
		String employeeProductType = enterpriseProduct.getProductType();
		if(employeeProductType.equals(AniConstants.LEVEL))
		{
			employeeProductType = AniConstants.LEVEL;
		}
		else if(employeeProductType.equals(AniConstants.BASE_OPTION))
		{
			List<EmployeeProduct> employeeProducts = employeeProductService.findByenterpriseProductId_employeeProductType_regime(enterpriseProductId, AniConstants.BASE, regime);
			if(Validator.isNull(employeeProducts) || employeeProducts.size() == 0){
				employeeProductType = AniConstants.BASE;
			}
			else{
				employeeProductType = AniConstants.OPTION;
			}
		}
		LOGGER.info(MhmConstants.END+"getEmployeeProductType");
		return employeeProductType;
	}
	
	/**
	 * method to populate EmployeeProductPriceDetailsVO
	 * @param regime
	 * @param employeeProductPeriodId
	 * @param zone1
	 * @param zone2
	 * @param zone3
	 * @param status 
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public String populatePriceDetails(String regime, boolean zone1, boolean zone2, boolean zone3, long zone1EmployeeProductPeriodId, long zone2EmployeeProductPeriodId, long zone3EmployeeProductPeriodId, long zone4EmployeeProductPeriodId, String status,long structureId) throws MhmCheckedTechnicalException
	{
		LOGGER.info(MhmConstants.START+"populatePriceDetails");
		// populating EmployeeProductPriceDetailsVO
		EmployeeProductPriceDetailsVO priceDetailsVO = new EmployeeProductPriceDetailsVO();
		
		EmployeeProductPrice employeeProductPriceOne = null;
		EmployeeProductPrice employeeProductPriceTwo = null;
		EmployeeProductPrice employeeProductPriceThree = null;
		 
		long structureOne = 0l;
		long structureTwo = 0l;
		long structureThree = 0l;
		
		List<Long> structureAgeIds = null;
		
		if(structureId == AniConstants.STRUCTURE_ADULTE_ID){
			structureOne = AniConstants.STRUCTURE_DETAILS_F_ADULTE_ID;
			structureTwo = AniConstants.STRUCTURE_DETAILS_F_ENFANT_ID;
		} else if(structureId == AniConstants.STRUCTURE_AGE_ID){
			structureAgeIds = getStructureDetailsAgeId();
		} else {
			structureOne = AniConstants.STRUCTURE_DETAILS_S_SALARIE_ID;
			structureTwo = AniConstants.STRUCTURE_DETAILS_S_CONJOINT_ID;
			structureThree = AniConstants.STRUCTURE_DETAILS_S_ENFANT_ID;
		}
		
		if(regime.equals(AniConstants.RG))
		{
			if(zone1)
			{
				try {
					if(structureId == AniConstants.STRUCTURE_AGE_ID){
						priceDetailsVO.setZone1EmployeeProductPeriodId(zone1EmployeeProductPeriodId);
						Map<Long,String> zone1_Age_Emp = new HashMap<Long,String>();
						Map<Long,String> zone1_Age_Adh = new HashMap<Long,String>();

						for(Long structureAgeId : structureAgeIds){
							try{
								employeeProductPriceOne = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone1EmployeeProductPeriodId, structureAgeId, status);
								zone1_Age_Emp.put(structureAgeId, employeeProductPriceOne.getEmpPart());
								zone1_Age_Adh.put(structureAgeId, employeeProductPriceOne.getAdhPart());
							} catch (MhmCheckedTechnicalException e){
								zone1_Age_Emp.put(structureAgeId,EMPTY_PRICE);
								zone1_Age_Adh.put(structureAgeId, EMPTY_PRICE);
							}
						}
						
						priceDetailsVO.setZone1_Age_Emp(zone1_Age_Emp);
						priceDetailsVO.setZone1_Age_Adh(zone1_Age_Adh);
					} else{
						employeeProductPriceOne = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone1EmployeeProductPeriodId, structureOne, status);
						priceDetailsVO.setZone1EmployeeProductPeriodId(zone1EmployeeProductPeriodId);
						priceDetailsVO.setZone1_aduAdh(employeeProductPriceOne.getAdhPart());
						priceDetailsVO.setZone1_aduEmp(employeeProductPriceOne.getEmpPart());
						
						employeeProductPriceTwo = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone1EmployeeProductPeriodId, structureTwo, status);
						priceDetailsVO.setZone1EmployeeProductPeriodId(zone1EmployeeProductPeriodId);
						priceDetailsVO.setZone1_enfAdh(employeeProductPriceTwo.getAdhPart());
						priceDetailsVO.setZone1_enfEmp(employeeProductPriceTwo.getEmpPart());
						
						if(structureId != AniConstants.STRUCTURE_ADULTE_ID){
							employeeProductPriceThree = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone1EmployeeProductPeriodId, structureThree, status);
							priceDetailsVO.setZone1EmployeeProductPeriodId(zone1EmployeeProductPeriodId);
							priceDetailsVO.setZone1_conAdh(employeeProductPriceThree.getAdhPart());
							priceDetailsVO.setZone1_conEmp(employeeProductPriceThree.getEmpPart());
						} 
					}		
				} catch (MhmCheckedTechnicalException e) {}
			}
			if(zone2)
			{
				try {
					if(structureId == AniConstants.STRUCTURE_AGE_ID){
						priceDetailsVO.setZone2EmployeeProductPeriodId(zone2EmployeeProductPeriodId);
						Map<Long,String> zone2_Age_Emp = new HashMap<Long,String>();
						Map<Long,String> zone2_Age_Adh = new HashMap<Long,String>();

						for(Long structureAgeId : structureAgeIds){
							try{
								employeeProductPriceOne = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone2EmployeeProductPeriodId, structureAgeId, status);
								zone2_Age_Emp.put(structureAgeId, employeeProductPriceOne.getEmpPart());
								zone2_Age_Adh.put(structureAgeId, employeeProductPriceOne.getAdhPart());
							} catch (MhmCheckedTechnicalException e){
								zone2_Age_Emp.put(structureAgeId,EMPTY_PRICE);
								zone2_Age_Adh.put(structureAgeId, EMPTY_PRICE);
							}
						}
						
						priceDetailsVO.setZone2_Age_Emp(zone2_Age_Emp);
						priceDetailsVO.setZone2_Age_Adh(zone2_Age_Adh);
					} else{
						employeeProductPriceOne = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone2EmployeeProductPeriodId, structureOne, status);
						priceDetailsVO.setZone2EmployeeProductPeriodId(zone2EmployeeProductPeriodId);
						priceDetailsVO.setZone2_aduAdh(employeeProductPriceOne.getAdhPart());
						priceDetailsVO.setZone2_aduEmp(employeeProductPriceOne.getEmpPart());
						
						employeeProductPriceTwo = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone2EmployeeProductPeriodId, structureTwo, status);
						priceDetailsVO.setZone2EmployeeProductPeriodId(zone2EmployeeProductPeriodId);
						priceDetailsVO.setZone2_enfAdh(employeeProductPriceTwo.getAdhPart());
						priceDetailsVO.setZone2_enfEmp(employeeProductPriceTwo.getEmpPart());
						
						if(structureId != AniConstants.STRUCTURE_ADULTE_ID){
							employeeProductPriceThree = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone2EmployeeProductPeriodId, structureThree, status);
							priceDetailsVO.setZone2EmployeeProductPeriodId(zone1EmployeeProductPeriodId);
							priceDetailsVO.setZone2_conAdh(employeeProductPriceThree.getAdhPart());
							priceDetailsVO.setZone2_conEmp(employeeProductPriceThree.getEmpPart());
						} 
					}
				} catch (MhmCheckedTechnicalException e) {}
			}
			if(zone3)
			{
				try {
					if(structureId == AniConstants.STRUCTURE_AGE_ID){
						priceDetailsVO.setZone3EmployeeProductPeriodId(zone3EmployeeProductPeriodId);
						Map<Long,String> zone3_Age_Emp = new HashMap<Long,String>();
						Map<Long,String> zone3_Age_Adh = new HashMap<Long,String>();

						for(Long structureAgeId : structureAgeIds){
							try{
								employeeProductPriceOne = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone3EmployeeProductPeriodId, structureAgeId, status);
								zone3_Age_Emp.put(structureAgeId, employeeProductPriceOne.getEmpPart());
								zone3_Age_Adh.put(structureAgeId, employeeProductPriceOne.getAdhPart());
							} catch (MhmCheckedTechnicalException e){
								zone3_Age_Emp.put(structureAgeId,EMPTY_PRICE);
								zone3_Age_Adh.put(structureAgeId, EMPTY_PRICE);
							}
						}
						
						priceDetailsVO.setZone3_Age_Emp(zone3_Age_Emp);
						priceDetailsVO.setZone3_Age_Adh(zone3_Age_Adh);
					} else{
						employeeProductPriceOne = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone3EmployeeProductPeriodId, structureOne, status);
						priceDetailsVO.setZone3EmployeeProductPeriodId(zone3EmployeeProductPeriodId);
						priceDetailsVO.setZone3_aduAdh(employeeProductPriceOne.getAdhPart());
						priceDetailsVO.setZone3_aduEmp(employeeProductPriceOne.getEmpPart());
						
						employeeProductPriceTwo = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone3EmployeeProductPeriodId, structureTwo, status);
						priceDetailsVO.setZone3EmployeeProductPeriodId(zone3EmployeeProductPeriodId);
						priceDetailsVO.setZone3_enfAdh(employeeProductPriceTwo.getAdhPart());
						priceDetailsVO.setZone3_enfEmp(employeeProductPriceTwo.getEmpPart());
						
						if(structureId != AniConstants.STRUCTURE_ADULTE_ID){
							employeeProductPriceThree = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone3EmployeeProductPeriodId, structureThree, status);
							priceDetailsVO.setZone3EmployeeProductPeriodId(zone3EmployeeProductPeriodId);
							priceDetailsVO.setZone3_conAdh(employeeProductPriceThree.getAdhPart());
							priceDetailsVO.setZone3_conEmp(employeeProductPriceThree.getEmpPart());
						}
					}
				} catch (MhmCheckedTechnicalException e) {}
			}
		}
		else
		{
			try {
				if(structureId == AniConstants.STRUCTURE_AGE_ID){
					priceDetailsVO.setZone4EmployeeProductPeriodId(zone4EmployeeProductPeriodId);
					Map<Long,String> zone4_Age_Emp = new HashMap<Long,String>();
					Map<Long,String> zone4_Age_Adh = new HashMap<Long,String>();

					for(Long structureAgeId : structureAgeIds){
						try{
							employeeProductPriceOne = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone4EmployeeProductPeriodId, structureAgeId, status);
							zone4_Age_Emp.put(structureAgeId, employeeProductPriceOne.getEmpPart());
							zone4_Age_Adh.put(structureAgeId, employeeProductPriceOne.getAdhPart());
						} catch (MhmCheckedTechnicalException e){
							zone4_Age_Emp.put(structureAgeId,EMPTY_PRICE);
							zone4_Age_Adh.put(structureAgeId, EMPTY_PRICE);
						}
					}
					
					priceDetailsVO.setZone4_Age_Emp(zone4_Age_Emp);
					priceDetailsVO.setZone4_Age_Adh(zone4_Age_Adh);
				} else{
					employeeProductPriceOne = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone4EmployeeProductPeriodId, structureOne, status);
					priceDetailsVO.setZone4EmployeeProductPeriodId(zone4EmployeeProductPeriodId);
					priceDetailsVO.setZone4_aduAdh(employeeProductPriceOne.getAdhPart());
					priceDetailsVO.setZone4_aduEmp(employeeProductPriceOne.getEmpPart());
					
					employeeProductPriceTwo = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone4EmployeeProductPeriodId, structureTwo, status);
					priceDetailsVO.setZone4EmployeeProductPeriodId(zone4EmployeeProductPeriodId);
					priceDetailsVO.setZone4_enfAdh(employeeProductPriceTwo.getAdhPart());
					priceDetailsVO.setZone4_enfEmp(employeeProductPriceTwo.getEmpPart());
					
					if(structureId != AniConstants.STRUCTURE_ADULTE_ID){
						employeeProductPriceThree = employeeProductPriceService.findByEmpProdPeriodId_StruDetailsId_Status(zone4EmployeeProductPeriodId, structureThree, status);
						priceDetailsVO.setZone4EmployeeProductPeriodId(zone4EmployeeProductPeriodId);
						priceDetailsVO.setZone4_conAdh(employeeProductPriceThree.getAdhPart());
						priceDetailsVO.setZone4_conEmp(employeeProductPriceThree.getEmpPart());
					}
				}
			} catch (MhmCheckedTechnicalException e) {}
		}
		
		String jsonString = JSONFactoryUtil.serialize(priceDetailsVO);
		LOGGER.info(MhmConstants.END+"populatePriceDetails");
		return jsonString;
	}
	
	/**
	 * method to get the associated base Id
	 * @param enterpriseProductId
	 * @param regime
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	private String getAssociatedBaseId(String enterpriseProductId,
			String regime) throws MhmCheckedTechnicalException {
			
			LOGGER.info(MhmConstants.START+"getAssociatedBaseId");
			String associatedBaseId = StringPool.BLANK;
			List<EmployeeProduct> employeeProducts = employeeProductService.findByenterpriseProductId_employeeProductType_regime(enterpriseProductId, AniConstants.BASE, regime);
			if(Validator.isNotNull(employeeProducts) &&  !employeeProducts.isEmpty()){
				associatedBaseId = employeeProducts.get(0).getEmployeeProductId();
			}
			LOGGER.info(MhmConstants.END+"getAssociatedBaseId");
		return associatedBaseId;
	}
	
	/**
	 * method to update employeeProduct modified date
	 * @param employeeProduct
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public EmployeeProduct updateEmployeeProductModifiedDate(String employeeProductId, String status) throws MhmCheckedTechnicalException
	{
		LOGGER.info(MhmConstants.START+"updateEmployeeProductModifiedDate");
		EmployeeProduct employeeProduct = employeeProductService.findByemployeeProductId_status(employeeProductId, status);
		employeeProduct.setModifiedDate(new Date());
		LOGGER.info(MhmConstants.END+"updateEmployeeProductModifiedDate");
		return employeeProductService.updateEmployeeProduct(employeeProduct);
	}
	
	/**
	 * method to get StructureDetails
	 * @param structureLabel
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public StructureDetails getStructureDetailsByStructureLabel(
			String structureLabel) throws MhmCheckedTechnicalException
	{
		return offerService.getStructureDetailsByStructureLabel(structureLabel);		
	}
	
	/**
	 * method to update employeeProduct
	 * @param employeeProduct
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public EmployeeProduct updateEmployeeProduct(EmployeeProduct employeeProduct) throws MhmCheckedTechnicalException
	{
		return employeeProductService.updateEmployeeProduct(employeeProduct);
	}
	
	/**
	 * 
	 * @param employeeProductPeriodId
	 * @throws MhmCheckedTechnicalException
	 */
	public void deleteEmployeeProductPeriod(long employeeProductPeriodId) throws MhmCheckedTechnicalException
	{
		employeeProductPeriodService.deleteEmployeeProductPeriod(employeeProductPeriodId);
	}
	
	/**
	 * method to get employee product
	 * @param employeeProductId
	 * @param status
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public EmployeeProduct findByemployeeProductId_status(
			String employeeProductId, String status) throws MhmCheckedTechnicalException{
		return employeeProductService.findByemployeeProductId_status(
				employeeProductId, status);
	}

	/**
	 * method to get employee product list
	 * @param enterpriseProductId
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public List<EmployeeProduct> findByenterpriseProductId(
			String enterpriseProductId) throws MhmCheckedTechnicalException{
		return employeeProductService
				.findByenterpriseProductId(enterpriseProductId);
	}
	
	/**
	 * method to get EmployeeProductPriceList
	 * @param employeeProductPeriodId
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public List<EmployeeProductPrice> getEmployeeProductPriceList(
			long employeeProductPeriodId) throws MhmCheckedTechnicalException
	{
		return employeeProductPriceService.findByemployeeProductPeriodId(employeeProductPeriodId);
	}
	
	/**
	 * method to get EnterpriseProduct from 
	 * @param offerId
	 * @param status
	 * @return EnterpriseProduct
	 * @throws MhmCheckedTechnicalException
	 */	
	public EnterpriseProduct getByEnterpriseProductIdStatus(String enterpriseProductId,String status) throws MhmCheckedTechnicalException {
		return enterPriseProductService.getByEnterpriseProductIdStatus(enterpriseProductId,status);
	}
	
	/**
	 * method to get offer on basis of offerId and status
	 * @param offerId
	 * @param status
	 * @return Offer
	 * @throws MhmCheckedTechnicalException
	 */
	public Offer getOffer(String offerId,String status) throws MhmCheckedTechnicalException{
		return offerService.getOffer(offerId, status);
	}
	
	/**
	 * method to get Situation
	 * @param situationId
	 * @return
	 * @throws MhmCheckedTechnicalException 
	 */
	public Situation getSituation(long situationId) throws MhmCheckedTechnicalException
	{
		return offerService.getSituation(situationId);
	}
	
	/**
	 * method to get the employeeProduct list using enterpriseProductId, employeeProductType, regime
	 * @param enterpriseProductId
	 * @param employeeProductType
	 * @param regime
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public List<EmployeeProduct> findByenterpriseProductId_employeeProductType_regime(
			String enterpriseProductId, java.lang.String employeeProductType,
			java.lang.String regime) throws MhmCheckedTechnicalException
	{
			return employeeProductService.findByenterpriseProductId_employeeProductType_regime(enterpriseProductId, employeeProductType, regime);
	}
	
	/**
	 * method to find EmployeeProductPeriod
	 * @param employeeProductPeriodId
	 * @param status
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public EmployeeProductPeriod findByEmployeeProductPeriodId_Status(
			long employeeProductPeriodId, java.lang.String status) throws MhmCheckedTechnicalException{
				return employeeProductPeriodService
						   .findByEmployeeProductPeriodId_Status(employeeProductPeriodId, status);
		}
	
	/**
	 * method to get Structure
	 * @param structureId
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public Structure getStructure(long structureId) throws MhmCheckedTechnicalException
	{
		return offerService.getStructure(structureId);
	}
	
	/**
	 * method to get Zone
	 * @param zoneId
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public Zone getZone(long zoneId) throws MhmCheckedTechnicalException
	{
		return offerService.getZone(zoneId);
	}
	
	// convert date from String
	public Date convertDateFromString(String date) throws ParseException{
		return dateService.convertDateFromString(date);
	}
	
	/**
	 * method to get EmployeeProductPrice List 
	 * @param employeeProductPeriodId
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public List<EmployeeProductPrice> getEmployeeProductPriceListByEmployeeProductPeriodId(
			long employeeProductPeriodId) throws MhmCheckedTechnicalException
	{
		return employeeProductPriceService.findByemployeeProductPeriodId(employeeProductPeriodId);
	}
	
	public EmployeeProductCodesTarif findByZoneId_EmployeeProductId_Status(long zoneId, String employeeProductId, String status) throws MhmCheckedTechnicalException
	{
		return employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status(zoneId, employeeProductId, status);
	}
	/**
	 * method to delete employee product price
	 * @param employeeProductPriceId
	 * @throws MhmCheckedTechnicalException
	 */
	public void deleteEmployeeProductPrice(long employeeProductPriceId) throws MhmCheckedTechnicalException
	{
		employeeProductPriceService.deleteEmployeeProductPrice(employeeProductPriceId);
	}
	
	/**
	 * method to get the employee product completed status
	 * @param employeeProductId
	 * @param regime
	 * @param zone1
	 * @param zone2
	 * @param zone3
	 * @param status
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public String getCompletedStatus(String employeeProductId,String codeProductTechnique, String regime, boolean zone1, boolean zone2, boolean zone3, String status,long structureId, List<String> tranchesAge) throws MhmCheckedTechnicalException
	{
		LOGGER.info(MhmConstants.START+"getCompletedStatus");
		EmployeeProductCodesTarif employeeProductCodesTarif = null;
		String tempStatus = AniConstants.NON;
		
		EmployeeProduct employeeProduct = employeeProductService.findByemployeeProductId_status(employeeProductId, status);
		if(codeProductTechnique==null){
			codeProductTechnique = employeeProduct.getCodeProduitTechnique();
		}
		if(Validator.isNull(codeProductTechnique))
		{
			return AniConstants.NON;
		}
		
		for(String trancheAge : tranchesAge){
			
			if(regime.equals(AniConstants.RG))
			{		
				if(zone1)
				{
					employeeProductCodesTarif = null;
					try {
						employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_1_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
					} catch (MhmCheckedTechnicalException e) {
						return AniConstants.NON;
					} catch (MhmUncheckedTechnicalException e) {
					}
					if(employeeProductCodesTarif==null){
						return AniConstants.NON;
					}
					if(Validator.isNull(employeeProductCodesTarif.getCodesTarif()))
					{
						return AniConstants.NON;
					}
					tempStatus = getCompletedStatusByCodesTarif(employeeProductCodesTarif.getEmployeeproductcodestarifId(), status);
					if(tempStatus.equals(AniConstants.NON))
					{
						return AniConstants.NON;
					}
				}
				if(zone2)
				{
					employeeProductCodesTarif = null;
					try {
						employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_2_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
					} catch (MhmCheckedTechnicalException e) {
						return AniConstants.NON;
					} catch (MhmUncheckedTechnicalException e) {
					}
					
					if(employeeProductCodesTarif==null){
						return AniConstants.NON;
					}
					if(Validator.isNull(employeeProductCodesTarif.getCodesTarif()))
					{
						return AniConstants.NON;
					}
					tempStatus = getCompletedStatusByCodesTarif(employeeProductCodesTarif.getEmployeeproductcodestarifId(), status);
					if(tempStatus.equals(AniConstants.NON))
					{
						return AniConstants.NON;
					}
				}
				if(zone3)
				{
					employeeProductCodesTarif = null;
					try {
						employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_3_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
					} catch (MhmCheckedTechnicalException e) {
						return AniConstants.NON;
					} catch (MhmUncheckedTechnicalException e) {
					}
					
					if(employeeProductCodesTarif==null){
						return AniConstants.NON;
					}
					if(Validator.isNull(employeeProductCodesTarif.getCodesTarif()))
					{
						return AniConstants.NON;
					}
					tempStatus = getCompletedStatusByCodesTarif(employeeProductCodesTarif.getEmployeeproductcodestarifId(), status);
					if(tempStatus.equals(AniConstants.NON))
					{
						return AniConstants.NON;
					}
				}
				LOGGER.info(MhmConstants.END+"getCompletedStatus");
				return AniConstants.OUI;
			}
			else
			{
				employeeProductCodesTarif = null;
				try {
					employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_4_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
				} catch (MhmCheckedTechnicalException e) {
					return AniConstants.NON;
				} catch (MhmUncheckedTechnicalException e) {
				}
				if(employeeProductCodesTarif==null){
					return AniConstants.NON;
				}
				if(Validator.isNull(employeeProductCodesTarif.getCodesTarif()))
				{
					return AniConstants.NON;
				}
				tempStatus = getCompletedStatusByCodesTarif(employeeProductCodesTarif.getEmployeeproductcodestarifId(), status);
				LOGGER.info(MhmConstants.END+"getCompletedStatus");
				return tempStatus;
			}
		}
		
		return tempStatus;
	}
	
	/**
	 * Utility method to return completed status based on Codes Tarif
	 * @param employeeProductCodesTarifId
	 * @param status
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	private String getCompletedStatusByCodesTarif(long employeeProductCodesTarifId, String status) throws MhmCheckedTechnicalException
	{
		
		List<EmployeeProductPrice> employeeProductPriceList = null;
		List<EmployeeProductPeriod> employeeProductPeriodList = employeeProductPeriodService.findByEmployeeProductCodesTarifId_Status(employeeProductCodesTarifId, status);
		if(Validator.isNull(employeeProductPeriodList) || employeeProductPeriodList.size() == 0)
		{
			return AniConstants.NON;
		}
		for(EmployeeProductPeriod employeeProductPeriod : employeeProductPeriodList)
		{
			if(Validator.isNull(employeeProductPeriod.getTaxe())){
				return AniConstants.NON;
			}
			
			employeeProductPriceList = getEmployeeProductPriceListByEmployeeProductPeriodId(employeeProductPeriod.getEmployeeProductPeriodId());
			if(Validator.isNull(employeeProductPriceList) || employeeProductPriceList.size() == 0)
			{
				return AniConstants.NON;
			}
			
			for(EmployeeProductPrice employeeProductPrice : employeeProductPriceList)
			{
				if(Validator.isNull(employeeProductPrice.getAdhPart()) && Validator.isNull(employeeProductPrice.getEmpPart()))
				{
					return AniConstants.NON;
				}
			}
		}
		return AniConstants.OUI;
	}
	
	/**
	 * method to update employeeProduct completed status
	 * @param employeeProduct
	 * @param completedStatus
	 * @param status 
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public EmployeeProduct updateEmployeeProductCompletedStatus(String employeeProductId, String completedStatus, String status) throws MhmCheckedTechnicalException
	{
		LOGGER.info(MhmConstants.START+"updateEmployeeProductCompletedStatus");
		EmployeeProduct employeeProduct = employeeProductService.findByemployeeProductId_status(employeeProductId, status);
		employeeProduct.setCompleted(completedStatus);
		employeeProduct.setModifiedDate(new Date());
		LOGGER.info(MhmConstants.END+"updateEmployeeProductCompletedStatus");
		return employeeProductService.updateEmployeeProduct(employeeProduct);
	}
	
	/**
	 * method to populate CodesTerifData
	 * @param regime
	 * @param employeeProductId
	 * @param zone1
	 * @param zone2
	 * @param zone3
	 * @param status
	 * @return
	 */
	public CodesTarifVO populateCodesTerifVO(String regime, String employeeProductId,long structureId, boolean zone1, boolean zone2, boolean zone3, String status, String trancheAge)
	{
		LOGGER.info(MhmConstants.START+"populateCodesTerifVO");
		CodesTarifVO codesTarifVO = new CodesTarifVO();
		EmployeeProductCodesTarif employeeProductCodesTarif = null;
		
		if(regime.equals(AniConstants.RG))
		{
			if(zone1)
			{
				try {
					employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_1_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
					
					codesTarifVO.setZone1CodesTarif(employeeProductCodesTarif.getCodesTarif());
					codesTarifVO.setZone1CodeTerifId(employeeProductCodesTarif.getEmployeeproductcodestarifId());
					codesTarifVO.setZone1PriceType(employeeProductCodesTarif.getPriceType());
					codesTarifVO.setZone1PeriodList(populatePeriodVOList(employeeProductCodesTarif.getEmployeeproductcodestarifId(), employeeProductCodesTarif.getStatus()));
				} catch (MhmCheckedTechnicalException e) {} catch (MhmUncheckedTechnicalException e) {
				}
			}
			if(zone2)
			{
				try {
					employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_2_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
					
					codesTarifVO.setZone2CodesTarif(employeeProductCodesTarif.getCodesTarif());
					codesTarifVO.setZone2CodeTerifId(employeeProductCodesTarif.getEmployeeproductcodestarifId());
					codesTarifVO.setZone2PriceType(employeeProductCodesTarif.getPriceType());
					codesTarifVO.setZone2PeriodList(populatePeriodVOList(employeeProductCodesTarif.getEmployeeproductcodestarifId(), employeeProductCodesTarif.getStatus()));
				} catch (MhmCheckedTechnicalException e) {} catch (MhmUncheckedTechnicalException e) {
				}
			}
			if(zone3)
			{
				try {
					employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_3_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
					
					codesTarifVO.setZone3CodesTarif(employeeProductCodesTarif.getCodesTarif());
					codesTarifVO.setZone3CodeTerifId(employeeProductCodesTarif.getEmployeeproductcodestarifId());
					codesTarifVO.setZone3PriceType(employeeProductCodesTarif.getPriceType());
					codesTarifVO.setZone3PeriodList(populatePeriodVOList(employeeProductCodesTarif.getEmployeeproductcodestarifId(), employeeProductCodesTarif.getStatus()));
				} catch (MhmCheckedTechnicalException e) {} catch (MhmUncheckedTechnicalException e) {
				}
			}
		}
		else
		{
			try {
				employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_4_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
				
				codesTarifVO.setZone4CodesTarif(employeeProductCodesTarif.getCodesTarif());
				codesTarifVO.setZone4CodeTerifId(employeeProductCodesTarif.getEmployeeproductcodestarifId());
				codesTarifVO.setZone4PriceType(employeeProductCodesTarif.getPriceType());
				codesTarifVO.setZone4PeriodList(populatePeriodVOList(employeeProductCodesTarif.getEmployeeproductcodestarifId(), employeeProductCodesTarif.getStatus()));
			} catch (MhmCheckedTechnicalException e) {} catch (MhmUncheckedTechnicalException e) {
			}
		}
		LOGGER.info(MhmConstants.END+"populateCodesTerifVO");
		return codesTarifVO;
	}
	
	/**
	 * method to populate CodesTerifData
	 * @param regime
	 * @param employeeProductId
	 * @param zone1
	 * @param zone2
	 * @param zone3
	 * @param status
	 * @return
	 */
	public CodesTarifVO populateCodesTerifAgesVO(String regime, String employeeProductId,long structureId, boolean zone1, boolean zone2, boolean zone3, String status, String trancheAge)
	{
		LOGGER.info(MhmConstants.START+"populateCodesTerifVO");
		CodesTarifVO codesTarifVO = new CodesTarifVO();
		EmployeeProductCodesTarif employeeProductCodesTarif = null;
		
		if(regime.equals(AniConstants.RG))
		{
			if(zone1)
			{
				try {
					employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_1_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
					
					codesTarifVO.setZone1CodesTarif(employeeProductCodesTarif.getCodesTarif());
					codesTarifVO.setZone1CodeTerifId(employeeProductCodesTarif.getEmployeeproductcodestarifId());
					codesTarifVO.setZone1PriceType(employeeProductCodesTarif.getPriceType());
					codesTarifVO.setZone1PeriodList(populatePeriodVOList(employeeProductCodesTarif.getEmployeeproductcodestarifId(), employeeProductCodesTarif.getStatus()));
				} catch (MhmCheckedTechnicalException e) {} catch (MhmUncheckedTechnicalException e) {
				}
			}
			if(zone2)
			{
				try {
					employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_2_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
					
					codesTarifVO.setZone2CodesTarif(employeeProductCodesTarif.getCodesTarif());
					codesTarifVO.setZone2CodeTerifId(employeeProductCodesTarif.getEmployeeproductcodestarifId());
					codesTarifVO.setZone2PriceType(employeeProductCodesTarif.getPriceType());
					codesTarifVO.setZone2PeriodList(populatePeriodVOList(employeeProductCodesTarif.getEmployeeproductcodestarifId(), employeeProductCodesTarif.getStatus()));
				} catch (MhmCheckedTechnicalException e) {} catch (MhmUncheckedTechnicalException e) {
				}
			}
			if(zone3)
			{
				try {
					employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_3_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
					
					codesTarifVO.setZone3CodesTarif(employeeProductCodesTarif.getCodesTarif());
					codesTarifVO.setZone3CodeTerifId(employeeProductCodesTarif.getEmployeeproductcodestarifId());
					codesTarifVO.setZone3PriceType(employeeProductCodesTarif.getPriceType());
					codesTarifVO.setZone3PeriodList(populatePeriodVOList(employeeProductCodesTarif.getEmployeeproductcodestarifId(), employeeProductCodesTarif.getStatus()));
				} catch (MhmCheckedTechnicalException e) {} catch (MhmUncheckedTechnicalException e) {
				}
			}
		}
		else
		{
			try {
				employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(AniConstants.ZONE_4_ID, employeeProductId, status,AniConstants.STATIC_SITUATION_ID,structureId, trancheAge);
				
				codesTarifVO.setZone4CodesTarif(employeeProductCodesTarif.getCodesTarif());
				codesTarifVO.setZone4CodeTerifId(employeeProductCodesTarif.getEmployeeproductcodestarifId());
				codesTarifVO.setZone4PriceType(employeeProductCodesTarif.getPriceType());
				codesTarifVO.setZone4PeriodList(populatePeriodVOList(employeeProductCodesTarif.getEmployeeproductcodestarifId(), employeeProductCodesTarif.getStatus()));
			} catch (MhmCheckedTechnicalException e) {} catch (MhmUncheckedTechnicalException e) {
			}
		}
		LOGGER.info(MhmConstants.END+"populateCodesTerifVO");
		return codesTarifVO;
	}
	
	/**
	 * methed to get EmployeeProductPeriodVO List
	 * @param codesTarifId
	 * @param status
	 * @return
	 */
	public List<EmployeeProductPeriodVO> populatePeriodVOList(long codesTarifId, String status)
	{
		LOGGER.info(MhmConstants.START+"populatePeriodVOList");
		EmployeeProductPeriodVO periodVO = null;
		List<EmployeeProductPeriodVO> employeeProductPeriodVOList = new ArrayList<EmployeeProductPeriodVO>();
		List<EmployeeProductPeriod> employeeProductPeriodList = null; 
		
		try {
			employeeProductPeriodList = employeeProductPeriodService.findByEmployeeProductCodesTarifId_Status(codesTarifId, status);
		} catch (MhmCheckedTechnicalException e) {}
		
		if(Validator.isNotNull(employeeProductPeriodList) && employeeProductPeriodList.size() > 0)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String startDate = StringPool.BLANK;
			String endDate = StringPool.BLANK;
			
			for(EmployeeProductPeriod productPeriod : employeeProductPeriodList)
			{
				periodVO = new EmployeeProductPeriodVO();
				startDate = sdf.format(productPeriod.getStartDate());
				endDate = sdf.format(productPeriod.getEndDate());
				
				periodVO.setEmployeeProductPeriodId(productPeriod.getEmployeeProductPeriodId());
				periodVO.setStartDate(startDate);
				periodVO.setEndDate(endDate);
				periodVO.setTaxe(productPeriod.getTaxe());
				
				employeeProductPeriodVOList.add(periodVO);
			}
		}
		LOGGER.info(MhmConstants.END+"populatePeriodVOList");
		return employeeProductPeriodVOList;
	}
	
	/**
	 * method to Add/Update Employee Product CodesTarif
	 * @param employeeProductId
	 * @param zoneId
	 * @param priceType
	 * @param codesTarif
	 * @param situationId
	 * @param structureId
	 * @param status
	 * @return
	 * @throws MhmCheckedTechnicalException 
	 */
	public EmployeeProductCodesTarif addOrUpdateCodesTarif(
			String employeeProductId, long zoneId, String priceType,
			String codesTarif, long situationId, long structureId, String status, String trancheAge) throws MhmCheckedTechnicalException{
		
		LOGGER.info(MhmConstants.START+"addOrUpdateCodesTarif");
		EmployeeProductCodesTarif employeeProductCodesTarif = null;
		
		try {
			employeeProductCodesTarif = employeeProductCodesTarifService.findByZoneId_EmployeeProductId_Status_SituationId_StructureId_trancheAge(zoneId, employeeProductId, status, AniConstants.STATIC_SITUATION_ID, structureId, trancheAge);
		} catch (MhmCheckedTechnicalException e) {} catch (MhmUncheckedTechnicalException e) {
		}
		
		if(Validator.isNull(employeeProductCodesTarif))
		{
			employeeProductCodesTarif = employeeProductCodesTarifService.createEmployeeProductCodesTarif();
			
			employeeProductCodesTarif.setEmployeeProductId(employeeProductId);
			employeeProductCodesTarif.setZoneId(zoneId);
			employeeProductCodesTarif.setPriceType(priceType);
			employeeProductCodesTarif.setCodesTarif(codesTarif);
			employeeProductCodesTarif.setSituationId(situationId);
			employeeProductCodesTarif.setStructureId(structureId);
			employeeProductCodesTarif.setStatus(status);
			employeeProductCodesTarif.setTrancheAge(trancheAge);
			employeeProductCodesTarif.setPositionTrancheAge(getPositionTrancheAge(zoneId, employeeProductId, trancheAge, employeeProductCodesTarif));
			employeeProductCodesTarifService.addEmployeeProductCodesTarif(employeeProductCodesTarif);
		}
		else
		{
			employeeProductCodesTarif.setCodesTarif(codesTarif);
			employeeProductCodesTarif.setPriceType(priceType);
			employeeProductCodesTarif.setStatus(status);
			employeeProductCodesTarif.setSituationId(situationId);
			employeeProductCodesTarif.setStructureId(structureId);
			employeeProductCodesTarif.setTrancheAge(trancheAge);
			employeeProductCodesTarif.setPositionTrancheAge(getPositionTrancheAge(zoneId, employeeProductId, trancheAge, employeeProductCodesTarif));
			employeeProductCodesTarifService.updateEmployeeProductCodesTarif(employeeProductCodesTarif);
		}
				
		LOGGER.info(MhmConstants.END+"addOrUpdateCodesTarif");
		return employeeProductCodesTarif;
	}
	
	/**
	 * method to clone EmployeeProduct and its descendants i.e. EmployeeProductCodesTarif, EmployeeProductPeriod, EmployeeProductPrice
	 * @param epId
	 * @param fetchFrom
	 * @param changeTo
	 * @return 
	 */
	public boolean cloneEmployeeProductAndDescendants(EmpProdVO employeeProductDescendantVO)
	{
		
		LOGGER.info(MhmConstants.START+"cloneEmployeeProductAndDescendants");
		boolean empProdFlag = false;
		boolean errorFlag = false;
		// clone EmployeeProduct
		try {
			employeeProductService.addEmployeeProduct(employeeProductDescendantVO.getEmployeeProduct(), false);
		} catch (MhmCheckedTechnicalException e) {
			empProdFlag = true;
		}
		// clone EmployeeProductCodesTarif
		EmployeeProductCodesTarif tempEmployeeProductCodesTarif = null;
		for(EmpProdCodeTarifVO codesTarif : employeeProductDescendantVO.getEmployeeProductCodesTarifList())
		{
			tempEmployeeProductCodesTarif = (EmployeeProductCodesTarif) codesTarif.getEmployeeProductCodesTarif().clone();
			try {
				tempEmployeeProductCodesTarif = employeeProductCodesTarifService.addEmployeeProductCodesTarif(tempEmployeeProductCodesTarif);
			} catch (MhmCheckedTechnicalException e) {
				empProdFlag = true;
			}
			
			// clone EmployeeProductPeriod
			EmployeeProductPeriod tempEmployeeProductPeriod = null;
			for(EmpProdPeriodVO employeeProductPeriod : codesTarif.getEmployeeProductPeriodList())
			{
				tempEmployeeProductPeriod = (EmployeeProductPeriod) employeeProductPeriod.getEmployeeProductPeriod().clone();
				tempEmployeeProductPeriod.setEmployeeproductcodestarifId(tempEmployeeProductCodesTarif.getEmployeeproductcodestarifId());
				try {
					tempEmployeeProductPeriod = employeeProductPeriodService.addEmployeeProductPeriod(tempEmployeeProductPeriod);
				} catch (MhmCheckedTechnicalException e) {
					empProdFlag = true;
				}
				
				// clone EmployeeProductPrice
				EmployeeProductPrice tempEmployeeProductPrice = null;
				for(EmployeeProductPrice employeeProductPrice : employeeProductPeriod.getEmployeeProductPriceList())
				{
					tempEmployeeProductPrice = (EmployeeProductPrice) employeeProductPrice.clone();
					tempEmployeeProductPrice.setEmployeeProductPeriodId(tempEmployeeProductPeriod.getEmployeeProductPeriodId());
					try {
						tempEmployeeProductPrice = employeeProductPriceService.addEmployeeProductPrice(tempEmployeeProductPrice);
					} catch (MhmCheckedTechnicalException e) {
						empProdFlag = true;
					}
				}
				
			}
		}
		
		// clone DocumentDetails
		DocumentDetails tempDocumentDetails = null;
		for(DocumentDetails documentDetails : employeeProductDescendantVO.getWarrantyDocumentVO().getDocumentDetails())
		{
			tempDocumentDetails = (DocumentDetails) documentDetails.clone();
			try {
				documentDetailsService.addDocumentDetails(tempDocumentDetails);
			} catch (MhmCheckedTechnicalException e) {
				errorFlag = true;
			}
		}
		
		// clone WarrantyDocument
		errorFlag = cloneWarrantyDocument(employeeProductDescendantVO.getWarrantyDocumentVO());
		
		if(errorFlag || empProdFlag)
			return true;
		else
			return false;
	}
	
	/**
     * Used to clone Warranty Document
     * @param warrantyDocumentVO
     * @return boolean
     */
     public boolean cloneWarrantyDocument(WarrantyDocumentVO warrantyDocumentVO)
     {
		boolean errorFlag = false;
		for(WarrantyVO warrantyVO:warrantyDocumentVO.getWarrantyVO()){
		try {
             WarrantyDetails tempWarrantyDetails = (WarrantyDetails)warrantyVO.getWarrantyDetails().clone();
             tempWarrantyDetails = warrantyDetailsService.addWarrantyDetails(tempWarrantyDetails);

             for(WarrantyExamples examples:warrantyVO.getWarrantyExamples()){
                    try {
                           WarrantyExamples tempWarrantyExamples = (WarrantyExamples)examples.clone();
                           tempWarrantyExamples.setWarrantyDetailsId(tempWarrantyDetails.getWarrantyDetailsId());
                           warrantyDetailsService.addWarrantyExample(tempWarrantyExamples);
                    } catch (MhmCheckedTechnicalException e) {
                           LOGGER.error("Issue in cloning WarrantyExamples :: " + examples.getWarrantyExampleId());
                           errorFlag=true;
                    }
             }
		 } catch (MhmCheckedTechnicalException e) {
			 LOGGER.error("Issue in cloning WarrantyDetails :: " + warrantyVO.getWarrantyDetails().getWarrantyDetailsId());
		     errorFlag=true;
		 }
	}
    return errorFlag;
}

	
    /**
    * method to populate EmployeeProductDescendantVO
    * @param employeeProduct
    * @param fetchFrom
    * @return 
     * @throws MhmCheckedTechnicalException
    */
    public EmpProdVO populateEmployeeProductDescendantVO(EmployeeProduct employeeProduct, String fetchFrom) throws MhmCheckedTechnicalException
    {
          LOGGER.info(MhmConstants.START+"populateEmployeeProductDescendantVO");
          EmpProdVO empProdVO = new EmpProdVO();
          List<EmployeeProductCodesTarif> codesTarifs = employeeProductCodesTarifService.findByEmployeeProductId_Status(employeeProduct.getEmployeeProductId(), fetchFrom);
          List<EmpProdCodeTarifVO> empProdCodeTarifVOList = new ArrayList<EmpProdCodeTarifVO>();
          for(EmployeeProductCodesTarif codesTarif:codesTarifs)
          {
        	    EmpProdCodeTarifVO empProdCodeTarifVO = new EmpProdCodeTarifVO();
                List<EmployeeProductPeriod> employeeProductPeriods = employeeProductPeriodService.findByEmployeeProductCodesTarifId_Status(codesTarif.getEmployeeproductcodestarifId(), fetchFrom);
                List<EmpProdPeriodVO> empProdPeriodVOList = new ArrayList<EmpProdPeriodVO>();
                for(EmployeeProductPeriod employeeProductPeriod:employeeProductPeriods)
                {
                	  EmpProdPeriodVO empProdPeriodVO = new EmpProdPeriodVO();	
                      List<EmployeeProductPrice> employeeProductPrices = employeeProductPriceService.findByEmployeeProductPeriodId_Status(employeeProductPeriod.getEmployeeProductPeriodId(), fetchFrom);
                      empProdPeriodVO.setEmployeeProductPeriod(employeeProductPeriod);
                      empProdPeriodVO.setEmployeeProductPriceList(employeeProductPrices);
                      empProdPeriodVOList.add(empProdPeriodVO);
                 }
                empProdCodeTarifVO.setEmployeeProductCodesTarif(codesTarif);
                empProdCodeTarifVO.setEmployeeProductPeriodList(empProdPeriodVOList);
                empProdCodeTarifVOList.add(empProdCodeTarifVO);
          }
          
          empProdVO.setEmployeeProduct(employeeProduct);
          empProdVO.setEmployeeProductCodesTarifList(empProdCodeTarifVOList);
          
          // populate WarrantyDocumentDesecendantVO
          WarrantyDocumentVO warrantyDocumentVO = populateWarrantyDocumentDesecendantVO(employeeProduct.getEmployeeProductId(), fetchFrom); 
          empProdVO.setWarrantyDocumentVO(warrantyDocumentVO);
          
          LOGGER.info(MhmConstants.END+"populateEmployeeProductDescendantVO");
          return empProdVO;
    }
    
    /**
    * method to populate WarrantyDocumentDesecendantVO
    * @param epId
    * @param fetchFrom
    * @return 
     * @throws MhmCheckedTechnicalException
    */
    public WarrantyDocumentVO populateWarrantyDocumentDesecendantVO(String epId, String fetchFrom) throws MhmCheckedTechnicalException 
    {
          LOGGER.info(MhmConstants.START+"populateWarrantyDocumentDesecendantVO");
          WarrantyDocumentVO warrantyDocumentVO = new WarrantyDocumentVO();
          List<WarrantyVO> warrantyVOs = new ArrayList<WarrantyVO>();
          
          List<DocumentDetails> documentDetails = documentDetailsService.findByEpId_Status(epId, fetchFrom);
          List<WarrantyDetails> warrantyDetails = warrantyDetailsService.findByEpId_Status(epId, fetchFrom);
          
          for(WarrantyDetails details:warrantyDetails)
          {
              List<WarrantyExamples> examples = warrantyDetailsService.findByWarrantyDetails_Status(details.getWarrantyDetailsId(), fetchFrom);
              WarrantyVO warrantyVO = new WarrantyVO();
              warrantyVO.setWarrantyDetails(details);
              warrantyVO.setWarrantyExamples(examples);
              
              warrantyVOs.add(warrantyVO);

          }
          warrantyDocumentVO.setWarrantyVO(warrantyVOs);
          warrantyDocumentVO.setDocumentDetails(documentDetails);
          
          LOGGER.info(MhmConstants.END+"populateWarrantyDocumentDesecendantVO");
          return warrantyDocumentVO;
    }
    
    /**
     * method to implement cloning when status is online
     * @param employeeProductId
     * @param status
     * @return
     * @throws MhmCheckedTechnicalException
     */
    public List<EmpProdVO> onlineCloneConditionMethod(String employeeProductId, String status) throws MhmCheckedTechnicalException
	{
    	LOGGER.info(MhmConstants.START+"onlineCloneConditionMethod");
		List<EmpProdVO> descendantVOs = new ArrayList<EmpProdVO>();
		EmployeeProduct employeeProduct = employeeProductService.findByemployeeProductId_status(employeeProductId, status);
		EmployeeProduct tempEmployeeProduct = null;
		if(employeeProduct.getEmployeeProductType().equals(AniConstants.BASE))
		{
			List<EmployeeProduct> employeeProducts = employeeProductService.findByEnterpriseProductIdRegimeStatus(employeeProduct.getEnterpriseProductId(), employeeProduct.getRegime(), status);
			for(EmployeeProduct empProduct : employeeProducts)
			{
				tempEmployeeProduct = (EmployeeProduct) empProduct.clone();
				descendantVOs.add(populateEmployeeProductDescendantVO(tempEmployeeProduct, AniConstants.ONLINE));
				try {
					offerManagementBusinessDelegate.updateEmployeeProductAndDescendants(empProduct, AniConstants.ONLINE, AniConstants.DRAFT);
				} catch (MhmCheckedTechnicalException e1) {
					LOGGER.error("EXCEPTION IN onlineCloneConditionMethod " + e1.getMessage());
				}
			}
		}
		else
		{
			tempEmployeeProduct = (EmployeeProduct) employeeProduct.clone();
			descendantVOs.add(populateEmployeeProductDescendantVO(tempEmployeeProduct, AniConstants.ONLINE));
			try {
				offerManagementBusinessDelegate.updateEmployeeProductAndDescendants(employeeProduct, AniConstants.ONLINE, AniConstants.DRAFT);
			} catch (MhmCheckedTechnicalException e1) {
				LOGGER.error("EXCEPTION IN onlineCloneConditionMethod "+ e1.getMessage());
			}
		}
		LOGGER.info(MhmConstants.END+"onlineCloneConditionMethod");
		return descendantVOs;
	}
	
	
	/**
	 * method to implement cloning when status is retire
	 * @param employeeProductId
	 * @param status
	 * @throws MhmCheckedTechnicalException
	 */
	public void retireCloneConditionMethod(String employeeProductId, String status) throws MhmCheckedTechnicalException
	{
		LOGGER.info(MhmConstants.START+"retireCloneConditionMethod");
		EmployeeProduct employeeProduct = employeeProductService.findByemployeeProductId_status(employeeProductId, status);
		if(employeeProduct.getEmployeeProductType().equals(AniConstants.BASE))
		{
			List<EmployeeProduct> employeeProducts = employeeProductService.findByEnterpriseProductIdRegimeStatus(employeeProduct.getEnterpriseProductId(), employeeProduct.getRegime(), status);
			for(EmployeeProduct empProduct : employeeProducts)
			{
				offerManagementBusinessDelegate.updateEmployeeProductAndDescendants(empProduct, AniConstants.RETIRE, AniConstants.DRAFT);
			}
		}
		else
		{
			offerManagementBusinessDelegate.updateEmployeeProductAndDescendants(employeeProduct, AniConstants.RETIRE, AniConstants.DRAFT);
		}
		LOGGER.info(MhmConstants.END+"retireCloneConditionMethod");
	}
	
	/**
	 * method to create render refresh url
	 * @param resourceRequest
	 * @param resourceResponse
	 * @param offerStatus
	 * @param productStatus
	 * @return
	 */
	public String createRenderRefreshURL(ResourceRequest resourceRequest, ResourceResponse resourceResponse,String offerStatus,String productStatus)
	{
		LOGGER.info(MhmConstants.START+"createRenderRefreshURL");
		PortletURL renderURL=resourceResponse.createRenderURL();
		renderURL.setParameter("action", "addEditEmployeeProduct");
		renderURL.setParameter("employeeProductId", ParamUtil.getString(resourceRequest, "employeeProductId"));
		renderURL.setParameter("offerId", ParamUtil.getString(resourceRequest, "offerId"));
		renderURL.setParameter("formAction", ParamUtil.getString(resourceRequest, "formAction"));
		renderURL.setParameter("status", AniConstants.DRAFT);
		renderURL.setParameter("offerStatus", offerStatus);
		renderURL.setParameter("productStatus", productStatus);
		LOGGER.info(MhmConstants.END+"createRenderRefreshURL");
		return renderURL.toString();
	}
	
	/**
	 * method to delete price data for given EmployeeProductCodesTarifId
	 * @param employeeproductcodestarifId
	 * @param status
	 * @throws MhmCheckedTechnicalException
	 */
	public void deletePriceData(long employeeproductcodestarifId, String status) throws MhmCheckedTechnicalException 
	{
		LOGGER.info(MhmConstants.START+"deletePriceData");
		List<EmployeeProductPeriod> employeeProductPeriods = employeeProductPeriodService.findByEmployeeProductCodesTarifId_Status(employeeproductcodestarifId, status);
		for(EmployeeProductPeriod period : employeeProductPeriods)
		{
			List<EmployeeProductPrice> employeeProductPrices = employeeProductPriceService.findByEmployeeProductPeriodId_Status(period.getEmployeeProductPeriodId(), status);
			for(EmployeeProductPrice price : employeeProductPrices)
			{
				try {
					employeeProductPriceService.deleteEmployeeProductPrice(price.getEmployeeProductPriceId());
				} catch (MhmCheckedTechnicalException e) {
					LOGGER.error("Error in delete EmployeeProductPrice: " + price.getEmployeeProductPriceId());
				}
			}
		}
		LOGGER.info(MhmConstants.END+"deletePriceData");
	}
	
	/**
	 * method to update enterprise product complete status
	 * @param enterpriseProductId
	 * @param productStatus
	 * @throws MhmCheckedTechnicalException 
	 */
	public void updateEnterpriseProductCompletedStatus(String enterpriseProductId, String status) throws MhmCheckedTechnicalException
	{
		LOGGER.info(MhmConstants.START+"updateEnterpriseProductCompletedStatus");

		EnterpriseProduct enterpriseProduct = offerManagementBusinessDelegate.enterpriseProductBusinessDelegate.getByEnterpriseProductIdStatus(enterpriseProductId, status);
		
		ModelMap map = new ModelMap();
		
		List<Offer> offers = offerManagementBusinessDelegate.offerService.getOfferByOfferId(enterpriseProduct.getOfferId());
		Offer offer = offers.get(0);
    	offerManagementBusinessDelegate.enterpriseProductBusinessDelegate.availableZones(map, enterpriseProduct.getOfferId(), offer.getStatus(), enterpriseProduct.getEnterpriseProductId(), enterpriseProduct.getStatus());
    	
		offerManagementBusinessDelegate.enterpriseProductBusinessDelegate.setModelContrat(map, enterpriseProduct.getEnterpriseProductId(), enterpriseProduct.getStatus());
		
		RefModelVO refModelVO = (RefModelVO)map.get("refModelVO");
		
		boolean setCompleted = offerManagementBusinessDelegate.enterpriseProductBusinessDelegate.setCompletedOrNot(map,refModelVO);
		
		int countEmployeeproducts = offerManagementBusinessDelegate.employeeProductService.countByEnterpriseProductId_Status(enterpriseProduct.getEnterpriseProductId(), enterpriseProduct.getStatus());
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(" SET COMPLETED :: " + setCompleted);
			LOGGER.debug("COUNT EMP PROD :: " + countEmployeeproducts);
		}
		
		if(setCompleted && countEmployeeproducts > 0){
			enterpriseProduct.setCompleted(AniConstants.OUI);
		} else {
			enterpriseProduct.setCompleted(AniConstants.NON);
		}
		offerManagementBusinessDelegate.enterpriseProductBusinessDelegate.updateProduct(enterpriseProduct);
		
		LOGGER.info(MhmConstants.END+"updateEnterpriseProductCompletedStatus");
	}
	
	/**
	 * method to check validation period overlapping
	 * @param codeTarifId
	 * @param newStartDateString
	 * @param newEndDateString
	 * @param status
	 * @return
	 * @throws ParseException
	 * @throws MhmCheckedTechnicalException
	 */
	public boolean validateDateAddition(long codeTarifId, String newStartDateString, String newEndDateString, String status) throws ParseException, MhmCheckedTechnicalException 
	{
		boolean errorFlag = false;
		Date newStartDate = dateService.convertDateFromString(newStartDateString);
		Date newEndDate = dateService.convertDateFromString(newEndDateString);
		List<EmployeeProductPeriod> employeeProductPeriods = employeeProductPeriodService.findByEmployeeProductCodesTarifId_Status(codeTarifId, status);
		for(EmployeeProductPeriod employeeProductPeriod : employeeProductPeriods)
		{
			if((newStartDate.compareTo(employeeProductPeriod.getStartDate()) >= 0) && (newStartDate.compareTo(employeeProductPeriod.getEndDate()) <=0) ||
					(newEndDate.compareTo(employeeProductPeriod.getStartDate()) >= 0) && (newEndDate.compareTo(employeeProductPeriod.getEndDate()) <=0))
			{
				errorFlag = true;
				break;
			}
		}
		return errorFlag;
	}
	
	/**
	 * method to check edit validation period overlapping
	 * @param codeTarifId
	 * @param newStartDateString
	 * @param newEndDateString
	 * @param status
	 * @param employeeProductPeriodId 
	 * @return
	 * @throws ParseException
	 * @throws MhmCheckedTechnicalException
	 */
	public boolean validateDateUpdation(long codeTarifId, String newStartDateString, String newEndDateString, String status, long employeeProductPeriodId) throws ParseException, MhmCheckedTechnicalException 
	{
		boolean errorFlag = false;
		Date newStartDate = dateService.convertDateFromString(newStartDateString);
		Date newEndDate = dateService.convertDateFromString(newEndDateString);
		List<EmployeeProductPeriod> employeeProductPeriods = employeeProductPeriodService.findByEmployeeProductCodesTarifId_Status(codeTarifId, status);
		for(EmployeeProductPeriod employeeProductPeriod : employeeProductPeriods)
		{
			if(employeeProductPeriod.getEmployeeProductPeriodId() == employeeProductPeriodId)
			{
				continue;
			}
			if((newStartDate.compareTo(employeeProductPeriod.getStartDate()) >= 0) && (newStartDate.compareTo(employeeProductPeriod.getEndDate()) <=0) ||
					(newEndDate.compareTo(employeeProductPeriod.getStartDate()) >= 0) && (newEndDate.compareTo(employeeProductPeriod.getEndDate()) <=0))
			{
				errorFlag = true;
				break;
			}
		}
		return errorFlag;
	}
	
	/**
	 * Used to get Employee Products by codeProduitTechnique
	 * @param codeProduitTechnique
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public List<EmployeeProduct> findByCodeProduitTechnique(String codeProduitTechnique) throws MhmCheckedTechnicalException{
		return employeeProductService.findByCodeProduitTechnique(codeProduitTechnique);
	}

	/**
	 * 
	 * Enable to set position tranche age to choose order
	 * 
	 * @param zoneId
	 * @param employeeProductId
	 * @param trancheAge
	 * @param currentEmployeeProductCodesTarif
	 * @return
	 * @throws MhmCheckedTechnicalException
	 */
	public int getPositionTrancheAge(long zoneId, String employeeProductId, String trancheAge, EmployeeProductCodesTarif currentEmployeeProductCodesTarif) throws MhmCheckedTechnicalException{
		
		List<EmployeeProductCodesTarif> employeeProductCodesTarifs = employeeProductCodesTarifService.findByZoneId_EmployeeProductId(zoneId, employeeProductId);
		
		int position = 0;
		
		// renvoie 0 si le produit n utilise pas de tranche d age. 
		if(!AniConstants.NO_TRANCHE_AGE.equals(trancheAge)){
			// premiere position initialise a 1
			position = 1;
		} else {
			return position;
		}
		
		// Test sur les autres codes tarifs pour un meme produit et une meme zone
		for(EmployeeProductCodesTarif employeeProductCodesTarif : employeeProductCodesTarifs){
			// si le code produit existe deja, on ne modifie pas sa position
			if(currentEmployeeProductCodesTarif!=null && currentEmployeeProductCodesTarif.getEmployeeproductcodestarifId()==employeeProductCodesTarif.getEmployeeproductcodestarifId()){
				position = currentEmployeeProductCodesTarif.getPositionTrancheAge();
				break;
			} else if(employeeProductCodesTarif.getPositionTrancheAge()!=0 && !AniConstants.NO_TRANCHE_AGE.equals(employeeProductCodesTarif.getTrancheAge())){
				// si pour une meme zone et un meme produit salarie, un code tarif existe, on incremente la position. 
				position = employeeProductCodesTarif.getPositionTrancheAge() + 1;
			}
		}
		
		return position;
	}
	
	/**
	 * 
	 * get Tranche Age distinct of one offer
	 * 
	 * @param employeeProductId
	 * @return
	 */
	public List<String> getTrancheAgeByOfferId(String offerId, boolean setNoTrancheAge) {
		
		
		List<String> tranchesAgeOffer = new ArrayList<String>();
		
		try {
				
			List<EnterpriseProduct> enterpriseProducts = enterPriseProductService.getByOfferId(offerId);
			
			for(EnterpriseProduct enterpriseProduct : enterpriseProducts){
				List<EmployeeProduct> employeeProducts = employeeProductService.findByenterpriseProductId(enterpriseProduct.getEnterpriseProductId());
				for(EmployeeProduct employeeProduct : employeeProducts){
					if(employeeProduct.getEmployeeProductType().equals(AniConstants.BASE)){
						List<String> tranchesAgeProduct = new ArrayList<String>();
						tranchesAgeProduct = getTrancheAgeByEmployeeProduct(employeeProduct, setNoTrancheAge);
						for(String trancheAgeProduct : tranchesAgeProduct){
							if(!tranchesAgeOffer.contains(trancheAgeProduct)){
								tranchesAgeOffer.add(trancheAgeProduct);
							}
						}
					}			
				}
			}
		} catch (MhmCheckedTechnicalException e) {
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("employeeProductCodesTarifService.getTrancheAgeByOfferId :: offerId :: " + offerId);
			}
			e.printStackTrace();
		}
		
		return tranchesAgeOffer;
		
	}
	
	
	/**
	 * 
	 * get Tranche Age distinct of one employee product
	 * 
	 * 
	 * @param employeeProduct
	 * @param setNoTrancheAge
	 * @param tranchesAge
	 * @return 
	 * @throws MhmCheckedTechnicalException
	 */
	public List<String> getTrancheAgeByEmployeeProduct(EmployeeProduct employeeProduct, boolean setNoTrancheAge){
		
		List<String> tranchesAge = new ArrayList<String>();
		
		if(employeeProduct.getEmployeeProductType().equals(AniConstants.BASE)){
			try {
				
				List<EmployeeProductCodesTarif> employeeProductCodesTarifs = employeeProductCodesTarifService
						.findByEmployeeProductId(employeeProduct.getEmployeeProductId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, OrderByComparatorFactoryUtil.create("EmployeeProductCodesTarif", "positionTrancheAge", true));
				
				for(EmployeeProductCodesTarif employeeProductCodesTarif : employeeProductCodesTarifs ){
					String currentTrancheAge = employeeProductCodesTarif.getTrancheAge();
					if(!tranchesAge.contains(currentTrancheAge)){
						if(!AniConstants.NO_TRANCHE_AGE.equals(employeeProductCodesTarif.getTrancheAge()) || (setNoTrancheAge && AniConstants.NO_TRANCHE_AGE.equals(employeeProductCodesTarif.getTrancheAge()))){
							tranchesAge.add(employeeProductCodesTarif.getTrancheAge());			
						}
					}
				}
				
			} catch (MhmCheckedTechnicalException e) {
				if(LOGGER.isDebugEnabled()){
					LOGGER.debug("EmployeeProductBusinessDelegate.getTrancheAgeByEmployeeProductIdStatus :: employeeProductId :: " + employeeProduct.getEmployeeProductId());
				}
				e.printStackTrace();
			}
		} else {
			tranchesAge.add(AniConstants.NO_TRANCHE_AGE);
		}
		
		return tranchesAge;	
	}
	

	/**
	 * 
	 * get Tranche Age distinct of one employee product
	 * 
	 * @param employeeProductId
	 * @param status
	 * @param setNoTrancheAge
	 * @return
	 */
	public List<String> getTrancheAgeByEmployeeProductIdStatus(String employeeProductId, String status, boolean setNoTrancheAge){
		
		EmployeeProduct employeeProduct = null;
		try {
			employeeProduct = employeeProductService.findByemployeeProductId_status(employeeProductId, status);
		} catch (MhmCheckedTechnicalException e) {
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("employeeProductService.findByemployeeProductId_status :: employeeProductId, staus :: " + employeeProductId+", "+status);
			}
			e.printStackTrace();
		}
		
		return getTrancheAgeByEmployeeProduct(employeeProduct, setNoTrancheAge);
	}


	/**
	 * @param employeeProductId
	 * @param status
	 * @return
	 */
	public String getFirstTrancheIfExist(String employeeProductId, String status) {
			
		List<String> tranches = getTrancheAgeByEmployeeProductIdStatus(employeeProductId, status, true);
			
		if(!tranches.isEmpty()){
			return tranches.get(0);
		}
		
		return AniConstants.NO_TRANCHE_AGE;
	}
	
	
	/**
	 * 
	 * get ALL label import in StructureDetail Table by Script for Age Structure
	 * 
	 * @return
	 */
	public Map<Long,String> getStructureDetailsAgeLabel(){
		
		Map<Long,String> structureDetailsAgeLabel = new HashMap<Long,String>();

		try {
			
			List<StructureDetails> structureDetails = StructureDetailsLocalServiceUtil.getByStructureId(AniConstants.STRUCTURE_AGE_ID);
			
			for(StructureDetails details : structureDetails){
				
				String structureLabel = details.getStructureLabel();
				
				try{
					Integer.parseInt(structureLabel);
					structureLabel = structureLabel+" ans"; 
				} catch (NumberFormatException e) {}
				
				structureDetailsAgeLabel.put(details.getStructureDetailsId(), structureLabel);
			}
			
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return structureDetailsAgeLabel;
	}
	
	/**
	 * 
	 * get ALL id import in StructureDetail Table by Script for Age Structure
	 * 
	 * 
	 * @return
	 */
	public List<Long> getStructureDetailsAgeId(){
		
		List<Long> structureDetailsAgeId = new ArrayList<Long>();

		try {
			
			List<StructureDetails> structureDetails = StructureDetailsLocalServiceUtil.getByStructureId(AniConstants.STRUCTURE_AGE_ID);
			
			for(StructureDetails details : structureDetails){
				structureDetailsAgeId.add(details.getStructureDetailsId());
			}
			
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return structureDetailsAgeId;
	}
	
	
}
