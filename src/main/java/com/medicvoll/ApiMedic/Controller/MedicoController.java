package com.medicvoll.ApiMedic.Controller;


import com.medicvoll.ApiMedic.Dominio.Medico.DTO.Atualizardados;
import com.medicvoll.ApiMedic.Dominio.Medico.DTO.DadosListaMedico;
import com.medicvoll.ApiMedic.Dominio.Medico.DTO.MedicoDados;
import com.medicvoll.ApiMedic.Dominio.Medico.Medico;
import com.medicvoll.ApiMedic.Repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medics")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

//    @GetMapping
//    public List<Medico> lista(){
//        var medico = repository.findAll();
//        return medico;
//    }


    @GetMapping
    public ResponseEntity<  Page<DadosListaMedico> > listamedicoDados (Pageable paginacao){
        var page =  repository.findByAtivoTrue(paginacao).map(DadosListaMedico::new);
        return  ResponseEntity.ok(page);
    }

//    @GetMapping
//   public Page<DadosListaMedico>  listamedicoDados (Pageable paginacao){
//        return  repository.findAll(paginacao).map(DadosListaMedico::new);
//    }

//    @GetMapping
//    public List<DadosListaMedico> dadosListaMedicos(){
//        return repository.findAll().stream().map(DadosListaMedico::new).toList();
//    }
//    A expressão map(DadosListaMedico::new).toList() que você mencionou é típica de operações de stream em Java, provavelmente usando a API Streams do Java 8 ou superior. Vamos analisar cada parte dessa expressão para entender como funciona:
//
//            1. map(DadosListaMedico::new)
//    O método map é usado para transformar (ou mapear) elementos de um stream de dados em outros elementos. No contexto de programação funcional em Java, o map é geralmente usado para aplicar uma função a cada elemento do stream e gerar um novo stream com os resultados dessas operações.
//
//    No seu caso, DadosListaMedico::new é uma referência para um construtor da classe DadosListaMedico. Isso significa que para cada elemento do stream, um novo objeto DadosListaMedico será criado usando o construtor especificado.
//
//            2. .toList()
//    O método .toList() é usado para coletar os elementos do stream em uma lista. Ele retorna uma lista contendo os elementos transformados pelo map.


    @PostMapping
    @Transactional
    public ResponseEntity cadastroMedico(@RequestBody @Valid MedicoDados dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);

        repository.save(medico);
        var uri = uriBuilder.path("medics/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListaMedico(medico));

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDadosMedicos(@RequestBody @Valid Atualizardados atualizardados){
        var medico = repository.getReferenceById(atualizardados.id());
      medico.atualizarDados(atualizardados);

        return ResponseEntity.ok(new DadosListaMedico(medico));
    }

//    @PutMapping("/{id}")
//    @Transactional
//    public void atualizar (@PathVariable Long id){
//        var medico = repository.getReferenceById(id);
//        medico.incluir();
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhamentoMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListaMedico(medico));
    }







    }



