package com.marcosxavier.assembleia.utils.assemblers;

import com.marcosxavier.assembleia.adapters.inbound.controllers.VotoRestController;
import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Voto;
import com.marcosxavier.assembleia.domain.dto.voto.CustomVotoCollectionDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoRequestDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoResponseDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoUpdateDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VotoAssembler  implements RepresentationModelAssembler<Voto, VotoResponseDTO> {

    public VotoResponseDTO toResponseDTO(Voto voto){
        VotoResponseDTO response = new VotoResponseDTO();
        response.setId(voto.getId());
        response.setIdEleitor(voto.getIdEleitor());
        response.setIdPauta(voto.getIdPauta());
        response.setAprovacao(voto.getAprovacao());
        return response;
    }

    @Override
    public VotoResponseDTO toModel(Voto voto) {
        VotoResponseDTO response = new VotoResponseDTO(voto);
        response.add(linkTo(methodOn(VotoRestController.class).buscaPorId(voto.getId())).withRel("busca Voto por id").withType("GET"));
        response.add(linkTo(methodOn(VotoRestController.class).criaVoto(new VotoRequestDTO())).withRel("cria um voto").withType("POST"));
        response.add(linkTo(methodOn(VotoRestController.class).atualizaVoto(new VotoUpdateDTO())).withRel("atualiza um voto").withType("PUT"));
        response.add(linkTo(methodOn(VotoRestController.class).deletaVoto(voto.getId())).withRel("deleta um voto").withType("DELETE"));
        response.add(linkTo(methodOn(VotoRestController.class).buscaTodosVotos()).withRel("lista todos votos").withType("GET"));
        return response;
    }

    public VotoResponseDTO toModelBuscaPorId(Voto voto) {
        VotoResponseDTO response = new VotoResponseDTO(voto);
        response.add(linkTo(methodOn(VotoRestController.class).buscaPorId(voto.getId())).withSelfRel().withType("GET"));
        response.add(linkTo(methodOn(VotoRestController.class).criaVoto(new VotoRequestDTO())).withRel("cria um voto").withType("POST"));
        response.add(linkTo(methodOn(VotoRestController.class).atualizaVoto(new VotoUpdateDTO())).withRel("atualiza um voto").withType("PUT"));
        response.add(linkTo(methodOn(VotoRestController.class).deletaVoto(voto.getId())).withRel("deleta um voto").withType("DELETE"));
        response.add(linkTo(methodOn(VotoRestController.class).buscaTodosVotos()).withRel("lista todos votos").withType("GET"));
        return response;
    }

    public VotoResponseDTO toModelCriaVoto(Voto voto) {
        VotoResponseDTO response = new VotoResponseDTO(voto);
        response.add(linkTo(methodOn(VotoRestController.class).criaVoto(new VotoRequestDTO())).withSelfRel().withType("POST"));
        response.add(linkTo(methodOn(VotoRestController.class).buscaPorId(voto.getId())).withRel("busca voto por id").withType("GET"));
        response.add(linkTo(methodOn(VotoRestController.class).atualizaVoto(new VotoUpdateDTO())).withRel("atualiza um voto").withType("PUT"));
        response.add(linkTo(methodOn(VotoRestController.class).deletaVoto(voto.getId())).withRel("deleta um voto").withType("DELETE"));
        response.add(linkTo(methodOn(VotoRestController.class).buscaTodosVotos()).withRel("lista todos votos").withType("GET"));
        return response;
    }

    public VotoResponseDTO toModelAtualizaVoto(Voto voto) {
        VotoResponseDTO response = new VotoResponseDTO(voto);
        response.add(linkTo(methodOn(VotoRestController.class).atualizaVoto(new VotoUpdateDTO())).withSelfRel().withType("PUT"));
        response.add(linkTo(methodOn(VotoRestController.class).buscaPorId(voto.getId())).withRel("busca voto por id").withType("GET"));
        response.add(linkTo(methodOn(VotoRestController.class).criaVoto(new VotoRequestDTO())).withRel("cria um voto").withType("POST"));
        response.add(linkTo(methodOn(VotoRestController.class).deletaVoto(voto.getId())).withRel("deleta um voto").withType("DELETE"));
        response.add(linkTo(methodOn(VotoRestController.class).buscaTodosVotos()).withRel("lista todos votoes").withType("GET"));
        return response;
    }

    public CustomVotoCollectionDTO<VotoResponseDTO> toCollectionModel(List<Voto> votos) {
        List<VotoResponseDTO> responses =  votos.stream().map(this::toModel).toList();
        CollectionModel<VotoResponseDTO> collection = CollectionModel.of(responses);
        collection.add(linkTo(methodOn(VotoRestController.class).buscaTodosVotos()).withSelfRel().withType("GET"));
        return new CustomVotoCollectionDTO<>(collection);
    }
}
