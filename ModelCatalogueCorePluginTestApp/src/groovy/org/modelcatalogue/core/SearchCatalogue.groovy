package org.modelcatalogue.core

import org.modelcatalogue.core.util.lists.ListWithTotalAndType
import org.modelcatalogue.core.util.RelationshipDirection
import rx.Observable

public interface SearchCatalogue {

    ListWithTotalAndType<Relationship> search(CatalogueElement element, RelationshipType type, RelationshipDirection direction, Map params)
    public <T> ListWithTotalAndType<T> search(Class<T> resource, Map params)
    ListWithTotalAndType<CatalogueElement> search(Map params)

    Observable<Boolean> index(Object element)
    Observable<Boolean> index(Iterable<Object> resource)
    Observable<Boolean> unindex(Object object)
    Observable<Boolean> unindex(Collection<Object> object)
    Observable<Boolean> reindex(boolean soft)

    boolean isIndexingManually()

}
