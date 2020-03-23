/// <reference types="cypress"/>

describe("Sign-Up", () => {
  before(() => {
    cy.fixture("urls").then(data => {
      self.signUpUrl = data.signUp;
    });

    cy.fixture("signUp-ids").then(data => {
      self.signUpIds = data;
    });

    cy.fixture("signUp-data").then(data => {
      self.signUpData = data;
    });
  });

  beforeEach(() => {
    cy.visit(`/${self.signUpUrl}`);
  });

  it("all fields are required", () => {
    cy.get(`#${self.signUpIds.button}`).click();

    cy.get(`${self.signUpIds.requiresEmail}`).should(
      "contain",
      `${self.signUpData.requiresEmail}`
    );

    cy.get(`${self.signUpIds.requiresConfirmEmail}`).should(
      "contain",
      `${self.signUpData.requiresConfirmEmail}`
    );

    cy.get(`${self.signUpIds.requiresPassword}`).should(
      "contain",
      `${self.signUpData.requiresPassword}`
    );

    cy.get(`${self.signUpIds.requiresDisplayName}`).should(
      "contain",
      `${self.signUpData.requiresDisplayName}`
    );

    cy.get(`${self.signUpIds.requiresYear}`).should(
      "contain",
      `${self.signUpData.requiresYear}`
    );

    cy.get(`${self.signUpIds.requiresMonth}`).should(
      "contain",
      `${self.signUpData.requiresMonth}`
    );

    cy.get(`${self.signUpIds.requiresDay}`).should(
      "contain",
      `${self.signUpData.requiresDay}`
    );

    cy.get(`${self.signUpIds.requiresGender}`).should(
      "contain",
      `${self.signUpData.requiresGender}`
    );
  });

  it("invalid email", () => {
    cy.get(`#${self.signUpIds.email}`).type(`oudtesting`);
    cy.get(`#${self.signUpIds.confirmEmail}`).type(`oudtesting`);

    cy.get(`#${self.signUpIds.button}`).click();

    cy.get(`${self.signUpIds.requiresEmail}`).should(
      "contain",
      `${self.signUpData.invalidEmail}`
    );
    cy.get(`${self.signUpIds.requiresConfirmEmail}`).should(
      "contain",
      `${self.signUpData.invalidEmail}`
    );
  });

  it("Short password", () => {
    cy.get(`#${self.signUpIds.password}`).type("1234567");
    cy.get(`#${self.signUpIds.button}`).click();

    cy.get(`${self.signUpIds.requiresPassword}`).should(
      "contain",
      `${self.signUpData.shortPassword}`
    );
  });

  it("Login link", () => {
    cy.get(`#${self.signUpIds.loginLink}`).click();

    cy.url().should("contain", "login");
  });

  it("Successful sign-up", () => {
    cy.get(`#${self.signUpIds.email}`).type(`${self.signUpData.email}`);

    cy.get(`#${self.signUpIds.confirmEmail}`).type(
      `${self.signUpData.confirmEmail}`
    );

    cy.get(`#${self.signUpIds.password}`).type(`${self.signUpData.password}`);

    cy.get(`#${self.signUpIds.displayName}`).type(
      `${self.signUpData.displayName}`
    );

    cy.get(`#${self.signUpIds.year}`).type(`${self.signUpData.year}`);

    cy.get(`#${self.signUpIds.month}`).select(`${self.signUpData.month}`);

    cy.get(`#${self.signUpIds.day}`).type(`${self.signUpData.day}`);

    cy.get(`#${self.signUpIds.gender}`).click(); //(`${self.signUpData.gender}`);

    cy.get(`#${self.signUpIds.button}`).click();
  });
});
