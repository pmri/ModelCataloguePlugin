package org.modelcatalogue.core.xml

import groovy.xml.XmlUtil
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.custommonkey.xmlunit.DetailedDiff
import org.custommonkey.xmlunit.Diff
import org.custommonkey.xmlunit.XMLUnit
import org.modelcatalogue.builder.api.CatalogueBuilder
import org.modelcatalogue.builder.xml.XmlCatalogueBuilder
import org.modelcatalogue.core.*
import org.modelcatalogue.core.util.builder.DefaultCatalogueBuilder
import org.modelcatalogue.integration.xml.CatalogueXmlLoader
import spock.lang.Ignore
import spock.lang.Shared

class CatalogueXmlImportSpec extends AbstractIntegrationSpec {

    @Shared GrailsApplication grailsApplication

    CatalogueBuilder builder
    CatalogueXmlLoader loader
    def dataModelService
    def elementService



    def setup() {
        initCatalogue()

    }


   def "load xml - make change - load again"(){

        DefaultCatalogueBuilder builder = new DefaultCatalogueBuilder(dataModelService, elementService, true)
        loader = new CatalogueXmlLoader(builder)

        when:
        InputStream nhic = getClass().getResourceAsStream('nhic.catalogue.xml')
        loader.load(nhic)

        then:
        DataModel.findByName("NHIC")


    }





    def "load cancer model - large model for testing purposes"(){

        DefaultCatalogueBuilder builder = new DefaultCatalogueBuilder(dataModelService, elementService, true)
        loader = new CatalogueXmlLoader(builder)

        when:
        InputStream can = getClass().getResourceAsStream('cancer.catalogue.xml')
        loader.load(can)

        then:
        DataModel.findByName("Cancer Model")
        DataClass.findByName("Participant Information (GMC Clinic Registration)")

    }


    def "load cosd model - large model for testing purposes"(){

        DefaultCatalogueBuilder builder = new DefaultCatalogueBuilder(dataModelService, elementService, true)
        loader = new CatalogueXmlLoader(builder)

        when:
        InputStream can = getClass().getResourceAsStream('cosd.catalogue.xml')
        loader.load(can)

        then:
        DataModel.findByName("COSD")
        DataClass.findByName("Colorectal")

    }

}
