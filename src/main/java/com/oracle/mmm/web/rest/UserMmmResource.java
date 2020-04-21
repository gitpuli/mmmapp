package com.oracle.mmm.web.rest;

import com.oracle.mmm.domain.UserMmm;
import com.oracle.mmm.repository.UserMmmRepository;
import com.oracle.mmm.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.oracle.mmm.domain.UserMmm}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UserMmmResource {

    private final Logger log = LoggerFactory.getLogger(UserMmmResource.class);

    private static final String ENTITY_NAME = "userMmm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserMmmRepository userMmmRepository;

    public UserMmmResource(UserMmmRepository userMmmRepository) {
        this.userMmmRepository = userMmmRepository;
    }

    /**
     * {@code POST  /user-mmms} : Create a new userMmm.
     *
     * @param userMmm the userMmm to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userMmm, or with status {@code 400 (Bad Request)} if the userMmm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-mmms")
    public ResponseEntity<UserMmm> createUserMmm(@Valid @RequestBody UserMmm userMmm) throws URISyntaxException {
        log.debug("REST request to save UserMmm : {}", userMmm);
        if (userMmm.getId() != null) {
            throw new BadRequestAlertException("A new userMmm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserMmm result = userMmmRepository.save(userMmm);
        return ResponseEntity.created(new URI("/api/user-mmms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-mmms} : Updates an existing userMmm.
     *
     * @param userMmm the userMmm to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userMmm,
     * or with status {@code 400 (Bad Request)} if the userMmm is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userMmm couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-mmms")
    public ResponseEntity<UserMmm> updateUserMmm(@Valid @RequestBody UserMmm userMmm) throws URISyntaxException {
        log.debug("REST request to update UserMmm : {}", userMmm);
        if (userMmm.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserMmm result = userMmmRepository.save(userMmm);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userMmm.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-mmms} : get all the userMmms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userMmms in body.
     */
    @GetMapping("/user-mmms")
    public List<UserMmm> getAllUserMmms() {
        log.debug("REST request to get all UserMmms");
        return userMmmRepository.findAll();
    }

    /**
     * {@code GET  /user-mmms/:id} : get the "id" userMmm.
     *
     * @param id the id of the userMmm to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userMmm, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-mmms/{id}")
    public ResponseEntity<UserMmm> getUserMmm(@PathVariable Long id) {
        log.debug("REST request to get UserMmm : {}", id);
        Optional<UserMmm> userMmm = userMmmRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userMmm);
    }

    /**
     * {@code DELETE  /user-mmms/:id} : delete the "id" userMmm.
     *
     * @param id the id of the userMmm to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-mmms/{id}")
    public ResponseEntity<Void> deleteUserMmm(@PathVariable Long id) {
        log.debug("REST request to delete UserMmm : {}", id);
        userMmmRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
