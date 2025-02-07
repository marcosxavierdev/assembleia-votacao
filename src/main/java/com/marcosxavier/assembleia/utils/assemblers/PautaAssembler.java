package com.marcosxavier.assembleia.utils.assemblers;

import com.marcosxavier.assembleia.adapters.inbound.controllers.PautaRestController;
import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Pauta;
import com.marcosxavier.assembleia.domain.dto.pauta.CustomPautaCollectionDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaRequestDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaResponseDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaUpdateDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PautaAssembler implements RepresentationModelAssembler<Pauta, PautaResponseDTO> {

    public PautaResponseDTO toResponseDTO(Pauta pauta){
        PautaResponseDTO response = new PautaResponseDTO();
        response.setId(pauta.getId());
        response.setAssunto(pauta.getAssunto());
        response.setTempoMinutos(pauta.getTempoMinutos());
        response.setStatus(pauta.getStatus());
        return response;
    }

    @Override
    public PautaResponseDTO toModel(Pauta pauta) {
        PautaResponseDTO response = new PautaResponseDTO(pauta);
        response.add(linkTo(methodOn(PautaRestController.class).buscaPorId(pauta.getId())).withRel("busca pauta por id").withType("GET"));
        response.add(linkTo(methodOn(PautaRestController.class).criaPauta(new PautaRequestDTO())).withRel("cria uma pauta").withType("POST"));
        response.add(linkTo(methodOn(PautaRestController.class).atualizaPauta(new PautaUpdateDTO())).withRel("atualiza uma pauta").withType("PUT"));
        response.add(linkTo(methodOn(PautaRestController.class).deletaPauta(pauta.getId())).withRel("deleta uma pauta").withType("DELETE"));
        response.add(linkTo(methodOn(PautaRestController.class).encerrarPauta(pauta.getId())).withRel("encerra uma pauta").withType("POST"));
        response.add(linkTo(methodOn(PautaRestController.class).buscaTodasPautas()).withRel("lista todas pautas").withType("GET"));
        return response;
    }

    public PautaResponseDTO toModelBuscaPorId(Pauta pauta) {
        PautaResponseDTO response = new PautaResponseDTO(pauta);
        response.add(linkTo(methodOn(PautaRestController.class).buscaPorId(pauta.getId())).withSelfRel().withType("GET"));
        response.add(linkTo(methodOn(PautaRestController.class).criaPauta(new PautaRequestDTO())).withRel("cria uma pauta").withType("POST"));
        response.add(linkTo(methodOn(PautaRestController.class).atualizaPauta(new PautaUpdateDTO())).withRel("atualiza um pauta").withType("PUT"));
        response.add(linkTo(methodOn(PautaRestController.class).deletaPauta(pauta.getId())).withRel("deleta uma pauta").withType("DELETE"));
        response.add(linkTo(methodOn(PautaRestController.class).encerrarPauta(pauta.getId())).withRel("encerra uma pauta").withType("POST"));
        response.add(linkTo(methodOn(PautaRestController.class).buscaTodasPautas()).withRel("lista todas pautas").withType("GET"));
        return response;
    }

    public PautaResponseDTO toModelCriaPauta(Pauta pauta) {
        PautaResponseDTO response = new PautaResponseDTO(pauta);
        response.add(linkTo(methodOn(PautaRestController.class).criaPauta(new PautaRequestDTO())).withSelfRel().withType("POST"));
        response.add(linkTo(methodOn(PautaRestController.class).buscaPorId(pauta.getId())).withRel("busca pauta por id").withType("GET"));
        response.add(linkTo(methodOn(PautaRestController.class).atualizaPauta(new PautaUpdateDTO())).withRel("atualiza uma pauta").withType("PUT"));
        response.add(linkTo(methodOn(PautaRestController.class).deletaPauta(pauta.getId())).withRel("deleta uma pauta").withType("DELETE"));
        response.add(linkTo(methodOn(PautaRestController.class).encerrarPauta(pauta.getId())).withRel("encerra uma pauta").withType("POST"));
        response.add(linkTo(methodOn(PautaRestController.class).buscaTodasPautas()).withRel("lista todas pautas").withType("GET"));
        return response;
    }

    public PautaResponseDTO toModelAtualizaPauta(Pauta pauta) {
        PautaResponseDTO response = new PautaResponseDTO(pauta);
        response.add(linkTo(methodOn(PautaRestController.class).atualizaPauta(new PautaUpdateDTO())).withSelfRel().withType("PUT"));
        response.add(linkTo(methodOn(PautaRestController.class).buscaPorId(pauta.getId())).withRel("busca pauta por id").withType("GET"));
        response.add(linkTo(methodOn(PautaRestController.class).criaPauta(new PautaRequestDTO())).withRel("cria uma pauta").withType("POST"));
        response.add(linkTo(methodOn(PautaRestController.class).deletaPauta(pauta.getId())).withRel("deleta uma pauta").withType("DELETE"));
        response.add(linkTo(methodOn(PautaRestController.class).encerrarPauta(pauta.getId())).withRel("encerra uma pauta").withType("POST"));
        response.add(linkTo(methodOn(PautaRestController.class).buscaTodasPautas()).withRel("lista todas pautas").withType("GET"));
        return response;
    }

    public PautaResponseDTO toModelEncerraPauta(Pauta pauta) {
        PautaResponseDTO response = new PautaResponseDTO(pauta);
        response.add(linkTo(methodOn(PautaRestController.class).encerrarPauta(pauta.getId())).withSelfRel().withType("POST"));
        response.add(linkTo(methodOn(PautaRestController.class).buscaPorId(pauta.getId())).withRel("busca pauta por id").withType("GET"));
        response.add(linkTo(methodOn(PautaRestController.class).criaPauta(new PautaRequestDTO())).withRel("cria uma pauta").withType("POST"));
        response.add(linkTo(methodOn(PautaRestController.class).atualizaPauta(new PautaUpdateDTO())).withRel("atualiza uma pauta").withType("PUT"));
        response.add(linkTo(methodOn(PautaRestController.class).deletaPauta(pauta.getId())).withRel("deleta uma pauta").withType("DELETE"));
        response.add(linkTo(methodOn(PautaRestController.class).buscaTodasPautas()).withRel("lista todas pautas").withType("GET"));
        return response;
    }

    public CustomPautaCollectionDTO<PautaResponseDTO> toCollectionModel(List<Pauta> pautas) {
        List<PautaResponseDTO> responses =  pautas.stream().map(this::toModel).toList();
        CollectionModel<PautaResponseDTO> collection = CollectionModel.of(responses);
        collection.add(linkTo(methodOn(PautaRestController.class).buscaTodasPautas()).withSelfRel().withType("GET"));
        return new CustomPautaCollectionDTO<>(collection);
    }
}
