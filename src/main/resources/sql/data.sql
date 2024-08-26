-- Users
insert into USERS (ID, EMAIL, PASSWORD, USERNAME, ENABLED)
VALUES (0, 'anonymous@example.com', '', 'anonymous', true);

insert into USERS (ID, EMAIL, PASSWORD, USERNAME, ENABLED)
VALUES (1, 'admin@example.com', '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'admin', true);

insert into USERS (ID, EMAIL, PASSWORD, USERNAME, ENABLED)
VALUES (2, 'user@example.com', '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'user', true);

-- Authorities
insert into AUTHORITIES (ID, AUTHORITY)
VALUES (0, 'ROLE_ANONYMOUS');

insert into AUTHORITIES (ID, AUTHORITY)
VALUES (1, 'ROLE_ADMIN');

insert into AUTHORITIES (ID, AUTHORITY)
VALUES (2, 'ROLE_USER');

-- User-Authority Associations
insert into USERS_AUTHORITIES (USER_ID, AUTHORITY_ID)
VALUES (1, 1);

insert into USERS_AUTHORITIES (USER_ID, AUTHORITY_ID)
VALUES (1, 2);

insert into USERS_AUTHORITIES (USER_ID, AUTHORITY_ID)
VALUES (2, 2);

-- Posts
insert into POSTS (ID, BODY, CREATION_DATE, TITLE, USER_ID)
VALUES (1, 'In the fast-evolving world of educational technology, it is essential to stay ahead of the curve to unlock new revenue opportunities and maintain a competitive edge. In this detailed case study, we explore how Valuebound assisted a leading provider of educational products and services in achieving this objective. The client faced a significant challenge: how to offer their applications as free downloads on popular app stores while ensuring that access remained restricted to registered users who had purchased the relevant course content.

To address this challenge, Valuebound developed a unique solution that combined innovative authentication mechanisms with an efficient admin portal. This approach allowed the client to meet their goal without incurring the high costs associated with app store commissions. By implementing these strategies, the client was able to offer their educational applications for free while maintaining control over who could access the content.

The technology stack used in this project included a range of cutting-edge tools and platforms. Valuebound utilized React for the front-end development, Node and Express for the back-end, and MariaDB for the database. The applications were deployed on AWS EC2 t2.micro instances, ensuring scalability and reliability. Additionally, the development included Native Android and iOS applications, further broadening the reach of the client''s offerings.

A key component of this solution was the implementation of device-based security. By storing each device''s unique identifier in the database and cross-referencing login requests, Valuebound was able to ensure that only authorized users could access the content. This added an extra layer of security, making it difficult for unauthorized users to gain access, even if they had managed to download the application.

In summary, Valuebound''s innovative approach not only enabled the client to offer their applications as free downloads but also ensured that only paying users could access the content. This case study highlights the importance of leveraging technology to create new revenue opportunities and maintain a competitive advantage in the educational sector.', 
'2020-01-10', 'Innovative Authentication Solutions in EdTech', 2);

insert into POSTS (ID, BODY, CREATION_DATE, TITLE, USER_ID)
VALUES (2, 'LORD Corporation, a global leader in adhesives, coatings, motion control devices, and sensing technologies, approached us with a critical issue. Their Drupal-based website was underperforming, especially on mobile devices, which was negatively impacting user experience and engagement. Our team was tasked with identifying the root causes of these performance issues and implementing strategic optimizations to enhance the website''s performance.

The first step in this process was to conduct a thorough analysis of the website using tools such as NewRelic, Google PageSpeed Insights, and WebPageTest. This analysis revealed several areas that required improvement, including server-side processing and client-side rendering. With these insights in hand, we developed a comprehensive optimization plan that addressed each of these issues.

One of the primary challenges we faced was optimizing the caching mechanisms in Drupal. While caching can significantly improve performance, it must be carefully configured to avoid potential pitfalls. For example, caching for logged-in users presents unique challenges, as it is essential to ensure that users receive the most up-to-date content while still benefiting from the performance improvements that caching provides. To address this, we explored options like Memcache and Redis, which allowed us to optimize caching for both anonymous and logged-in users.

In addition to caching, we also focused on optimizing the database configuration. By reviewing and optimizing query performance, we were able to reduce server load and improve overall website responsiveness. This was particularly important given the complexities of server-side processing in Drupal, which can often lead to slow loading times if not properly managed.

Finally, we implemented several client-side optimizations to further enhance the website''s performance on mobile devices. These included minimizing JavaScript and CSS files, leveraging browser caching, and optimizing images for faster load times. As a result of these efforts, LORD Corporation''s website now delivers a much-improved user experience, particularly on mobile devices, which has led to increased engagement and satisfaction among users.', 
'2020-02-20', 'Optimizing Drupal Performance for Mobile Users', 1);

insert into POSTS (ID, BODY, CREATION_DATE, TITLE, USER_ID)
VALUES (3, 'In the higher education sector, e-commerce platforms play a crucial role in facilitating the purchase and distribution of educational materials. Our recent project involved working with a leading e-commerce platform that offers a vast range of 22,000 products tailored for educators and students. The platform was already a key player in the market, but the client recognized the need for continuous innovation to meet the evolving needs of their users and stay competitive in the digital landscape.

Our primary objective was to upgrade and enhance the platform to provide an unmatched user experience while also ensuring it could accommodate future growth. One of the standout features of the platform was its sophisticated taxonomy-based hierarchical facet filters. These filters allowed users to easily navigate the extensive product catalog and find the materials they needed. However, as the platform grew, it became apparent that the existing CMS infrastructure was not equipped to handle the increased demand and complexity.

To address this, we implemented a comprehensive overhaul of the CMS, focusing on scalability and performance. We began by optimizing the platform''s integration with key systems such as Navision ERP, ChargeLogic payment gateway, and the SureTax Address Validation API. These integrations were crucial for maintaining the platform''s functionality, but they also presented significant challenges in terms of performance and scalability.

In addition to optimizing existing systems, we also introduced several new features to enhance the user experience. These included a more intuitive search interface, improved product recommendations, and a streamlined checkout process. By focusing on these areas, we were able to significantly improve the overall user experience, making it easier for educators and students to find and purchase the materials they needed.

Furthermore, we worked closely with the client to develop a long-term strategy for ongoing platform maintenance and optimization. This included regular performance audits, user feedback analysis, and the implementation of new features based on emerging trends and technologies. As a result of these efforts, the platform is now well-positioned to continue its growth and success in the higher education market.', 
'2020-03-30', 'Enhancing E-commerce Platforms for Higher Education', 2);

-- Comments
insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (1, 'This is a very insightful case study, particularly the innovative authentication mechanism implemented. It provides a great example of how technology can be leveraged to create new revenue opportunities.', current_timestamp(), 1, 0);

insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (2, 'The optimization strategies outlined here are impressive, especially the detailed analysis and the approach to caching for logged-in users.', current_timestamp(), 1, 2);

insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (3, 'The focus on user experience in the e-commerce platform upgrade is commendable. It''s clear that the project was approached with a deep understanding of the needs of educators and students.', current_timestamp(), 1, 0);

insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (4, 'I appreciate the detailed breakdown of the optimization techniques used for Drupal performance. It''s evident that a lot of thought went into ensuring a smooth user experience.', current_timestamp(), 2, 0);

insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (5, 'This post does a great job of highlighting the complexities involved in enhancing an e-commerce platform, especially with such a large product catalog.', current_timestamp(), 2, 1);

insert into COMMENTS (ID, BODY, CREATION_DATE, POST_ID, USER_ID)
VALUES (6, 'The strategies used to improve the mobile performance of LORD Corporation''s website are very relevant to the challenges we''re facing with our own site.', current_timestamp(), 1, 0);
