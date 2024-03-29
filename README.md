# Stock Market Simulator

---
This project features a Stock Market Simulator, implemented using an Object Oriented Programming language with a team of 6. Built under the constraints of a Software Development Life Cycle (SDLC) with Agile Development. Listed is the documentation and process of creation alongside the functionality and usage of the program.

## Features
- **Admin/User Simulation:** On booting up the interface, you are faced with the option to enter the simulation as either an Admin or User. Admins are able to add and remove users from the system, as well as manage their cart and add/remove stocks. Users can interact with the stock market system itself, by adding or removing stocks to their cart, deposit or withdraw funds, and export transaction logs.
- **Login Simulation:** When setting up the program you are also prompted to register with an email and username, which adds your credentials temporarily to the system and assigns you a corresponding user ID. This also applies to accounts created through the admin command.
- **Extensive Manual Page:** By calling the command `man`, you are greeted with a plethora of different commands and their corresponding functionality description. The page is descriptive enough to give the user an idea of how to use the program in its entirety.
- **Rigorously Tested:** Included in the source folder are also the test cases for this program; with the help of JUnit. The program was tested for and passed all validity checks for CRUD functionality (CREATE, READ, UPDATE, DELETE).

## Creation Process
To boot, we created a Gantt chart to assign timeframes to certain tasks, and get a general idea of the lifespan of the development.
Figure 1:
![image](https://lh3.googleusercontent.com/keep-bbsk/ALhRneF8SmFldfAgMbYrm0nE_I6gyKfcuC_Q9PXQJE6k543s38xZZreeTncJrToM_QCmBaeIPpN-cDxrCPT8iMUwsc5V_85efvZAFDwD2I2zmjDV-C25=s512)

After which, we created a list of functional and non-functional requirements- proceedingly creating a class diagra


To run this system:

```bash
$ cd src
$ java MarketInterface.java
```
