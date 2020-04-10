/// <reference types="Cypress"/>

describe('Next/Prev', () => {
    before('vistit Oud', () => {
        cy.fixture('Player/playerIDs.json').then((data) => {
            self.playerIds = data;
        })
        cy.visit('/').then(() => {
            cy.wait(1);

        });
    });

    it('next Button/ previous Button', () => {
        cy.get(`[data-testid='${self.playerIds.queue}']`).click().then(() => {
            cy.wait(1000).then(() => {
                cy.get(`[data-testid='${self.playerIds.trackName}']`).then((trackName) => {
                    let oldName = trackName.text();
                    let tName = oldName;
                    let nextTrackName = "";
                    //getting the next track's name in the queue 
                    //getting the div contains the current track then get it's parent that has class= track then get the next sibling
                    //then get the track name within it 
                    cy.get(`[data-testid='${self.playerIds.queueTrack}']`).contains(tName).parents('.track').next().within(() => {
                        cy.get(`[data-testid='${self.playerIds.queueTrack}']`).within(() => {
                            cy.get('a').then((nextTrack) => {
                                nextTrackName = nextTrack.text();
                            });
                        });
                    });

                    cy.get(`[data-testid='${self.playerIds.next}']`).click().then(() => {
                        tName = trackName.text();
                        expect(tName).eq(nextTrackName);
                    });

                    cy.get(`[data-testid='${self.playerIds.prev}']`).click().then(() => {
                        tName = trackName.text();
                        expect(tName).eq(oldName);
                    });

                })
            });

        });
    });

});