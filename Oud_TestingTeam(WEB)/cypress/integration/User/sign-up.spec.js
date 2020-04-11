/// <reference types="cypress"/>

describe("Sign-Up", () => {
  before(() => {
    cy.fixture("URLS").then((data) => {
      self.signUpUrl = data.signUp;
    });

    cy.fixture("SignUP/signUp-ids").then((data) => {
      self.signUpIds = data;
    });

    cy.fixture("SignUP/signUp-data").then((data) => {
      self.signUpData = data;
    });
  });

  beforeEach(() => {
    cy.visit(`/${self.signUpUrl}`);
  });

  it("all fields are required", () => {
    cy.get(`${self.signUpIds.button}`).click();

    cy.get(`${self.signUpIds.requiresDisplayName}`).should("be.visible");
    cy.get(`${self.signUpIds.requiresEmail}`).should("be.visible");

    // should contain specific message to differ it from weak password
    cy.get(`${self.signUpIds.requiresPassword}`).should(
      "contain",
      `${self.signUpData.requiresPassword}`
    );

    // currently, it will contain until giving it an id
    cy.contains(`${self.signUpIds.requiresBirthDate}`).should("be.visible");
    cy.get(`${self.signUpIds.requiresGender}`).should("be.visible");
  });

  it("invalid emails", () => {
    cy.get(`${self.signUpIds.email}`).then(() => {
      for (let i of self.signUpData.invalidEmails) {
        cy.get(`${self.signUpIds.email}`).clear();
        cy.get(`${self.signUpIds.email}`).type(i);

        cy.get(`${self.signUpIds.button}`).click();
        cy.get(`${self.signUpIds.requiresEmail}`).should(
          "contain",
          `${self.signUpData.invalidEmail}`
        );
      }
    });
  });

  it("short passwords", () => {
    cy.get(`${self.signUpIds.password}`).then((password) => {
      for (let i of signUpData.shortPasswords) {
        cy.get(`${self.signUpIds.password}`).clear();
        cy.get(`${self.signUpIds.password}`).type(i);

        cy.get(`${self.signUpIds.requiresPassword}`).should(
          "contain",
          `${self.signUpData.shortPassword}`
        );
      }
    });
  });

  it("weak passwords", () => {
    cy.get(`${self.signUpIds.password}`).then((password) => {
      for (let i of signUpData.weakPasswords) {
        cy.get(`${self.signUpIds.password}`).clear();
        cy.get(`${self.signUpIds.password}`).type(i);

        cy.get(`${self.signUpIds.requiresPassword}`).should(
          "contain",
          `${self.signUpData.weakPassword}`
        );
      }
    });
  });

  it("valid birthday", () => {
    cy.get(`${self.signUpIds.button}`).click();

    cy.get(`${self.signUpIds.year}`).select("1990");
    cy.get(`${self.signUpIds.month}`).select("May");
    cy.get(`${self.signUpIds.day}`).select("19");

    cy.contains(`${self.signUpIds.requiresBirthDate}`).should("be.visible");
  });

  it("too young age", () => {
    cy.get(`${self.signUpIds.button}`).click();

    cy.get(`${self.signUpIds.year}`).select("2018");
    cy.get(`${self.signUpIds.month}`).select("May");
    cy.get(`${self.signUpIds.day}`).select("15");
    cy.get(`${self.signUpIds.button}`).click();

    cy.contains(`${self.signUpIds.requiresBirthDate}`).should("be.visible");
  });

  it("successful sign-up", () => {
    cy.get(`${self.signUpIds.displayName}`).type(
      `${self.signUpData.displayName}`
    );

    cy.get(`${self.signUpIds.email}`).type(`${self.signUpData.email}`);

    cy.get(`${self.signUpIds.password}`).type(`${self.signUpData.password}`);
    cy.get(`${self.signUpIds.confirmPassword}`).type(
      `${self.signUpData.confirmPassword}`
    );

    cy.get(`${self.signUpIds.gender}`).select(`${self.signUpData.gender}`);

    cy.get(`${self.signUpIds.year}`).select(`${self.signUpData.year}`);
    cy.get(`${self.signUpIds.month}`).select(`${self.signUpData.month}`);
    cy.get(`${self.signUpIds.day}`).select(`${self.signUpData.day}`);

    cy.get(`${self.signUpIds.acceptCheck}`).click();

    cy.server();
    cy.route({
      method: "POST",
      url: "/users",
    }).as("new-user");

    cy.get(`${self.signUpIds.button}`).click();
    cy.wait("@new-user").should("have.property", "status", 201);
  });

  it("sign-up without accepting the terms & conditions", () => {
    cy.get(`${self.signUpIds.email}`).type(`${self.signUpData.email}`);

    cy.get(`${self.signUpIds.password}`).type(`${self.signUpData.password}`);
    cy.get(`${self.signUpIds.confirmPassword}`).type(
      `${self.signUpData.confirmPassword}`
    );

    cy.get(`${self.signUpIds.displayName}`).type(
      `${self.signUpData.displayName}`
    );

    cy.get(`${self.signUpIds.year}`).select(`${self.signUpData.year}`);
    cy.get(`${self.signUpIds.month}`).select(`${self.signUpData.month}`);
    cy.get(`${self.signUpIds.day}`).select(`${self.signUpData.day}`);

    cy.get(`${self.signUpIds.gender}`).select(`${self.signUpData.gender}`);

    cy.server();
    cy.route({
      method: "POST",
      url: "/users",
    }).as("new-user");

    cy.get(`${self.signUpIds.button}`).click();
    cy.wait("@new-user").should("not.exist");
  });

  it("login link", () => {
    cy.get(`${self.signUpIds.loginLink}`).should("exist");
  });
});
