package com.example.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class FirstController {

    @Autowired
    FirstRepo repo;



    @PostConstruct
    public void init(){
        Students s1 = new Students(101,"name1","grade1");
        repo.save(s1);
        Students s2 = new Students(102,"name2","grade2");
        repo.save(s2);

    }

    @GetMapping("/all")
    public Iterable<Students> allStudents(){

        return repo.findAll();
    }

    @PostMapping("/")
    public Students getStudents(@RequestBody Students student){
         return repo.save(student);

    }

    @GetMapping("/{id}")
    public Optional<Students> searchstudent(@PathVariable(value = "id") int id){
        return repo.findById(id);


    }

     @PatchMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable(value = "id") int id, @RequestBody Students student){
             Optional<Students> temp = null;
             temp = repo.findById(id);
             if(temp == null){
                 return new ResponseEntity<>("no id found to update", HttpStatus.NOT_FOUND);
             }
             return new ResponseEntity<>(repo.save(student),HttpStatus.OK);

     }
     @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable(value = "id") int id){
         Students temp = null;
         temp = repo.findById(id).get();
         if(temp == null){
             return new ResponseEntity<>("no id found to delete", HttpStatus.NOT_FOUND);
         }
         repo.delete(temp);
        return new ResponseEntity( "deleted successfully", HttpStatus.OK);
     }
}
