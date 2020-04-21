package com.oracle.mmm.web.rest;

import com.oracle.mmm.domain.UserGoalMmm;
import com.oracle.mmm.repository.UserGoalMmmRepository;
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
 * REST controller for managing {@link com.oracle.mmm.domain.UserGoalMmm}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UserGoalMmmResource {

    private final Logger log = LoggerFactory.getLogger(UserGoalMmmResource.class);

    private static final String ENTITY_NAME = "userGoalMmm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserGoalMmmRepository userGoalMmmRepository;

    public UserGoalMmmResource(UserGoalMmmRepository userGoalMmmRepository) {
        this.userGoalMmmRepository = userGoalMmmRepository;
    }

    /**
     * {@code POST  /user-goal-mmms} : Create a new userGoalMmm.
     *
     * @param userGoalMmm the userGoalMmm to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userGoalMmm, or with status {@code 400 (Bad Request)} if the userGoalMmm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-goal-mmms")
    public ResponseEntity<UserGoalMmm> createUserGoalMmm(@Valid @RequestBody UserGoalMmm userGoalMmm) throws URISyntaxException {
        log.debug("REST request to save UserGoalMmm : {}", userGoalMmm);
        if (userGoalMmm.getId() != null) {
            throw new BadRequestAlertException("A new userGoalMmm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserGoalMmm result = userGoalMmmRepository.save(userGoalMmm);
        return ResponseEntity.created(new URI("/api/user-goal-mmms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-goal-mmms} : Updates an existing userGoalMmm.
     *
     * @param userGoalMmm the userGoalMmm to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userGoalMmm,
     * or with status {@code 400 (Bad Request)} if the userGoalMmm is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userGoalMmm couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-goal-mmms")
    public ResponseEntity<UserGoalMmm> updateUserGoalMmm(@Valid @RequestBody UserGoalMmm userGoalMmm) throws URISyntaxException {
        log.debug("REST request to update UserGoalMmm : {}", userGoalMmm);
        if (userGoalMmm.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserGoalMmm result = userGoalMmmRepository.save(userGoalMmm);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userGoalMmm.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-goal-mmms} : get all the userGoalMmms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userGoalMmms in body.
     */
    @GetMapping("/user-goal-mmms")
    public List<UserGoalMmm> getAllUserGoalMmms() {
        log.debug("REST request to get all UserGoalMmms");
        return userGoalMmmRepository.findAll();
    }

    /**
     * {@code GET  /user-goal-mmms/:id} : get the "id" userGoalMmm.
     *
     * @param id the id of the userGoalMmm to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userGoalMmm, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-goal-mmms/{id}")
    public ResponseEntity<UserGoalMmm> getUserGoalMmm(@PathVariable Long id) {
        log.debug("REST request to get UserGoalMmm : {}", id);
        Optional<UserGoalMmm> userGoalMmm = userGoalMmmRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userGoalMmm);
    }

    /**
     * {@code DELETE  /user-goal-mmms/:id} : delete the "id" userGoalMmm.
     *
     * @param id the id of the userGoalMmm to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-goal-mmms/{id}")
    public ResponseEntity<Void> deleteUserGoalMmm(@PathVariable Long id) {
        log.debug("REST request to delete UserGoalMmm : {}", id);
        userGoalMmmRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
