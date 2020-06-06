/// <reference types="cypress"/>

describe("Search", () => {
    
    before("Login",() => {
    
      cy.fixture("Search/search-data").then((data) => {
        self.searchData = data;
      });
      cy.visit('/');
    
      cy.contains('Log In').click().then(()=>{
        cy.get(`#${self.searchData.username}`).type(Cypress.env('username'));
        cy.get(`#${self.searchData.password}`).type(Cypress.env('password'));
        cy.get(`#${self.searchData.loginButton}`).click().then(()=>{
            cy.wait(1000);
            cy.get('span').contains('Spotify').click().then(()=>{
                cy.get(`#${self.searchData.launchWebPlayer}`).click();
            });
        });
      });
    });
    beforeEach(() => {
        Cypress.Cookies.preserveOnce();
    });

    it ('library is empty ',()=>{

    });
});