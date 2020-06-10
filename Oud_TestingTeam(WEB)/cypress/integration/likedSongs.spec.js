/// <reference types="cypress"/>

describe("liked songs", () => {
  before(() => {
    cy.visit("/");

    cy.contains("Log in").click();
    cy.get("#login-username").type("cry.try124@gmail.com");
    cy.get("#login-password").type("Medodedo21");
    cy.get("#login-button").click();
  });

  it("liked songs", () => {
    cy.wait(8000);
    cy.get(".react-contextmenu-wrapper", { timeout: 20000 }).eq(1).click();

    cy.get('[class="tracklist-name ellipsis-one-line"]')
      .first()
      .then(($likedSong) => {
        cy.get('[class="tracklist-name ellipsis-one-line"]')
          .first()
          .rightclick();

        cy.contains("Save to your Liked Songs").click();
        cy.contains("Liked Songs").click();

        cy.get('[class="tracklist-name ellipsis-one-line"]')
          .first()
          .then(($songsInLikedSongsPage) => {
            expect($songsInLikedSongsPage.text()).equal($likedSong.text());
          });

        cy.get('[class="tracklist-name ellipsis-one-line"]')
          .first()
          .rightclick("center");

        cy.contains("Remove from your Liked Songs").click();

        cy.contains("It's a bit empty here...").should("exist");
      });
  });
});
