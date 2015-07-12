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

<aui:button-row>

		<portlet:renderURL var="addExportURL">
			<portlet:param name="action" value="add_export" />
			<portlet:param name="jspPage" value="/add_export.jsp" />
		</portlet:renderURL>

		<aui:button href="<%= addExportURL %>" name="addExportButton" value="add-export"/>
		<br/><br/>
		<liferay-ui:search-container delta="10" emptyResultsMessage="no-users-were-found">
			<liferay-ui:search-container-results
				results="<%= ExportManagerLocalServiceUtil.getExportManagers(QueryUtil.ALL_POS, QueryUtil.ALL_POS)%>"
				total="<%=ExportManagerLocalServiceUtil.getExportManagersCount()%>"
			/>
		
			<liferay-ui:search-container-row
				className="com.bihan.exportmanager.model.ExportManager"
				keyProperty="exportManagerId"
				modelVar="modelVar"
			>
				<liferay-ui:search-container-column-text
					name="name"
					property="name"
				/>
		
				<liferay-ui:search-container-column-text
					name="description"
					property="description"
				/>
				
				<liferay-ui:search-container-column-text
					name="classNameValue"
					property="classNameValue"
				/>
				
				<liferay-ui:search-container-column-text
					name="scope"
					property="scope"
				/>
				
				<liferay-ui:search-container-column-jsp
				align="right"
				path="/export_action.jsp"
			/>
				
			</liferay-ui:search-container-row>
		
			<liferay-ui:search-iterator />
	
	</liferay-ui:search-container>

</aui:button-row>