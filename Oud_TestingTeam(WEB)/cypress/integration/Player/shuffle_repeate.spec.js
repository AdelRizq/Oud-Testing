/// <reference types="Cypress"/>

describe("Shuffle/Repeat", () => {
  before("vistit Oud", () => {
    cy.fixture("Player/playerIDs.json").then((data) => {
      self.playerIds = data;
    });
    cy.visit("/").then(() => {
      cy.wait(1);
    });
  });

  it("shuffle Button", () => {
    cy.wait(1000);
    cy.get(`[data-testid='${self.playerIds.shuffle}']`)
      .click()
      .then((shuffle) => {
        cy.get(`[data-testid='${self.playerIds.trackName}']`).then(
          (trackName) => {
            let oldName = trackName.text();
            let tName = oldName;
            let nextTrackName = "";
            cy.get(`[data-testid='${self.playerIds.queueTrack}']`)
              .contains(tName)
              .parents(".track")
              .next()
              .within(() => {
                cy.get(`[data-testid='${self.playerIds.queueTrack}']`).within(
                  () => {
                    cy.get("a").then((nextTrack) => {
                      nextTrackName = nextTrack.text();
                    });
                  }
                );
              });
            cy.get(`[data-testid='${self.playerIds.next}']`)
              .click()
              .then(() => {
                cy.wait(1000).then(() => {
                  tName = trackName.text();
                  expect(tName).not.eq(nextTrackName);
                  shuffle.click();
                });
              });
          }
        );
      });
  });

  it("repeate Button", () => {
    cy.wait(1000);
    cy.get(`[data-testid='${self.playerIds.repeate}']`)
      .click()
      .then((repeate) => {
        cy.get(`[data-testid='${self.playerIds.trackName}']`).then(
          (trackName) => {
            let tName = trackName.text();
            cy.get(`[data-testid='${self.playerIds.progressBar}']`)
              .click("right")
              .then(() => {
                //wait till the end of the track
                cy.wait(5000).then(() => {
                  let nName = trackName.text();
                  expect(tName).eq(nName);
                });
              });
            cy.log("Do it once more to make sure");
            // do it once More
            cy.get(`[data-testid='${self.playerIds.progressBar}']`)
              .click("right")
              .then(() => {
                //wait till the end of the track
                cy.wait(5000).then(() => {
                  let nName = trackName.text();
                  expect(tName).eq(nName);
                  repeate.click();
                });
              });
          }
        );
      });
  });
});
