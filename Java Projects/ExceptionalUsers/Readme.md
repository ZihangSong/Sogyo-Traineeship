Exceptional users (required)
=
### We are creating a user management system. We need to be able to add users to our database. A user has a username and a password. The password should not be easy to guess, so it should consist of one lowercase character, one uppercase character and one number.
#
Create a static function that validates the password with the given constraint.<br>
Make a new function that takes a username and password as input. It should print that the user is registered if the password meets the condition. This is a mock for our database.<br>
Let the program ask for a username and a password.<br>

**1. $ java Users<br>
2. Enter a username<br>
3. > Sogyo<br>
4. Enter a password<br>
5. > Welcome123<br>
6. Registered user Sogyo.<br>**

If the function is called with a password that does not meet the requirements, the user should not be registered. The function should agressively make public that invalid input was entered. Throw an exception to let the error propagate through the program. If you enter an invalid password, you should see the error message and a stacktrace.<br>
The current output is not very user friendly. Use a catch clause to catch the exception and write a generic error message to the screen.

**1. $ java Users<br>
2. Enter a username<br>
3. > Sogyo<br>
4. Enter a password<br>
5. > sogyo<br>
6. Something has gone wrong.<br>**

You can use the catch block to handle user input, but this is not recommended. This anti-pattern is called “design by exception” and should be avoided. We therefore want a user-friendly check that does not use exceptions. Use the validation function that you’ve build before.

**1. $ java Users<br>
2. Enter a username<br>
3. > Sogyo<br>
4. Enter a password<br>
5. > sogyo<br>
6. This is not a valid password. Please enter a stronger password.<br>
7. > Welcome123<br>
8. Registered user Sogyo.<br>**

In normal use of the program, exceptions should not occur and invalid input is handled subtly. However, when a back-end function (like the one that registers the user) is called with invalid arguments, some programmer made the wrong assumptions regarding validation. In such an exceptional situation, an error must be raised and the program is allowed to crash.

Learning goals
-
Dealing with invalid input.<br>
Raising and handling exceptions.
