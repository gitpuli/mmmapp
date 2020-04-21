package com.oracle.mmm.web.rest;

import com.oracle.mmm.MmmappApp;
import com.oracle.mmm.domain.UserMmm;
import com.oracle.mmm.repository.UserMmmRepository;

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
 * Integration tests for the {@link UserMmmResource} REST controller.
 */
@SpringBootTest(classes = MmmappApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UserMmmResourceIT {

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
    private UserMmmRepository userMmmRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserMmmMockMvc;

    private UserMmm userMmm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserMmm createEntity(EntityManager em) {
        UserMmm userMmm = new UserMmm()
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
        return userMmm;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserMmm createUpdatedEntity(EntityManager em) {
        UserMmm userMmm = new UserMmm()
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
        return userMmm;
    }

    @BeforeEach
    public void initTest() {
        userMmm = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserMmm() throws Exception {
        int databaseSizeBeforeCreate = userMmmRepository.findAll().size();

        // Create the UserMmm
        restUserMmmMockMvc.perform(post("/api/user-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMmm)))
            .andExpect(status().isCreated());

        // Validate the UserMmm in the database
        List<UserMmm> userMmmList = userMmmRepository.findAll();
        assertThat(userMmmList).hasSize(databaseSizeBeforeCreate + 1);
        UserMmm testUserMmm = userMmmList.get(userMmmList.size() - 1);
        assertThat(testUserMmm.getRightAnkle()).isEqualTo(DEFAULT_RIGHT_ANKLE);
        assertThat(testUserMmm.getLeftAnkle()).isEqualTo(DEFAULT_LEFT_ANKLE);
        assertThat(testUserMmm.getRightCalf()).isEqualTo(DEFAULT_RIGHT_CALF);
        assertThat(testUserMmm.getLeftCalf()).isEqualTo(DEFAULT_LEFT_CALF);
        assertThat(testUserMmm.getRightThigh()).isEqualTo(DEFAULT_RIGHT_THIGH);
        assertThat(testUserMmm.getLeftThigh()).isEqualTo(DEFAULT_LEFT_THIGH);
        assertThat(testUserMmm.getRightArm()).isEqualTo(DEFAULT_RIGHT_ARM);
        assertThat(testUserMmm.getLeftArm()).isEqualTo(DEFAULT_LEFT_ARM);
        assertThat(testUserMmm.getHips()).isEqualTo(DEFAULT_HIPS);
        assertThat(testUserMmm.getWaist()).isEqualTo(DEFAULT_WAIST);
        assertThat(testUserMmm.getBust()).isEqualTo(DEFAULT_BUST);
        assertThat(testUserMmm.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUserMmm.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testUserMmm.getLastUpdateDate()).isEqualTo(DEFAULT_LAST_UPDATE_DATE);
        assertThat(testUserMmm.getLastUpdateBy()).isEqualTo(DEFAULT_LAST_UPDATE_BY);
    }

    @Test
    @Transactional
    public void createUserMmmWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userMmmRepository.findAll().size();

        // Create the UserMmm with an existing ID
        userMmm.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserMmmMockMvc.perform(post("/api/user-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMmm)))
            .andExpect(status().isBadRequest());

        // Validate the UserMmm in the database
        List<UserMmm> userMmmList = userMmmRepository.findAll();
        assertThat(userMmmList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userMmmRepository.findAll().size();
        // set the field null
        userMmm.setCreatedDate(null);

        // Create the UserMmm, which fails.

        restUserMmmMockMvc.perform(post("/api/user-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMmm)))
            .andExpect(status().isBadRequest());

        List<UserMmm> userMmmList = userMmmRepository.findAll();
        assertThat(userMmmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = userMmmRepository.findAll().size();
        // set the field null
        userMmm.setCreatedBy(null);

        // Create the UserMmm, which fails.

        restUserMmmMockMvc.perform(post("/api/user-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMmm)))
            .andExpect(status().isBadRequest());

        List<UserMmm> userMmmList = userMmmRepository.findAll();
        assertThat(userMmmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userMmmRepository.findAll().size();
        // set the field null
        userMmm.setLastUpdateDate(null);

        // Create the UserMmm, which fails.

        restUserMmmMockMvc.perform(post("/api/user-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMmm)))
            .andExpect(status().isBadRequest());

        List<UserMmm> userMmmList = userMmmRepository.findAll();
        assertThat(userMmmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdateByIsRequired() throws Exception {
        int databaseSizeBeforeTest = userMmmRepository.findAll().size();
        // set the field null
        userMmm.setLastUpdateBy(null);

        // Create the UserMmm, which fails.

        restUserMmmMockMvc.perform(post("/api/user-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMmm)))
            .andExpect(status().isBadRequest());

        List<UserMmm> userMmmList = userMmmRepository.findAll();
        assertThat(userMmmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserMmms() throws Exception {
        // Initialize the database
        userMmmRepository.saveAndFlush(userMmm);

        // Get all the userMmmList
        restUserMmmMockMvc.perform(get("/api/user-mmms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userMmm.getId().intValue())))
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
    public void getUserMmm() throws Exception {
        // Initialize the database
        userMmmRepository.saveAndFlush(userMmm);

        // Get the userMmm
        restUserMmmMockMvc.perform(get("/api/user-mmms/{id}", userMmm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userMmm.getId().intValue()))
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
    public void getNonExistingUserMmm() throws Exception {
        // Get the userMmm
        restUserMmmMockMvc.perform(get("/api/user-mmms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserMmm() throws Exception {
        // Initialize the database
        userMmmRepository.saveAndFlush(userMmm);

        int databaseSizeBeforeUpdate = userMmmRepository.findAll().size();

        // Update the userMmm
        UserMmm updatedUserMmm = userMmmRepository.findById(userMmm.getId()).get();
        // Disconnect from session so that the updates on updatedUserMmm are not directly saved in db
        em.detach(updatedUserMmm);
        updatedUserMmm
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

        restUserMmmMockMvc.perform(put("/api/user-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserMmm)))
            .andExpect(status().isOk());

        // Validate the UserMmm in the database
        List<UserMmm> userMmmList = userMmmRepository.findAll();
        assertThat(userMmmList).hasSize(databaseSizeBeforeUpdate);
        UserMmm testUserMmm = userMmmList.get(userMmmList.size() - 1);
        assertThat(testUserMmm.getRightAnkle()).isEqualTo(UPDATED_RIGHT_ANKLE);
        assertThat(testUserMmm.getLeftAnkle()).isEqualTo(UPDATED_LEFT_ANKLE);
        assertThat(testUserMmm.getRightCalf()).isEqualTo(UPDATED_RIGHT_CALF);
        assertThat(testUserMmm.getLeftCalf()).isEqualTo(UPDATED_LEFT_CALF);
        assertThat(testUserMmm.getRightThigh()).isEqualTo(UPDATED_RIGHT_THIGH);
        assertThat(testUserMmm.getLeftThigh()).isEqualTo(UPDATED_LEFT_THIGH);
        assertThat(testUserMmm.getRightArm()).isEqualTo(UPDATED_RIGHT_ARM);
        assertThat(testUserMmm.getLeftArm()).isEqualTo(UPDATED_LEFT_ARM);
        assertThat(testUserMmm.getHips()).isEqualTo(UPDATED_HIPS);
        assertThat(testUserMmm.getWaist()).isEqualTo(UPDATED_WAIST);
        assertThat(testUserMmm.getBust()).isEqualTo(UPDATED_BUST);
        assertThat(testUserMmm.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserMmm.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUserMmm.getLastUpdateDate()).isEqualTo(UPDATED_LAST_UPDATE_DATE);
        assertThat(testUserMmm.getLastUpdateBy()).isEqualTo(UPDATED_LAST_UPDATE_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingUserMmm() throws Exception {
        int databaseSizeBeforeUpdate = userMmmRepository.findAll().size();

        // Create the UserMmm

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserMmmMockMvc.perform(put("/api/user-mmms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMmm)))
            .andExpect(status().isBadRequest());

        // Validate the UserMmm in the database
        List<UserMmm> userMmmList = userMmmRepository.findAll();
        assertThat(userMmmList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserMmm() throws Exception {
        // Initialize the database
        userMmmRepository.saveAndFlush(userMmm);

        int databaseSizeBeforeDelete = userMmmRepository.findAll().size();

        // Delete the userMmm
        restUserMmmMockMvc.perform(delete("/api/user-mmms/{id}", userMmm.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserMmm> userMmmList = userMmmRepository.findAll();
        assertThat(userMmmList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
