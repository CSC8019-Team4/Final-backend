package uk.ac.ncl.csc8019.team4.location;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.ncl.csc8019.team4.BaseIntegrationTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LocationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void publicCanViewLocations() throws Exception {
        // Anyone should be able to see where the kiosks are
        mockMvc.perform(get("/api/locations"))
                .andExpect(status().isOk());
    }

    @Test
    void onlyStaffCanUpdateLocationStatus() throws Exception {
        // A customer should not be allowed to close a kiosk
        mockMvc.perform(get("/api/locations/1/status")
                .with(user("customer").roles("CUSTOMER")))
                .andExpect(status().isForbidden());
    }
}