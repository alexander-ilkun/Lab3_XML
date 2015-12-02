<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:f="flowerNamespace"
                xmlns:cf="conFlowerNamespace"
                xmlns:vp="visParNamespace">

    <xsl:output method="html" />

    <xsl:template match="/">
        <html>
            <head>
                <title>Florizm</title>
            </head>
            <body>
                <xsl:apply-templates />
            </body>
        </html>
    </xsl:template>

    <xsl:template match="f:Flower">
        <table border="1">
            <tr>
                <th rowspan="3">Name</th>
                <th rowspan="3">Quantity</th>
                <th rowspan="3">Soil</th>
                <th rowspan="3">Origin</th>
                <th rowspan="3">Multiplying</th>
                <th colspan="6">VisPar</th>
                <th colspan="3">Growing tips</th>
            </tr>
            <tr>
                <th colspan="3">stem</th>
                <th colspan="3">leaf</th>
                <th rowspan="2">Temperature</th>
                <th rowspan="2">Photophilous</th>
                <th rowspan="2">Watering</th>
            </tr>
					
            <tr>
                <th>r</th>
                <th>g</th>
                <th>b</th>
                <th>r</th>
                <th>g</th>
                <th>b</th>                        
            </tr>
            <xsl:for-each select="f:concreteFlower">
                <tr>
                    <td>
                        <xsl:value-of select="@name"/>
                    </td>
                    <td>
                        <xsl:value-of select="@quantity"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:soil"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:origin"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:multiplying"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:visualParameters/vp:stem/vp:r"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:visualParameters/vp:stem/vp:g"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:visualParameters/vp:stem/vp:b"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:visualParameters/vp:leaf/vp:r"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:visualParameters/vp:leaf/vp:g"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:visualParameters/vp:leaf/vp:b"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:growingTips/cf:temperature"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:growingTips/cf:photophilous"/>
                    </td>
                    <td>
                        <xsl:value-of select="cf:growingTips/cf:watering"/>
                    </td>
                </tr>
            </xsl:for-each>
        </table>

    </xsl:template>
	
</xsl:stylesheet>