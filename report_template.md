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

# Postmortem
Once the project is finished, summarize your experiences.

- For each practice, reflect on the combined experience from all sprints.

- Which practices had the most impact on the software developed?
  Think of both positives and negatives.

- What would you do differently in a future but similar project?

- Outcome, screenshots or similar.

## Administrative details

- Send in the sprint reports and postmortem report via mail
  to Terese and Magnus. Deadlines are in the course schedule.

- The postmortem part shall be 2000-3000 words long.

