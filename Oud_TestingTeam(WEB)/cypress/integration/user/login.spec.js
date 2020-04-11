/// <reference types="cypress"/>

describe("Logging In", () => {
  before(() => {
    cy.fixture("Login/login-data").then((data) => {
      self.loginData = data;
    });

    cy.fixture("Login/login-ids").then((data) => {
      self.loginIds = data;
    });

    cy.fixture("URLS").then((data) => {
      self.loginUrl = data.login;
      self.forgotUrl = data.forgotPassword;
    });
  });

  beforeEach(() => {
    cy.visit(`/${self.loginUrl}`);
  });

  it("valid password empty email", () => {
    cy.server();
    cy.route({
      method: "POST",
      url: "/login",
      response: "{test1: test2}",
    }).as("login");

    cy.get(`${self.loginIds.password}`).type(self.loginData.password);
    cy.get(`${self.loginIds.button}`).click();

    cy.get("@login").should("not.exist");

    // cy.get(`${self.loginIds.requiresEmail}`).should(
    //   "contain",
    //   `${self.loginData.requiresEmail}`
    // );
  });

  it("valid email empty password", () => {
    cy.server();
    cy.route({
      method: "POST",
      url: "/login",
      response: "{test1: test2}",
    }).as("login");

    cy.get(`${self.loginIds.email}`).type(self.loginData.email);
    cy.get(`${self.loginIds.button}`).click();

    cy.get("@login").should("not.exist");

    // cy.get(`${self.loginIds.requiresPassword}`).should(
    //   "contain",
    //   `${self.loginData.requiresPassword}`
    // );
  });

  it("invalid email", () => {
    cy.server();
    cy.route({
      method: "POST",
      url: "/login",
      response: "{test1: test2}",
    }).as("login");

    cy.get(`${self.loginIds.password}`).type(`${self.loginData.password}`);

    cy.get(`${self.loginIds.email}`).then(() => {
      for (let i of self.loginData.invalidEmails) {
        cy.get(`${self.loginIds.email}`).clear();
        cy.get(`${self.loginIds.email}`).type(i);

        cy.get(`${self.loginIds.button}`).click();
        cy.get(`${self.loginIds.requiresEmail}`).should(
          "contain",
          `${self.loginData.invalidEmail}`
        );
        //cy.get("@login").should("not.exist");
      }
    });
  });

  it("short passwords", () => {
    cy.server();
    cy.route({
      method: "POST",
      url: "/login",
      response: "{test1: test2}",
    }).as("login");

    cy.get(`${self.loginIds.email}`).type(`${self.loginData.email}`);

    cy.get(`${self.loginIds.password}`).then(() => {
      for (let i of self.loginData.shortPasswords) {
        cy.get(`${self.loginIds.password}`).clear();
        cy.get(`${self.loginIds.password}`).type(i);

        cy.get(`${self.loginIds.button}`).click();
        cy.get(`${self.loginIds.requiresPassword}`).should(
          "have.html",
          `${self.loginData.shortPassword}`
        );
        //cy.get("@login").should("not.exist");
      }
    });
  });

  it("weak passwords", () => {
    cy.server();
    cy.route({
      method: "POST",
      url: "/login",
      response: "{test1: test2}",
    }).as("login");

    cy.get(`${self.loginIds.email}`).type(`${self.loginData.email}`);

    cy.get(`${self.loginIds.password}`).then(() => {
      for (let i of self.loginData.weakPasswords) {
        cy.get(`${self.loginIds.password}`).clear();
        cy.get(`${self.loginIds.password}`).type(i);

        cy.get(`${self.loginIds.button}`).click();
        cy.get(`${self.loginIds.requiresPassword}`).should(
          "have.html",
          `${self.loginData.waekPassword}`
        );
        //cy.get("@login").should("not.exist");
      }
    });
  });

  it("link to Sign up", () => {
    cy.get(`${self.loginIds.signUpLink}`).should("contain.html", "SIGN UP");
  });

  it("forgot pasword", () => {
    cy.server();
    cy.route({
      method: "POST",
      url: "/ForgottenPasswords",
      response: {
        email: "Dola@gmail.com",
      },
    }).as("sendEmail");

    cy.get(`${self.loginIds.forgotPassword}`).click();
    cy.get(`${self.loginIds.forgotPasswordEmail}`).type(
      "oudtesting@gmail.com{enter}"
    );

    cy.get("@sendEmail").should("exist");
  });

  it("invalid emails in forgot password", () => {
    cy.server();
    cy.route({
      method: "POST",
      url: "/login",
      response: "{test1: test2}",
    }).as("login");

    cy.get(`${self.loginIds.forgotPassword}`).click();

    cy.get(`${self.loginIds.forgotPasswordEmail}`).then(() => {
      for (let i of self.loginData.invalidEmails) {
        cy.get(`${self.loginIds.forgotPasswordEmail}`).clear();
        cy.get(`${self.loginIds.forgotPasswordEmail}`).type(i);

        cy.get(`${self.loginIds.forgotPasswordButton}`).click();
        cy.get(`${self.loginIds.requiresEmail}`).should(
          "contain",
          `${self.loginData.invalidEmail}`
        );
        //cy.get("@login").should("not.exist");
      }
    });
  });

  it("Successful login", () => {
    cy.server();
    cy.route({
      method: "POST",
      url: "login",
      response: "{as:as}",
    }).as("login");

    cy.login();

    cy.get("@login").should("exist");
  });
});
