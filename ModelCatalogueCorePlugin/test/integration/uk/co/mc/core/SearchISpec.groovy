package uk.co.mc.core

import groovy.util.slurpersupport.GPathResult
import org.codehaus.groovy.grails.web.json.JSONElement
import spock.lang.Shared
import spock.lang.Unroll
import uk.co.mc.core.util.DefaultResultRecorder
import uk.co.mc.core.util.ResultRecorder

/**
 * Created by adammilward on 05/02/2014.
 */
class SearchISpec extends AbstractIntegrationSpec{



    def "placeholder test"(){
        expect:
        true
    }

 //runs ok in integration test (test-app :integration), fails as part of test-app (Grails Bug) - uncomment to run
//RE: http://jira.grails.org/browse/GRAILS-11047?page=com.atlassian.jira.plugin.system.issuetabpanels:comment-tabpanel


    @Shared
    def grailsApplication, elasticSearchService

    def setupSpec(){
        loadFixtures()
        RelationshipType.initDefaultRelationshipTypes()
        def de = DataElement.findByName("DE_author1")
        def vd = ValueDomain.findByName("value domain Celsius")
        def cd = ConceptualDomain.findByName("public libraries")
        def mod = Model.findByName("book")

        Relationship.link(cd, mod, RelationshipType.findByName("context"))
        Relationship.link(de, vd, RelationshipType.findByName("instantiation"))
        Relationship.link(mod, de, RelationshipType.findByName("containment"))

        elasticSearchService.index()
    }

    def cleanup(){
    }

    @Unroll
    def "#no - json search for resource"(){
        ResultRecorder recorder = DefaultResultRecorder.create(
                "../ModelCatalogueCorePlugin/target/xml-samples/modelcatalogue/core",
                "../ModelCatalogueCorePlugin/test/js/modelcatalogue/core",
                className[0].toLowerCase() + className.substring(1)
        )

        JSONElement json
        GPathResult xml

        expect:
        def domain = grailsApplication.getArtefact("Domain", "uk.co.mc.core.${className}")?.getClazz()
        def expectedResult = domain.findByName(expectedResultName)

        when:
        controller.response.format = response
        controller.params.search = searchString
        controller.search()

        String recordName = "searchElement${no}"

        CatalogueElement.get(37)
        CatalogueElement.get(28)
        CatalogueElement.get(8)

        if(response=="json"){
            json = controller.response.json
            recorder.recordResult recordName, json
        }else{
            xml = controller.response.xml
            recorder.recordResult recordName, xml
        }
        then:

        if(json){
            assert json
            assert json.get(0).id == expectedResult.id
            assert json.get(0).name == expectedResult.name
        }else if(xml){
            assert xml
            def list = xml.children()
            assert list[0].@id == expectedResult.id
            assert list[0].name == expectedResult.name
        }else{
            throw new AssertionError("no result returned")
        }

        where:

        no| className           | controller                          | searchString                    | response  | expectedResultName
//        1 | "DataType"          | new DataTypeController()            | "boolean"                       | "json"    | "boolean"
//        2 | "DataType"          | new DataTypeController()            | "xdfxdf"                        | "json"    | "boolean"
//        3 | "DataType"          | new DataTypeController()            | "boolean"                       | "xml"     | "boolean"
//        4 | "DataType"          | new DataTypeController()            | "xdfxdf"                        | "xml"     | "boolean"
        5 | "DataElement"       | new DataElementController()         | "XXX_1"                         | "json"    | "DE_author1"
        6 | "DataElement"       | new DataElementController()         | "XXX_1"                         | "xml"     | "DE_author1"
        7 | "ConceptualDomain"  | new ConceptualDomainController()    | "domain for public libraries"   | "json"    | "public libraries"
        8 | "ConceptualDomain"  | new ConceptualDomainController()    | "domain for public libraries"   | "xml"     | "public libraries"
        9 | "EnumeratedType"    | new EnumeratedTypeController()      | "sub1"                          | "json"    | "sub1"
       10 | "EnumeratedType"    | new EnumeratedTypeController()      | "sub1"                          | "xml"     | "sub1"
       11 | "MeasurementUnit"   | new MeasurementUnitController()     | "°C"                            | "json"    | "Degrees of Celsius"
       12 | "MeasurementUnit"   | new MeasurementUnitController()     | "°C"                            | "xml"     | "Degrees of Celsius"
       13 | "Model"             | new ModelController()               | "Jabberwocky"                   | "json"    | "chapter1"
       14 | "Model"             | new ModelController()               | "Jabberwocky"                   | "xml"     | "chapter1"
       15 | "ValueDomain"       | new ValueDomainController()         | "domain Celsius"                | "json"    | "value domain Celsius"
       16 | "ValueDomain"       | new ValueDomainController()         | "domain Celsius"                | "xml"     | "value domain Celsius"
       17 | "RelationshipType"  | new RelationshipTypeController()    | "context"                       | "json"    | "context"
       18 | "RelationshipType"  | new RelationshipTypeController()    | "context"                       | "xml"     | "context"
       19 | "ValueDomain"       | new ValueDomainController()         | "°F"                            | "xml"     | "value domain Fahrenheit"
       20 | "EnumeratedType"    | new EnumeratedTypeController()      | "male"                          | "json"    | "gender"
       21 | "EnumeratedType"    | new EnumeratedTypeController()      | "male"                          | "xml"     | "gender"
       22 | "DataElement"       | new DataElementController()         | "metadata"                      | "xml"     | "DE_author1"

    }

}
