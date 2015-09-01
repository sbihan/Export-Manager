<%@page import="com.bihan.exportmanager.model.ExportManager"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>

<%@ include file="init.jsp" %>

<%
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	
	ExportManager exportManager = (ExportManager)row.getObject();
		 
	pageContext.setAttribute("exportManager", exportManager);

%>	

<liferay-ui:icon-menu>

		<portlet:renderURL var="viewExportURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.VIEW %>" />
			<portlet:param name="mvcPath" value="/view_export.jsp" />
			<portlet:param name="exportManagerId" value="${exportManager.exportManagerId}" />
			<portlet:param name="currentURL" value="<%=themeDisplay.getURLCurrent() %>"/>
		</portlet:renderURL>

		<liferay-ui:icon
			image="view"
			url="<%= viewExportURL %>"
		/>
		
		
		<portlet:renderURL var="editExportURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EDIT %>" />
			<portlet:param name="mvcPath" value="/add_export.jsp" />
			<portlet:param name="exportManagerId" value="${exportManager.exportManagerId}" />
			<portlet:param name="currentURL" value="<%=themeDisplay.getURLCurrent() %>"/>
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editExportURL %>"
		/>
		
		<portlet:actionURL var="deleteExportURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="exportManagerId" value="${exportManager.exportManagerId}" />
			<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent() %>"/>
		</portlet:actionURL>

		<liferay-ui:icon
			image="delete"
			url="<%= deleteExportURL %>"
		/>

</liferay-ui:icon-menu>