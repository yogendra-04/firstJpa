package com.example.first.controller;

import com.example.first.repositories.FirstRepo;
import com.example.first.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/")
public class FirstController {

    @Autowired
    FirstRepo repo;



    @PostConstruct
    public void init(){
        Students s1 = new Students("name1","grade1");
        repo.save(s1);
        Students s2 = new Students("name2","grade2");
        repo.save(s2);


    }

    @GetMapping("/students/all")
    public Iterable<Students> allStudents(){

        return repo.findAll();
    }


    @PostMapping("/students")
    public Students getStudents(@RequestBody Students student){
         return repo.save(student);

    }


    @GetMapping("/students/{id}")
    public ResponseEntity<?> searchStudent(@PathVariable(value = "id") int id){
        Students temp = null;
            if(repo.findById(id).isPresent()) {
                temp = repo.findById(id).get();
            }
            System.out.println("found");

        if(temp == null){
            return new ResponseEntity<>("no id found ", HttpStatus.NOT_FOUND);
        }
        System.out.println(temp.toString());
        return new ResponseEntity<>(temp,HttpStatus.OK);
    }


     @PatchMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable(value = "id") int id, @RequestBody Students student) {
         Students temp = null;
         if(repo.findById(id).isPresent()) {
             temp = repo.findById(id).get();
         }
         if (temp == null) {
             return new ResponseEntity<>("no id found to update", HttpStatus.NOT_FOUND);
         } else {


             return new ResponseEntity<>(repo.save(student), HttpStatus.OK);
         }
     }

     @DeleteMapping("/students/{id}")
    public ResponseEntity deleteStudent(@PathVariable(value = "id") int id){
         Students temp = null;
         if(repo.findById(id).isPresent()) {
             temp = repo.findById(id).get();
         }

         if(temp == null){
             return new ResponseEntity("no id found to delete", HttpStatus.NOT_FOUND);
         }
         repo.delete(temp);
         return new ResponseEntity( "deleted successfully", HttpStatus.OK);

     }


}
