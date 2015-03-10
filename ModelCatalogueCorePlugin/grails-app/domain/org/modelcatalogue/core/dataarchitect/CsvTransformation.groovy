package org.modelcatalogue.core.dataarchitect

class CsvTransformation {

    String name
    String description

    List<ColumnTransformationDefinition> columnDefinitions

    // time stamping
    Date dateCreated
    Date lastUpdated

    static hasMany = [columnDefinitions: ColumnTransformationDefinition]

    static constraints = {
        name size: 1..255
        description nullable: true
    }

    static mapping = {
        columnDefinitions cascade: "all-delete-orphan"
		separator column:"separatorChar"
	}

}
