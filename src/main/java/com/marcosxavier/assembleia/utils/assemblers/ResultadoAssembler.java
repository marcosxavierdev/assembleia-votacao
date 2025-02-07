package com.marcosxavier.assembleia.utils.assemblers;

import com.marcosxavier.assembleia.adapters.inbound.controllers.ResultadoRestController;
import com.marcosxavier.assembleia.domain.dto.resultado.CustomResultadoCollectionDTO;
import com.marcosxavier.assembleia.domain.dto.resultado.ResultadoDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ResultadoAssembler implements RepresentationModelAssembler<ResultadoDTO, ResultadoDTO> {


    public ResultadoDTO toModel(ResultadoDTO resultado) {
        ResultadoDTO response = new ResultadoDTO(resultado);
        response.add(linkTo(methodOn(ResultadoRestController.class).buscaResultadoPorPauta(resultado.getPauta().getId())).withSelfRel().withType("GET"));
        response.add(linkTo(methodOn(ResultadoRestController.class).listarResultados()).withRel("todos-os-resultados"));
        return response;
    }

    public CustomResultadoCollectionDTO<ResultadoDTO> toCollectionModel(List<ResultadoDTO> resultados) {
        List<ResultadoDTO> responses = resultados.stream().map(this::toModel).toList();
        CollectionModel<ResultadoDTO> collection = CollectionModel.of(responses);
        collection.add(linkTo(methodOn(ResultadoRestController.class).listarResultados()).withSelfRel().withType("GET"));
        return new CustomResultadoCollectionDTO<>(collection);
    }

}
