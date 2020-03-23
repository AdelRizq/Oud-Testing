/// <reference types="cypress"/>

describe("Logging In", () => {
  before(() => {
    cy.fixture("login-data").then(data => {
      self.loginData = data;
    });

    cy.fixture("login-ids").then(data => {
      self.loginIds = data;
    });

    cy.fixture("urls").then(data => {
      self.loginUrl = data.login;
    });
  });

  beforeEach(() => {
    cy.visit(`/${self.loginUrl}`);
  });

  it("greets with Sign in", () => {
    cy.get("#login-to-continue").should(
      "contain",
      "To continue, log in to Spotify."
    );
  });

  it("requires Email", () => {
    cy.get(`${self.loginIds.button}`).click();
    cy.get(`${self.loginIds.requiresEmail}`).should(
      "contain",
      `${self.loginData.requiresEmail}`
    );
  });

  it("requires Password", () => {
    cy.get(`${self.loginIds.button}`).click();
    cy.get(`${self.loginIds.requiresPassword}`).should(
      "contain",
      `${self.loginData.requiresPassword}`
    );
  });

  it("requires valid Email ans Password", () => {
    cy.get(`${self.loginIds.email}`).type("oud");
    cy.get(`${self.loginIds.password}`).type("oud");
    cy.get(`${self.loginIds.button}`).click();

    cy.get(`${self.loginIds.requiresBoth}`).should(
      "contain",
      `${self.loginData.requiresBoth}`
    );
  });

  it("link to Sign up", () => {
    cy.get(`${self.loginIds.signUpLink}`).click();
    cy.url().should("contain", "signup");
  });

  it("forgot pasword", () => {
    cy.get(`${self.loginIds.forgotPassword}`).click();
    cy.get("#form_input").type("oudtesting@gmail.com");
    cy.get("#form_send").click();
  });

  it.only("Successful login", () => {
    cy.login();

    cy.url().should("contain", "account/overview");
  });
});
