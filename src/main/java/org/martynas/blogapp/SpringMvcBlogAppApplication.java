// package org.martynas.blogapp;

// import org.h2.tools.Server;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import java.sql.SQLException;

// @SpringBootApplication
// public class SpringMvcBlogAppApplication {

//     public static void main(String[] args) {
//         SpringApplication.run(SpringMvcBlogAppApplication.class, args);
//     }

//     // Start internal H2 server so we can query the DB from IDE
//     @Bean(initMethod = "start", destroyMethod = "stop")
//     public Server inMemoryH2DatabaseServer() throws SQLException {
//         return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
//     }
// }
package org.martynas.blogapp;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.sql.SQLException;

@SpringBootApplication
public class SpringMvcBlogAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcBlogAppApplication.class, args);
    }

    // Start internal H2 server with dynamic port configuration
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer(Environment env) throws SQLException {
        // Get the port from the environment, or fallback to 9092
        String port = env.getProperty("H2_PORT", "9092");
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", port);
    }
}
