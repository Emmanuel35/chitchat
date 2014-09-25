<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:tns="http://www.anteo-consulting.com/chitchat"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:ns0="http://xmlns.oracle.com/osb12c-application/search/search"
                xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction"
                xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction"
                xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 tns oraxsl DVMFunctions BasicCredentialsUserFunction UUIDUserFunction IsUserInRoleFunction IsUserInGroupFunction XrefFunctions"
                xmlns:inp1="http://anteo-consulting.com/tweet"
                xmlns:plnk="http://docs.oasis-open.org/wsbpel/2.0/plnktype"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
    <oracle-xsl-mapper:schema>
        <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
        <oracle-xsl-mapper:mapSources>
            <oracle-xsl-mapper:source type="WSDL">
                <oracle-xsl-mapper:schema location="../Resources/search.wsdl"/>
                <oracle-xsl-mapper:rootElement name="search_params"
                                               namespace="http://xmlns.oracle.com/osb12c-application/search/search"/>
            </oracle-xsl-mapper:source>
        </oracle-xsl-mapper:mapSources>
        <oracle-xsl-mapper:mapTargets>
            <oracle-xsl-mapper:target type="XSD">
                <oracle-xsl-mapper:schema location="../../common/Resources/ChitChatEJBService.xsd"/>
                <oracle-xsl-mapper:rootElement name="search" namespace="http://www.anteo-consulting.com/chitchat"/>
            </oracle-xsl-mapper:target>
        </oracle-xsl-mapper:mapTargets>
        <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [WED SEP 24 19:17:46 CEST 2014].-->
    </oracle-xsl-mapper:schema>
    <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
    <xsl:template match="/">
        <tns:search>
            <text>
                <xsl:value-of select="/ns0:search_params/ns0:text"/>
            </text>
        </tns:search>
    </xsl:template>
</xsl:stylesheet>
