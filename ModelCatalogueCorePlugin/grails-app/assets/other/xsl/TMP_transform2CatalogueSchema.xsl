<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:mc="http://www.metadataregistry.org.uk/assets/schema/2.0/metadataregistry.xsd"
                xmlns:xd="http://www.oxygenxml.com/ns/doc/xsl"
                exclude-result-prefixes="xs mc xd xsl"
                version="2.0">


    <xsl:output method="html" indent="yes" media-type="string"/>

    <xsl:template match="/">
        <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
                   xmlns:vc='http://www.w3.org/2007/XMLSchema-versioning'
                   xmlns='%SCHEMA_NAME%'
                   targetNamespace='%SCHEMA_NAME%'
                   vc:minVersion='1.1' elementFormDefault='qualified'>
            <!-- Convert all MC dataType entities to simpleTypes -->
            <xsl:apply-templates select="//mc:dataType"/>
            <!-- Convert all MC dataElement entities to elements -->
            <xsl:apply-templates select="//mc:dataElement"/>
            <!-- Convert all MC dataClass entities to complex elements-->
            <xsl:apply-templates select="//mc:dataClass"/>
        </xs:schema>
    </xsl:template>

    <xsl:template match="mc:dataClass">
        <xsl:variable name="nameValue" select="@name"/>
        <xsl:variable name="CamelCaseName">
            <xsl:call-template name="Compress">
                <xsl:with-param name="text" select="$nameValue"/>
            </xsl:call-template>
        </xsl:variable>

        <!-- complexType 1 -->
        <xs:complexType >
            <!--Build up a complex type named using the (mcxml) name with the mcid added on: eg "name-id" -->
            <xsl:attribute name="name"><xsl:value-of select='translate(replace(replace(@id, "^%SCHEMA_NAME%/dataClass/(\d*)$", "placeholder-$1.2"),"placeholder",@name )," ","-")'/></xsl:attribute>
            <xs:sequence>
                <xsl:for-each select="child::mc:dataElement">
                    <!--xsl:variable name="dtName" select="mc:dataType/@name"/-->
                    <xsl:variable name="deName" select='translate(replace(@name," ","-"),"([)]*","")'/>
                    <xsl:variable name="delcName">
                        <xsl:call-template name="LowerCaseWord">
                            <xsl:with-param name="text" select="$deName"/>
                        </xsl:call-template>
                    </xsl:variable>
                    <xsl:variable name="dtName" select='translate(replace(  mc:dataType/@name," ","-"),"([)]*","")'/>
                    <xsl:variable name="dtlcName">
                        <xsl:call-template name="LowerCaseWord">
                            <xsl:with-param name="text" select="$dtName"/>
                        </xsl:call-template>
                    </xsl:variable>
                    <xsl:choose>
                        <xsl:when test="not($dtName)">
                            <xsl:variable name="dtRef" select='translate(replace(  mc:dataType/@ref," ","-"),"([)]*","")'/>
                            <xsl:variable name="dtlcRef">
                                <xsl:call-template name="LowerCaseWord">
                                    <xsl:with-param name="text" select="$dtRef"/>
                                </xsl:call-template>
                            </xsl:variable>
                            <xs:element>
                                <xsl:attribute name="name"><xsl:value-of select='translate(replace( @name  ," ","-"),"([)]*","")'/></xsl:attribute>
                                <xsl:choose>
                                    <xsl:when test="matches($dtRef, '^http://www.w3.org/2001/XMLSchema#*')">
                                        <xsl:variable name="type1">
                                            <xsl:call-template name="string-replace-all">
                                                <xsl:with-param name="text" select='$dtlcRef' />
                                                <xsl:with-param name="replace" select="'http://www.w3.org/2001/xmlschema#'" />
                                                <xsl:with-param name="by" select="'xs:'" />
                                            </xsl:call-template>
                                        </xsl:variable>
                                       <xsl:attribute name="type"><xsl:value-of select='$type1'/></xsl:attribute>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:if test="$dtlcRef">
                                            <xsl:attribute name="type"><xsl:value-of select='translate(replace(replace( mc:dataType/@id, "^%SCHEMA_NAME%/dataType/(\d*)$", "placeholder-$1.1"),"placeholder", $dtlcRef )," ","-") '/></xsl:attribute>
                                        </xsl:if>
                                    </xsl:otherwise>
                                </xsl:choose>
                                <xsl:if test='mc:metadata/mc:extension'>
                                    <xsl:attribute name="minOccurs">
                                        <xsl:for-each select="mc:metadata/mc:extension">
                                            <xsl:call-template name="Min" />
                                        </xsl:for-each>
                                    </xsl:attribute>
                                    <xsl:attribute name="maxOccurs">
                                        <xsl:for-each select="mc:metadata/mc:extension">
                                            <xsl:call-template name="Max" />
                                        </xsl:for-each>
                                    </xsl:attribute>
                                </xsl:if>
                                <xs:annotation>
                                    <xs:documentation>
                                        <xsl:value-of select='mc:description'/>
                                    </xs:documentation>
                                </xs:annotation>

                            </xs:element>

                        </xsl:when>
                        <xsl:otherwise>
                            <xs:element>
                                <!--xsl:attribute name="level"><xsl:value-of select='count(ancestor::*)'/></xsl:attribute-->
                                <xsl:attribute name="name"><xsl:value-of select='translate(replace( $delcName ," ","-"),"([)]*","")'/></xsl:attribute>
                                <!--xsl:attribute name="test1">
                                <xsl:value-of select='translate(replace( @name," ","-"),"([)]","")'/>
                            </xsl:attribute-->
                                <xsl:choose>
                                    <xsl:when test="matches($dtName, '^xs:*')">
                                        <xsl:attribute name="type"><xsl:value-of select='$dtName'></xsl:value-of></xsl:attribute>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <!--xsl:attribute name="test2"><xsl:value-of select='$dtName'></xsl:value-of></xsl:attribute-->
                                        <xsl:if test='mc:dataType/@id'>
                                            <xsl:attribute name="type"><xsl:value-of select='translate(replace(replace( mc:dataType/@id, "^%SCHEMA_NAME%/dataType/(\d*)$", "placeholder-$1.1"),"placeholder", $dtlcName )," ","-") '/></xsl:attribute>
                                        </xsl:if>
                                    </xsl:otherwise>
                                </xsl:choose>
                                <xsl:if test='mc:metadata/mc:extension'>
                                    <xsl:attribute name="minOccurs">
                                        <xsl:for-each select="mc:metadata/mc:extension">
                                            <xsl:call-template name="Min" />
                                        </xsl:for-each>
                                    </xsl:attribute>
                                    <xsl:attribute name="maxOccurs">
                                        <xsl:for-each select="mc:metadata/mc:extension">
                                            <xsl:call-template name="Max" />
                                        </xsl:for-each>
                                    </xsl:attribute>
                                </xsl:if>
                                <xs:annotation>
                                    <xs:documentation>
                                        <xsl:value-of select='mc:description'/>
                                    </xs:documentation>
                                </xs:annotation>
                            </xs:element>
                        </xsl:otherwise>

                    </xsl:choose>

                </xsl:for-each>
            </xs:sequence>
        </xs:complexType>


        <!--xsl:apply-templates select="mc:dataClass" /-->
        <!-- End Recurse -->
    </xsl:template>


    <!-- Template : mc:dataElement  :: This template matches a ModelCatalogue dataElement and transforms it to complexType XSD element -->
    <xsl:template match="mc:dataElement">
        <xsl:variable name="nameValue" select="@name"/>
        <xsl:variable name="CamelCaseName">
            <xsl:call-template name="Compress">
                <xsl:with-param name="text" select="$nameValue"/>
            </xsl:call-template>
        </xsl:variable>
        <xsl:variable name="LowerCaseDTName">
            <xsl:call-template name="LowerCaseWord">
                <xsl:with-param name="text" select="mc:dataType/@name"/>
            </xsl:call-template>
        </xsl:variable>
        <xsl:variable name="dtmcid" select="mc:dataType/@id"/>

        <xs:element>
            <xsl:attribute name="name"><xsl:value-of select='translate(replace(replace(@id, "^%SCHEMA_NAME%/dataElement/(\d*)$", "placeholder-$1.2"),"placeholder",$CamelCaseName )," ","-")'/></xsl:attribute>
            <xs:annotation>
                <xs:documentation>
                    <xsl:value-of select='mc:description'/>
                </xs:documentation>
            </xs:annotation>
            <!-- Here we get the data type from the child dataType entity, if it doesn't exist then we see if there is a given xs:type and if not insert an xs:string as default -->
            <xsl:choose>
                <xsl:when test="not(matches(mc:dataType/@name, 'xs:*')) and (mc:dataType/@name != '')">
                    <xs:simpleType>
                        <xs:restriction> <!--base="dataType"-->
                            <xsl:attribute name="base">
                                <!--xsl:value-of select='translate(replace(mc:dataType/@name ," ","-"),"([)]","")'/-->
                                <xsl:value-of select='translate(replace(replace($dtmcid, "^%SCHEMA_NAME%/dataType/(\d*)$", "DataType-$1.1"),"DataType",$LowerCaseDTName),"([)]*","")'/>
                            </xsl:attribute>
                            <xs:minLength value='1' />
                        </xs:restriction>
                    </xs:simpleType>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:choose>
                        <xsl:when test="matches(mc:dataType/@name, 'xs:*')">
                            <xs:simpleType>
                                <xs:restriction>
                                    <xsl:attribute name="base">
                                        <xsl:value-of select='mc:dataType/@name'/>
                                    </xsl:attribute>
                                </xs:restriction>
                            </xs:simpleType>
                        </xsl:when>
                        <xsl:otherwise>
                            <xs:simpleType>
                                <xs:restriction>
                                    <xsl:attribute name="base">xs:string</xsl:attribute>
                                </xs:restriction>
                            </xs:simpleType>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:otherwise>
            </xsl:choose>




        </xs:element>
    </xsl:template>


    <!-- Template : mc:dataType  :: This template matches a ModelCatalogue dataType and transforms it to simpleType XSD element -->
    <xsl:template match="mc:dataType">
        <xsl:variable name="nameValue" select="@name"/>
        <xsl:variable name="refValue" select="@ref"/>
        <xsl:variable name="mcid" select="@id"/>

        <xsl:variable name="LowerCaseName">
            <xsl:call-template name="LowerCaseWord">
                <xsl:with-param name="text" select="$nameValue"/>
            </xsl:call-template>
        </xsl:variable>
        <xsl:if test="(not(matches($nameValue, 'xs:*'))) and (not(matches($refValue, 'http://www.w3.org/2001/XMLSchema#*')))">
            <xsl:if test="$mcid">
                <xs:simpleType>
                    <xsl:attribute name="name">
                        <xsl:value-of select='translate(replace(replace($mcid, "^%SCHEMA_NAME%/dataType/(\d*)$", "DataType-$1.1"),"DataType",$LowerCaseName),"([)]*","")'/>
                    </xsl:attribute>
                    <xsl:choose>
                        <xsl:when test="(child::mc:enumerations)">
                            <xsl:apply-templates select="mc:enumerations" />
                        </xsl:when>
                        <xsl:otherwise>
                            <xs:restriction base="xs:string">
                            </xs:restriction>
                        </xsl:otherwise>
                    </xsl:choose>
                </xs:simpleType>
            </xsl:if>
        </xsl:if>
    </xsl:template>
    <!-- Template : mc:enumerations  :: This template matches a ModelCatalogue enumeration - normally a component of dataType - and transforms it to part of a simpleType XSD element -->
    <xsl:template match="mc:enumerations">
        <xs:restriction base="xs:token">
            <xsl:apply-templates select="mc:enumeration" />
        </xs:restriction>
    </xsl:template>

    <xsl:template match="mc:enumeration">
        <xs:enumeration>
            <xsl:attribute name="value">
                <xsl:value-of select="@value"/>
            </xsl:attribute>
            <!--xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute-->
            <xs:annotation>
                <xs:appinfo>
                    <display-text><xsl:value-of select="."/> </display-text>
                </xs:appinfo>
            </xs:annotation>
        </xs:enumeration>
    </xsl:template>




    <!-- Template : LowerCaseWord  :: This template matches an upper-case letter and replaces it with lower case for the first and remaing characters -->
    <xsl:template name="LowerCaseWord">
        <xsl:param name="text"/>
        <xsl:value-of select="translate(substring($text,1,1),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')" />
        <xsl:value-of select="translate(substring($text,2,string-length($text)-1),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')" />
    </xsl:template>

    <xsl:template name="Compress">
        <xsl:param name="text"/>
        <xsl:call-template name="CamelCase">
            <xsl:with-param name="text" select="translate($text,'\s-,:;&amp;)(','')"/>
        </xsl:call-template>

    </xsl:template>

    <xsl:template name="CamelCase">
        <xsl:param name="text"/>
        <xsl:choose>
            <xsl:when test="contains($text,' ')">
                <xsl:call-template name="CamelCaseWord">
                    <xsl:with-param name="text" select="substring-before($text,' ')"/>
                </xsl:call-template>
                <xsl:text> </xsl:text>
                <xsl:call-template name="CamelCase">
                    <xsl:with-param name="text" select="substring-after($text,' ')"/>
                </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
                <xsl:call-template name="CamelCaseWord">
                    <xsl:with-param name="text" select="$text"/>
                </xsl:call-template>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="CamelCaseWord">
        <xsl:param name="text"/>
        <xsl:value-of select="translate(substring($text,1,1),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')" />
        <xsl:value-of select="translate(substring($text,2,string-length($text)-1),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')" />
    </xsl:template>

    <xsl:template match="@*">
        <xsl:attribute name="{name()}">
            <xsl:value-of select="translate(., ' ', '')" />
        </xsl:attribute>
    </xsl:template>

    <xsl:template name="string-replace-all">
        <xsl:param name="text" />
        <xsl:param name="replace" />
        <xsl:param name="by" />
        <xsl:choose>
            <xsl:when test="contains($text, $replace)">
                <xsl:value-of select="substring-before($text,$replace)" />
                <xsl:value-of select="$by" />
                <xsl:call-template name="string-replace-all">
                    <xsl:with-param name="text"
                                    select="substring-after($text,$replace)" />
                    <xsl:with-param name="replace" select="$replace" />
                    <xsl:with-param name="by" select="$by" />
                </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$text" />
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>


    <xsl:template match="node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template name="Min">
        <xsl:param name="text"/>
        <xsl:choose>
            <xsl:when test="@key='Min Occurs'">
                <xsl:value-of select="."/>
            </xsl:when>
            <xsl:otherwise></xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="Max">
        <xsl:param name="text"/>
        <xsl:choose>
            <xsl:when test="@key='Max Occurs'">
                <xsl:value-of select="."/>
            </xsl:when>
            <xsl:otherwise></xsl:otherwise>
        </xsl:choose>
    </xsl:template>



</xsl:stylesheet>
