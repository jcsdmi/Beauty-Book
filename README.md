# Beauty Book

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
A beauty centered social media that let's users share and view step by step guides on all things beauty.

### App Evaluation
- **Category:** Social Media/Beauty
- **Mobile:** This app would be developed for mobile devices so that users could view step-by-step guides whenever, and wherever they want. It would use the phone's camera, and potentially push notifications.
- **Story:** Provides the user with step-by-step guides on how to do different hairstyles, makeup, and nail designs. The user can search guides for what they want to learn, or post their own guides to teach others.
- **Market:** The primary market of this app would be girls and women.
- **Habit:** The more beauty related things a user wants to learn, the more they would use the app. If the user isn't searching for how to do something, they could give back to the community by adding more guides.
- **Scope:** Firstly, the focus would be on learning and teaching different beauty techniques. Potentially beauty (makeover, dressup, nail art, etc.) games could be added to make the app more engaging.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can login
* User can create an account
  * User will select their interests after registering (hair, makeup, nails, etc.)
* User can post tutorials
* User can view tutorials
* User can delete their tutorials
* User can search tutorials
* User can search for other users
* User can like posts

**Optional Nice-to-have Stories**

* User can see trending guides
* User can get notifications when their tutorial is liked
* User can see a list of their followers
* User can see a list of their following
* User can view other user's profile
* User can comment on guides

### 2. Screen Archetypes

* Login
* Register - User signs up or logs into their account
   * Upon Download/Reopening of the application, the user is prompted to log in to gain access to their profile information and uploaded videos. 
* Vision Board - Users can view other hairstyles that appear on the page to be liked and can view the hairstyles they upload themselves.
* Profile Screen - Users can view their "Artist" profile and view the videos they uploaded and a tab to view the posts they liked.
* Search - Users can search by tags or keywords to find a list of similar styles. The same can also be done with user names.
* Settings - Lets people change language, and app notification settings.


### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Login
* Profile
* Activity Feed
* Guides
* Explore
* Camera
* Settings


**Flow Navigation** (Screen to Screen)

* Forced Log In -> Account creation if no log in is available
  
* Activity Feed -> View recent tutorials you and others have done 
                   -> Guides
                   -> Profile
   
* Explore -> Search for guides or users 
                   -> Guides
                   -> Profile
 
* Profile -> View and edit profile
                   -> Camera
  
* Guides -> View user's saved step-by-step instructions and pictures, or create a new guide 
   
* Settings-> Toggle settings
   
* Camera-> Take pictures to add to guides or profile

## Wireframes
<img src="https://i.imgur.com/XXM98eX.jpg" width=400> <img src="https://i.imgur.com/3yfynCK.jpg" width=400>

### [BONUS] Digital Wireframes & Mockups
<img src="https://i.imgur.com/GOpb0Hz.png" width=600>

## Schema 
### Models
#### Profiles

   | Property      		  | Type                | Description |
   | ---------------------| --------------------| ----------------------------------------------------------|
   | profileId     		  | String              | unique id for the user profile 						  	|
   | username     		  | String              | string that is the username    						  	|
   | password     		  | String              | string that is the password    						  	|   
   | profileImage         | File                | image for the user profile     						  	|
   | profileDescription   | String              | Bio/text details about te user 						  	|
   | profileFollower      | Array 	| array of pointers to people who are following the user 	|
   | following     		  | Array   | array of pointers to people who are followed by the user  |

#### Guide

   | Property           | Type     		   | Description |
   | -------------------| -----------------| -----------------------------------------------------------------|
   | guideId            | String   		   | unique id for the user post (default field) 	  				  |
   | guideTitle         | String 		   | Title For guide(default field)  			 					  |
   | guideDescription   | String 		   | Description for guide(default field) 						 	  |
   | guideImage         | File     		   | image that user posts 						  					  |
   | guideCreator       | Pointer to Profile | Pointer to the Profile of Creator of Guide				      |
   | guideLearners      | Array            | Array of Pointers to the Users who selected guide to learn from  |
   | guideLikes         | Array   		   | Array of the Likes objects					   					  |
   | instruction        | Array  		   | Array of the Instruction objects    			  				  |
   | tags		        | Array  		   | Array of the strings. Strings can be searched for.				  |
   | createdAt          | DateTime 		   | date when post is created (default field) 	  					  |
   | updatedAt     		| DateTime 		   | date when post is last updated (default field) 				  |

#### Instruction

   | Property      		  | Type     		 | Description 										 |
   | ---------------------| -----------------| --------------------------------------------------|
   | instructId    		  | String   		 | unique id for the instructor post (default field) |
   | guide         		  | Pointer to Guide | pointer to the guide 							 |
   | instuctImage         | File     		 | image for instrcutions 							 |
   | instructDescription  | String   		 | text instructions 								 |


#### Likes

   | Property      | Type     		 | Description 									|
   | ------------- | ----------------| ---------------------------------------------|
   | likeId    	   | String   		 | unique id for the like (default field) 		|
   | isLiked       | Boolean		 | Like value. True = Like, False = Disliked	|
   | liker         | Pointer to Profile | Pointer to the user who liked this			|

### Networking
- Home Screen
  - (Read/GET) Query all posts where the user can view object
  - (Create/POST) Create a new like on a post
  - (Delete) Delete existing like
  - (Create/POST) Create a new comment on a post
  - (Delete) Delete existing comment
- Profile
  - (Read/GET) Query logged in user object
  - (Update/PUT) Update user profile image
- Guides
  - (Read/GET) Query all available topics to narrow search
- Explore
  - (Read/GET) Query for users to search users or individual hairstyles
- Settings
  - 
