package com.marcosxavier.assembleia.domain.dto.eleitor;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomEleitorCollectionDTO<T> {
    private List<T> eleitores;
    private final Links _links;

    public CustomEleitorCollectionDTO(CollectionModel<T> collection) {
        this.eleitores = collection.getContent().stream().collect(Collectors.toList());
        this._links = collection.getLinks();
    }
}
