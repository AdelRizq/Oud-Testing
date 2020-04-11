# ⚒ OUD_TestingTeam 
This project is tracking the code of The testing team in OUD Application  
This project test OUD Application as E2E testing , It tests its Android version and Frontend Version 👿👿
### Getting start 
Before any thing you should execute some commands and install some files to be able to run this code in your local devices firstly we will  discuss how to setup the environment for web testing 😀😀
## Prerequisites for web 

What things you need to install the software and how to install them

- **clone the repo** 

```
git clone https://github.com/Thebrownboy/Oud_TestingTeam.git
```
- **then run this command**
```
cd "Oud_TestingTeam(WEB)"
```
```
npm install cypress 
```
- **then run this command** 
```
npm install -D cypress-xpath
```
- **then run this command** 
```
npm run test 
```

- **The will ask you for access to your web browser so give the access and enjoy watching the automation test**
## Android Environment Setup 

- then run this command 
```
npm install -g appium
```
- after installtion run this command 

```
appium 
```
 it will start the server 
- then connect your mobile phone to the computer  and allow the developer options(Debugger mode)
- then run this command 
```
abd devices 
```
- take the id form the terminal 
- then go to /src/test/java/ open  BaseClass.java by any texteditor 
- then go "setCaps" Function  
- Edit your device name , put the udid , Change your Platfrom and Change the PlatformVersion
- then run the test File that you want 
