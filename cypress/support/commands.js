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
    cy.get(`${self.loginIds.email}`).type(self.loginData.email);
    cy.get(`${self.loginIds.password}`).type(self.loginData.password);
    cy.get(`${self.loginIds.button}`).click();

    // cy.request({
    //   method: "get",
    //   url: "accounts.spotify.com/en/login"
    // }).then(() => {
    //   cy.url().should("contain", "login");
    // });
});
Cypress.Commands.add("Login", () => {
    cy.visit("/");
    cy.contains('Log In').click({ force: true });
    cy.get("#login-username").type(Cypress.env('username'));
    cy.get("#login-password").type(Cypress.env('password'));
    cy.get("#login-button").click();
    cy.get('.mh-header-primary .svelte-1gcdbl9').contains("Spotify").click();
    cy.get("#segmented-desktop-launch").click();
})