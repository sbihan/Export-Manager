<%@page import="fr.mhm.portal.vo.EmployeeProductVO"%>
<%@page import="com.liferay.portal.kernel.util.UnicodeFormatter"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@include file="/html/common/init.jsp"%>
<link rel="stylesheet" href="<%=renderRequest.getContextPath() %>/css/ani/main.css">

<%
	EmployeeProductVO epVO = (EmployeeProductVO)renderRequest.getAttribute("employeeProductVO");
%>

<liferay-portlet:actionURL var="addURL">
	<liferay-portlet:param name="action" value="addEditEmployeeProduct" />
</liferay-portlet:actionURL>
<liferay-portlet:resourceURL id="addDateRange" var="addDateRangeURL">
	<liferay-portlet:param name="action" value="addEditEmployeeProduct" />
</liferay-portlet:resourceURL>
<liferay-portlet:resourceURL id="deleteDateRange" var="deleteDateRangeURL">
	<liferay-portlet:param name="action" value="addEditEmployeeProduct" />
</liferay-portlet:resourceURL>
<liferay-portlet:resourceURL id="populatePriceDetails" var="populatePriceDetailsURL">
	<liferay-portlet:param name="action" value="addEditEmployeeProduct" />
</liferay-portlet:resourceURL>
<liferay-portlet:resourceURL id="addUpdateCodesTarif" var="addUpdateCodesTarifURL">
	<liferay-portlet:param name="action" value="addEditEmployeeProduct" />
</liferay-portlet:resourceURL>
<liferay-portlet:renderURL portletName="15" windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="selectDLURL">
    <liferay-portlet:param name="struts_action" value="/journal/select_document_library" />
    <liferay-portlet:param name="currentURL" value="<%= PortalUtil.getCurrentURL(renderRequest)%>" />
    <liferay-portlet:param name="groupId" value="${globalScopeGroupId }" /> 
</liferay-portlet:renderURL>
<liferay-portlet:renderURL var="reloadEmpProductURL">
	<liferay-portlet:param name="action" value="addEditEmployeeProduct" />
	<liferay-portlet:param name="employeeProductId" value="${employeeProductVO.employeeProductId}" />
	<liferay-portlet:param name="offerId" value="${offer.offerId}"/>
	<liferay-portlet:param name="formAction" value="Edit" />
	<liferay-portlet:param name="status" value="${employeeProductVO.status}" />
	<liferay-portlet:param name="offerStatus" value="${employeeProductVO.offerStatus}" />
	<liferay-portlet:param name="productStatus" value="${employeeProductVO.productStatus}" />
</liferay-portlet:renderURL>

<!----<style>
.cot_label{
  margin-bottom:22px;
}
.ui-state-hover , .ui-widget-header .ui-state-hover{
  
  background-color:none!important;

 }
 
 
 .ui-state-hover a:hover {
   color:#06c!important;
   text-decoration: none;
 }
 
 .ui-tabs .ui-tabs-nav {
		margin-top: -37.6px;
		padding: .2em .2em 0;
		background: none;
		margin-left: 16px;
		border: none!important;
 } 
 
 .ui-tabs .ui-tabs-nav li.ui-tabs-selected{
  background-color:#4b4b4b!important;
  color:white!important;
 
 }
 

 
  .ui-state-selected a:link {
    color: #ffffff;
}
  </style>----->
  
  
  <style>
    .ui-tabs .ui-tabs-nav {
		margin-top: -37.6px;
		padding: .2em .2em 0;
		background: none;
		margin-left: 16px;
		border: none!important;
 } 
 
 .aui-field-label{
 
    display:inline-block !important;
 }
 
 .cot_label{
       margin-bottom:22px;
}

.ui-tabs .ui-tabs-nav li.ui-tabs-selected { 
       margin-bottom: 0;
	   padding-bottom: 1px;
	   background: #F9B030;
	   
 }
 
 .ui-tabs .ui-tabs-nav li.ui-tabs-selected a{
      cursor:default!important;
 }
 

 
  </style>
<div>
	<div class="taglib-header">
	    <h1 class="header-title">
	           <span>
	                 Produit Salarié
	           </span>
	    
	           <span style="font-size: 15px;">
	           <!-- Change done due to MHM-5466 improvement -->
	                 <c:if test="${not empty employeeProductVO.employeeProductId}">(${employeeProductVO.employeeProductId} - 
		                 <c:if test="${employeeProductVO.employeeProductType eq 'OPTION'}">Option</c:if>
		                 <c:if test="${employeeProductVO.employeeProductType eq 'BASE'}">Base</c:if>
		                 <c:if test="${employeeProductVO.employeeProductType eq 'LEVEL'}">Niveau</c:if>)
	                 </c:if>
	           </span>
	    </h1>
    </div>
	<h3>Produit Entreprise associé - ${employeeProductVO.enterpriseProductId}</h3>
	<c:if test="${employeeProductVO.employeeProductType eq 'OPTION'}">
		<h3>Base associée - ${employeeProductVO.associatedBaseId}</h3>
	</c:if> 
	<h3>Type de Régime - 
	<c:if test="${employeeProductVO.regime eq 'RG'}">Régime Général</c:if>
	<c:if test="${employeeProductVO.regime eq 'RL'}">Régime Local</c:if>
	</h3><br /> 
</div>

<form action="<%=addURL%>" name="employeeProductForm" id="employeeProductForm" enctype="multipart/form-data" method="post" onsubmit="return validateFormData();">
	<input type="hidden" id="offerId" name="offerId" value="${offer.offerId}" />
	<input type="hidden" id="folderPath" name="folderPath" value="<%=renderRequest.getContextPath()%>"/>
	<input type="hidden" id="priceDetailsURL" name="priceDetailsURL" value="<%=populatePriceDetailsURL%>"/>
	<input type="hidden" id="employeeProductId" name="employeeProductId" value="${employeeProductVO.employeeProductId}" />
	<input type="hidden" id="selectedEmpProdPeriodId" name="selectedEmpProdPeriodId" value="${employeeProductVO.employeeProductPeriodId}" />
	<input type="hidden" name="enterpriseProductId" id="enterpriseProductId" value="${employeeProductVO.enterpriseProductId}"> 
	<input type="hidden" name="regime" id="regime" value="${employeeProductVO.regime}">
	<input type="hidden" name="situationId" id="situationId" value="${employeeProductVO.situationId}">
	<input type="hidden" name="situationName" id="situationName" value="${employeeProductVO.situationName}">
	<input type="hidden" id="priceType" name="priceType" value="${employeeProductVO.priceType}" />
	<input type="hidden" id="employeeProductType" name="employeeProductType" value="${employeeProductVO.employeeProductType}" />
	<input type="hidden" name="structureId" id="structureId" value="${employeeProductVO.structureId}">
	<input type="hidden" id="formAction" name="formAction" value="${employeeProductVO.formAction}" />
	<input type="hidden" id="zone1" name="zone1" value="${employeeProductVO.zone1}" />
	<input type="hidden" id="zone2" name="zone2" value="${employeeProductVO.zone2}" />
	<input type="hidden" id="zone3" name="zone3" value="${employeeProductVO.zone3}" />
	<input type="hidden" id="trancheAgeSelected" name="trancheAgeSelected" value="${trancheAgeSelected}" />
	
	<input type="hidden" id="deletedDocumentIds" name="deletedDocumentIds" />
	<input type="hidden" id="offerStatus" name="offerStatus" value="${employeeProductVO.offerStatus}" />
	<input type="hidden" id="productStatus" name="productStatus" value="${employeeProductVO.productStatus}" />
	<input type="hidden" id="status" name="status" value="${employeeProductVO.status}" />
	<input type="hidden" id="formFinalStateChanged" name="formFinalStateChanged" value="false" />
	<input type="hidden" id="1lastRadio" name="1lastRadio" />
	<input type="hidden" id="2lastRadio" name="2lastRadio" />
	<input type="hidden" id="3lastRadio" name="3lastRadio" />
	<input type="hidden" id="4lastRadio" name="4lastRadio" />
	<input type="hidden" id="1lastRadioPriceType" name="1lastRadioPriceType" />
	<input type="hidden" id="2lastRadioPriceType" name="2lastRadioPriceType" />
	<input type="hidden" id="3lastRadioPriceType" name="3lastRadioPriceType" />
	<input type="hidden" id="4lastRadioPriceType" name="4lastRadioPriceType" />
	
	<input type="hidden" id="addDateCodeTarifId" name="addDateCodeTarifId" />
	<input type="hidden" id="addDateZoneId" name="addDateZoneId" />
   <!-- hidden paramets for control -->
   <input type="hidden" name="documentListCount" id="documentListCount" value="1" >
   <input type="hidden" name="warrantyCount" id="warrantyCount" value="${fn:length(detailsVOs)}" >
   	
   	<!-- static rich text areas -->
   	
<div id="baseFormDiv" style="display: block;">
	<span class="aui-field-content" style="display: block; margin-bottom: 15px; line-height: 25px;">
		<label class="font-bold aui-field-label" style="width:144px;">Nom :</label>
		<span class="aui-field-element ">
			<input type="text" name="epName" id="epName" value="${employeeProductVO.name}" />
		</span>
			<span class="epname-error-msg form_error" style="display: none">Remplir les champs obligatoires</span>
	</span>
	<span class="aui-field-content">
		<label class="font-bold aui-field-label">
			Code Produit Technique :
		</label>
		<span class="aui-field-element ">
			<input type="text" name="codeProduitTechnique" id="codeProduitTechnique" value="${employeeProductVO.codeProduitTechnique}" /> 
		</span>
	</span>
	<div style="color: red; font-size: 12px;display: none;" id="codeProduitErrorMsg">Ce code produit technique est déjà associé à un Produit Salarié</div>
</div>

	<div id="baseDetailsDiv" style="display: none; margin-top: 20px;">
		<!-- Change done due to MHM-5466 improvement -->
		<div style="float:right;margin-top:-174px">
			<label style="float: left;width: 100%;">Status : <b>${employeeProductVO.status}</b></label>
			<c:choose>
			    <c:when test="${employeeProductVO.employeeProductType eq 'BASE'}">
				</c:when>
				<c:otherwise>
					<div>
						<label style="float:left; position:relative; top:9px; text-align: left; margin: 0px; display: inline-block !important; width: auto;">Position :</label>
						<span class="aui-field-element ">
							<input type="text" class="allow-digit" name="position" id="position" 
							<c:choose>
							    <c:when test="${employeeProductVO.position == 0}">
							        value=""
							    </c:when>
							    <c:otherwise>
							        value="${employeeProductVO.position }"
							    </c:otherwise>
							</c:choose> 
							style="margin-left:5px;margin-top:5px;" maxlength='2' size='2'/>
						</span>
					</div>	
				</c:otherwise>		
			</c:choose>
		</div>
		<div class="cot_label"><b>Cotisations :</b></div>
		<br />
		<div id="tabsMainDiv">
			<c:if test="${employeeProductVO.regime eq 'RG'}">
				<div id="tabs">
					<ul>
						<c:if test="${employeeProductVO.zone1}">
						   <li><a href="#tabs-1" onclick="tabClickMethod(1);"><%=AniConstants.ZONE1 %></a></li>
						</c:if>
						
						 <c:if test="${employeeProductVO.zone2}">
						   <li><a href="#tabs-2" onclick="tabClickMethod(2);"><%=AniConstants.ZONE2 %></a></li>
						 </c:if>
						 <c:if test="${employeeProductVO.zone3}">
						    <li><a href="#tabs-3" onclick="tabClickMethod(3);"><%=AniConstants.ZONE3 %></a></li>
						 </c:if>
					</ul>
					<c:if test="${employeeProductVO.zone1}">
						<div id="tabs-1">
							<c:if test="${not empty employeeProductVO.tranchesAges}">			
								<div id="trancheAgeDiv1">
									<label class="font-bold">Tranche d'âge :</label>
									<select id="trancheAgeZone1" onchange="trancheAgeChangeMethod('<%=reloadEmpProductURL.toString()%>','<%=AniConstants.ZONE1 %>',this.value);">
										<c:forEach items="${employeeProductVO.tranchesAges}" var="item">
											<option value="${item}" ${item == trancheAgeSelected ? 'selected' : ''}>${item}</option>
										</c:forEach>
									</select>
									<br><br>
								</div>
							</c:if>
							
							<div id="zoneCodeTerifDiv1" class="results-grid aui-searchcontainer-content" style="border:0px;">
								<table border="0" style="width: 100%;" class="taglib-search-iterator">
									<tr class="portlet-section-header results-header"><th colspan="3">Assurés</th></tr>
									<tr class="results-row portlet-section-alternate-hover">
										<td>Codes Tarif</td>
										<td>
											<input type="text" name="zone1_codesTarif" id="zone1_codesTarif" value="${codesTarifVO.zone1CodesTarif}" />
											<input type="hidden" name="zone1codesTarifId" id="zone1codesTarifId" value="${codesTarifVO.zone1CodeTerifId}" />
										</td>
										<td>&nbsp;<img alt="Périodes de valorisation" src="<%=renderRequest.getContextPath()%>/img/icons/periods.png" onclick="addUpdateCodesTarif('${employeeProductVO.employeeProductId}',1,'<%=addUpdateCodesTarifURL%>');" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<c:set var="enChecked1" value="checked" />
										<c:set var="pmssChecked1" value="" />
										<c:if test="${codesTarifVO.zone1PriceType eq 'PMSS'}">
											<c:set var="enChecked1" value="" />
											<c:set var="pmssChecked1" value="checked" />									
										</c:if>
										<input type="radio" name="zonePriceType1" id="enRadio1" onclick="confirmPriceTypeChange();" value="EN" ${enChecked1}>&nbsp; &euro; &nbsp;
										<input type="radio" name="zonePriceType1" id="pmssRadio1" onclick="confirmPriceTypeChange();" value="PMSS" ${pmssChecked1}>&nbsp; %
										</td></tr>
									<tr class="results-row portlet-section-alternate-hover"><td colspan="3">${employeeProductVO.situationName}</td></tr>
								</table>
							</div>
							<br />
							<div id="1outerDateDiv">
								<label class="font-bold">Période de Valorisation :</label><br />
								<table border="0" style="width: 100%;" id="1dateRangeTable" class="taglib-search-iterator">
									<tr class="portlet-section-header results-header">
										<th style="width: 20px;">&nbsp;</th>
										<th style="width: 100px;">Date de début</th>
										<th style="width: 100px;">Date de fin</th>
										<th>Taxe</th>
										<th></th>
										<th></th>
										
									</tr>
									<c:forEach var="zone1Period" items="${codesTarifVO.zone1PeriodList}">
										<tr id="tr_${zone1Period.employeeProductPeriodId}" class="results-row portlet-section-alternate-hover">
											<td style="width: 20px" nowrap="nowrap" align="center"><input id="${zone1Period.employeeProductPeriodId}" type="radio" name="1dateRadio" value="${zone1Period.employeeProductPeriodId}" onclick="confirmPriceChange();" /></td>
											<td id="td1_${zone1Period.employeeProductPeriodId}">${zone1Period.startDate}</td>
											<td id="td2_${zone1Period.employeeProductPeriodId}">${zone1Period.endDate}</td>
											<td id="td3_${zone1Period.employeeProductPeriodId}">${zone1Period.taxe}</td>
											<td style="width: 20px" nowrap="nowrap"><img alt="Edit Icon" src="<%=renderRequest.getContextPath()%>/img/icons/edit.png" onclick="editDateRange(${zone1Period.employeeProductPeriodId});" /></td>
											<td style="width: 20px" nowrap="nowrap"><img alt="Delete Icon" class="1deleteDateButton" src="<%=renderRequest.getContextPath()%>/img/icons/ico-delete.png" id="addDate" onclick="deleteDateRange(${zone1Period.employeeProductPeriodId},1);" /></td>
											
										</tr>
									</c:forEach>
								</table>
								<img alt="Ajouter une période" title="Ajouter une période" src="<%=renderRequest.getContextPath()%>/img/icons/add.png" onclick="javascript:addDateRangePanel(1);" /><br /> 
							</div>
							<br /> 
							<%-- Price Table Panel  --%>
							<div id="1zonePriceDiv" style="display: none;">
		 						<div id="table-scroll">
									<table border="1" style="width: 100%; height:150px;overflow:auto;" class="taglib-search-iterator">
										<thead>
											<tr class="portlet-section-header results-header"><th style="width:235px;">Parts</th><th>Part Employeur</th><th>Part Adhérent</th></tr>
										</thead>
										<tbody>
											<c:if test="${empty agesStrutctureLabel }">
												<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelOne }</td>
												<td><input class="priceTextClass" type="text" name="zone1_aduEmp" id="zone1_aduEmp" style="width:110px;" /></td>
												<td><input class="priceTextClass" type="text" name="zone1_aduAdh" id="zone1_aduAdh" style="width:110px;" /></td></tr>
												<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelTwo }</td>
												<td><input class="priceTextClass" type="text" name="zone1_enfEmp" id="zone1_enfEmp" style="width:110px;" /></td>
												<td><input class="priceTextClass" type="text" name="zone1_enfAdh" id="zone1_enfAdh" style="width:110px;" /></td></tr>
												<c:if test="${not empty structureLabelThree }">
													<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelThree }</td>
													<td><input class="priceTextClass" type="text" name="zone1_conEmp" id="zone1_conEmp" style="width:110px;" /></td>
													<td><input class="priceTextClass" type="text" name="zone1_conAdh" id="zone1_conAdh" style="width:110px;" /></td></tr>
												</c:if>
											</c:if>
											<c:if test="${not empty agesStrutctureLabel }">
												<c:forEach items="${agesStrutctureLabel}" var="age">
													<tr class="results-row portlet-section-alternate-hover">
														<td>${age.value}</td>
														<td><input class="priceTextClass" type="text" name="zone1_${age.key}_Emp" id="zone1_${age.key}_Emp" style="width:110px;" /></td>
														<td><input class="priceTextClass" type="text" name="zone1_${age.key}_Adh" id="zone1_${age.key}_Adh" style="width:110px;" /></td></tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${employeeProductVO.zone2}">
						<div id="tabs-2">
							<c:if test="${not empty employeeProductVO.tranchesAges}">	
								<div id="trancheAgeDiv2">
									<label class="font-bold">Tranche d'âge :</label>
									<select id="trancheAgeZone2" onchange="trancheAgeChangeMethod('<%=reloadEmpProductURL.toString()%>','<%=AniConstants.ZONE2 %>',this.value);">
										<c:forEach items="${employeeProductVO.tranchesAges}" var="item">
											<option value="${item}" ${item == trancheAgeSelected ? 'selected' : ''}>${item}</option>
										</c:forEach>
									</select>
									<br><br>
								</div>
							</c:if>
							<div id="zoneCodeTerifDiv2">
								<table border="0" style="width: 100%;" class="taglib-search-iterator">
									<tr class="portlet-section-header results-header"><th colspan="3">Assurés</th></tr>
									<tr class="results-row portlet-section-alternate-hover"><td>Codes Tarif</td>
									<td>
										<input type="text" name="zone2_codesTarif" id="zone2_codesTarif" value="${codesTarifVO.zone2CodesTarif}" />
										<input type="hidden" name="zone2codesTarifId" id="zone2codesTarifId" value="${codesTarifVO.zone2CodeTerifId}" />
									</td>
									<td>&nbsp;<img alt="Périodes de valorisation" src="<%=renderRequest.getContextPath()%>/img/icons/periods.png" onclick="addUpdateCodesTarif('${employeeProductVO.employeeProductId}',2,'<%=addUpdateCodesTarifURL%>');" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<c:set var="enChecked2" value="checked" />
									<c:set var="pmssChecked2" value="" />
									<c:if test="${codesTarifVO.zone2PriceType eq 'PMSS'}">
										<c:set var="enChecked2" value="" />
										<c:set var="pmssChecked2" value="checked" />									
									</c:if>
									<input type="radio" name="zonePriceType2" id="enRadio2" onclick="confirmPriceTypeChange();" value="EN" ${enChecked2}>&nbsp; &euro; &nbsp;
									<input type="radio" name="zonePriceType2" id="pmssRadio2" onclick="confirmPriceTypeChange();" value="PMSS" ${pmssChecked2}>&nbsp; %
									</td></tr>
									<tr class="results-row portlet-section-alternate-hover"><td colspan="3">${employeeProductVO.situationName}</td></tr>
								</table>
							</div>
							<br />
							<div id="2outerDateDiv">
								<label class="font-bold">Période de Valorisation :</label><br />
								<table border="0" style="width: 100%" id="2dateRangeTable" class="taglib-search-iterator">
									<tr class="portlet-section-header results-header">
										<th style="width: 20px;">&nbsp;</th>
										<th style="width: 100px;">Date de début</th>
										<th style="width: 100px;">Date de fin</th>
										<th>Taxe</th>
										<th></th>
										<th></th>
										
									</tr>
									<c:forEach var="zone2Period" items="${codesTarifVO.zone2PeriodList}">
										<tr id="tr_${zone2Period.employeeProductPeriodId}" class="results-row portlet-section-alternate-hover">
											<td style="width: 20px" nowrap="nowrap" align="center"><input id="${zone2Period.employeeProductPeriodId}" type="radio" name="2dateRadio" value="${zone2Period.employeeProductPeriodId}" onclick="confirmPriceChange();" /></td>
											<td id="td1_${zone2Period.employeeProductPeriodId}">${zone2Period.startDate}</td>
											<td id="td2_${zone2Period.employeeProductPeriodId}">${zone2Period.endDate}</td>
											<td id="td3_${zone2Period.employeeProductPeriodId}">${zone2Period.taxe}</td>
											<td style="width: 20px" nowrap="nowrap"><img alt="Edit Icon" src="<%=renderRequest.getContextPath()%>/img/icons/edit.png" onclick="editDateRange(${zone2Period.employeeProductPeriodId});" /></td>
											<td style="width: 20px" nowrap="nowrap"><img alt="Delete Icon" class="2deleteDateButton" src="<%=renderRequest.getContextPath()%>/img/icons/ico-delete.png" onclick="deleteDateRange(${zone2Period.employeeProductPeriodId},2);" /></td>
											
										</tr>
									</c:forEach>
								</table>
								<img alt="Ajouter une période" title="Ajouter une période" src="<%=renderRequest.getContextPath()%>/img/icons/add.png" id="addDate" onclick="javascript:addDateRangePanel(2);" /><br /> 
							</div>
							<br />
							<div id="2zonePriceDiv" style="display: none;">
								<table border="0" style="width: 100%;" class="taglib-search-iterator">
									<tr class="portlet-section-header results-header"><th style="width:235px;">Parts</th><th>Part Employeur</th><th>Part Adhérent</th></tr>
									<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelOne }</td>
									<td><input class="priceTextClass" type="text" name="zone2_aduEmp" id="zone2_aduEmp" style="width:110px;" /></td>
									<td><input class="priceTextClass" type="text" name="zone2_aduAdh" id="zone2_aduAdh" style="width:110px;" /></td></tr>
									<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelTwo }</td>
									<td><input class="priceTextClass" type="text" name="zone2_enfEmp" id="zone2_enfEmp" style="width:110px;" /></td>
									<td><input class="priceTextClass" type="text" name="zone2_enfAdh" id="zone2_enfAdh" style="width:110px;" /></td></tr>
									<c:if test="${not empty structureLabelThree }">
										<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelThree }</td>
										<td><input class="priceTextClass" type="text" name="zone2_conEmp" id="zone2_conEmp" style="width:110px;" /></td>
										<td><input class="priceTextClass" type="text" name="zone2_conAdh" id="zone2_conAdh" style="width:110px;" /></td></tr>
									</c:if>
								</table>
							</div>
						</div>
					</c:if>
					<c:if test="${employeeProductVO.zone3}">
						<div id="tabs-3">
							<c:if test="${not empty employeeProductVO.tranchesAges}">	
								<div id="trancheAgeDiv3">
									<label class="font-bold">Tranche d'âge :</label>
									<select id="trancheAgeZone3" onchange="trancheAgeChangeMethod('<%=reloadEmpProductURL.toString()%>','<%=AniConstants.ZONE3 %>',this.value);">
										<c:forEach items="${employeeProductVO.tranchesAges}" var="item">
											<option value="${item}" ${item == trancheAgeSelected ? 'selected' : ''}>${item}</option>
										</c:forEach>
									</select>
									<br><br>
								</div>
							</c:if>
							<div id="zoneCodeTerifDiv3">
								<table border="0" style="width: 100%;" class="taglib-search-iterator">
									<tr class="portlet-section-header results-header"><th colspan="3">Assurés</th></tr>
									<tr class="results-row portlet-section-alternate-hover"><td>Codes Tarif</td>
									<td>
										<input type="text" name="zone3_codesTarif" id="zone3_codesTarif" value="${codesTarifVO.zone3CodesTarif}" />
										<input type="hidden" name="zone3codesTarifId" id="zone3codesTarifId" value="${codesTarifVO.zone3CodeTerifId}" />
									</td>
									<td>&nbsp;<img alt="Périodes de valorisation" src="<%=renderRequest.getContextPath()%>/img/icons/periods.png" onclick="addUpdateCodesTarif('${employeeProductVO.employeeProductId}',3,'<%=addUpdateCodesTarifURL%>');" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<c:set var="enChecked3" value="checked" />
									<c:set var="pmssChecked3" value="" />
									<c:if test="${codesTarifVO.zone3PriceType eq 'PMSS'}">
										<c:set var="enChecked3" value="" />
										<c:set var="pmssChecked3" value="checked" />									
									</c:if>
									<input type="radio" name="zonePriceType3" id="enRadio3" onclick="confirmPriceTypeChange();" value="EN" ${enChecked3}>&nbsp; &euro; &nbsp;
									<input type="radio" name="zonePriceType3" id="pmssRadio3" onclick="confirmPriceTypeChange();" value="PMSS" ${pmssChecked3}>&nbsp; %
									</td></tr>
									<tr class="results-row portlet-section-alternate-hover"><td colspan="3">${employeeProductVO.situationName}</td></tr>
								</table>
							</div>
							<br/>
							<div id="3outerDateDiv">
								<label class="font-bold">Période de Valorisation :</label><br/>
								<table border="0" style="width: 100%;" id="3dateRangeTable" class="taglib-search-iterator">
									<tr class="portlet-section-header results-header">
										<th style="width: 20px;">&nbsp;</th>
										<th style="width: 100px;">Date de début</th>
										<th style="width: 100px;">Date de fin</th>
										<th>Taxe</th>
										<th></th>
										<th></th>
										
									</tr>
									<c:forEach var="zone3Period" items="${codesTarifVO.zone3PeriodList}">
										<tr id="tr_${zone3Period.employeeProductPeriodId}" class="results-row portlet-section-alternate-hover">
											<td style="width: 20px" nowrap="nowrap" align="center"><input id="${zone3Period.employeeProductPeriodId}" type="radio" name="3dateRadio" value="${zone3Period.employeeProductPeriodId}" onclick="confirmPriceChange();" /></td>
											<td id="td1_${zone3Period.employeeProductPeriodId}">${zone3Period.startDate}</td>
											<td id="td2_${zone3Period.employeeProductPeriodId}">${zone3Period.endDate}</td>
											<td id="td3_${zone3Period.employeeProductPeriodId}">${zone3Period.taxe}</td>
											<td style="width: 20px" nowrap="nowrap"><img alt="Edit Icon" src="<%=renderRequest.getContextPath()%>/img/icons/edit.png" onclick="editDateRange(${zone3Period.employeeProductPeriodId});" /></td>
											<td style="width: 20px" nowrap="nowrap"><img alt="Delete Icon" class="3deleteDateButton" src="<%=renderRequest.getContextPath()%>/img/icons/ico-delete.png" id="addDate" onclick="deleteDateRange(${zone3Period.employeeProductPeriodId},3);" /></td>
											
										</tr>
									</c:forEach>
								</table>
								<img alt="Ajouter une période" title="Ajouter une période" src="<%=renderRequest.getContextPath()%>/img/icons/add.png" onclick="javascript:addDateRangePanel(3);" /><br /> 
							</div>
							<br/>
							<div id="3zonePriceDiv" style="display: none;">
								<table border="0" style="width: 100%;" class="taglib-search-iterator">
									<tr class="portlet-section-header results-header"><th style="width:235px;">Parts</th><th>Part Employeur</th><td>Part Adhérent</th></tr>
									<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelOne }</td>
									<td><input class="priceTextClass" type="text" name="zone3_aduEmp" id="zone3_aduEmp" style="width:110px;" /></td>
									<td><input class="priceTextClass" type="text" name="zone3_aduAdh" id="zone3_aduAdh" style="width:110px;" /></td></tr>
									<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelTwo }</td>
									<td><input class="priceTextClass" type="text" name="zone3_enfEmp" id="zone3_enfEmp" style="width:110px;" /></td>
									<td><input class="priceTextClass" type="text" name="zone3_enfAdh" id="zone3_enfAdh" style="width:110px;" /></td></tr>
									<c:if test="${not empty structureLabelThree }">
										<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelThree }</td>
										<td><input class="priceTextClass" type="text" name="zone3_conEmp" id="zone3_conEmp" style="width:110px;" /></td>
										<td><input class="priceTextClass" type="text" name="zone3_conAdh" id="zone3_conAdh" style="width:110px;" /></td></tr>
									</c:if>
								</table>
							</div>
						</div>
					</c:if>
				</div>
				<!-- tabs div ends here -->
			</c:if>
			<c:if test="${employeeProductVO.regime eq 'RL'}">
				<div id="tabs-4" class="ui-tabs ui-widget ui-widget-content ui-corner-all" style="padding: 20px;"> <%-- tab 4 --%>
					<c:if test="${not empty employeeProductVO.tranchesAges}">	
						<div id="trancheAgeDiv4">
							<label class="font-bold">Tranche d'âge :</label>
							<select id="trancheAgeZone4" onchange="trancheAgeChangeMethod('<%=reloadEmpProductURL.toString()%>','<%=AniConstants.ZONE4 %>',this.value);">
								<c:forEach items="${employeeProductVO.tranchesAges}" var="item">
									<option value="${item}" ${item == trancheAgeSelected ? 'selected' : ''}>${item}</option>
								</c:forEach>
							</select>
							<br><br>
						</div>
					</c:if>
					<div id="zoneCodeTerifDiv4">
						<table border="0" style="width: 100%;" class="taglib-search-iterator">
							<tr class="portlet-section-header results-header"><th colspan="3">Assurés</th></tr>
							<tr class="results-row portlet-section-alternate-hover"><td>Codes Tarif</td>
							<td>
								<input type="text" name="zone4_codesTarif" id="zone4_codesTarif" value="${codesTarifVO.zone4CodesTarif}" />
								<input type="hidden" name="zone4codesTarifId" id="zone4codesTarifId" value="${codesTarifVO.zone4CodeTerifId}" />
							</td>
							<td>&nbsp;<img alt="Périodes de valorisation" src="<%=renderRequest.getContextPath()%>/img/icons/periods.png" onclick="addUpdateCodesTarif('${employeeProductVO.employeeProductId}',4,'<%=addUpdateCodesTarifURL%>');" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:set var="enChecked4" value="checked" />
							<c:set var="pmssChecked4" value="" />
							<c:if test="${codesTarifVO.zone4PriceType eq 'PMSS'}">
								<c:set var="enChecked4" value="" />
								<c:set var="pmssChecked4" value="checked" />									
							</c:if>
							<input type="radio" name="zonePriceType4" id="enRadio4" onclick="confirmPriceTypeChange();" value="EN" ${enChecked4}>&nbsp; &euro; &nbsp;
							<input type="radio" name="zonePriceType4" id="pmssRadio4" onclick="confirmPriceTypeChange();" value="PMSS" ${pmssChecked4}>&nbsp; %
							<tr class="results-row portlet-section-alternate-hover"><td colspan="3">${employeeProductVO.situationName}</td></tr>
						</table>
					</div>
					<br />
					<div id="4outerDateDiv">
						<label class="font-bold">Période de Valorisation :</label><br />
						<table border="0" style="width: 100%;" id="4dateRangeTable" class="taglib-search-iterator">
							<tr class="portlet-section-header results-header">
								<th style="width: 20px;">&nbsp;</th>
								<th style="width: 100px;">Date de début</th>
								<th style="width: 100px;">Date de fin</th>
								<th>Taxe</th>
								<th></th>
								<th></th>
								
							</tr>
							<c:forEach var="zone4Period" items="${codesTarifVO.zone4PeriodList}">
								<tr id="tr_${zone4Period.employeeProductPeriodId}" class="results-row portlet-section-alternate-hover">
									<td style="width: 20px" nowrap="nowrap" align="center"><input id="${zone4Period.employeeProductPeriodId}" type="radio" name="4dateRadio" value="${zone4Period.employeeProductPeriodId}" onclick="confirmPriceChange();" /></td>
									<td id="td1_${zone4Period.employeeProductPeriodId}">${zone4Period.startDate}</td>
									<td id="td2_${zone4Period.employeeProductPeriodId}">${zone4Period.endDate}</td>
									<td id="td3_${zone4Period.employeeProductPeriodId}">${zone4Period.taxe}</td>
									<td style="width: 20px" nowrap="nowrap"><img alt="Edit Icon" src="<%=renderRequest.getContextPath()%>/img/icons/edit.png" onclick="editDateRange(${zone4Period.employeeProductPeriodId});" /></td>
									<td style="width: 20px" nowrap="nowrap"><img alt="Delete Icon" class="4deleteDateButton" src="<%=renderRequest.getContextPath()%>/img/icons/ico-delete.png" id="addDate" onclick="deleteDateRange(${zone4Period.employeeProductPeriodId},4);" /></td>
									
								</tr>
							</c:forEach>
						</table>
						<img alt="Ajouter une période" title="Ajouter une période" src="<%=renderRequest.getContextPath()%>/img/icons/add.png" onclick="javascript:addDateRangePanel(4);" /><br /> 
					</div>
					<br />
					<div id="4zonePriceDiv" style="display: none;">
						<table border="0" style="width: 100%;" class="taglib-search-iterator">
							<tr class="portlet-section-header results-header"><th>Parts</th><th style="width:235px;">Part Employeur</th><th>Part Adhérent</th></tr>
							<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelOne }</td>
							<td><input class="priceTextClass" type="text" name="zone4_aduEmp" id="zone4_aduEmp" style="width:110px;" /></td>
							<td><input class="priceTextClass" type="text" name="zone4_aduAdh" id="zone4_aduAdh" style="width:110px;" /></td></tr>
							<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelTwo }</td>
							<td><input class="priceTextClass" type="text" name="zone4_enfEmp" id="zone4_enfEmp" style="width:110px;" /></td>
							<td><input class="priceTextClass" type="text" name="zone4_enfAdh" id="zone4_enfAdh" style="width:110px;" /></td></tr>
							<c:if test="${not empty structureLabelThree }">
								<tr class="results-row portlet-section-alternate-hover"><td>${structureLabelThree }</td>
								<td><input class="priceTextClass" type="text" name="zone4_conEmp" id="zone4_conEmp" style="width:110px;" /></td>
								<td><input class="priceTextClass" type="text" name="zone4_conAdh" id="zone4_conAdh" style="width:110px;" /></td></tr>
							</c:if>
						</table>
					</div>
				</div> <%-- tab 4 --%>
			</c:if>
		</div>
		<br />
		<%-- Date Range Panel  --%>
		<div id="dateRangeDiv" style="display: none;">
		
			<h2 style="margin: 0px;">Ajouter une période de valorisation</h2>
	
			<input type="hidden" id="dateFlag" name="dateFlag" value="Add" />
			<input type="hidden" id="editDateId" name="editDateId" value="" />
			<input type="hidden" id="startDate" name="startDate" value="" /> 
			<input type="hidden" id="endDate" name="endDate" value="" /> 
			<span class="date-error-msg form_error" style="display: none;margin-left: 0px;position: relative;float: left;">Remplir les champs obligatoires</span><br/>
			<span class="date-overlap-error-msg form_error" style="display: none;margin-left: 0px;position: relative;float: left; width: 220px;">Les périodes ne doivent pas se chevaucher</span>	
			<table>
				<tr><td>Date de début: </td><td><input type="text" class='datepicker1' id='sDate' /></td></tr>
				<tr><td>Date de fin: </td><td><input type="text" class='datepicker2' id='eDate' /></td></tr>
				<tr><td>Taxe: </td><td><input type="text" id="taxe" size="20" style="margin-top: 10px;"></td></tr>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td><input type="button" value="Sauvegarder" onclick="submitDateRange('<%=addDateRangeURL%>',$('#dateFlag').val());" /></td>
					<td>&nbsp;<input type="button" value="Annuler" onclick="cancelDateProcess();" /></td>
				</tr>
			</table>
		</div>
		
		<div>
			
		</div>
	</div> 
	<br/><br/>
	<div id="ep2formDiv" style="display: none;">
		<div id="levelOfprotection">
			<p><b>Niveau de protection :</b></p>
			<p> 
				<div style="float: left; margin-right: 15px;">
	            	<textarea name="levelOfProtectionDocument" id="levelOfProtectionDocument" style="float: left; width: 500px; height: 30px;">${employeeProductVO.levelOfProtectionDocument}</textarea>
					<div style="color: red; font-size: 12px;">Taille de l'image : 257x125 px</div>
	            </div>
	            <div>
	        	    <input type="button" onclick="callDLUploader('levelOfProtectionDocument')" value="Choisir" />
	            </div>
		</div>
	<br />
	<div id="productSummary"> 
		<aui:field-wrapper label="Résumé du produit salarié :">
			 <liferay-ui:input-editor name="productSummaryEditorText" initMethod="initProductSummary" />  
		</aui:field-wrapper>
		<aui:script>
			function <portlet:namespace />initProductSummary() {
				return "<%= UnicodeFormatter.toString(epVO.getProductSummaryEditorText()) %>";
			}
		</aui:script>
		<div class="form_error display-none" id="err_summary" style="display: none">Vous avez atteint la limite du nombre de caractères</div>
		<div style="color: red; font-size: 12px;margin-top: 4px;">Maximum 4000 caractères</div>
	</div>
	<br />

	<div class="group_border">
		<img src="<%=renderRequest.getContextPath()%>/img/icons/add.png" title="Ajouter" alt="Ajouter" class="pointer float-right" style=" margin-bottom:4px;" onclick="return documentUpload()" />
        	<p><b>Documents :</b>
	<div id="existingDocumentsDiv">
		<table border="0" style="width: 100%;" id="documentsTable" class="taglib-search-iterator">
			<c:forEach var="productDocuments" items="${employeeProductVO.productDocumentsVOList}" >
				<tr id="existDocument_${productDocuments.documentDetailsId}" class="results-row portlet-section-alternate-hover">
					<td style="width: 500px;"><b>${productDocuments.documentName}</b></td>
					<td style="width: 100px;"><img alt="Delete Document Icon" src="<%=renderRequest.getContextPath()%>/img/icons/ico-delete.png" id="deleteDocument" onclick="deleteDocumentMethod(${productDocuments.documentDetailsId});" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>

		<table border = "0" style="width:100%;">
		 <tr> <td>	
		 <div id="documentsList">
			<table id="1" cellspcing="4" style="margin: 0px; width: 100%; padding: 5px 5px 15px 5px;">
				<tr style="height:30px;">			   
					<td width="60px">
						<p>Titre :</p>
					</td>
					<td>
						<input name="document1" style="width:238px;">
					</td>
					  <td> </td>
				</tr>
				<tr style="height:35px;">			
					<td>
						<p>Fichier : </p>
					</td>
					<td width="10px" nowrap="nowrap">
						<input name='file1' type='file' onchange="check_fileType(this)" id="file1" />
					</td>
					<td> <img src="<%=renderRequest.getContextPath()%>/img/icons/ico-delete.png" onclick="deleteDocumentList(1);"/></td>
				</tr>
				<tr style="height:30px; display: none;">
				<td> </td>
					<td>
						<input type="checkbox" name="offerFrontEndcheckBox1">Afficher dans le canal de Souscription
					</td>
					<td>
						<input type="checkbox" name="simulatorcheckbox1">Afficher dans le Simulateur
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<hr style="border-top: 1px solid #cccccc; margin: 15px 0px;" />
					</td>
				</tr>
			</table>		
			</div>
			<br/>
			<span class="document-title-error-msg form_error" style="display: none">Remplir les champs obligatoires</span>
			</td></tr>
	   </table>
	</div>
   <br />
    <div id="warrantyDetailsTable">
				<label class="font-bold">Détail des garanties :</label>
				
			    <input type="hidden" name="deleteWarrantyDetailsId" id="deleteWarrantyDetailsId" value="" />
				<table border ="1" id="warrantyTable" class="taglib-search-iterator">
					<tr class="portlet-section-header results-header">
						<th style="width:60px;">Position </th>
						<th>Principales Garanties </th>
						<th style="width:60px;">Jauge </th>
						<th>Détail</th>
						<th>Niveau </th>
						<th style="width: 20px;" nowrap="nowrap">Exemples </th>
						<th style="width: 20px;" nowrap="nowrap"></th>
					 <tr>
					 <c:set value="1" var="warrantyListGlobal" />
					 <c:forEach items="${detailsVOs }" var="wdVO">
					 	<tr id="1${warrantyListGlobal}" class="results-row portlet-section-alternate-hover">
					 		<td>
								<input type='text' class="allow-digit" name="warrantyPositionId${warrantyListGlobal}" id="warrantyPositionId${warrantyListGlobal}" value='${wdVO.warrantyDetails.warrantyPosition }' maxlength='2' size='2'/>					 			
							</td>
					 		<td style="width: 20px;" nowrap="nowrap">
					 			<input type='hidden' id="warrantyDetailsId${warrantyListGlobal}" name='warrantyDetailsId${warrantyListGlobal}' value='${wdVO.warrantyDetails.warrantyDetailsId }' />
					 			<input type='text' name="principalesGaranties${warrantyListGlobal}" value="${wdVO.warrantyDetails.principalesGaranties }" style="width:60px;">
					 		</td>
							<td>
								<select id='warrantydropDown${warrantyListGlobal}' name='warrantydropDown${warrantyListGlobal}'>
									<c:forEach var="i" begin="0" end="5">
										<option value='${i}' 
											<c:if test="${i eq wdVO.warrantyDetails.jauge}">
											selected="selected"
											</c:if>
										>${i}</option>
						            </c:forEach>
						   		</select>
							</td>
							<td>
								<input type='text' name='textArea${warrantyListGlobal}' 
									onfocus='return addRichTextPopup(${warrantyListGlobal});' onclick='addRichTextPopup(${warrantyListGlobal});' style="width:60px;"/>
								<input type='hidden' name='detailsText${warrantyListGlobal}' value="${wdVO.warrantyDetails.details }" style="width:60px;">
							</td>
							<td>
								<input type='text' name='wtextArea${warrantyListGlobal}' 
									onfocus='return addRichTextPopupforNiveau("${warrantyListGlobal}");' onclick='addRichTextPopupforNiveau(${warrantyListGlobal});' style="width:60px;">
								<input type='hidden' name='detailsTextNiveau${warrantyListGlobal}' value="${wdVO.warrantyDetails.niveau }" style="width:60px;">
							</td>
							<td style="width: 20px;" nowrap="nowrap">
								<img src="<%=renderRequest.getContextPath() %>/img/icons/add.png" onclick='return addExample(${warrantyListGlobal});' style="cursor:pointer;"/>
								<input type='hidden' name='selectedExamples${warrantyListGlobal}' value='${wdVO.exampleIdPos }'/>
							</td>
							<td style="width: 20px;" nowrap="nowrap">
								<img src="<%=renderRequest.getContextPath() %>/img/icons/ico-delete.png" onclick='deleteexampleRecord(${warrantyListGlobal});' style="cursor:pointer;"/>
							</td>
						</tr>
						<c:set value="${warrantyListGlobal + 1 }" var="warrantyListGlobal" />
					 </c:forEach>
				</table>
				
				<!-- <p><div class="add_details_warranty" onclick="warrantUpload();" ></div></p> -->
				<input type="button" value="Ajouter" title="Ajouter Exemples" alt="Ajouter Exemples" class="margin-top25 pointer" onclick="return warrantUpload();" />
				   
				   <div id="light" class="white_content box"> 
				   
				   <div id='exmapleTitle'>
					<h4>Choisissez les exemples de remboursement : </h4>
					<table border ="0" id="examplesData" class="taglib-search-iterator">
						<tr class="portlet-section-header results-header">
							<th></th>
							<th style='width:150px;'>Exemples</th>
							<th  style='width:150px;'>Position</th>
						</tr>
						 
					</table>
				</div>		   
				   <div id="popinIdPass" class="float-right margin-top25"></div></div>
				   <div id="fade" class="black_overlay"></div>
				   
				   
				   <div id="light2" class="white_content box"> 		   
				    	
					  <liferay-ui:input-editor 
					    	name="detailsEditor"
					    	editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>" 		  
							toolbarSet="liferay-article"
							initMethod='initEditorDetailsEditor'
							height="300"
							width="500"
							/>			
					
						<aui:script>
							function <portlet:namespace />initEditorDetailsEditor() {
								return "";
							}
						</aui:script>
				    
				   		<div id="popinIdPass2" class="float-right margin-top25"></div>
				   </div>
				   <div id="fade2" class="black_overlay"></div>
				   
				 
				   
		   </div>
	<br />
	 <div id="priceDetails">
		<aui:field-wrapper label="Détail des tarifs :">
			    <liferay-ui:input-editor name="priceDetailseditorText" initMethod="initPriceDetailseditor" />		   
		</aui:field-wrapper>
		<aui:script>
			function <portlet:namespace />initPriceDetailseditor() {
				return "<%= UnicodeFormatter.toString(epVO.getPriceDetailseditorText()) %>";
			}
		</aui:script>
		<div class="form_error display-none" id="err_price_details" style="display: none">Vous avez atteint la limite du nombre de caractères</div>
		<div style="color: red; font-size: 12px;margin-top: 4px;">Maximum 4000 caractères</div>
		<div style="color: red; font-size: 12px;">Pour sélectionner la version courte des détails des tarifs, ajouter le contenu à l'intérieur des balises &lt;version_courte&gt;&lt;/version_courte&gt;</div>
   </div>
   
    <div id="automaticallyIncluded">
		<aui:field-wrapper label="Votre garantie comprend automatiquement :">
			  <liferay-ui:input-editor name="includedEditorText" initMethod="initIncludedEditor" />		
		</aui:field-wrapper>
		<aui:script>
			function <portlet:namespace />initIncludedEditor() {
				return "<%= UnicodeFormatter.toString(epVO.getIncludedEditorText()) %>";
			}
		</aui:script>	
		<div class="form_error display-none" id="err_details" style="display: none">Vous avez atteint la limite du nombre de caractères</div>
		<div style="color: red; font-size: 12px;margin-top: 4px;">Maximum 4000 caractères</div>		 
   </div>
	<br />
</div>
	
<br />
<br />

	<div class="taglib-form-navigator" style="width: 20%;float: left;margin-left: 33px;margin-top: 35px;">
		<%@ include file="/html/ani/container/pane.jsp"%>
	</div>
	<input type="hidden" id="contextPath" name="contextPath" value="<%=renderRequest.getContextPath() %>"  />
</form>

<div class="hos-mat" id="confirmPop" style="display: none; z-index: 9999; position: fixed; top: 50%; left: 50%; margin: -50px 0 0 -100px;">
	
	&Ecirc;tes-vous certain de vouloir supprimer cet (ces) &eacute;l&eacute;ment (s) ?
	
	<span style="float:left; margin-top: 50px">
		<input type="button" value="Annuler" onclick="disablePopup();">
	</span>
	<span style="float:right; margin-top: 50px">
		<input type="button" value="Confirm" onclick='deleteDateMethod("<%=deleteDateRangeURL%>");' />
	</span>
	<input type="hidden" id="deletePopupEPPID"  />
	<input type="hidden" id="deleteDateZoneId" />
</div>
<div class="common-popup" id="confirmPricePop"  style="display: none; z-index: 9999; position: fixed; top: 50%; left: 50%; margin: -50px 0 0 -100px; width: 300px;">
	
	<h3 style="text-align: center;">Changer la période de valorisation implique la perte de données non sauvegardées.</h3>
	
	<span style="float:left; margin-top: 50px">
		<input type="button" value="Annuler" onclick="cancelDisablePricePopup();">
	</span>
	<span style="float:right; margin-top: 50px">
		<input type="button" value="Continuer" onclick="priceChangeDone();" />
	</span>
</div>
<div class="common-popup" id="confirmPriceTypePop"  style="display: none; z-index: 9999; position: fixed; top: 50%; left: 50%; margin: -50px 0 0 -100px; width: 300px;">
	
	<h3 style="text-align: center;">Vous devez sauvegarder cette modification avant de mettre à jour les données du formulaire.</h3>
	
	<span style="float:left; margin-top: 50px">
		<input type="button" value="Annuler" onclick="cancelDisablePriceTypePopup();">
	</span>
	<span style="float:right; margin-top: 50px">
		<input type="button" value="Continuer" onclick="priceTypeChangeDone();" />
	</span>
</div>

<script type="text/javascript" src="<%=renderRequest.getContextPath()%>/js/common/jquery.center.js"></script>
<script type="text/javascript" src="<%=renderRequest.getContextPath()%>/js/ani/offer/offer.js"></script>
<script>

/// Validating the submit button

function validateFormData()
{
	// validate name
	var formActionVal = $('#formAction').val();
	var validName = validateEmpty($("#epName"));
	
	var errRich = false;

	var charLengthPro = window.<portlet:namespace/>productSummaryEditorText.getHTML().length;
	var charLengthDetails = window.<portlet:namespace/>priceDetailseditorText.getHTML().length;
	var charLengthInclude = window.<portlet:namespace/>includedEditorText.getHTML().length;
	
	if(charLengthPro>4000){
		$("#err_summary").show();
		errRich = true;
	} else {
		$("#err_summary").hide();
	}
	if(charLengthDetails>4000){
		$("#err_price_details").show();
		errRich = true;
	} else {
		$("#err_price_details").hide();
	}
	if(charLengthInclude>4000){
		$("#err_details").show();
		errRich = true;
	} else {
		$("#err_details").hide();
	}
	
	if(formActionVal === 'Add')
	{
		if(validName)
		{
			$(".epname-error-msg").hide();
			return true;	
		}else{
			$(".epname-error-msg").show();
			return false;
		}
	}
	else if(formActionVal === 'Edit')
	{
		//check final form state
		checkFormFinalStateMethod();
		
		var namePass = true;
		if(validName)
		{
			$(".epname-error-msg").hide();
			namePass = true;	
		}else{
			$(".epname-error-msg").show();
			namePass = false;
		}
		
		var actionPass = true;
		for(var counter = 1; counter <= documentListGlobal; counter++){		 
			var fname = "file"+counter;
			var dName = "document"+counter;		
			if(typeof $('input[name="'+dName+'"]').val() != "undefined"){
				if($('input[name="'+fname+'"]').val() != ""){
					if($('input[name="'+dName+'"]').val() == ""){
						actionPass = false;
						$('input[name="'+dName+'"]').addClass("error_input");
					}
					else{
						$('input[name="'+dName+'"]').removeClass("error_input");
					}
				}
			} 
		}
		if(actionPass){
			$('.document-title-error-msg').hide();
		}else{
			$('.document-title-error-msg').show();
		}
		if(namePass && actionPass && !errRich)
		{
			return true;	
		}
		else
		{
			return false;	
		}
	}
}

// deleted docoumentDetailsIds
var deletedDcoumentDetailsString = '';
var exampleJSON = '<c:out value="${exampleData}" escapeXml="false"/>';
var exampleJSONData = jQuery.parseJSON(exampleJSON);
/** global params for reading*/
 var documentListGlobal = 2;
 var warrantyListGlobal = parseInt($('#warrantyCount').val());
 var defaultExampleData = $("#examplesData").html();
 
 $(function() {
	 for(var i=1;i<=warrantyListGlobal;i++){
		 setRichText(i);
		 setRichTextNiveau(i);
	 }
	 
	 $(".allow-digit").keypress(function(e) {
			if (!String.fromCharCode(e.which).match(/[0-9 ]/)) {
		    	e.preventDefault();
			}
	   });
 });
 /** document upload set **/
function documentUpload(){	  
	 var processNewRequest = "<table id="+documentListGlobal+" cellspcing='4' style='margin: 0px; width: 100%; padding: 5px 5px 15px 5px;'><tr style='height:30px;'><td width='60px'><p>Titre :</p></td><td><input name='document"+documentListGlobal+"''></td><td>"+
       " </td></tr><tr style='height:35px;'><td><p>Fichier : </p></td><td width='10px' nowrap='nowrap'><input type='file'  onchange='check_fileType(this)' id='file"+documentListGlobal+"'  name='file"+documentListGlobal+"''>"+
				"</td><td><img src='" + $("#contextPath").val() + "/img/icons/ico-delete.png' onclick='return deleteDocumentList("+documentListGlobal+");' /></td></tr><tr style='height:30px; display: none;'><td>"+
       " </td><td><input type='checkbox' name='offerFrontEndcheckBox"+documentListGlobal+"''>Afficher dans l' Offre</td>"+
       "<td><input type='checkbox' name='simulatorcheckbox"+documentListGlobal+"'>Afficher dans le Simulateur</td></tr></table>";
	  $('input[name="documentListCount"]:hidden').val(documentListGlobal);	  
	  documentListGlobal = documentListGlobal + 1;	  
	 $("#documentsList").append(processNewRequest);
}
/**warranty table upload **/
function warrantUpload(){	 
	warrantyListGlobal = warrantyListGlobal + 1;
	var requestedtoAddRecord = "<tr id='1" + warrantyListGlobal + "' class='results-row portlet-section-alternate-hover'>" +
	"<td><input type='text' class='allow-digit' name='warrantyPositionId" + warrantyListGlobal + "' id='warrantyPositionId" + warrantyListGlobal + "' maxlength='2' size='2' /> </td> "+
	"<td style='width: 20px;' nowrap='nowrap'> <input type='hidden' id='warrantyDetailsId" + warrantyListGlobal + "' name='warrantyDetailsId" + warrantyListGlobal + "' value='0' /><input type='text' name='principalesGaranties"+warrantyListGlobal+"' style='width:60px;'> </td> " +
	"<td><select id='warrantydropDown" + warrantyListGlobal + "' name='warrantydropDown" + warrantyListGlobal + "'> "+
		"  <option value='0'>0</option>"+
		 " <option value='1'>1</option>"+
		 " <option value='2'>2</option>"+
		 " <option value='3'>3</option>"+
		 " <option value='4'>4</option>"+
		 " <option value='5'>5</option>"+
		"</select> </td> "
	+ "<td><input type='text' name='textArea"+warrantyListGlobal
	+ "' onfocus='return addRichTextPopup("+warrantyListGlobal+");'" 
	+ " onclick='return addRichTextPopup("+warrantyListGlobal+");' style='width:60px;'>"
	+ "<input type='hidden' name='detailsText"+warrantyListGlobal+"''></td> "
	+ "<td><input type='text' name='wtextArea"
	+ warrantyListGlobal+"' onfocus='return addRichTextPopupforNiveau("+warrantyListGlobal+");'" 
	+ " onclick='return addRichTextPopupforNiveau("+warrantyListGlobal+");' style='width:60px;'>"
	+ "<input type='hidden' name='detailsTextNiveau"+warrantyListGlobal+"''> </td> <td style='width: 20px;' nowrap='nowrap'>"
	+ "<img src='" + $("#folderPath").val() + "/img/icons/add.png' onclick='return addExample(" + warrantyListGlobal + ");'/></div> "
	+ "<input type='hidden' name='selectedExamples"+warrantyListGlobal+"' value=''/></td><td style='width: 20px;' nowrap='nowrap'>"
	+ "<img src='" + $("#folderPath").val() + "/img/icons/ico-delete.png' onclick='return deleteexampleRecord(" + warrantyListGlobal + ");'/></td> </tr>";	 
	
	$('#warrantyCount').val(warrantyListGlobal);
    $("#warrantyTable").append(requestedtoAddRecord);
    
    $(".allow-digit").keypress(function(e) {
		if (!String.fromCharCode(e.which).match(/[0-9 ]/)) {
	    	e.preventDefault();
		}
	});
}


/** Delete warrant record**/

function deleteexampleRecord (id) {
      if($("#deleteWarrantyDetailsId").val()!=''){
   	   $("#deleteWarrantyDetailsId").val($("#deleteWarrantyDetailsId").val() + "," + $("#warrantyDetailsId" + id).val());
      } else {
   	   $("#deleteWarrantyDetailsId").val($("#warrantyDetailsId" + id).val());
      }
      $('#warrantyTable tr#1'+id).html('');
}


/**Add Example **/
function addExample(id){  
	var addedExampleData = defaultExampleData;
	var counter = 1;
	$.each(exampleJSONData, function(idx, rec){
	   
		var processData = "<tr style='height:50px;' class='results-row portlet-section-alternate-hover'> "+
							"<td><input type='hidden' name='examplecheckvalues"+counter+"' id='examplecheckvalues"+counter+"' value='"+rec.name+"'/> "+					
							"<input type='checkbox' name='"+rec.id+"' id='"+rec.id+"' value='"+rec.id+"'/></td> "+
							"<td>"+rec.name+"</td> "+
							"<td><input type='text' name='exampleposition"+counter+"' id='exampleposition"+counter+"' value='' maxlength='2' size='2'/></td> "+
						"</tr> ";
		addedExampleData = addedExampleData+processData;
		counter++;
	
	}); 
	
	$("#examplesData").html(addedExampleData);
	$("#popinIdPass").html('<input type="button" value="Annuler" style="margin-right: 10px;" onclick = "hidePopUp(' + "'light'" + ')" /><input type="button" value="Validez" onclick = "closeExamplePopin('+id+');"> ');	
	setBackgroundPopUp();
	$("#light").center();
	$("#light").show();
	
	/*Read the example parameters already selected*/
	var readParameters = "selectedExamples"+id;
	readParameters =  $("input[name="+readParameters+"]:hidden").val();	

	/* display already selected Data */
	 if(readParameters.length > 0){	

	 	var iData = readParameters.split(",");	 	

	 	for (var i = 0; i < iData.length; i++) {

	 		var idExample = iData[i].split("##");
	 		var populateCounter = 1;

	 		$.each(exampleJSONData, function(idx, rec){

	 			if (rec.id === idExample[0]) {

	 				$("input[value='"+rec.id+"']").prop('checked', true);
	 				 var exampleposition = "exampleposition"+ populateCounter;
	 			 	 $('input[name="'+exampleposition+'"]').val(idExample[1]); 
	 			};

	 			populateCounter++;
	 		});	
	 	}; 	 	 
	 };
	 $("#light input[type='text']").keypress(function(e) {
		if (!String.fromCharCode(e.which).match(/[0-9 ]/)) {
	    	e.preventDefault();
		}
	});
}

/**closing example **/
function closeExamplePopin(id){	
	var selectedExamplesRead = "";
	var selectedExamplesShow = "";
	var counter = 1;
	var closeValidation = true;
	$.each(exampleJSONData, function(idx, rec){
		if(	$('#'+rec.id).attr('checked')?true:false){
			    var exampleposition = "exampleposition"+counter;

			    /* validation call on 0 to 99*/
			   
					var validationValue = isNaN($('input[name="'+exampleposition+'"]').val());	

					if(!validationValue){
					   closeValidation =  false;
					}

			   	var positiongivenValue = 	$('input[name="'+exampleposition+'"]').val();   
		    	selectedExamplesRead = selectedExamplesRead + rec.id + "##" + positiongivenValue +",";
		    	selectedExamplesShow = selectedExamplesShow + rec.name + ",";
		    };	
		    counter++;	
	});

	var paramPassing = "selectedExamples"+id;
	var shownSelectedItems = "selectedExampleItems"+id;
	if(selectedExamplesRead.length > 0){		
		//$("input[name="+shownSelectedItems+"]").val(selectedExamplesShow.substring(0, selectedExamplesShow.length-1));	
		$("input[name="+paramPassing+"]:hidden").val(selectedExamplesRead.substring(0, selectedExamplesRead.length-1));
	} else{
		$("input[name="+shownSelectedItems+"]").val('');	
		$("input[name="+paramPassing+"]:hidden").val('');
	};

	$('#warrantyCount').val(warrantyListGlobal);
	hidePopUp("light");
	$("#examplesData").html(defaultExampleData);
	
}
/**adding richtext for waranty table **/

function addRichTextPopup(id){
	setBackgroundPopUp();
	$("#light2").center();
	$("#light2").show();
	$("#popinIdPass2").html('<input type="button" value="Annuler" style="margin-right: 10px;" onclick = "hidePopUp(' + "'light2'" + ')" /><input type="button" value="Validez" onclick = "closeRichTextPopin('+id+');">');	
	
	//adding Data
	var detailsText = "detailsText"+id;
	detailsText = $('input[name="'+detailsText+'"]:hidden').val(); 
	window.<portlet:namespace />detailsEditor.setHTML(detailsText);
}
/**adding richtext for waranty table **/
function addRichTextPopupforNiveau(id){
	setBackgroundPopUp();
	$("#light2").center();
	$("#light2").show();
	$("#popinIdPass2").html('<input type="button" value="Annuler" style="margin-right: 10px;" onclick = "hidePopUp(' + "'light2'" + ')" /><input type="button" value="Validez" onclick = "closeRichTextPopinforNiveau('+id+');">');
	
	//adding data 
	var detailsTextNiveau = "detailsTextNiveau"+id;
	detailsTextNiveau = $('input[name="'+detailsTextNiveau+'"]:hidden').val();
	window.<portlet:namespace />detailsEditor.setHTML(detailsTextNiveau);
}
/**close richtext for waranty table **/
function setRichText(id){
	var detailsText = "detailsText"+id;
	messageData = $('input[name="'+detailsText+'"]:hidden').val(); 
	var subStringData = $(messageData).text().trim();	
	 subStringData = subStringData.substring(0, 10);	
	var shownData = "textArea"+id;
	$('input[name="'+shownData+'"]').val(subStringData+"...");  
}
function setRichTextNiveau(id){
	var detailsText = "detailsTextNiveau"+id;
	messageData = $('input[name="'+detailsText+'"]:hidden').val();
	var subStringData = $(messageData).text().trim();	
	 subStringData = subStringData.substring(0, 10);	
	var shownData = "wtextArea"+id;
	$('input[name="'+shownData+'"]').val(subStringData+"...");  
}
function closeRichTextPopin(id){
    var detailsText = "detailsText"+id;
	var messageData = window.<portlet:namespace />detailsEditor.getHTML();	 
	$('input[name="'+detailsText+'"]:hidden').val(messageData);  
	var subStringData = $(messageData).text().trim();	
	 subStringData = subStringData.substring(0, 10);	 
    var shownData = "textArea"+id;	
	if(subStringData.length > 0){
		$('input[name="'+shownData+'"]').val(subStringData+"...");  
	}  
	hidePopUp("light2");
	window.<portlet:namespace />detailsEditor.setHTML('');
}
/**close richtext for waranty table **/
function closeRichTextPopinforNiveau(id){
	var detailsTextNiveau = "detailsTextNiveau"+id;
	var messageData = window.<portlet:namespace />detailsEditor.getHTML();	 
	$('input[name="'+detailsTextNiveau+'"]:hidden').val(messageData);  
	var subStringData = $(messageData).text().trim();	
	 subStringData = subStringData.substring(0, 10);	 
    var shownData = "wtextArea"+id;	
	if(subStringData.length > 0){
		$('input[name="'+shownData+'"]').val(subStringData+"...");  
	}
	hidePopUp("light2");
	window.<portlet:namespace />detailsEditor.setHTML('');
}
/**
 * img upload
 */
var <portlet:namespace/>popup;
var _15_selectDocumentLibrary;
                
function callDLUploader(fieldNameDL){
       <portlet:namespace/>popup = window.open('<%=selectDLURL%>','_blank','directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');

       <portlet:namespace/>popup.focus();
       _15_selectDocumentLibrary=function(url){
              <portlet:namespace/>popup.close();
              jQuery("#" + fieldNameDL).val(url);
       }
}
function <portlet:namespace />initEditor() {
}
/**checking file type **/
function check_fileType(obj){	    
  var str=document.getElementById(obj.name).value.toUpperCase();	 
	  var suffix2=".PDF";
		if(str.indexOf(suffix2) == -1){
				alert('Only PDF Files are Allowed.');
			    document.getElementById(obj.name).value='';
		}
 }
/**delete table id from the document list **/
function deleteDocumentList(id){
	$("table #"+id).html('');
}

function deleteDocumentMethod(documentDetailsId)
{
	$('#existDocument_' + documentDetailsId).remove();
	deletedDcoumentDetailsString = deletedDcoumentDetailsString + documentDetailsId + ',';
	$('#deletedDocumentIds').val(deletedDcoumentDetailsString);
}

function submitDateRange(url, dateFlag) 
{
	var codeTarifId = $('#addDateCodeTarifId').val();
	var employeeProductId = $('#employeeProductId').val();
 	var zoneId = $('#addDateZoneId').val();
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var taxe = $('#taxe').val();
	var employeeProductPeriodId = $('#editDateId').val();
	var status = $('#status').val();
	
	var regime = $('#regime').val();
	var zone1 = $('#zone1').val();
	var zone2 = $('#zone2').val();
	var zone3 = $('#zone3').val();
	var structure = $('#structureId').val();
	
	if(validateDateForm())
	{
		if(dateFlag == 'Add')
		{
			$.ajax({
				url : url,
				data : {
					codeTarifId : codeTarifId,
					employeeProductId : employeeProductId,
					startDate : startDate,
					endDate : endDate,
					taxe : taxe,
					dateFlag : dateFlag,
					regime : regime,
					zone1 : zone1,
					zone2 : zone2,
					zone3 : zone3,
					status : status,
					structure : structure
				},
				type : 'GET',
				dataType : "json",
				success : function(data) {
					if (data.output == "success") {
						$('#'+zoneId+'dateRangeTable')
								.append(
										'<tr id="tr_'+data.employeeProductPeriodId+'" class="results-row portlet-section-alternate-hover"><td align="center"><input id="'+ data.employeeProductPeriodId +'" onclick="confirmPriceChange();" type="radio" name="' + zoneId+ 'dateRadio" checked="checked" value="'+data.employeeProductPeriodId + '"  style="width: 20px" nowrap="nowrap" align="center" /></td><td id="td1_'+data.employeeProductPeriodId+'">'
												+ startDate
												+ '</td><td id="td2_'+data.employeeProductPeriodId+'">'
												+ endDate
												+ '</td><td id="td3_'+data.employeeProductPeriodId+'">'
												+ taxe
												+ '</td><td style="width: 20px" nowrap="nowrap"><img alt="Edit Icon" src="'+ $("#folderPath").val() +'/img/icons/edit.png" onclick="editDateRange('+data.employeeProductPeriodId+');" /></td><td style="width: 20px" nowrap="nowrap"><img alt="Delete Icon" class="'+zoneId+'deleteDateButton" src="'+ $("#folderPath").val() +'/img/icons/ico-delete.png" onclick="deleteDateRange('+data.employeeProductPeriodId+','+zoneId+');" /></td></tr>');
						$('.date-overlap-error-msg').hide();
						$('#dateRangeDiv').hide();
						deleteButtonCheck(zoneId);
						tabClickMethod(zoneId);
						populatePriceDetails();
					}
					else{
						$('.date-overlap-error-msg').show();
					}
				}
			});	
		}
		else if(dateFlag == 'Edit')
		{
			$.ajax({
				url : url,
				data : {
					codeTarifId : codeTarifId,
					employeeProductId : employeeProductId,
					startDate : startDate,
					endDate : endDate,
					taxe : taxe,
					employeeProductPeriodId:employeeProductPeriodId,
					dateFlag:dateFlag,
					regime : regime,
					zone1 : zone1,
					zone2 : zone2,
					zone3 : zone3,
					status : status,
					structure : structure
				},
				type : 'GET',
				dataType : "json",
				success : function(data) {
					if (data.output == "success") {
						$('.date-overlap-error-msg').hide();
						$('#td1_'+employeeProductPeriodId).html(startDate);
						$('#td2_'+employeeProductPeriodId).html(endDate);
						$('#td3_'+employeeProductPeriodId).html(taxe);
						$('#dateFlag').val('Add');
						$('#dateRangeDiv').hide();
						if(data.pageReload)
						{
							location.href = data.refreshURL;
						}
					}
					else{
						$('.date-overlap-error-msg').show();
					}
				}
			});				
		}
	}
}

function validateEmpty(field){
	 if ($.trim(field.val()) === '') {
		 field.addClass("error_input");
		 return false;
	 }else{
		 field.removeClass("error_input");
		 return true;
	 }
}

function validateDateForm(){
	var sDate = validateEmpty($("#sDate"));
	var eDate = validateEmpty($("#eDate"));
	
	if(sDate && eDate){
		$(".date-error-msg").hide();
		return true;
	}else{
		$(".date-error-msg").show();
		return false;
	}
}

function validateName()
{
	var validName = validateEmpty($("#epName"));
	if(validName)
	{
		$(".epname-error-msg").hide();
		return true;	
	}else{
		$(".epname-error-msg").show();
		return false;
	}
}

function addDateRangePanel(zoneId) {
	$('#editDateId').val('');
	$('#dateFlag').val('Add');
	$('#dateRangeDiv').show();
	$('#sDate').val('');
 	$('#eDate').val('');
 	$('#taxe').val('');
 	$('.date-overlap-error-msg').hide();
	
	var codeTarifId = $('#zone' + zoneId + 'codesTarifId').val();
 	$('#addDateCodeTarifId').val(codeTarifId);
 	$('#addDateZoneId').val(zoneId);
}

$(function() {
	$("#tabs").tabs();	
	
	if($('#formAction').val() == 'Edit')
	{
		$('#baseDetailsDiv').show();
		$('#ep2formDiv').show();
	}
	showHidePeriodPanel();
	var regimeVal = $('#regime').val();
	if(regimeVal == 'RG')
	{
		tabClickMethod(1);
		tabClickMethod(2);
		tabClickMethod(3);
		deleteButtonCheck(1);
	}
	else if(regimeVal == 'RL')
	{
		tabClickMethod(4);
		deleteButtonCheck(4);
	}
	populatePriceDetails();
	// persist the selected radio buttons value
	$('#1lastRadio').val($('input[name="1dateRadio"]:checked').val());
	$('#2lastRadio').val($('input[name="2dateRadio"]:checked').val());
	$('#3lastRadio').val($('input[name="3dateRadio"]:checked').val());
	$('#4lastRadio').val($('input[name="4dateRadio"]:checked').val());
	// persist the selected price type radio button value
	$('#1lastRadioPriceType').val($('input[name="zonePriceType1"]:checked').val());
	$('#2lastRadioPriceType').val($('input[name="zonePriceType2"]:checked').val());
	$('#3lastRadioPriceType').val($('input[name="zonePriceType3"]:checked').val());
	$('#4lastRadioPriceType').val($('input[name="zonePriceType4"]:checked').val());
});

$(function() {
	
	$(".priceTextClass").keypress(function(e) {
		if (!String.fromCharCode(e.which).match(/^[0-9,]+$/)) {
	    	e.preventDefault();
		//	formState();
		}
	});
	
	$(".datepicker1").datepicker(
			{
				showOn : "button",
				buttonImage : $("#folderPath").val() + "/img/calendar_icon.png",
				buttonImageOnly : true,
				changeMonth : true,
				dateFormat: 'dd/mm/yy',
				changeYear : true,
				yearRange : 2014 + ':' + 2020,
				defaultDate : new Date(),
				minDate : new Date(),
				onClose: function( selectedDate ) {
			        $( ".datepicker2" ).datepicker( "option", "minDate", selectedDate );
			      },
				onSelect : function(dateText, inst) {
			    	$('#startDate').val(dateText);
				}
			}, $.datepicker.regional['fr']);
});

$(function() {
	$(".datepicker2").datepicker(
			{
				showOn : "button",
				buttonImage : $("#folderPath").val() + "/img/calendar_icon.png",
				buttonImageOnly : true,
				changeMonth : true,
				dateFormat: 'dd/mm/yy',
				changeYear : true,
				yearRange : 2014 + ':' + 2020,
				onSelect : function(dateText, inst) {
					$('#endDate').val(dateText);
				}
			}, $.datepicker.regional['fr']);
});
	
function editDateRange(employeeProductPeriodId) 
{
	$('#editDateId').val(employeeProductPeriodId);
	$('#dateFlag').val('Edit');
	$('#dateRangeDiv').show();
 	$('#sDate').val($('#td1_'+employeeProductPeriodId).html());
 	$('#eDate').val($('#td2_'+employeeProductPeriodId).html());
 	$('#taxe').val($('#td3_'+employeeProductPeriodId).html());
	$('#startDate').val($('#td1_'+employeeProductPeriodId).html());
 	$('#endDate').val($('#td2_'+employeeProductPeriodId).html());
}

function cancelDateProcess()
{
	$('#editDateId').val('');
	$('#dateFlag').val('Add');
	$('#dateRangeDiv').hide();
	$('#sDate').val('');
 	$('#eDate').val('');
 	$('#taxe').val('');
 	$('#sDate').removeClass("error_input");
 	$('#eDate').removeClass("error_input");
 	$(".date-error-msg").hide();
 	$('.date-overlap-error-msg').hide();
}
	
function deleteDateRange(employeeProductPeriodId, zoneId)
{
	$('#deletePopupEPPID').val(employeeProductPeriodId);
	$('#deleteDateZoneId').val(zoneId);
	
	// to disable background page
	pageHeight = $('#wrapper').height();
	windowHeight = $(window).height(); 
	height = (pageHeight > windowHeight)? pageHeight : windowHeight;
	
	$("#backgroundPopup").css({
        "opacity" : "0.7",
        "width" : $(window).width(),
        "height" : height,
        "display" : "block",
        "position" : "absolute",
        "top" : "0",
        "bottom" : "0",
        "background-color" : "#000",
        "z-index" : "999"
	});

	$("#backgroundPopup").fadeIn("slow");

	// to display confirm dialog
	$("#confirmPop").fadeIn("slow");
}

function deleteDateMethod(deleteUrl) 
{
	var employeeProductPeriodId = $('#deletePopupEPPID').val();
	var employeeProductId = $('#employeeProductId').val();
	var deleteDateZoneId = $('#deleteDateZoneId').val();
	var regime = $('#regime').val();
	var zone1 = $('#zone1').val();
	var zone2 = $('#zone2').val();
	var zone3 = $('#zone3').val();
	var status = $('#status').val();
	var structure = $('#structureId').val();
	
	$.ajax({
		url : deleteUrl,
		data : {
			employeeProductPeriodId : employeeProductPeriodId,
			employeeProductId : employeeProductId,
			regime : regime,
			zone1 : zone1,
			zone2 : zone2,
			zone3 : zone3,
			status : status,
			structure : structure
		},
		type : 'GET',
		dataType : "json",
		success : function(data) {
			if (data.output == "success") {
				$('#tr_' + employeeProductPeriodId).remove();
				deleteButtonCheck(deleteDateZoneId);
				tabClickMethod(deleteDateZoneId);
				if(data.pageReload)
				{
					location.href = data.refreshURL;
				}
			}
		}
	});
	disablePopup();	
}
	
function deleteButtonCheck(zoneId)
{
	var count = $(':radio[name="'+zoneId+'dateRadio"]').length;
	if(count <= 1)
	{
		$('.'+zoneId+'deleteDateButton').hide();	
	}
	else
	{
		$('.'+zoneId+'deleteDateButton').show();
	}
}

function showHidePeriodPanel()
{
	var zone1codesTarifVal = $('#zone1_codesTarif').val();
	var zone2codesTarifVal = $('#zone2_codesTarif').val();
	var zone3codesTarifVal = $('#zone3_codesTarif').val();
	var zone4codesTarifVal = $('#zone4_codesTarif').val();
	
	 if ($.trim(zone1codesTarifVal) === '') {
		$('#1outerDateDiv').hide();
		$('#1zonePriceDiv').hide();
	}else{
		$('#1outerDateDiv').show();	
	}
	if ($.trim(zone2codesTarifVal) === '') {
		$('#2outerDateDiv').hide();
		$('#2zonePriceDiv').hide();
	}else{
		$('#2outerDateDiv').show();	
	}
	 if ($.trim(zone3codesTarifVal) === '') {
		$('#3outerDateDiv').hide();
		$('#3zonePriceDiv').hide();
	}else{
		$('#3outerDateDiv').show();	
	}
	if ($.trim(zone4codesTarifVal) === '') {
		$('#4outerDateDiv').hide();
		$('#4zonePriceDiv').hide();
	}else{
		$('#4outerDateDiv').show();	
	}
} 

function disablePopup()
{
	$("#backgroundPopup").fadeOut("slow");
	$("#confirmPop").fadeOut("slow");	
}
function disablePricePopup()
{
	$("#backgroundPopup").fadeOut("slow");
	$("#confirmPricePop").fadeOut("slow");
}
function cancelDisablePricePopup()
{
	$("#backgroundPopup").fadeOut("slow");
	$("#confirmPricePop").fadeOut("slow");
	$("#" + $('#1lastRadio').val()).prop("checked", true);
	$("#" + $('#2lastRadio').val()).prop("checked", true);
	$("#" + $('#3lastRadio').val()).prop("checked", true);
	$("#" + $('#4lastRadio').val()).prop("checked", true);
}

function cancelDisablePriceTypePopup()
{
	$("#backgroundPopup").fadeOut("slow");
	$("#confirmPriceTypePop").fadeOut("slow");
	
	if($('#1lastRadioPriceType').val() === 'EN')
	{
		$("#enRadio1").prop("checked", true);		
	}
	else
	{
		$("#pmssRadio1").prop("checked", true);
	}
	if($('#2lastRadioPriceType').val() === 'EN')
	{
		$("#enRadio2").prop("checked", true);		
	}
	else
	{
		$("#pmssRadio2").prop("checked", true);
	}
	if($('#3lastRadioPriceType').val() === 'EN')
	{
		$("#enRadio3").prop("checked", true);		
	}
	else
	{
		$("#pmssRadio3").prop("checked", true);
	}
	if($('#4lastRadioPriceType').val() === 'EN')
	{
		$("#enRadio4").prop("checked", true);		
	}
	else
	{
		$("#pmssRadio4").prop("checked", true);
	}
}

function confirmPriceChange()
{
	// to disable background page
	pageHeight = $('#wrapper').height();
	windowHeight = $(window).height(); 
	height = (pageHeight > windowHeight)? pageHeight : windowHeight;
	
	$("#backgroundPopup").css({
        "opacity" : "0.7",
        "width" : $(window).width(),
        "height" : height,
        "display" : "block",
        "position" : "absolute",
        "top" : "0",
        "bottom" : "0",
        "background-color" : "#000",
        "z-index" : "999"
	});

	$("#backgroundPopup").fadeIn("slow");

	// to display confirm dialog
	$("#confirmPricePop").fadeIn("slow");	
}


function confirmPriceTypeChange()
{
	if($('#status').val() === 'En ligne')
	{
		// to disable background page
		pageHeight = $('#wrapper').height();
		windowHeight = $(window).height(); 
		height = (pageHeight > windowHeight)? pageHeight : windowHeight;
		
		$("#backgroundPopup").css({
	        "opacity" : "0.7",
	        "width" : $(window).width(),
	        "height" : height,
	        "display" : "block",
	        "position" : "absolute",
	        "top" : "0",
	        "bottom" : "0",
	        "background-color" : "#000",
	        "z-index" : "999"
		});

		$("#backgroundPopup").fadeIn("slow");

		// to display confirm dialog
		$("#confirmPriceTypePop").fadeIn("slow");		
	}
}

function priceChangeDone()
{
	// update the latest selected radio button values
	$('#1lastRadio').val($(':radio[name="1dateRadio"]:checked').val());
	$('#2lastRadio').val($(':radio[name="2dateRadio"]:checked').val());
	$('#3lastRadio').val($(':radio[name="3dateRadio"]:checked').val());
	$('#4lastRadio').val($(':radio[name="4dateRadio"]:checked').val());
	populatePriceDetails();
}

function priceTypeChangeDone()
{
	// update the latest selected radio button values
	$('#1lastRadioPriceType').val($(':radio[name="zonePriceType1"]:checked').val());
	$('#2lastRadioPriceType').val($(':radio[name="zonePriceType2"]:checked').val());
	$('#3lastRadioPriceType').val($(':radio[name="zonePriceType3"]:checked').val());
	$('#4lastRadioPriceType').val($(':radio[name="zonePriceType4"]:checked').val());
	$("#backgroundPopup").fadeOut("slow");
	$("#confirmPriceTypePop").fadeOut("slow");
}

function populatePriceDetails() 
{
	var priceDetailsURL = $('#priceDetailsURL').val();
	var zone1EmployeeProductPeriodId = $(':radio[name="1dateRadio"]:checked').val();
	var zone2EmployeeProductPeriodId = $(':radio[name="2dateRadio"]:checked').val();
	var zone3EmployeeProductPeriodId = $(':radio[name="3dateRadio"]:checked').val();
	var zone4EmployeeProductPeriodId = $(':radio[name="4dateRadio"]:checked').val();
	var regime = $('#regime').val();
	var zone1 = $('#zone1').val();
	var zone2 = $('#zone2').val();
	var zone3 = $('#zone3').val();
	var status = $('#status').val();
	var structure = $('#structureId').val();
	
	$.ajax({
		url : priceDetailsURL,
		data : {
			zone1EmployeeProductPeriodId : zone1EmployeeProductPeriodId,
			zone2EmployeeProductPeriodId : zone2EmployeeProductPeriodId,
			zone3EmployeeProductPeriodId : zone3EmployeeProductPeriodId,
			zone4EmployeeProductPeriodId : zone4EmployeeProductPeriodId,
			regime : regime,
			zone1 : zone1,
			zone2 : zone2,
			zone3 : zone3,
			status : status,
			structure : structure
		},
		type : 'GET',
		dataType : "json",
		success : function(data) {

			$('#zone1_aduEmp').val(data.serializable.zone1_aduEmp);
			$('#zone1_aduAdh').val(data.serializable.zone1_aduAdh);
			$('#zone1_enfEmp').val(data.serializable.zone1_enfEmp);
			$('#zone1_enfAdh').val(data.serializable.zone1_enfAdh);
			$('#zone1_conEmp').val(data.serializable.zone1_conEmp);
			$('#zone1_conAdh').val(data.serializable.zone1_conAdh);
			$.each(data.serializable.zone1_Age_Adh.map, function(index, value) {
				$('#zone1_'+index+'_Adh').val(value);
			});
			$.each(data.serializable.zone1_Age_Emp.map, function(index, value) {
				$('#zone1_'+index+'_Emp').val(value);
			});
			
			$('#zone2_aduEmp').val(data.serializable.zone2_aduEmp);
			$('#zone2_aduAdh').val(data.serializable.zone2_aduAdh);
			$('#zone2_enfEmp').val(data.serializable.zone2_enfEmp);
			$('#zone2_enfAdh').val(data.serializable.zone2_enfAdh);
			$('#zone2_conEmp').val(data.serializable.zone2_conEmp);
			$('#zone2_conAdh').val(data.serializable.zone2_conAdh);
			$.each(data.serializable.zone2_Age_Adh.map, function(index, value) {
				$('#zone2_'+index+'_Adh').val(value);
			});
			$.each(data.serializable.zone2_Age_Emp.map, function(index, value) {
				$('#zone2_'+index+'_Emp').val(value);
			});
			
			$('#zone3_aduEmp').val(data.serializable.zone3_aduEmp);
			$('#zone3_aduAdh').val(data.serializable.zone3_aduAdh);
			$('#zone3_enfEmp').val(data.serializable.zone3_enfEmp);
			$('#zone3_enfAdh').val(data.serializable.zone3_enfAdh);
			$('#zone3_conEmp').val(data.serializable.zone3_conEmp);
			$('#zone3_conAdh').val(data.serializable.zone3_conAdh);
			$.each(data.serializable.zone3_Age_Adh.map, function(index, value) {
				$('#zone3_'+index+'_Adh').val(value);
			});
			$.each(data.serializable.zone3_Age_Emp.map, function(index, value) {
				$('#zone3_'+index+'_Emp').val(value);
			});
			
			$('#zone4_aduEmp').val(data.serializable.zone4_aduEmp);
			$('#zone4_aduAdh').val(data.serializable.zone4_aduAdh);
			$('#zone4_enfEmp').val(data.serializable.zone4_enfEmp);
			$('#zone4_enfAdh').val(data.serializable.zone4_enfAdh);
			$('#zone4_conEmp').val(data.serializable.zone4_conEmp);
			$('#zone4_conAdh').val(data.serializable.zone4_conAdh);
			$.each(data.serializable.zone4_Age_Adh.map, function(index, value) {
				$('#zone4_'+index+'_Adh').val(value);
			});
			$.each(data.serializable.zone4_Age_Emp.map, function(index, value) {
				$('#zone4_'+index+'_Emp').val(value);
			});
			setFieldsInitialValues();
		}
	});
	disablePricePopup();
}

function addUpdateCodesTarif(employeeProductId, zoneId, codesTarifURL)
{
	// need to update this method and radio button HTML
	var codeTarifTextId = '#zone' + zoneId + '_codesTarif';
	var priceTypeVal = $('input[name="zonePriceType'+zoneId+'"]:checked').val();
	var codesTarifVal = $(codeTarifTextId).val();
	var situationId = $('#situationId').val();
	var structureId = $('#structureId').val();
	var status = $('#status').val();
			
	if(validateEmpty($(codeTarifTextId)))
	{
		checkCodeTarifFieldChange(zoneId);
		$.ajax({
			url : codesTarifURL,
			data : {
				employeeProductId : employeeProductId,
				zoneId : zoneId,
				priceTypeVal : priceTypeVal,
				codesTarifVal : codesTarifVal,
				situationId : situationId,
				structureId : structureId,
				status : status,
				formFinalStateChanged : $('#formFinalStateChanged').val()
			},
			type : 'GET',
			dataType : "json",
			success : function(data) {
				if (data.output == "success") {
					$('#'+zoneId+'outerDateDiv').show();
					showHidePricePanel(zoneId);
					$('#zone'+zoneId+'codesTarifId').val(data.employeeProductCodesTarifId);
					if(data.pageReload)
					{
						location.href = data.refreshURL;
					}
				}
			}
		});
	}
}

function showHidePricePanel(zoneId)
{
	var countRadio = $(':radio[name="' + zoneId + 'dateRadio"]').length;
	var codeTarifVal = $('#zone'+zoneId+'_codesTarif').val(); 

	if(countRadio < 1 || $.trim(codeTarifVal) === '')
	{
		$('#' + zoneId + 'zonePriceDiv').hide();	
	}
	else
	{
		$('#' + zoneId + 'zonePriceDiv').show();
	}
} 

function tabClickMethod(zoneId)
{
	showHidePricePanel(zoneId);
	deleteButtonCheck(zoneId);
	cancelDateProcess();
	$("#" + zoneId + "dateRangeTable input:radio:first").prop("checked", true);
}
var oldZonePriceType1;
var oldZonePriceType2;
var oldZonePriceType3;
var oldZonePriceType4;
function setFieldsInitialValues(){
	$("#employeeProductForm").find('input[name="codeProduitTechnique"]').each(function() { 
	    $(this).data('initialValue', $(this).val()); 
	}); 
	$('#1zonePriceDiv').find('input[type="text"]').each(function() { 
	    $(this).data('initialValue', $(this).val()); 
	});
	$('#2zonePriceDiv').find('input[type="text"]').each(function() { 
	    $(this).data('initialValue', $(this).val()); 
	});
	$('#3zonePriceDiv').find('input[type="text"]').each(function() { 
	    $(this).data('initialValue', $(this).val()); 
	});
	$('#4zonePriceDiv').find('input[type="text"]').each(function() { 
	    $(this).data('initialValue', $(this).val()); 
	});
	$('#zoneCodeTerifDiv1').find('input[type="text"]').each(function() { 
	    $(this).data('initialValue', $(this).val()); 
	});
	$('#zoneCodeTerifDiv2').find('input[type="text"]').each(function() { 
	    $(this).data('initialValue', $(this).val()); 
	});
	$('#zoneCodeTerifDiv3').find('input[type="text"]').each(function() { 
	    $(this).data('initialValue', $(this).val()); 
	});
	$('#zoneCodeTerifDiv4').find('input[type="text"]').each(function() { 
	    $(this).data('initialValue', $(this).val()); 
	});
	
	oldZonePriceType1 = $('input[name="zonePriceType1"]:checked').val();
	oldZonePriceType2 = $('input[name="zonePriceType2"]:checked').val();
	oldZonePriceType3 = $('input[name="zonePriceType3"]:checked').val();
	oldZonePriceType4 = $('input[name="zonePriceType4"]:checked').val();
}

function checkFormFinalStateMethod(){
	var isDirty = false;
	$("#employeeProductForm").find('input[name="codeProduitTechnique"]').each(function() {  
        if($(this).data('initialValue') != $(this).val())
        { 
        	isDirty = true; 
        } 
    }); 
	$('#1zonePriceDiv').find('input[type="text"]').each(function() { 
	 if($(this).data('initialValue') != $(this).val())
        { 
        	isDirty = true; 
        } 
	});
	$('#2zonePriceDiv').find('input[type="text"]').each(function() { 
		 if($(this).data('initialValue') != $(this).val())
	        { 
	        	isDirty = true; 
	        } 
		});
	$('#3zonePriceDiv').find('input[type="text"]').each(function() { 
		 if($(this).data('initialValue') != $(this).val())
	        { 
	        	isDirty = true; 
	        } 
		});
	$('#4zonePriceDiv').find('input[type="text"]').each(function() { 
		 if($(this).data('initialValue') != $(this).val())
	        { 
	        	isDirty = true; 
	        } 
		});
	$('#zoneCodeTerifDiv1').find('input[type="text"]').each(function() { 
	if($(this).data('initialValue') != $(this).val())
	        { 
	        	isDirty = true; 
	        } 
		});
	$('#zoneCodeTerifDiv2').find('input[type="text"]').each(function() { 
	if($(this).data('initialValue') != $(this).val())
	        { 
	        	isDirty = true; 
	        } 
		});
	$('#zoneCodeTerifDiv3').find('input[type="text"]').each(function() { 
	if($(this).data('initialValue') != $(this).val())
	        { 
	        	isDirty = true; 
	        } 
		});
	$('#zoneCodeTerifDiv4').find('input[type="text"]').each(function() { 
	if($(this).data('initialValue') != $(this).val())
	        { 
	        	isDirty = true; 
	        } 
		});
	if(oldZonePriceType1 != $('input[name="zonePriceType1"]:checked').val())
	{
		isDirty = true;
	}
	if(oldZonePriceType2 != $('input[name="zonePriceType2"]:checked').val())
	{
		isDirty = true;
	}
	if(oldZonePriceType3 != $('input[name="zonePriceType3"]:checked').val())
	{
		isDirty = true;
	}
	if(oldZonePriceType4 != $('input[name="zonePriceType4"]:checked').val())
	{
		isDirty = true;
	}
	
	if(isDirty == true){ 
    	$('#formFinalStateChanged').val(true);
    } else {
    	$('#formFinalStateChanged').val(false);
    }
}

function checkCodeTarifFieldChange(zoneId)
{
	$('#zoneCodeTerifDiv'+zoneId).find('input[type="text"],input[type="radio"]').each(function() { 
		if($(this).data('initialValue') != $(this).val())
		{ 
			isDirty = true; 
		} 
	});

	if(isDirty == true){ 
    	$('#formFinalStateChanged').val(true);
    } else {
    	$('#formFinalStateChanged').val(false);
    }
}

function trancheAgeChangeMethod(url, zone, tranche)
{
	this.document.location.href = url+'&zoneselected='+zone+'&trancheselected='+tranche;
}

$(function() {
	
	if('${zoneSelected}'=='<%=AniConstants.ZONE1%>'){
		$("#tabs").tabs("select", 0);
	} else if('${zoneSelected}'=='<%=AniConstants.ZONE2%>'){
		$("#tabs").tabs("select", 1);
	} else if('${zoneSelected}'=='<%=AniConstants.ZONE3%>'){
		$("#tabs").tabs("select", 2);
	}
});

</script>

<style>

	#table-scroll {
	  height:150px;
	  overflow:auto;  
	}

</style>