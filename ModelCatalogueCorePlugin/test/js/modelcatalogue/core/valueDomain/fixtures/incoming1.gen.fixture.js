/** Generated automatically from valueDomain. Do not edit this file manually! */
    (function (window) {
        window['fixtures'] = window['fixtures'] || {};
        var fixtures = window['fixtures']
        fixtures['valueDomain'] = fixtures['valueDomain'] || {};
        var valueDomain = fixtures['valueDomain']

        window.fixtures.valueDomain.incoming1 = {
   "total": 11,
   "previous": "",
   "page": 10,
   "itemType": "org.modelcatalogue.core.Relationship",
   "listType": "org.modelcatalogue.core.util.Relationships",
   "next": "/valueDomain/74/incoming/relationship?max=10&offset=10",
   "list": [
      {
         "id": 5301,
         "direction": "destinationToSource",
         "removeLink": "/valueDomain/74/incoming/relationship",
         "relation": {
            "outgoingRelationships": {
               "count": 1,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/73/outgoing"
            },
            "includedIn": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/73/incoming/inclusion"
            },
            "link": "/valueDomain/73",
            "instantiates": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/73/incoming/instantiation"
            },
            "elementType": "org.modelcatalogue.core.ValueDomain",
            "incomingRelationships": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/73/incoming"
            },
            "version": 1,
            "id": 73,
            "dataType": {
               "id": 57,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/57/outgoing"
               },
               "valueDomains": {
                  "count": 1,
                  "itemType": "org.modelcatalogue.core.ValueDomain",
                  "link": "/dataType/57/valueDomain"
               },
               "description": "a double",
               "name": "double",
               "link": "/dataType/57",
               "elementTypeName": "Data Type",
               "elementType": "org.modelcatalogue.core.DataType",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/57/incoming"
               },
               "version": 1
            },
            "description": null,
            "name": "value domain Fahrenheit",
            "mappings": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Mapping",
               "link": "/valueDomain/73/mapping"
            },
            "rule": null,
            "elementTypeName": "Value Domain",
            "unitOfMeasure": {
               "id": 58,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/58/outgoing"
               },
               "symbol": "°F",
               "description": "Fahrenheit (symbol °F) is a temperature scale based on one proposed in 1724 by the physicist Daniel Gabriel Fahrenheit (1686–1736), after whom the scale is named.[1] On Fahrenheit's original scale the lower defining point was the lowest temperature to which he could reproducibly cool brine (defining 0 degrees), while the highest was that of the average human core body temperature (defining 100 degrees). There exist several stories on the exact original definition of his scale; however, some of the specifics have been presumed lost or exaggerated with time. The scale is now usually defined by two fixed points: the temperature at which water freezes into ice is defined as 32 degrees, and the boiling point of water is defined to be 212 degrees, a 180 degree separation, as defined at sea level and standard atmospheric pressure.",
               "name": "Degrees of Fahrenheit",
               "link": "/measurementUnit/58",
               "elementTypeName": "Measurement Unit",
               "elementType": "org.modelcatalogue.core.MeasurementUnit",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/58/incoming"
               },
               "version": 0
            }
         },
         "type": {
            "id": 3,
            "sourceClass": "org.modelcatalogue.core.CatalogueElement",
            "sourceToDestination": "relates to",
            "destinationClass": "org.modelcatalogue.core.CatalogueElement",
            "name": "relationship",
            "link": "/relationshipType/3",
            "elementTypeName": "Relationship Type",
            "elementType": "org.modelcatalogue.core.RelationshipType",
            "destinationToSource": "is relationship of",
            "version": 0
         }
      },
      {
         "id": 5302,
         "direction": "destinationToSource",
         "removeLink": "/valueDomain/74/incoming/relationship",
         "relation": {
            "outgoingRelationships": {
               "count": 1,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/75/outgoing"
            },
            "includedIn": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/75/incoming/inclusion"
            },
            "link": "/valueDomain/75",
            "instantiates": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/75/incoming/instantiation"
            },
            "elementType": "org.modelcatalogue.core.ValueDomain",
            "incomingRelationships": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/75/incoming"
            },
            "version": 1,
            "id": 75,
            "dataType": {
               "id": 35,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/enumeratedType/35/outgoing"
               },
               "valueDomains": {
                  "count": 2,
                  "itemType": "org.modelcatalogue.core.ValueDomain",
                  "link": "/enumeratedType/35/valueDomain"
               },
               "description": null,
               "name": "sub1",
               "link": "/enumeratedType/35",
               "elementTypeName": "Enumerated Type",
               "elementType": "org.modelcatalogue.core.EnumeratedType",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/enumeratedType/35/incoming"
               },
               "version": 2,
               "enumerations": {
                  "GEO": "geography",
                  "P": "politics",
                  "SCI": "science",
                  "H": "history"
               }
            },
            "description": null,
            "name": "school subject2",
            "mappings": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Mapping",
               "link": "/valueDomain/75/mapping"
            },
            "rule": null,
            "elementTypeName": "Value Domain",
            "unitOfMeasure": null
         },
         "type": {
            "id": 3,
            "sourceClass": "org.modelcatalogue.core.CatalogueElement",
            "sourceToDestination": "relates to",
            "destinationClass": "org.modelcatalogue.core.CatalogueElement",
            "name": "relationship",
            "link": "/relationshipType/3",
            "elementTypeName": "Relationship Type",
            "elementType": "org.modelcatalogue.core.RelationshipType",
            "destinationToSource": "is relationship of",
            "version": 0
         }
      },
      {
         "id": 5303,
         "direction": "destinationToSource",
         "removeLink": "/valueDomain/74/incoming/relationship",
         "relation": {
            "outgoingRelationships": {
               "count": 1,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/76/outgoing"
            },
            "includedIn": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/76/incoming/inclusion"
            },
            "link": "/valueDomain/76",
            "instantiates": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/76/incoming/instantiation"
            },
            "elementType": "org.modelcatalogue.core.ValueDomain",
            "incomingRelationships": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/76/incoming"
            },
            "version": 1,
            "id": 76,
            "dataType": {
               "id": 8,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/enumeratedType/8/outgoing"
               },
               "valueDomains": {
                  "count": 1,
                  "itemType": "org.modelcatalogue.core.ValueDomain",
                  "link": "/enumeratedType/8/valueDomain"
               },
               "description": null,
               "name": "sub2",
               "link": "/enumeratedType/8",
               "elementTypeName": "Enumerated Type",
               "elementType": "org.modelcatalogue.core.EnumeratedType",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/enumeratedType/8/incoming"
               },
               "version": 1,
               "enumerations": {
                  "HISTORY": "history",
                  "POLITICS": "politics",
                  "SCIENCE": "science"
               }
            },
            "description": null,
            "name": "value domain uni subjects 2",
            "mappings": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Mapping",
               "link": "/valueDomain/76/mapping"
            },
            "rule": null,
            "elementTypeName": "Value Domain",
            "unitOfMeasure": null
         },
         "type": {
            "id": 3,
            "sourceClass": "org.modelcatalogue.core.CatalogueElement",
            "sourceToDestination": "relates to",
            "destinationClass": "org.modelcatalogue.core.CatalogueElement",
            "name": "relationship",
            "link": "/relationshipType/3",
            "elementTypeName": "Relationship Type",
            "elementType": "org.modelcatalogue.core.RelationshipType",
            "destinationToSource": "is relationship of",
            "version": 0
         }
      },
      {
         "id": 5304,
         "direction": "destinationToSource",
         "removeLink": "/valueDomain/74/incoming/relationship",
         "relation": {
            "outgoingRelationships": {
               "count": 1,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/77/outgoing"
            },
            "includedIn": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/77/incoming/inclusion"
            },
            "link": "/valueDomain/77",
            "instantiates": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/77/incoming/instantiation"
            },
            "elementType": "org.modelcatalogue.core.ValueDomain",
            "incomingRelationships": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/77/incoming"
            },
            "version": 1,
            "id": 77,
            "dataType": {
               "id": 3,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/3/outgoing"
               },
               "valueDomains": {
                  "count": 7,
                  "itemType": "org.modelcatalogue.core.ValueDomain",
                  "link": "/dataType/3/valueDomain"
               },
               "description": "an integer",
               "name": "integer",
               "link": "/dataType/3",
               "elementTypeName": "Data Type",
               "elementType": "org.modelcatalogue.core.DataType",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/3/incoming"
               },
               "version": 7
            },
            "description": null,
            "name": "value domain test2",
            "mappings": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Mapping",
               "link": "/valueDomain/77/mapping"
            },
            "rule": null,
            "elementTypeName": "Value Domain",
            "unitOfMeasure": {
               "id": 49,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/49/outgoing"
               },
               "symbol": "°C",
               "description": "Celsius, also known as centigrade,[1] is a scale and unit of measurement for temperature. It is named after the Swedish astronomer Anders Celsius (1701–1744), who developed a similar temperature scale. The degree Celsius (°C) can refer to a specific temperature on the Celsius scale as well as a unit to indicate a temperature interval, a difference between two temperatures or an uncertainty. The unit was known until 1948 as \"centigrade\" from the Latin centum translated as 100 and gradus translated as \"steps\".",
               "name": "Degrees of Celsius",
               "link": "/measurementUnit/49",
               "elementTypeName": "Measurement Unit",
               "elementType": "org.modelcatalogue.core.MeasurementUnit",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/49/incoming"
               },
               "version": 0
            }
         },
         "type": {
            "id": 3,
            "sourceClass": "org.modelcatalogue.core.CatalogueElement",
            "sourceToDestination": "relates to",
            "destinationClass": "org.modelcatalogue.core.CatalogueElement",
            "name": "relationship",
            "link": "/relationshipType/3",
            "elementTypeName": "Relationship Type",
            "elementType": "org.modelcatalogue.core.RelationshipType",
            "destinationToSource": "is relationship of",
            "version": 0
         }
      },
      {
         "id": 5305,
         "direction": "destinationToSource",
         "removeLink": "/valueDomain/74/incoming/relationship",
         "relation": {
            "outgoingRelationships": {
               "count": 1,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/78/outgoing"
            },
            "includedIn": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/78/incoming/inclusion"
            },
            "link": "/valueDomain/78",
            "instantiates": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/78/incoming/instantiation"
            },
            "elementType": "org.modelcatalogue.core.ValueDomain",
            "incomingRelationships": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/78/incoming"
            },
            "version": 1,
            "id": 78,
            "dataType": {
               "id": 3,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/3/outgoing"
               },
               "valueDomains": {
                  "count": 7,
                  "itemType": "org.modelcatalogue.core.ValueDomain",
                  "link": "/dataType/3/valueDomain"
               },
               "description": "an integer",
               "name": "integer",
               "link": "/dataType/3",
               "elementTypeName": "Data Type",
               "elementType": "org.modelcatalogue.core.DataType",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/3/incoming"
               },
               "version": 7
            },
            "description": null,
            "name": "value domain test1",
            "mappings": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Mapping",
               "link": "/valueDomain/78/mapping"
            },
            "rule": null,
            "elementTypeName": "Value Domain",
            "unitOfMeasure": {
               "id": 49,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/49/outgoing"
               },
               "symbol": "°C",
               "description": "Celsius, also known as centigrade,[1] is a scale and unit of measurement for temperature. It is named after the Swedish astronomer Anders Celsius (1701–1744), who developed a similar temperature scale. The degree Celsius (°C) can refer to a specific temperature on the Celsius scale as well as a unit to indicate a temperature interval, a difference between two temperatures or an uncertainty. The unit was known until 1948 as \"centigrade\" from the Latin centum translated as 100 and gradus translated as \"steps\".",
               "name": "Degrees of Celsius",
               "link": "/measurementUnit/49",
               "elementTypeName": "Measurement Unit",
               "elementType": "org.modelcatalogue.core.MeasurementUnit",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/49/incoming"
               },
               "version": 0
            }
         },
         "type": {
            "id": 3,
            "sourceClass": "org.modelcatalogue.core.CatalogueElement",
            "sourceToDestination": "relates to",
            "destinationClass": "org.modelcatalogue.core.CatalogueElement",
            "name": "relationship",
            "link": "/relationshipType/3",
            "elementTypeName": "Relationship Type",
            "elementType": "org.modelcatalogue.core.RelationshipType",
            "destinationToSource": "is relationship of",
            "version": 0
         }
      },
      {
         "id": 5306,
         "direction": "destinationToSource",
         "removeLink": "/valueDomain/74/incoming/relationship",
         "relation": {
            "outgoingRelationships": {
               "count": 1,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/79/outgoing"
            },
            "includedIn": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/79/incoming/inclusion"
            },
            "link": "/valueDomain/79",
            "instantiates": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/79/incoming/instantiation"
            },
            "elementType": "org.modelcatalogue.core.ValueDomain",
            "incomingRelationships": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/79/incoming"
            },
            "version": 1,
            "id": 79,
            "dataType": {
               "id": 3,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/3/outgoing"
               },
               "valueDomains": {
                  "count": 7,
                  "itemType": "org.modelcatalogue.core.ValueDomain",
                  "link": "/dataType/3/valueDomain"
               },
               "description": "an integer",
               "name": "integer",
               "link": "/dataType/3",
               "elementTypeName": "Data Type",
               "elementType": "org.modelcatalogue.core.DataType",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/3/incoming"
               },
               "version": 7
            },
            "description": null,
            "name": "value domain test3",
            "mappings": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Mapping",
               "link": "/valueDomain/79/mapping"
            },
            "rule": null,
            "elementTypeName": "Value Domain",
            "unitOfMeasure": {
               "id": 49,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/49/outgoing"
               },
               "symbol": "°C",
               "description": "Celsius, also known as centigrade,[1] is a scale and unit of measurement for temperature. It is named after the Swedish astronomer Anders Celsius (1701–1744), who developed a similar temperature scale. The degree Celsius (°C) can refer to a specific temperature on the Celsius scale as well as a unit to indicate a temperature interval, a difference between two temperatures or an uncertainty. The unit was known until 1948 as \"centigrade\" from the Latin centum translated as 100 and gradus translated as \"steps\".",
               "name": "Degrees of Celsius",
               "link": "/measurementUnit/49",
               "elementTypeName": "Measurement Unit",
               "elementType": "org.modelcatalogue.core.MeasurementUnit",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/49/incoming"
               },
               "version": 0
            }
         },
         "type": {
            "id": 3,
            "sourceClass": "org.modelcatalogue.core.CatalogueElement",
            "sourceToDestination": "relates to",
            "destinationClass": "org.modelcatalogue.core.CatalogueElement",
            "name": "relationship",
            "link": "/relationshipType/3",
            "elementTypeName": "Relationship Type",
            "elementType": "org.modelcatalogue.core.RelationshipType",
            "destinationToSource": "is relationship of",
            "version": 0
         }
      },
      {
         "id": 5307,
         "direction": "destinationToSource",
         "removeLink": "/valueDomain/74/incoming/relationship",
         "relation": {
            "outgoingRelationships": {
               "count": 1,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/80/outgoing"
            },
            "includedIn": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/80/incoming/inclusion"
            },
            "link": "/valueDomain/80",
            "instantiates": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/80/incoming/instantiation"
            },
            "elementType": "org.modelcatalogue.core.ValueDomain",
            "incomingRelationships": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/80/incoming"
            },
            "version": 1,
            "id": 80,
            "dataType": {
               "id": 3,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/3/outgoing"
               },
               "valueDomains": {
                  "count": 7,
                  "itemType": "org.modelcatalogue.core.ValueDomain",
                  "link": "/dataType/3/valueDomain"
               },
               "description": "an integer",
               "name": "integer",
               "link": "/dataType/3",
               "elementTypeName": "Data Type",
               "elementType": "org.modelcatalogue.core.DataType",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/3/incoming"
               },
               "version": 7
            },
            "description": null,
            "name": "value domain test4",
            "mappings": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Mapping",
               "link": "/valueDomain/80/mapping"
            },
            "rule": null,
            "elementTypeName": "Value Domain",
            "unitOfMeasure": {
               "id": 49,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/49/outgoing"
               },
               "symbol": "°C",
               "description": "Celsius, also known as centigrade,[1] is a scale and unit of measurement for temperature. It is named after the Swedish astronomer Anders Celsius (1701–1744), who developed a similar temperature scale. The degree Celsius (°C) can refer to a specific temperature on the Celsius scale as well as a unit to indicate a temperature interval, a difference between two temperatures or an uncertainty. The unit was known until 1948 as \"centigrade\" from the Latin centum translated as 100 and gradus translated as \"steps\".",
               "name": "Degrees of Celsius",
               "link": "/measurementUnit/49",
               "elementTypeName": "Measurement Unit",
               "elementType": "org.modelcatalogue.core.MeasurementUnit",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/49/incoming"
               },
               "version": 0
            }
         },
         "type": {
            "id": 3,
            "sourceClass": "org.modelcatalogue.core.CatalogueElement",
            "sourceToDestination": "relates to",
            "destinationClass": "org.modelcatalogue.core.CatalogueElement",
            "name": "relationship",
            "link": "/relationshipType/3",
            "elementTypeName": "Relationship Type",
            "elementType": "org.modelcatalogue.core.RelationshipType",
            "destinationToSource": "is relationship of",
            "version": 0
         }
      },
      {
         "id": 5308,
         "direction": "destinationToSource",
         "removeLink": "/valueDomain/74/incoming/relationship",
         "relation": {
            "outgoingRelationships": {
               "count": 1,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/81/outgoing"
            },
            "includedIn": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/81/incoming/inclusion"
            },
            "link": "/valueDomain/81",
            "instantiates": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/81/incoming/instantiation"
            },
            "elementType": "org.modelcatalogue.core.ValueDomain",
            "incomingRelationships": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/81/incoming"
            },
            "version": 1,
            "id": 81,
            "dataType": {
               "id": 43,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/enumeratedType/43/outgoing"
               },
               "valueDomains": {
                  "count": 1,
                  "itemType": "org.modelcatalogue.core.ValueDomain",
                  "link": "/enumeratedType/43/valueDomain"
               },
               "description": null,
               "name": "sub3",
               "link": "/enumeratedType/43",
               "elementTypeName": "Enumerated Type",
               "elementType": "org.modelcatalogue.core.EnumeratedType",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/enumeratedType/43/incoming"
               },
               "version": 1,
               "enumerations": {
                  "p": "politics",
                  "sci": "science",
                  "h": "history"
               }
            },
            "description": null,
            "name": "value domain uni subjects 3",
            "mappings": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Mapping",
               "link": "/valueDomain/81/mapping"
            },
            "rule": null,
            "elementTypeName": "Value Domain",
            "unitOfMeasure": null
         },
         "type": {
            "id": 3,
            "sourceClass": "org.modelcatalogue.core.CatalogueElement",
            "sourceToDestination": "relates to",
            "destinationClass": "org.modelcatalogue.core.CatalogueElement",
            "name": "relationship",
            "link": "/relationshipType/3",
            "elementTypeName": "Relationship Type",
            "elementType": "org.modelcatalogue.core.RelationshipType",
            "destinationToSource": "is relationship of",
            "version": 0
         }
      },
      {
         "id": 5309,
         "direction": "destinationToSource",
         "removeLink": "/valueDomain/74/incoming/relationship",
         "relation": {
            "outgoingRelationships": {
               "count": 1,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/82/outgoing"
            },
            "includedIn": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/82/incoming/inclusion"
            },
            "link": "/valueDomain/82",
            "instantiates": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/82/incoming/instantiation"
            },
            "elementType": "org.modelcatalogue.core.ValueDomain",
            "incomingRelationships": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/82/incoming"
            },
            "version": 1,
            "id": 82,
            "dataType": {
               "id": 35,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/enumeratedType/35/outgoing"
               },
               "valueDomains": {
                  "count": 2,
                  "itemType": "org.modelcatalogue.core.ValueDomain",
                  "link": "/enumeratedType/35/valueDomain"
               },
               "description": null,
               "name": "sub1",
               "link": "/enumeratedType/35",
               "elementTypeName": "Enumerated Type",
               "elementType": "org.modelcatalogue.core.EnumeratedType",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/enumeratedType/35/incoming"
               },
               "version": 2,
               "enumerations": {
                  "GEO": "geography",
                  "P": "politics",
                  "SCI": "science",
                  "H": "history"
               }
            },
            "description": null,
            "name": "school subject",
            "mappings": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Mapping",
               "link": "/valueDomain/82/mapping"
            },
            "rule": null,
            "elementTypeName": "Value Domain",
            "unitOfMeasure": null
         },
         "type": {
            "id": 3,
            "sourceClass": "org.modelcatalogue.core.CatalogueElement",
            "sourceToDestination": "relates to",
            "destinationClass": "org.modelcatalogue.core.CatalogueElement",
            "name": "relationship",
            "link": "/relationshipType/3",
            "elementTypeName": "Relationship Type",
            "elementType": "org.modelcatalogue.core.RelationshipType",
            "destinationToSource": "is relationship of",
            "version": 0
         }
      },
      {
         "id": 5310,
         "direction": "destinationToSource",
         "removeLink": "/valueDomain/74/incoming/relationship",
         "relation": {
            "outgoingRelationships": {
               "count": 1,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/83/outgoing"
            },
            "includedIn": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/83/incoming/inclusion"
            },
            "link": "/valueDomain/83",
            "instantiates": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/83/incoming/instantiation"
            },
            "elementType": "org.modelcatalogue.core.ValueDomain",
            "incomingRelationships": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Relationship",
               "link": "/valueDomain/83/incoming"
            },
            "version": 1,
            "id": 83,
            "dataType": {
               "id": 3,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/3/outgoing"
               },
               "valueDomains": {
                  "count": 7,
                  "itemType": "org.modelcatalogue.core.ValueDomain",
                  "link": "/dataType/3/valueDomain"
               },
               "description": "an integer",
               "name": "integer",
               "link": "/dataType/3",
               "elementTypeName": "Data Type",
               "elementType": "org.modelcatalogue.core.DataType",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/dataType/3/incoming"
               },
               "version": 7
            },
            "description": null,
            "name": "value domain test5",
            "mappings": {
               "count": 0,
               "itemType": "org.modelcatalogue.core.Mapping",
               "link": "/valueDomain/83/mapping"
            },
            "rule": null,
            "elementTypeName": "Value Domain",
            "unitOfMeasure": {
               "id": 49,
               "outgoingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/49/outgoing"
               },
               "symbol": "°C",
               "description": "Celsius, also known as centigrade,[1] is a scale and unit of measurement for temperature. It is named after the Swedish astronomer Anders Celsius (1701–1744), who developed a similar temperature scale. The degree Celsius (°C) can refer to a specific temperature on the Celsius scale as well as a unit to indicate a temperature interval, a difference between two temperatures or an uncertainty. The unit was known until 1948 as \"centigrade\" from the Latin centum translated as 100 and gradus translated as \"steps\".",
               "name": "Degrees of Celsius",
               "link": "/measurementUnit/49",
               "elementTypeName": "Measurement Unit",
               "elementType": "org.modelcatalogue.core.MeasurementUnit",
               "incomingRelationships": {
                  "count": 0,
                  "itemType": "org.modelcatalogue.core.Relationship",
                  "link": "/measurementUnit/49/incoming"
               },
               "version": 0
            }
         },
         "type": {
            "id": 3,
            "sourceClass": "org.modelcatalogue.core.CatalogueElement",
            "sourceToDestination": "relates to",
            "destinationClass": "org.modelcatalogue.core.CatalogueElement",
            "name": "relationship",
            "link": "/relationshipType/3",
            "elementTypeName": "Relationship Type",
            "elementType": "org.modelcatalogue.core.RelationshipType",
            "destinationToSource": "is relationship of",
            "version": 0
         }
      }
   ],
   "offset": 0,
   "success": true,
   "size": 10
}

    })(window)