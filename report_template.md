---
title: 'Agile Development Processes Project Report'
author:
- Dimitrios Platis
- Lídia Nyman
- Elsa Mjöll Bergsteinsdóttir
- Lois Alberte Gómez Sánchez
- Ayesha Aslam
- Jobaer Ahmed
- Henrik Helén Edholm
---

# Project Description
An application that allows users to subscribe to specific flights, providing flight information (e.g. delays, cancellation, early arrival) via push notifications.

The app will show all the flights from, and to, Gothenburg Landvetter Airport (GOT). Later on the user will be able to choose among other airports in Sweden and then we can might expand to the rest of the world depending on public interest. The user can filter the departures/arrivals and subscribe to some flight of concern. The user can filter to many flights. The user can choose (in settings) or in each flight what kind of notification he/she wants to receive about that flight.

# Sprint Log
## Sprint 1:
We started the sprint by populating the backlog of stories written as issues in git. Next we had to estimate the issues by giving them story points that would indicate their complexity, in our wiki the explanation of how we map our story point metric to complexity for the estimation of each issue can be read. We used a a well known estimation technque to estimate our backlog the Planning Poker. For the Planning Poker we used a online servie Plan IT Poker (http://www.planitpoker.com/) so every member of the group could remotely vote. After the results of the voting we reasoned together to come to a estimation conclustion that everybody agreed on. When every issue had an estimation, we had to plan how many issues we were going to commit to for the first acceptance test.  Based on the time capacity each member should contribute per week and the issues that had the most priority we decided to commit to 6 issues in Sprint 1. 

## Commitment
List the features/stories the team commits to finish during the sprint.
### Sprint 1 Commitment:
In the parathesis the estimation point of each issue, followed by the issue number.
1. Create a hello world application (3) #5
2. Travis CI setup (5) #26
3. Investigate appropriate API for flight retrieval (3) #3
4. Retrieve flight information from public API (5) #22
5. Parse flight information (programmatic representation of flight data) (5) #18
6. Present flight data (5) #13


## Work Done
Log what was accomplished, and how.

Feature | Commits  | Group members | Effort | Practices
----------------|----------------|----------------|----------------|----------------
Name and ID of each feature | Sha1 for the commit(s) that make up the feature | Names of the group members who developed the feature | The development effort expended, in story points or other unit of choice | The agile practices practiced when developing the feature
|#5|xx|xx|xx|xx|
|#26| 7de24dc, 917ab15 |Dimitris|5|Continuous Integration|
|#3|xx|xx|xx|xx|
|#22|xx|xx|xx|xx|
|#18| dce7b29, 72ec745 |Dimitris|5|Test-first, Refactoring|
|#13| fb27b88, 64bb0e4, fe56364, 7ab13e2, |Henrik, Elsa Mjöll|5|Refactoring, Small Releases, Simple Design, Sustainable Pace|

## Reflections
Reflect on how the work worked.

Discuss any deviations from the sprint commitment.

For each practice practiced:

- Did your experience correspond to or contradict with what literature claims?

    - Analysis of why. Mostly interesting if something unexpected happens, but even
      if everything runs according to plan, reflecting on the underlying mechanisms
      can be interesting.

- How efficient was the practice, given the time it took to use?

### Sprint 1:
During the 1st sprint we setted both the procedural and technical foundations of the product. The team got acquainted with various XP practices (e.g. pair programming, planning poker etc) and was able to complete the planned user stories, indicating that the chosen sprint velocity is appropriate and allows for a sustainable development pace.

* **Planning poker**: Planning poker proved interestingly troublesome, as to its effectiveness and efficiency, since often large deviations between the team member estimations emerged that remained unsettled. This is however natural, as it was the first session of the kind that the team was engaged in and it is expected for the process to become more "normalized" and faster as the team members get better acquainted to each other, the agile methodology but most importantly the product under development and its challenges.

* **Test-first**: Unit tests were written for a part of the system, namely the _Flight_ class. By writing the test cases first, based on the various use cases that were decided, we were able to formulate the class' API before the implementation. This allowed us to maintain a cleaner interface and avoid small bugs which could result from omitting to consider the various edge cases.

* **Pair programming**:

* **Continuous Integration**:

* **Onsite Customer**: Having an onsite customer has felt reassuring. If we have any uncertanties of what the customer wants we have been able to get almost immediate response. Considering that it hasn't cost us any extra time to use this practice it should be considered to be rather efficient.

* **Small Releases**: Producing small (internal) releases have proven very beneficial for us. A lot of our initial development have had a lot of dependencies. By producing small releases of functionality for the ongoing User Stories we have been able to keep the team aware of design decisions without the necessity of always sitting next to eachother.

* **Simple Design**: Parts of the development has been done by using simple design. We tried to do the simplest solution to fix the task at hand, corner cases were then handled and refactoring had to take place. The practice was rather efficient as it enabled constant progress, instead of sitting stuck trying to figure out everything at once.

* **Refactoring**: Refactoring has been highly related to the Simple Design. The practice was really efficient, making the code more readable and functional.

* **Sustainable Pace**: A lot of the work has been done at a sustainable pace. No real overtime or extreme peaks of work have been encountered throughout the sprint. This might partially be out of pure luck as we havn't planned that everyone should work X hours/day etc.

* **Point estimates**: All the user stories that were subject to enter the sprint received (function) point estimates during the Planning Poker session. The point estimates were to a quite large extent guesstimates, they were however not pointless. As the team gets into the habit of making such estimates, future estimates are likely to be more accurate.

* **Definition of Done (DoD)**: We came up with a somewhat standardized definition of what it means to be 'done'. This definition was added to every User Story. The DoD wasn't very efficient as we didn't reflect too much on it once we started with the work. It was also hard to live up the the DoD as some parts of the code is difficult to test. To make this practice more beneficial we would have to make the generic DoD more realistic, and then extend the definition (with e.g. tests etc.) where applicable.

* **Backlog**: User Stories were kept in two distinct backlogs, one for the product and one for the sprint. Whenever a new User Story is created it is added to the Product Backlog. Before the sprint is started the team selects the top prioritized user User Stories from the product backlog and put them into the Sprint Backlog. The Sprint Backlog is then closed, meaning that the team cannot commit to more work than what is in the Sprint Backlog.

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

