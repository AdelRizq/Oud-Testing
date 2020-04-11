describe("Queue", () => {
  before("vistit Oud", () => {
    cy.fixture("Player/playerIDs.json").then((data) => {
      self.playerIds = data;
    });
    cy.visit("/").then(() => {
      cy.wait(1);
    });
  });
  it("Queue btn and play a track from it", () => {
    cy.get(`[data-testid='${self.playerIds.queue}']`)
      .click()
      .then((queue) => {
        cy.get(".track").should("be.visible");
        cy.get(".close-btn")
          .should("be.visible")
          .click()
          .then(() => {
            cy.get(".track").should("not.be.visible");
            queue.click();
          });
        cy.get(`[data-testid='${self.playerIds.trackName}']`).then(
          (curentTrack) => {
            cy.get(`[data-testid='${self.playerIds.totalTime}']`).then(
              (totalCurrentTime) => {
                let trackName = "";
                let duration = "";
                cy.get("a")
                  .contains("Story of a child")
                  .parents(".track")
                  .within((track) => {
                    cy.get(".duration").within(() => {
                      cy.get("text").then((durationText) => {
                        duration = durationText.text();
                      });
                    });
                    cy.get(
                      `[data-testid='${self.playerIds.queueTrack}']`
                    ).within(() => {
                      cy.get("a").then((tName) => {
                        trackName = tName.text();
                      });
                    });
                    cy.get(`[data-testid='${self.playerIds.queuePlay}']`)
                      .click({ force: true })
                      .then(() => {
                        let curentTrackName = curentTrack.text();
                        let currentTotalTime = totalCurrentTime.text();

                        expect(trackName).eq(curentTrackName);
                        expect(currentTotalTime).eq(duration);

                        cy.wait(5000).then(() => {
                          currentTotalTime = totalCurrentTime.text();
                          expect(currentTotalTime).eq(duration);
                        });
                      });
                  });
              }
            );
          }
        );
      });
  });

  it("play another track from queue while playing", () => {
    cy.wait(5000).then(() => {
      cy.get(`[data-testid='${self.playerIds.currentTime}']`).then(
        (currentTime) => {
          cy.get("a")
            .contains("Dorak gai")
            .parents(".track")
            .within((track) => {
              cy.get(`[data-testid='${self.playerIds.queuePlay}']`)
                .click({ force: true })
                .then(() => {
                  let timeText = currentTime.text();
                  expect(timeText).eq("0.01");
                });
            });
        }
      );
    });
  });

  it("delete From Queue", () => {
    cy.get(`[data-testid='${self.playerIds.queue}']`).click();
    cy.get(`[data-testid='${self.playerIds.deleteQueue}']`).then(
      (deleteButton) => {
        cy.get("a")
          .contains("Dorak gai")
          .parents(".track")
          .within((track) => {
            cy.get(`[data-testid='${self.playerIds.optionMenu}']`)
              .click({ force: true })
              .then(() => {
                deleteButton.click();
              });
          });
        cy.get(`[data-testid='${self.playerIds.queueTrack}']`).should(
          "not.have.text",
          "Dorak gai"
        );
        cy.get(`[data-testid='${self.playerIds.trackName}']`).should(
          "not.have.text",
          "Dorak gai"
        );
      }
    );
  });

  it("click on the name of the track", () => {
    cy.get("a")
      .contains("Story of a child")
      .click({ force: true })
      .then(() => {
        cy.get(`[data-testid='${self.playerIds.trackName}']`).should(
          "have.text",
          "Story of a child"
        );
      });
  });
});
