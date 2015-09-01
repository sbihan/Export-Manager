<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.bihan.exportmanager.util.ExportManagerUtil"%>
<%@ include file="/init.jsp" %>

<%

String classNameValue = (String) pageContext.findAttribute("classNameValue");

%>
<liferay-ui:header backURL="${backURL}" title="${exportManagerDTO.exportManager.name}"></liferay-ui:header>

<aui:fieldset>
	${exportManagerDTO.exportManager.description}
</aui:fieldset>
<br/>
<liferay-ui:search-container delta="20" emptyResultsMessage="no-entity-were-found" iteratorURL="${exportManagerDTO.portletURL}">
		
		<liferay-ui:search-container-results
			results="<%=ExportManagerUtil.getEntities(classNameValue, searchContainer.getStart(),searchContainer.getEnd()) %>"
			total="${exportManagerDTO.sizeObjects}"
		/>
				
		<liferay-ui:search-container-row
			className="java.lang.Object"
			modelVar="export"
		>
			<c:forEach items="${exportManagerDTO.fields}" var="field">
				<liferay-ui:search-container-column-text
					name="${field.fieldDisplayName}"
					property="${field.fieldName}"
				/>
			</c:forEach>

		</liferay-ui:search-container-row>
	
		<liferay-ui:search-iterator />

</liferay-ui:search-container>