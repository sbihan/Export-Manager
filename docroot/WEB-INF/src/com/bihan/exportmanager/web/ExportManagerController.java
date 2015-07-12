package com.bihan.exportmanager.web;

import com.bihan.exportmanager.model.ExportManager;
import com.bihan.exportmanager.model.ExportManagerField;
import com.bihan.exportmanager.service.ExportManagerFieldLocalServiceUtil;
import com.bihan.exportmanager.service.ExportManagerLocalServiceUtil;
import com.bihan.exportmanager.util.ClassNameComparator;
import com.bihan.exportmanager.util.ExportManagerUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.StringUtil_IW;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.persistence.OrgGroupPermissionFinderUtil;
import com.liferay.portal.service.persistence.OrgGroupPermissionUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.sun.xml.internal.ws.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

public class ExportManagerController extends MVCPortlet {
	
	private static final String RENDER_PARAM_ADD_EXPORT="add_export";

	private static final String ACTION_PARAM = "action";
	
	String[] blackAttributes = {"getExpandoBridge","getPrimaryKey","getPrimaryKeyObj","getModelAttributes","getModelClass","getModelClassName"};
	List<String> blackAttributesList =Arrays.asList(blackAttributes);

	@Override
	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {
		
		
		String action = ParamUtil.getString(actionRequest, ACTION_PARAM, StringPool.BLANK);
		
		if(RENDER_PARAM_ADD_EXPORT.equals(action)){
			try {
				
				ExportManager exportManager = ExportManagerLocalServiceUtil.createExportManager(CounterLocalServiceUtil.increment(ExportManager.class.getName()));
				
				String name = ParamUtil.getString(actionRequest, "name");
				String description = ParamUtil.getString(actionRequest, "description");
				String scope = ParamUtil.getString(actionRequest, "scope");
				long classNameId = ParamUtil.getLong(actionRequest, "classNameId");

				exportManager.setName(name);
				exportManager.setDescription(description);
				exportManager.setScope(scope);
				exportManager.setClassNameId(classNameId);
				exportManager.setClassNameValue(ClassNameLocalServiceUtil.getClassName(classNameId).getClassName());
				
				ExportManagerLocalServiceUtil.addExportManager(exportManager);
				
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
					
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.processAction(actionRequest, actionResponse);		
	}

	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		// TODO Auto-generated method stub
		
		String action = ParamUtil.getString(request, ACTION_PARAM, StringPool.BLANK);
		
		if(RENDER_PARAM_ADD_EXPORT.equals(action)){
						
			List<ClassName> localServiceClassNames = ExportManagerUtil.getUnLocalServiceClassNames();			
			
			Collections.sort(localServiceClassNames, new ClassNameComparator());
			
			request.setAttribute("classNames", localServiceClassNames);		
			
		}
		
		super.render(request, response);
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
					entityAttributesDTO.put(methodName, methodName);
					jsonObjectDirect.put(methodName, methodName);
				}
			}
									
			resourceResponse.setContentType(ContentTypes.APPLICATION_JSON);
			
			PortletResponseUtil.write(resourceResponse, jsonObjectDirect.toString());
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	
	private String removeLastComma(String str) {
	    if (str.length() > 0) {
	      str = str.substring(0, str.length()-1);
	    }
	    return str;
	}

	
	
}
