package com.dataemission.controllers;

import com.dataemission.entities.NewUser;
import com.dataemission.repository.NewUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRegistration {

    @Autowired
    private NewUserRepository newUserRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/addUser")
    public String insertNewUser(@RequestBody NewUser newuser){
        newUserRepository.save(newuser);
        return "Added New User " +newuser.getUsername()+"successfully";
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<NewUser> updateUser(@PathVariable int id, @RequestBody NewUser newUser) {
        Optional<NewUser> existUser= newUserRepository.findById(id);

        if (existUser.isPresent()) {
            NewUser user = existUser.get();
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setAge(newUser.getAge());


            return new ResponseEntity<>(newUserRepository.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   /* @PutMapping("/updateUser/{id}")
    public String updateExistUser(@RequestBody NewUser newuser,@PathVariable int id){
        Query query =new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Optional <NewUser> existuser = Optional.ofNullable(mongoTemplate.findOne(query, NewUser.class));
        if(existuser.isPresent()){
            mongoTemplate.save(newuser);
        }

        mongoTemplate.save(existuser);
        return "Added New User " +newuser.getUsername()+"successfully";
    }
   /*@PutMapping("/products/{id}")
   public ResponseEntity<NewUser> updateProduct(@PathVariable int id, @RequestBody NewUser newuser) {
       newuser.setId(id);
       return ResponseEntity.ok().body(this.productService.updateProduct(product));
   }*/
    @GetMapping("/getAllUsers")
    public List<NewUser> getUsers(){

       return newUserRepository.findAll();
    }

    @GetMapping("/getUser/{id}")
    public Optional<NewUser> getUserById(@PathVariable int id){

        return newUserRepository.findById(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id){
        newUserRepository.deleteById(id);
        return "User Deleted Successfully";
    }

    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }


}
