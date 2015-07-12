package fr.mhm.portal.portlet.animanagement;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.log4j.Logger;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.Controller;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import fr.mhm.portal.businessdelegate.animanagement.EmployeeProductBusinessDelegate;
import fr.mhm.portal.businessdelegate.animanagement.OfferManagementBusinessDelegate;
import fr.mhm.portal.constants.AniConstants;
import fr.mhm.portal.constants.MhmConstants;
import fr.mhm.portal.exception.MhmCheckedTechnicalException;
import fr.mhm.portal.exception.MhmUncheckedTechnicalException;
import fr.mhm.portal.helper.EmployeeProductHelper;
import fr.mhm.portal.helper.EnterpriseProductHelper;
import fr.mhm.portal.service.model.EmployeeProduct;
import fr.mhm.portal.service.model.EmployeeProductCodesTarif;
import fr.mhm.portal.service.model.EmployeeProductPeriod;
import fr.mhm.portal.service.model.EmployeeProductPrice;
import fr.mhm.portal.service.model.EnterpriseProduct;
import fr.mhm.portal.service.model.Offer;
import fr.mhm.portal.service.service.StructureDetailsLocalServiceUtil;
import fr.mhm.portal.vo.EmployeeProductVO;
import fr.mhm.portal.vo.EnterpriseEmployeeVO;
import fr.mhm.portal.vo.ani.EmpProdVO;
/**
 * EmployeeProduct controller class
 * @author h.h.kumar
 *
 */
public class EmployeeProductController implements Controller, ResourceAwareController {
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeProductController.class);
	private EmployeeProductBusinessDelegate employeeProductBusinessDelegate;
	private EmployeeProductHelper employeeProductHelper;
	private OfferManagementBusinessDelegate offerManagementBusinessDelegate;
	private EnterpriseProductHelper enterpriseProductHelper;
	
	/**
	 * @param employeeProductHelper the employeeProductHelper to set
	 */
	public void setEmployeeProductHelper(EmployeeProductHelper employeeProductHelper) {
		this.employeeProductHelper = employeeProductHelper;
	}

	public void setEmployeeProductBusinessDelegate(
			EmployeeProductBusinessDelegate employeeProductBusinessDelegate) {
		this.employeeProductBusinessDelegate = employeeProductBusinessDelegate;
	}

	public void setOfferManagementBusinessDelegate(
			OfferManagementBusinessDelegate offerManagementBusinessDelegate) {
		this.offerManagementBusinessDelegate = offerManagementBusinessDelegate;
	}

	/**
	 * @param enterpriseProductHelper the enterpriseProductHelper to set
	 */
	public void setEnterpriseProductHelper(
			EnterpriseProductHelper enterpriseProductHelper) {
		this.enterpriseProductHelper = enterpriseProductHelper;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.Controller#handleRenderRequest(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public ModelAndView handleRenderRequest(RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception{
		
		LOGGER.info(MhmConstants.START+"handleRenderRequest");
		ModelAndView modelAndView = new ModelAndView();
		
		String formAction = ParamUtil.getString(renderRequest, AniConstants.FORM_ACTION);
		String offerId = ParamUtil.getString(renderRequest, AniConstants.OFFER_ID);
		String offerStatus = ParamUtil.getString(renderRequest, "offerStatus");
		String productStatus = ParamUtil.getString(renderRequest, "productStatus");
		EmployeeProductVO employeeProductVO = new EmployeeProductVO();
		
		if(formAction.equals(AniConstants.ADD)){
			String regime = ParamUtil.getString(renderRequest, AniConstants.REGIME);
			String enterpriseProductId = ParamUtil.getString(renderRequest, AniConstants.ENTERPRISE_PRODUCT_ID);
			LOGGER.info("Adding Employee Product with Enterprise Product Id : " + enterpriseProductId);
			employeeProductVO = employeeProductBusinessDelegate.populateEmployeeProductVOAdd(enterpriseProductId, regime, offerStatus, productStatus);
		}else if(formAction.equals(AniConstants.EDIT)){
			String employeeProductId = ParamUtil.getString(renderRequest, AniConstants.EMPLOYEE_PRODUCT_ID);
			String status = ParamUtil.getString(renderRequest, AniConstants.STATUS);
			String trancheAgeSelected = ParamUtil.getString(renderRequest, "trancheselected", employeeProductBusinessDelegate.getFirstTrancheIfExist(employeeProductId, status));
			String zoneSelected = ParamUtil.getString(renderRequest, "zoneselected", AniConstants.ZONE1);
			LOGGER.info("Updating Employee Product Id : " + employeeProductId);
			long employeeProductPeriodId = ParamUtil.getLong(renderRequest, AniConstants.EMPLOYEE_PRODUCT_PERIOD_ID);
			employeeProductVO = employeeProductBusinessDelegate.populateEmployeeProductVOEdit(employeeProductId, offerStatus, productStatus, status);
			employeeProductVO.setEmployeeProductPeriodId(employeeProductPeriodId);
			// populate CodesTarif VO
			modelAndView.addObject("codesTarifVO", employeeProductBusinessDelegate.populateCodesTerifVO(employeeProductVO.getRegime(), employeeProductId,employeeProductVO.getStructureId(), employeeProductVO.isZone1(), employeeProductVO.isZone2(), employeeProductVO.isZone3(), status, trancheAgeSelected));
			// Populate Warranty Details VO
			modelAndView.addObject("detailsVOs", enterpriseProductHelper.getWarrantyDetails(employeeProductId, status));
			modelAndView.addObject("exampleData", enterpriseProductHelper.getExamples(status));
			// trancheAge et zone
			modelAndView.addObject("trancheAgeSelected", trancheAgeSelected);
			modelAndView.addObject("zoneSelected", zoneSelected);
		}
		
		Offer offer = offerManagementBusinessDelegate.getOffer(offerId, offerStatus);
		List<EnterpriseProduct> listAllenterpriseProducts = offerManagementBusinessDelegate.getEnterpriseProductByOfferId(offer.getOfferId());
		listAllenterpriseProducts = offerManagementBusinessDelegate.getEnterpriseProductByStatus(listAllenterpriseProducts);
		List<EnterpriseEmployeeVO> listAllProducts = new ArrayList<EnterpriseEmployeeVO>();
		listAllProducts = offerManagementBusinessDelegate.getListAllProducts(listAllenterpriseProducts);
		
		if(employeeProductVO.getStructureId() == AniConstants.STRUCTURE_ADULTE_ID){
			modelAndView.addObject("structureLabelOne",AniConstants.ADULTE);
			modelAndView.addObject("structureLabelTwo",AniConstants.ENFANT);
		} if(employeeProductVO.getStructureId() == AniConstants.STRUCTURE_AGE_ID){
			modelAndView.addObject("agesStrutctureLabel", employeeProductBusinessDelegate.getStructureDetailsAgeLabel());			
		} else {
			modelAndView.addObject("structureLabelOne",AniConstants.SALARIE);
			modelAndView.addObject("structureLabelTwo",AniConstants.CONJOINT);
			modelAndView.addObject("structureLabelThree",AniConstants.ENFANT);
		}
		
		modelAndView.addObject("listAllProducts",listAllProducts);
		modelAndView.addObject("offer",offer);		
		modelAndView.addObject("employeeProductVO", employeeProductVO);
		modelAndView.setView("container/container");
		modelAndView.addObject("aniView","employeeproduct");
		modelAndView.addObject("selectedId",employeeProductVO.getEmployeeProductId());
		modelAndView.addObject("globalScopeGroupId", employeeProductHelper.getGlobalContentGroupId());

		LOGGER.info(MhmConstants.END+"handleRenderRequest");
		
		return modelAndView;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.Controller#handleActionRequest(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	public void handleActionRequest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception
	{	
		LOGGER.info(MhmConstants.START+"handleActionRequest");
		
		UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		
		String formAction = ParamUtil.getString(uploadRequest, AniConstants.FORM_ACTION);
		if(formAction.equals(AniConstants.ADD)){
			addEmployeeProduct(uploadRequest, actionResponse);
		}else if(formAction.equals(AniConstants.EDIT)){
			editEmployeeProduct(uploadRequest, actionRequest, actionResponse);
		}
		actionResponse.setRenderParameter("offerId", ParamUtil.getString(uploadRequest, "offerId"));
		LOGGER.info(MhmConstants.END+"handleActionRequest");
	}

	/**
	 * @param resourceRequest
	 * @param resourceResponse
	 * @return
	 * @throws Exception
	 */
	public ModelAndView handleResourceRequest(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws Exception{
		
		LOGGER.info(MhmConstants.START+"handleResourceRequest");
		String resourceId = resourceRequest.getResourceID();
		PrintWriter writer = resourceResponse.getWriter();
		JSONObject json = JSONFactoryUtil.createJSONObject();
		
		String employeeProductId = ParamUtil.getString(resourceRequest, AniConstants.EMPLOYEE_PRODUCT_ID);
		String status = ParamUtil.getString(resourceRequest, AniConstants.STATUS);
		String offerStatus = ParamUtil.getString(resourceRequest, "offerStatus");
		String productStatus = ParamUtil.getString(resourceRequest, "productStatus");
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("EMPLOYEE PRODUCT ID :: " + employeeProductId);
			LOGGER.debug("STATUS :: " + status);
		}
		EmployeeProduct employeeProduct = null;
		try{
			employeeProduct = employeeProductBusinessDelegate.findByemployeeProductId_status(employeeProductId, status);
		} catch(MhmCheckedTechnicalException e){}
		
		if(resourceId.equals(AniConstants.ADD_DATE_RANGE))
		{
			String dateFlag = ParamUtil.getString(resourceRequest, AniConstants.DATE_FLAG);
			long codeTarifId = ParamUtil.getLong(resourceRequest, "codeTarifId");
			String startDate = ParamUtil.getString(resourceRequest, AniConstants.START_DATE);
			Date updatedStartDate = employeeProductBusinessDelegate.convertDateFromString(startDate);
			String endDate = ParamUtil.getString(resourceRequest, AniConstants.END_DATE);
			String taxe = ParamUtil.getString(resourceRequest, AniConstants.TAXE);
			String regime = ParamUtil.getString(resourceRequest, AniConstants.REGIME);
			boolean zone1 = ParamUtil.getBoolean(resourceRequest, "zone1");
			boolean zone2 = ParamUtil.getBoolean(resourceRequest, "zone2");
			boolean zone3 = ParamUtil.getBoolean(resourceRequest, "zone3");
			long structureId = ParamUtil.getLong(resourceRequest, "structure");
			String trancheAge = ParamUtil.getString(resourceRequest, "trancheAge", AniConstants.NO_TRANCHE_AGE);

			if(dateFlag.equals(AniConstants.ADD))
			{
				boolean errorFlag = false;
				errorFlag = employeeProductBusinessDelegate.validateDateAddition(codeTarifId, startDate, endDate, status);
				if(errorFlag)
				{
					json.put(AniConstants.OUTPUT, "Failure");
				}
				else
				{
					if(LOGGER.isDebugEnabled()){
						LOGGER.info("Adding Date Range");
					}
					EmployeeProductPeriod employeeProductPeriod = employeeProductBusinessDelegate.addEmployeeProductPeriod(codeTarifId, startDate, endDate,taxe, status);
					// update employee product modified date and completed status
					String completedStatus = employeeProductBusinessDelegate.getCompletedStatus(employeeProductId,null, regime, zone1, zone2, zone3, status,structureId,employeeProductBusinessDelegate.getTrancheAgeByEmployeeProduct(employeeProduct, true));
					employeeProductBusinessDelegate.updateEmployeeProductCompletedStatus(employeeProductId, completedStatus, status);
					// write response to json object
					json.put(AniConstants.OUTPUT, AniConstants.SUCCESS);
				    json.put(AniConstants.EMPLOYEE_PRODUCT_PERIOD_ID, employeeProductPeriod.getEmployeeProductPeriodId());
				}
			    writer.write(json.toString());
			}
			else if(dateFlag.equals(AniConstants.EDIT))
			{
				if(LOGGER.isDebugEnabled()){
					LOGGER.debug("Updating Date Range");
				}
				long employeeProductPeriodId = ParamUtil.getLong(resourceRequest, AniConstants.EMPLOYEE_PRODUCT_PERIOD_ID);
				if(LOGGER.isDebugEnabled()){
					LOGGER.debug("Id: " + employeeProductPeriodId + " start: " + startDate + "  end: " + endDate);
				}
				
				boolean errorFlag = false;
				errorFlag = employeeProductBusinessDelegate.validateDateUpdation(codeTarifId, startDate, endDate, status, employeeProductPeriodId);
				if(errorFlag)
				{
					json.put(AniConstants.OUTPUT, "Failure");
				}
				else
				{
					String cloneStatus = new String(status);
					boolean pageReload = false;
					String refreshURL = StringPool.BLANK;
					List<EmpProdVO> descendantVOs = new ArrayList<EmpProdVO>();
					boolean cloneFlag = false;
					Date currentDate = new Date();
					
					// checks for current validation period for cloning
					EmployeeProductPeriod employeeProductPeriod = employeeProductBusinessDelegate.findByEmployeeProductPeriodId_Status(employeeProductPeriodId, status);
					if(Validator.isNotNull(employeeProductPeriod))
					{
						Date oldStartDate = employeeProductPeriod.getStartDate();
						Date oldEndDate = employeeProductPeriod.getEndDate();
						if(oldStartDate.compareTo(currentDate) <=0 &&  oldEndDate.compareTo(currentDate) >= 0)
						{
							if(updatedStartDate.compareTo(currentDate) > 0)
							{
								cloneFlag = true;
							}
						}
						if(!taxe.equalsIgnoreCase(employeeProductPeriod.getTaxe())){
							cloneFlag = true;
						}
					}
					if(cloneFlag)
					{
						// check cloning conditions
						if(status.equals(AniConstants.ONLINE))
						{
							pageReload = true;
							descendantVOs = employeeProductBusinessDelegate.onlineCloneConditionMethod(employeeProductId, status);
							status = AniConstants.DRAFT;
						}
						else if(status.equals(AniConstants.RETIRE))
						{
							pageReload = true;
							employeeProductBusinessDelegate.retireCloneConditionMethod(employeeProductId, status);
							status = AniConstants.DRAFT;
						}
					}
					// update employee product period
					employeeProductBusinessDelegate.updateEmployeeProductPeriod(employeeProductPeriodId, startDate, endDate,taxe, status);
					// update employee product modified date 
					employeeProductBusinessDelegate.updateEmployeeProductModifiedDate(employeeProductId, status);
					// update employee product modified date and completed status
					String completedStatus = employeeProductBusinessDelegate.getCompletedStatus(employeeProductId,null, regime, zone1, zone2, zone3, status,structureId,employeeProductBusinessDelegate.getTrancheAgeByEmployeeProduct(employeeProduct, true));
					employeeProductBusinessDelegate.updateEmployeeProductCompletedStatus(employeeProductId, completedStatus, status);
					
					// perform cloning
					Map<String, Boolean> changeStatusMap = new HashMap<String, Boolean>();
					if(cloneStatus.equals(AniConstants.ONLINE) && cloneFlag)
					{
						for(EmpProdVO employeeProductDescendantVO : descendantVOs)
						{
							employeeProductBusinessDelegate.cloneEmployeeProductAndDescendants(employeeProductDescendantVO);
						}
						// Check Online and make impact upwards if last draft employee product. 
						offerManagementBusinessDelegate.draftEmployeeProductImpactUpward(employeeProduct,changeStatusMap);
					}
					if(cloneStatus.equals(AniConstants.RETIRE))
					{
						// Check retire and make impact upwards if last draft employee product. 
						offerManagementBusinessDelegate.draftEmployeeProductImpactUpwardForRetire(employeeProduct, changeStatusMap);
					}
					
					if(changeStatusMap.get("draftOffer")!=null && changeStatusMap.get("draftOffer")){
						offerStatus = AniConstants.DRAFT;
					}
					
					if(changeStatusMap.get("draftProduct")!=null && changeStatusMap.get("draftProduct")){
						productStatus = AniConstants.DRAFT;
					} 
					
					if(pageReload){
						refreshURL = employeeProductBusinessDelegate.createRenderRefreshURL(resourceRequest, resourceResponse,offerStatus,productStatus);
					}
					
					if(LOGGER.isDebugEnabled()){
						LOGGER.debug("refresh url :: " + refreshURL);
					}
					// write response to json object
					json.put(AniConstants.OUTPUT, AniConstants.SUCCESS);
					json.put("pageReload", pageReload);
					json.put("refreshURL", refreshURL);
				}
				writer.write(json.toString());
			}
		}
		else if(resourceId.equals(AniConstants.DELETE_DATE_RANGE))
		{
			long employeeProductPeriodId = ParamUtil.getLong(resourceRequest, AniConstants.EMPLOYEE_PRODUCT_PERIOD_ID);
			String regime = ParamUtil.getString(resourceRequest, AniConstants.REGIME);
			boolean zone1 = ParamUtil.getBoolean(resourceRequest, "zone1");
			boolean zone2 = ParamUtil.getBoolean(resourceRequest, "zone2");
			boolean zone3 = ParamUtil.getBoolean(resourceRequest, "zone3");
			String cloneStatus = new String(status);
			boolean pageReload = false;
			String refreshURL = StringPool.BLANK;
			List<EmpProdVO> descendantVOs = new ArrayList<EmpProdVO>();
			boolean cloneFlag = false;
			Date currentDate = new Date();
			String trancheAge = ParamUtil.getString(resourceRequest, "trancheAge", AniConstants.NO_TRANCHE_AGE);

			// checks for current validation period for cloning
			EmployeeProductPeriod employeeProductPeriod = employeeProductBusinessDelegate.findByEmployeeProductPeriodId_Status(employeeProductPeriodId, status);
			if(Validator.isNotNull(employeeProductPeriod))
			{
				Date startDate = employeeProductPeriod.getStartDate();
				Date endDate = employeeProductPeriod.getEndDate();
				if(startDate.compareTo(currentDate) <=0 &&  endDate.compareTo(currentDate) >= 0)
				{
					cloneFlag = true;
				}
			}
			if(cloneFlag)
			{
				// check cloning conditions
				if(status.equals(AniConstants.ONLINE))
				{
					pageReload = true;
					descendantVOs = employeeProductBusinessDelegate.onlineCloneConditionMethod(employeeProductId, status);
					status = AniConstants.DRAFT;
				}
				else if(status.equals(AniConstants.RETIRE))
				{
					pageReload = true;
					employeeProductBusinessDelegate.retireCloneConditionMethod(employeeProductId, status);
					status = AniConstants.DRAFT;
				}
			}
			
			// delete validation period 
			employeeProductBusinessDelegate.deleteEmployeeProductPeriod(employeeProductPeriodId);
			
			// delete price data
			List<EmployeeProductPrice> employeeProductPrices = employeeProductBusinessDelegate.getEmployeeProductPriceListByEmployeeProductPeriodId(employeeProductPeriodId);
			for(EmployeeProductPrice employeeProductPrice : employeeProductPrices){
				employeeProductBusinessDelegate.deleteEmployeeProductPrice(employeeProductPrice.getEmployeeProductPriceId());
			}
			long structureId = ParamUtil.getLong(resourceRequest, "structureId");
			// update employee product modified date and completed status
			String completedStatus = employeeProductBusinessDelegate.getCompletedStatus(employeeProductId,null, regime, zone1, zone2, zone3, status,structureId,employeeProductBusinessDelegate.getTrancheAgeByEmployeeProduct(employeeProduct, true));
			employeeProductBusinessDelegate.updateEmployeeProductCompletedStatus(employeeProductId, completedStatus, status);
			// perform cloning
			Map<String, Boolean> changeStatusMap = new HashMap<String, Boolean>();
			if(cloneStatus.equals(AniConstants.ONLINE) && cloneFlag)
			{
				for(EmpProdVO employeeProductDescendantVO : descendantVOs)
				{
					employeeProductBusinessDelegate.cloneEmployeeProductAndDescendants(employeeProductDescendantVO);
				}
				// Check Online and make impact upwards if last draft employee product. 
				offerManagementBusinessDelegate.draftEmployeeProductImpactUpward(employeeProduct,changeStatusMap);
			}
			if(cloneStatus.equals(AniConstants.RETIRE))
			{
				// Check retire and make impact upwards if last draft employee product. 
				offerManagementBusinessDelegate.draftEmployeeProductImpactUpwardForRetire(employeeProduct, changeStatusMap);
			}
			
			if(changeStatusMap.get("draftOffer")!=null && changeStatusMap.get("draftOffer")){
				offerStatus = AniConstants.DRAFT;
			}
			
			if(changeStatusMap.get("draftProduct")!=null && changeStatusMap.get("draftProduct")){
				productStatus = AniConstants.DRAFT;
			} 
			
			if(pageReload){
				refreshURL = employeeProductBusinessDelegate.createRenderRefreshURL(resourceRequest, resourceResponse,offerStatus,productStatus);
			}
			// write response to json object
		    json.put(AniConstants.OUTPUT, AniConstants.SUCCESS);
		    json.put("pageReload", pageReload);
		    json.put("refreshURL", refreshURL);
		    writer.write(json.toString());
		}
		else if(resourceId.equals("populatePriceDetails"))
		{
			long zone1EmployeeProductPeriodId = ParamUtil.getLong(resourceRequest, "zone1EmployeeProductPeriodId");
			long zone2EmployeeProductPeriodId = ParamUtil.getLong(resourceRequest, "zone2EmployeeProductPeriodId");
			long zone3EmployeeProductPeriodId = ParamUtil.getLong(resourceRequest, "zone3EmployeeProductPeriodId");
			long zone4EmployeeProductPeriodId = ParamUtil.getLong(resourceRequest, "zone4EmployeeProductPeriodId");
			String regime = ParamUtil.getString(resourceRequest, AniConstants.REGIME);
			boolean zone1 = ParamUtil.getBoolean(resourceRequest, "zone1");
			boolean zone2 = ParamUtil.getBoolean(resourceRequest, "zone2");
			boolean zone3 = ParamUtil.getBoolean(resourceRequest, "zone3");
			long structureId = ParamUtil.getLong(resourceRequest, "structure");
			
			String jsonString = employeeProductBusinessDelegate.populatePriceDetails(regime, zone1, zone2, zone3, zone1EmployeeProductPeriodId, zone2EmployeeProductPeriodId, zone3EmployeeProductPeriodId, zone4EmployeeProductPeriodId, status,structureId);
			LOGGER.info("populatePriceDetails resource called");
			// write response to json object
		    writer.write(jsonString);
		}
		else if(resourceId.equals("addUpdateCodesTarif"))
		{
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Add/Update Employee Product CodesTarif");
			}
			long zoneId = ParamUtil.getLong(resourceRequest, "zoneId");
			String priceType = ParamUtil.getString(resourceRequest, "priceTypeVal");
			String codesTarif = ParamUtil.getString(resourceRequest, "codesTarifVal");
			long situationId = ParamUtil.getLong(resourceRequest, "situationId");
			long structureId = ParamUtil.getLong(resourceRequest, "structureId");
			String trancheAge = ParamUtil.getString(resourceRequest, "trancheAge", AniConstants.NO_TRANCHE_AGE);
			String cloneStatus = new String(status);
			boolean formFinalStateChanged = ParamUtil.getBoolean(resourceRequest, "formFinalStateChanged");
			boolean pageReload = false;
			String refreshURL = StringPool.BLANK;
			List<EmpProdVO> descendantVOs = new ArrayList<EmpProdVO>();

			// check cloning conditions
			if(status.equals(AniConstants.ONLINE))
			{
				// check if form field changed or not
				if(formFinalStateChanged)
				{
					pageReload = true;
					descendantVOs = employeeProductBusinessDelegate.onlineCloneConditionMethod(employeeProductId, status);
					status = AniConstants.DRAFT;
				}
			}
			else if(status.equals(AniConstants.RETIRE))
			{
				pageReload = true;
				employeeProductBusinessDelegate.retireCloneConditionMethod(employeeProductId, status);
				status = AniConstants.DRAFT;
			}
			
			//check price type changes, if needed wipe prices
			EmployeeProductCodesTarif employeeProductCodesTarif = null;
			EmployeeProductCodesTarif updatedEmployeeProductCodesTarif = null;
			String oldPriceType = StringPool.BLANK;
			// update code tarifs info
			try{
				employeeProductCodesTarif = employeeProductBusinessDelegate.findByZoneId_EmployeeProductId_Status(zoneId, employeeProductId, status);
				oldPriceType = employeeProductCodesTarif.getPriceType();
			}catch(MhmCheckedTechnicalException e){}
			
			// Add or Update EmployeeProductCodesTarif
			updatedEmployeeProductCodesTarif = employeeProductBusinessDelegate.addOrUpdateCodesTarif(employeeProductId, zoneId, priceType, codesTarif, situationId, structureId, status, trancheAge);
			// check price type, if changed, wipe all the prices for this code tarif
			if(Validator.isNotNull(employeeProductCodesTarif) && cloneStatus.equals(AniConstants.ONLINE))
			{
				if(!updatedEmployeeProductCodesTarif.getPriceType().equals(oldPriceType))
				{
					employeeProductBusinessDelegate.deletePriceData(employeeProductCodesTarif.getEmployeeproductcodestarifId(), status);
				}
			}
			// update employee product modified date and completed status
			employeeProductBusinessDelegate.updateEmployeeProductCompletedStatus(employeeProductId, AniConstants.NON, status);
			
			// perform cloning
			Map<String, Boolean> changeStatusMap = new HashMap<String, Boolean>();
			if(cloneStatus.equals(AniConstants.ONLINE) && formFinalStateChanged)
			{
				for(EmpProdVO employeeProductDescendantVO : descendantVOs)
				{
					employeeProductBusinessDelegate.cloneEmployeeProductAndDescendants(employeeProductDescendantVO);
				}
				// Check Online and make impact upwards if last draft employee product. 
				offerManagementBusinessDelegate.draftEmployeeProductImpactUpward(employeeProduct,changeStatusMap);
			}
			if(cloneStatus.equals(AniConstants.RETIRE))
			{
				// Check retire and make impact upwards if last draft employee product. 
				offerManagementBusinessDelegate.draftEmployeeProductImpactUpwardForRetire(employeeProduct, changeStatusMap);
			}
			
			if(changeStatusMap.get("draftOffer")!=null && changeStatusMap.get("draftOffer")){
				offerStatus = AniConstants.DRAFT;
			}
			
			if(changeStatusMap.get("draftProduct")!=null && changeStatusMap.get("draftProduct")){
				productStatus = AniConstants.DRAFT;
			} 
			
			if(pageReload){
				refreshURL = employeeProductBusinessDelegate.createRenderRefreshURL(resourceRequest, resourceResponse,offerStatus,productStatus);
			}
			// write response to json object
			json.put(AniConstants.OUTPUT, AniConstants.SUCCESS);
		    json.put(AniConstants.EMPLOYEE_PRODUCT_CODESTARIF_ID, updatedEmployeeProductCodesTarif.getEmployeeproductcodestarifId());
		    json.put("pageReload", pageReload);
		    json.put("refreshURL", refreshURL);
		    writer.write(json.toString());
		}
		
		LOGGER.info(MhmConstants.END+"handleResourceRequest");
		writer.close();
		return null;
	}
	
	/**
	 * method to add new Employee Product
	 * @param uploadRequest
	 * @param actionResponse
	 * @throws MhmCheckedTechnicalException 
	 */
	private void addEmployeeProduct(UploadRequest uploadRequest,
			ActionResponse actionResponse) throws MhmCheckedTechnicalException {
		
		LOGGER.info(MhmConstants.START+"addEmployeeProduct");
		ThemeDisplay themeDisplay = (ThemeDisplay) uploadRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String offerStatus = ParamUtil.getString(uploadRequest, "offerStatus");
		String productStatus = ParamUtil.getString(uploadRequest, "productStatus");
		String name = ParamUtil.getString(uploadRequest, AniConstants.EPNAME);
		String codeProduitTechnique = ParamUtil.getString(uploadRequest, AniConstants.CODE_PRODUIT_TECHNIQUE);
		String enterpriseProductId = ParamUtil.getString(uploadRequest, AniConstants.ENTERPRISE_PRODUCT_ID);
		String regime = ParamUtil.getString(uploadRequest, AniConstants.REGIME);
		String employeeProductType = employeeProductBusinessDelegate.getEmployeeProductType(enterpriseProductId, regime, productStatus);
		long contributor = themeDisplay.getUserId();
		
		EmployeeProduct employeeProduct = employeeProductBusinessDelegate.addEmployeeProduct(name, codeProduitTechnique, enterpriseProductId, employeeProductType, regime, new Date(), new Date(), contributor, AniConstants.DRAFT, AniConstants.NON);
		actionResponse.setRenderParameter(AniConstants.EMPLOYEE_PRODUCT_ID, String.valueOf(employeeProduct.getEmployeeProductId()));
		actionResponse.setRenderParameter(AniConstants.FORM_ACTION, AniConstants.EDIT);
		actionResponse.setRenderParameter("offerStatus", offerStatus);
		actionResponse.setRenderParameter("productStatus", productStatus);
		actionResponse.setRenderParameter("status", AniConstants.DRAFT);
		LOGGER.info(MhmConstants.END+"addEmployeeProduct");
		
		// Update Enterprise Product Status
		employeeProductBusinessDelegate.updateEnterpriseProductCompletedStatus(enterpriseProductId, productStatus);
		
	}
	
	/**
	 * method to update Employee Product
	 * @param uploadRequest 
	 * @param actionRequest
	 * @param actionResponse
	 * @throws MhmCheckedTechnicalException 
	 * @throws MhmUncheckedTechnicalException 
	 */
	private void editEmployeeProduct(UploadRequest uploadRequest, ActionRequest actionRequest,
			ActionResponse actionResponse) throws MhmCheckedTechnicalException, MhmUncheckedTechnicalException {
		
		LOGGER.info(MhmConstants.START+"editEmployeeProduct");
		
		String employeeProductId = ParamUtil.getString(uploadRequest, AniConstants.EMPLOYEE_PRODUCT_ID);
		String offerStatus = ParamUtil.getString(uploadRequest, "offerStatus");
		String productStatus = ParamUtil.getString(uploadRequest, "productStatus");
		String status = ParamUtil.getString(uploadRequest, AniConstants.STATUS);
		boolean formFinalStateChanged = ParamUtil.getBoolean(uploadRequest, "formFinalStateChanged");
		String cloneStatus = new String(status);
		List<EmpProdVO> descendantVOs = new ArrayList<EmpProdVO>();
		
		EmployeeProduct employeeProduct = employeeProductBusinessDelegate.findByemployeeProductId_status(employeeProductId, status);
		EnterpriseProduct enterpriseProduct = employeeProductBusinessDelegate.getByEnterpriseProductIdStatus(employeeProduct.getEnterpriseProductId(), productStatus);
		Offer offer = employeeProductBusinessDelegate.getOffer(enterpriseProduct.getOfferId(), offerStatus);
		
		/*Change done due to MHM-5466 improvement*/ 
		int position = ParamUtil.getInteger(uploadRequest, "position");
		
		
		String name = ParamUtil.getString(uploadRequest, AniConstants.EPNAME);
		String codeProduitTechnique = ParamUtil.getString(uploadRequest, AniConstants.CODE_PRODUIT_TECHNIQUE);
		String levelOfProtectionDocument = ParamUtil.getString(uploadRequest, "levelOfProtectionDocument");
		String productSummaryEditorText = ParamUtil.getString(uploadRequest, "productSummaryEditorText");
		String priceDetailseditorText = ParamUtil.getString(uploadRequest, "priceDetailseditorText");
		String includedEditorText = ParamUtil.getString(uploadRequest, "includedEditorText");
		String regime = ParamUtil.getString(uploadRequest, AniConstants.REGIME);
		boolean zone1 = ParamUtil.getBoolean(uploadRequest, "zone1");
		boolean zone2 = ParamUtil.getBoolean(uploadRequest, "zone2");
		boolean zone3 = ParamUtil.getBoolean(uploadRequest, "zone3");
		long situationId = ParamUtil.getLong(uploadRequest, "situationId");
		long structureId = ParamUtil.getLong(uploadRequest, "structureId");
		String deletedDocumentIds = ParamUtil.getString(uploadRequest, "deletedDocumentIds");
		String trancheAgeSelected = ParamUtil.getString(uploadRequest, "trancheAgeSelected", AniConstants.NO_TRANCHE_AGE);

		
		long zone1employeeProductPeriodId = ParamUtil.getLong(uploadRequest, "1dateRadio");
		String zonePriceType1 = ParamUtil.getString(uploadRequest, "zonePriceType1");
		String zone1_codesTarif = ParamUtil.getString(uploadRequest, "zone1_codesTarif");
		String zone1_aduEmp = ParamUtil.getString(uploadRequest, "zone1_aduEmp");
		String zone1_aduAdh = ParamUtil.getString(uploadRequest, "zone1_aduAdh");
		String zone1_enfEmp = ParamUtil.getString(uploadRequest, "zone1_enfEmp");
		String zone1_enfAdh = ParamUtil.getString(uploadRequest, "zone1_enfAdh");
		String zone1_conEmp = ParamUtil.getString(uploadRequest, "zone1_conEmp");
		String zone1_conAdh = ParamUtil.getString(uploadRequest, "zone1_conAdh");
		long zone2employeeProductPeriodId = ParamUtil.getLong(uploadRequest, "2dateRadio");
		String zonePriceType2 = ParamUtil.getString(uploadRequest, "zonePriceType2");
		String zone2_codesTarif = ParamUtil.getString(uploadRequest, "zone2_codesTarif");
		String zone2_aduEmp = ParamUtil.getString(uploadRequest, "zone2_aduEmp");
		String zone2_aduAdh = ParamUtil.getString(uploadRequest, "zone2_aduAdh");
		String zone2_enfEmp = ParamUtil.getString(uploadRequest, "zone2_enfEmp");
		String zone2_enfAdh = ParamUtil.getString(uploadRequest, "zone2_enfAdh");
		String zone2_conEmp = ParamUtil.getString(uploadRequest, "zone2_conEmp");
		String zone2_conAdh = ParamUtil.getString(uploadRequest, "zone2_conAdh");
		long zone3employeeProductPeriodId = ParamUtil.getLong(uploadRequest, "3dateRadio");
		String zonePriceType3 = ParamUtil.getString(uploadRequest, "zonePriceType3");
		String zone3_codesTarif = ParamUtil.getString(uploadRequest, "zone3_codesTarif");
		String zone3_aduEmp = ParamUtil.getString(uploadRequest, "zone3_aduEmp");
		String zone3_aduAdh = ParamUtil.getString(uploadRequest, "zone3_aduAdh");
		String zone3_enfEmp = ParamUtil.getString(uploadRequest, "zone3_enfEmp");
		String zone3_enfAdh = ParamUtil.getString(uploadRequest, "zone3_enfAdh");
		String zone3_conEmp = ParamUtil.getString(uploadRequest, "zone3_conEmp");
		String zone3_conAdh = ParamUtil.getString(uploadRequest, "zone3_conAdh");
		long zone4employeeProductPeriodId = ParamUtil.getLong(uploadRequest, "4dateRadio");
		String zonePriceType4 = ParamUtil.getString(uploadRequest, "zonePriceType4");
		String zone4_codesTarif = ParamUtil.getString(uploadRequest, "zone4_codesTarif");
		String zone4_aduEmp = ParamUtil.getString(uploadRequest, "zone4_aduEmp");
		String zone4_aduAdh = ParamUtil.getString(uploadRequest, "zone4_aduAdh");
		String zone4_enfEmp = ParamUtil.getString(uploadRequest, "zone4_enfEmp");
		String zone4_enfAdh = ParamUtil.getString(uploadRequest, "zone4_enfAdh");
		String zone4_conEmp = ParamUtil.getString(uploadRequest, "zone4_conEmp");
		String zone4_conAdh = ParamUtil.getString(uploadRequest, "zone4_conAdh");
		
		// check cloning conditions
		if(status.equals(AniConstants.ONLINE))
		{
			// check if form field changed or not
			if(formFinalStateChanged)
			{
				descendantVOs = employeeProductBusinessDelegate.onlineCloneConditionMethod(employeeProductId, status);
				status = AniConstants.DRAFT;
			}
		}
		else if(status.equals(AniConstants.RETIRE))
		{
			employeeProductBusinessDelegate.retireCloneConditionMethod(employeeProductId, status);
			status = AniConstants.DRAFT;
		}
		
		//  Persist Warranty Details
		enterpriseProductHelper.setWarrantyDetails(uploadRequest, employeeProductId, status);
		
		// update EmployeeProductPrice
		LOGGER.info("Persisting Price Details");
		EmployeeProductCodesTarif employeeProductCodesTarif = null;
		EmployeeProductCodesTarif updatedEmployeeProductCodesTarif = null;
		if(regime.equals(AniConstants.RG)){
			if(zone1){
				String oldPriceType = StringPool.BLANK;
				// update code tarifs info
				try{
					employeeProductCodesTarif = employeeProductBusinessDelegate.findByZoneId_EmployeeProductId_Status(AniConstants.ZONE_1_ID, employeeProductId, status);
					oldPriceType = employeeProductCodesTarif.getPriceType();
				}catch(MhmCheckedTechnicalException e){}
								
				updatedEmployeeProductCodesTarif = employeeProductBusinessDelegate.addOrUpdateCodesTarif(employeeProductId, AniConstants.ZONE_1_ID, zonePriceType1, zone1_codesTarif, situationId, structureId, status, trancheAgeSelected);
				// update zone1 prices
				if(Validator.isNotNull(zone1employeeProductPeriodId))
				{
					if(structureId == AniConstants.STRUCTURE_ADULTE_ID){
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone1employeeProductPeriodId, zone1_aduEmp, zone1_aduAdh, AniConstants.STRUCTURE_DETAILS_F_ADULTE_ID, status,false);
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone1employeeProductPeriodId, zone1_enfEmp, zone1_enfAdh, AniConstants.STRUCTURE_DETAILS_F_ENFANT_ID, status,false);
					} else {
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone1employeeProductPeriodId, zone1_aduEmp, zone1_aduAdh, AniConstants.STRUCTURE_DETAILS_S_SALARIE_ID, status,false);
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone1employeeProductPeriodId, zone1_enfEmp, zone1_enfAdh, AniConstants.STRUCTURE_DETAILS_S_CONJOINT_ID, status,false);
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone1employeeProductPeriodId, zone1_conEmp, zone1_conAdh, AniConstants.STRUCTURE_DETAILS_S_ENFANT_ID, status,false);
					}
				}
				// check price type, if changed, wipe all the prices for this code tarif
				if(Validator.isNotNull(employeeProductCodesTarif) && cloneStatus.equals(AniConstants.ONLINE))
				{
					if(!updatedEmployeeProductCodesTarif.getPriceType().equals(oldPriceType))
					{
						employeeProductBusinessDelegate.deletePriceData(employeeProductCodesTarif.getEmployeeproductcodestarifId(), status);
					}
				}
			}
			if(zone2){
				String oldPriceType = StringPool.BLANK;
				// update code tarifs info
				try{
					employeeProductCodesTarif = employeeProductBusinessDelegate.findByZoneId_EmployeeProductId_Status(AniConstants.ZONE_2_ID, employeeProductId, status);
					oldPriceType = employeeProductCodesTarif.getPriceType();
				}catch(MhmCheckedTechnicalException e){}
				
				updatedEmployeeProductCodesTarif = employeeProductBusinessDelegate.addOrUpdateCodesTarif(employeeProductId, AniConstants.ZONE_2_ID, zonePriceType2, zone2_codesTarif, situationId, structureId, status, trancheAgeSelected);
				// update zone2 prices
				if(Validator.isNotNull(zone2employeeProductPeriodId))
				{
					if(structureId == AniConstants.STRUCTURE_ADULTE_ID){
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone2employeeProductPeriodId, zone2_aduEmp, zone2_aduAdh, AniConstants.STRUCTURE_DETAILS_F_ADULTE_ID, status,false);
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone2employeeProductPeriodId, zone2_enfEmp, zone2_enfAdh, AniConstants.STRUCTURE_DETAILS_F_ENFANT_ID, status,false);
					} else {
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone2employeeProductPeriodId, zone2_aduEmp, zone2_aduAdh, AniConstants.STRUCTURE_DETAILS_S_SALARIE_ID, status,false);
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone2employeeProductPeriodId, zone2_enfEmp, zone2_enfAdh, AniConstants.STRUCTURE_DETAILS_S_CONJOINT_ID, status,false);
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone2employeeProductPeriodId, zone2_conEmp, zone2_conAdh, AniConstants.STRUCTURE_DETAILS_S_ENFANT_ID, status,false);
					}
				}
				// check price type, if changed, wipe all the prices for this code tarif
				if(Validator.isNotNull(employeeProductCodesTarif) && cloneStatus.equals(AniConstants.ONLINE))
				{
					if(!updatedEmployeeProductCodesTarif.getPriceType().equals(oldPriceType))
					{
						employeeProductBusinessDelegate.deletePriceData(employeeProductCodesTarif.getEmployeeproductcodestarifId(), status);
					}
				}
			}
			if(zone3){
				String oldPriceType = StringPool.BLANK;
				// update code tarifs info
				try{
					employeeProductCodesTarif = employeeProductBusinessDelegate.findByZoneId_EmployeeProductId_Status(AniConstants.ZONE_3_ID, employeeProductId, status);
					oldPriceType = employeeProductCodesTarif.getPriceType();
				}catch(MhmCheckedTechnicalException e){}
				
				updatedEmployeeProductCodesTarif = employeeProductBusinessDelegate.addOrUpdateCodesTarif(employeeProductId, AniConstants.ZONE_3_ID, zonePriceType3, zone3_codesTarif, situationId, structureId, status, trancheAgeSelected);
				// update zone3 prices
				if(Validator.isNotNull(zone3employeeProductPeriodId))
				{
					if(structureId == AniConstants.STRUCTURE_ADULTE_ID){
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone3employeeProductPeriodId, zone3_aduEmp, zone3_aduAdh, AniConstants.STRUCTURE_DETAILS_F_ADULTE_ID, status,false);
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone3employeeProductPeriodId, zone3_enfEmp, zone3_enfAdh, AniConstants.STRUCTURE_DETAILS_F_ENFANT_ID, status,false);
					} else {
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone3employeeProductPeriodId, zone3_aduEmp, zone3_aduAdh, AniConstants.STRUCTURE_DETAILS_S_SALARIE_ID, status,false);
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone3employeeProductPeriodId, zone3_enfEmp, zone3_enfAdh, AniConstants.STRUCTURE_DETAILS_S_CONJOINT_ID, status,false);
						employeeProductBusinessDelegate.addEmployeeProductPrice(zone3employeeProductPeriodId, zone3_conEmp, zone3_conAdh, AniConstants.STRUCTURE_DETAILS_S_ENFANT_ID, status,false);
					}
				}
				// check price type, if changed, wipe all the prices for this code tarif
				if(Validator.isNotNull(employeeProductCodesTarif) && cloneStatus.equals(AniConstants.ONLINE))
				{
					if(!updatedEmployeeProductCodesTarif.getPriceType().equals(oldPriceType))
					{
						employeeProductBusinessDelegate.deletePriceData(employeeProductCodesTarif.getEmployeeproductcodestarifId(), status);
					}
				}
			}
		}
		else{
			String oldPriceType = StringPool.BLANK;
			try{
				employeeProductCodesTarif = employeeProductBusinessDelegate.findByZoneId_EmployeeProductId_Status(AniConstants.ZONE_4_ID, employeeProductId, status);
				oldPriceType = employeeProductCodesTarif.getPriceType();
			}catch(MhmCheckedTechnicalException e){}
			// update code tarifs info
			updatedEmployeeProductCodesTarif = employeeProductBusinessDelegate.addOrUpdateCodesTarif(employeeProductId, AniConstants.ZONE_4_ID, zonePriceType4, zone4_codesTarif, situationId, structureId, status, trancheAgeSelected);
			// update regime local prices (zone4)
			if(Validator.isNotNull(zone4employeeProductPeriodId))
			{
				if(structureId == AniConstants.STRUCTURE_ADULTE_ID){
					employeeProductBusinessDelegate.addEmployeeProductPrice(zone4employeeProductPeriodId, zone4_aduEmp, zone4_aduAdh, AniConstants.STRUCTURE_DETAILS_F_ADULTE_ID, status,false);
					employeeProductBusinessDelegate.addEmployeeProductPrice(zone4employeeProductPeriodId, zone4_enfEmp, zone4_enfAdh, AniConstants.STRUCTURE_DETAILS_F_ENFANT_ID, status,false);
				} else {
					employeeProductBusinessDelegate.addEmployeeProductPrice(zone4employeeProductPeriodId, zone4_aduEmp, zone4_aduAdh, AniConstants.STRUCTURE_DETAILS_S_SALARIE_ID, status,false);
					employeeProductBusinessDelegate.addEmployeeProductPrice(zone4employeeProductPeriodId, zone4_enfEmp, zone4_enfAdh, AniConstants.STRUCTURE_DETAILS_S_CONJOINT_ID, status,false);
					employeeProductBusinessDelegate.addEmployeeProductPrice(zone4employeeProductPeriodId, zone4_conEmp, zone4_conAdh, AniConstants.STRUCTURE_DETAILS_S_ENFANT_ID, status,false);
				}
			}
			// check price type, if changed, wipe all the prices for this code tarif
			if(Validator.isNotNull(employeeProductCodesTarif) && cloneStatus.equals(AniConstants.ONLINE))
			{
				if(!updatedEmployeeProductCodesTarif.getPriceType().equals(oldPriceType))
				{
					employeeProductBusinessDelegate.deletePriceData(employeeProductCodesTarif.getEmployeeproductcodestarifId(), status);
				}
			}
		}
		
		// Get completed status
		String completedStatus = AniConstants.NON;
		completedStatus = employeeProductBusinessDelegate.getCompletedStatus(employeeProductId,codeProduitTechnique, regime, zone1, zone2, zone3, status,structureId,employeeProductBusinessDelegate.getTrancheAgeByEmployeeProduct(employeeProduct, true));
		
		// update EmployeeProduct
		/*Change done due to MHM-5466 improvement*/ 
		employeeProduct.setPosition(position);
		employeeProduct.setName(name);
		employeeProduct.setCodeProduitTechnique(codeProduitTechnique);
		employeeProduct.setLevelOfProtection(levelOfProtectionDocument);
		employeeProduct.setSummary(productSummaryEditorText);
		employeeProduct.setPricingDetails(priceDetailseditorText);
		employeeProduct.setIncludeWarrantyDesc(includedEditorText);
		employeeProduct.setStatus(status);
		employeeProduct.setModifiedDate(new Date());
		employeeProduct.setCompleted(completedStatus);
		employeeProductBusinessDelegate.updateEmployeeProduct(employeeProduct);
		
		// upload document
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		employeeProductHelper.uploadDocuments(actionRequest, uploadRequest, themeDisplay, offer.getOfferId(), employeeProduct.getEnterpriseProductId(), employeeProductId, status);
		
		// delete documents
		employeeProductHelper.deleteDocuments(deletedDocumentIds);
		
		Map<String, Boolean> changeStatusMap = new HashMap<String, Boolean>();
		
		// Update Enterprise Product Status
		
		employeeProductBusinessDelegate.updateEnterpriseProductCompletedStatus(enterpriseProduct.getEnterpriseProductId(), productStatus);
		offerManagementBusinessDelegate.setOfferComplete(offer);
		
		// perform cloning
		if(cloneStatus.equals(AniConstants.ONLINE) && formFinalStateChanged)
		{
			for(EmpProdVO employeeProductDescendantVO : descendantVOs)
			{
				employeeProductBusinessDelegate.cloneEmployeeProductAndDescendants(employeeProductDescendantVO);
			}
			// Check Online and make impact upwards if last draft employee product. 
			offerManagementBusinessDelegate.draftEmployeeProductImpactUpward(employeeProduct,changeStatusMap);
		}
		
		if(cloneStatus.equals(AniConstants.RETIRE))
		{
			// Check retire and make impact upwards if last draft employee product. 
			offerManagementBusinessDelegate.draftEmployeeProductImpactUpwardForRetire(employeeProduct, changeStatusMap);
		}
		// set render parameters
		actionResponse.setRenderParameter(AniConstants.EMPLOYEE_PRODUCT_ID, employeeProductId);
		
		if(changeStatusMap.get("draftOffer")!=null && changeStatusMap.get("draftOffer")){
			actionResponse.setRenderParameter("offerStatus", AniConstants.DRAFT);
		} else {
			actionResponse.setRenderParameter("offerStatus", offerStatus);
		}
		
		if(changeStatusMap.get("draftProduct")!=null && changeStatusMap.get("draftProduct")){
			actionResponse.setRenderParameter("productStatus", AniConstants.DRAFT);
		} else {
			actionResponse.setRenderParameter("productStatus", productStatus);
		}
		
		actionResponse.setRenderParameter(AniConstants.STATUS, status);
		actionResponse.setRenderParameter(AniConstants.FORM_ACTION, AniConstants.EDIT);
		LOGGER.info(MhmConstants.END+"editEmployeeProduct");
	}

}
