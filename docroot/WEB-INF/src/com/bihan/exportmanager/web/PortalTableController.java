package com.bihan.exportmanager.web;

import com.bihan.exportmanager.model.ExportManager;
import com.bihan.exportmanager.service.ExportManagerLocalServiceUtil;
import com.bihan.exportmanager.util.ExportManagerConstant;
import com.bihan.exportmanager.util.ExportManagerDTO;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.taglib.ui.SearchContainerRowParameterTag;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class PortalTableController extends MVCPortlet {

	@Override
	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {
		// TODO Auto-generated method stub
		super.processAction(actionRequest, actionResponse);
	}

	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		try {
			
			List<ExportManager> exportManagers = ExportManagerLocalServiceUtil.getExportManagers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						
			List<ExportManagerDTO> exportManagerDTOs = new ArrayList<ExportManagerDTO>();
			
			List<SearchContainer> searchContainers = new ArrayList<SearchContainer>();

			for(ExportManager exportManager : exportManagers){
				if(exportManager.getScope().equals(ExportManagerConstant.SCOPE_PORTAL)){
					
					ExportManagerDTO exportManagerDTO = new ExportManagerDTO();
					
					exportManagerDTO.setExportManager(exportManager);
					
					
					ClassName className = ClassNameLocalServiceUtil.getClassName(exportManager.getClassNameId());
					
					exportManagerDTO.setClassNameObject(className.getClassName());
					
					String methodGetClassNames = className.getClassName().substring(className.getClassName().indexOf("model.")+"model.".length());
					
					methodGetClassNames += "s";
					methodGetClassNames = "get"+methodGetClassNames;

					String classNameLocalServiceUtil = className.getClassName()+"LocalServiceUtil";
					
					classNameLocalServiceUtil = classNameLocalServiceUtil.replaceAll("model", "service");
					
					Class objectLocalServiceUtil = Class.forName(classNameLocalServiceUtil);
					
					Method method = objectLocalServiceUtil.getMethod(methodGetClassNames, int.class, int.class);
					
					List<Object> listObject = (List<Object>) method.invoke(null, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
					
					exportManagerDTO.setObjects(listObject);
					
//					String fields = exportManager.getFields();
//					
//					String[] fieldsArray = fields.split(StringPool.COMMA);
//					
//					List<String> fieldsList = new ArrayList<String>();
//					
//					for(int i=0; i<fieldsArray.length; i++){
//						
//						String str = fieldsArray[i];
//						
//						str = str.replaceFirst(str.substring(0,1), str.substring(0,1).toLowerCase());
//
//						if(i==0){
//							exportManagerDTO.setPrimaryKey(str);
//							fieldsList.add(str);
//						} else{
//							fieldsList.add(str);
//						}
//					}
//					
//					exportManagerDTO.setFields(fieldsList);
					
					exportManagerDTOs.add(exportManagerDTO);
																	
//					SearchContainer searchContainer = new SearchContainer();
//					
//					searchContainer.setClassName(exportManagerDTO.getClassNameObject());
//					searchContainer.setHeaderNames(fieldsList);
//					searchContainer.setResults(exportManagerDTO.getObjects());
//					searchContainer.setTotal(exportManagerDTO.getObjects().size());
//					
//					searchContainers.add(searchContainer);
//					
//					request.setAttribute("totoContainer", searchContainer);

				}
				
			}
			
			request.setAttribute("exportManagerDTOs", exportManagerDTOs);

						
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.render(request, response);
	}

}
