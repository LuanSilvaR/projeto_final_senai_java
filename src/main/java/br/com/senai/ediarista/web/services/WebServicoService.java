package br.com.senai.ediarista.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.ediarista.core.exceptions.ServicoNaoEncontradoException;
import br.com.senai.ediarista.core.models.Servico;
import br.com.senai.ediarista.core.repositories.ServicoRepository;

import br.com.senai.ediarista.web.dtos.ServicoForm;
import br.com.senai.ediarista.web.mappers.WebServicoMapper;

@Service 
public class WebServicoService {
    
    @Autowired
    private ServicoRepository repository;

    @Autowired
    private WebServicoMapper mapper;

    public List<Servico> buscarTodos() {
        return repository.findAll();
    }

    public Servico cadastrar (ServicoForm form){
        var model = mapper.toModel(form);

        return repository.save(model);
    }

    public Servico buscarPorId(Long id){
        var ServicoEncontrado = repository.findById(id);

        if(ServicoEncontrado.isPresent()){
            return ServicoEncontrado.get();

        }

        var mensagem  = String.format("Serviço com id %d não encontrado", id);
        throw new ServicoNaoEncontradoException(mensagem);

    }

    public Servico editar(ServicoForm form, Long id){
        var ServicoEncontrado = buscarPorId(id);

        var model = mapper.toModel(form);
        model.setId(ServicoEncontrado.getId());

        return repository.save(model);
    }

    public void excluirPorId(Long id){
        var servicoEncontrado = buscarPorId(id);

        repository.delete(servicoEncontrado);
    }
}
