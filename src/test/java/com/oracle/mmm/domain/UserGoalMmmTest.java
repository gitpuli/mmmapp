package com.oracle.mmm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.oracle.mmm.web.rest.TestUtil;

public class UserGoalMmmTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserGoalMmm.class);
        UserGoalMmm userGoalMmm1 = new UserGoalMmm();
        userGoalMmm1.setId(1L);
        UserGoalMmm userGoalMmm2 = new UserGoalMmm();
        userGoalMmm2.setId(userGoalMmm1.getId());
        assertThat(userGoalMmm1).isEqualTo(userGoalMmm2);
        userGoalMmm2.setId(2L);
        assertThat(userGoalMmm1).isNotEqualTo(userGoalMmm2);
        userGoalMmm1.setId(null);
        assertThat(userGoalMmm1).isNotEqualTo(userGoalMmm2);
    }
}
