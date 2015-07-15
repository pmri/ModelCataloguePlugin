package x.org.modelcatalogue.core.util

import grails.test.spock.IntegrationSpec
import grails.util.Holders
import org.modelcatalogue.core.*
import org.modelcatalogue.core.publishing.DraftContext
import org.modelcatalogue.builder.api.CatalogueBuilder
import org.modelcatalogue.core.util.builder.DefaultCatalogueBuilder
import spock.lang.Issue

class CatalogueBuilderIntegrationSpec extends IntegrationSpec {

    def initCatalogueService
    def dataModelService
    def elementService

    Set<CatalogueElement> created = []

    def setup() {
        initCatalogueService.initDefaultRelationshipTypes()
    }
    
    def "creates new classification with given name, namespace and description"() {
        build {
            classification(name: 'TestSchema', namespace: 'http://www.w3.org/2001/TestSchema') {
                description '''
                    This is a test schema which is just for test purposes!
                '''
            }
        }

        expect:
        DataModel.findByName('TestSchema')
        DataModel.findByName('TestSchema').description == 'This is a test schema which is just for test purposes!'
        DataModel.findByModelCatalogueId('http://www.w3.org/2001/TestSchema')
    }

    def "reuse existing classification by name"() {
        DataModel c = new DataModel(name: 'ExistingSchema', status: org.modelcatalogue.core.api.ElementStatus.DEPRECATED).save(failOnError: true)

        build {
            classification(name: 'ExistingSchema', namespace: 'http://www.w3.org/2001/ExistingSchema') {
                description '''
                    This is a test schema which is just for test purposes!
                '''
            }
        }

        expect:
        created.first().latestVersionId == c.refresh().latestVersionId
    }

    def "reuse existing classification by id"() {
        DataModel c = new DataModel(name: 'SchemaWithId', modelCatalogueId: 'http://www.example.com/SWI').save(failOnError: true)

        build {
            classification(name: 'NotUniqueName', id: 'http://www.example.com/SWI') {
                description '''
                    This is a test schema which is just for test purposes!
                '''
            }
        }

        expect:
        created.first() == c
        created.first().name == 'NotUniqueName'
    }

    def "complain if classification name is missing"() {
        when:
        build {
            classification namespace: 'http://www.w3.org/2001/TestSchema'
        }

        then:
        IllegalArgumentException e = thrown(IllegalArgumentException)
        e.message.startsWith "Cannot create element abstraction from"
    }
    
    def "creates new measurement unit with given name"() {
        build {
            measurementUnit(name: 'TestUnit', symbol: 'TU') {
                description '''
                    This is a test unit which is just for test purposes!
                '''
            }
        }

        expect:
        MeasurementUnit.findByName('TestUnit')
        MeasurementUnit.findByName('TestUnit').description == 'This is a test unit which is just for test purposes!'
    }

    def "reuse existing measurement unit by name"() {
        MeasurementUnit unit = new MeasurementUnit(name: 'ExistingUnit', status: org.modelcatalogue.core.api.ElementStatus.DEPRECATED).save(failOnError: true)

        build {
            measurementUnit(name: 'ExistingUnit', symbol: 'EU') {
                description '''
                    This is a test unit which is just for test purposes!
                '''
            }
        }

        expect:
        created.first().latestVersionId == unit.refresh().latestVersionId
    }

    def "creates only one measurement unit as measurement unit name is unique"() {
        MeasurementUnit unit = new MeasurementUnit(name: 'ExistingUnit2', status: org.modelcatalogue.core.api.ElementStatus.DEPRECATED).save(failOnError: true, flush: true)

        build {
            classification(name: "TestClassificationA") {
                measurementUnit(name: 'ExistingUnit2', symbol: 'EU2') {
                    description '''
                        This is a test unit which is just for test purposes!
                    '''
                }
            }
            classification(name: "TestClassificationB") {
                measurementUnit(name: 'ExistingUnit2', symbol: 'EU2') {
                    description '''
                        This is a test unit which is just for test purposes!
                    '''
                }
            }
        }

        expect:
        created.find { it instanceof  MeasurementUnit}.latestVersionId == unit.refresh().latestVersionId
        created.size() == 3
        created.any { it.name == 'TestClassificationA'}
        created.any { it.name == 'TestClassificationB'}
        created.any { it.name == 'ExistingUnit2'}
    }

    def "complain if measurement unit name is missing"() {
        when:
        build {
            measurementUnit symbol: 'TU'
        }

        then:
        IllegalArgumentException e = thrown(IllegalArgumentException)
        e.message.startsWith "Cannot create element abstraction from"
    }

    def "creates new data element with given name"() {
        build {
            dataElement(name: 'TestElement') {
                description '''
                    This is a test element which is just for test purposes!
                '''
            }
        }

        expect:
        DataElement.findByName('TestElement')
        DataElement.findByName('TestElement').description == 'This is a test element which is just for test purposes!'
    }

    def "reuse existing data element unit by name"() {
        DataElement unit = new DataElement(name: 'ExistingElement', status: org.modelcatalogue.core.api.ElementStatus.DEPRECATED).save(failOnError: true)

        build {
            dataElement(name: 'ExistingElement') {
                description '''
                    This is a test element which is just for test purposes!
                '''
            }
        }

        expect:
        created.first().latestVersionId == unit.refresh().latestVersionId
    }

    def "complain if data element name is missing"() {
        when:
        build {
            dataElement([:])
        }

        then:
        IllegalArgumentException e = thrown(IllegalArgumentException)
        e.message.startsWith "Cannot create element abstraction from"
    }

    def "creates new model with given name"() {
        build {
            model(name: 'TestModel') {
                description '''
                    This is a test model which is just for test purposes!
                '''
            }
        }

        expect:
        DataClass.findByName('TestModel')
        DataClass.findByName('TestModel').description == 'This is a test model which is just for test purposes!'
    }

    def "reuse existing model by name"() {
        DataClass unit = new DataClass(name: 'ExistingModel', status: org.modelcatalogue.core.api.ElementStatus.DEPRECATED).save(failOnError: true)

        build {
            model(name: 'ExistingModel') {
                description '''
                    This is a test model which is just for test purposes!
                '''
            }
        }

        expect:
        created.first().latestVersionId == unit.refresh().latestVersionId
    }

    def "complain if model name is missing"() {
        when:
        build {
            model([:])
        }

        then:
        IllegalArgumentException e = thrown(IllegalArgumentException)
        e.message.startsWith "Cannot create element abstraction from"
    }

    def "creates new value domain with given name"() {
        build {
            valueDomain name: 'Domain'
        }

        expect:
        ValueDomain.findByName('Domain')
    }

    def "reuses existing value domain with given name in classification"() {
        DataModel cls  = new DataModel(name: 'Some').save(failOnError: true)
        ValueDomain domain1 = new ValueDomain(name: 'SomeDomain').save(failOnError: true)
        ValueDomain domain2 = new ValueDomain(name: 'SomeDomain').save(failOnError: true)

        cls.addToDefines(domain1)

        build {
            valueDomain name: 'SomeDomain'
            classification(name: 'Some') {
                valueDomain name: 'SomeDomain'
            }
        }

        expect:
        ValueDomain.countByName('SomeDomain') == 2

        cleanup:
        [domain2].each {
            it.beforeDelete()
            it.delete()
        }
    }

    def "complain if value domain name is missing"() {
        when:
        build {
            valueDomain rule: 'is number'
        }

        then:
        IllegalArgumentException e = thrown(IllegalArgumentException)
        e.message.startsWith "Cannot create element abstraction from"
    }

    def "specify rule as regex"() {
        when:
        build {
            valueDomain name: 'with regex', {
                regex(/\w+/)
            }
        }

        ValueDomain domain = ValueDomain.findByName('with regex')

        then:
        domain
        domain.rule == """x ==~ /\\w+/"""
    }

    def "add extensions"() {
        build {
            dataType name: 'test:blah', {
                ext 'Local Identifier', 'BLAH'
                ext Alias: 'test:TYPE'
                ext foo: 'bar'
            }
        }

        when:
        DataType blah = DataType.findByName('test:blah')

        then:
        blah
        blah.ext.size() == 3
        blah.ext.containsKey('Local Identifier')
        blah.ext.containsKey('Alias')
        blah.ext.containsKey('foo')
    }

    def "creates new data type with given name"() {
        build {
            dataType name: 'Type'
        }

        expect:
        DataType.findByName('Type')
    }

    def "reuses existing data type with given name"() {
        DataModel cls  = new DataModel(name: 'Some').save(failOnError: true)
        DataType dt1 = new DataType(name: 'SomeType').save(failOnError: true)
        DataType dt2 = new DataType(name: 'SomeType').save(failOnError: true)

        cls.addToDefines(dt1)

        build {
            dataType name: 'SomeType'
            classification(name: 'Some') {
                dataType name: 'SomeType'
            }
        }

        expect:
        DataType.countByName('SomeType') == 2

        cleanup:
        [dt2].each {
            it.beforeDelete()
            it.delete()
        }
    }

    def "complain if data type name is missing"() {
        when:
        build {
            dataType status: org.modelcatalogue.core.api.ElementStatus.FINALIZED
        }

        then:
        IllegalArgumentException e = thrown(IllegalArgumentException)
        e.message.startsWith "Cannot create element abstraction from"
    }

    def "do not complain if data type name is missing but inside value domain"() {
        build {
            valueDomain(name: 'test:number') {
                dataType()
            }
        }

        expect:
        DataType.findByName('test:number')
        ValueDomain.findByName('test:number')
        ValueDomain.findByName('test:number').dataType
        ValueDomain.findByName('test:number').dataType == DataType.findByName('test:number')
    }



    def "elements are added to classification"() {
        build {
            classification(name: 'TestSchema') {
                valueDomain(name: 'test:string domain') {
                    dataType(name: 'test:string')
                }
                valueDomain(name: 'test:token domain') {
                    basedOn 'test:string domain'
                }
            }
        }

        DataModel schema       = DataModel.findByName('TestSchema')
        ValueDomain stringDomain    = ValueDomain.findByName('test:string domain')
        ValueDomain tokenDomain     = ValueDomain.findByName('test:token domain')
        DataType stringType         = DataType.findByName('test:string')

        expect:
        schema
        stringDomain
        tokenDomain
        stringType

        schema.defines
        stringDomain    in schema.defines
        tokenDomain     in schema.defines
        stringType      in schema.defines

        stringDomain.dataType == stringType

        tokenDomain.isBasedOn
        tokenDomain.isBasedOn.contains stringDomain

    }

    def "complex model"() {
        build {
            automatic valueDomain
            automatic dataType

            classification name: 'Complex', {
                id 'http://www.example.com/complex-model'

                model name: "Complex Grand Parent", {
                    model name: "Complex Parent", {
                        model name: "Complex Child", {
                            dataElement name: "Complex Element 1"
                            dataElement name: "Complex Element 2", {
                                valueDomain name: "Complex Domain 2", {
                                    dataType enumerations: [yes: 'Yes', no: 'No']
                                    measurementUnit name: 'Unit'
                                }
                            }
                        }
                    }

                    model name: "Complex Sibling", {
                        dataElement name: "Sibling Element"
                    }
                }
            }
        }

        expect:
        DataClass.findByName('Complex Grand Parent')
        DataClass.findByName('Complex Grand Parent').parentOf
        DataClass.findByName('Complex Grand Parent').parentOf.size() == 2

        DataClass.findByName('Complex Child')
        DataClass.findByName('Complex Child').contains
        DataClass.findByName('Complex Child').contains.size() == 2

        ValueDomain.findByName('Complex Element 1')
    }

    def "create generic relationship"() {
        build {
            classification name: "Other123", {
                valueDomain name: 'WD40'
            }
            classification name: "Other234", {
                valueDomain name: 'VDRel1'
                valueDomain name: 'VDRel2'
                valueDomain name: 'VDRel3'
                valueDomain name: 'VDRel4', {
                    rel 'synonym'   to      valueDomain called 'VDRel2'
                    rel 'synonym'   from    'VDRel1'
                    rel 'relatedTo' to      'Other123', 'WD40'
                    rel 'base'      to      'Other123', 'WD40'
                }
            }
        }

        expect:
        ValueDomain.findByName('VDRel4')
        ValueDomain.findByName('VDRel4').countRelationshipsByType(RelationshipType.readByName('synonym'))   == 2
        ValueDomain.findByName('VDRel4').countRelationshipsByType(RelationshipType.readByName('base'))      == 1
        ValueDomain.findByName('VDRel4').countRelationshipsByType(RelationshipType.readByName('relatedTo')) == 1
    }

    def "creates new version of the element"() {
        build {
            classification(name: "NewVersion1") {
                // creates finalized model
                model name: "ModelNV1", id: "http://www.example.com/models/ModelNV1"
            }
        }

        created.each {
            it.publish(elementService)
        }

        build {
            classification(name: "NewVersion2") {
                model name: "ModelNVX1", id: "http://www.example.com/models/ModelNVX1"
            }
        }

        expect:
        DataClass.findByName("ModelNV1")?.status == org.modelcatalogue.core.api.ElementStatus.FINALIZED

        when:
        build {
            classification(name: "NewVersion1") {
                // this should create new version with different name
                model name: "ModelNV2", id: "http://www.example.com/models/ModelNV1"
            }
        }


        then: "new model is draft"
        DataClass.findByName("ModelNV2")?.modelCatalogueId  == "http://www.example.com/models/ModelNV1"
        DataClass.findByName("ModelNV2")?.latestVersionId   == DataClass.findByName("ModelNV1")?.id
        DataClass.findByName("ModelNV2")?.status            == org.modelcatalogue.core.api.ElementStatus.DRAFT

        and: "the old model is still finalized"
        DataClass.findByName("ModelNV1")?.status            == org.modelcatalogue.core.api.ElementStatus.FINALIZED
        DataClass.findByName("ModelNV1")?.modelCatalogueId  == "http://www.example.com/models/ModelNV1"

        and: "there are two NewVersion1 classifications at the moment"
        DataModel.countByName('NewVersion1')                                   == 2
        DataModel.countByNameAndStatus('NewVersion1', org.modelcatalogue.core.api.ElementStatus.DRAFT)     == 1
        DataModel.countByNameAndStatus('NewVersion1', org.modelcatalogue.core.api.ElementStatus.FINALIZED) == 1


    }

    def "adds metadata to nested relationship like child model"() {
        build {
            model(name: "Parent 007") {
                model(name: "Child 008") {
                    relationship {
                        ext "Min. Occurs", "1"
                    }
                }
            }
        }

        when:
        Relationship rel = Relationship.findBySourceAndDestination(DataClass.findByName('Parent 007'), DataClass.findByName('Child 008'))

        then:
        rel
        rel.ext
        rel.ext.size() == 1
        rel.ext['Min. Occurs'] == '1'


        when:
        elementService.finalizeElement(DataClass.findByName('Parent 007'))


        then:
        DataClass.findByName('Parent 007').status == org.modelcatalogue.core.api.ElementStatus.FINALIZED

        when:
        build {
            model(name: "Parent 007") {
                model(name: "Child 008") {
                    relationship {
                        ext "Min. Occurs", "0"
                    }
                }
            }
        }

        then:
        DataClass.findByName('Parent 007', [sort: 'versionNumber', order: 'desc']).status == org.modelcatalogue.core.api.ElementStatus.DRAFT
    }


    def "migrates hierarchy relationship to new draft version"() {
        build {
            model(name: 'MHR ROOT') {
                model(name: 'MHR L1') {
                    model(name: 'MHR L2') {
                        model(name: 'MHR L3')
                    }
                }
            }
        }

        elementService.finalizeElement(DataClass.findByName('MHR ROOT'))

        DataClass l1Finalized = DataClass.findByName('MHR L1')

        expect:
        l1Finalized
        l1Finalized.status == org.modelcatalogue.core.api.ElementStatus.FINALIZED
        l1Finalized.countParentOf() == 1

        when:
        DataClass l1Draft = elementService.createDraftVersion(l1Finalized, DraftContext.userFriendly())

        then:
        l1Draft
        l1Draft.status == org.modelcatalogue.core.api.ElementStatus.DRAFT
        l1Draft.countParentOf() == 1

        when:
        DataClass l3Finalized = DataClass.findByName('MHR L3')

        then:
        l3Finalized
        l3Finalized.countChildOf() == 1

        when:
        DataClass l3Draft = elementService.createDraftVersion(l3Finalized, DraftContext.userFriendly())

        then:
        l3Draft
        l3Draft.status == org.modelcatalogue.core.api.ElementStatus.DRAFT
        l3Draft.countChildOf()  == 1
        l1Draft.countParentOf() == 1
    }

    private void build(@DelegatesTo(CatalogueBuilder) Closure cl) {
        DefaultCatalogueBuilder defaultCatalogueBuilder = new DefaultCatalogueBuilder(dataModelService, elementService)
        defaultCatalogueBuilder.build cl
        created = defaultCatalogueBuilder.created
    }


    def "order from builder is persisted"() {
        when:
        build {
            dataModel (name: 'Order Test') {
                dataClass (name: 'OT Parent') {
                    dataElement (name: 'OT Child 002')
                    dataElement (name: 'OT Child 001')
                    dataElement (name: 'OT Child 004')
                    dataElement (name: 'OT Child 003')
                }
            }
        }

        then:
        DataClass.findByName('OT Parent').contains*.name == [
                'OT Child 002',
                'OT Child 001',
                'OT Child 004',
                'OT Child 003'
        ]

        when:
        build {
            dataModel (name: 'Order Test') {
                dataClass (name: 'OT Parent') {
                    dataElement (name: 'OT Child 001')
                    dataElement (name: 'OT Child 002')
                    dataElement (name: 'OT Child 003')
                    dataElement (name: 'OT Child 004')
                }
            }
        }

        then:
        DataClass.findByName('OT Parent').contains*.name == [
                'OT Child 001',
                'OT Child 002',
                'OT Child 003',
                'OT Child 004'
        ]
    }

    def "define id as closure"() {
        Object old = Holders.grailsApplication.config.grails.serverURL
        Holders.grailsApplication.config.grails.serverURL = "http://localhost:8080/ModelCatalogueCorePluginTestApp"
        build {
            classification(name: 'CS4ID') {
                id { String name, Class type ->
                    "http://www.example.com/classification/${type.simpleName[0].toLowerCase()}/$name"
                }
                model(name: "Model_ID")
            }
        }

        DataClass model = DataClass.findByName('Model_ID')

        expect:
        model.modelCatalogueId == "http://www.example.com/classification/d/Model_ID"

        cleanup:
        Holders.grailsApplication.config.grails.serverURL = old
    }

    @Issue("MET-587")
    def "remove child model when missing"() {
        when:
        build {
            classification(name: "C4RMC") {
                model (name: 'C4RMC Parent') {
                    model (name: 'C4RMC Child 1')
                    model (name: 'C4RMC Child 2')
                    model (name: 'C4RMC Child 3')
                }
            }
        }

        DataModel c4rmc = DataModel.findByName('C4RMC')
        DataClass c4rmcParent = DataClass.findByName('C4RMC Parent')
        then:
        c4rmc
        c4rmcParent
        c4rmcParent.countParentOf() == 3

        when: "model is removed from finalized element"
        elementService.finalizeElement(c4rmc)
        c4rmc.status == org.modelcatalogue.core.api.ElementStatus.FINALIZED

        build {
            classification(name: "C4RMC") {
                model (name: 'C4RMC Parent') {
                    model (name: 'C4RMC Child 2')
                    model (name: 'C4RMC Child 3')
                }
            }
        }

        DataClass draft = DataClass.findByNameAndStatus('C4RMC Parent', org.modelcatalogue.core.api.ElementStatus.DRAFT)

        then: "creation of draft should be triggered for finalized element"
        draft
        draft.countParentOf() == 2

        when: "model is removed from draft element"
        elementService.finalizeElement(c4rmc)
        c4rmc.status == org.modelcatalogue.core.api.ElementStatus.FINALIZED

        build {
            classification(name: "C4RMC") {
                model (name: 'C4RMC Parent') {
                    model (name: 'C4RMC Child 3')
                }
            }
        }

        draft = DataClass.findByNameAndStatus('C4RMC Parent', org.modelcatalogue.core.api.ElementStatus.DRAFT)

        then: "the relationship should be removed from the element"
        draft
        draft.countParentOf() == 1
    }

    @Issue("MET-620")
    def "finalizes model after creation"() {
        when:
        build {
            model(name: 'Parent Model 4 Finalization') {
                status finalized
                model(name: 'Child Model 4 Finalization')
            }
        }
        then:
        noExceptionThrown()
        DataClass.findByName('Parent Model 4 Finalization').status == org.modelcatalogue.core.api.ElementStatus.FINALIZED
        DataClass.findByName('Child Model 4 Finalization').status == org.modelcatalogue.core.api.ElementStatus.FINALIZED
    }

    def "can call relationship closure more than once"() {
        String name = 'Model for Double Relationship Call'
        when:
        build {
            model(name: name) {
                model(name: "$name Child") {
                    relationship {
                        ext 'one', '1'
                    }
                    relationship {
                        archived = true
                    }
                }
            }
        }

        DataClass parent = created.find { it.name == name }

        then:
        parent
        parent.parentOfRelationships
        parent.parentOfRelationships.size() == 1
        parent.parentOfRelationships[0].archived
        parent.parentOfRelationships[0].ext['one'] == '1'
    }

    def "don't get misconfigured from other relationship calls"() {
        String name = 'Model for Misconfigured Relationship Call'
        String ch1Name = "$name Child 1"
        String ch2Name = "$name Child 2"
        when:
        build {
            model(name: name) {
                model(name: ch1Name) {
                    relationship {
                        ext 'one', '1'
                        ext 'two', '2'
                    }
                }

                model(name: ch2Name) {
                    relationship {
                        ext 'two', 'II'
                        ext 'three', 'III'
                    }
                }
            }
        }
        DataClass parent = created.find { it.name == name } as DataClass

        then:
        parent

        when:
        Relationship ch1 = parent.outgoingRelationships.find { it.destination.name == ch1Name }
        Relationship ch2 = parent.outgoingRelationships.find { it.destination.name == ch2Name }

        then:
        ch1
        ch1.ext.one == '1'
        ch1.ext.two == '2'
        ch2
        ch2.ext.two == 'II'
        ch2.ext.three == 'III'
    }

}
