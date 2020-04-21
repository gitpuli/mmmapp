package com.oracle.mmm.web.rest;

import com.oracle.mmm.MmmappApp;
import com.oracle.mmm.domain.UserGoalMmm;
import com.oracle.mmm.repository.UserGoalMmmRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserGoalMmmResource} REST controller.
 */
@SpringBootTest(classes = MmmappApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UserGoalMmmResourceIT {

    private static final Integer DEFAULT_RIGHT_ANKLE = 1;
    private static final Integer UPDATED_RIGHT_ANKLE = 2;

    private static final Integer DEFAULT_LEFT_ANKLE = 1;
    private static final Integer UPDATED_LEFT_ANKLE = 2;

    private static final Integer DEFAULT_RIGHT_CALF = 1;
    private static final Integer UPDATED_RIGHT_CALF = 2;

    private static final Integer DEFAULT_LEFT_CALF = 1;
    private static final Integer UPDATED_LEFT_CALF = 2;

    private static final Integer DEFAULT_RIGHT_THIGH = 1;
    private static final Integer UPDATED_RIGHT_THIGH = 2;

    private static final Integer DEFAULT_LEFT_THIGH = 1;
    private static final Integer UPDATED_LEFT_THIGH = 2;

    private static final Integer DEFAULT_RIGHT_ARM = 1;
    private static final Integer UPDATED_RIGHT_ARM = 2;

    private static final Integer DEFAULT_LEFT_ARM = 1;
    private static final Integer UPDATED_LEFT_ARM = 2;

    private static final Integer DEFAULT_HIPS = 1;
    private static final Integer UPDATED_HIPS = 2;

    private static final Integer DEFAULT_WAIST = 1;
    private static final Integer UPDATED_WAIST = 2;

    private static final Integer DEFAULT_BUST = 1;
    private static final Integer UPDATED_BUST = 2;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LAST_UPDATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_UPDATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LAST_UPDATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATE_BY = "BBBBBBBBBB";

    @Autowired
    private UserGoalMmmRepository userGoalMmmRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserGoalMmmMockMvc;

    private UserGoalMmm userGoalMmm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserGoalMmm createEntity(EntityManager em) {
        UserGoalMmm userGoalMmm = new UserGoalMmm()
            .rightAnkle(DEFAULT_RIGHT_ANKLE)
            .leftAnkle(DEFAULT_LEFT_ANKLE)
            .rightCalf(DEFAULT_RIGHT_CALF)
            .leftCalf(DEFAULT_LEFT_CALF)
            .rightThigh(DEFAULT_RIGHT_THIGH)
            .leftThigh(DEFAULT_LEFT_THIGH)
            .rightArm(DEFAULT_RIGHT_ARM)
            .leftArm(DEFAULT_LEFT_ARM)
            .hips(DEFAULT_HIPS)
            .waist(DEFAULT_WAIST)
            .bust(DEFAULT_BUST)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdateDate(DEFAULT_LAST_UPDATE_DATE)
            .lastUpdateBy(DEFAULT_LAST_UPDATE_BY);
        return userGoalMmm;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserGoalMmm createUpdatedEntity(EntityManager em) {
        UserGoalMmm userGoalMmm = new UserGoalMmm()
            .rightAnkle(UPDATED_RIGHT_ANKLE)
            .leftAnkle(UPDATED_LEFT_ANKLE)
            .rightCalf(UPDATED_RIGHT_CALF)
            .leftCalf(UPDATED_LEFT_CALF)
            .rightThigh(UPDATED_RIGHT_THIGH)
            .leftThigh(UPDATED_LEFT_THIGH)
            .rightArm(UPDATED_RIGHT_ARM)
            .leftArm(UPDATED_LEFT_ARM)
            .hips(UPDATED_HIPS)
            .waist(UPDATED_WAIST)
            .bust(UPDATED_BUST)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdateDate(UPDATED_LAST_UPDATE_DATE)
            .lastUpdateBy(UPDATED_LAST_UPDATE_BY);
        return userGoalMmm;
    }

    @BeforeEach
    public void initTest() {
        userGoalMmm = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserGoalMmm() throws Exception {
        int databaseSizeBeforeCreate = userGoalMmmRepository.findAll().size();

        // Create the UserGoalMmm
        restUserGoalMmmMockMvc.perform(post("/api/user-goal-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userGoalMmm)))
            .andExpect(status().isCreated());

        // Validate the UserGoalMmm in the database
        List<UserGoalMmm> userGoalMmmList = userGoalMmmRepository.findAll();
        assertThat(userGoalMmmList).hasSize(databaseSizeBeforeCreate + 1);
        UserGoalMmm testUserGoalMmm = userGoalMmmList.get(userGoalMmmList.size() - 1);
        assertThat(testUserGoalMmm.getRightAnkle()).isEqualTo(DEFAULT_RIGHT_ANKLE);
        assertThat(testUserGoalMmm.getLeftAnkle()).isEqualTo(DEFAULT_LEFT_ANKLE);
        assertThat(testUserGoalMmm.getRightCalf()).isEqualTo(DEFAULT_RIGHT_CALF);
        assertThat(testUserGoalMmm.getLeftCalf()).isEqualTo(DEFAULT_LEFT_CALF);
        assertThat(testUserGoalMmm.getRightThigh()).isEqualTo(DEFAULT_RIGHT_THIGH);
        assertThat(testUserGoalMmm.getLeftThigh()).isEqualTo(DEFAULT_LEFT_THIGH);
        assertThat(testUserGoalMmm.getRightArm()).isEqualTo(DEFAULT_RIGHT_ARM);
        assertThat(testUserGoalMmm.getLeftArm()).isEqualTo(DEFAULT_LEFT_ARM);
        assertThat(testUserGoalMmm.getHips()).isEqualTo(DEFAULT_HIPS);
        assertThat(testUserGoalMmm.getWaist()).isEqualTo(DEFAULT_WAIST);
        assertThat(testUserGoalMmm.getBust()).isEqualTo(DEFAULT_BUST);
        assertThat(testUserGoalMmm.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUserGoalMmm.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testUserGoalMmm.getLastUpdateDate()).isEqualTo(DEFAULT_LAST_UPDATE_DATE);
        assertThat(testUserGoalMmm.getLastUpdateBy()).isEqualTo(DEFAULT_LAST_UPDATE_BY);
    }

    @Test
    @Transactional
    public void createUserGoalMmmWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userGoalMmmRepository.findAll().size();

        // Create the UserGoalMmm with an existing ID
        userGoalMmm.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserGoalMmmMockMvc.perform(post("/api/user-goal-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userGoalMmm)))
            .andExpect(status().isBadRequest());

        // Validate the UserGoalMmm in the database
        List<UserGoalMmm> userGoalMmmList = userGoalMmmRepository.findAll();
        assertThat(userGoalMmmList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userGoalMmmRepository.findAll().size();
        // set the field null
        userGoalMmm.setCreatedDate(null);

        // Create the UserGoalMmm, which fails.

        restUserGoalMmmMockMvc.perform(post("/api/user-goal-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userGoalMmm)))
            .andExpect(status().isBadRequest());

        List<UserGoalMmm> userGoalMmmList = userGoalMmmRepository.findAll();
        assertThat(userGoalMmmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = userGoalMmmRepository.findAll().size();
        // set the field null
        userGoalMmm.setCreatedBy(null);

        // Create the UserGoalMmm, which fails.

        restUserGoalMmmMockMvc.perform(post("/api/user-goal-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userGoalMmm)))
            .andExpect(status().isBadRequest());

        List<UserGoalMmm> userGoalMmmList = userGoalMmmRepository.findAll();
        assertThat(userGoalMmmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userGoalMmmRepository.findAll().size();
        // set the field null
        userGoalMmm.setLastUpdateDate(null);

        // Create the UserGoalMmm, which fails.

        restUserGoalMmmMockMvc.perform(post("/api/user-goal-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userGoalMmm)))
            .andExpect(status().isBadRequest());

        List<UserGoalMmm> userGoalMmmList = userGoalMmmRepository.findAll();
        assertThat(userGoalMmmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdateByIsRequired() throws Exception {
        int databaseSizeBeforeTest = userGoalMmmRepository.findAll().size();
        // set the field null
        userGoalMmm.setLastUpdateBy(null);

        // Create the UserGoalMmm, which fails.

        restUserGoalMmmMockMvc.perform(post("/api/user-goal-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userGoalMmm)))
            .andExpect(status().isBadRequest());

        List<UserGoalMmm> userGoalMmmList = userGoalMmmRepository.findAll();
        assertThat(userGoalMmmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserGoalMmms() throws Exception {
        // Initialize the database
        userGoalMmmRepository.saveAndFlush(userGoalMmm);

        // Get all the userGoalMmmList
        restUserGoalMmmMockMvc.perform(get("/api/user-goal-mmms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userGoalMmm.getId().intValue())))
            .andExpect(jsonPath("$.[*].rightAnkle").value(hasItem(DEFAULT_RIGHT_ANKLE)))
            .andExpect(jsonPath("$.[*].leftAnkle").value(hasItem(DEFAULT_LEFT_ANKLE)))
            .andExpect(jsonPath("$.[*].rightCalf").value(hasItem(DEFAULT_RIGHT_CALF)))
            .andExpect(jsonPath("$.[*].leftCalf").value(hasItem(DEFAULT_LEFT_CALF)))
            .andExpect(jsonPath("$.[*].rightThigh").value(hasItem(DEFAULT_RIGHT_THIGH)))
            .andExpect(jsonPath("$.[*].leftThigh").value(hasItem(DEFAULT_LEFT_THIGH)))
            .andExpect(jsonPath("$.[*].rightArm").value(hasItem(DEFAULT_RIGHT_ARM)))
            .andExpect(jsonPath("$.[*].leftArm").value(hasItem(DEFAULT_LEFT_ARM)))
            .andExpect(jsonPath("$.[*].hips").value(hasItem(DEFAULT_HIPS)))
            .andExpect(jsonPath("$.[*].waist").value(hasItem(DEFAULT_WAIST)))
            .andExpect(jsonPath("$.[*].bust").value(hasItem(DEFAULT_BUST)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdateDate").value(hasItem(DEFAULT_LAST_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdateBy").value(hasItem(DEFAULT_LAST_UPDATE_BY)));
    }
    
    @Test
    @Transactional
    public void getUserGoalMmm() throws Exception {
        // Initialize the database
        userGoalMmmRepository.saveAndFlush(userGoalMmm);

        // Get the userGoalMmm
        restUserGoalMmmMockMvc.perform(get("/api/user-goal-mmms/{id}", userGoalMmm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userGoalMmm.getId().intValue()))
            .andExpect(jsonPath("$.rightAnkle").value(DEFAULT_RIGHT_ANKLE))
            .andExpect(jsonPath("$.leftAnkle").value(DEFAULT_LEFT_ANKLE))
            .andExpect(jsonPath("$.rightCalf").value(DEFAULT_RIGHT_CALF))
            .andExpect(jsonPath("$.leftCalf").value(DEFAULT_LEFT_CALF))
            .andExpect(jsonPath("$.rightThigh").value(DEFAULT_RIGHT_THIGH))
            .andExpect(jsonPath("$.leftThigh").value(DEFAULT_LEFT_THIGH))
            .andExpect(jsonPath("$.rightArm").value(DEFAULT_RIGHT_ARM))
            .andExpect(jsonPath("$.leftArm").value(DEFAULT_LEFT_ARM))
            .andExpect(jsonPath("$.hips").value(DEFAULT_HIPS))
            .andExpect(jsonPath("$.waist").value(DEFAULT_WAIST))
            .andExpect(jsonPath("$.bust").value(DEFAULT_BUST))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdateDate").value(DEFAULT_LAST_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdateBy").value(DEFAULT_LAST_UPDATE_BY));
    }

    @Test
    @Transactional
    public void getNonExistingUserGoalMmm() throws Exception {
        // Get the userGoalMmm
        restUserGoalMmmMockMvc.perform(get("/api/user-goal-mmms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserGoalMmm() throws Exception {
        // Initialize the database
        userGoalMmmRepository.saveAndFlush(userGoalMmm);

        int databaseSizeBeforeUpdate = userGoalMmmRepository.findAll().size();

        // Update the userGoalMmm
        UserGoalMmm updatedUserGoalMmm = userGoalMmmRepository.findById(userGoalMmm.getId()).get();
        // Disconnect from session so that the updates on updatedUserGoalMmm are not directly saved in db
        em.detach(updatedUserGoalMmm);
        updatedUserGoalMmm
            .rightAnkle(UPDATED_RIGHT_ANKLE)
            .leftAnkle(UPDATED_LEFT_ANKLE)
            .rightCalf(UPDATED_RIGHT_CALF)
            .leftCalf(UPDATED_LEFT_CALF)
            .rightThigh(UPDATED_RIGHT_THIGH)
            .leftThigh(UPDATED_LEFT_THIGH)
            .rightArm(UPDATED_RIGHT_ARM)
            .leftArm(UPDATED_LEFT_ARM)
            .hips(UPDATED_HIPS)
            .waist(UPDATED_WAIST)
            .bust(UPDATED_BUST)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdateDate(UPDATED_LAST_UPDATE_DATE)
            .lastUpdateBy(UPDATED_LAST_UPDATE_BY);

        restUserGoalMmmMockMvc.perform(put("/api/user-goal-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserGoalMmm)))
            .andExpect(status().isOk());

        // Validate the UserGoalMmm in the database
        List<UserGoalMmm> userGoalMmmList = userGoalMmmRepository.findAll();
        assertThat(userGoalMmmList).hasSize(databaseSizeBeforeUpdate);
        UserGoalMmm testUserGoalMmm = userGoalMmmList.get(userGoalMmmList.size() - 1);
        assertThat(testUserGoalMmm.getRightAnkle()).isEqualTo(UPDATED_RIGHT_ANKLE);
        assertThat(testUserGoalMmm.getLeftAnkle()).isEqualTo(UPDATED_LEFT_ANKLE);
        assertThat(testUserGoalMmm.getRightCalf()).isEqualTo(UPDATED_RIGHT_CALF);
        assertThat(testUserGoalMmm.getLeftCalf()).isEqualTo(UPDATED_LEFT_CALF);
        assertThat(testUserGoalMmm.getRightThigh()).isEqualTo(UPDATED_RIGHT_THIGH);
        assertThat(testUserGoalMmm.getLeftThigh()).isEqualTo(UPDATED_LEFT_THIGH);
        assertThat(testUserGoalMmm.getRightArm()).isEqualTo(UPDATED_RIGHT_ARM);
        assertThat(testUserGoalMmm.getLeftArm()).isEqualTo(UPDATED_LEFT_ARM);
        assertThat(testUserGoalMmm.getHips()).isEqualTo(UPDATED_HIPS);
        assertThat(testUserGoalMmm.getWaist()).isEqualTo(UPDATED_WAIST);
        assertThat(testUserGoalMmm.getBust()).isEqualTo(UPDATED_BUST);
        assertThat(testUserGoalMmm.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserGoalMmm.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUserGoalMmm.getLastUpdateDate()).isEqualTo(UPDATED_LAST_UPDATE_DATE);
        assertThat(testUserGoalMmm.getLastUpdateBy()).isEqualTo(UPDATED_LAST_UPDATE_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingUserGoalMmm() throws Exception {
        int databaseSizeBeforeUpdate = userGoalMmmRepository.findAll().size();

        // Create the UserGoalMmm

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserGoalMmmMockMvc.perform(put("/api/user-goal-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userGoalMmm)))
            .andExpect(status().isBadRequest());

        // Validate the UserGoalMmm in the database
        List<UserGoalMmm> userGoalMmmList = userGoalMmmRepository.findAll();
        assertThat(userGoalMmmList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserGoalMmm() throws Exception {
        // Initialize the database
        userGoalMmmRepository.saveAndFlush(userGoalMmm);

        int databaseSizeBeforeDelete = userGoalMmmRepository.findAll().size();

        // Delete the userGoalMmm
        restUserGoalMmmMockMvc.perform(delete("/api/user-goal-mmms/{id}", userGoalMmm.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserGoalMmm> userGoalMmmList = userGoalMmmRepository.findAll();
        assertThat(userGoalMmmList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
