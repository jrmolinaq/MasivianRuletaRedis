package com.masivian.ruleta.controller;

import com.masivian.ruleta.dto.RuletaDto;
import com.masivian.ruleta.model.Estado;
import com.masivian.ruleta.model.Ruleta;
import com.masivian.ruleta.repository.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/ruletas")
public class RuletaController {

    RuletaRepository ruletaRepository;

    @Autowired
    public RuletaController(RuletaRepository ruletaRepository) {
        this.ruletaRepository = ruletaRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer crearRuleta(){
        return ruletaRepository.save(new Ruleta(Estado.cerrada)).getId();
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/{idRuleta}")
    @ResponseStatus(HttpStatus.OK)
    public void abrirRuleta(@PathVariable(value = "idRuleta") Integer idRuleta){
        Ruleta ruleta = verificarRuletaExistente(idRuleta);

        if(ruleta.getEstado() == Estado.cerrada){
            ruleta.setEstado(Estado.abierta);
            ruletaRepository.save(ruleta);
        }else{
            throw new IllegalStateException("La ruleta de ID " + idRuleta
                    + " no se puede abrir porque no está en estado cerrada");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RuletaDto> listarRuletas(){
        return ((List<Ruleta>) ruletaRepository.findAll()).stream().map(r -> toDto(r)).collect(Collectors.toList());
    }

    private Ruleta verificarRuletaExistente(Integer idRuleta){
        return ruletaRepository.findById(idRuleta)
                .orElseThrow(() -> new NoSuchElementException("No existe una Ruleta de ID " + idRuleta));
    }

    private RuletaDto toDto(Ruleta ruleta){
        return new RuletaDto(ruleta.getId(), ruleta.getEstado().toString());
    }

}
