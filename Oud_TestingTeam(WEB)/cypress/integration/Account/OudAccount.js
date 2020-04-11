describe('Checking all the features in the account ', function (){
  it(' Routes',function() {
    cy.CheckRoutes()
  })
  it('Check the Validation in the box',function() {

    cy.OudCheck_The_Box()
   })
  it(' Access the list && checking the routes',function() {
    cy.CheckOudaccountoverview()
  })
  it ('check Email is token',function(){
  })
  it('Check the Validation in the box for some pattern like  123@gmail.-a ',function() {
    cy.CheckInvalidPatterns(1)
  })
  it('Check the Validation in the box for some pattern like 123@gmail.a-',function() {
    cy.CheckInvalidPatterns(2)
  })
  it('Check the Validation in the box for some pattern like 123@gmail',function() {
    cy.CheckInvalidPatterns(3)
  })
  it('Check the Validation in the box for some pattern like 123@gmail.0a ',function() {
    cy.CheckInvalidPatterns(4)
  })
  it('check the changing password',function(){
    
   cy.OudChange_Password()
  })
  it('check the Password changing ',function(){
    
   cy.CheckChangingpassword()
    
   })
   it('check changing the Email',function(){
    cy.ChangeTheEmail()
   })
   it ('check if the box of the Email changing ',function(){
     cy.ChangeTheEmail()
     cy.CheckChangingTheEmail()
   })
})

// the four email is valid in the real cycle 
// but there are invalid here !!!
