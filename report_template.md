---
Title: 'Agile Development Processes Project Report'
Authors:
- Dimitrios Platis
- Lídia Nyman
- Elsa Mjöll Bergsteinsdóttir
- Lois Alberte Gómez Sánchez
- Ayesha Aslam
- Jobaer Ahmed
- Henrik Helén Edholm
---

# Project Description
This is an android application that allows users to subscribe to specific flights, providing flight information (e.g. delays, cancellation, early arrival) via push notifications.

The app will show all the flights from, and to, Gothenburg Landvetter Airport (GOT). Later, the user will be able to choose among other airports in Sweden which can further be expanded to the rest of the world depending on public interest. The user can filter the departures/arrivals and subscribe to some flights of concern. The user can filter to many flights. The user can choose (in settings) or in each flight what kind of notification he/she wants to receive about that flight.

# Sprint Log
## Sprint 1:
We started the sprint by populating the backlog of stories written as issues in git. Next we had to estimate the issues by giving them story points that would indicate their complexity, in our wiki the explanation of how we map our story point metric to complexity for the estimation of each issue can be read. We used a a well known estimation technique to estimate our backlog, the Planning Poker. For the Planning Poker, we used an online servie Plan IT Poker (http://www.planitpoker.com/) so every member of the group could remotely vote. After the results of the voting, we reasoned together to come to an estimation conclustion that everybody agreed on. When every issue had an estimation, we had to plan how many issues we were going to commit to for the first acceptance test.  Based on the time capacity each member should contribute per week and the issues that had the most priority, we decided to commit to 6 issues in Sprint 1. 

### Commitment
In the parathesis the estimation point of each issue, followed by the issue number.
1. Create a hello world application (3) #5
2. Travis CI setup (5) #26
3. Investigate appropriate API for flight retrieval (3) #3
4. Retrieve flight information from public API (5) #22
5. Parse flight information (programmatic representation of flight data) (5) #18
6. Present flight data (5) #13


### Work Done 
Feature | Commits  | Group members | Effort | Practices
----------------|----------------|----------------|----------------|----------------
Name and ID of each feature | Sha1 for the commit(s) that make up the feature | Names of the group members who developed the feature | The development effort expended, in story points or other unit of choice | The agile practices practiced when developing the feature
|#5|b8df8fe|Lídia, Elsa, Lois, Ayesha, Jobaer, Henrik|3|Simple Design, Collective Ownership|
|#26| 7de24dc, 917ab15 |Dimitris|5|Continuous Integration|
|#3|2c44c23|Ayesha, Lois|3|Pair Programming|
|#22|2c44c23, bc6100e, 06c9049, 3739373, 6740509 |Ayesha, Lois|5|Pair Programming, Refactoring, Simple Design|
|#18| dce7b29, 72ec745, 3739373, 43e74f7, 169b844|Dimitris, Lídia, Jobaer|5|Test-first, Refactoring, Pair Programming|
|#13| fb27b88, 64bb0e4, fe56364, 7ab13e2, |Henrik, Elsa Mjöll|5|Refactoring, Small Releases, Simple Design, Sustainable Pace|

### Reflections
During the 1st sprint we setted both the procedural and technical foundations of the product. The team got acquainted with various XP practices (e.g. pair programming, planning game etc) and was able to complete the planned user stories, indicating that the chosen sprint velocity is appropriate and allows for a sustainable development pace.

* **Planning poker**: Planning poker proved interestingly troublesome, as to its effectiveness and efficiency, since often large deviations between the team member estimations emerged that remained unsettled. This is however natural, as it was the first session of the kind that the team was engaged in and it is expected for the process to become more "normalized" and faster as the team members get better acquainted to each other, the agile methodology but most importantly the product under development and its challenges. However, the deviations among team members were also helpful in pointing out some trivial yet important details that other members were overlooking. therefore, it helped to take those details in account thus improving effort estimation.

* **Test-first**: Unit tests were written for a part of the system, namely the _Flight_ class. By writing the test cases first, based on the various use cases that were decided, we were able to formulate the class' API before the implementation. This allowed us to maintain a cleaner interface and avoid small bugs which could result from omitting to consider the various edge cases.

* **Pair programming**: We splitted some of the features among the team members in groups of two persons in order to execute pair programming. In some cases this was not possible due to conflicts in time schedules for members but for those that experienced pair programming, it was specially useful for the members who were not familiar with Android development or the managing of Git control version. However, there was some drawbacks associated with pair programming such as boredom for the member helping the other one who is actually coding. It would have been a more appropriate approach to actually use a timer to switch positions.

* **Continuous Integration**: By using continous integration we merged the code changes into our repository often, sometimes several times a day in order to have a functioning app at any moment. To produce a clean build of the system several times a day, we integrated Travis CI to our repository so it could compile the Android application for us. Travis runs our builds after every commit to the master branch, this way Travis can provide us with a "safety net": when we commit a change a build will be triggered automatically and if everything works as expected, nothing will prompt us, to the contrary if something stops working we would get an email that informs us about the problem and we can take action to react. 


* **Onsite Customer**: Having an onsite customer has felt reassuring. If we have any uncertanties of what the customer wants we have been able to get almost immediate response. Considering that it hasn't cost us any extra time to use this practice it should be considered to be rather efficient.

* **Small Releases**: Producing small (internal) releases have proven very beneficial for us. A lot of our initial development have had a lot of dependencies. By producing small releases of functionality for the ongoing User Stories we have been able to keep the team aware of design decisions without the necessity of always sitting next to eachother.

* **Simple Design**: Parts of the development has been done by using simple design. We tried to do the simplest solution to fix the task at hand, corner cases were then handled and refactoring had to take place. The practice was rather efficient as it enabled constant progress, instead of sitting stuck trying to figure out everything at once.

* **Refactoring**: Refactoring has been highly related to the Simple Design. The practice was really efficient, making the code more readable and functional. A lot of refactoring has been done in this initial sprint due to the lack of experience working with the Android framework and the complexity invoved on the initial phase of the project.

* **Sustainable Pace**: A lot of the work has been done at a sustainable pace. No real overtime or extreme peaks of work have been encountered throughout the sprint. This might partially be out of pure luck as we havn't planned that everyone should work X hours/day etc.

* **Point estimates**: All the user stories that were subject to enter the sprint received (function) point estimates during the Planning Poker session. The point estimates were to a quite large extent guesstimates, they were however not pointless. As the team gets into the habit of making such estimates, future estimates are likely to be more accurate.

* **Definition of Done (DoD)**: We came up with a somewhat standardized definition of what it means to be 'done'. This definition was added to every User Story. The DoD wasn't very efficient as we didn't reflect too much on it once we started with the work. It was also hard to live up the the DoD as some parts of the code is difficult to test. To make this practice more beneficial we would have to make the generic DoD more realistic, and then extend the definition (with e.g. tests etc.) where applicable.

* **Backlog**: User Stories were kept in two distinct backlogs, one for the product and one for the sprint. Whenever a new User Story is created it is added to the Product Backlog. Before the sprint is started the team selects the top prioritized user User Stories from the product backlog and put them into the Sprint Backlog. The Sprint Backlog is then closed, meaning that the team cannot commit to more work than what is in the Sprint Backlog.

## Sprint 2:
Sprint two started by going through the sprint goals set by the product owner. We also analyzed how we could integrate all feature request; which were feasible and which weren't. We moved on to populating the product backlog with all the user stories which we felt were missing. The user stories were prioritized and those that related to the sprint goal were estimated via planning poker. We realized that our velocity for the last sprint was rather low, and therefore commited to more work than we did for Sprint 1. Once the sprint backlog was set, everyone were able to pick their first user story of the sprint. No one were allowed to take more than one user story at a time. 

### Commitment
In the parenthesis the estimation point of each issue, followed by the issue number.
1. Loading wheel when fetching API information for FlightActivity #52 (2)
2. Manual retrieval of API information #42 (2)
3. Refactoring of the Flight Request Interface #44 (2)
4. Splash screen #45 (2)
5. Add shaded containers to main interface for future items #49 (2)
6. Wiki entry with code convention #50 (1)
7. Extend the information stored on Flight class #51 (3)
8. Create background service #12 (5)
9. Notify user at least 1 hour before the subscribed flight #25 (8)
10. Choose a flight to subcscribe to #4 (3)
11. Present flight subscription #19 (3)
12. Provide detailed information about the flight(s) #48 (5)
13. Present notifications #15 (5)
14. Add flight to subscription list #8 (5)
15. Remove flight from subscriptions list #10 (3)


### Work Done 
Feature | Commits  | Group members | Effort | Practices
----------------|----------------|----------------|----------------|----------------
Name and ID of each feature | Sha1 for the commit(s) that make up the feature | Names of the group members who developed the feature | The development effort expended, in story points or other unit of choice | The agile practices practiced when developing the feature
|#52|0996d6d|Elsa|2|Simple Design|
|#42|3348c13|Ayesha|2|Refactoring, Simple Design|
|#44|2638149, 865d854|Lois|2 (Real effort 3)|Simple Design, Refactoring|
|#45|04620e5|Ayesha|2 (Real Effort 3)|Simple Design, Refactoring|
|#49|940b7a9|Henrik|2|Refactoring|
|#50|N/A|Henrik|1|Coding standards|
|#51|87437e3|Lois|3|Refactoring|
|#12|6f2cd9a|Lois|5|Simple Design|
|#25|72a8075|Dimitris|8|Simple Design|
|#4|0d2356a|Jobaer, Lídia|3 (Real effort 5) |Simple Design, Refactoring, Pair Programming|
|#19|a106116,d4db884|Elsa|3|Refactoring|
|#48|84e3da7, e828628, 0b132c5|Lídia, Jobaer|5|Simple Design, Refactoring, Pair Programming|
|#15|2d3cf1d, 96f1b1d, e0a7d26|Henrik, Ayesha|5|Refactoring|
|#08|7bcdcb7, f0e43f7, 394a05e|Ayesha,Lois,...|5|Pair Programming|
|#10|7bcdcb7, f0e43f7, 394a05e|Ayesha,Lois,...|5|Pair Programming|

### Reflections
During the 2nd sprint we vastly increased our velocity and focused on getting a big chunk of functionality implemented. The team kept some of the agile practices, while excluding others. The XP which we hadn't tried out in sprint one, and therefore added to this sprint were: Collective code ownership and Coding standards. 

* **Collective code ownership**: When we had our code base from the first sprint we all felt acquainted with the foundation and could therefore branch out easily. We divided the work so that the risk of bottlenecks were severly decreased. Everyone could almost always work on something without having to wait for someone else to get done. If that occurred we used Pair Programming to speed up the work of the bottleneck, while giving work to the dependee at the same time. The person working on a task have been the chief architect for that specific part of the design. The practice could be further strenghtened by a larger test coverage.

* **Coding standards**: We came up with coding standards, primarilty based on the ones that Google have set up for Android contributors. Coding standards can be a very effective practice to ease readability and make the team members feel a larger collective ownership. The coding standards were however defined in the middle of the sprint, which was very stacked with work from the start. This made it difficult to integrate the coding standards for this sprint. This is however a practice that we could benefit greatly from for next sprint. 

* **Refactoring**: Naturally we refactored much of the code for this sprint. A lot of previous code were already outdated and needed to be polished, complete reworked or removed. 

* **Pair programming**: In the sprint 2, pair programming proved to be more effective and fruitful as compared to sprint 1. The reason might be that android beginner member in the pair did some tasks individually first to get familiar with android. Later, pair programming was complemented with switching places between the members. Additionally, it also reduced the time spent on the task to some extent.

* **Simple design**: During sprint 2, despite the tasks being characterized by increased complexity, we tried to implement the simplest yet most effective solutions by using well-proven concepts (e.g. CRUD database) and keeping coupling between components at a minimum.

During the last acceptance meeting, the Product Owner and the Customer requested several specific features to be implemented during the coming sprint. However, due to the extensive nature of the features there was no room for self-organizing and determining the velocity, since the user stories contained in the sprint backlog were practically already decided. We understand that emergent feature requests by the customer are something not unusual in the fast-pacing industry and practically necessary. However, they should be generally kept at a minimum as they invalidate many of the aspects of the adopted agile methodology.

## Sprint 3:
Sprint 3 started with a happy product owner who set the sprint goal to focus on making the application holistic. This meant that we had to think about what features were missing to make the application complete for a potential release. These were mainly revolving around settings and filters, but also on polishing the user interface to become consistent and more appealing to both customers and end-users. The sprint backlog was therefore filled with a wide variety of user stories, making it easier for the team members to work without bottlenecks.

### Commitment
In the parathesis the estimation point of each issue, followed by the issue number.
1. Refactor code, i.e. structure #83 (1)
2. Placeholders when there are no subscriptions or notifications #87 (1)
3. Present a settings view #20 (3)
4. Notify a user at least 1 hour before the subscribed flight #25 (8)
5. Fix flight list UI #90 (3)
6. Order list of flights #85 (3)
7. Add column to dB to check for delays or early flights #92 (8)
8. Present filtering options #14 (8)
9. Add tabs for arrivals and departures to the list of flights #47 (8)
10. Create a time-interval filter to the flight data #16 (5)
11. Make the UI more consistent #86 (3)
12. Create a filter based on airline #9 (5)
13. Create a filter for local or internation flights #7 (5)
14. Notification settings #88 (8)
15. Subscription settings #91 (8)
16. Apply settings on those parts of the code that use them #105 (N/A)
17. Make activity overview more readable to the user #115 (1)


### Work Done 
Feature | Commits  | Group members | Effort | Practices
----------------|----------------|----------------|----------------|----------------
Name and ID of each feature | Sha1 for the commit(s) that make up the feature | Names of the group members who developed the feature | The development effort expended, in story points or other unit of choice | The agile practices practiced when developing the feature
|#83|4305abf|Dimitris|1|Refactoring|
|#87|38da7ee, 493b0dd|Elsa|1|Simple Design|
|#20|9553347|Henrik|3|Simple design|
|#25|07b99f7, 6847714|Dimitris|5|Simple Design, Refactoring|
|#90|329787d|Elsa|3|Refactoring|
|#85|8884b34|Lois|3 (Real effort 1)|Simple Design|
|#92|a4903db|Lois|8|Simple Design, Refactoring, Test-First|
|#86|97c09dc, 1076f1e, c50867b, eca6c97, 2d7f6bb, 7bbda1b, 1076f1e|Henrik, Ayesha, Elsa, Lois|3|Refactoring, Coding standards, Collective code ownership|
|#14|fb8aaa5, eca6c97, 2d7f6bb|Ayesha|8|Refactoring, Coding Standard, Simple Design|
|#105|f0a0448|Henrik|N/A|Refactoring, Simple Design|
|#16|c43b1b3|Ayesha|5|Refactoring, Coding Standard, Simple Design|
|#9|c43b1b3,ce45bfc|Ayesha,Jobaer|5|Refactoring, Coding Standard, Simple Design|
|#88|4ac4608|Lois|8 (Real effort 3)|Simple Design, Refactoring|
|#91|4ac4608|Lois|8 (Real effort 3)|Simple Design, Refactoring|
|#115|3a29fab|Lídia|1|Simple Design|
|#117|fe0a207|Dimitris|3|Simple Design|
|#47||xxxx|Lídia|8|-|   

### Reflections
The practice of not letting people to work on more than one user story at a time were kept for this sprint, since it worked so well the last time around. As the third sprint were almost twice as long as the previous two sprints we increased our velocity. Going in to the third sprint we did not have any more (core) practices to try out. Instead we focused on keep working with the practices that had worked well for us in the previous sprints.

* **Simple design**: Simple design was used effectively to get a quick outline for the settings activity and it's respective view. Instead of trying to create something ideal, which would require hours of research on potential solutions, we created something that worked and made it possible to continue with the issues which are reliant on #20. The other option would be to make the issue into a bottleneck for future settings progress.

* **Refactoring**: Naturally refactoring have occurred as we have continued development of existing components. The refactoring have helped to make the code more streamlined and easier to understand. Additionally, the source code was organized in packages in order to facilitate its discoverability during development.

* **Coding standards**: For this sprint we tried to keep the coding standards in mind while coding, refactoring some of the old code to make it more inline with our standards when deemed fit. Doing this did however cost some extra development time, but we believe that we would make up for it if we would do more sprints in the future. Unfortunately this is our last sprint, and if none of the team members pick up the project for future development all the work of implementing the coding standards would've been futile.

* **Collectice code ownership**: For this sprint we have gotten into the habit of refactoring each others code. The team members all know that they are free to refactor it and does not hesitate to do so if they see room for improvements.

* **Test-First**: In this sprint the group has obtained more experience with the Android framework, and has been able to apply Test-First practices to some of the functionality of the app, discovering early mistakes in the app.


# Postmortem
**Introduction**

Our end product from 3 sprints is a fully functional application Aviato, that offers the user to search and filter for a flight and subscribe to it. If the user subscribes to a flight notification is sent to alarm the user of departure/arrival for that flight. 

During the development of the mobile application Aviato we have not only improved our skills for working with Android, but also learnt a lot about agile processes. The development have been done in iterations, specifically sprints, where we have tried out different practices from both Extreme Programming and Scrum. We believe that the agile practices have eased the development and we would certainly apply the practices in future projects.

**Reflection of used agile practices**
* **Planning poker**: 
Planning poker was used during the sprint planning of all the three sprints. The practice proved to be both positive and negative. Positive since it encouraged us to discuss the different user stories; if the user story was ambiguous we would discuss more specifically how the task would be carried out. We then re-estimated and chose the value of the majority, if two function points were equal we would go for the more conservative measure. The estimation itself were however futile as we did not make any use of it. Selecting the next task to work on were rarely affected with how much effort it was estimated to take. Filling the sprint backlog was not either affected by the function points (or the velocity of the last sprint), but rather by all functionality requested from the product owner.

* **Test-first**: 
The test first practice was poorly used, since most of the team members were inexperience with the Android framework. Implementing test for some functionality in Android required a previous expertise on the framework that the team lacked. A valuable amount of time was spent on researching the testing framework with mixed results. But some of the testing performed during the development helped tracking down inconsistencies in the design early. Overall the team agrees on the value of the test first to achieve software quality but also that it requires some previous knowledge in order to successfully implement this practice.

* **Pair programming**: 
This practice has proven to be particularly useful at the beginning of the project since some of the team members did not have any previous experience with the framework. It also had an impact on building better work relationships.


* **Continuous Integration**: 
By following this practice, we have used single source of repository and every group members worked in their local workspaces. We made sure to test each update and then commit the changes into the repository. This practice helped us to find bug easily and remove them before it creates any bigger problem. Our repository was continuously monitored by Travis, it tested the latest build automatically and notified us if any build fails. In general, it was a positive experience which enabled everyone to have the latest build. Although, the practice would have been more powerful if we had a larger test coverage as well as integration tests.


* **Onsite Customer**: 
In all of the sprints, the customer could interact freely with the developers and have the opportunity to try the project in the acceptance tests. In addition, the customer was also onsite during the workshops hours and could answer questions right away. As it turns out we did not communicate to the onsite customer much, one reason for that could be that our application had a very clear scope and often most of the questionable functionality was sorted out during our customer acceptance test.


* **Small Releases**:
We used small releases throughout the project, in our experience the biggest benefit of this practice is delivering value to the customer relatively fast. By handing over value iteratively, it enabled short progress feedback cycles. We made sure that in every sprint our release was small and contained the most valuable business requirements. 


* **Simple Design**: 
We have used this practice in most of the part of our development process. We only added those functionalities, which were required by the current sprint without thinking about what we might need in the future. Because of this practice we have avoided issues like overdesign. We found out that most design details were not needed for that specific sprint and if we tried to work them out upfront we might fall into a situation where refactoring could be difficult. Moreover, our code is prepared to anticipate any sort of change in the future. This agile practice also reduced the risks for long running bottlenecks.

* **Refactoring**: 
Kept the code in shape, making it cleaner. Helped with collective code ownership, making everyone able to understand the code and feeling that they were allowed to make changes wherever necessary. Additionally, this technique increased the discoverability of the different components, dependencies, logical links and eventually features of the product, on a code-level. This in turn decreased the necessary to identify the block(s) of code that was needed to be worked on during development and allowed the team to be more cross-functional and attain a universal overview of the code, by enabling all team members to promptly identify which parts of the system needed to be changed in order for a goal to be reached.
Moreover, refactoring enhanced the maintainability of our code and product, by making it easier for the team members to determine which are the most suitable candidates (i.e. source files, components etc) for the fault to exist in and fix it.  This, regardless of whether they had personally worked on that part of the system.

* **Sustainable Pace**: 
This method helped us keep the project and its greater picture in mind. Particularly, the size of the commits was consistent without too many large chunks of code at every push. This facilitated the resolution of conflicts, whenever they emerged, during integration. By having a sustainable pace, the team was able to keep the product at high quality standards and introducing a relatively low amount of bugs and defects, even throughout the feature growth sprint.
Furthermore, during the sprint planning meetings, the team's agreed velocity (i.e. the amount of complexity to be developed as well as the business value that would be generated) was carefully considered when collectively deciding on the sprint's scope. At certain occasions, the team had to ramp up the effort in order to complete the features that it had committed, but this happened rather exceptionally and was not the norm.


* **Collectice code ownership**:
This tactic helped us maintain a sustainable pace and remove unnecessary “blockers” due to parts of the system being comprehensible by only a subset of the team. This in turn made team members more committed and focused to do their part, as they felt that the system at its entirety was apt to modifications, without feeling that they lack the knowledge to do so. Additionally, it allowed stress and pressure on individuals to remain at manageable levels, as the workload could be easily distributed among the team members.
By not having specific tasks or features tightly coupled with specific developers, the option to engage different parts of the system and the capability to work on a diverse set of stories, gave the members additional motivation and decreased the time which would otherwise be spent on attempting to cope with a steep learning curve.


* **Coding standards**:
For the initial sprint we did not consider coding standards at all, the focus was to quickly get a codebase. Once we entered the second sprint we realized that it would be useful to implement some coding standards and therefore assigned a user story for it. The outcome was a bit too extensive to implement throughout the code, but as we refactored our code we tried to implement more and more of the standards. The main reason why we didn't dogmatically follow the coding standards was that it was too much of an effort for the value it would provide for us as developers, we rather kept focus on developing value to our customer. We were therefore satisfied once each class was consistent with itself. The coding standards that we ended up with helped a lot with increasing the collective code ownership as it became less traceable who had done what and it therefore really felt like the code was developed by the team, rather than a specific individual. The code also became easier to read, and thus understand.


* **Point estimates**: 
We used Fibonacci numbers from 1 to 13 to assign points to each task based on the effort required for it. However, it was not much helpful since we overestimated and underestimated some tasks and estimated points for it didn’t really reflect the effort and time invested in it. It was the reason that no one really considered those points while picking a new task from backlog or while calculating total estimation effort for a sprint.
We also think that a more uniform scale, for example, from 1 - 10 would have been more accurate in estimation as compared to fibonacci scale. It is because some tasks were somewhere 5 and 8 but we had to choose either of these because we did not have any choice.  

* **Definition of Done (DoD)**: 
We added DoD for all the tasks in the beginning of sprint 1. Scope of each task wasn’t clear back then. Because of this, DoDs were outdated by the end of sprint 1 because many task had evolved during the sprint. Also in the sprint 1, our tasks were very generic covering many sub-tasks. Therefore, DoDs for these task were also very strictly defined and limited. In sprint 2, we broke down the task in several smaller well-defined tasks, thus, leaving the DoDs useless and they were not taken into consideration while pushing a final version of user story.
We think that DoDs could have been used more efficiently if they were added at the beginning of each sprint, because scope of each task was well-established at the beginning of each respective sprint in which it was implemented.


* **Backlog**: 
By maintaining an online backlog in our GitHub repository, we kept track of our progress while taking into consideration future tasks in mind. Furthermore, the Backlog was helpful during customer meetings as the customer was able to see what had already been achieved, what they thought was lacking as well as what we had in mind for the next iterations of the application. The backlog not only did it help us discover duplicate tasks and administer our workload in a systematic manner, but also made it possible to track which features were currently being developed and by whom, so probable discussions could be initiated with the responsible (e.g. in the case of interdependent tasks) when it was deemed necessary. Finally, the Backlog was beneficial to the "softer" aspects of the development process. In particular, its visualization of the current status and the amount of completed work, a figure that increased day by day, raised the motivation and the morale of the team, by indicating that the final goal was increasingly within reach.

**Most impactful practices**
* **Simple design**:
With simple design being used overall in the whole development it enabled us to progress at a high pace and being able to change the system quickly. Because of the simplicity design being used throughout it could cause problem in the future for scalability of the application.

* **Refactoring**:
we had one large refactoring session which slowed down the development a bit, but once it was completed we managed to increase our productivity because the code were more consistent and clearly divided. Generally, we realized that refactoring was very beneficial yet mostly feasible during short periods of time which are characterized by a decreased rate of feature growth. This helps to avoid merge conflicts when work that was started before refactoring needs to be committed.

* **Planning Poker**:
Even though planning poker was a bit of a waste of time since we did not use the points, it provided an arena for important discussions. If we would not have used this practice it is likely that the development would have been slowed down since people would be uncertain about what, and how, to do certain user stories.

* **Test First**:
The test-first practice impacted the development process in a negative way. When we tried to apply this practice the development pace often stalled resulting in frustration within the team. This overhead was often perceived by the team as not giving much customer value, mainly due to the inexperience of the team with the framework, as noted before.   

* **Collective code ownership**:
Everyone felt comfortable modifying each other’s code which allowed for an easier distribution of tasks and prompt issue of hotfixes when a bug was detected. Team members had both the liberty and the knowledge to develop different parts of the system which had a positive impact on the development speed and the eventual minimize of technical debt.

* **Backlog**:
Backlog provided an important overview of the project for all the stakeholders of the project. It was the point of reference during sprint planning for the development team and for each member individually when deciding to work on a feature. Additionally, it provided the guidelines for the customer and the product owner to get a grasp on the amount and nature of work conducted during the sprint, as well as of what was to follow in the next one. The fact that the Backlog was hosted on GitHub, allowed great integration of the user stories with the development activities (e.g. correlating commits with features, closing bugs via commit messages etc).

**What would we do differently in a future project?**
* For this project, we didn’t use the on-site customer much. For the future practices, we would use a customer-proxy within a group. Customer-proxy would communicate with the customer on regular basis and all the group members will refer to the proxy for any input about the tasks.

* The team has agreed on increasing the test coverage amount in future projects, since some of the testing performed has proven to be a driver for software quality. Coverage for future projects should be increased or at least aim to cover 70% of the code. Striving for a good balance on the time spent on testing and the overall time spent on the core functionality.

* We would use different point scale for the story point estimation, rather than using the fibonacci sequence we could use a linear one so the team members could hopefully have a better “gut feeling” about the estimation.

* We would add the definition of done for each task, to be implemented for a sprint, in the beginning of that sprint because scope of task is clear at the that point rather than adding DoDs before sprint 1 and freezing them for the rest of the sprints. Tasks evolve as the project progresses and their DoDs are volatile.

* For the first sprint we should have made the user stories less generic and smaller, this would have helped us estimate the real effort necessary to implement each functionality. For future similar projects, we shall validate if the functionality is really necessary before adding it, as well add business value and benefit for customer to the user stories.

* We would create some upfront design, with both technical and user interfaces. Having an upfront design would have helped us to save time and allowed us to focus on the technical aspects. 

* We would create a Git workflow feature oriented, instead of each member having their own branch. This would have made our commit history more clear, and helped us to avoid rebase conflicts. 
