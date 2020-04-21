package com.oracle.mmm.web.rest;

import com.oracle.mmm.MmmappApp;
import com.oracle.mmm.domain.UserInstance;
import com.oracle.mmm.repository.UserInstanceRepository;

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
 * Integration tests for the {@link UserInstanceResource} REST controller.
 */
@SpringBootTest(classes = MmmappApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UserInstanceResourceIT {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_USER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_USER_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_USER_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LAST_UPDATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_UPDATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LAST_UPDATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATE_BY = "BBBBBBBBBB";

    @Autowired
    private UserInstanceRepository userInstanceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserInstanceMockMvc;

    private UserInstance userInstance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserInstance createEntity(EntityManager em) {
        UserInstance userInstance = new UserInstance()
            .userName(DEFAULT_USER_NAME)
            .userEmail(DEFAULT_USER_EMAIL)
            .userPhone(DEFAULT_USER_PHONE)
            .password(DEFAULT_PASSWORD)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdateDate(DEFAULT_LAST_UPDATE_DATE)
            .lastUpdateBy(DEFAULT_LAST_UPDATE_BY);
        return userInstance;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserInstance createUpdatedEntity(EntityManager em) {
        UserInstance userInstance = new UserInstance()
            .userName(UPDATED_USER_NAME)
            .userEmail(UPDATED_USER_EMAIL)
            .userPhone(UPDATED_USER_PHONE)
            .password(UPDATED_PASSWORD)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdateDate(UPDATED_LAST_UPDATE_DATE)
            .lastUpdateBy(UPDATED_LAST_UPDATE_BY);
        return userInstance;
    }

    @BeforeEach
    public void initTest() {
        userInstance = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserInstance() throws Exception {
        int databaseSizeBeforeCreate = userInstanceRepository.findAll().size();

        // Create the UserInstance
        restUserInstanceMockMvc.perform(post("/api/user-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInstance)))
            .andExpect(status().isCreated());

        // Validate the UserInstance in the database
        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeCreate + 1);
        UserInstance testUserInstance = userInstanceList.get(userInstanceList.size() - 1);
        assertThat(testUserInstance.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUserInstance.getUserEmail()).isEqualTo(DEFAULT_USER_EMAIL);
        assertThat(testUserInstance.getUserPhone()).isEqualTo(DEFAULT_USER_PHONE);
        assertThat(testUserInstance.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testUserInstance.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUserInstance.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testUserInstance.getLastUpdateDate()).isEqualTo(DEFAULT_LAST_UPDATE_DATE);
        assertThat(testUserInstance.getLastUpdateBy()).isEqualTo(DEFAULT_LAST_UPDATE_BY);
    }

    @Test
    @Transactional
    public void createUserInstanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userInstanceRepository.findAll().size();

        // Create the UserInstance with an existing ID
        userInstance.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserInstanceMockMvc.perform(post("/api/user-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInstance)))
            .andExpect(status().isBadRequest());

        // Validate the UserInstance in the database
        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInstanceRepository.findAll().size();
        // set the field null
        userInstance.setUserName(null);

        // Create the UserInstance, which fails.

        restUserInstanceMockMvc.perform(post("/api/user-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInstance)))
            .andExpect(status().isBadRequest());

        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInstanceRepository.findAll().size();
        // set the field null
        userInstance.setUserEmail(null);

        // Create the UserInstance, which fails.

        restUserInstanceMockMvc.perform(post("/api/user-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInstance)))
            .andExpect(status().isBadRequest());

        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInstanceRepository.findAll().size();
        // set the field null
        userInstance.setPassword(null);

        // Create the UserInstance, which fails.

        restUserInstanceMockMvc.perform(post("/api/user-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInstance)))
            .andExpect(status().isBadRequest());

        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInstanceRepository.findAll().size();
        // set the field null
        userInstance.setCreatedDate(null);

        // Create the UserInstance, which fails.

        restUserInstanceMockMvc.perform(post("/api/user-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInstance)))
            .andExpect(status().isBadRequest());

        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInstanceRepository.findAll().size();
        // set the field null
        userInstance.setCreatedBy(null);

        // Create the UserInstance, which fails.

        restUserInstanceMockMvc.perform(post("/api/user-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInstance)))
            .andExpect(status().isBadRequest());

        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInstanceRepository.findAll().size();
        // set the field null
        userInstance.setLastUpdateDate(null);

        // Create the UserInstance, which fails.

        restUserInstanceMockMvc.perform(post("/api/user-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInstance)))
            .andExpect(status().isBadRequest());

        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdateByIsRequired() throws Exception {
        int databaseSizeBeforeTest = userInstanceRepository.findAll().size();
        // set the field null
        userInstance.setLastUpdateBy(null);

        // Create the UserInstance, which fails.

        restUserInstanceMockMvc.perform(post("/api/user-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInstance)))
            .andExpect(status().isBadRequest());

        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserInstances() throws Exception {
        // Initialize the database
        userInstanceRepository.saveAndFlush(userInstance);

        // Get all the userInstanceList
        restUserInstanceMockMvc.perform(get("/api/user-instances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userInstance.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].userEmail").value(hasItem(DEFAULT_USER_EMAIL)))
            .andExpect(jsonPath("$.[*].userPhone").value(hasItem(DEFAULT_USER_PHONE)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdateDate").value(hasItem(DEFAULT_LAST_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdateBy").value(hasItem(DEFAULT_LAST_UPDATE_BY)));
    }
    
    @Test
    @Transactional
    public void getUserInstance() throws Exception {
        // Initialize the database
        userInstanceRepository.saveAndFlush(userInstance);

        // Get the userInstance
        restUserInstanceMockMvc.perform(get("/api/user-instances/{id}", userInstance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userInstance.getId().intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.userEmail").value(DEFAULT_USER_EMAIL))
            .andExpect(jsonPath("$.userPhone").value(DEFAULT_USER_PHONE))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdateDate").value(DEFAULT_LAST_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdateBy").value(DEFAULT_LAST_UPDATE_BY));
    }

    @Test
    @Transactional
    public void getNonExistingUserInstance() throws Exception {
        // Get the userInstance
        restUserInstanceMockMvc.perform(get("/api/user-instances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserInstance() throws Exception {
        // Initialize the database
        userInstanceRepository.saveAndFlush(userInstance);

        int databaseSizeBeforeUpdate = userInstanceRepository.findAll().size();

        // Update the userInstance
        UserInstance updatedUserInstance = userInstanceRepository.findById(userInstance.getId()).get();
        // Disconnect from session so that the updates on updatedUserInstance are not directly saved in db
        em.detach(updatedUserInstance);
        updatedUserInstance
            .userName(UPDATED_USER_NAME)
            .userEmail(UPDATED_USER_EMAIL)
            .userPhone(UPDATED_USER_PHONE)
            .password(UPDATED_PASSWORD)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdateDate(UPDATED_LAST_UPDATE_DATE)
            .lastUpdateBy(UPDATED_LAST_UPDATE_BY);

        restUserInstanceMockMvc.perform(put("/api/user-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserInstance)))
            .andExpect(status().isOk());

        // Validate the UserInstance in the database
        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeUpdate);
        UserInstance testUserInstance = userInstanceList.get(userInstanceList.size() - 1);
        assertThat(testUserInstance.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testUserInstance.getUserEmail()).isEqualTo(UPDATED_USER_EMAIL);
        assertThat(testUserInstance.getUserPhone()).isEqualTo(UPDATED_USER_PHONE);
        assertThat(testUserInstance.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUserInstance.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserInstance.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUserInstance.getLastUpdateDate()).isEqualTo(UPDATED_LAST_UPDATE_DATE);
        assertThat(testUserInstance.getLastUpdateBy()).isEqualTo(UPDATED_LAST_UPDATE_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingUserInstance() throws Exception {
        int databaseSizeBeforeUpdate = userInstanceRepository.findAll().size();

        // Create the UserInstance

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserInstanceMockMvc.perform(put("/api/user-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userInstance)))
            .andExpect(status().isBadRequest());

        // Validate the UserInstance in the database
        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserInstance() throws Exception {
        // Initialize the database
        userInstanceRepository.saveAndFlush(userInstance);

        int databaseSizeBeforeDelete = userInstanceRepository.findAll().size();

        // Delete the userInstance
        restUserInstanceMockMvc.perform(delete("/api/user-instances/{id}", userInstance.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserInstance> userInstanceList = userInstanceRepository.findAll();
        assertThat(userInstanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
