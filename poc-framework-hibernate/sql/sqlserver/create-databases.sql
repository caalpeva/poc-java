CREATE DATABASE POC_HB03_xml_oneToOne_identicalPrimaryKeys;
CREATE DATABASE POC_HB03_xml_oneToOne_unidirectional;
CREATE DATABASE POC_HB03_annotation_oneToOne_identicalPrimaryKeys;
CREATE DATABASE POC_HB03_annotation_oneToOne_unidirectional;
CREATE DATABASE POC_HB03_annotation_oneToOne_unidirectional_table;
CREATE DATABASE POC_HB03_xml_oneToOne_bidirectional;
CREATE DATABASE POC_HB03_annotation_oneToOne_bidirectional;
CREATE DATABASE POC_HB03_annotation_oneToOne_bidirectional_identicalPrimaryKeys;
CREATE DATABASE POC_HB03_xml_oneToMany_unidirectional;
CREATE DATABASE POC_HB03_annotation_oneToMany_unidirectional;
CREATE DATABASE POC_HB03_xml_oneToMany_bidirectional;
CREATE DATABASE POC_HB03_annotation_oneToMany_bidirectional;
CREATE DATABASE POC_HB03_xml_manyToMany_unidirectional;
CREATE DATABASE POC_HB03_annotation_manyToMany_unidirectional;
CREATE DATABASE POC_HB03_xml_manyToMany_bidirectional;
CREATE DATABASE POC_HB03_annotation_manyToMany_bidirectional;
CREATE DATABASE POC_HB03_xml_inheritance_oneTable;
CREATE DATABASE POC_HB03_annotation_inheritance_oneTable;
CREATE DATABASE POC_HB03_xml_inheritance_oneTablePerSubclass;
CREATE DATABASE POC_HB03_annotation_inheritance_oneTablePerSubclass;
CREATE DATABASE POC_HB03_xml_inheritance_oneTablePerConcreteClass;
CREATE DATABASE POC_HB03_annotation_inheritance_oneTablePerConcreteClass;
CREATE DATABASE POC_H05_interceptors;
CREATE DATABASE POC_H05_events;
CREATE DATABASE POC_H05_filters_annotation;
CREATE DATABASE POC_H05_filters_xml;
CREATE DATABASE POC_H06_criteria;
SELECT * FROM ::fn_helpcollations()
SELECT SERVERPROPERTY('COLLATION')
SELECT name, collation_name 
FROM sys.databases
WHERE name = 'POC_H06_criteria'
--// Modern_Spanish_CI_AS
ALTER DATABASE POC_H06_criteria
COLLATE Modern_Spanish_CS_AS



