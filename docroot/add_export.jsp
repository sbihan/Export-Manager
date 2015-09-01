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

<portlet:actionURL var="editExportURL">
	<portlet:param name="<%=Constants.CMD%>" value="<%=Constants.ADD%>"/>
</portlet:actionURL>

<portlet:resourceURL var="classNameURL">
</portlet:resourceURL>

<c:if test='${not empty exportManager}'>
	<c:set var="exportManagerId" value="${exportManager.exportManagerId}" />
	<c:set var="exportManagerName" value="${exportManager.name}" />
	<c:set var="exportManagerDescription" value="${exportManager.description}" />
	<c:set var="exportManagerNameClassNameId" value="${exportManager.classNameId}" />
</c:if>
<c:if test='${empty exportManager}'>
	<c:set var="exportManagerId" value="0l" />
	<c:set var="exportManagerName" value="" />
	<c:set var="exportManagerDescription" value="" />
	<c:set var="exportManagerNameClassNameId" value="0" />
</c:if>

<liferay-ui:header backURL="${backURL}" title="add-export"></liferay-ui:header>

<aui:form action="<%= editExportURL %>" method="post" name="fm" >

	<aui:fieldset>
		
		<aui:input name="exportManagerId" value="${exportManagerId}" type="hidden"/>
		<aui:input name="redirect" value="${backURL}" type="hidden"/>
		
		<aui:input label="name" name="name" value="${exportManagerName}"><aui:validator name="required"/></aui:input>
	
		<aui:input label="description" cssClass="lfr-textarea-container" type="textarea" name="description" value="${exportManagerDescription}"/>
		
		<div style="display:none;">
			<aui:select id="scope" name="scope" label="scope">
				<aui:option label="<%=ExportManagerConstant.SCOPE_SITE %>" value="<%=ExportManagerConstant.SCOPE_SITE %>"/>
				<aui:option label="<%=ExportManagerConstant.SCOPE_PORTAL %>" value="<%=ExportManagerConstant.SCOPE_PORTAL %>"/>
			</aui:select>
		</div>
		
		<aui:select id="classNameSelectId" name="classNameId" label="Class Name" required="<%= true %>">
			<aui:option label="className" value="" disabled="<%= true %>" selected="${empty exportManager}"/>
			<c:forEach items="${classNames}" var="item">
				<aui:option selected="${exportManagerNameClassNameId == item.classNameId}" label="${item.value}" value="${item.classNameId}"/>	
			</c:forEach>
		</aui:select>	
		
		<div id="ajax_refresh_area">
			
		</div>
		
		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="${backURL}" type="cancel" />
		</aui:button-row>

	</aui:fieldset>

</aui:form>

<aui:script use="aui-dialog,aui-overlay-manager,aui-base">
	
	var A = AUI();
		
	Liferay.provide(
		window,
		'<portlet:namespace />chargerAjaxRefreshArea',
		function() {
		
			var A = AUI();
			var ajax_refresh_area = A.one('#ajax_refresh_area');
			
			ajax_refresh_area.empty();
			
			var classNameSelectId = A.one('#<portlet:namespace />classNameSelectId').val();

			var url = '<%= classNameURL.toString() %>';
			A.io.request(
				url,
				{
					//data to be sent to server
					data: {
						
						<portlet:namespace />classNameSelectId: classNameSelectId,

					},
					dataType: 'json',
					
					on: {
						failure: function() {
						},
						
						success: function(event, id, obj) {
							
							var result = this.get('responseData');
							
							var count = Object.keys(result).length
							
							var sizeNodeObject = A.Node.create('<input id="result_size" type="hidden" name="result_size" value="'+count+'"/>');
							
							ajax_refresh_area.appendChild(sizeNodeObject);
							
							var index = 1;
							
							for (var key in result) {
															
								var newNodeObject = A.Node.create('<input id="checkbox_'+key+'" class="checkbox_attributes" type="checkbox" name="attributes_'+index+'" value="'+key+'" onchange="<portlet:namespace />displayNextInput(this);">&nbsp;'+result[key]+'&nbsp;</input><input id="display_name_'+key+'" name="display_name_'+index+'" style="display:none;width:200px;margin: 3px 0;" type="text"></input>&nbsp;<select id="position_'+key+'" name="position_attributes_'+index+'" class="select_attributes" style="display:none;"></select><br/>');
								
								index++;
								
								ajax_refresh_area.appendChild(newNodeObject);

							}
							
							var selectAttributes = A.all('.select_attributes');
					        selectAttributes.each(function(item) {
					             var increment = 1;
					             
					             while (increment < index) {
								    var text = increment;
								    var option = document.createElement("option");
								    option.value = increment;
								    option.text = increment;
								    item.appendChild(option);
								    increment++;
								}
					        }); 
					        
					       <c:if test='${not empty exportManager}'>
						       	<c:forEach items="${exportManagerFields}" var="exportManagerField">
							    	var exportManagerFieldName = "${exportManagerField.fieldName}";
						
							    	var checkboxField  = document.getElementById('checkbox_'+exportManagerFieldName).checked = true;
							    		
								    var	displayNameField = document.getElementById('display_name_'+exportManagerFieldName);
								    displayNameField.style.display = "inline";
								    displayNameField.value = "${exportManagerField.fieldDisplayName}";
								    
								   	var	positionField = document.getElementById('position_'+exportManagerFieldName);
								   	positionField.style.display = "inline";
								   	positionField.selectedIndex=${exportManagerField.position}-1;
							    
							    </c:forEach>
					       </c:if> 
														
						}						
					}
				}
				
			); //END of io Request			
		},
	['aui-io','aui-base','aui-node']
	);  //End of Provide
	
	var classNameSelectId = A.one('#<portlet:namespace />classNameSelectId');
		      
      if (classNameSelectId) {
           classNameSelectId.on(
              'change',function(e){
       			<portlet:namespace />chargerAjaxRefreshArea();
       		}
          );
          
		<c:if test='${not empty exportManager}'>
		    <portlet:namespace />chargerAjaxRefreshArea();
		</c:if>
      }
      
      
      Liferay.provide(
		window,
		'<portlet:namespace />displayNextInput',
		function(checkbox) {
		
			checkbox.nextElementSibling.style.display = checkbox.checked ? "inline":"none";
			checkbox.nextElementSibling.placeholder = "Display name : "+checkbox.value;
			
			checkbox.nextElementSibling.nextElementSibling.style.display = checkbox.checked ? "inline":"none";
						
		},
			['aui-base']
		);


</aui:script>