package s24.backend.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s24.backend.domain.AppUser;
import s24.backend.domain.AppUserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RestAppUserController {

    @Autowired
    private AppUserRepository appuserrepo;

    // Get a specific AppUser by username
    @GetMapping("appuser/{username}")
    public Optional<AppUser> getAppUserByUsername(@PathVariable("username") String username) {
        return Optional.of((appuserrepo.findByUsername(username)));
    }

      // Get specific customer by id
    @GetMapping("/appuser/{id}")
    public Optional<AppUser> getAppUserById(@PathVariable("id") Long userid) {
        return appuserrepo.findById(userid);
    }

    // Adding a new AppUser
    @PostMapping("/addappuser")
    public AppUser newAppUser(@RequestBody AppUser newAppUser) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newAppUser.setPasswordhash(encoder.encode(newAppUser.getPasswordhash()));
        newAppUser.setRole("ROLE_USER");
        return appuserrepo.save(newAppUser);
    }

    // Deleting an AppUser
    @DeleteMapping("/deleteappuser/{id}")
    public Iterable<AppUser> deleteAppUser(@PathVariable("id") Long userid) {

        appuserrepo.deleteById(userid);
        return appuserrepo.findAll();
    }

}
