/** Generated automatically from measurementUnit. Do not edit this file manually! */
    (function (window) {
        window['fixtures'] = window['fixtures'] || {};
        var fixtures = window['fixtures']
        fixtures['measurementUnit'] = fixtures['measurementUnit'] || {};
        var measurementUnit = fixtures['measurementUnit']

        window.fixtures.measurementUnit.outgoing6 = {
   "total": 11,
   "previous": "/measurementUnit/49/outgoing/relationship?max=2&offset=8",
   "page": 2,
   "itemType": "org.modelcatalogue.core.Relationship",
   "listType": "org.modelcatalogue.core.util.Relationships",
   "next": "",
   "list": [{
      "id": 3672,
      "direction": "sourceToDestination",
      "removeLink": "/measurementUnit/49/outgoing/relationship",
      "relation": {
         "id": 66,
         "outgoingRelationships": {
            "count": 0,
            "itemType": "org.modelcatalogue.core.Relationship",
            "link": "/measurementUnit/66/outgoing"
         },
         "symbol": "°7",
         "description": "test7 mu",
         "name": "test mu7",
         "link": "/measurementUnit/66",
         "elementTypeName": "Measurement Unit",
         "elementType": "org.modelcatalogue.core.MeasurementUnit",
         "incomingRelationships": {
            "count": 1,
            "itemType": "org.modelcatalogue.core.Relationship",
            "link": "/measurementUnit/66/incoming"
         },
         "version": 1
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
   }],
   "offset": 10,
   "success": true,
   "size": 1
}

    })(window)