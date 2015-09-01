<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.bihan.exportmanager.service.ExportManagerLocalServiceUtil"%>
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

<liferay-ui:success key="actionSuccessExportManager" message="your-request-completed-successfully"></liferay-ui:success>
<liferay-ui:error key="actionErrorExportManager" message="your-request-failed-to-complete"></liferay-ui:error>

<aui:button-row>
		<div class="lfr-portlet-toolbar">
			<portlet:renderURL var="addExportURL">
				<portlet:param name="<%=Constants.CMD %>" value="<%=Constants.ADD %>" />
				<portlet:param name="jspPage" value="/add_export.jsp" />
				<portlet:param name="currentURL" value="<%=themeDisplay.getURLCurrent() %>"/>
			</portlet:renderURL>
			<span class="lfr-toolbar-button add-button">
				<a href="<%= addExportURL %>"><liferay-ui:message key="add-export" /></a>
			</span>	
		</div>
		<liferay-ui:search-container delta="20" emptyResultsMessage="no-export-were-found">
			<liferay-ui:search-container-results
				results="<%= ExportManagerLocalServiceUtil.getExportManagers(searchContainer.getStart(), searchContainer.getEnd())%>"
				total="<%=ExportManagerLocalServiceUtil.getExportManagersCount()%>"
			/>
		
			<liferay-ui:search-container-row
				className="com.bihan.exportmanager.model.ExportManager"
				keyProperty="exportManagerId"
				modelVar="exportManager"
			>
				<liferay-ui:search-container-column-text
					name="Name"
					property="name"
				/>
		
				<liferay-ui:search-container-column-text
					name="Description"
					property="description"
				/>
				
				<liferay-ui:search-container-column-text
					name="Class Name"
					property="classNameValue"
				/>

				<liferay-ui:search-container-column-jsp
				align="right"
				path="/export_action.jsp"
				/>
				
			</liferay-ui:search-container-row>
		
			<liferay-ui:search-iterator />
	
	</liferay-ui:search-container>

</aui:button-row>