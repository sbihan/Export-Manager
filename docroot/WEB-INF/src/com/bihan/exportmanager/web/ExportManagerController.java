package com.bihan.exportmanager.web;

import com.bihan.exportmanager.model.ExportManager;
import com.bihan.exportmanager.model.ExportManagerField;
import com.bihan.exportmanager.service.ExportManagerFieldLocalServiceUtil;
import com.bihan.exportmanager.service.ExportManagerLocalServiceUtil;
import com.bihan.exportmanager.util.ExportManagerDTO;
import com.bihan.exportmanager.util.ExportManagerFieldComparator;
import com.bihan.exportmanager.util.ExportManagerUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http.Response;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

public class ExportManagerController extends MVCPortlet {
	
	private static final Log LOG = LogFactoryUtil.getLog(ExportManagerController.class);

	String[] blackAttributes = {"getExpandoBridge","getPrimaryKey","getPrimaryKeyObj","getModelAttributes","getModelClass","getModelClassName"};
	List<String> blackAttributesList =Arrays.asList(blackAttributes);

	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		// TODO Auto-generated method stub
		
		String cmd = ParamUtil.getString(request, Constants.CMD, StringPool.BLANK);
		String currentURL = ParamUtil.getString(request, "currentURL");
		
		try{
			if(Constants.ADD.equals(cmd)){
							
				List<ClassName> localServiceClassNames = ExportManagerUtil.getUnLocalServiceClassNames();			
								
				request.setAttribute("classNames", localServiceClassNames);		
				
				request.setAttribute("backURL",currentURL);

			} else if(Constants.EDIT.equals(cmd)){
				
				long exportManagerId = ParamUtil.getLong(request, "exportManagerId");

				ExportManager exportManager = ExportManagerLocalServiceUtil.getExportManager(exportManagerId);
				List<ExportManagerField> exportManagerFields = ExportManagerFieldLocalServiceUtil.getExportManagerFields(exportManagerId);
				List<ClassName> localServiceClassNames = ExportManagerUtil.getUnLocalServiceClassNames();			
				
				request.setAttribute("classNames", localServiceClassNames);	
				request.setAttribute("exportManager", exportManager);		
				request.setAttribute("exportManagerFields", exportManagerFields);		
				request.setAttribute("backURL",currentURL);

			} else if(Constants.VIEW.equals(cmd)){
				
				long exportManagerId = ParamUtil.getLong(request, "exportManagerId");
		
				ExportManager exportManager = ExportManagerLocalServiceUtil.getExportManager(exportManagerId);
				
				ExportManagerDTO exportManagerDTO = new ExportManagerDTO();
				
				exportManagerDTO.setExportManager(exportManager);
				
				ClassName className = ClassNameLocalServiceUtil.getClassName(exportManager.getClassNameId());
				
				exportManagerDTO.setClassName(className);
				
				Method methodCOUNT = ExportManagerUtil.getEntitiesMethodCount(className);
				
				int sizeObject = (Integer) methodCOUNT.invoke(null);
						
				exportManagerDTO.setSizeObjects(sizeObject);

				List<ExportManagerField> exportManagerFields = ExportManagerFieldLocalServiceUtil.getExportManagerFields(exportManagerId);
				
				List<ExportManagerField> exportManagerFieldsSort = new ArrayList<ExportManagerField>(exportManagerFields);
				
				Collections.sort(exportManagerFieldsSort, new ExportManagerFieldComparator());
				
				exportManagerDTO.setFields(exportManagerFieldsSort);

				PortletURL portletURL = response.createRenderURL();
				
				portletURL.setParameter("mvcPath", "/view_export.jsp");
				portletURL.setParameter(Constants.CMD, Constants.VIEW);
				portletURL.setParameter("exportManagerId", String.valueOf(exportManagerId));
				portletURL.setParameter("currentURL", currentURL);

				exportManagerDTO.setPortletURL(portletURL);
				
				request.setAttribute("exportManagerDTO", exportManagerDTO);
				request.setAttribute("backURL",currentURL);
				request.setAttribute("classNameValue",className.getValue());

			}
			
		} catch (PortalException e) {
			LOG.error("PortalException on render : "+ e.getMessage());
			if (LOG.isDebugEnabled()) {
				LOG.error(e);
			}
		} catch (SystemException e) {
			LOG.error("SystemException on render : "+ e.getMessage());
			if (LOG.isDebugEnabled()) {
				LOG.error(e);
			}
		} catch (SecurityException e) {
			LOG.error("SecurityException on render : "+ e.getMessage());
			if (LOG.isDebugEnabled()) {
				LOG.error(e);
			}
		} catch (IllegalAccessException e) {
			LOG.error("IllegalAccessException on render : "+ e.getMessage());
			if (LOG.isDebugEnabled()) {
				LOG.error(e);
			}
		} catch (IllegalArgumentException e) {
			LOG.error("IllegalArgumentException on render : "+ e.getMessage());
			if (LOG.isDebugEnabled()) {
				LOG.error(e);
			}
		} catch (InvocationTargetException e) {
			LOG.error("InvocationTargetException on render : "+ e.getMessage());
			if (LOG.isDebugEnabled()) {
				LOG.error(e);
			}
		}
		
		
		super.render(request, response);
	}
	
	@Override
	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {
		
		
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD, StringPool.BLANK);
		String redirectURL = ParamUtil.getString(actionRequest, "redirect", StringPool.BLANK);
		
		if(Constants.ADD.equals(cmd)){
			try {
				
				long exportManagerId = ParamUtil.getLong(actionRequest, "exportManagerId", 0l);

				
				ExportManager exportManager = null;
						
				if(exportManagerId==0l){
					exportManager = ExportManagerLocalServiceUtil.createExportManager(CounterLocalServiceUtil.increment(ExportManager.class.getName()));
				} else{
					exportManager = ExportManagerLocalServiceUtil.getExportManager(exportManagerId);
				}
						
				
				String name = ParamUtil.getString(actionRequest, "name");
				String description = ParamUtil.getString(actionRequest, "description");
				String scope = ParamUtil.getString(actionRequest, "scope");
				long classNameId = ParamUtil.getLong(actionRequest, "classNameId");

				exportManager.setName(name);
				exportManager.setDescription(description);
				exportManager.setScope(scope);
				exportManager.setClassNameId(classNameId);
				exportManager.setClassNameValue(ClassNameLocalServiceUtil.getClassName(classNameId).getClassName());
				
				if(exportManagerId==0l){
					ExportManagerLocalServiceUtil.addExportManager(exportManager);
				} else{
					ExportManagerLocalServiceUtil.updateExportManager(exportManager);
					for(ExportManagerField exportManagerField : ExportManagerFieldLocalServiceUtil.getExportManagerFields(exportManagerId)){
						ExportManagerFieldLocalServiceUtil.deleteExportManagerField(exportManagerField);
					}
				}
				
				int resultSize = ParamUtil.getInteger(actionRequest, "result_size");

				int index = 1;

				while(index<=resultSize){
					
					String attributeName = ParamUtil.getString(actionRequest, "attributes_"+index);
					String displayName = ParamUtil.getString(actionRequest, "display_name_"+index, StringPool.BLANK);
					int positionName = ParamUtil.getInteger(actionRequest, "position_attributes_"+index);

					index++;
					
					if(attributeName!=null && attributeName!=StringPool.BLANK){
						
						ExportManagerField exportManagerField = ExportManagerFieldLocalServiceUtil.createExportManagerField(CounterLocalServiceUtil.increment(ExportManagerField.class.getName()));
						
						exportManagerField.setExportManagerId(exportManager.getExportManagerId());
						exportManagerField.setFieldName(attributeName);
						exportManagerField.setFieldDisplayName(displayName);
						exportManagerField.setPosition(positionName);
						
						ExportManagerFieldLocalServiceUtil.addExportManagerField(exportManagerField);

					}
				}
				
				SessionMessages.add(actionRequest, "actionSuccessExportManager");
					
			} catch (SystemException e) {
				LOG.error("SystemException when ADD or EDIT an export : "+ e.getMessage());
				if (LOG.isDebugEnabled()) {
					LOG.error(e);
				}
				SessionErrors.add(actionRequest, "actionErrorExportManager");
			} catch (PortalException e) {
				LOG.error("PortalException when ADD or EDIT an export : "+ e.getMessage());
				if (LOG.isDebugEnabled()) {
					LOG.error(e);
				}
				SessionErrors.add(actionRequest, "actionErrorExportManager");
			}
		} else if(Constants.DELETE.equals(cmd)){
			long exportManagerId = ParamUtil.getLong(actionRequest, "exportManagerId", 0l);

			try{
				ExportManagerLocalServiceUtil.deleteExportManager(exportManagerId);
				for(ExportManagerField exportManagerField : ExportManagerFieldLocalServiceUtil.getExportManagerFields(exportManagerId)){
					ExportManagerFieldLocalServiceUtil.deleteExportManagerField(exportManagerField);
				}
				SessionMessages.add(actionRequest, "actionSuccessExportManager");

			} catch (SystemException e) {
				LOG.error("SystemException when DELETE an export : "+ e.getMessage());
				if (LOG.isDebugEnabled()) {
					LOG.error(e);
				}
				SessionErrors.add(actionRequest, "actionErrorExportManager");
			} catch (PortalException e) {
				LOG.error("PortalException when DELETE an export : "+ e.getMessage());
				if (LOG.isDebugEnabled()) {
					LOG.error(e);
				}
				SessionErrors.add(actionRequest, "actionErrorExportManager");
			}
		}
						
		actionResponse.sendRedirect(redirectURL);
		
		super.processAction(actionRequest, actionResponse);		
	}


	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		// TODO Auto-generated method stub
		
		String classNameSelectId = resourceRequest.getParameter("classNameSelectId");

		try {
			
			ClassName className = ClassNameLocalServiceUtil.getClassName(Long.valueOf(classNameSelectId));
						
			String classNameModel = className.getValue()+"Model";
			
			Class<?> cls = Class.forName(classNameModel);
	
			Method[] methods = cls.getMethods();
			
			Map<String, String> entityAttributesDTO = new HashMap<String, String>();
					
			JSONObject jsonObjectDirect = JSONFactoryUtil.createJSONObject();
			
			
			for(int i=0; i<methods.length; i++){
				
				String methodName = methods[i].getName();
				if(!methodName.startsWith("set") && methodName.startsWith("get") && !blackAttributesList.contains(methodName)){
					methodName = methodName.replaceFirst("get", "");
					methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
					entityAttributesDTO.put(methodName, methodName);
					jsonObjectDirect.put(methodName, methodName);
				}
			}
									
			resourceResponse.setContentType(ContentTypes.APPLICATION_JSON);
			
			PrintWriter writer = resourceResponse.getWriter();
			
			writer.write(jsonObjectDirect.toString());
			writer.close();

		} catch (NumberFormatException e) {
			LOG.error("NumberFormatException on attributes load for classNameId : "+classNameSelectId+" : "+ e.getMessage());
			if (LOG.isDebugEnabled()) {
				LOG.error(e);
			}
		} catch (PortalException e) {
			LOG.error("PortalException on attributes load for classNameId : "+classNameSelectId+" : "+ e.getMessage());
			if (LOG.isDebugEnabled()) {
				LOG.error(e);
			}
		} catch (SystemException e) {
			LOG.error("SystemException on attributes load for classNameId : "+classNameSelectId+" : "+ e.getMessage());
			if (LOG.isDebugEnabled()) {
				LOG.error(e);
			}
		} catch (ClassNotFoundException e) {
			LOG.error("ClassNotFoundException on attributes load for classNameId : "+classNameSelectId+" : "+ e.getMessage());
			if (LOG.isDebugEnabled()) {
				LOG.error(e);
			}
		}
		
		super.serveResource(resourceRequest, resourceResponse);
	}
}
