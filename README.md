# TaskMaster

## Overview

A taskLocal app to learn the ins and outs of Android app development.

## Ongoing Work:

### 2020-11-12 Upload to Google Play Console!

I went through and created (and paid for) a Google Developer account. Cool stuff! I then went through many of the tasks necessary to release an app on the Google Play app store, everything excepting privacy, and having the program check my aab to see if it passes the checks (which it likely would not, currently).

<br/><br/>
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-12%20App%20bundle%20explorer_covered.jpg?raw=true" width="200"/>
<br/><br/>
What we have here is the potential 1.0 of Taskmaster! With enough polish, and care, it could be. Until then, it lies in wait here.
<br/><br/>
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/taskmasterL.png?raw=true" width="200"/>
<br/><br/>
No app is complete without an icon. This is Taskmaster's official (stand-in) icon.
<br/><br/>

<br/><br/>
### 2020-11-10 adMob
<br/><br/>
Admob banner integration.
<br/><br/>
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-11%20admob.png?raw=true" width="200"/>
<br/><br/>
### 2020-11-10 Location Services working
<br/><br/>
The location's address is in fact coming from the location info provided. Also, created a latitude and longitude "entity"/"model" that is fully formed and has a one-to-one relationship to a task, so I will (should) be able to get latitude and longitude easily enough.
 <img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-10%20location.png?raw=true" width="200"/>
<br/><br/>
### 2020-11-09 image from Intent
<br/><br/>
Showcasing the "Share screen" from another activity
<br/><br/>
 <img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-09_via_Intent.png?raw=true" width="200"/>
<br/><br/>
Showcasing Result
<br/><br/>
 <img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-09_from_Internet.png?raw=true" width="200"/>
<br/><br/>
### 2020-11-04 Setup systems for simple Push Notifications from AWS Pinpoint and Google Firebase
 <br/><br/>
 <img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-04%20Phone%20Notifications.png.png?raw=true" width="200"/>
 <br/><br/>
(Details of notifications from the respective consoles below)
<br/><br/>
 <img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-04%20Amazon%20PinPoint%20-%20small.png?raw=true" width="200"/>
 <br/><br/>
  <img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-04%20FireBase%20Notification%20Support%20-%20small.png?raw=true" width="200"/>
 <br/><br/>

### 2020-11-03 Upload and display a photo (with integration to Amazon s3 storage)
~NOTE: saving photo as a one to one relationship with a task ongoing. Did not reach the end goal on 2020-11-03.~
Update: refactored. no longer attempting a 1-1 relationship, but key is now stored as value in the Task object (and accordingly reflected in the dynamoDB).

 <br/><br/>
 <img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-04%20task_detailsWpic.png?raw=true" width="200"/>
 <br/><br/>
 <img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-03%20AddPicProgress.png?raw=true" width="200"/>
 <br/><br/>

###
Incorporate Cognito: Sign up and login via Amazon Amplify Cognito now in place.

==== Sign Up Screens ====
<br/><br/>
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-03%20tm%20SignUp.png?raw=true" width="200"/>
<br/><br/>
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-03%20tm%20Confirm.png?raw=true" width="200"/>
<br/><br/>
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-03%20tm%20Login.png?raw=true" width="200"/>
<br/><br/>
==== Current Signed In Experience ====
<br/><br/>
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-03%20tm%20Signed_In.png?raw=true" width="200"/>
<br/><br/>
==== Functioning Logout Capability ====
<br/><br/>
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-11-03%20tm_clear.png?raw=true" width="200"/>
<br/><br/>
###
General Work: significant refactor, zombie code removed, helper methods created.
=======
- Add Task Activity now has spinner for Team selection
- Add Task Activity now has spinner for Status selection
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-10-29%20newAddTaskLayout.png_2.png?raw=true" width="200"/>
=======
<br/><br/>
### 2020-10-28
=======
- Main Activity now showing only selected team's tasks
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-10-29%20HOME.png?raw=true" alt="drawing" width="200"/>
=======
<br/><br/>
=======
- Add Task Activity has (working) option to assign task to a team.
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-10-29%20%20ADD_TASK.png?raw=true" alt="drawing" width="200"/>
=======
<br/><br/>
=======
- Settings Activity now saves user's team preference.
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-10-29%20SETTINGS.png?raw=true" alt="drawing" width="200"/>
=======

### 2020-10-27
====
<br/><br/>
- [x] ```To Do``` tasks now fully wired (both adding and getting) via aws api Dynamo db.
====

### 2020-10-26
====
<br/><br/>
- [x] Espresso test now incorporated for:
    - Save settings persisting
    - New taskLocal able to be added, persists via Room db persistence.
    - Task details persisting, viewable on Task Details activity view.
====

### 2020-10-22
=======
- Main Activity updated with RecycleView now being populated from wired Room database
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-10-22%20tm%20Home.png?raw=true" alt="drawing" width="200"/>
=======
<br/><br/>
=======
- Add Task Page taskLocal status field added, now creates Task object and then adds into database upon Add Task button onclick.
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-10-22%20tm%20Add%20Task.png?raw=true" alt="drawing" width="200"/>
=======
<br/><br/>
### 2020-10-21
=======
- Main Activity updated with RecycleView of all taskLocals (hardcoded atm)
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-10-21%20tm_HomeScreen_MainActivity.png?raw=true" alt="drawing" width="200"/>
=======
<br/><br/>
=======
- RecycleViewer wired, necessary fragment created, placed in activities accordingly.
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/2020-10-21%20tm_DetailsPage.png?raw=true" alt="drawing" width="200"/>
=======
<br/><br/>
### 2020-10-20

=======

- Main Activity updated with buttons w onClick listeners, passing key value pairs via sharedPreferences.
- Main Activity dynamically updated title via Settings' username key value.
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/tm_MainActivity_v2.png?raw=true" alt="drawing" width="200"/>
=======
<br/><br/>
=======
<br/><br/>
- Settings activity created, key value pair via sharedPreferences stored and passed back to Main Activity
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/tm_settings.png?raw=true" alt="drawing" width="200"/>
=======
<br/><br/>
======= 
<br/><br/>
- Task Details Activity created, currently showing title via the button text from Main Activity.
<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/taskDetailsTrio.png?raw=true" alt="drawing" width="600"/>
=======

### 2020-10-19

=======
- Created Main Activity activity with onClickListeners to Add Tasks and All Tasks 

<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/Screenshot_1603175491.png" alt="drawing" width="200"/>
=======
<br/><br/>
=======

- Created Add Tasks activity and a Toast notification when a taskLocal is added (then moves to the All Tasks activity)

<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/Screenshot_1603175498.png" alt="drawing" width="200"/>
=======
<br/><br/>
=======

- Created All Tasks activity

<img src="https://github.com/PVOBrien/taskmaster/blob/main/screenshots/Screenshot_1603175402.png" alt="drawing" width="200"/>
