package org.modelcatalogue.core.b

import static org.modelcatalogue.core.geb.Common.*

import org.modelcatalogue.core.geb.AbstractModelCatalogueGebSpec
import spock.lang.Stepwise

@Stepwise
class DataModelWizardSpec extends AbstractModelCatalogueGebSpec {

    static final String classificationWizzard = 'div.create-classification-wizard'
    static final String name = 'div.create-classification-wizard #name'
    static final String description ='div.create-classification-wizard #description'
    static final String modelCatalogueId ='div.create-classification-wizard #modelCatalogueId'
    static final String stepImports = "#step-imports"
    static final String stepFinish = "#step-finish"
    static final String exitButton = "#exit-wizard"


    def "go to login"() {
        login admin

        expect:
        waitFor(120) { browser.title == 'Data Models' }
    }

    def "add new data model"() {
        click createActionInInfiniteList

        expect: 'the model dialog opens'
        check classificationWizzard displayed

        when:
        fill name with "New Data Model"
        fill modelCatalogueId with "http://www.example.com/${UUID.randomUUID().toString()}"
        fill description with "Description of Data Model"

        then:
        check stepImports enabled

        when:
        click stepImports

        then:
        check stepImports has 'btn-primary'

        when:
        fill name with 'NHIC'
        selectCepItemIfExists()


        and:
        click stepFinish

        then:
        check 'div.messages-panel span', text: "Data Model New Data Model created"

        when:
        click exitButton

        then:
        check rightSideTitle is 'New Data Model DRAFT'
        check tabTotal('imports') is '1'
    }

    def "finalize element"() {
        check backdrop gone
        when: "finalize is clicked"
        click finalize

        then: "modal prompt is displayed"
        check confirm displayed

        when: "ok is clicked"
        click OK

        then: "the element is finalized"
        check status is 'FINALIZED'
    }

    def "create new version of the element"() {
        check backdrop gone
        when: "new version is clicked"
        click newVersion

        then: "modal prompt is displayed"
        check confirm displayed

        when: "ok is clicked"
        click OK

        then: "the element new draft version is created"
        check status is 'DRAFT'
    }

    def "deprecate the data model"() {
        waitUntilModalClosed()
        when: "deprecate action is clicked"
        click archive

        then: "modal prompt is displayed"
        check confirm displayed

        when: "ok is clicked"
        click OK

        then: "the element is now deprecated"
        check subviewStatus is 'DEPRECATED'

    }

    def "hard delete the data model"() {
        check backdrop gone
        when: "delete action is clicked"
        click delete

        then: "modal prompt is displayed"
        check confirm displayed

        when: "ok is clicked"
        click OK

        then: "you are redirected to the data models page"
        waitFor(120) { browser.title == 'Data Models' }

    }


}