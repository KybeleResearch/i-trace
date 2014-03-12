-- iTrace SQL Script Generation --
-- Model Name: /M2DATDB/Models/ORDBD4ORA2CODE2.iTrace

INSERT INTO iTraceModel (uuid_iTraceModel, iTraceModel, projectName, version) VALUES ('_oqEyQDfOEeKpC9FV0t3jSg','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#/','ORDB4ORA2CODE','1.0');
INSERT INTO TraceLink (uuid_TraceLink, traceLink, createdOn, type, fromFileName, tl_comment, createdBy, tl_mode, technicalBinding, ruleName, linkType, iTraceModel) VALUES ('_oqEyQTfOEeKpC9FV0t3jSg','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#//@traceLinks.0','11-10-2012','Transformation','file:/D:/Workspaces/Case%20Studies/M2DATDB/Models/ORDBD4ORA2CODE.traceabilitymodel','iTrace for M2T Transformation with MofScript','iTrace','Automatic','MofScript','generateAttribute','M2TLink','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#/');
INSERT INTO TraceLinkElement (uuid_TraceLinkElement, traceLinkElement, ref, objectType, relationType, artefact, traceLink, id) VALUES ('_oqFZUTfOEeKpC9FV0t3jSg','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#//@traceLinks.0/@sourceElements.0','//@datatype.19/@attribute.0','MofTrace!ModelElementRef','Source','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#//@artefacts.0','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#//@traceLinks.0','ORDB4ORA//@datatype.19/@attribute.0');
INSERT INTO Artefact (uuid_Artefact, artefact, aspect, name, abstractionLevel, metamodel, path, artefactType, iTraceModel) VALUES ('_oqFZUDfOEeKpC9FV0t3jSg','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#//@artefacts.0','Content','ORDB4ORA','PSM','/M2DATDB/Metamodels/ORDB4ORA.ecore','/M2DATDB/Models/omdb.ordb4ora','Model','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#/');
INSERT INTO Block (uuid_block, block, blockNumber, startLine, endLine, startColumn, endColumn, artefact, traceLink, id) VALUES ('_oqFZUzfOEeKpC9FV0t3jSg','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#//@traceLinks.0/@targetBlocks.0','1','6','6','3','10','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#//@artefacts.1','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#//@traceLinks.0','omdb.sql_oqFZUzfOEeKpC9FV0t3jSg');
INSERT INTO Artefact (uuid_Artefact, artefact, aspect, name, abstractionLevel, metamodel, path, artefactType, iTraceModel) VALUES ('_oqFZUjfOEeKpC9FV0t3jSg','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#//@artefacts.1','Content','omdb.sql','CODE','null','D:/Workspaces/Case Studies/M2DATDB/Code/','Code','/M2DATDB/Models/ORDBD4ORA2CODE2.iTrace#/');
