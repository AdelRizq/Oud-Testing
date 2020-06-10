
describe("Static pages ", function () {
    it("Categories  ",function(){
        cy.catpage()
    }) 
    it("Locations page",function(){
        cy.locationpage()
    }) 
    it("Seach for job page",function(){
        cy.searchforjobs()
    }) 
    it ("favorite jobs ",function(){

        cy.myfavorite()

    })
})
//you should add more assertions on the urls .. 
