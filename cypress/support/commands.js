// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })

Cypress.Commands.add("login", () => {
  cy.get(`#${self.loginIds.email}`).type(self.loginData.email);
  cy.get(`#${self.loginIds.password}`).type(self.loginData.password);
  cy.get(`#${self.loginIds.button}`).click();
});

Cypress.Commands.add("OudChange_Password",()=>{
/*cy.get('[id="set new password"]').click({force:true})
cy.contains('Please fill out this field')*/
// the previous test is made to check that the password won't be changed if you didn't enter any data 
// but the error message can't be catched ??!! 

cy.fixture('Account/Data.json').then((Data)=>{
  cy.get(Data.ChangePasswordButton).click({force:true})
  cy.get(Data.CurrentPasswordBox).type('123')
  cy.get(Data.SubmitButton).click({force:true})
  cy.contains(Data.WrongPasswordMessage)
// if you entered invalid password 

  cy.get(Data.CurrentPasswordBox).clear().type(Data.Password)
  cy.get(Data.NewPasswordBox).clear().type(Data.WrongNewPassword)
  cy.get(Data.ConfirmPasswordBox).clear().type(Data.NewPassword)
  cy.get(Data.SubmitButton).click({force:true})
  cy.contains(Data.DismatchPasswordsMessage)
// if the password in the second box is not identical ... 

  cy.get(Data.CurrentPasswordBox).clear().type(Data.Password)
  cy.get(Data.NewPasswordBox).clear().type(Data.NewPassword)
  cy.get(Data.ConfirmPasswordBox).clear().type(Data.NewPassword)
  // if y
  cy.get(Data.SubmitButton).click({force:true})
  cy.contains(Data.ChangingPasswordSuccessfullyMessage)

  cy.reload()
  cy.get(Data.CurrentPasswordBox).clear().type(Data.Password)
  cy.get(Data.NewPasswordBox).clear().type(Data.WrongNewPassword)
  cy.get(Data.ConfirmPasswordBox).clear().type(Data.WrongNewPassword)
  // if y
  cy.get(Data.SubmitButton).click({force:true})
  cy.contains(Data.WrongPasswordMessage)
})
})

Cypress.Commands.add("CheckOudpattern",(Pattern)=>{
  cy.fixture('Account/Data.json').then((Data)=>{
  cy.get(Data.EmailBoxID)
  .clear()
  .type(Pattern)
  cy.contains(Data.InvalidEmailMessage)
})
})

Cypress.Commands.add("CheckOudaccountoverview",()=>{
  /* 
    this command is completley independent  it is used to check the working of the overview page and its
    buttons .. 
  */
 
  // it is supposed to use the login command 
  cy.fixture('URLS.json','Account/Data.json').then((URLS_messages_AllData)=>{
  cy.visit(URLS_messages_AllData.AccountURL)
  cy.get(URLS_messages_AllData.AccountOverviewID).click({force:true})
  cy.url()
    .should('eq',URLS_messages_AllData.OverViewURL)
  cy.scrollTo(0, 500)           
  cy.contains('EDIT BROFILE')
    .click({force:true})

 // cy.wait(3000)
  cy.url()
    .should('eq',URLS_messages_AllData.EditProfileURL)

  cy.go('back') //  the route won't be checked again when I get back bc it is a browser feature 
 
  cy.scrollTo(0, 1000)     
  cy.contains('JOIN PREMIUM')
    .click({force:true})
  cy.url()
    .should('eq',URLS_messages_AllData.JoinPremiumURL)
  cy.contains('Dummy Sign out and Dummy Get Premium Page')
  cy.go('back')
  cy.scrollTo(0, 1500)     
  cy.contains('SIGN OUT').click({force:true})
  cy.url()
    .should('eq',URLS_messages_AllData.SignoutURL)
  cy.contains('Dummy Sign out and Dummy Get Premium Page')
  cy.go('back')
  }) 
})
Cypress.Commands.add("CheckOudpattern",(Pattern)=>{
  cy.fixture('AllData.json').then((Data)=>{
  cy.get(Data.EmailBoxID)
  .clear()
  .type(Pattern)
  cy.contains(Data.InvalidEmailMessage)
})
})

Cypress.Commands.add("Check_Start_Com_with_invalid_char",()=>{
  cy.CheckPattern('123@gamil.')
  var text = '123@gmail.'
  
  for (var i = 33 ; i<=47; i++){
    if(i==45)
      continue; 
    var char = String.fromCharCode(i); 
    var newtext = text+char+'a' ;
    cy.CheckPattern(newtext)
  }
  //cy.CheckPattern('123@gmail.0a')
  for (var i =58 ; i<64 ; i++ )
  {
    var char = String.fromCharCode(i); 
    var newtext = text+char+'a' ;
    cy.CheckPattern(newtext)
  }

  for (var i = 91  ; i<=96;i++){
    var char = String.fromCharCode(i); 
    var newtext = text+char+'a' ;
    cy.CheckPattern(newtext)
  }

  for (var i = 123 ; i<=126; i++) {
    var char = String.fromCharCode(i); 
    var newtext = text+char+'a' ;
    cy.CheckPattern(newtext)
  }

  cy.CheckPattern('123@gamil.1231a fd') // check the space inside the 
})

Cypress.Commands.add("check_end_Com_With_invalid_char",()=>{

  var text = '123@gmail.'

  for (var i = 33 ; i<=47; i++){
    if(i==45)
      continue; // this test fails so I want to except this to continue the tests 
    var char = String.fromCharCode(i); 
    var newtext = text+'a'+char ;
    cy.CheckPattern(newtext)
  }
  //cy.CheckPattern('123@gmail.a0')
  for (var i =58 ; i<64 ; i++ )
  {
    var char = String.fromCharCode(i); 
    var newtext = text+'a'+char ;
    cy.CheckPattern(newtext)
  }

  for (var i = 91  ; i<=96;i++){
    var char = String.fromCharCode(i); 
    var newtext = text+'a'+char ;
    cy.CheckPattern(newtext)
  }

  for (var i = 123 ; i<=126; i++){
    var char = String.fromCharCode(i); 
    var newtext = text+'a'+char ;
    cy.CheckPattern(newtext)
  }  
})

Cypress.Commands.add("Check_invalid_char_in_middle_in_Com",()=>{
var text='123@gamil.c'
  for (var i = 33 ; i<=44; i++){
    var char = String.fromCharCode(i); 
    var newtext = text+char+'om' ;
    cy.CheckPattern(newtext )
  }

  cy.CheckPattern('123@gamil.c/om' ) 
  // instead of ending the loop at 47 and adding if cond. 
  // in the loop i will test  it outside the loop .. 
  for (var i = 58 ; i<=64 ; i++){
    var char = String.fromCharCode(i); 
    var newtext = text+char+'om' ;
    cy.CheckPattern(newtext )
  }

  for (var i = 91  ; i<=96;i++){
      if(i!=95){ 
        var char = String.fromCharCode(i); 
        var newtext = text+char+'a' ;
        cy.CheckPattern(newtext )
      }
  }

  for (var i = 123 ; i<=126; i++){
    if(i!=126){ 
      var char = String.fromCharCode(i); 
      var newtext = text+char+'a' ;
      cy.CheckPattern(newtext )
    }
  }
})

Cypress.Commands.add("Check_Start_at_with_invalid_char",( )=>{
  var text = '123@'

  for (var i = 33 ; i<=47; i++){
    if(i==45)
    continue; // this test fails so I want to except this to continue the tests 
    var char = String.fromCharCode(i); 
    var newtext = text+char+'gmail.com' ;
    cy.CheckPattern(newtext )
  }
  for (var i = 58 ; i<=64 ; i++){
    var char = String.fromCharCode(i); 
    var newtext = text+char+'gmail.com' ;
    cy.CheckPattern(newtext )
  }
  for (var i = 91  ; i<=96;i++){
    var char = String.fromCharCode(i); 
    var newtext = text+char+'gmail.com' ;
    cy.CheckPattern(newtext )
  }

  for (var i = 123 ; i<=126; i++) {
    var char = String.fromCharCode(i); 
    var newtext = text+char+'gmail.com' ;
    cy.CheckPattern(newtext )
  }
})
Cypress.Commands.add("Check_end_at_with_invalid_char",( )=>
{
var text = '123@'

  for (var i = 33 ; i<=47; i++){
    if(i==45)
    continue; // this test fails so I want to except this to continue the tests 
    var char = String.fromCharCode(i); 
    var newtext = text+'gmail'+char+'.com' ;
    cy.CheckPattern(newtext )
  }

  for (var i = 58 ; i<=64 ; i++){
    var char = String.fromCharCode(i); 
    var newtext = text+'gmail'+char+'.com' ;
    cy.CheckPattern(newtext )
  }

  for (var i = 91  ; i<=96;i++){
    var char = String.fromCharCode(i); 
    var newtext = text+'gmail'+char+'.com' ;
    cy.CheckPattern(newtext )
  }

  for (var i = 123 ; i<=126; i++) {
    var char = String.fromCharCode(i); 
    var newtext = text+'gmail'+char+'.com' ;
    cy.CheckPattern(newtext )
  }
})

Cypress.Commands.add("Check_Middle_at_with_invalid_char",( )=>
{
  var text = '123@g'
  for (var i = 33 ; i<=44; i++){
    var char = String.fromCharCode(i); 
    var newtext = text+char+'mail.com' ;
    cy.CheckPattern(newtext )
  }
  cy.CheckPattern('123@g/mail.com' )
  // instead of adding if condition for just two char which are (. and - )
  // the loop will stop in , and the / test is written manually 
  for (var i = 58 ; i<=64 ; i++){
    var char = String.fromCharCode(i); 
    var newtext = text+char+'mail.com' ;
    cy.CheckPattern(newtext )
  }

  for (var i = 91  ; i<=94;i++){
    var char = String.fromCharCode(i); 
    var newtext = text+char+'mail.com' ;
    cy.CheckPattern(newtext )
  }
  cy.CheckPattern('123@g\'mail.com' )
  // instead of writting if condition inside the loop for just one char which is (_) 
  // It will stop on (^) and write the last test outside the loop 

  for (var i = 123 ; i<=125; i++) {
    var char = String.fromCharCode(i); 
    var newtext = text+char+'mail.com' ;
    cy.CheckPattern(newtext )
  }

})


Cypress.Commands.add("Check_The_Word_Pre_At",( )=>
{

  cy.CheckPattern('abd"alla@gamil.com' )
  cy.CheckPattern('abdalla"@gamil.com' )
  cy.CheckPattern('"abdalla@gamil.com' )
  cy.CheckPattern('abd(alla@gamil.com' )
  cy.CheckPattern('(abdalla@gamil.com' )
  cy.CheckPattern('abdalla)@gamil.com' )
  cy.CheckPattern('abd@alla@gamil.com' )
  cy.CheckPattern('abdalla@@gamil.com' )
  cy.CheckPattern('@abdalla@gamil.com' )
  cy.CheckPattern('abd]alla@gamil.com' )
  cy.CheckPattern('abdalla]@gamil.com' )
  cy.CheckPattern('[abdalla@gamil.com' )
  cy.CheckPattern('abd\\alla@gamil.com' )
  cy.CheckPattern('abdalla\\@gamil.com' )
  cy.CheckPattern('\\abdalla@gamil.com' )
  cy.CheckPattern('abd;alla@gamil.com' )
  cy.CheckPattern('abdalla;@gamil.com' )
  cy.CheckPattern(';abdalla@gamil.com' )
  cy.CheckPattern('abd:alla@gamil.com' )
  cy.CheckPattern('abdalla:@gamil.com' )
  cy.CheckPattern(':abdalla@gamil.com' )
  cy.CheckPattern('abd<alla@gamil.com' )
  cy.CheckPattern('abdalla>@gamil.com' )
  cy.CheckPattern('<abdalla@gamil.com' )
// THIS FUCNTION WILL HANDLE ALL TEST CASES THAT IS NOT COVERED IN 
// IN THE PREVIUOS 
  cy.CheckPattern('.' )
  cy.CheckPattern('...' )
  cy.CheckPattern('@gmail.com' )
 // cy.CheckPattern('abdalla@gmail' )
  cy.CheckPattern('.com' )
  cy.CheckPattern('!' )
  cy.CheckPattern('abdalla.com' )
  cy.CheckPattern('abdalla@gmail.' )
  cy.CheckPattern('abdalla@' )
  cy.CheckPattern('abdalla' )

})

Cypress.Commands.add("OudCheck_The_Box",( )=>
{
  cy.fixture('Account/Data.json').then((Data)=>{
  cy.get(Data.EditProfileID).click({force:true})})
  cy.Check_Start_Com_with_invalid_char( )
  cy.check_end_Com_With_invalid_char( )
  cy.Check_invalid_char_in_middle_in_Com( )

  cy.Check_Start_at_with_invalid_char( )
  cy.Check_end_at_with_invalid_char( )
  cy.Check_Middle_at_with_invalid_char( )

  cy.Check_The_Word_Pre_At( )

})


