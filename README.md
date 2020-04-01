Group Project - README Template
===

# Savvy

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Savvy is an app for students that allows any students from any major and any company to swipe left or right to be matched with one another for potential job opportunities. From the students standpoint, swipe left to discard companies of uninterested. Then, you can swipe right for a potential match and get connected with a recruiter.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:**
    -Job Search
    -Workforce
    -College Students
- **Mobile:**
    -Very mobile
    -Fast / Easy Use on Cell Phone
    -Accessible from anywhere anytime
    -Real Time & Video Calling
- **Story:**
    -We are connecting students and companies together in a fun way. Job searching can be tiring for a college student. Let's
     cut the intense research and make it easier for students to get started and connected with companies.
    -Interest levels are high from other students
- **Market:**
    - College Students
        -20 million (Average total of American College Students)
        -20 thousand average at a University(May scale from school to school in the beginning)
        -Niche group are students who really want to get internships and full time positions
    - All Companies in Industry
        - Specifically focusing on companies who have internship positions
    -Audience are both College Students and Companies 
- **Habit:**
    - Very Habit Forming for user, they get exposed to new companies and learn about new opportunities.
    - Average user would just open this anytime for job search
    - The app gurantess something new everytime the user opens it
    - Companies can post stories about different day in the life or tips for students
    - The companies are creators, while the students are consumers
- **Scope:**
    - The technical details right now are feasible, since we're focus on core functionalities. 
    - We are doing a stripped down version in order to better potrary the vision and idea
    - Very Valuable as stripped down version
    - There's clear story dialogue of how we plan to progress the development of the app

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**


* Allow users to login
* Uploading Resume, Transcripts and Brief Description
* Allow Users to Add Skills
* Allow Companies to filter students by skill
* Allow companies and students to swip across different candidates
* Matching Companies and Students
* Chatting with Recruiters
* Video Call Recruiter(Twillio API)

**Optional Nice-to-have Stories**

* [fill in your required user stories here]
* Allow companies to post Real Time 24 hour based stories
* Allow students to post gif demos of side projects/presentations


### 2. Screen Archetypes

* Login
   * Allow users to login
* Sign Up
   * Uploading Resume, Transcripts and Brief Description
   * Allow Users to Add Skills
* Edit Profile Screen
  * Allow Users to Add Skills
  * Uploading Resume, Transcripts and Brief Description
  * 
* Explore Screen
   * Allow Companies to filter students by skill
   * Allow companies and students to swip across different candidates
   * 
* Matches Screen
   * Matching Companies and Students
*Chatting Screen
    * Chatting with Recruiters
    * Video Call Recruiter(Twillio API)

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Explore 
* Matching
* Chatting Screen

**Flow Navigation** (Screen to Screen)

* Login
   * Stream
* Sign Up
   * Stream
* Explore
    * Modal view of student/companies profile  
* Matches
   * Chatting Screen
     *Video Call

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="https://github.com/SavvyLLC/Savvy-Submission1/blob/master/Wire_Frame_Screenshot.png" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]

<img src="https://i.imgur.com/BMwYWkm.png" width=600>

<img src="https://imgur.com/1e6dVZc.png" width=600>

<img src="https://imgur.com/OQnrC5Q.png" width=600>

<img src="https://imgur.com/3L6xYpU.png" width=600>

<img src="https://imgur.com/seXTrSV.png" width=600>

<img src="https://imgur.com/CEQkdOK.png" width=600>

<img src="https://imgur.com/6Lek5vs.png" width=600>

```
. Screen Archetypes
Login
(Update/PUT) Allow users to login
Sign Up
(Create/POST)Uploading Resume, Transcripts and Brief Description
(Create/POST)Allow Users to Add Skills
(Delete/DELETE)Allow user to remove skills
(Delete/DELETE)Allow user to remove resume, transcript and description
Edit Profile Screen
(Update/PUT)Allow Users to Add Skills
(Update/PUT)Uploading Resume, Transcripts and Brief Description
(Delete/DELETE)Allow user to remove skills
(Delete/DELETE)Allow user to remove resume, transcript and description
Explore Screen
(Read/GET)Filtering through students/companies
(Read/GET)Allow Companies to filter students by skill
(Create/POST)Allow companies and students to like other candidates


Matches/Top Picks
(Read/GET)View Matches
(Read/GET)View Potential Top PIcks
(Delete/DELETE)Unmatch a pick

Messages
(Read/GET) Matches for Companies and Students *Chatting Screen
(Create/POST) Create message to other user
Parse Query Code Snippet:
Login
PFQuery userObject = PFQuery(className:"Put")
userObject.attemptSignIn { (posts: [PFObject]?, error: Error?) in
   if(error) {
      Log.e(TAG, "ERROR: Could not sign in user ");
   } else {
      Log.e(TAG, "Sign in was successful! ");
  }
}
Upload documents

PFQuery[] userDocuments = PFQuery(className:"Post");
userDocuments.attemptUpload { (posts: [PFObject]?, error: Error?) in
   if(error) {
      Log.e(TAG, "ERROR: Could not upload users documents ");
   } else if let posts = posts {
      Log.e(TAG, "Sign In with documents! ");
  }
}

Edit Profile
PFQuery editProfileAddUpdate = PFQuery(className:"Put");
editProfile.updateUserProfile { (posts: [PFObject]?, error: Error?) in
   if(error) {
      Log.e(TAG, "ERROR: Could not upload users documents ");
   } else if let posts = posts {
      Log.e(TAG, "Updating user profile is successful! ");
  }
}

PFQuery editProfileRemove = PFQuery(className:"Delete");
editProfileRemove.updateUserProfileRemove { (posts: [PFObject]?, error: Error?) in
   if(error) {
      Log.e(TAG, "ERROR: Could not upload remove from user’s profile");
   } else if let posts = posts {
      Log.e(TAG, "Successfully removed from user’s profile ");
  }
}
Get Messages
PFQuery getMessages = PFQuery(className:"Get")
getMessages.attemptToQueryMessages { (posts: [PFObject]?, error: Error?) in
   if(error) {
      Log.e(TAG, "ERROR: Could not query messages");
   } else {
      Log.e(TAG, "Querying of messages was successful! ");
  }
}

```



