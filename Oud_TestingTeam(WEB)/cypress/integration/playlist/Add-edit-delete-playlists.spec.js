/// <reference types="cypress"/>

describe("Add, edit and delete playlists", () => {
  before(() => {
    cy.visit("/");

    cy.contains("Log in").click();
    cy.get("#login-username").type("cry.try124@gmail.com");
    cy.get("#login-password").type("Medodedo21");
    cy.get("#login-button").click();
  });

  it("Add, edit and delete playlists", () => {
    cy.wait(8000);

    cy.contains("Create Playlist").click();
    cy.get('[placeholder="New Playlist"]').type("Adele");
    cy.contains("CREATE").click();

    cy.get('[aria-label="Search"]').click();
    cy.get('[data-testid="search-input"]').type("Adele");
    cy.get('[href="/artist/4dpARuHxo51G3z768sgnrY"]')
      .first()
      .click({ force: true });

    cy.get('[class="tracklist-name ellipsis-one-line"]')
      .first()
      .then(($addedSong) => {
        cy.get('[class="tracklist-name ellipsis-one-line"]')
          .first()
          .rightclick();
        cy.contains("Add to Playlist").click();
        cy.wait(2000);
        cy.get('[class="cover-art shadow cover-art--with-auto-height"]')
          .first()
          .click();

        cy.wait(2000);
        cy.get(".RootlistItem__link").first().click();
        cy.get('[class="tracklist-name ellipsis-one-line"]', { timeout: 8000 })
          .first()
          .then(($existSong) => {
            expect($existSong.text()).equal($addedSong.text());
          });

        cy.get(".RootlistItem__link").first().rightclick();
        cy.contains("Rename").click();
        cy.get(".TextInput__input").type("Dola{enter}");
        cy.wait(1000);
        cy.get(".RootlistItem__link")
          .first()
          .then(($renamed) => {
            expect($renamed.text()).equal("Dola");
          });

        cy.get(".RootlistItem__link").first().rightclick();
        cy.contains("Delete").click();
        cy.wait(1000);
        cy.contains("DELETE").click();

        cy.wait(2000);
        cy.get(".RootlistItem__link")
          .first()
          .should(($div) => {
            const text = $div.text();
            expect(text).not.to.include("Dola");
          });
      });
  });
});
