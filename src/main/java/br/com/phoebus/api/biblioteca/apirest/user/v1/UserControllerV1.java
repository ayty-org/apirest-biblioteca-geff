package br.com.phoebus.api.biblioteca.apirest.user.v1;

import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import br.com.phoebus.api.biblioteca.apirest.user.service.DeleteUserService;
import br.com.phoebus.api.biblioteca.apirest.user.service.EditUserService;
import br.com.phoebus.api.biblioteca.apirest.user.service.GetUserService;
import br.com.phoebus.api.biblioteca.apirest.user.service.ListPageUsersService;
import br.com.phoebus.api.biblioteca.apirest.user.service.ListUsersService;
import br.com.phoebus.api.biblioteca.apirest.user.service.SaveUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/user")
public class UserControllerV1 {

    private final SaveUserService saveUserService;
    private final DeleteUserService deleteUserService;
    private final ListUsersService listUsersService;
    private final ListPageUsersService listPageUsersService;
    private final GetUserService getUserService;
    private final EditUserService editUserService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postUser(@Valid @RequestBody UserAppDTO newUserDTO) {
        saveUserService.save(newUserDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUserById(@PathVariable(value = "id") long id) {
        deleteUserService.delete(id);
    }

    @GetMapping
    List<UserAppDTO> listUsers() {
        return listUsersService.listUsers();
    }

    @GetMapping("/{id}")
    UserAppDTO getUser(@PathVariable(value = "id") long id) {
        return getUserService.getUser(id);
    }

    @GetMapping(params = {"page", "size"})
    Page<UserAppDTO> findPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return listPageUsersService.ListUserOnPage(page, size);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putUser(@Valid @PathVariable(value = "id") long id, @RequestBody UserAppDTO attUserDTO) {
        editUserService.edit(id, attUserDTO);
    }

}
