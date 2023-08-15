# reneonate Design Document

## Pittman Park Pickup Management Software Design

## 1. Problem Statement

I take part in local pickup soccer games. I've found that local pickup soccer players have a difficult time
efficiently planning and managing pickup soccer games because it's unknown how many players will be attending until the
time of the game (usually set at sunrise) once players show up. If game admins had a better understanding of how many
players intend to show up for the game, they could properly prepare and plan game resources, split up teams into equal
numbered teams, create more than one game if necessary, or, if not enough players will be in attendance, let everyone know
the game is cancelled.

## 2. Top Questions to Resolve in Review

1.  Most effective tech stack to use (read about benefits of Svelt for FE vs. React, advised to consider deploying via
AppRunner on AWS, etc.)
2. Do the classes of Users, Games, and RSVPs make sense? I'm I missing any more classes? Should location be a separate class?
3. For users, we have players and admins, how do I efficiently distinguish between these two classes? Would it just be a
member field in the user class (e.g. boolean isAdmin - true or false) and then display the frontend components accordingly?
4. How do we handle user permissions correctly?
5. There are a few different options when it comes to handling dates in Java, what would be the appropriate things to use?
LocalDate, LocalTime, LocalDateTime?
6. Proper way to handle RSVPs? For example, should I just have a field in the RSVP, like, isAttending, and when an player
RSVPs, it's updated to true. Then, if they end up cancelling, update the field to false?
7. For the location piece, should I look to use something like Google Maps in order to facilitate the location pieces or
would it be easier to just let admins enter info manually? For the immediate application, there would only be a few
initial locations since it's a group that plays on a limited number of fields. I could even hardcode the locations to
begin with. However, having an eye towards other groups being able to use it, should I make it a little more robust from
the beginning?
8. Not sure if the RSVP endpoint should live on its own (e.g. /rsvp/:rsvpId) or if it should live as an endpoint on a specific game
(e.g. /games/:gameId/rsvps/:rsvpId)

## 3. Use Cases

U1. As a player, I want to see an upcoming games list when I first log into the service.

U2. As a player, I want to be able to RSVP for a specific game by clicking a button.
    
U3. As a player, I want to be able to RSVP both from the initial games list or from a game detail page.

U4. As a player, I want to see the location and time details of a given game when I click on the game in the games list.

U5. As a player, I want to see how many players have RSVP'd for a given game.

U6. As a player, I want to be able to sign up for the service using an existing service I already have, like Google or
Facebook.

U7. As a player, I want to be able to cancel my RSVP by clicking a button in the upcoming games list or the game detail
page.

U8. As an admin, I want to be able to create a game by visiting an admin specific page that has a button to create a
new game.

U9. As an admin, I want to be able to limit how many players can sign up for a game when I create a game.

U10. As an admin, I want to be able to update the details of a game (such as location, time, max number of players, etc.)
or even cancel a game by visiting the game detail page or from the admin page.

U11. As an admin, I want to be able to vet new signups to the platform by going to a "New Signups" section in the
admin page.

U12. As an admin, I want to be able to make a registered player an admin via the admin page.

U13. As an admin, I want to be able to mark someone as having attended a game they RSVP'd for or not via the game
detail page.

U14. As an admin, I want the system to automatically bar a player from RSVP'ing for a game if they were not in attendance
for a previous game they RSVP'd for.

U15. As an admin, I want the system to automatically create equitable teams from all the players that have RSVP'd based
on a rating system.

U16. As a player, I want the system to ask other players to upvote or downvote my player rating based on their play with
me during a pickup game.

U17. As a player, I don't want other players to be able to see my rating and I don't want to see my rating either, I
just want the system to use the rating to create equitable teams.

U18. As a player, I'd like to get an email or text reminder the day before a game I've RSVP'd for and have a way to
confirm or change my RSVP based on that notification.

U19. As an admin, I want to be able to send a notification to players that have RSVP'd for a game to provide important
updates (e.g. cancelled because of weather, lack of players, etc.)

U20. As a player, I want to be able to see the projected weather for a given game in the game details page.

U21. As a player, if a game is fully RSVP'd, I'd like to be added to a waitlist and updated to RSVP'd for the game if
someone cancels their RSVP.

U22. As an admin, I want to make sure players have confirmed their RSVP at least 24 hours prior to the game and, if
they don't, then assume they're not attending and promote waitlisted players automatically

U23. As an admin, I want to use something easy to use, like Google Maps, to help me create a new location for a game


## 4. Project Scope

### 4.1. In Scope

There are X major problems that need to be solved for local pickup soccer games.

First, during the summer months, when
demand is high, it's unclear how many players will be attending and, because of that, it's not clear at what point the
initial game should be closed to new players and a second game should be started. Because players show up a slightly
different times, it's possible the admins cut off a game early, but then not enough players to get a second game started
will end up showing up. So, what happens currently is that one huge game is played so everyone can play, but the size of
the game makes it difficult, even unenjoyable, to play. However, it's difficult to break the game up one it's started
because of player's desire to stay put.

Second, during the winter months when it's cold out, players don't really know if a game will materialize until they
actually show up to the field. If not enough players show up, it could be a waste of a trip out to the field.

Both these major problems can be solved with a basic RSVP system where admins create games and players can go in and RSVP
for games so admins now, before hand, how many players to expect and can take action accordingly. In the example of the
first problem, admins can know whether two games would be appropriate from the beginning and set up two fields and make
sure people split up from the beginning. In the example of the second problem, admins and players can see if enough
players will show up and can make the determination to continue with the game or go ahead and cancel it.

With this context, the most important use cases to complete for this MVP are pieces that revolve around creating and
updating games, allowing players to RSVP and update their RSVPs, and allowing players to add themselves to the waitlist.

These use cases would be: 1, 2, 3, 4, 5, 6(?), 7, 8, 10, 12, 18(?), 19(?)

### 4.2. Out of Scope

At the moment, I'm not going to worry about the rating system or the automated notifications aspect of the system. The
automated notification and confirmation system is something that would be on the roadmap and the rating system would be
considered a potential feature further down the road. If it's possible to create the core pieces quickly and I feel I
have the capacity to do more, I'd like to focus on the automated notifications/confirmations.

# 5. Proposed Architecture Overview

*Describe broadly how you are proposing to solve for the requirements you
described in Section 3.*

*This may include class diagram(s) showing what components you are planning to
build.*

*You should argue why this architecture (organization of components) is
reasonable. That is, why it represents a good data flow and a good separation of
concerns. Where applicable, argue why this architecture satisfies the stated
requirements.*

I'm not sure if I should outline the deployment architecture or the backend design, so I'll cover my thoughts for both.

With regards to tech stack/deployment architecture, I'm leaning towards Svelt for the FE and Spring Boot for the BE.
However, I'm still considering API Gateway/Lambda for the API. Would need help with the pros/cons of this. I'd like to
leverage infrastructure as code via CloudFormation or Terraform, if it doesn't prove too burdensome. DynamoDB for
the DB. It would be great if I could set up a basic CI/CD pipeline in order to easily deploy things as I make changes.
However, I want to move fast, so if that proves to be too big of a hurdle to learn/overcome quickly, I can leave that piece.

# 6. API

## 6.1. Public Models

Models would include:

```
// UserModel

String userId
String firstName
String lastName
boolean isAdmin
```

```
// GameModel

String gameId
Location location
DateTime date
DateTime time
List<RSVP> rsvps
```

```
// RSVPModel

String rsvpId
User player
Game game
boolean isAttending
```

```
// LocationModel

String locationId
String name
String address
String city
String state
String zip
```

## 6.2. Get All Games Endpoint

* Accept `GET` requests at the `/games` endpoint
* Returns a list of all the scheduled games (limited to the next two weeks, by default?)

## 6.3. Create Game Endpoint

* Accept `POST` requests at the `/games` endpoint
* Needs to have a body of the request with a locationId, a date, and a time
  * If an invalid location or an invalid date or time is submitted, returns an `InvalidGameCriteriaException`
* Returns a newly created gameId along with the current attributes of the game

## 6.4 Get Single Game Endpoint

* Accept `GET` request at the `/games/:gameId` endpoint
* Accepts a gameId and returns the corresponding game
  * If the gameId is not found, returns a `GameNotFoundException`

## 6.5 Edit Game Endpoint

* Accept `PUT` request at the `/games/:gameId` endpoint
* Needs to have a body with an attribute to update: locationID || date || time
  * If an invalid attribute is presented in the body, returns an `InvalidGameCriteriaException`
  * If it's an attempt to edit a game that's already happened, returns a `GameAlreadyHappenedException`

## 6.6 Create Location Endpoint

* Accepts a `POST` request at the `/locations` endpoint
* Needs to have a body with valid attributes for the location: name, street address, city, state, zip
  * If invalid or missing attribute is sent, returns an `InvalidLocationInputException`

## 6.7 Create RSVP

* Accepts a `POST` request at the `/games/:gameId/rsvps`
* Expects a body with a userId and whether the player is attending
  * If any of these attributes are missing or if they're invalid, returns an `InvalidRsvpInputException`
  * If the game has already happened, returns a `GameAlreadyHappenedException`

## 6.8 Get all RSVPs

* Accepts a `GET` request at the `/games/:gameId/rsvps` endpoint
* Returns a list of RSVPs for a specific game

## 6.9 Get specific RSVP

* Accepts a `GET` request at `/games/:gameId/rsvps/:rsvpId`
* Returns a specific RSVP
  * If an invalid rsvpId is sent, returns an `InvalidRsvpIdException`

## 6.8 Edit specific RSVP

* Accepts a `PUT` request at `/games/:gameId/rsvps/:rsvpId`
* Needs to have an updated isAttending attribute in the body
  * If there is an attempt to edit an RSVP for a game that's already happened, returns a `GameAlreadyHappenedException`

## 6.9 Create User

* Accepts a `POST` request at `/players`
* Expects a body with firstName, lastName, and email
  * If missing any of the above info, returns an `InvalidUserInputException`
* Returns a new user with a userId along with the passed in attributes

## 6.10 Edit User

* Accepts a `PUT` request at the `/players/:playerId`
* Expects a body with an updated firstName, lastName, and/or email
  * If none of the above attributes are found in the body, returns an `InvalidUpdateUserRequestException`
* Returns an updated user with all attributes


# 7. Tables

### 7.1. `players`

```
playerId // partition key, string
firstName // string
lastName // string
email // string
isAdmin // boolean
```

### 7.2 `locations`
```
locationId // partition key, string
name // string
streetAddress // string
city // string
state // string
zipcode // string
```

### 7.3 `games`
```
gameId // partition key, string
location // string
date // string (sort key?)
time // string
```

### 7.4 `rsvps`
```
rsvpId // partition key, string
game // sort key, string
player // string
isAttending // boolean
```

# 8. Pages

*Include mock-ups of the web pages you expect to build. These can be as
sophisticated as mockups/wireframes using drawing software, or as simple as
hand-drawn pictures that represent the key customer-facing components of the
pages. It should be clear what the interactions will be on the page, especially
where customers enter and submit data. You may want to accompany the mockups
with some description of behaviors of the page (e.g. “When customer submits the
submit-dog-photo button, the customer is sent to the doggie detail page”)*
