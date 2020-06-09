describe("reset Password", () => {
  before(() => {
    cy.fixture("URLS").then((data) => {
      cy.visit(`${data.resetPassword}`);
    });

    cy.fixture("Login/login-ids").then((data) => {
      self.loginIds = data;
    });
  });

  it("reset password", () => {
    cy.get("#form_input").type("cry.try124@gmail.com");
    cy.server();
    cy.route({
      method: "POST",
      url: "https://www.spotify.com/eg-ar/password-reset/",
    }).as("resetPassword");
    wait(2000);

    cy.get(`#form_send`).click();
    cy.wait("@resetPassword").should("have.property", "status", 200);
  });
});
