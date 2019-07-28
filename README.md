# changepwd
Demo program for changing passwords. Use old_password = fakePassword to prevent password from validating that the old_password does not match with the current password.

# Docker
See: https://cloud.docker.com/repository/docker/ammpedro/changepwd

# Pre-requisites:
1. java 1.8.0_05
2. jUnit and hamcrest jars (https://github.com/junit-team/junit4/wiki/Download-and-Install)


# To run and compile program via cmd
```
javac ChangePasswordDemo.java
jar -cf ChangePasswordDemo.jar ChangePasswordDemo.class
java ChangePasswordDemo <old_password> <new_password>
```

# Example Input
```
# Fake old password is accepted, new password contains uppercase characters, 
# lowercase characters, digits, and special characters
java ChangePasswordDemo fakePassword 2Qh97QQQyKJxJXNhp@

# Old password is invalid
java ChangePasswordDemo password1 2Qh97QQQyKJxJXNhp@

# New password should be at least 18 characters in length
java ChangePasswordDemo password1 C$8aE5G8Hv

# New password contains the invalid special character: ?
java ChangePasswordDemo password1 VrsXhFPG?7V6ev6HyB</new_password></old_password>
```

# To run and compile tests via cmd
```
javac -cp junit-4.12.jar:hamcrest-core-1.3.jar:ChangePasswordDemo.jar ChangePasswordDemoTest.java
java -cp ./:junit-4.12.jar:hamcrest-core-1.3.jar:ChangePasswordDemo.jar org.junit.runner.JUnitCore ChangePasswordDemoTest
```
