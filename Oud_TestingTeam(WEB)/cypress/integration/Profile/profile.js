describe("Profile", function () {
    it("All profile Features ",function(){
        cy.Login()
        cy.toprofile()
        cy.createlist()
    }) 
})