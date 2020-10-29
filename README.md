# TaskMaster

## Overview

A taskLocal app to learn the ins and outs of Android app development.

## Ongoing Work:

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
