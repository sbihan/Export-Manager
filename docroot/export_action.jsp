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

<liferay-ui:icon-menu>
		<portlet:renderURL var="editExportURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EDIT %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editExportURL %>"
		/>
		
		<portlet:renderURL var="deleteExportURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="delete"
			url="<%= deleteExportURL %>"
		/>

</liferay-ui:icon-menu>