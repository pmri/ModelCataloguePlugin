package org.modelcatalogue.core.dataarchitect

import groovy.json.JsonSlurper

/**
 * James Welch, A.Milward
 */
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.builder.api.CatalogueBuilder

class UmljService {

    def dataModelService, elementService

    static transactional = false

    void importUmlDiagram(CatalogueBuilder builder, InputStream is, String name, DataModel classification) {
        log.info "Parsing Umlj file for ${name}"
        def slurper = new JsonSlurper()
        def result  = slurper.parse(new BufferedReader(new InputStreamReader(is)))
        def umlFile = new StarUMLDiagram(result)
        generateCatalogueElements(builder, umlFile, classification)
    }


    protected void generateCatalogueElements(CatalogueBuilder builder, StarUMLDiagram umlFile, DataModel clsf) {
        builder.build {
            dataModel(name: clsf.name) {
                globalSearchFor dataType
                dataClass(name: clsf.name){
                    umlFile.topLevelClasses.each { String id, Map cls ->
                        createClasses(builder, cls, umlFile)
                    }
                }
            }
        }

    }


    static createDataType(CatalogueBuilder builder, Map att, StarUMLDiagram umlFile) {

            if (!(att.type instanceof String) && att.type?.$ref && umlFile.allDataTypes.get(att?.type?.$ref)) {
                // Find highest supertype
                def currType = umlFile.allDataTypes.get(att.type?.$ref)
                while (currType.ownedElements?.findAll({ oe -> oe._type.equals("UMLGeneralization") }) != null) {
                    currType = umlFile.allDataTypes.get(currType.ownedElements.findAll({ oe -> oe._type.equals("UMLGeneralization") }).get(0).target?.$ref)
                }

                return builder.dataType(name: currType.name.toString())

            }

            if (!(att.type instanceof String) && att.type?.$ref && umlFile.allEnumerations.get(att.type?.$ref)) {
                def enumeration = umlFile.allEnumerations.get(att.type?.$ref)
                def enumMap = [:]
                enumeration.literals.each { ev ->
                    enumMap.put(ev.name, ev.documentation)
                }

                return builder.dataType(name: enumeration.name, enumerations: enumMap)

            } else if (att.type instanceof String) {
                if (att.type == "") att.type = "xs:string"

                return builder.dataType(name: att.type)
        }
    }



    protected createClasses(CatalogueBuilder builder, Map cls, StarUMLDiagram umlFile, ArrayList<Object> carried_forward_atts = new ArrayList<Object>(), ArrayList<Object> carried_forward_comps = new ArrayList<Object>()) {

        println("Outputting model: " + cls.name)

        def cfa = getAttributes(cls, carried_forward_atts)
        def cfc = getComponents(cls, carried_forward_comps, umlFile)
        def subtypes = getSubTypes(cls, umlFile)
        if (!cls.isAbstract) {
            builder.dataClass(name: cls.name.replaceAll("_", " "), description: cls.documentation) {
                    // first output the attributes for this class
                    cfa.each { att ->
                       def multiplicity = getMultiplicity(att)

                        dataElement(name: att.name.replaceAll("_", " "), description: att.documentation) {

                            if(att.tags?.value) ext("cosd id", att.tags?.value[0])
                            if(multiplicity.size()>0){
                                relationship {
                                    if (multiplicity["minRepeat"]) ext("Min Occurs", multiplicity["minRepeat"])
                                    if (multiplicity["maxRepeat"]) ext("Max Occurs", multiplicity["maxRepeat"])
                                }
                            }

                            createDataType(builder, att, umlFile)

                        }


                    }
                    println("No. of components: " + cfc.size())
                    cfc.each { component ->
                            createClasses(builder, component, umlFile)
                    }
                }

        } else {
            println("Abstract class: " + cls.name);
        }

        subtypes.each { subtype ->
            createClasses(builder, subtype.value, umlFile, cfa, cfc)
        }

    }




    protected static getAttributes(cls, carried_forward_atts){
        def cfa = new ArrayList<Object>()
        cfa.addAll(carried_forward_atts)
        if(cls.attributes)cfa.addAll(cls.attributes)
        return cfa
    }

    protected static getComponents(cls, carried_forward_comps, umlFile){
        def cfc = new ArrayList<Object>()
        cfc.addAll(carried_forward_comps)
        def components = umlFile.allClasses.findAll{
            id, component -> component.ownedElements.findAll{
                ct ->
                    ct._type.equals("UMLAssociation") && ct.end2.aggregation.equals("composite") && ct.end2.reference?.$ref?.equals(cls._id)
            }.size() > 0

        }

        if(components) components.each { id, component -> cfc.add(component) }

        return cfc

    }

    protected static getMultiplicity(att){

        def multiplicity = [:]

        switch (att.multiplicity){
            case "1":
                multiplicity["minRepeat"] = "1";
                multiplicity["maxRepeat"] = "1";
                break;
            case "0..1":
                multiplicity["minRepeat"] = "0";
                multiplicity["maxRepeat"] = "1";
                break;
            case "0..*":
                multiplicity["minRepeat"] = "0";
                multiplicity["maxRepeat"] = "unbounded";
                break;
            case "1..*":
                multiplicity["minRepeat"] = "1";
                multiplicity["maxRepeat"] = "unbounded";
                break;

            default:
                println("unknown multiplicity: " + att.multiplicity);

        }

        return multiplicity

    }

    protected static getSubTypes(cls, umlFile){
        return umlFile.allClasses.findAll{
            id, subtype -> subtype.ownedElements.findAll{
                oe ->
                    oe._type?.equals("UMLGeneralization") && oe?.target?.$ref?.equals(cls._id)
            }.size() > 0
        }
    }

}
