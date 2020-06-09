describe("facebook login", () => {
  before(() => {
    cy.fixture("URLS").then((data) => {
      cy.visit(`${data.spotifyLogin}`);
    });

    cy.fixture("Login/login-ids").then((data) => {
      self.loginIds = data;
    });
  });

  it("facebook login", () => {
    cy.get('[analytics-event="Facebook Button"]').click();
    cy.url().contains("facebook");

    cy.get("#email").type("cry.try124@gmail.com");
    cy.get("#pass").type("medodedo12");
    cy.get("#loginbutton").click();

    cy.server();
    cy.route({
      method: "POST",
      url: "https://www.spotify.com/eg-ar/password-reset/",
    }).as("resetPassword");
  });
});
