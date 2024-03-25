import com.english.springcrud.SpringcrudApplication;
import com.english.springcrud.config.WebSecurityConfig;
import com.english.springcrud.controllers.MainController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SpringcrudApplication.class, WebSecurityConfig.class})
@AutoConfigureMockMvc
public class LoginTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController controller;

    @Test
    public void test() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Главная")))
                .andExpect(content().string(containsString("Past Simple")));
    }

    @Test
    public void accessDeniedTest() throws Exception {
        this.mockMvc.perform(get("/simple/pastSimple/pastSimple"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void correctLoginTest() throws Exception {
        this.mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin()
                .user("halfa").password("1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

  //  @Test
//    public void badCredentials() throws Exception {
//        this.mockMvc.perform(post("/login")
//                    .param("user", "halfaroppo@gmail.com"))
//                    .andDo(print())
//                    .andExpect(status().isForbidden());
//    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
