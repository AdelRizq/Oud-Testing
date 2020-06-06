/// <reference types="cypress"/>

describe("Welcome", () => {
    before(() => {
      cy.fixture("Welcome/welcome-data").then((data) => {
        self.welcomeData = data;
      });
      cy.visit('/');
    });
    afterEach(()=>{
        cy.go('back');
    });

    it("static ", () => {
        cy.contains(`${self.welcomeData.stringInWelcome}`).should('exist');
        cy.title().should('eq',`${self.welcomeData.welcomeTitle}`);
        cy.get(`[title="${self.welcomeData.instagram}"]`).should('have.attr','href').and('contain','instagram').and('contain','spotify');
        cy.get(`[title="${self.welcomeData.facebook}"]`).should('have.attr','href').and('contain','facebook').and('contain','Spotify');
        cy.get(`[title="${self.welcomeData.twitter}"]`).should('have.attr','href').and('contain','twitter').and('contain','spotify');
        cy.get('footer').should('contain.html','Company').and('contain.html','Communities').and('contain.html','Useful links');
        cy.get(`#${self.welcomeData.getSpotifyFree}`).click().then(()=>{
            cy.url().should('contain','signup');     
        });

    });

    // it("Log in button", () => {
    //     cy.contains("Log In").click().then(()=>{
    //         cy.url().should("contain","login");
    //         cy.title().should('eq',`${self.welcomeData.loginTitle}`);
    //         cy.get(`#${self.welcomeData.loginButton}`).should('exist');
    //         cy.contains(`${self.welcomeData.stringInLogin}`).should('exist');
    //     });

    // });

    // it("Sign up button", () => {
    //     cy.contains("Sign up").click().then(()=>{
    //         cy.url().should("contain","signup");
    //         cy.title().should('eq',`${self.welcomeData.signupTitle}`);
    //         cy.get('button').contains(`${self.welcomeData.signupButton}`).should('exist');
    //         cy.contains(`${self.welcomeData.stringInSignup}`).should('exist');
    //     });
    // });

    // it("Help button", () => {
    //     cy.contains("Help").click().then(()=>{
    //         cy.url().should("contain","support");
    //         cy.title().should('eq',`${self.welcomeData.helpTitle}`);
    //         cy.contains(`${self.welcomeData.stringInHelp}`).should('exist');
    //     });
    // });

    // it("Premium button", () => {
    //     cy.contains("Premium").click().then(()=>{
    //         cy.url().should("contain","premium");
    //         cy.title().should('eq',`${self.welcomeData.premiumTitle}`);
    //         cy.contains(`${self.welcomeData.stringInPremium}`).should('exist');
    //     });
    // });

    // it("Download button", () => {
    //     cy.contains("Download").click().then(()=>{
    //         cy.url().should("contain","download");
    //         cy.title().should('eq',`${self.welcomeData.downloadTitle}`);
    //         cy.contains(`${self.welcomeData.stringInDownload}`).should('exist');
    //     });
    // });
});