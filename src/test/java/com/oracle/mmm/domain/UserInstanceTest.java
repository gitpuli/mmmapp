package com.oracle.mmm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.oracle.mmm.web.rest.TestUtil;

public class UserInstanceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserInstance.class);
        UserInstance userInstance1 = new UserInstance();
        userInstance1.setId(1L);
        UserInstance userInstance2 = new UserInstance();
        userInstance2.setId(userInstance1.getId());
        assertThat(userInstance1).isEqualTo(userInstance2);
        userInstance2.setId(2L);
        assertThat(userInstance1).isNotEqualTo(userInstance2);
        userInstance1.setId(null);
        assertThat(userInstance1).isNotEqualTo(userInstance2);
    }
}
