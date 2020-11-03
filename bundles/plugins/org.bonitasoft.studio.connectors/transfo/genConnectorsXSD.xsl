<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xso="dummy" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:def="http://www.bonitasoft.org/ns/connector/definition/6.0">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:namespace-alias stylesheet-prefix="xso" result-prefix="xs"/>
	<xsl:template match="/">
		<xso:schema xmlns="http://www.bonitasoft.org/studio/connector/interface/6.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" targetNamespace="http://www.bonitasoft.org/studio/connector/interface/6.0" elementFormDefault="qualified" attributeFormDefault="unqualified">
			<xsl:for-each select="def:ConnectorDefinition">
				<xso:element>
					<xsl:attribute name="name"><xsl:value-of select="concat(@id,'Connector')"/></xsl:attribute>
					<xso:complexType>
						<xso:sequence>
							<xso:element>
								<xsl:attribute name="name"><xsl:value-of select="concat(@id,'Input')"/></xsl:attribute>
								<xsl:attribute name="type"><xsl:value-of select="concat(@id,'InputType')"/></xsl:attribute>
								<xsl:attribute name="type"><xsl:value-of select="concat(@id,'OutputType')"/></xsl:attribute>
							</xso:element>
						</xso:sequence>
						<xso:attribute>
							<xsl:attribute name="name">category</xsl:attribute>
							<xsl:attribute name="fixed"><xsl:value-of select="def:category/@name"/></xsl:attribute>
						</xso:attribute>
						<xso:attribute>
							<xsl:attribute name="name">operation</xsl:attribute>
							<xsl:attribute name="fixed"><xsl:value-of select="concat('Exec',@id)"/></xsl:attribute>
						</xso:attribute>
						<xso:attribute>
							<xsl:attribute name="name">version</xsl:attribute>
							<xsl:attribute name="fixed"><xsl:value-of select="@version"/></xsl:attribute>
						</xso:attribute>
					</xso:complexType>
				</xso:element>
				<xso:complexType>
					<xsl:attribute name="name"><xsl:value-of select="concat(@id,'InputType')"/></xsl:attribute>
					<xso:sequence>
						<xsl:for-each select="def:input">
							<xso:element>
								<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
								<xsl:attribute name="type"><xsl:choose><xsl:when test="@type[starts-with(.,'java.lang')]"><xsl:value-of select="substring-after(@type,'java.lang.')"/></xsl:when><xsl:when test="@type[starts-with(.,'java.util')]"><xsl:value-of select="substring-after(@type,'java.util.')"/></xsl:when></xsl:choose></xsl:attribute>
								<xsl:for-each select="@defaultValue">
									<xsl:attribute name="default"><xsl:value-of select="."/></xsl:attribute>
								</xsl:for-each>
								<xsl:if test="not(@mandatory='true')">
									<xsl:attribute name="minOccurs">0</xsl:attribute>
								</xsl:if>
							</xso:element>
						</xsl:for-each>
					</xso:sequence>
				</xso:complexType>
				<xso:complexType>
					<xsl:attribute name="name"><xsl:value-of select="concat(@id,'OutputType')"/></xsl:attribute>
					<xso:sequence>
						<xsl:for-each select="def:output">
							<xso:element>
								<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
								<xsl:attribute name="type"><xsl:choose><xsl:when test="@type[starts-with(.,'java.lang')]"><xsl:value-of select="substring-after(@type,'java.lang.')"/></xsl:when><xsl:when test="@type[starts-with(.,'java.util')]"><xsl:value-of select="substring-after(@type,'java.util.')"/></xsl:when></xsl:choose></xsl:attribute>
								<xsl:for-each select="@defaultValue">
									<xsl:attribute name="default"><xsl:value-of select="."/></xsl:attribute>
								</xsl:for-each>
								<xsl:if test="not(@mandatory='true')">
									<xsl:attribute name="minOccurs">0</xsl:attribute>
								</xsl:if>
							</xso:element>
						</xsl:for-each>
					</xso:sequence>
				</xso:complexType>
			</xsl:for-each>
		</xso:schema>
	</xsl:template>
</xsl:stylesheet>
