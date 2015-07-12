create table EM_ExportManager (
	exportManagerId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	classNameId LONG,
	classNameValue VARCHAR(75) null,
	scope VARCHAR(75) null
);

create table EM_ExportManagerField (
	exportManagerFieldId LONG not null primary key,
	exportManagerId LONG,
	fieldName VARCHAR(75) null,
	fieldDisplayName VARCHAR(75) null,
	position INTEGER
);

create table EM_ExportManagerFields (
	exportManagerFieldsId LONG not null primary key,
	exportManagerId LONG,
	fieldName VARCHAR(75) null,
	fieldLabel VARCHAR(75) null
);