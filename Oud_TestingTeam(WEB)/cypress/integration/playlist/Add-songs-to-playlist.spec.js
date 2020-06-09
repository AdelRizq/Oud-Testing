/// <reference types="cypress"/>

describe("Add songs to a playlist", () => {
  beforeEach(() => {
    cy.visit("/");

    cy.contains("Log in").click();
    cy.get("#login-username").type("cry.try124@gmail.com");
    cy.get("#login-password").type("Medodedo21");
    cy.get("#login-button").click();

    cy.fixture("URLS.json").then((URLSData) => {
      self.URLS = URLSData;
    });
    cy.fixture("Home/Data.json").then((DataID) => {
      self.Data = DataID;
    });
  });

  it("AddSongsToPlaylist", () => {
    cy.wait(8000);
    cy.get(".react-contextmenu-wrapper", { timeout: 12000 }).eq(1).click();

    cy.get('[class="tracklist-name ellipsis-one-line"]')
      .first()
      .then((addedsong) => {
        cy.get('[class="tracklist-name ellipsis-one-line"]')
          .first()
          .rightclick("center");

        cy.contains("Add to Playlist").click();
        cy.get(
          '[class="cover-art shadow cover-art--with-auto-height"]'
        ).click();

        cy.contains("oud Testing").click();
        cy.get('[class="tracklist-name ellipsis-one-line"]', { timeout: 8000 })
          .first()
          .then((removedSong) => {
            expect(removedSong.text).equal(addedsong.text);
          });
      });
  });

  it("remove Songs from playlist", () => {
    cy.wait(8000);
    cy.contains("oud Testing").click();
    cy.get('[class="tracklist-name ellipsis-one-line"]').first().rightclick();
    cy.contains("Remove from this Playlist").click();
  });
});
