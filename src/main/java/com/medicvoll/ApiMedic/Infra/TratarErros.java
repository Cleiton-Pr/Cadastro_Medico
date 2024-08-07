package com.medicvoll.ApiMedic.Infra;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class TratarErros {

//   classe criada e gerenciada pelo Spring para lidar Excessoes, por ter varios metodos de API REst
    //sem precisar fazer um Try/Cath

/*Note que é simples criar uma classe com métodos que trataram exceções não tratadas no
controller. Logo, esse código fica isolado e não precisamos ter try-catch nos controllers.
Estes nem percebem que há uma classe externa tratando o erro.*/



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity lidarErro404(){
    return ResponseEntity.notFound().build();
   }


   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity lidarErro400(MethodArgumentNotValidException ex){
        var erro = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erro.stream().map(DadosErrosValid::new).toList());

   }

    private  record  DadosErrosValid (String campo, String mensagem){

        public DadosErrosValid (FieldError error){
            this(error.getField(), error.getDefaultMessage());

        }
    }



}
