package com.oracle.mmm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.oracle.mmm.web.rest.TestUtil;

public class UserMmmTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserMmm.class);
        UserMmm userMmm1 = new UserMmm();
        userMmm1.setId(1L);
        UserMmm userMmm2 = new UserMmm();
        userMmm2.setId(userMmm1.getId());
        assertThat(userMmm1).isEqualTo(userMmm2);
        userMmm2.setId(2L);
        assertThat(userMmm1).isNotEqualTo(userMmm2);
        userMmm1.setId(null);
        assertThat(userMmm1).isNotEqualTo(userMmm2);
    }
}
