NerdUp 

README 5/7/2018

This app is a simple app are for competetive gamers.  The main point of this app is it will allow users to look up other users profiles to read, edit, and create notes on other users.
Users will be able to create their own profile and look at notes on themselves but are unable to edit notes on themselves, that would defeat the purpose of this app.  This will allow
users to look up notes that other have put, let others look at the notes they have taken, and look at notes that people have put on them.

Notes:
- Google sign in users cannot edit their username or profile picture
- to change profile picture while in edit just click on your profile picture

Features:
- Can log in using google sign in
- connected to a firebase database
- upload or take picture for profile
- send an email for forgotten password
- send email for changing password
- simple navigation using actionbar
- custom floating hints for edit texts
- autocomplete search on all users except yourself
- live updates of profiles

Known Issues
- app can be slow with updates when connecting with the database
- issues when updating username, it points to a different document when changing username so data will not allways be correct when changing username; can be fixed by using keys but ran out of time
- if users have same username it will grab the first instance in the database, need to implement key creation and use key to fix this just ran out of time
- currently can remove any notes and add notes without digression; needs checks by adding admin and moderators
- everyone has the same permissions at the moment; was unable to implement permission creation
- if app becomes disconnected from the database the app loses a majority of the features; need to implement the offline firebase for the app just ran out of time
- right now to use the app you must have access to a camera otherwise you cannot use the phone; put it in the manifest that you need access to camera can change so that all users can use but
    was unable to implement because of time

*currently have been testing using the latest android and apk 21 so do not know if it works with older phones

