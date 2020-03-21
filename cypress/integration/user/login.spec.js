/// <reference types="cypress"/>

describe("Logging In", () => {
  beforeEach(() => {
    cy.visit("/login");
  });

  before(() => {
    cy.fixture("login-data").then(data => {
      self.loginData = data;
    });
    cy.fixture("login-ids").then(data => {
      self.loginIds = data;
    });
  });

  it("greets with Sign in", () => {
    cy.get("#login-to-continue").should(
      "contain",
      "To continue, log in to Spotify."
    );
  });

  it("requires Email", () => {
    cy.get(`#${self.loginIds.button}`).click();
    cy.get(`${self.loginIds.requiresEmail}`).should(
      "contain",
      `${self.loginData.requiresEmail}`
    );
  });

  it("requires Password", () => {
    cy.get(`#${self.loginIds.button}`).click();
    cy.get(`${self.loginIds.requiresPassword}`).should(
      "contain",
      `${self.loginData.requiresPassword}`
    );
  });

  it("requires valid Email ans Password", () => {
    cy.get(`#${self.loginIds.email}`).type("oud");
    cy.get(`#${self.loginIds.password}`).type("oud");
    cy.get(`#${self.loginIds.button}`).click();

    cy.get(`${self.loginIds.requiresBoth}`).should(
      "contain",
      `${self.loginData.requiresBoth}`
    );
  });

  it("link to Sign up", () => {
    cy.get(`#${signUpLink}`).click();
    cy.url().should("contain", "signup");
  });

  it("Successful login", () => {
    cy.login();
  });
});
