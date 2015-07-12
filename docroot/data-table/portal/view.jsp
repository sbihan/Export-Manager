<%@page import="java.lang.reflect.Method"%>
<%@page import="com.liferay.portal.model.ClassName"%>
<%@page import="com.liferay.portal.service.ClassNameLocalServiceUtil"%>
<%@ include file="/init.jsp" %>


<c:forEach items="${exportManagerDTOs}" var="exportManagerDTO">


	<liferay-ui:search-container delta="10" emptyResultsMessage="no-users-were-found">
			<liferay-ui:search-container-results
				results="${exportManagerDTO.objects}"
				total="${fn:length(exportManagerDTO.objects)}"
			/>
					
			<liferay-ui:search-container-row
				className="java.lang.Object"
				modelVar="export"
			>
				<c:forEach items="${exportManagerDTO.fields}" var="field">
					<liferay-ui:search-container-column-text
						name="${field}"
						property="${field}"
					/>
				</c:forEach>

			</liferay-ui:search-container-row>
		
			<liferay-ui:search-iterator />
	
	</liferay-ui:search-container>
</c:forEach>
